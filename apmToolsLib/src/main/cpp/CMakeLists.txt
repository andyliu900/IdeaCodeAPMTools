cmake_minimum_required(VERSION 3.22.1)
project("native_hook")

# 添加编译选项
add_compile_options(-fno-omit-frame-pointer)

find_package(bytehook REQUIRED CONFIG)

add_library(
        native_hook
        SHARED
        native_hook.cpp
)

add_library(
        mock_native_fun
        SHARED
        mock_native_fun.cpp
)

find_library(
        log-lib
        log
)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++17")

target_link_libraries(
        native_hook
        ${log-lib}
        bytehook::bytehook
)

target_link_libraries(
        mock_native_fun
        ${log-lib}
)