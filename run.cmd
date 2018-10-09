if "%1" == "" goto no_param
 
java -jar decomppng-1.0.jar %1
goto exit

:no_param
echo Parameter expected
 
:exit
pause



