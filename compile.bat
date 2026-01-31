@echo off
REM Compile all source files
echo Compiling source files...
javac -d bin src\*.java
if %errorlevel% neq 0 (
    echo ERROR: Source compilation failed!
    exit /b 1
)
echo Source files compiled successfully!

REM Compile test files
echo Compiling test files...
javac -cp "bin;lib\junit-platform-console-standalone-1.10.1.jar" -d bin test\*.java
if %errorlevel% neq 0 (
    echo ERROR: Test compilation failed!
    exit /b 1
)
echo Test files compiled successfully!
echo.
echo All files compiled successfully!
