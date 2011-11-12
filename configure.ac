AC_INIT(blueyeti, 0.0.1, dummy@email.com)
AM_INIT_AUTOMAKE
AC_CONFIG_SRCDIR([src/])
AM_CONFIG_HEADER([src/config.h])

AC_DEFINE([PROGNAME],["blueyeti"],["bluetooth"])

# Checks for programs.
AC_PROG_CC

# Checks for header files.
AC_HEADER_STDC

# Checks for typedefs, structures, and compiler characteristics.
AC_C_CONST
AC_TYPE_SIZE_T

# Checks for library functions.
AC_FUNC_MALLOC

# check for gthread
PKG_CHECK_MODULES(GTHREAD, gthread-2.0)

# check for librest
PKG_CHECK_MODULES(LIBREST, rest-0.7)

# check for glib
PKG_CHECK_MODULES(GLIB, [glib-2.0])

# check for dbus
PKG_CHECK_MODULES(DBUS, [dbus-1 dbus-glib-1])

# check for libsoup
PKG_CHECK_MODULES(LIBSOUP, [libsoup-2.4])

AC_CONFIG_FILES([Makefile src/Makefile])

AC_OUTPUT
