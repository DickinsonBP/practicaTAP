PRACTICA DE TAP DE:
Dickinson Bedoya Perez
Anna Gracia Colmenarejo

-----------------------------------------

Para poder usar la CLI en la parte de OOP se tiene que ejecutar el archivo Main.java .
En este archivo se encuentra una MailStore de tipo File donde ya se han creado varios usuarios
y mensajes para que se pueda usar.

Hay 4 opciones posibles para usar, si no se escribe alguna de ellas sale un mensaje de ayuda
para avisar como se tienen que usar estas opciones.

	createuser <username> <name> <datebirth>
	filter <contains/lessthan> <>
	logas <nombre de usuario>
	stop

Sin acceder como usuario se pueden filtrar todos los mensajes que hay en el sistema.

Una vez se haya iniciado como usuario hay unas nuevas 5 opciones:
	send <to> 'subject' 'body'
	update
	order <...>
	filter <contains/lessthan> <>
	exit

Todos los mensajes se guardan en el archivo MailStore.txt .

-----------------------------------

El archivo MainPrincipal.java de OOP es un programa que ejecuta y prueba la MailStore de tipo Memory.

