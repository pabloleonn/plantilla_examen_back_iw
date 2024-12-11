Configuraciones a hacer:

- Archivo: main/resources/application.properties
  - Poner tu mongodb uri
  - Poner tus credenciales de aws

- Añadir al directorio raiz ("examen/)
  - Vuestra clave privada de firebase (.json)
  - Tras añadir la clave, en config/FirebaseConfig.java cambiar por el nombre de tu clave-privada.json

- Añadir a UsuarioDTO y Usuario lo que pida el enunciado que tenga la base de datos con Usuario
- Añadir a UsuarioFirebaseDTO lo que te vaya a pasar el frontend con datos del usuario (por ejemplo si te pide la caducidad tendrías que añadirlo)