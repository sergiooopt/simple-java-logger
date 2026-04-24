# Simple Java Logger

Un logger simple y configurable para aplicaciones Java, que permite escribir mensajes de log (`info`, `warning`, `error`) en archivos específicos por clase o en un archivo global.

---

### Configuración

En `src/main/resources` dentro del proyecto creamos `sjl-config.properties`.

La clase `LoggerConfig` va a leer dos parámetros en este archivo:

* Ruta general:

```java
logger.ruta.general=<ruta a log>
```

* Ruta específica por clase:

```java
logger.ruta.<clase java>=<ruta a log>
```

---

### Obtención del Logger

La configuración se carga automáticamente mediante el archivo `sjl-config.properties`.

Para usar un logger se puede instanciar con `LoggerBuilder.getLogger()`.

---

### Logging

El logger escribe mensajes en archivo/archivos configurados en los niveles:

* `[INFO]`
* `[WARNING]`
* `[ERROR]`

---

## Ejemplo de Uso

```java
public class Main {
  
  private static Logger logger = LoggerBuilder.getLogger();

  public static void main(String[] args) {
    logger.info("Hola Mundo :)");
  }
}
```

---

## Estructura del Proyecto

* **LoggerConfig**
  Lee el archivo `sjl-config.properties` y obtiene las rutas.

* **LoggerBuilder**
  Crea las instancias de `Logger`.

* **Logger**
  Escribe los mensajes en los archivos configurados.

---

## Notas

Este logger esta creado a modo de proyecto personal.
