set (CMAKE_INSTALL_PREFIX /home/ayu/Desktop/study/Make/CMake/Tutorial)

add_library(MathFunctions mysqrt.cxx)

install (TARGETS MathFunctions DESTINATION bin)
install (FILES MathFunctions.h DESTINATION include)
