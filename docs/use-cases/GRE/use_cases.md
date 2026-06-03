# Módulo GRE - Check-in

## Índice

* [CU-01 enviarAlerta](#cu-01-enviaralerta)
* [CU-02 programarVisita](#cu-02-programarvisita)
* [CU-03 registrarEntrada](#cu-03-registrarentrada)
* [CU-04 notificarEntrada](#cu-04-notificarentrada)
* [CU-05 registrarHistorial](#cu-05-registrarhistorial)
* [CU-06 registrarIngresoParqueadero](#cu-06-registraringresoparqueadero)

---

# CU-01 enviarAlerta

## Descripción

Permite al Administrador o al Personal de Seguridad enviar una alerta a los residentes del condominio en situaciones de emergencia, simulacro o comunicación importante.

## Actor Principal

Administrador, Personal de Seguridad

## Precondiciones

* El actor está autorizado para enviar alertas.
* Existen residentes registrados para recibir la alerta.
* La información de la alerta está disponible para su comunicación.

## Postcondiciones

* La alerta queda enviada a los residentes correspondientes.
* La alerta queda registrada para su posterior consulta.

## Escenario Básico

1. El proceso inicia cuando el Administrador o el Personal de Seguridad solicita enviar una alerta.
2. Se solicita la información necesaria para comunicar la alerta.
3. El actor proporciona el tipo de alerta, el contenido de la comunicación y los destinatarios.
4. Se verifica que la información de la alerta sea correcta y completa.
5. El actor confirma el envío de la alerta.
6. La alerta se comunica a los residentes seleccionados.
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
3. Se identifica que no existen residentes disponibles para recibir la alerta.
4. Se informa al actor que no existen destinatarios disponibles.
5. El proceso finaliza sin enviar la alerta.

---

# CU-02 programarVisita

## Descripción

Permite al Residente programar una visita antes de que el visitante llegue al condominio. La información registrada facilita el ingreso y permite que el Personal de Seguridad verifique la autorización.

## Actor Principal

Residente

## Precondiciones

* El Residente está autorizado para programar visitas.
* El Residente posee una cuenta activa en el condominio.
* El visitante todavía no ha ingresado al condominio.

## Postcondiciones

* La visita queda programada.
* El Personal de Seguridad puede consultar la visita programada.

## Escenario Básico

1. El proceso inicia cuando el Residente solicita programar una visita.
2. Se solicita la información necesaria para programar la visita.
3. El Residente proporciona los datos del visitante.
4. El Residente proporciona la fecha y hora estimada de llegada.
5. El Residente indica el motivo de la visita.
6. Se verifica que la información de la visita sea correcta y completa.
7. El Residente confirma la programación de la visita.
8. La visita queda registrada como visita programada.
9. El proceso finaliza cuando se informa que la visita fue programada exitosamente.

## Escenarios Alternos

### A1. Información incompleta

1. El proceso inicia cuando el Residente solicita programar una visita.
2. El Residente proporciona los datos del visitante.
3. Se identifica que falta información requerida para programar la visita.
4. Se informa al Residente que debe completar toda la información necesaria.
5. El proceso finaliza sin programar la visita.

### A2. Fecha u hora no válida

1. El proceso inicia cuando el Residente solicita programar una visita.
2. El Residente proporciona la fecha y hora estimada de llegada.
3. Se identifica que la fecha u hora indicada no es válida para la programación.
4. Se informa al Residente que la fecha u hora ingresada no es válida.
5. El proceso finaliza sin programar la visita.

### A3. Visitante ya programado

1. El proceso inicia cuando el Residente solicita programar una visita.
2. El Residente proporciona los datos del visitante.
3. Se identifica que el visitante ya tiene una visita programada para la misma fecha.
4. Se informa al Residente que el visitante ya tiene una visita programada.
5. El proceso finaliza sin registrar una nueva programación.

---

# CU-03 registrarEntrada

## Descripción

Permite al Personal de Seguridad registrar el ingreso de un visitante al condominio. Como parte de este registro, se informa al residente sobre la llegada del visitante y se conserva el historial del ingreso.

## Actor Principal

Personal de Seguridad

## Precondiciones

* El Personal de Seguridad está autorizado para registrar entradas.
* El visitante se encuentra en el punto de ingreso del condominio.
* La información del visitante y del residente está disponible para su revisión.

## Postcondiciones

* La entrada del visitante queda registrada.
* El residente recibe la notificación de entrada.
* El ingreso queda guardado en el historial.
* Si el visitante ingresa con vehículo, se puede registrar el ingreso al parqueadero.

## Escenario Básico

1. El proceso inicia cuando el visitante llega al condominio.
2. El Personal de Seguridad solicita registrar la entrada del visitante.
3. Se solicita la información necesaria para registrar el ingreso.
4. El Personal de Seguridad proporciona los datos del visitante.
5. El Personal de Seguridad indica el residente al que visita.
6. El Personal de Seguridad registra la fecha, hora y motivo de la visita.
7. Se verifica que la información del ingreso sea correcta y completa.
8. Se informa al Residente sobre la llegada del visitante.
9. Se conserva el registro del ingreso en el historial del condominio.
10. El proceso finaliza cuando se informa que la entrada fue registrada exitosamente.

## Escenarios Alternos

### A1. Visitante no autorizado

1. El proceso inicia cuando el visitante llega al condominio.
2. El Personal de Seguridad proporciona los datos del visitante.
3. Se identifica que el visitante no tiene autorización de ingreso.
4. Se informa al Personal de Seguridad que el visitante no está autorizado.
5. El proceso finaliza sin registrar la entrada.

### A2. Residente no encontrado

1. El proceso inicia cuando el Personal de Seguridad registra la entrada del visitante.
2. El Personal de Seguridad indica el residente al que visita.
3. Se identifica que el residente no se encuentra registrado.
4. Se informa al Personal de Seguridad que el residente no fue encontrado.
5. El proceso finaliza sin registrar la entrada.

### A3. Información incompleta

1. El proceso inicia cuando el Personal de Seguridad registra la entrada del visitante.
2. El Personal de Seguridad proporciona los datos del visitante.
3. Se identifica que falta información requerida para registrar la entrada.
4. Se informa al Personal de Seguridad que debe completar toda la información necesaria.
5. El proceso finaliza sin registrar la entrada.

---

# CU-04 notificarEntrada

## Descripción

Permite informar al Residente que su visitante ha llegado al condominio. Este caso de uso forma parte obligatoria de registrarEntrada.

## Actor Principal

Personal de Seguridad

## Precondiciones

* Se está realizando registrarEntrada.
* La información del visitante ha sido revisada.
* El residente se encuentra registrado.

## Postcondiciones

* El Residente recibe la notificación de llegada del visitante.

## Escenario Básico

1. El proceso inicia cuando se acepta el ingreso del visitante.
2. Se identifica al Residente relacionado con la visita.
3. Se prepara la notificación de entrada.
4. Se informa al Residente sobre la llegada del visitante.
5. El proceso finaliza cuando la notificación queda enviada exitosamente.

## Escenarios Alternos

### A1. No se logra notificar al Residente

1. El proceso inicia cuando se prepara la notificación de entrada.
2. Se intenta informar al Residente sobre la llegada del visitante.
3. Se identifica que la notificación no pudo ser enviada.
4. Se informa que no se logró notificar al Residente.
5. El proceso finaliza sin enviar la notificación.

### A2. Residente sin medio de contacto disponible

1. El proceso inicia cuando se identifica al Residente relacionado con la visita.
2. Se identifica que el Residente no tiene un medio de contacto disponible.
3. Se informa que el Residente no tiene un medio de contacto registrado.
4. El proceso finaliza sin enviar la notificación.

---

# CU-05 registrarHistorial

## Descripción

Permite conservar la información del ingreso de un visitante en el historial del condominio. Este caso de uso forma parte obligatoria de registrarEntrada.

## Actor Principal

Personal de Seguridad

## Precondiciones

* Se está realizando registrarEntrada.
* La información del visitante ha sido revisada.
* La entrada del visitante ha sido aceptada.

## Postcondiciones

* El ingreso del visitante queda registrado en el historial.
* La información puede ser consultada posteriormente.

## Escenario Básico

1. El proceso inicia cuando se acepta el registro de entrada.
2. Se reúne la información del visitante, residente, fecha, hora y motivo de visita.
3. Se deja constancia de la información del ingreso.
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
2. Se identifica que la información del visitante o del residente está incompleta.
3. Se informa que la información del ingreso está incompleta.
4. El proceso finaliza sin registrar el historial.

---

# CU-06 registrarIngresoParqueadero

## Descripción

Permite al Personal de Seguridad registrar el ingreso de un vehículo al parqueadero de visita. Este caso de uso extiende a registrarEntrada, ya que solo se realiza cuando el visitante ingresa con vehículo.

## Actor Principal

Personal de Seguridad

## Precondiciones

* Se está realizando registrarEntrada.
* El visitante ingresa con vehículo.
* Existe disponibilidad de parqueadero de visita.

## Postcondiciones

* El ingreso del vehículo queda registrado.
* El parqueadero asignado queda asociado a la visita.

## Escenario Básico

1. El proceso inicia cuando el visitante indica que ingresa con vehículo.
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

1. El proceso inicia cuando el visitante indica que ingresa con vehículo.
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

### A3. Placa ya registrada en una visita activa

1. El proceso inicia cuando el Personal de Seguridad proporciona los datos del vehículo.
2. Se revisa la placa del vehículo.
3. Se identifica que la placa ya se encuentra asociada a una visita activa.
4. Se informa al Personal de Seguridad que la placa ya se encuentra registrada en una visita activa.
5. El proceso finaliza sin registrar el ingreso al parqueadero.
