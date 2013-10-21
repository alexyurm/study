cmake_minimum_required(VERSION 2.8)
SET(LIBHELLO_SRC, hello.c)
#ADD_LIBRARY(hello SHARED "${LIBHELLO_SRC}")
ADD_LIBRARY(hello SHARED hello.c)
