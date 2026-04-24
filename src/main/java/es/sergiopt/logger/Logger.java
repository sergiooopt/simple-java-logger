package es.sergiopt.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import es.sergiopt.configuracion.LoggerConfig;

/**
 * 
 * Clase que lleva a cabo la escritura a los .log declarados en {@code LoggerConfig}. <br>
 * Se recomienda instanciar desde {@code LoggerBuilder} para evitar problemas. <br>
 * 
 */
public class Logger {
    private static final String INFO = "[INFO]";
    private static final String WARNING = "[WARNING]";
    private static final String ERROR = "[ERROR]";

    private Class<?> clazz;

    public Logger(Class<?> clazz) {this.clazz = clazz;}

    private void escribir(String nivel, String mensaje, Exception exception) {
        if (LoggerConfig.getRutaPorClase(clazz) == null) {
            System.out.println("La configuración no devuelve ninguna ruta :( ¿arhivo src/main/resources/sjl-config.properties sin configurar?");
            return;  
        }

        String archivo = LoggerConfig.getRutaPorClase(clazz);

        // Procesar mensaje
        StringBuilder sbMensaje = new StringBuilder();
        sbMensaje.append(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)).append(" ").append(clazz.getName()).append(" ").append(nivel).append(" - ").append(mensaje).append("\n");
        
        // Obtener mensaje de error
        if (exception != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            sbMensaje.append(sw).append("\n");
        }

        try {
            // Realizar escritura
            Files.writeString(Paths.get(archivo), sbMensaje, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.err.println("Error de E/S en archivo {" + archivo + "} :(");
            e.printStackTrace();
        }
    }

    public void info(String mensaje) {
        escribir(INFO, mensaje, null);
    }

    public void warning(String mensaje) {
        escribir(WARNING, mensaje, null);
    }

    public void error(String mensaje, Exception e) {
        escribir(ERROR, mensaje, e);
    }
}
