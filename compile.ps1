# PowerShell script to compile Java source and test files

Write-Host "Compiling source files..." -ForegroundColor Cyan

# Compile source files
javac -d bin src\*.java
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Source compilation failed!" -ForegroundColor Red
    exit 1
}
Write-Host "Source files compiled successfully!" -ForegroundColor Green

Write-Host "`nCompiling test files..." -ForegroundColor Cyan

# Compile test files
javac -cp "bin;lib\junit-platform-console-standalone-1.10.1.jar" -d bin test\*.java
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Test compilation failed!" -ForegroundColor Red
    exit 1
}
Write-Host "Test files compiled successfully!" -ForegroundColor Green

Write-Host "`nAll files compiled successfully!" -ForegroundColor Green
