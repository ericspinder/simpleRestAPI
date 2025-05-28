@echo off
REM test.bat

REM Function to run the application with a specific profile
:run_app_with_profile
echo Starting application with profile: %1
start /b mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=%1
timeout /t 15 /nobreak > nul
goto :eof

REM Function to run JMeter tests
:run_jmeter_tests
echo Running JMeter tests: %1
RD /S /Q "target/jmeter.reports"
REM C:\jmeter\apache-jmeter-5.6.3\apache-jmeter-5.6.3\bin\jmeter -n -t src/test/jmeter/SimpleRestAPI_Test_Plan.jmx -l results_%1.jtl -e -o reports/%1

goto :eof


REM Test local profile
call :run_app_with_profile local
call :run_jmeter_tests local
taskkill /f /im java.exe

REM Test dev profile
call :run_app_with_profile dev
call :run_jmeter_tests dev
taskkill /f /im java.exe

echo Testing completed. Results are available in the 'reports' directory.