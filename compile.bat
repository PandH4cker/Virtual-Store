@echo off

if exist %java_home% (
    set "javaExecutablePath=%java_home%\bin\java.exe"
    "%javaExecutablePath%" "-version"
)

pause