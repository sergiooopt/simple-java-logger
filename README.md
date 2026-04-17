# Simple Java Logger

Un logger simple y configurable para aplicaciones Java, que permite escribir mensajes de log (`info`, `warning`, `error`) en archivos específicos por clase o en un archivo global.

---

### Configuración

Crea una instancia de `LoggerConfig` para definir las rutas de los archivos de log. Puedes:

* Establecer una ruta global para todos los logs:

```java
config.establecerRuta("/home/xxx/log.log");
```

* Asignar rutas específicas por clase:

```java
config.añadirRutaPorClase(MiClase.class, "/home/xxx/log.log");
```

---

### Obtención del Logger

Usa `LoggerBuilder` para cargar la configuración y obtener un logger para una clase específica. Esto asegura que la configuración esté cargada antes de usar el logger.

---

### Logging

El logger escribe mensajes en el archivo correspondiente en los niveles:

* `[INFO]`
* `[WARNING]`
* `[ERROR]`

---

## Ejemplo de Uso

```java
import es.sergiopt.config.LoggerConfig;
import es.sergiopt.config.LoggerBuilder;
import es.sergiopt.impl.Logger;

// Configuración
LoggerConfig config = new LoggerConfig();
config.establecerRuta("/home/xxx/log.log");

// Obtener logger
LoggerBuilder.cargarConfiguracion(config);
Logger logger = LoggerBuilder.getLogger(MiClase.class);

// Usar
logger.info("Mensaje de info :)");
```

---

## Estructura del Proyecto

* **LoggerConfig**
  Gestiona las rutas de los archivos de log.

* **LoggerBuilder**
  Crea las instancias de `Logger` con la configuración correspondiente.

* **Logger**
  Encargada de escribir los mensajes en los archivos.

---

## Notas

Este logger esta creado a modo de mini proyecto personal. Tengo intenciones de volverlo más complejo
según vaya pasando el tiempo.
