# Módulo de Finanzas y Pagos GRA

## Índice

- [generarReporteDePagosRealizados](#requisito:-generarReporteDePagosRealizados)
- [registrarEntidadBancaria](#requisito:-registrarEntidadBancaria)
- [eliminarDeuda](#requisito:-eliminarDeuda)
- [registrarDeuda](#cu-04-asignar-rol-a-usuario)
- [registrarPagoEfectivo/Transferencia](#cu-05-definir-permisos-de-rol)
- [modificarFechaMaximaDePagoDeUnaDeuda](#cu-06-iniciar-sesión)
- [definirValorMensualDeAlicuotas](#cu-07-actualizar-perfil)
- [consultarDeuda](#cu-08-recuperar-contraseña)
- [consultarPagosEfectuados](#cu-09-enviar-token-de-restablecimiento)
- [solicitarPagoEnCuotas](#cu-09-enviar-token-de-restablecimiento)
- [eliminarFormaDePagoRecurrente](#cu-09-enviar-token-de-restablecimiento)
- [generarCertificadoDeNoDeudor](#cu-09-enviar-token-de-restablecimiento)
- [registrarPago](#cu-09-enviar-token-de-restablecimiento)
- [definirPagoDeAlicuotasDeFormaMensual](#cu-09-enviar-token-de-restablecimiento)
- [registrarDeudaMensual](#cu-09-enviar-token-de-restablecimiento)
- [enviarRecordatorioDeDeudaPendiente](#cu-09-enviar-token-de-restablecimiento)


---

# Requisito: generarReporteDePagosRealizados

El sistema permitirá generar un reporte financiero de los pagos realizados por los residentes

## Datos del Caso de Uso

- **Actor:** Administrador
- **Entradas:** fecha de inicio y fecha de fin
- **Salidas:** reporte de pagos realizados y mensaje

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando la fecha de inicio
2. El Administrador ingresa la fecha de fin
3. El Sistema valida el formato de las fechas
4. Si las fechas tienen un formato valido, entonces el sistema valida que las fechas sean mayores o iguales a la fecha actual
5. Si las fechas son mayores o iguales a la fecha actual, entonces el sistema valida que la fecha de fin sea mayor o igual a la fecha de inicio
6. Si la fecha de fin es mayor o igual a la fecha de inicio, entonces el Sistema consulta todos los pagos efectuados durante ese periodo.
7. El Sistema calcula el total de ingresos por multas, alicuotas y reservas.
8. El Sistema genera el reporte donde cada registro contiene: número de cédula de identidad del residente asociado al pago, valor del pago y motivo y muestra el total de valor del pago de multas, alicuotas y reservas.
9. El caso de uso termina con el Sistema mostrando el reporte y emitiendo el mensaje "Reporte generado correctamente".


## Escenarios Alternos

### Escenario Alterno 1

1. El caso de uso inicia con el Administrador ingresando la fecha de inicio
2. El Administrador ingresa la fecha de fin
3. El Sistema valida el formato de las fechas
4. Si las fechas NO tienen un formato valido, entonces el caso de uso finaliza con el Sistema emitiendo el mensaje "Formato de fechas incorrecto, ingrese la fecha en formato DD/MM/YYYY"

### Escenario Alterno 2

1. El caso de uso inicia con el Administrador ingresando la fecha de inicio
2. El Administrador ingresa la fecha de fin
3. El Sistema valida el formato de las fechas
4. Si las fechas tienen un formato valido, entonces el sistema valida que las fechas sean mayores o iguales a la fecha actual
5. Si las fechas NO son mayores o iguales a la fecha actual, entonces el caso de uso finaliza con el Sistema emitiendo el mensaje "Las fechas deben ser mayores o iguales a la fecha actual"

### Escenario Alterno 3

1. El caso de uso inicia con el Administrador ingresando la fecha de inicio
2. El Administrador ingresa la fecha de fin
3. El Sistema valida el formato de las fechas
4. Si las fechas tienen un formato valido, entonces el sistema valida que las fechas sean mayores o iguales a la fecha actual
5. Si las fechas son mayores o iguales a la fecha actual, entonces el sistema valida que la fecha de fin sea mayor o igual a la fecha de inicio
6. Si la fecha de fin NO es mayor o igual a la fecha de inicio, entonces el caso de uso termina con el Sistema emitiendo el mensaje "La fecha de fin debe ser mayor o igual a la fecha de inicio para la consulta"

---

## Requisito: registrarEntidadBancaria

El sistema permitirá a un Administrador registrar una entidad bancaria.

## Datos del Caso de Uso

- **Actor:** Administrador
- **Entradas:** nombre de la entidad bancaria, número de cuenta, número de cédula de identidad del titular de la cuenta, tipo de cuenta, correo electrónico del titular la cuenta
- **Salidas:** mensaje

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando el número de cuenta de la entidad bancaria
2. El Administrador ingresa el nombre de la entidad bancaria.
3. El Administrador ingresa el número de cédula de identidad del titular de la cuenta
4. El Administrador ingresa el tipo de cuenta
5. El Administrador ingresa el correo electrónico del titular de la cuenta
6. El Sistema valida que la información ingresada tenga formato valido.
7. SI la información ingresada es válida ENTONCES el Sistema verifica que no exista una entidad bancaria registrada con el número de cuenta ingresado.
8. SI no existe una entidad bancaria registrada con el número de cuenta ingresado ENTONCES el Sistema registra la entidad bancaria.
9. El caso de uso termina con el Sistema emitiendo el mensaje "Entidad bancaria registrada correctamente".

## Escenarios Alternos

### Escenario Alterno 1

1. El caso de uso inicia con el Administrador ingresando el número de cuenta de la entidad bancaria
2. El Administrador ingresa el nombre de la entidad bancaria.
3. El Administrador ingresa el número de cédula de identidad del titular de la cuenta
4. El Administrador ingresa el tipo de cuenta
5. El Administrador ingresa el correo electrónico del titular de la cuenta
6. El Sistema valida que la información ingresada tenga formato valido.
3. SI la información ingresada NO es válida ENTONCES el Sistema emite el mensaje "La información de la entidad bancaria no es válida".
4. El caso de uso termina sin registrar la entidad bancaria.

### Escenario Alterno 2

1. El caso de uso inicia con el Administrador ingresando el número de cuenta de la entidad bancaria
2. El Administrador ingresa el nombre de la entidad bancaria.
3. El Administrador ingresa el número de cédula de identidad del titular de la cuenta
4. El Administrador ingresa el tipo de cuenta
5. El Administrador ingresa el correo electrónico del titular de la cuenta
6. El Sistema valida que la información ingresada tenga formato valido.
7. SI la información ingresada es válida ENTONCES el Sistema verifica que no exista una entidad bancaria registrada con el número de cuenta ingresado.
8. SI ya existe una entidad bancaria registrada con el número de cuenta ingresado ENTONCES el Sistema emite el mensaje "Ya existe una entidad bancaria registrada con el número de cuenta ingresado".
9. El caso de uso termina sin registrar nuevamente la entidad bancaria.

---

# Requisito: eliminarDeuda

## Datos del Caso de Uso

- **Actor:** Administrador
- **Entradas:** identificador de la deuda
- **Salidas:** mensaje de confirmacion

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando el identificador de la deuda
2. El Sistema verifica que exista una deuda con el identificador proporcionado
3. Si existe una deuda con el identificador proporcionado, entonces el Sistema cambia el estado de la deuda a "ELIMINADA"
4. El caso de uso finaliza con el Sistema emitiendo el mensaje "Deuda Eliminada Exitosamente"


## Escenarios Alternativos

### Escenario alternativo 1**
1. El caso de uso inicia con el Administrador ingresando el identificador de la deuda
2. El Sistema verifica que exista una deuda con el identificador proporcionado
3. Si NO existe una deuda con el identificador proporcionado, entonces el caso de uso finaliza con el Sistema emitiendo el mensaje " No existe una deuda con el identificador proporcionado.

---

# Requisito: registrarDeuda

El sistema permitirá a un Administrador registrar una deuda

## Datos del Caso de Uso

- **Actor:** Administrador
- **Entradas:** número de cédula de identidad del residente, motivo de la deuda (ALICUOTA, MULTA, RESERVA), fecha maxima de pago, descripcion, valor de la deuda
- **Salidas:** mensaje

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando el número de cedula de identidad del Residente
2. El Sistema verifica que exista un Residente con el número de cédula de identidad proporcionado
3. Si el Residente existe, entonces el Administrador ingresa el motivo de la deuda
4. El Administrador ingresa la fecha máxima de pago
5. El Administrador ingresa la descripcion
6. El Administrador ingresa el valor de la deuda
7. El Sistema valida que todos los datos tengan un formato válido
8. Si todos los datos tienen un formato válido, entonces el sistema verifica que el motivo de la deuda sea "ALICUOTA", "MULTA" O "RESERVA"
9. Si el motivo de la deuda es ALICUOTA, entonces el Sistema consulta el valor mensual de alícuotas, el departamento del residente, el tamaño del condomio, y calcula el valor de la deuda por alicuota.
10. El caso de uso finaliza con Sistema emitiendo el mensaje: "Deuda por motivo de alicuota con el valor de valorDeuda registrada exitosamente para el residente nombreResidente"


## Escenarios Alternos

### Escenario Alterno 1
1. El caso de uso inicia con el Administrador ingresando el número de cedula de identidad del Residente
2. El Sistema verifica que exista un Residente con el número de cédula de identidad proporcionado
3. Si el Residente existe, entonces el Administrador ingresa el motivo de la deuda
4. El Administrador ingresa la fecha máxima de pago
5. El Administrador ingresa la descripcion
6. El Administrador ingresa el valor de la deuda
7. El Sistema valida que todos los datos tengan un formato válido
8. Si todos los datos tienen un formato válido, entonces el sistema verifica que el motivo de la deuda sea "ALICUOTA", "MULTA" O "RESERVA"
9. Si el motivo de la deuda es MULTA, el caso de uso finaliza con Sistema emitiendo el mensaje: "Deuda por motivo de multa con el valor de valorDeuda registrada exitosamente para el residente nombreResidente"

### Escenario Alterno 2

1. El caso de uso inicia con el Administrador ingresando el número de cedula de identidad del Residente
2. El Sistema verifica que exista un Residente con el número de cédula de identidad proporcionado
3. Si el Residente existe, entonces el Administrador ingresa el motivo de la deuda
4. El Administrador ingresa la fecha máxima de pago
5. El Administrador ingresa la descripcion
6. El Administrador ingresa el valor de la deuda
7. El Sistema valida que todos los datos tengan un formato válido
8. Si todos los datos tienen un formato válido, entonces el sistema verifica que el motivo de la deuda sea "ALICUOTA", "MULTA" O "RESERVA"
9. Si el motivo de la deuda es RESERVA, el caso de uso finaliza con Sistema emitiendo el mensaje: "Deuda por motivo de reserva con el valor de valorDeuda registrada exitosamente para el residente nombreResidente"

### Escenario Alterno 3

1. El caso de uso inicia con el Administrador ingresando el número de cedula de identidad del Residente
2. El Sistema verifica que exista un Residente con el número de cédula de identidad proporcionado
3. Si el Residente NO existe, entonces el Sistema emite el mensaje "No existe un cliente con el número de cédula de identidad proporcionada"
4. El caso de uso finaliza sin registrar una nueva deuda


### Escenario Alterno 4

1. El caso de uso inicia con el Administrador ingresando el número de cedula de identidad del Residente
2. El Sistema verifica que exista un Residente con el número de cédula de identidad proporcionado
3. Si el Residente existe, entonces el Administrador ingresa el motivo de la deuda
4. El Administrador ingresa la fecha máxima de pago
5. El Administrador ingresa la descripcion
6. El Administrador ingresa el valor de la deuda
7. El Sistema valida que todos los datos tengan un formato válido
8. Si los datos NO tienen un formato válido, entonces el Sistema emite el mensaje de error correspondiente
9. El caso de uso finaliza sin que se registre una nueva deuda.

---

# Requisito: registrarPagoEnEfectivo/Transferencia

## Datos del Caso de Uso

- **Entradas:** idDeuda
- **Salidas:** mensaje

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando el idDeuda.
2. El Sistema verifica el idDeuda.
3. SI existe una Deuda con el idDeuda ENTONCES el Sistema verifica el estado de la Deuda.
4. SI el estado de la Deuda es ‘PENDIENTE’ ENTONCES el Administrador cambia el estado de la Deuda a ‘PAGADA’.
5. El Sistema muestra el mensaje “Pago egistrado exitosamente”.
6. El caso de uso finaliza con el cambio de estado de la Deuda a 'PAGADA'.

## Escenarios Alternos

### Escenario Alterno 1

1. El caso de uso inicia con el Administrador ingresando el idDeuda.
2. El Sistema verifica el idDeuda.
3. SI NO existe una Deuda con el idDeuda ENTONCES el Sistema muestra el mensaje “No existe una deuda con el identificador proporcionado”.
4. El caso de uso termina sin registrar el pago.

### Escenario Alterno 2

1. El caso de uso inicia con el Administrador ingresando el idDeuda.
2. El Sistema verifica el idDeuda.
3. SI existe una Deuda con el idDeuda ENTONCES el Sistema verifica el estado de la Deuda.
4. SI el estado de la Deuda es ‘PAGADA’ ENTONCES el Sistema emite el mensaje “Esta deuda ya ha sido pagada”.
5. El caso de uso termina sin registrar el pago.

---

# Requisito: modificarFechaMaximaDePagoDeUnaDeuda
El sistema permitirá modificar la fecha máxima de pago de una deuda.

## Escenario Básico:

1. El caso de uso inicia con el Administrador ingresando el número de deuda.
2. El Sistema verifica el número de deuda ingresado.
3. SI el número de deuda existe ENTONCES el Sistema recupera la deuda identificada por el número de deuda.
4. El Administrador ingresa la nueva fecha de pago.
5. El Sistema valida la nueva fecha de pago ingresada.
6. SI la fecha de pago es válida ENTONCES el Sistema modifica la fecha máxima de pago de la deuda.
7. El caso de uso termina con el Sistema emitiendo el mensaje "Fecha máxima de pago modificada con éxito".

## Escenario(s) alternativo(s):

### Escenario alternativo 1:

1. El caso de uso inicia con el Administrador ingresando el número de deuda.
2. El Sistema verifica el número de deuda ingresado.
3. SI el número de deuda NO existe ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje "Deuda no registrada en el sistema".

### Escenario alternativo 2:

1. El caso de uso inicia con el Administrador ingresando el número de deuda.
2. El Sistema verifica el número de deuda ingresado.
3. SI el número de deuda existe ENTONCES el Sistema recupera la deuda identificada por el número de deuda.
4. El Administrador ingresa la nueva fecha de pago.
5. El Sistema valida la nueva fecha de pago ingresada.
6. SI la fecha de pago NO es válida ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje "La fecha ingresada no es válida, asegúrese que sea mayor que la fecha máxima de pago actual".

---

# Requisito: definirValorMensualDeAlicuotas
El sistema permitirá definir el valor mensual a obtener de las alicuotas

## Escenario Básico:

1. El caso de uso inicia con el Administrador ingresando el nuevo valor de la alícuota.
2. El Sistema valida el valor ingresado.
3. SI el valor ingresado es válido ENTONCES el Sistema cambia el valor de la alícuota.
4. El caso de uso termina con el Sistema emitiendo el mensaje “Valor de la alícuota establecido correctamente”.

## Escenario(s) alternativo(s):

### Escenario alternativo 1:

1. El caso de uso inicia con el Administrador ingresando el nuevo valor de la alícuota.
2. El Sistema valida el valor ingresado.
3. SI el valor ingresado NO es válido ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje “El valor de la alícuota ingresado no es válido, asegúrese que haya ingresado un número mayor a 0 con hasta 2 cifras decimales”.

---

# Requisito: consultarDeuda
El sistema permitirá consultar las deudas pendientes de un residente mediante su número de cédula

## Escenario Básico:**

1. El caso de uso inicia con el Administrador/Residente ingresando el número de cédula de identidad.
2. El Sistema verifica el número de cédula de identidad.
3. SI el número de cédula de identidad es válido ENTONCES el Sistema busca deudas con estado pendiente asociadas al número de cédula ingresado.
4. SI existen deudas con estado pendiente ENTONCES el Sistema genera un reporte con todos los detalles de las deudas encontradas.
5. El caso de uso termina con el Sistema mostrando el reporte generado.

## Escenario(s) alternativo(s):**

### Escenario alternativo 1:**

1. El caso de uso inicia con el Administrador/Residente ingresando el número de cédula de identidad.
2. El Sistema verifica el número de cédula de identidad.
3. SI el número de cédula de identidad NO es válido ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje "El número de cédula ingresado no se encuentra registrado".

### Escenario alternativo 2:**

2. El Sistema verifica el número de cédula de identidad.
3. SI el número de cédula de identidad es válido ENTONCES el Sistema busca deudas con estado pendiente asociadas al número de cédula ingresado.
4. SI existen deudas con estado pendiente ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje "Residente sin deudas pendientes".

---

# Requisito: consultarPagosEfectuados

El sistema permitirá consultar los pagos efectuados durante una fecha de inicio y una fecha de fin.

## Datos del Caso de Uso

- **Actores:** Administrador y Residente
- **Entradas:** fecha de inicio y fecha de fin
- **Salidas:** pagos efectuados y mensaje

## Escenario Básico

1. El caso de uso inicia con el Administrador o el Residente ingresando la fecha de inicio y la fecha de fin.
2. El Sistema valida que las fechas ingresadas tengan un formato válido.
3. SI las fechas ingresadas tienen un formato válido ENTONCES el Sistema verifica que la fecha de fin sea mayor o igual a la fecha de inicio.
4. SI el rango de fechas es válido ENTONCES el Sistema verifica que existan pagos efectuados dentro del rango indicado.
5. SI existen pagos efectuados dentro del rango indicado ENTONCES el Sistema muestra los pagos efectuados correspondientes al actor que inició el caso de uso.
6. El caso de uso termina con el Sistema emitiendo el mensaje "Pagos efectuados consultados correctamente".

## Escenarios Alternos

### Escenario Alterno 1

1. El caso de uso inicia con el Administrador o el Residente ingresando la fecha de inicio y la fecha de fin.
2. El Sistema valida que las fechas ingresadas tengan un formato válido.
3. SI alguna de las fechas ingresadas NO tiene un formato válido ENTONCES el Sistema emite el mensaje "Las fechas ingresadas no son válidas".
4. El caso de uso termina sin mostrar pagos efectuados.

### Escenario Alterno 2

1. El caso de uso inicia con el Administrador o el Residente ingresando la fecha de inicio y la fecha de fin.
2. El Sistema valida que las fechas ingresadas tengan un formato válido.
3. SI las fechas ingresadas tienen un formato válido ENTONCES el Sistema verifica que la fecha de fin sea mayor o igual a la fecha de inicio.
4. SI la fecha de fin es menor que la fecha de inicio ENTONCES el Sistema emite el mensaje "El rango de fechas ingresado no es válido".
6. El caso de uso termina sin mostrar pagos efectuados.

### Escenario Alterno 3

1. El caso de uso inicia con el Administrador o el Residente ingresando la fecha de inicio y la fecha de fin.
2. El Sistema valida que las fechas ingresadas tengan un formato válido.
3. SI las fechas ingresadas tienen un formato válido ENTONCES el Sistema verifica que la fecha de fin sea mayor o igual a la fecha de inicio.
4. SI el rango de fechas es válido ENTONCES el Sistema verifica que existan pagos efectuados dentro del rango indicado.
5. SI NO existen pagos efectuados dentro del rango indicado ENTONCES el Sistema emite el mensaje "No existen pagos efectuados dentro del rango de fechas indicado".
6. El caso de uso termina sin mostrar pagos efectuados.

--- 

# Requisito: solicitarPagoEnCuotas
El Sistema permitirá a un residente solicitar el pago en cuotas.

## Escenario Básico:

1. El caso de uso inicia con el Residente ingresando el idDeuda que desea diferir
2. El Residente ingresa el numero de meses a los cuales desea diferir la deuda 
3. El Residente ingresa al módulo de "Mis Obligaciones" y selecciona una deuda específica elegible para financiamiento (por ejemplo, un cargo extraordinario).
4. El Residente hace clic en la opción "Solicitar Pago en Cuotas".
5. El Sistema despliega un formulario solicitando la información del financiamiento: Número de cuotas deseadas (entre 2 y 12 meses)
6. El Residente selecciona el número de cuotas (entero mayor a uno y menor o igual a 12 ej. 6) y confirma la solicitud.
7. El Sistema calcula automáticamente el valor individual de cada cuota dividiendo el valor total de la deuda para el número de cuotas ingresadas, redondeando a dos decimales.
8. El Sistema muestra una vista previa del plan de pagos detallando: ID del plan, ID del residente, obligación asociada, valor total de la deuda, número de cuotas, valor de cada cuota, fecha de inicio y fecha de finalización.
9. El Residente revisa y acepta los términos del diferimiento.
10. El Sistema registra el plan de pagos con estado "SOLICITADO", genera una notificación para el Administrador, y muestra un mensaje de éxito en pantalla.
11. El plan de pagos queda registrado en la base de datos.

## Escenario(s) alternativo(s):**

### Escenario alternativo 1**
1. El Residente se encuentra autenticado en el sistema.
2. El Residente no posee obligaciones de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El Residente intenta solicitar un diferimiento sobre una obligación cuyo estado es "PAGADO".
4. El Sistema verifica el estado de la obligación en la base de datos.
5. El Sistema al detectar que el estado es "PAGADO", oculta o inhabilita por completo el botón "Solicitar Pago en Cuotas" para esa transacción específica, impidiendo el inicio del flujo.

### Escenario alternativo 2**
1. El Residente se encuentra autenticado en el sistema.
2. El Residente posee al menos una obligación de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El Residente ingresa al módulo de "Mis Obligaciones" y selecciona una deuda específica elegible para financiamiento (por ejemplo, un cargo extraordinario).
4. El Residente intenta solicitar un plan de pagos para una obligación que ya se encuentra asociada a un plan previo en estado "SOLICITADO" o "APROBADO".
5. El Sistema, al procesar la solicitud, realiza una consulta cruzada en la tabla de planes de pago.
6. El Sistema interrumpe el proceso y despliega una alerta en pantalla: "Acción no permitida: Esta obligación ya cuenta con un plan de pagos activo o en proceso de aprobación".
7. El Sistema redirecciona al usuario al módulo de "Mis Obligaciones".

### Escenario alternativo 3**
1. El Residente se encuentra autenticado en el sistema.
2. El Residente posee al menos una obligación de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El Residente ingresa al módulo de "Mis Obligaciones" y selecciona una deuda específica elegible para financiamiento (por ejemplo, un cargo extraordinario).
4. El Residente hace clic en la opción "Solicitar Pago en Cuotas".
5. El Sistema despliega un formulario solicitando la información del financiamiento: Número de cuotas deseadas (entre 2 y 12 meses)
6. El Residente selecciona el número de cuotas (entero mayor a uno y menor o igual a 12 ej. 6) y confirma la solicitud.
7. El Sistema calcula automáticamente el valor individual de cada cuota dividiendo el valor total de la deuda para el número de cuotas ingresadas, redondeando a dos decimales.
8. El Sistema muestra una vista previa del plan de pagos detallando: ID del plan, ID del residente, obligación asociada, valor total de la deuda, número de cuotas, valor de cada cuota, fecha de inicio y fecha de finalización.
9. el Residente decide no aceptar la simulación de las cuotas o el valor individual calculado por el sistema.
10. El Residente hace clic en el botón "Cancelar Solicitud".
11. Sistema: Revierte los cálculos temporales en memoria, no realiza ningún registro en la base de datos y regresa al usuario al módulo de "Mis Obligaciones".

---

# Requisito: eliminarFormaDePagoRecurrente
El sistema permitira eliminar la forma de pago recurrente.
Este escenario describe el camino ideal en el que el usuario elimina una tarjeta o cuenta bancaria configurada para débitos automáticos sin inconvenientes.

## Escenario Básico:**

1. El usuario (Residente) se encuentra autenticado en el sistema. 
2. El usuario tiene al menos una forma de pago configurada en estado "RECURRENTE" o "ACTIVA" (débito automático para alícuotas o mensualidades).
3. El residente ingresa al módulo de "Métodos de Pago".
4. El Sistema consulta y despliega la lista de tarjetas o cuentas asociadas, identificando claramente cuál de ellas está marcada como "Pago Recurrente / Débito Automático".
5. El Residente selecciona el método de pago recurrente y hace clic en el botón "Eliminar Forma de Pago Recurrente" (o "Desvincular Débito Automático").
6. El Sistema despliega un mensaje emergente de confirmación advirtiendo sobre las implicaciones (ej. «Si elimina este método, sus próximas facturas deberán ser canceladas de forma manual antes de la fecha de vencimiento»). El mensaje incluye dos botones: "Confirmar" y "Cancelar".
7. El Residente hace clic en "Confirmar".
8. El Sistema se comunica de forma segura mediante API con la pasarela de pagos para dar de baja el token de recurrencia.
9. La pasarela responde con éxito. El sistema actualiza el estado de la forma de pago en la base de datos interna (la elimina o cambia su estado a "INDIVIDUAL / MANUAL").
10. El Sistema muestra un mensaje de éxito en pantalla: «La recurrencia de pago ha sido eliminada exitosamente».
11. El Sistema envía una notificación automática (correo electrónico) al Residente, confirmando la cancelación del débito automático.
12. Los futuros cargos del residente ya no se debitarán automáticamente y pasarán al flujo de facturación manual en estado "PENDIENTE".

## Escenario(s) alternativo(s):**

### Escenario alternativo 1**

1. El usuario (Residente) se encuentra autenticado en el sistema.
2. El usuario tiene al menos una forma de pago configurada en estado "RECURRENTE" o "ACTIVA" (débito automático para alícuotas o mensualidades).
3. El Residente ingresa al módulo de "Métodos de Pago".
4. El Sistema consulta y despliega la lista de tarjetas o cuentas asociadas, identificando claramente cuál de ellas está marcada como "Pago Recurrente / Débito Automático".
5. El Residente selecciona el método de pago recurrente y hace clic en el botón "Eliminar Forma de Pago Recurrente" (o "Desvincular Débito Automático").
6. El Sistema despliega un mensaje emergente de confirmación advirtiendo sobre las implicaciones (ej. «Si elimina este método, sus próximas facturas deberán ser canceladas de forma manual antes de la fecha de vencimiento»). El mensaje incluye dos botones: "Confirmar" y "Cancelar".
7. El Residente hace clic en el botón "Cancelar" en el mensaje emergente.
8. El Sistema cierra la ventana de confirmación, no realiza modificaciones en la base de datos ni en la pasarela de pagos, y mantiene al usuario en la interfaz de "Métodos de Pago" con la recurrencia intacta.

### Escenario alternativo 2**

1. El usuario (Residente) se encuentra autenticado en el sistema.
2. El usuario tiene al menos una forma de pago configurada en estado "RECURRENTE" o "ACTIVA" (débito automático para alícuotas o mensualidades).
3. El Residente ingresa al módulo de "Métodos de Pago".
4. El Sistema consulta y despliega la lista de tarjetas o cuentas asociadas, identificando claramente cuál de ellas está marcada como "Pago Recurrente / Débito Automático".
5. El Residente selecciona el método de pago recurrente y hace clic en el botón "Eliminar Forma de Pago Recurrente" (o "Desvincular Débito Automático").
6. El Sistema despliega un mensaje emergente de confirmación advirtiendo sobre las implicaciones (ej. «Si elimina este método, sus próximas facturas deberán ser canceladas de forma manual antes de la fecha de vencimiento»). El mensaje incluye dos botones: "Confirmar" y "Cancelar".
7. El Residente hace clic en "Confirmar".
8. El Sistema se comunica de forma segura mediante API con la pasarela de pagos para dar de baja el token de recurrencia.
9. El Sistema espera la respuesta de la pasarela durante un tiempo límite (Timeout). Al no recibir respuesta o recibir un código de error de red (ej. HTTP 503), el sistema revierte los cambios locales en memoria.
10. El Sistema despliega una notificación de error técnico en la interfaz: «Temporalmente no podemos procesar su solicitud. Su método de pago recurrente sigue activo. Por favor, intente más tarde o contacte al administrador».
11. El Sistema registra el incidente en el log de errores interno para auditoría técnica.

---

# Requisito: generarCertificadoDeNoDeudor
El sistema permitirá generar un certificado de no deudor.
Este escenario describe el camino ideal en el que un residente solvente solicita su certificado y el sistema lo genera de manera automática y exitosa.

## Escenario Básico:**

1. El usuario (Residente o Administrador) se encuentra autenticado en el sistema.
2. El Residente cuenta con un expediente registrado (ID de residente y departamento asignado).
3. El usuario ingresa al módulo de "Certificados" o "Estado de Cuenta".
4. El usuario hace clic en el botón "Generar Certificado de No Deudor".
5. El Sistema identifica el ID del residente e inicia una consulta en tiempo real en la base de datos de obligaciones financieras asociadas a dicho usuario.
6. El Sistema verifica que el residente no tenga ninguna obligación en estado "PENDIENTE" o "EN MORA".
7. El Sistema compila la información requerida para el documento: Nombre completo del residente (validando la cadena de texto), Cédula de identidad, ID de departamento, fecha y hora de emisión.
8. El Sistema genera un archivo digital en formato PDF no editable con el Certificado de No Deudor / Solvencia.
9. El Sistema registra en la bitácora de auditoría que se emitió un certificado de solvencia para el residente con su respectiva fecha y hora.
10. El Sistema despliega el documento en pantalla y habilita de forma automática las opciones de "Descargar" e "Imprimir".
11. El Sistema registra la acción en un historial de logs.




## Escenario(s) alternativo(s):**

### Escenario alternativo 1**

1. El usuario (Residente o Administrador) se encuentra autenticado en el sistema.
2. El Residente cuenta con un expediente registrado (ID de residente y departamento asignado).
3. El usuario ingresa al módulo de "Certificados" o "Estado de Cuenta".
4. El usuario hace clic en el botón "Generar Certificado de No Deudor".
5. El Sistema identifica el ID del residente e inicia una consulta en tiempo real en la base de datos de obligaciones financieras asociadas a dicho usuario.
6. El Sistema verifica que el residente no tenga ninguna obligación en estado "PENDIENTE" o "EN MORA", pero detecta que tiene un plan de pagos diferido vigente (cuotas futuras que aún no se han vencido).
7. Sistema: Evalúa si el plan de pagos se encuentra al día.
8. Si las cuotas facturadas hasta la fecha están pagadas, pero quedan cuotas futuras por vencer, el sistema detiene la emisión regular y muestra una advertencia: «Usted posee un plan de pagos activo. Para emitir un certificado de liberación total, debe liquidar las cuotas diferidas anticipadamente. ¿Desea generar un certificado parcial con observaciones?».
9. El usuario selecciona el botón "Aceptar".
10. El Sistema genera el certificado en PDF añadiendo una cláusula de observación obligatoria que indica: "El residente se encuentra al día, manteniendo un convenio de pago activo en cuotas pendientes".
11. El Sistema compila la información requerida para el documento: Nombre completo del residente (validando la cadena de texto), Cédula de identidad, ID de departamento, fecha y hora de emisión.
12. El Sistema genera un archivo digital en formato PDF no editable con el Certificado de No Deudor / Solvencia.
13. El Sistema registra en la bitácora de auditoría que se emitió un certificado de solvencia para el residente con su respectiva fecha y hora.
14. El Sistema despliega el documento en pantalla y habilita de forma automática las opciones de "Descargar" e "Imprimir".
15. El Sistema registra la acción en un historial de logs.


---

## Requisito: definirPagoDeAlicuotasDeFormaMensual
El sistema permitirá definir el pago de  alícuotas de forma mensual (como pago recurrente).

### Escenario Básico:**

1. El caso de uso inicia con el Administrador ingresando el número de meses de alícuota.
2. El Sistema valida el valor ingresado.
3. SI el valor ingresado es válido ENTONCES el Administrador ingresa el día del mes para el pago.
4. El Sistema valida el valor ingresado.
5. Si el valor ingresado es válido ENTONCES el Sistema calcula las fechas de pago de cada alícuota.
6. El caso de uso termina con el sistema definiendo las deudas por concepto de alícuota con su valor a pagar y fecha límite.

## Escenario(s) alternativo(s):**

### Escenario alternativo 1:**

1. El caso de uso inicia con el Administrador ingresando el número de meses de alícuota.
2. El Sistema valida el valor ingresado.
3. SI el valor ingresado NO es válido ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje “El número de meses ingresado no es válido, asegúrese que sea un número entero mayor a 0”.

### Escenario alternativo 2:**

1. El caso de uso inicia con el Administrador ingresando el número de meses de alícuota.
2. El Sistema valida el valor ingresado.
3. SI el valor ingresado es válido ENTONCES el Administrador ingresa el día del mes para el pago.
4. El Sistema valida el valor ingresado.
5. Si el valor ingresado NO es válido ENTONCES el caso de uso termina con el Sistema emitiendo el mensaje “El día ingresado no es válido, asegúrese que haya ingresado un número entero mayor que 0 y menor que 28”.


## Requisito: enviarRecordatorioDeDeudaPendiente

El sistema permitirá enviar un recordatorio al correo electrónico de un residente cuando tenga obligaciones de pago en estado "PENDIENTE" o "EN MORA".

## Datos del Caso de Uso

- **Actor:** Sistema Automático
- **Entradas:** obligación de pago en estado "PENDIENTE" o "EN MORA"
- **Salidas:** recordatorio de deuda pendiente y mensaje

## Escenario Básico

1. El caso de uso inicia cuando el Sistema identifica una obligación de pago en estado "PENDIENTE" o "EN MORA".
2. El Sistema verifica que la obligación de pago esté asociada a un residente.
3. SI la obligación de pago está asociada a un residente ENTONCES el Sistema verifica que el residente tenga un correo electrónico registrado.
4. SI el residente tiene un correo electrónico registrado ENTONCES el Sistema genera un recordatorio con el nombre completo del residente, el identificador del departamento y el saldo pendiente total.
5. El caso de uso termina con el Sistema enviando el recordatorio y emitiendo el mensaje "Recordatorio de deuda pendiente enviado correctamente".

## Escenarios Alternos

### Escenario Alterno 1

1. El caso de uso inicia cuando el Sistema identifica una obligación de pago en estado "PENDIENTE" o "EN MORA".
2. El Sistema verifica que la obligación de pago esté asociada a un residente.
3. SI la obligación de pago NO está asociada a un residente ENTONCES el Sistema emite el mensaje "No existe un residente asociado a la obligación de pago".
4. El caso de uso termina sin enviar el recordatorio de deuda pendiente.

### Escenario Alterno 2

1. El caso de uso inicia cuando el Sistema identifica una obligación de pago en estado "PENDIENTE" o "EN MORA".
2. El Sistema verifica que la obligación de pago esté asociada a un residente.
3. SI la obligación de pago está asociada a un residente ENTONCES el Sistema verifica que el residente tenga un correo electrónico registrado.
4. SI el residente NO tiene un correo electrónico registrado ENTONCES el Sistema emite el mensaje "No se puede enviar el recordatorio porque el residente no tiene un correo electrónico registrado".
5. El caso de uso termina sin enviar el recordatorio de deuda pendiente.