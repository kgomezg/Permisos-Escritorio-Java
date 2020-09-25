USE permisosUsuario

/*
PPROCEDIMIENTOS ALMACENADOS
*/
IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_GuardarError')
BEGIN
	DROP PROC sp_GuardarError
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para guardar el error.
EXEC sp_GuardarError 'PRUEBA', 'PRUEBA', 'PRUEBA' 
*/
CREATE PROC sp_GuardarError (
@_ErrClase VARCHAR(MAX)
,@_ErrCausa VARCHAR(MAX)
,@_ErrMensaje VARCHAR(MAX)
) AS
BEGIN

INSERT INTO commErrores(cmmErrClase, cmmErrCausa, cmmErrMensaje) VALUES(@_ErrClase, @_ErrCausa, @_ErrMensaje)

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_consultaLogin')
BEGIN
	DROP PROC sp_consultaLogin
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar si existe algun login.
EXEC sp_consultaLogin 'Admin', 'a61565f4559ca2b9ce9e27a83fc4d2e'
*/
CREATE PROC sp_consultaLogin (
@_usuario VARCHAR(MAX)
,@_clave VARCHAR(MAX)
) AS
BEGIN

DECLARE @EXISTE INT = 0;

IF EXISTS(SELECT * FROM autenticacion WITH(NOLOCK) INNER JOIN commUsuario WITH(NOLOCK) ON autUsuarioId = usuId WHERE usuLogin = @_usuario AND autClave = @_clave)
BEGIN
	SET @EXISTE = (SELECT autUsuarioId FROM autenticacion WITH(NOLOCK) INNER JOIN commUsuario WITH(NOLOCK) ON autUsuarioId = usuId WHERE usuLogin = @_usuario AND autClave = @_clave) 
END

SELECT @EXISTE IdUsuario

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_consultaUsuario')
BEGIN
	DROP PROC sp_consultaUsuario
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar el usuario.
EXEC sp_consultaUsuario 1
EXEC sp_consultaUsuario 2
EXEC sp_consultaUsuario 3
*/
CREATE PROC sp_consultaUsuario (
@_IdUsuario INT
) AS
BEGIN

DECLARE @EXISTE INT = 0;

IF EXISTS(SELECT * FROM autenticacion WITH(NOLOCK) INNER JOIN commUsuario WITH(NOLOCK) ON autUsuarioId = usuId WHERE autUsuarioId = @_IdUsuario)
BEGIN
	SELECT commUsuario.*, autenticacion.autClave usuClave FROM autenticacion WITH(NOLOCK) INNER JOIN commUsuario WITH(NOLOCK) ON autUsuarioId = usuId WHERE autUsuarioId = @_IdUsuario
END

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_creaEditaUsuario')
BEGIN
	DROP PROC sp_creaEditaUsuario
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar el usuario.
*/
CREATE PROC sp_creaEditaUsuario (
@_PerfilLogeado INT
,@_TipoPerfil INT
,@_Usuario NVARCHAR(MAX)
,@_Clave NVARCHAR(MAX)
,@_TipoDocumento INT
,@_DocumentoIdentificacion NVARCHAR(MAX)
,@_PrimerNombre NVARCHAR(MAX)
,@_SegundoNombre NVARCHAR(MAX)
,@_PrimerApellido NVARCHAR(MAX)
,@_SegundoApellido NVARCHAR(MAX)
,@_Direccion NVARCHAR(MAX)
,@_Telefono NVARCHAR(MAX)
,@_Email NVARCHAR(MAX)

) AS
BEGIN

-- Perfil Administrador
IF @_PerfilLogeado = 1
BEGIN
	IF NOT EXISTS(SELECT * FROM commUsuario WITH(NOLOCK) WHERE usuLogin = @_Usuario) 
	BEGIN
		INSERT INTO commUsuario (usuLogin,usuIden,usuIdenTipo,usuPrimerNombre,usuSegundoNombre,usuPrimerApellido,usuSegundoApellido,usuDireccion,usuTelefono,usuEmail,usuPerfil)
		VALUES(@_Usuario,@_DocumentoIdentificacion,@_TipoDocumento,@_PrimerNombre,@_SegundoNombre,@_PrimerApellido,@_SegundoApellido,@_Direccion,@_Telefono,@_Email,@_TipoPerfil)


		INSERT INTO autenticacion(autUsuarioId,autClave,autEstado) VALUES (
		(
			SELECT usuId FROM commUsuario WITH(NOLOCK) WHERE usuLogin = @_Usuario AND usuIden = @_DocumentoIdentificacion AND usuIdenTipo = @_TipoDocumento
		), @_Clave, 1)

	END
	ELSE
	BEGIN

		UPDATE commUsuario SET 
			usuPrimerNombre = @_PrimerNombre
			,usuSegundoNombre = @_SegundoNombre
			,usuPrimerApellido = @_PrimerApellido
			,usuSegundoApellido = @_SegundoApellido
			,usuDireccion = @_Direccion
			,usuTelefono = @_Telefono
			,usuEmail = @_Email
			,usuPerfil = @_TipoPerfil
		WHERE usuLogin = @_Usuario

	END

END
-- Perfil Usuario
ELSE IF @_PerfilLogeado = 2
BEGIN
	IF EXISTS(SELECT * FROM commUsuario WITH(NOLOCK) WHERE usuLogin = @_Usuario)
	BEGIN
		UPDATE commUsuario SET 
			usuPrimerNombre = @_PrimerNombre
			,usuSegundoNombre = @_SegundoNombre
			,usuPrimerApellido = @_PrimerApellido
			,usuSegundoApellido = @_SegundoApellido
			,usuDireccion = @_Direccion
			,usuTelefono = @_Telefono
			,usuEmail = @_Email
		WHERE usuLogin = @_Usuario

	END
END

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_consultaCommTablas')
BEGIN
	DROP PROC sp_consultaCommTablas
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar las diferentes tablas.
EXEC sp_consultaCommTablas 1
EXEC sp_consultaCommTablas 2
EXEC sp_consultaCommTablas 3
EXEC sp_consultaCommTablas 4
*/
CREATE PROC sp_consultaCommTablas (
@_IdConsulta INT
) AS
BEGIN

-- Tipos Documento
IF @_IdConsulta = 1
BEGIN
	SELECT 0 Id, 'SELECCIONE' Descripcion, '' DescripcionAbreviada
	UNION
	SELECT 
	cmmtIdElemento Id
	,cmmtElemento1 Descripcion
	,cmmtElemento2 DescripcionAbreviada
	FROM commTabla WITH(NOLOCK) 
	INNER JOIN commTablaTablas WITH(NOLOCK) ON commTablaTablas.cmmttId = commTabla.cmmtIdTabla
	WHERE commTablaTablas.cmmttId = @_IdConsulta
END

-- Perfiles
IF @_IdConsulta = 2
BEGIN
	SELECT 0 Id, 'SELECCIONE' Perfil, '' PerfilAbreviado
	UNION
	SELECT 
	cmmtIdElemento Id
	,cmmtElemento1 Perfil
	,cmmtElemento2 PerfilAbreviado
	FROM commTabla WITH(NOLOCK) 
	INNER JOIN commTablaTablas WITH(NOLOCK) ON commTablaTablas.cmmttId = commTabla.cmmtIdTabla
	WHERE commTablaTablas.cmmttId = @_IdConsulta
END

IF @_IdConsulta = 3
BEGIN
	SELECT 0 Id, 'SELECCIONE' TipoPermiso
	UNION
	SELECT 
	cmmtIdElemento Id
	,cmmtElemento1 TipoPermiso
	FROM commTabla WITH(NOLOCK) 
	INNER JOIN commTablaTablas WITH(NOLOCK) ON commTablaTablas.cmmttId = commTabla.cmmtIdTabla
	WHERE commTablaTablas.cmmttId = @_IdConsulta
END

IF @_IdConsulta = 4
BEGIN
	SELECT 0 Id, 'SELECCIONE' Estado
	UNION
	SELECT 
	cmmtIdElemento Id
	,cmmtElemento1 Estado
	FROM commTabla WITH(NOLOCK) 
	INNER JOIN commTablaTablas WITH(NOLOCK) ON commTablaTablas.cmmttId = commTabla.cmmtIdTabla
	WHERE commTablaTablas.cmmttId = @_IdConsulta
END

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_consultaPermisos')
BEGIN
	DROP PROC sp_consultaPermisos
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar las diferentes tablas.
EXEC sp_consultaPermisos 0, ''
EXEC sp_consultaPermisos 1, '2'
EXEC sp_consultaPermisos 2, '11'
EXEC sp_consultaPermisos 3, '2'
*/
CREATE PROC sp_consultaPermisos (
@_IdConsulta INT
,@_Parametro_1 VARCHAR(MAX)

) AS
BEGIN

DECLARE @SQL VARCHAR(MAX) = ''

-- Consultar Todos
IF @_IdConsulta = 0
BEGIN
	SET @SQL += 'SELECT '
	SET @SQL += 'perId Num_Permiso '
	SET @SQL += ',EP.cmmtElemento1 Estado_Permiso'
	SET @SQL += ',commUsuario.usuLogin Usuario '
	SET @SQL += ',TP.cmmtElemento1 TipoPermiso '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perInicio,103) Fecha_Inicio '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perFin,103) Fecha_Fin '
	SET @SQL += ',permisos.perObsUsuario Observaciones_Usuario '
	SET @SQL += ',permisos.perObsAdministrador Observaciones_Administrador '
	SET @SQL += 'FROM permisos WITH(NOLOCK) '
	SET @SQL += 'INNER JOIN commUsuario WITH(NOLOCK) ON usuId = perIdUsuario '
	SET @SQL += 'INNER JOIN commTabla P WITH(NOLOCK) ON P.cmmtIdTabla = 2 AND P.cmmtIdElemento = usuPerfil '
	SET @SQL += 'INNER JOIN commTabla TP WITH(NOLOCK) ON TP.cmmtIdTabla = 3 AND TP.cmmtIdElemento = perTipoPermiso '
	SET @SQL += 'INNER JOIN commTabla EP WITH(NOLOCK) ON EP.cmmtIdTabla = 4 AND EP.cmmtIdElemento = perEstado '
END

-- Consultar X Usuario
IF @_IdConsulta = 1
BEGIN
	SET @SQL += 'SELECT '
	SET @SQL += 'perId Num_Permiso '
	SET @SQL += ',EP.cmmtElemento1 Estado_Permiso'
	SET @SQL += ',commUsuario.usuLogin Usuario '
	SET @SQL += ',TP.cmmtElemento1 TipoPermiso '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perInicio,103) Fecha_Inicio '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perFin,103) Fecha_Fin '
	SET @SQL += ',permisos.perObsUsuario Observaciones_Usuario '
	SET @SQL += ',permisos.perObsAdministrador Observaciones_Administrador '
	SET @SQL += 'FROM permisos WITH(NOLOCK) '
	SET @SQL += 'INNER JOIN commUsuario WITH(NOLOCK) ON usuId = perIdUsuario '
	SET @SQL += 'INNER JOIN commTabla P WITH(NOLOCK) ON P.cmmtIdTabla = 2 AND P.cmmtIdElemento = usuPerfil '
	SET @SQL += 'INNER JOIN commTabla TP WITH(NOLOCK) ON TP.cmmtIdTabla = 3 AND TP.cmmtIdElemento = perTipoPermiso '
	SET @SQL += 'INNER JOIN commTabla EP WITH(NOLOCK) ON EP.cmmtIdTabla = 4 AND EP.cmmtIdElemento = perEstado '
	SET @SQL += 'WHERE perIdUsuario = ' + @_Parametro_1 + ' '
END

-- Consultar X Tipo Permiso
IF @_IdConsulta = 2
BEGIN
	SET @SQL += 'SELECT '
	SET @SQL += 'perId Num_Permiso '
	SET @SQL += ',EP.cmmtElemento1 Estado_Permiso'
	SET @SQL += ',commUsuario.usuLogin Usuario '
	SET @SQL += ',TP.cmmtElemento1 TipoPermiso '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perInicio,103) Fecha_Inicio '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perFin,103) Fecha_Fin '
	SET @SQL += ',permisos.perObsUsuario Observaciones_Usuario '
	SET @SQL += ',permisos.perObsAdministrador Observaciones_Administrador '
	SET @SQL += 'FROM permisos WITH(NOLOCK) '
	SET @SQL += 'INNER JOIN commUsuario WITH(NOLOCK) ON usuId = perIdUsuario '
	SET @SQL += 'INNER JOIN commTabla P WITH(NOLOCK) ON P.cmmtIdTabla = 2 AND P.cmmtIdElemento = usuPerfil '
	SET @SQL += 'INNER JOIN commTabla TP WITH(NOLOCK) ON TP.cmmtIdTabla = 3 AND TP.cmmtIdElemento = perTipoPermiso '
	SET @SQL += 'INNER JOIN commTabla EP WITH(NOLOCK) ON EP.cmmtIdTabla = 4 AND EP.cmmtIdElemento = perEstado '
	SET @SQL += 'WHERE perTipoPermiso = ' + @_Parametro_1 + ' '
END

-- Consultar X Estado
IF @_IdConsulta = 3
BEGIN
	SET @SQL += 'SELECT '
	SET @SQL += 'perId Num_Permiso '
	SET @SQL += ',EP.cmmtElemento1 Estado_Permiso'
	SET @SQL += ',commUsuario.usuLogin Usuario '
	SET @SQL += ',TP.cmmtElemento1 TipoPermiso '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perInicio,103) Fecha_Inicio '
	SET @SQL += ',CONVERT(VARCHAR(MAX),permisos.perFin,103) Fecha_Fin '
	SET @SQL += ',permisos.perObsUsuario Observaciones_Usuario '
	SET @SQL += ',permisos.perObsAdministrador Observaciones_Administrador '
	SET @SQL += 'FROM permisos WITH(NOLOCK) '
	SET @SQL += 'INNER JOIN commUsuario WITH(NOLOCK) ON usuId = perIdUsuario '
	SET @SQL += 'INNER JOIN commTabla P WITH(NOLOCK) ON P.cmmtIdTabla = 2 AND P.cmmtIdElemento = usuPerfil '
	SET @SQL += 'INNER JOIN commTabla TP WITH(NOLOCK) ON TP.cmmtIdTabla = 3 AND TP.cmmtIdElemento = perTipoPermiso '
	SET @SQL += 'INNER JOIN commTabla EP WITH(NOLOCK) ON EP.cmmtIdTabla = 4 AND EP.cmmtIdElemento = perEstado '
	SET @SQL += 'WHERE perEstado = ' + @_Parametro_1 + ' '
END

EXEC(@SQL)
PRINT(@SQL)

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_consultaPermiso')
BEGIN
	DROP PROC sp_consultaPermiso
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar las diferentes tablas.
EXEC sp_consultaPermiso 1
EXEC sp_consultaPermiso 2
EXEC sp_consultaPermiso 3 
*/
CREATE PROC sp_consultaPermiso (
@_IdPermiso INT
) AS
BEGIN

SELECT * FROM permisos WITH(NOLOCK) WHERE perId = @_IdPermiso 

END
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_NAME = 'sp_creaEditaPermiso')
BEGIN
	DROP PROC sp_creaEditaPermiso
END
GO
/*
AUTOR: Kevin Camilo Gómez
FECHA: 19/09/2020
RAZON: Se crea el sp para consultar las diferentes tablas.
EXEC sp_consultaPermiso 1
EXEC sp_consultaPermiso 2
EXEC sp_consultaPermiso 3 
*/
CREATE PROC sp_creaEditaPermiso (
@_IdPerfilUsuarioLogeado INT
,@_IdPermiso INT
,@_IdUsuario INT
,@_IdTipoPermiso INT
,@_FechaIni VARCHAR(MAX)
,@_FechaFin VARCHAR(MAX)
,@_ObservacionesUsuario VARCHAR(MAX)
,@_ObservacionesAdministrador VARCHAR(MAX)
,@_IdEstado INT

) AS BEGIN

DECLARE @RESULTADO INT = 0;

IF @_IdPerfilUsuarioLogeado = 1
BEGIN

IF EXISTS(
	SELECT
	 * 
	FROM permisos 
	WHERE 1 = 1
	AND perId = @_IdPermiso
)
BEGIN

UPDATE permisos
SET perObsAdministrador = @_ObservacionesAdministrador
,perTipoPermiso = @_IdTipoPermiso
,perEstado = @_IdEstado
WHERE perId = @_IdPermiso

SELECT @RESULTADO = 1 

END

END
ELSE IF @_IdPerfilUsuarioLogeado = 2
BEGIN

INSERT INTO permisos (perIdUsuario,perTipoPermiso,perInicio,perFin,perObsUsuario)
VALUES(@_IdUsuario, @_IdTipoPermiso, CONVERT(DATETIME,@_FechaIni,103), CONVERT(DATETIME,@_FechaFin,103), @_ObservacionesUsuario)

SELECT @RESULTADO = 1 

END

SELECT @RESULTADO RESULTADO

END
GO