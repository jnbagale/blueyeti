#ifndef LOG_H_
#define LOG_H_

sqlite3 *openLog(char *file);
void closeLog(sqlite3 *db);
int logDevice(sqlite3 *db, char *mac, char *name);

#endif
