package es.sergiopt.configuracion;

import java.util.Properties;
import java.io.IOException;

public class LoggerConfig {
    private static Properties properties;

    private LoggerConfig() {}

    // Bloque de código que se ejecuta junto al arranque de la aplicación
    static {
        try {
            properties = new Properties();
            properties.load(LoggerConfig.class.getClassLoader().getResourceAsStream("sjl-config.properties"));

        } catch (IOException e) {
            System.err.println("Error al leer el archivo {sjl-config.properties} :(");
            e.printStackTrace();
        }
    }

    public static String getRutaGeneral() {
        return properties.getProperty("logger.ruta.general");
    }

    public static String getRutaPorClase(Class<?> clazz) {
        return properties.getProperty("logger.ruta." + clazz.getName(), getRutaGeneral());
    }
}
