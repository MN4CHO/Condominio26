# Módulo GRB - Gestión de Usuarios y Administradores

## Índice

- [CU-01 Registrar Cuenta](#cu-01-registrar-cuenta)
- [CU-02 Actualizar Información de Cuenta](#cu-02-actualizar-información-de-cuenta)
- [CU-03 Desactivar Cuenta](#cu-03-desactivar-cuenta)
- [CU-04 Asignar Rol a Usuario](#cu-04-asignar-rol-a-usuario)
- [CU-05 Definir Permisos de Rol](#cu-05-definir-permisos-de-rol)
- [CU-06 Iniciar Sesión](#cu-06-iniciar-sesión)
- [CU-07 Actualizar Perfil](#cu-07-actualizar-perfil)
- [CU-08 Recuperar Contraseña](#cu-08-recuperar-contraseña)
- [CU-09 Enviar Token de Restablecimiento](#cu-09-enviar-token-de-restablecimiento)

---

# CU-01 Registrar Cuenta

## Descripción

Permite al Administrador registrar una nueva cuenta de usuario en el sistema del condominio.

## Actor Principal

Administrador

## Precondiciones

- El Administrador ha iniciado sesión.
- Posee permisos para gestionar usuarios.

## Postcondiciones

- La cuenta queda registrada.

## Escenario Básico

1. El caso de uso comienza cuando el Administrador registra un usuario.
2. El Administrador proporciona el nombre y apellido del usuario.
3. El Sistema valida el nombre y apeliido del usuario entonces el Administrador proporciona el correo.
4. El Sistema valida el correo del usario entonces el Administrador proporciona la contraseña.
5. El Sistema valida la contraseña del usuario entonces el Administrador proporciona el rol del usuario.
6. El Sistema valida el rol del usario entonces el Administrador proporciona los permisos del rol del usuario.
7. El caso de uso termina cuando el Sistema registra la cuenta, envía las credenciales al usuario y muestra el mensaje de confirmación.

## Escenario Alternos

### A1. Correo ya registrado

1. El Sistema detecta que el correo existe.
2. El Sistema muestra un mensaje de error.
3. El caso de uso finaliza.

### A2. Campos incompletos

1. El Sistema detecta campos obligatorios vacíos.
2. El Sistema solicita completar la información.
3. El flujo regresa al ingreso de datos.

---

# CU-02 Actualizar Información de Cuenta

(Contenido)

---

# CU-03 Desactivar Cuenta

(Contenido)

---

# CU-04 Asignar Rol a Usuario

(Contenido)

---

# CU-05 Definir Permisos de Rol

(Contenido)

---

# CU-06 Iniciar Sesión

(Contenido)

---

# CU-07 Actualizar Perfil

(Contenido)

---

# CU-08 Recuperar Contraseña

(Contenido)

---

# CU-09 Enviar Token de Restablecimiento

(Contenido)
