@echo off
REM Run all JUnit tests
echo Running tests...
echo.
java -jar lib\junit-platform-console-standalone-1.10.1.jar execute --class-path bin --scan-class-path
echo.
echo Tests completed!
