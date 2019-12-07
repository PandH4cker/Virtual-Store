@echo off

echo Determining JAVA_HOME...
if exist %java_home% (
    echo Determining bin folder...
    for /f %%A in ('powershell -Command "Test-Path bin -PathType Container"') do if "%%A" == "True" (
        set "javaExecutablePath=%java_home%\bin\java.exe"
        pause
        echo Running...
        start "java" "%javaExecutablePath%" "-Dfile.encoding=UTF-8" "-classpath" "bin" "store.business.gui.view.VirtualStoreView"
        echo Exiting...
        pause
        exit
    ) else (
        echo You need to run compile.bat first...
        pause
        exit
    )
)