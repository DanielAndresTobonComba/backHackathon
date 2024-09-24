
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
