package es.sergiopt.configuracion;

import es.sergiopt.logger.Logger;

public class LoggerBuilder {
    
    // Esta clase no tiene mucho sentido ahora que LoggerConfig se carga con la aplicación
    // Decidí que se siga usando por asemejarse más a librerias profesionales de logger

    private LoggerBuilder(){}
    
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }
}
