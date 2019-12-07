@echo off

echo Determining JAVA_HOME...
if exist %java_home% (
    set "javaCompilerExecutablePath=%java_home%\bin\javac.exe"
    del /f sources.txt 2>nul
    echo Creating sources file for compiling...
    for /f "delims=" %%f in ('dir /b /s /c *.java') do @echo "%%f" >> sources.txt
    pause
    echo Deleting Tests class from sources...
    findstr /V "test" sources.txt > sourcesOut.txt
    del /f sources.txt
    ren sourcesOut.txt sources.txt
    pause
    echo Modifying sources file for Windows compilation...
    powershell -Command "(Get-Content sources.txt).replace('\', '/') | Set-Content sources.txt"
    pause
    echo Compiling...
    start "javac" "%javaCompilerExecutablePath%" "-d" "bin" "@sources.txt" "-verbose"
    echo Done !
    pause
    del /f sources.txt 2>nul
    exit
) else (
    echo Cannot resolve JAVA_HOME env path...
    echo Try to set it up.
    pause
    exit
)
