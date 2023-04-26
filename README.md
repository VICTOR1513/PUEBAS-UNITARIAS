# PUEBAS-UNITARIAS
Pruebas unitarias con Mokito y Junit


-----------------------------------
Desacargamos Docker Desktop
-----------------------------------
Intall
------------------------------------
powershell administrador:
https://docs.docker.com/desktop/install/windows-install/
wsl --install


Paso 1: Habilitación del Subsistema de Windows para Linux
dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart


Paso 3: Habilitación de la característica Máquina virtual
dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart

Paso 4: Descarga del paquete de actualización del kernel de Linux

Paso 5: Definición de WSL 2 como versión predeterminada
wsl --set-default-version 2
wsl -l -v
--------------------------------------------------------

En windows 10 home gabilitar caracteristicas de virtualizacio: https://docs.docker.com/desktop/troubleshoot/topics/#virtualization

--------------------------------------------------------
CREADENCIALES DOCKER
--------------------------------------------------------

	USER DOCKER:
	User: victor
	pass: Vsc151315

--------------------------------------------------------
EMPAQUETAMOS EL MICROSERVICIO A JAR
--------------------------------------------------------

	ENTRAR AL LA CARPETA RAIZ DEL MICROSERVICIO
	cd .\msvc-cursos\
	ls

	EMPAQUETAMOS JAR:
	.\mvnw.cmd clean package

	RUN JAR:
	java -jar nombreApp


