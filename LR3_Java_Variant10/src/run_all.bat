@echo off
echo === Compile ===
javac Task1Matrix.java Task2Building.java Task3Inheritance.java Task4AbstractInterface.java
if errorlevel 1 (
    echo Compilation error.
    pause
    exit /b 1
)
echo.
echo === Task 1 ===
java Task1Matrix
echo.
echo === Task 2 ===
java Task2Building
echo.
echo === Task 3 ===
java Task3Inheritance
echo.
echo === Task 4 ===
java Task4AbstractInterface
pause
