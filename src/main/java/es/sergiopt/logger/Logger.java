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

    private LoggerConfig config;
    private Class<?> clazz;

    public Logger(LoggerConfig config, Class<?> clazz) {
        this.config = config;
        this.clazz = clazz;
    }

    private void escribir(String nivel, String mensaje, Exception exception) {
        // Obtener archivo de escritura
        String archivo = "";
        if (config.getRutas().containsKey(clazz)) {
            archivo = config.getRutas().get(clazz).toString();
        } else if (config.getRuta() != null) {
            archivo = config.getRuta();
        } else {
            // Caso donde esta clase no tiene .log asociado
            System.out.println("{" + clazz.getName() + "} no tiene .log asociado :(");
            return;
        }

        // Procesar error
        String mensajeCompleto = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + " " + nivel + " - " + mensaje + "\n";
        if (exception != null) {
            // Obtener mensaje de error
            StringWriter writer = new StringWriter();
            PrintWriter mensajeExcepcion = new PrintWriter(writer);
            exception.printStackTrace(mensajeExcepcion);

            // Completar mensaje
            mensajeCompleto += writer.toString() + "\n";
        }

        try {
            // Realizar escritura
            Files.writeString(Paths.get(archivo), mensajeCompleto, StandardOpenOption.CREATE, StandardOpenOption.APPEND); // crear + añadir por final

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
