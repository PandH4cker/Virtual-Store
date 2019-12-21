@echo off

echo Determining JAVA_HOME...
if exist %java_home% (
    set "javaCompilerExecutablePath=%java_home%\bin\javac.exe"
    del /f testsSources.txt 2>nul
    echo Creating sources file for compiling...
    for /f "delims=" %%f in ('dir /b /s /c *.java') do @echo "%%f" >> testsSources.txt
    pause
    echo Modifying sources file for Windows compilation...
    powershell -Command "(Get-Content testsSources.txt).replace('\', '/') | Set-Content testsSources.txt"
    pause
    echo Compiling...
    start "javac" "%javaCompilerExecutablePath%" "-d" "bin" "-classpath" "lib/junit-jupiter-engine-5.5.2.jar;lib/apiguardian-api-1.1.0.jar;lib/junit-platform-engine-1.5.2.jar;lib/opentest4j-1.2.0.jar;lib/junit-platform-commons-1.5.2.jar;lib/junit-jupiter-api-5.5.2.jar;lib/junit-platform-runner-1.5.2.jar;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar;lib/junit-platform-launcher-1.5.2.jar;lib/junit-platform-suite-api-1.5.2.jar;lib/junit-jupiter-params-5.5.2.jar" "@testsSources.txt" "-verbose"
    echo Done !
    pause
    echo Launching tests...
    del /f testsSources.txt 2>nul
    set "javaExecutablePath=%java_home%\bin\java.exe"
    pause
    start "java" "%javaExecutablePath%" "-Dfile.encoding=UTF-8" "-classpath" "bin;lib\junit-jupiter-engine-5.5.2.jar;lib\apiguardian-api-1.1.0.jar;lib\junit-platform-engine-1.5.2.jar;lib\opentest4j-1.2.0.jar;lib\junit-platform-commons-1.5.2.jar;lib\junit-jupiter-api-5.5.2.jar;lib\junit-platform-runner-1.5.2.jar;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar;lib\junit-platform-launcher-1.5.2.jar;lib\junit-platform-suite-api-1.5.2.jar;lib\junit-jupiter-params-5.5.2.jar" "store.business.test.TestRunner"
    echo Exiting...
    pause
)