#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <bluetooth/bluetooth.h>
#include <bluetooth/l2cap.h>

int client_process(char *message, char *mac)
{
    struct sockaddr_l2 addr = { 0 };
    int s, status;
    //char *message = "hello!";
    //char dest[18] = "68:A3:C4:48:8D:8E";

    // allocate a socket
    s = socket(AF_BLUETOOTH, SOCK_SEQPACKET, BTPROTO_L2CAP);

    // set the connection parameters (who to connect to)
    addr.l2_family = AF_BLUETOOTH;
    addr.l2_psm = htobs(0x1001);
    str2ba( dest, &addr.l2_bdaddr );

    // connect to server
    status = connect(s, (struct sockaddr *)&addr, sizeof(addr));

    // send a message
    if( status == 0 ) {
      status = write(s, message, strlen(message));
    }

    if( status < 0 ) perror("message could not be sent");

    close(s);

    return 0;
}

