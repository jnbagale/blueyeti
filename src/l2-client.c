#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <bluetooth/bluetooth.h>
#include <bluetooth/l2cap.h>

#include "config.h"

static int retry = 0;

int client_process(char *message, char *mac)
{
    struct sockaddr_l2 addr = { 0 };
    int s, status;
    // allocate a socket
    s = socket(AF_BLUETOOTH, SOCK_SEQPACKET, BTPROTO_L2CAP);

    // set the connection parameters (who to connect to)
    addr.l2_family = AF_BLUETOOTH;
    addr.l2_psm = htobs(0x1001);
    str2ba( mac, &addr.l2_bdaddr );

    // connect to server
    status = connect(s, (struct sockaddr *)&addr, sizeof(addr));

    // send a message
    if( status == 0 ) {
      status = write(s, message, strlen(message));
      printf("sent message to %s\n",mac);
      retry = 0;
    }

    if( status < 0 ) 
      {
	perror("couldn't send message");
	retry++;
	if(retry <5) 
	  {
	    printf("Retrying to send message again\n");
	    client_process(message,mac);
	  }
      }

    close(s);

    return 0;
}

