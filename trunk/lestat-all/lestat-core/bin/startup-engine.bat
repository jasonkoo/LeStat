@echo off
::设置延迟环境变量扩充
setlocal enabledelayedexpansion
::java命令  
set JAVA=%JAVA_HOME%\bin\java
::jvm参数
::set OPTS=-Xms512M -Xmx512M -Xss128k -XX:+AggressiveOpts -XX:+UseParallelGC -XX:NewSize=64M
::**jar包所在的目录 
set LIBPATH=..\lib
::properties文件目录 
set CONFIG=..\config
::classpath 
set CP=%CONFIG%;
::main class
set MAIN=com.lenovo.push.marketing.lestat.core.App
::app home
::set APPHOME=C:\Users\liuhk2\Desktop\temp\aaa\lestat-0.0.1-SNAPSHOT
set APPHOME=..\

::循环加载jar包
for /f %%i in ('dir /b %LIBPATH%\*.jar^|sort') do (
   set CP=!CP!%LIBPATH%\%%i;
)

echo ===============================================================================
echo.
echo   Engine Startup Environment
echo.
echo   JAVA: %JAVA%
echo.
echo   CONFIG: %CONFIG%
::echo.
::echo   JAVA_OPTS: %OPTS%
echo.
echo   CLASSPATH: %CP%
echo.
echo   APPHOME: %APPHOME%
echo.
echo ===============================================================================
echo.

::echo "%JAVA%" -cp %CP% %MAIN% %CONFIG%\applicationContext.xml %CONFIG%\log4j.xml
echo "%JAVA%" -cp %CP% %MAIN%

::运行
::%JAVA% %OPTS% -cp %CP% %MAIN% 
::"%JAVA%" -cp %CP% %MAIN% %CONFIG%\applicationContext.xml %CONFIG%\log4j.xml
::"%JAVA%" -Dlestat.home=C:\Users\liuhk2\Desktop\temp\aaa\lestat-0.0.1-SNAPSHOT -Dlogfile.name=lestat-engine.0.log -cp %CP% %MAIN% engine
"%JAVA%" -Dlestat.home=%APPHOME% -Dlogfile.name=lestat-engine.0.log -cp %CP% %MAIN% engine