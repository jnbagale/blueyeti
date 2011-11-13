#ifndef SCAN_H_
#define SCAN_H_

typedef struct {
  sqlite3 *dbHandle;
  gint verbose;
  DBusGProxy *dbusObject;
} blueyetiObject;

blueyetiObject *setupService( DBusGConnection *connection, 
                              sqlite3 *db, 
                              gboolean verbose );
                              
void cleanupService(blueyetiObject *bobj);

gboolean findDevices(DBusGProxy *obj);

#endif
