@echo off

set PATH=%JAVA_HOME%\bin
set JNA_HOME=.
set CLASSPATH=.;%JNA_HOME%\conf

FOR %%F IN (%JNA_HOME%\lib\*.jar) DO call :addcp %%F

goto extlibe

:addcp
set CLASSPATH=%CLASSPATH%;%1
goto :eof

:extlibe
start java  -Xms512m -Xmx1024m com.base.Main

