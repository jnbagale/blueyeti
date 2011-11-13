INCLUDES = -Wall $(GLIB_CFLAGS) $(DBUS_CFLAGS) $(SQLITE3_CFLAGS) $(LIBSOUP_CFLAGS) $(GTHREAD_CFLAGS)  $(BLUEZ_CFLAGS)

bin_PROGRAMS = blueyeti server

blueyeti_SOURCES = main.c scan.c scan.h log.c log.h marshal.c marshal.h  l2-client.c l2-client.h config.h blueyetilog.db 

blueyeti_LDADD = $(GLIB_LIBS) $(DBUS_LIBS) $(SQLITE3_LIBS) $(LIBSOUP_LIBS) $(GTHREAD_LIBS) $(BLUEZ_LIBS)

blueyeti_DATA = blueyetilog.db

blueyetidir= /var/lib/misc

server_SOURCES = l2-server.c

server_LDADD = $(GLIB_LIBS) $(BLUEZ_LIBS)