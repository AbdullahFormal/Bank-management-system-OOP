# PowerShell script to run JUnit tests

Write-Host "Running tests..." -ForegroundColor Cyan
Write-Host ""

java -jar lib\junit-platform-console-standalone-1.10.1.jar execute --class-path bin --scan-class-path

Write-Host ""
Write-Host "Tests completed!" -ForegroundColor Green
