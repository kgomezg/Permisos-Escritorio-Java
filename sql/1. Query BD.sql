USE MASTER
/*
SE CREA LA BASE DE DATOS
*/
IF EXISTS(SELECT * FROM master.sys.databases WHERE name = 'permisosUsuario')
BEGIN
	DROP DATABASE permisosUsuario
END
GO

/*
SE CREA LA BASE DE DATOS
*/
IF NOT EXISTS(SELECT * FROM master.sys.databases WHERE name = 'permisosUsuario')
BEGIN
	CREATE DATABASE permisosUsuario
END
GO

IF EXISTS(SELECT * FROM master.sys.databases WHERE name = 'permisosUsuario')
BEGIN
	
	/*
	SE CAMBIA DE BASE DE DATOS
	*/
	USE permisosUsuario
	/*
	INICIO TABLAS
	*/
	IF EXISTS(SELECT name FROM master.sys.databases WHERE name = 'permisosUsuario')
	BEGIN

		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'commUsuario')
		BEGIN
			DROP TABLE permisosUsuario.dbo.commUsuario
		END

		CREATE TABLE permisosUsuario.dbo.commUsuario (
			usuId INT IDENTITY(1,1) PRIMARY KEY
			,usuLogin VARCHAR(MAX) NOT NULL
			,usuIden VARCHAR(50) NOT NULL
			,usuIdenTipo INT NOT NULL
			,usuPrimerNombre VARCHAR(50) NOT NULL
			,usuSegundoNombre VARCHAR(50) DEFAULT ''
			,usuPrimerApellido VARCHAR(50) NOT NULL
			,usuSegundoApellido VARCHAR(50) DEFAULT ''
			,usuDireccion VARCHAR(255)
			,usuTelefono VARCHAR(20)
			,usuEmail VARCHAR(255) NOT NULL
			,usuPerfil INT DEFAULT 2
			,usuFechaRegistro DATETIME DEFAULT GETDATE()
		)

		INSERT INTO permisosUsuario.dbo.commUsuario (
			usuLogin, usuIden, usuIdenTipo, usuPrimerNombre, usuSegundoNombre, usuPrimerApellido, usuSegundoApellido, usuDireccion, usuTelefono, usuEmail, usuPerfil
		) 
		VALUES(
			'Admin','00000000',1,'Administrador','','Sistema','','CL. 71 #9 - 84, Bogotá','5936464 Ext 7008','informacion@universidadean.edu.co', 1
		)

		INSERT INTO permisosUsuario.dbo.commUsuario (
			usuLogin, usuIden, usuIdenTipo, usuPrimerNombre, usuSegundoNombre, usuPrimerApellido, usuSegundoApellido, usuDireccion, usuTelefono, usuEmail
		) 
		VALUES(
			'kcgomezg','1013681832',1,'Kevin','Camilo','Gómez','González','Cll 42B SUR #72H-15','4705765','kgomezg81832@universidadean.edu.co'
		)
		
		INSERT INTO permisosUsuario.dbo.commUsuario (
			usuLogin, usuIden, usuIdenTipo, usuPrimerNombre, usuSegundoNombre, usuPrimerApellido, usuSegundoApellido, usuDireccion, usuTelefono, usuEmail
		) 
		VALUES(
			'ihjimenez','1013238182',1,'Inti','Hayna','Jimenez','','Cll 42B SUR #76H-18','7169182','ihjimenez23818@universidadean.edu.co'
		)

		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'autenticacion')
		BEGIN
			DROP TABLE permisosUsuario.dbo.autenticacion
		END

		CREATE TABLE permisosUsuario.dbo.autenticacion (
			autId INT IDENTITY(1,1) PRIMARY KEY
			,autUsuarioId INT NOT NULL
			,autClave VARCHAR(MAX) NOT NULL
			,autEstado INT DEFAULT 1
		)

		INSERT INTO permisosUsuario.dbo.autenticacion (autUsuarioId,autClave) VALUES(1,'a61565f4559ca2b9ce9e27a83fc4d2e')
		INSERT INTO permisosUsuario.dbo.autenticacion (autUsuarioId,autClave) VALUES(2,'a61565f4559ca2b9ce9e27a83fc4d2e')
		INSERT INTO permisosUsuario.dbo.autenticacion (autUsuarioId,autClave) VALUES(3,'a61565f4559ca2b9ce9e27a83fc4d2e')


		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'commTablaTablas')
		BEGIN
			DROP TABLE permisosUsuario.dbo.commTablaTablas
		END
	
		CREATE TABLE permisosUsuario.dbo.commTablaTablas (
			cmmttId INT IDENTITY(1,1) PRIMARY KEY
			,cmmttNombreTabla VARCHAR(MAX)
		)

		INSERT INTO permisosUsuario.dbo.commTablaTablas VALUES('Tipos de Documento')
		INSERT INTO permisosUsuario.dbo.commTablaTablas VALUES('Perfiles')
		INSERT INTO permisosUsuario.dbo.commTablaTablas VALUES('Tipo Permiso')
		INSERT INTO permisosUsuario.dbo.commTablaTablas VALUES('Estados Permiso')

		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'commTabla')
		BEGIN
			DROP TABLE permisosUsuario.dbo.commTabla
		END
	
		CREATE TABLE permisosUsuario.dbo.commTabla (
			cmmtId INT IDENTITY(1,1) PRIMARY KEY
			,cmmtIdTabla INT
			,cmmtIdElemento INT
			,cmmtElemento1 VARCHAR(MAX)
			,cmmtElemento2 VARCHAR(MAX)
		)

		/*
		INSERCIÓN DE TIPOS DE IDENTIDAD
		*/
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipos de Documento'),1,'CEDULA CIUDADANIA','CC')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipos de Documento'),2,'TARJETA DE IDENTIDAD','TI')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipos de Documento'),3,'REGISTRO CIVIL','RC')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipos de Documento'),4,'PASAPORTE','PA')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipos de Documento'),5,'CEDULA EXTRANJERIA','CE')

		/*
		INSERCIÓN DE PERFILES
		*/
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Perfiles'),1,'ADMINISTRADOR','Admin')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Perfiles'),2,'USUARIO','User')

		/*
		INSERCIÓN DE TIPOS DE PERMISO
		*/
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),1,'DIA DE LA FAMILIA II SEMESTRE','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),2,'CALAMIDAD DOMESTICA','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),3,'LICENCIA POR LUTO','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),4,'COMPENSATORIO POR VOTACION','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),5,'PERMISO PERSONAL X HORAS RIT','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),6,'COMPENSATORIO POR HORAS RIT','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),7,'AUSENTISMO POR CITAS MEDICAS','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),8,'AUSENTISMO CAPACITACION EMPRESA','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),9,'AUSENTISMO VISITA CLIENTE','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),10,'AUSENTISMO CENSO NACIONAL','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),11,'PERMISO CUMPLEAÑOS (PB','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),12,'PERMISO POR MATRIMONIO (PB','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),13,'PERMISO NACIMIENTO MADRE (PB','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),14,'PERMISO NACIMIENTO PADRE (PB','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),15,'PERMISO CEREMONIA GRADO (PB','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),16,'PERMISO VOLUNTARIADO CORP (PB','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),17,'AUSENTISMO VISITA PROVEEDOR','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),18,'PERMISO CAPACITACION JURADO VOTACION','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Tipo Permiso'),19,'COMPENSATORIO JURADO VOTACION','')

		/*
		INSERCIÓN DE ESTADOS DE PERMISO
		*/
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Estados Permiso'),1,'EN ESPERA','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Estados Permiso'),2,'APROBADO','')
		INSERT INTO permisosUsuario.dbo.commTabla VALUES((SELECT cmmttId from commTablaTablas WHERE cmmttNombreTabla = 'Estados Permiso'),3,'NO APROBADO','')


		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'commErrores')
		BEGIN
			DROP TABLE permisosUsuario.dbo.commErrores
		END
	
		CREATE TABLE permisosUsuario.dbo.commErrores (
			cmmErrId INT IDENTITY(1,1) PRIMARY KEY
			,cmmErrClase VARCHAR(MAX)
			,cmmErrCausa VARCHAR(MAX)
			,cmmErrMensaje VARCHAR(MAX)
			,cmmErrFecha DATETIME DEFAULT GETDATE()
		)

		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'permisos')
		BEGIN
			DROP TABLE permisosUsuario.dbo.permisos
		END
	
		CREATE TABLE permisosUsuario.dbo.permisos (
			perId INT IDENTITY(1,1) PRIMARY KEY
			,perIdUsuario INT NOT NULL
			,perTipoPermiso INT NOT NULL
			,perInicio DATETIME NOT NULL
			,perFin DATETIME NOT NULL
			,perObsUsuario VARCHAR(MAX) DEFAULT ''
			,perObsAdministrador VARCHAR(MAX) DEFAULT ''
			,perEstado INT DEFAULT 1
			,perFechaAccion DATETIME DEFAULT GETDATE()
		)

		
		INSERT INTO permisosUsuario.dbo.permisos(perIdUsuario,perTipoPermiso,perInicio,perFin,perObsUsuario,perObsAdministrador,perEstado,perFechaAccion)
		VALUES(2,11,'2020-11-07 00:00:00','2020-12-09 00:00:00','Permiso para Vacaciones','',1,'2020-10-31 14:38:40')

		INSERT INTO permisosUsuario.dbo.permisos(perIdUsuario,perTipoPermiso,perInicio,perFin,perObsUsuario,perObsAdministrador,perEstado,perFechaAccion)
		VALUES(3,11,'2019-04-01 00:00:00','2019-04-30 00:00:00','Permiso para Descansar','Se da aprobación por cumplimiento en entregas.',2,'2019-03-31 00:00:00')

	END

	/*
	FIN TABLAS
	*/
END
GO

USE master