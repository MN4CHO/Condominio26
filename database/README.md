# Base de Datos - Sistema de Gestión de Condominios

## Descripción

Este directorio contiene todos los recursos necesarios para crear, poblar y conectar la base de datos SQLite del proyecto *Sistema de Gestión de Condominios*.

La base de datos está diseñada para ser utilizada por todos los módulos del sistema, permitiendo que cada integrante del equipo trabaje sobre una misma estructura de datos.

---

# Estructura


Proyecto/
│
├── database/
│   ├── condominio.db
│   ├── schema.sql
│   ├── seed.sql
│   ├── reset_database.sql
│   └── README.md
│
└── src/
    └── main/
        └── java/
            └── fis/
                └── dsw/
                    └── sgc/
                        └── conexion_bd/
                            ├── DBConnection.java
                            └── main_test_bd.java


---

# Archivos

## condominio.db

Base de datos SQLite del proyecto.

Contiene toda la información utilizada por los distintos módulos.

---

## schema.sql

Script encargado de crear:

- Tablas
- Relaciones
- Claves foráneas
- Índices
- Restricciones
- Triggers

Debe ejecutarse únicamente cuando se crea una base nueva.

---

## seed.sql

Inserta información inicial para realizar pruebas del sistema.

Incluye registros para:

- Usuarios
- Roles
- Condominios
- Inmuebles
- Espacios comunes
- Reservas
- Deudas
- Pagos
- Visitas
- Mensajes
- Notificaciones

---


## DBConnection.java

Clase encargada de administrar la conexión con SQLite.

Implementa el patrón *Singleton*, garantizando que toda la aplicación utilice una única conexión a la base de datos.

La conexión utilizada es:

java
jdbc:sqlite:database/condominio.db


Funciones principales:

- Abrir conexión.
- Reutilizar la conexión existente.
- Reabrir la conexión si fue cerrada.
- Cerrar la conexión al finalizar la ejecución.

Todos los DAO del proyecto deben obtener la conexión mediante:

java
Connection conn = DBConnection.getInstance().getConnection();


---

## main_test_bd.java

Programa de prueba para verificar que la conexión con SQLite funciona correctamente.

Realiza una consulta simple:

sql
SELECT * FROM usuario;


Si la configuración es correcta, imprimirá los nombres de los usuarios almacenados en la base de datos.

Este archivo sirve únicamente para pruebas y puede eliminarse una vez que los DAO del proyecto estén implementados.

---

# Requisitos

- Java 21
- Maven
- SQLite JDBC
- DB Browser for SQLite (opcional)

---


# Verificación

Comprobar la integridad de la base ejecutando:

sql
PRAGMA foreign_key_check;


No debe devolver registros.

También pueden verificarse algunos datos mediante:

sql
SELECT COUNT(*) FROM usuario;
SELECT COUNT(*) FROM inmueble;
SELECT COUNT(*) FROM espacio_comun;
SELECT COUNT(*) FROM reserva;
SELECT COUNT(*) FROM deuda;
SELECT COUNT(*) FROM visita_programada;
SELECT COUNT(*) FROM mensaje;


---

# Uso desde Java

Obtener la conexión:

java
Connection conn = DBConnection.getInstance().getConnection();


Ejemplo de consulta:

java
String sql = "SELECT * FROM usuario";

PreparedStatement pstmt = conn.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();

while (rs.next()) {
    System.out.println(rs.getString("nombres"));
}


Cerrar la conexión:

java
DBConnection.getInstance().closeConnection();


---

# Buenas prácticas

- No crear conexiones nuevas utilizando DriverManager directamente.
- Utilizar siempre DBConnection.
- Utilizar PreparedStatement para todas las consultas SQL.
- Mantener activadas las claves foráneas de SQLite.
- No modificar manualmente condominio.db mientras la aplicación esté ejecutándose.

---
