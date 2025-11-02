@echo off
set APP_NAME=test
set SRC=src
set BUILD=build
set LIB_WEBINF=WEB-INF\lib
set CLASSES_WEBINF=WEB-INF\classes
set TOMCAT_LIB=C:\tomcat11\apache-tomcat-11.0.7\lib
set FRAMEWORK_JAR=..\Framework\framework.jar
set WAR_NAME=%APP_NAME%.war
set TOMCAT_PATH=C:\tomcat11\apache-tomcat-11.0.7


rem Nettoyage
if exist %BUILD% rmdir /s /q %BUILD%
mkdir %BUILD%
mkdir %BUILD%\WEB-INF\classes
mkdir %BUILD%\WEB-INF\lib

rem Compilation
echo Compilation des fichiers source...
javac -classpath "%TOMCAT_LIB%\servlet-api.jar;%FRAMEWORK_JAR%" -d %BUILD%\WEB-INF\classes src\com\cousin\controller\*.java
if errorlevel 1 (
    echo Erreur de compilation!
    pause
    exit /b 1
)

rem Copier framework.jar
copy /y %FRAMEWORK_JAR% %BUILD%\WEB-INF\lib\

rem Copier web.xml
copy /y WEB-INF\web.xml %BUILD%\WEB-INF\

rem Créer le WAR
cd %BUILD%
jar cvf ..\%WAR_NAME% .
cd ..

echo %WAR_NAME% genere avec succes dans le dossier courant.

rem =====================================
rem 4️⃣ Déployer sur Tomcat
rem =====================================
copy /y "%WAR_NAME%" "%TOMCAT_PATH%\webapps\"

pause
