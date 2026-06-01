# Módulo Gestión de Pagos

# Caso de uso: registrarPagoEnEfectivo

## Datos del Caso de Uso

- **Entradas:** idDeuda
- **Salidas:** mensaje

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando el idDeuda.
2. El Sistema verifica el idDeuda.
3. SI existe una Deuda con el idDeuda ENTONCES el Sistema verifica el estado de la Deuda.
4. SI el estado de la Deuda es ‘PENDIENTE’ ENTONCES el Administrador cambia el estado de la Deuda a ‘PAGADA’.
5. El Sistema muestra el mensaje “Pago en efectivo registrado exitosamente”.
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

# Caso de uso: registrarPagoPorTransferencia


## Datos del Caso de Uso

- **Entradas:** idDeuda
- **Salidas:** mensaje y comprobante

## Escenario Básico

1. El caso de uso inicia con el Administrador ingresando el idDeuda.
2. El Sistema verifica el idDeuda.
3. SI existe una Deuda con el idDeuda ENTONCES el Sistema verifica el estado de la Deuda.
4. SI el estado de la Deuda es ‘PENDIENTE’ ENTONCES el Sistema muestra el comprobante de pago.
5. El Administrador cambia el estado de la Deuda a ‘PAGADA’.
6. El caso de uso finaliza con el cambio de estado de la Deuda a 'PAGADA' y mostrando el mensaje “Pago por transferencia registrado exitosamente”.

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