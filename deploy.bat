@echo off
setlocal enabledelayedexpansion

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

rem Créer la liste des fichiers .java avec chemins relatifs
cd %SRC%
for /r %%i in (*.java) do (
    echo %%~fi >> ..\sources_abs.tmp
)
cd ..

rem Convertir les chemins absolus en relatifs
set "CURRENT_DIR=%CD%"
(for /f "usebackq delims=" %%a in ("sources_abs.tmp") do (
    set "abs_path=%%a"
    set "rel_path=!abs_path:%CURRENT_DIR%\=!"
    echo !rel_path!
)) > sources.txt

del sources_abs.tmp 2>nul

javac -classpath "%TOMCAT_LIB%\servlet-api.jar;%FRAMEWORK_JAR%" -d %BUILD%\WEB-INF\classes @"sources.txt"
if errorlevel 1 (
    echo Erreur de compilation!
    del sources.txt 2>nul
    pause
    exit /b 1
)
del sources.txt 2>nul

rem Copier framework.jar
copy /y %FRAMEWORK_JAR% %BUILD%\WEB-INF\lib\

rem Copier web.xml
copy /y WEB-INF\web.xml %BUILD%\WEB-INF\

rem Copier les autres fichiers web
if exist "*.html" copy /y "*.html" %BUILD%\
if exist "*.jsp" copy /y "*.jsp" %BUILD%\
if exist "*.css" copy /y "*.css" %BUILD%\
if exist "*.js" copy /y "*.js" %BUILD%\
if exist "META-INF" xcopy /s /y "META-INF" %BUILD%\META-INF\

rem Créer le WAR
cd %BUILD%
jar cvf ..\%WAR_NAME% .
cd ..

echo %WAR_NAME% genere avec succes dans le dossier courant.

rem Déployer sur Tomcat
copy /y "%WAR_NAME%" "%TOMCAT_PATH%\webapps\"

echo Deployment termine!
pause