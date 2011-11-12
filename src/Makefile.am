INCLUDES = -Wall $(GLIB_CFLAGS) $(DBUS_CFLAGS) $(SQLITE3_CFLAGS) $(LIBSOUP_CFLAGS) $(GTHREAD_CFLAGS) $(LIBREST_CFLAGS)

bin_PROGRAMS = blueyeti

blueyeti_SOURCES = scan.c scan.h log.c log.h marshal.c marshal.h tweet.c tweet.h config.h btlog.db main.c

blueyeti_LDADD = $(GLIB_LIBS) $(DBUS_LIBS) $(SQLITE3_LIBS) $(LIBSOUP_LIBS) $(GTHREAD_LIBS) $(LIBREST_LIBS)

blueyeti_DATA = btlog.db

blueyetidir= /var/lib/misc