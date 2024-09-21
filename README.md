# BackArtGallery

Este proyecto es una implementación de autenticación utilizando JSON Web Tokens (JWT) y Spring Security. El objetivo es proporcionar una forma segura de autenticar a los usuarios y proteger las rutas de la aplicación.

## Tecnologías utilizadas

* **Spring Boot**: Framework de desarrollo web para Java que proporciona una forma rápida y sencilla de crear aplicaciones web.
* **Spring Security**: Framework de seguridad para Spring que proporciona una forma de proteger las aplicaciones web de ataques y vulnerabilidades.
* **JWT (JSON Web Tokens)**: Estándar de token de autenticación que permite a los servidores verificar la identidad de los clientes de manera segura.
* **Java**: Lenguaje de programación utilizado para desarrollar la aplicación.
* **Lombok**: Biblioteca de Java que proporciona una forma de reducir la cantidad de código necesario para desarrollar aplicaciones.

## Funcionalidad

El proyecto proporciona las siguientes funcionalidades:

* **Autenticación**: Los usuarios pueden autenticarse utilizando un nombre de usuario y una contraseña.
* **Registro**: Los usuarios pueden registrarse en la aplicación proporcionando un nombre de usuario, una contraseña y otros datos.
* **Protección de rutas**: Las rutas de la aplicación están protegidas por autenticación, lo que significa que solo los usuarios autenticados pueden acceder a ellas.
* **Generación de tokens**: Cuando un usuario se autentica, se genera un token JWT que se utiliza para verificar su identidad en futuras solicitudes.
* ** Validación de datos**: Validacion de entrada de datos al momento de hacer la peticiones con mensages personalizados de retorno.

## Cómo funciona

1. El usuario se autentica proporcionando un nombre de usuario y una contraseña.
2. El servidor verifica la autenticación y, si es correcta, genera un token JWT.
3. El token JWT se devuelve al cliente, que lo almacena en su navegador o aplicación.
4. En futuras solicitudes, el cliente envía el token JWT en el encabezado de la solicitud.
5. El servidor verifica el token JWT y, si es válido, permite al cliente acceder a las rutas protegidas.


## Funcionalidad de Endpoints 

## Registrar Usuario: 
* URL Controller: http://localhost:8090/auth/registro
* Json Body: {
  "username": "exampleUser",
  "password": "examplePassword",
  "firstname": "John", 
  "lastname": "Doe",
  "country": "USA"
}

## Ingresar Usuario: 
* URL Controller: http://localhost:8090/auth/login
* Json Body : {
	"username":"exampleUser",
	"password": "examplePassword"
}


## Crear pais (ramaProtegida) 
#### Recuerde enviar en auth el token que le retorna el ingreso de usuario
* URL Controller: http://localhost:8090/Country/create
Json Body : {
	"nombre":"Colombia"
}


