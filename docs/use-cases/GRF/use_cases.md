# Módulo GRF - Comunicación

## Índice

- [CU-01 Enviar Mensaje a Residentes](#cu-01-enviar-mensaje-a-residentes)
- [CU-02 Enviar Comunicado a Trabajadores](#cu-02-enviar-comunicado-a-trabajadores)
- [CU-03 Enviar Mensaje Global](#cu-03-enviar-mensaje-global)
- [CU-04 Enviar Mensaje Directo](#cu-04-enviar-mensaje-directo)
- [CU-05 Crear Reporte de Comunicación](#cu-05-crear-reporte-de-comunicación)
- [CU-06 Modificar Anuncio General](#cu-06-modificar-anuncio-general)
- [CU-07 Eliminar Anuncio General](#cu-07-eliminar-anuncio-general)

---

# CU-01 Enviar Mensaje a Residentes

## Descripción
Permite al Administrador enviar mensajes dirigidos únicamente a los residentes del condominio.

## Actor Principal
Administrador

## Precondiciones
- El Administrador debe haber iniciado sesión.
- Deben existir residentes registrados en el sistema.

## Flujo Principal
1. El Administrador ingresa al módulo de comunicación.
2. Selecciona la opción de enviar mensaje a residentes.
3. Escribe el asunto y contenido del mensaje.
4. El sistema valida la información ingresada.
5. El sistema envía el mensaje a los residentes.
6. El sistema registra el envío del mensaje.

## Postcondiciones
- El mensaje queda enviado a los residentes.
- El sistema guarda un registro del mensaje enviado.

---

# CU-02 Enviar Comunicado a Trabajadores

## Descripción
Permite al Administrador enviar comunicados dirigidos al personal o trabajadores del condominio.

## Actor Principal
Administrador

## Precondiciones
- El Administrador debe haber iniciado sesión.
- Deben existir trabajadores registrados en el sistema.

## Flujo Principal
1. El Administrador ingresa al módulo de comunicación.
2. Selecciona la opción de enviar comunicado a trabajadores.
3. Escribe el asunto y contenido del comunicado.
4. El sistema valida los datos ingresados.
5. El sistema envía el comunicado a los trabajadores.
6. El sistema registra el comunicado enviado.

## Postcondiciones
- El comunicado queda enviado a los trabajadores.
- El sistema guarda el registro del comunicado.

---

# CU-03 Enviar Mensaje Global

## Descripción
Permite al Administrador enviar un mensaje global a todos los usuarios del condominio, incluyendo residentes y trabajadores.

## Actor Principal
Administrador

## Precondiciones
- El Administrador debe haber iniciado sesión.
- Deben existir usuarios registrados en el sistema.

## Flujo Principal
1. El Administrador ingresa al módulo de comunicación.
2. Selecciona la opción de mensaje global.
3. Escribe el asunto y contenido del mensaje.
4. El sistema valida la información.
5. El sistema envía el mensaje a todos los usuarios.
6. El sistema registra el mensaje global enviado.

## Postcondiciones
- El mensaje queda enviado a residentes y trabajadores.
- El sistema conserva el registro del mensaje global.

---
# CU-04 Enviar Mensaje Directo

## Descripción
Permite enviar un mensaje directo a un usuario específico del condominio.

## Actor Principal
Administrador, Residente o Trabajador

## Precondiciones
- El usuario debe haber iniciado sesión.
- El destinatario debe estar registrado en el sistema.

## Flujo Principal
1. El usuario ingresa al módulo de comunicación.
2. Selecciona la opción de mensaje directo.
3. Busca o selecciona al destinatario.
4. Escribe el contenido del mensaje.
5. El sistema valida el destinatario y el mensaje.
6. El sistema envía el mensaje directo.
7. El sistema registra el mensaje enviado.

## Postcondiciones
- El mensaje queda enviado al destinatario seleccionado.
- El sistema guarda el historial del mensaje directo.

---
# CU-05 Crear Reporte de Comunicación

## Descripción
Permite al Administrador generar reportes sobre mensajes, comunicados y anuncios enviados dentro del condominio.

## Actor Principal
Administrador

## Precondiciones
- El Administrador debe haber iniciado sesión.
- Deben existir registros de comunicación en el sistema.

## Flujo Principal
1. El Administrador ingresa al módulo de comunicación.
2. Selecciona la opción de reportes.
3. Define los filtros del reporte.
4. El sistema consulta los registros de comunicación.
5. El sistema genera el reporte.
6. El Administrador visualiza o descarga el reporte.

## Postcondiciones
- El reporte queda generado.
- El Administrador puede revisar la información de comunicación.

---

# CU-06 Modificar Anuncio General

## Descripción
Permite al Administrador modificar anuncios generales publicados previamente.

## Actor Principal
Administrador

## Precondiciones
- El Administrador debe haber iniciado sesión.
- Debe existir un anuncio general publicado.

## Flujo Principal
1. El Administrador ingresa al módulo de comunicación.
2. Selecciona la lista de anuncios generales.
3. Elige el anuncio que desea modificar.
4. Edita la información del anuncio.
5. El sistema valida los cambios realizados.
6. El sistema actualiza el anuncio general.

## Postcondiciones
- El anuncio general queda actualizado.
- El sistema guarda los cambios realizados.

---

# CU-07 Eliminar Anuncio General

## Descripción
Permite al Administrador eliminar anuncios generales publicados previamente.

## Actor Principal
Administrador

## Precondiciones
- El Administrador debe haber iniciado sesión.
- Debe existir un anuncio general publicado.

## Flujo Principal
1. El Administrador ingresa al módulo de comunicación.
2. Selecciona la lista de anuncios generales.
3. Elige el anuncio que desea eliminar.
4. El sistema solicita confirmación.
5. El Administrador confirma la eliminación.
6. El sistema elimina el anuncio general.

## Postcondiciones
- El anuncio general queda eliminado.
- El sistema actualiza la lista de anuncios disponibles.