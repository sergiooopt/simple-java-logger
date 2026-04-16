package es.sergiopt.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import es.sergiopt.config.LoggerConfig;

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

    private void escribir(String nivel, String mensaje) {
        // Obtener archivo de escritura
        String archivo = "";
        if (config.getRutas().containsKey(clazz)) archivo = config.getRutas().get(clazz).toString();
        else if (config.getRutaDefinitiva() != null) archivo = config.getRutaDefinitiva();
        else {
            // Caso donde esta clase no tiene .log asociado
            System.out.println("{" + clazz.getName() + "} no tiene .log asociado :(");
            return;
        }                    

        try {
            // Realizar escritura
            String mensajeCompleto = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + " " + nivel + " " + mensaje + "\n";
            Files.writeString(Paths.get(archivo), mensajeCompleto, StandardOpenOption.CREATE, StandardOpenOption.APPEND); // crear + añadir por final
        
        } catch (IOException e) {
            System.err.println("Error de E/S en archivo {"  + archivo + "} :(");
            e.printStackTrace();
        }
    }

    public void info(String mensaje) {
        escribir(INFO, mensaje);
    }

    public void warning(String mensaje) {
        escribir(WARNING, mensaje);
    }

    public void error(String mensaje) {
        escribir(ERROR, mensaje);
    }
}
