package es.sergiopt.configuracion;

import es.sergiopt.logger.Logger;

public class LoggerBuilder {
    private static LoggerConfig config;

    /**
     * 
     * Cargar configuración necesaria para la escritura de .log
     * 
     * @param config configuracion de rutas de ficheros .log
     */
    public static void cargarConfiguracion(LoggerConfig config) {
        LoggerBuilder.config = config;
    }

    /**
     * 
     * Se debe cargar una configuración antes de obtener logger.
     * 
     * @param clazz la clase desde donde se está llamando este método
     * @return logger
     */
    public static Logger getLogger(Class<?> clazz) {
        if (config == null) {
            System.out.println("Se debe cargar LoggerConfig antes de obtener el Logger :(");
            return null;
        }

        return new Logger(config, clazz);
    }
}
