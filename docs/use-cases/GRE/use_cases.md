# Módulo GRE - Check-in

## Índice

* [CU-01 enviarAlerta](#cu-01-enviaralerta)
* [CU-02 programarVisita](#cu-02-programarvisita)
* [CU-03 registrarEntrada](#cu-03-registrarentrada)
* [CU-04 registrarEntradaExterna](#cu-04-registrarentradaexterna)
* [CU-05 notificarVisitaExterna](#cu-05-notificarvisitaexterna)
* [CU-06 registrarHistorial](#cu-06-registrarhistorial)
* [CU-07 registrarIngresoParqueadero](#cu-07-registraringresoparqueadero)
* [CU-08 gestionarHistorial](#cu-08-gestionarhistorial)

---

> **Nota general:** El Administrador puede realizar las mismas acciones asignadas al Residente y al Presidente. Esta aclaración permite evitar demasiadas conexiones en el diagrama de casos de uso y mantiene el modelo más legible.

---

# CU-01 enviarAlerta

## Descripción

El sistema permitirá que el Presidente o el Personal de Seguridad envíe alertas al resto de miembros del condominio en situaciones de emergencia. 

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Tipo de alerta, contenido de la comunicación y destinatarios. |
| **Salidas** | Alerta enviada a los residentes seleccionados y confirmación del envío. |

## Escenario Básico

1. El proceso inicia cuando el Presidente o el Personal de Seguridad solicita enviar una alerta.
2. Se solicita la información necesaria para comunicar la alerta.
3. El actor proporciona el tipo de alerta, el contenido de la comunicación y los destinatarios.
4. Se verifica que la información de la alerta sea correcta y completa.
5. El actor confirma el envío de la alerta.
6. La alerta se comunica al o a los destinatarios.
7. El proceso finaliza cuando se informa que la alerta fue enviada exitosamente.

## Escenarios Alternos

### A1. Información incompleta

1. El proceso inicia cuando el actor solicita enviar una alerta.
2. El actor proporciona la información de la alerta.
3. Se identifica que falta información requerida para enviar la alerta.
4. Se informa al actor que debe completar toda la información necesaria.
5. El proceso finaliza sin enviar la alerta.

### A2. Destinatarios no disponibles

1. El proceso inicia cuando el actor solicita enviar una alerta.
2. El actor define los destinatarios de la alerta.
3. Se identifica que no existen destinatarios disponibles para recibir la alerta.
4. Se informa al actor que no existen destinatarios disponibles.
5. El proceso finaliza sin enviar la alerta.

---

# CU-02 programarVisita

## Descripción

Permite al Residente o al Presidente programar una visita antes de que el visitante llegue al condominio. La información registrada facilita el ingreso y permite que el Personal de Seguridad verifique la autorización.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Datos del visitante, fecha y hora estimada de llegada, motivo de la visita y residente o unidad relacionada. |
| **Salidas** | Visita programada y confirmación de la programación. |

## Escenario Básico

1. El proceso inicia cuando el Residente o el Presidente solicita programar una visita.
2. Se solicita la información necesaria para programar la visita.
3. El actor proporciona los datos del visitante.
4. El actor proporciona la fecha y hora estimada de llegada.
5. El actor indica el motivo de la visita.
6. Se verifica que la información de la visita sea correcta y completa.
7. El actor confirma la programación de la visita.
8. La visita queda registrada como visita programada.
9. El proceso finaliza cuando se informa que la visita fue programada exitosamente.

## Escenarios Alternos

### A1. Información incompleta

1. El proceso inicia cuando el actor solicita programar una visita.
2. El actor proporciona los datos del visitante.
3. Se identifica que falta información requerida para programar la visita.
4. Se informa al actor que debe completar toda la información necesaria.
5. El proceso finaliza sin programar la visita.

### A2. Fecha u hora no válida

1. El proceso inicia cuando el actor solicita programar una visita.
2. El actor proporciona la fecha y hora estimada de llegada.
3. Se identifica que la fecha u hora indicada no es válida para la programación.
4. Se informa al actor que la fecha u hora ingresada no es válida.
5. El proceso finaliza sin programar la visita.

### A3. Visitante ya programado

1. El proceso inicia cuando el actor solicita programar una visita.
2. El actor proporciona los datos del visitante.
3. Se identifica que el visitante ya tiene una visita programada para la misma fecha.
4. Se informa al actor que el visitante ya tiene una visita programada.
5. El proceso finaliza sin registrar una nueva programación.

---

# CU-03 registrarEntrada

## Descripción

Permite al Personal de Seguridad registrar el ingreso de un Residente al condominio. Como parte de este registro, se conserva la información del ingreso en el historial correspondiente.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Identificación del residente, datos de verificación, fecha y hora de ingreso. |
| **Salidas** | Entrada del residente registrada, historial actualizado y confirmación del registro. |

## Escenario Básico

1. El proceso inicia cuando el Residente llega al punto de ingreso del condominio.
2. El Personal de Seguridad solicita registrar la entrada del Residente.
3. Se solicita la información necesaria para registrar el ingreso.
4. El Personal de Seguridad proporciona o verifica los datos del Residente.
5. Se verifica que el Residente se encuentre registrado y autorizado para ingresar.
6. Se registra la fecha y hora del ingreso.
7. Se ejecuta el caso de uso **registrarHistorial** para conservar la información del ingreso.
8. El proceso finaliza cuando se informa que la entrada fue registrada exitosamente.

## Escenarios Alternos



### A1. Información incompleta

1. El proceso inicia cuando el Personal de Seguridad registra la entrada del Residente.
2. El Personal de Seguridad proporciona los datos disponibles.
3. Se identifica que falta información requerida para registrar la entrada.
4. Se informa al Personal de Seguridad que debe completar toda la información necesaria.
5. El proceso finaliza sin registrar la entrada.

### A2. No se logra registrar el historial

1. El proceso inicia cuando la entrada del Residente ya fue validada.
2. Se ejecuta el caso de uso **registrarHistorial**.
3. Se identifica que no fue posible conservar la información del ingreso.
4. Se informa que no se logró registrar el historial correspondiente.
5. El proceso finaliza sin completar el registro de entrada.

---

# CU-04 registrarEntradaExterna

## Descripción

Permite al Personal de Seguridad registrar el ingreso de una persona externa al condominio, como un visitante, proveedor o personal externo autorizado. Como parte obligatoria del proceso, se ejecutan los casos de uso **notificarVisitaExterna** y **registrarHistorial**. Además, cuando la persona externa requiere parqueadero, se ejecuta el caso de uso extendido **registrarIngresoParqueadero**.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Datos de la persona externa, residente o destino asociado, motivo de ingreso, fecha y hora de ingreso. |
| **Salidas** | Entrada externa registrada, notificación de visita externa enviada, parqueadero registrado cuando corresponda historial actualizado y confirmación del registro. |

## Escenario Básico

1. El proceso inicia cuando una persona externa llega al punto de ingreso del condominio.
2. El Personal de Seguridad solicita registrar la entrada externa.
3. Se solicita la información necesaria para registrar y verificar el ingreso.
4. El Personal de Seguridad proporciona los datos de la persona externa.
5. El Personal de Seguridad indica el residente, área o destino relacionado con el ingreso.
6. El Personal de Seguridad registra la fecha, hora y motivo de ingreso.
7. Se verifica que la información del ingreso sea correcta y completa.
8. Si la persona externa requiere parqueadero, se ejecuta el caso de uso extendido **registrarIngresoParqueadero**.
9. Se ejecuta el caso de uso **notificarVisitaExterna** para informar al residente o responsable sobre la llegada de la persona externa.
10. Se ejecuta el caso de uso **registrarHistorial** para conservar la información del ingreso externo.
11. El proceso finaliza cuando se informa que la entrada externa fue registrada exitosamente.

## Escenarios Alternos

### A1. Persona externa no autorizada

1. El proceso inicia cuando la persona externa llega al condominio.
2. El Personal de Seguridad proporciona los datos de la persona externa.
3. Se identifica que la persona externa no tiene autorización de ingreso.
4. Se informa al Personal de Seguridad que la persona externa no está autorizada.
5. El proceso finaliza sin registrar la entrada externa.

### A2. Información incompleta

1. El proceso inicia cuando el Personal de Seguridad registra la entrada externa.
2. El Personal de Seguridad proporciona los datos disponibles de la persona externa.
3. Se identifica que falta información requerida para registrar la entrada externa.
4. Se informa al Personal de Seguridad que debe completar toda la información necesaria.
5. El proceso finaliza sin registrar la entrada externa.


### A3. No se logra notificar la visita externa

1. El proceso inicia cuando la entrada externa ya fue validada.
2. Se ejecuta el caso de uso **notificarVisitaExterna**.
3. Se identifica que no fue posible informar al residente o responsable sobre la llegada de la persona externa.
4. Se informa al Personal de Seguridad que no se logró enviar la notificación correspondiente.
5. El proceso finaliza sin completar el registro de entrada externa.

### A4. No se logra registrar el historial

1. El proceso inicia cuando la entrada externa ya fue validada y la visita externa fue notificada.
2. Se ejecuta el caso de uso **registrarHistorial**.
3. Se identifica que no fue posible conservar la información del ingreso externo.
4. Se informa que no se logró registrar el historial correspondiente.
5. El proceso finaliza sin completar el registro de entrada externa.

### A5. Persona externa no requiere parqueadero

1. El proceso inicia cuando la entrada externa ya fue validada.
2. Se identifica que la persona externa no ingresa con vehículo o no requiere parqueadero.
3. No se ejecuta el caso de uso extendido **registrarIngresoParqueadero**.
4. El proceso continúa con el registro del historial del ingreso externo.
---

# CU-05 notificarVisitaExterna

## Descripción

Permite informar al Residente o Presidente que una persona externa ha llegado al condominio. Este caso de uso forma parte obligatoria de registrarEntradaExterna.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Datos de la persona externa, responsable relacionado y motivo de ingreso. |
| **Salidas** | Notificación enviada y confirmación de la notificación. |

## Escenario Básico

1. El proceso inicia cuando se acepta el ingreso de una persona externa.
2. Se identifica al Residente o responsable relacionado con la visita externa.
3. Se prepara la notificación con los datos principales de la persona externa.
4. Se informa al Residente o responsable sobre la llegada de la persona externa.
5. El proceso finaliza cuando la notificación queda enviada exitosamente.

## Escenarios Alternos

### A1. No se logra notificar al Residente o responsable

1. El proceso inicia cuando se prepara la notificación de visita externa.
2. Se intenta informar al Residente o responsable sobre la llegada de la persona externa.
3. Se identifica que la notificación no pudo ser enviada.
4. Se informa que no se logró notificar al Residente o responsable.
5. El proceso finaliza sin enviar la notificación.


---

# CU-06 registrarHistorial

## Descripción

Permite conservar la información de los ingresos registrados en el historial del condominio. Este caso de uso forma parte obligatoria de registrarEntrada y registrarEntradaExterna.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Datos del ingreso, persona relacionada, fecha, hora, motivo y tipo de entrada. |
| **Salidas** | Historial registrado y confirmación del registro. |

## Escenario Básico

1. El proceso inicia cuando se acepta el registro de entrada.
2. Se reúne la información de la persona que ingresa, la fecha, la hora, el motivo y el tipo de entrada.
3. Se registra la información del ingreso en el historial correspondiente.
4. El ingreso queda registrado en el historial del condominio.
5. El proceso finaliza cuando se confirma que el historial fue registrado exitosamente.

## Escenarios Alternos

### A1. No se logra conservar el historial

1. El proceso inicia cuando se reúne la información del ingreso.
2. Se intenta dejar constancia del ingreso en el historial del condominio.
3. Se identifica que el historial no pudo ser registrado.
4. Se informa que no se logró registrar el historial.
5. El proceso finaliza sin guardar el historial.

### A2. Información incompleta del ingreso

1. El proceso inicia cuando se reúne la información del ingreso.
2. Se identifica que la información del ingreso está incompleta.
3. Se informa que la información del ingreso debe completarse.
4. El proceso finaliza sin guardar el historial.

---

# CU-07 registrarIngresoParqueadero

## Descripción

Permite al Personal de Seguridad registrar el ingreso de un vehículo al parqueadero de visita. Este caso de uso extiende a registrarEntradaExterna, ya que solo se realiza cuando la persona externa ingresa con vehículo.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Datos del vehículo, placa, persona externa relacionada y parqueadero asignado. |
| **Salidas** | Ingreso del vehículo registrado, parqueadero asociado a la visita externa y confirmación del registro. |

## Escenario Básico

1. El proceso inicia durante registrarEntradaExterna, cuando se identifica que la persona externa ingresa con vehículo o requiere parqueadero.
2. El Personal de Seguridad solicita registrar el ingreso al parqueadero.
3. Se solicita la información necesaria del vehículo y del parqueadero de visita.
4. El Personal de Seguridad proporciona los datos del vehículo.
5. El Personal de Seguridad asigna un parqueadero disponible.
6. Se verifica que la información del vehículo y del parqueadero sea correcta y completa.
7. El Personal de Seguridad confirma el registro del ingreso al parqueadero.
8. El ingreso del vehículo queda registrado.
9. El proceso finaliza cuando se informa que el ingreso al parqueadero fue registrado exitosamente.

## Escenarios Alternos

### A1. No existe parqueadero disponible

1. El proceso inicia cuando la persona externa indica que ingresa con vehículo.
2. El Personal de Seguridad solicita registrar el ingreso al parqueadero.
3. Se identifica que no existen parqueaderos disponibles.
4. Se informa al Personal de Seguridad que no existen parqueaderos disponibles.
5. El proceso finaliza sin registrar el ingreso al parqueadero.

### A2. Datos del vehículo incompletos

1. El proceso inicia cuando el Personal de Seguridad registra el ingreso al parqueadero.
2. El Personal de Seguridad proporciona los datos del vehículo.
3. Se identifica que falta información requerida del vehículo.
4. Se informa al Personal de Seguridad que debe completar toda la información necesaria.
5. El proceso finaliza sin registrar el ingreso al parqueadero.

### A3. Placa ya registrada en una visita externa activa

1. El proceso inicia cuando el Personal de Seguridad proporciona los datos del vehículo.
2. Se revisa la placa del vehículo.
3. Se identifica que la placa ya se encuentra asociada a una visita externa activa.
4. Se informa al Personal de Seguridad que la placa ya se encuentra registrada en una visita externa activa.
5. El proceso finaliza sin registrar el ingreso al parqueadero.

---

# CU-08 gestionarHistorial

## Descripción

Permite al Presidente consultar y revisar el historial de ingresos del condominio, con el fin de verificar entradas de residentes, entradas externas y registros asociados al parqueadero.

## Entradas y Salidas

| Elemento | Descripción |
|-----------|------------|
| **Entradas** | Criterios de búsqueda, fechas, tipo de ingreso, persona relacionada o placa del vehículo. |
| **Salidas** | Historial consultado, detalle de registros encontrados y confirmación de la consulta. |

## Escenario Básico

1. El proceso inicia cuando el Presidente solicita gestionar el historial de ingresos.
2. Se solicita la información necesaria para consultar el historial.
3. El Presidente proporciona los criterios de búsqueda, como fecha, tipo de ingreso, persona relacionada o placa del vehículo.
4. Se verifica que los criterios de búsqueda sean correctos.
5. Se revisan los registros del historial del condominio.
6. Se muestra la información encontrada según los criterios ingresados.
7. El Presidente revisa el detalle de los ingresos registrados.
8. El proceso finaliza cuando se informa que la consulta del historial fue realizada exitosamente.

## Escenarios Alternos

### A1. Criterios de búsqueda incompletos

1. El proceso inicia cuando el Presidente solicita gestionar el historial.
2. El Presidente proporciona los criterios de búsqueda disponibles.
3. Se identifica que falta información necesaria para realizar la consulta.
4. Se informa al Presidente que debe completar los criterios de búsqueda.
5. El proceso finaliza sin consultar el historial.

### A2. No existen registros relacionados

1. El proceso inicia cuando el Presidente proporciona los criterios de búsqueda.
2. Se revisan los registros del historial del condominio.
3. Se identifica que no existen registros relacionados con los criterios ingresados.
4. Se informa al Presidente que no se encontraron registros para la consulta realizada.
5. El proceso finaliza sin mostrar resultados de historial.

### A3. Fecha no válida

1. El proceso inicia cuando el Presidente proporciona una fecha o rango de fechas para consultar el historial.
2. Se identifica que la fecha ingresada no es válida.
3. Se informa al Presidente que la fecha indicada no es válida para la consulta.
4. El proceso finaliza sin consultar el historial.
