#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <bluetooth/bluetooth.h>
#include <bluetooth/l2cap.h>
#include <glib.h>
#include <stdlib.h>

#include "config.h"

int server_process()
{
  printf("start of server process \n");

  struct sockaddr_l2 loc_addr = { 0 }, rem_addr = { 0 };
  char buf[1024] = { 0 };
  int s, client, bytes_read;
  socklen_t opt = sizeof(rem_addr);

  // allocate socket
  s = socket(AF_BLUETOOTH, SOCK_SEQPACKET, BTPROTO_L2CAP);

  // bind socket to port 0x1001 of the first available 
  // bluetooth adapter
  loc_addr.l2_family = AF_BLUETOOTH;
  loc_addr.l2_bdaddr = *BDADDR_ANY;
  loc_addr.l2_psm = htobs(0x1001);

  bind(s, (struct sockaddr *)&loc_addr, sizeof(loc_addr));

  // put socket into listening mode
  listen(s, 1);

  // accept one connection
  client = accept(s, (struct sockaddr *)&rem_addr, &opt);

  ba2str( &rem_addr.l2_bdaddr, buf );
  fprintf(stderr, "accepted connection from %s\n", buf);

  memset(buf, 0, sizeof(buf));

  // read data from the client
  bytes_read = read(client, buf, sizeof(buf));
  if( bytes_read > 0 ) {
    printf("received [%s]\n", buf);
  }

  // close connection
  close(client);
  close(s);
  printf("End of server process \n");
  return 0;
}

int main(int argc, char **argv)
{

  GMainLoop *mainloop = NULL;
  GError *error = NULL;
  GOptionContext *context;
  gboolean verbose = FALSE;

  
  GOptionEntry entries[] = 
  {
    { "verbose", 'v', 0, G_OPTION_ARG_NONE, &verbose, "Verbose output", NULL },
    { NULL }
  };

  context = g_option_context_new ("- send information over bluetooth");
  g_option_context_add_main_entries (context, entries, PACKAGE_NAME);
  
  if (!g_option_context_parse (context, &argc, &argv, &error)) {
    g_printerr("option parsing failed: %s\n", error->message);
    exit (EXIT_FAILURE);
  }
  
  mainloop = g_main_loop_new(NULL, FALSE);  
  if (mainloop == NULL) {
    g_printerr("Couldn't create GMainLoop\n");
    exit(EXIT_FAILURE);
  }


  // wait scan_freq seconds then call findDevices
  g_timeout_add( 10000, (GSourceFunc)server_process, NULL);

  g_main_loop_run(mainloop);
  
  return EXIT_FAILURE;  


  
}
