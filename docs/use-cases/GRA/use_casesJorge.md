## Requisito: solicitarPagoEnCuotas
El sistema permitirá a un residente solicitar el pago en cuotas.
Este escenario describe el camino ideal donde el residente realiza 
la solicitud de manera correcta y cumple con todos los parámetros del sistema.

**Escenario Básico:**

1. El residente se encuentra autenticado en el sistema.
2. El residente posee al menos una obligación de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El residente ingresa al módulo de "Mis Obligaciones" y selecciona una deuda específica elegible para financiamiento (por ejemplo, un cargo extraordinario).
4. El residente hace clic en la opción "Solicitar Pago en Cuotas".
5. El sistema despliega un formulario solicitando la información del financiamiento: Número de cuotas deseadas (entre 2 y 12 meses)
6. El residente selecciona el número de cuotas (entero mayor a uno y menor o igual a 12 ej. 6) y confirma la solicitud.
7. El sistema calcula automáticamente el valor individual de cada cuota dividiendo el valor total de la deuda para el número de cuotas ingresadas, redondeando a dos decimales.
8. El sistema muestra una vista previa del plan de pagos detallando: ID del plan, ID del residente, obligación asociada, valor total de la deuda, número de cuotas, valor de cada cuota, fecha de inicio y fecha de finalización.
9. El residente revisa y acepta los términos del diferimiento.
10. El sistema registra el plan de pagos con estado "SOLICITADO", genera una notificación para el Administrador, y muestra un mensaje de éxito en pantalla.
11. El plan de pagos queda registrado en la base de datos.

**Escenario(s) alternativo(s):**

**Escenario alternativo 1**
1. El residente se encuentra autenticado en el sistema.
2. El residente no posee obligaciones de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El residente intenta solicitar un diferimiento sobre una obligación cuyo estado es "PAGADO".
4. El sistema verifica el estado de la obligación en la base de datos.
5. El sistema al detectar que el estado es "PAGADO", oculta o inhabilita por completo el botón "Solicitar Pago en Cuotas" para esa transacción específica, impidiendo el inicio del flujo.

**Escenario alternativo 2**
1. El residente se encuentra autenticado en el sistema.
2. El residente posee al menos una obligación de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El residente ingresa al módulo de "Mis Obligaciones" y selecciona una deuda específica elegible para financiamiento (por ejemplo, un cargo extraordinario).
4. El residente intenta solicitar un plan de pagos para una obligación que ya se encuentra asociada a un plan previo en estado "SOLICITADO" o "APROBADO".
5. El sistema, al procesar la solicitud, realiza una consulta cruzada en la tabla de planes de pago.
6. El sistema interrumpe el proceso y despliega una alerta en pantalla: "Acción no permitida: Esta obligación ya cuenta con un plan de pagos activo o en proceso de aprobación".
7. El sistema redirecciona al usuario al módulo de "Mis Obligaciones".

**Escenario alternativo 3**
1. El residente se encuentra autenticado en el sistema.
2. El residente posee al menos una obligación de pago o cargo extraordinario en estado "PENDIENTE" o "EN MORA".
3. El residente ingresa al módulo de "Mis Obligaciones" y selecciona una deuda específica elegible para financiamiento (por ejemplo, un cargo extraordinario).
4. El residente hace clic en la opción "Solicitar Pago en Cuotas".
5. El sistema despliega un formulario solicitando la información del financiamiento: Número de cuotas deseadas (entre 2 y 12 meses)
6. El residente selecciona el número de cuotas (entero mayor a uno y menor o igual a 12 ej. 6) y confirma la solicitud.
7. El sistema calcula automáticamente el valor individual de cada cuota dividiendo el valor total de la deuda para el número de cuotas ingresadas, redondeando a dos decimales.
8. El sistema muestra una vista previa del plan de pagos detallando: ID del plan, ID del residente, obligación asociada, valor total de la deuda, número de cuotas, valor de cada cuota, fecha de inicio y fecha de finalización.
9. el residente decide no aceptar la simulación de las cuotas o el valor individual calculado por el sistema.
10. El residente hace clic en el botón "Cancelar Solicitud".
11. Sistema: Revierte los cálculos temporales en memoria, no realiza ningún registro en la base de datos y regresa al usuario al módulo de "Mis Obligaciones".

## Requisito: eliminarFormaDePagoRecurrente
### El sistema permitira eliminar la forma de pago recurrente.

**Escenario Básico:**

1. Primer paso
2. Segundo paso
3. Primer paso
4. g
5. g
6. g
7. gg
8. g

**Escenario(s) alternativo(s):**

**Escenario alternativo 1**
1. Primer paso
2. Segundo paso
3. 1. Primer paso
4. g
5. g
6. g
7. gg
8. g

## Requisito: eliminarDeuda
### El sistema permitirá eliminar una deuda

**Escenario Básico:**

1. Primer paso
2. Segundo paso
3. 1. Primer paso
4. g
5. g
6. g
7. gg
8. g

**Escenario(s) alternativo(s):**

**Escenario alternativo 1**
1. Primer paso
2. Segundo paso
3. 1. Primer paso
4. g
5. g
6. g
7. gg
8. g

## Requisito: generarCertificadoDeNoDeudor
### El sistema permitirá generar un certificado de no deudor.

**Escenario Básico:**

1. Primer paso
2. Segundo paso
3. 1. Primer paso
4. g
5. g
6. g
7. gg
8. g

**Escenario(s) alternativo(s):**

**Escenario alternativo 1**
1. Primer paso
2. Segundo paso
3. 1. Primer paso
4. g
5. g
6. g
7. gg
8. g


Este texto es **negrita** para resaltar.
Este texto es *itálica* o cursiva.

Lista de tareas:
* Elemento A
* Elemento B

Pasos a seguir:
1. Primer paso
2. Segundo paso


