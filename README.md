# api-users
API RESTful para crear usuarios

Para probar la API REST seguir estos pasos:
1. Clonar proyecto api-users en local.
2. Correr proyecto con Spring Boot App.
3. Una vez levantado ir a Postman o cualquier herramienta para testear APIs.
4. Seleccional el metodo POST y agregar la Url localhost:8080/users/add.
5. ingresar el JSON con el formato indicado en la evaluacion.
6. Se creara un registro en la base de datos en memoria H2 con la informacion enviada y retornara un status 201 y el JSON de respuestas con el token y demas campos cuando la creacion es exitosa.
