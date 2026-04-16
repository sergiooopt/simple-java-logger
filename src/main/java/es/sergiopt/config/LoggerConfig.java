package es.sergiopt.config;

import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Configuración de ruta a archivos de logger. Hay dos opciones: <br>
 * 
 * - Modificar {@code rutaDefinitiva} donde todo lo que se escriba para .log va a ir hay (En caso. <br>
 * de estar modificada esta propiedad, todas las clases se escribiran aqui excepto las que esten <br>
 * definidas en {@code rutas}). <br>
 * 
 * - Modificar {@code rutas}, donde cada clase tiene su correspondiente fichero .log <br>
 * 
 * {@code rutaDefinitiva} tiene preferencia sobre {@code rutas}, si la definitiva está configurada <br>
 * será esta la que se vaya a usar.
 * 
 */
public class LoggerConfig {
    private  String rutaDefinitiva;
    private  Map<Class<?>, String> rutas;

    public LoggerConfig() {
        // Copilot dice que HashMap no necesita implementar Comparable
        rutas = new HashMap<>();
    }

    public  String getRutaDefinitiva() {
        return rutaDefinitiva;
    }

    public  void setRutaDefinitiva(String rutaDefinitiva) {
        this.rutaDefinitiva = rutaDefinitiva;
    }

    public  Map<Class<?>, String> getRutas() {
        return rutas;
    }

    /**
     * 
     * Este método permite asociar por cada clase un .log distinto, ideal en situaciones <br>
     * donde se quieren separar los .log de la aplicación.
     * 
     * @param clazz la clase asociada a la ruta
     * @param ruta el nombre del fichero .log
     */
    public void añadirRuta(Class<?> clazz, String ruta) {
        if (rutas.containsKey(clazz)) rutas.replace(clazz, ruta);
        rutas.put(clazz, ruta);
    }
}
