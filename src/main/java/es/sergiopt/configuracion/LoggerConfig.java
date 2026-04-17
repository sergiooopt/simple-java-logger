package es.sergiopt.configuracion;

import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Configuración de rutas de archivos de logger. Hay dos opciones: <br>
 * 
 * - Modificar {@code ruta}, donde todas las clases se escribiran aqui excepto las que esten definidas en {@code rutas}. <br>
 * - Modificar {@code rutas}, donde cada clase tiene su correspondiente fichero .log
 * 
 */
public class LoggerConfig {
    private String ruta;
    private Map<Class<?>, String> rutas;

    public LoggerConfig() {
        // Copilot dice que HashMap no necesita implementar Comparable
        rutas = new HashMap<>();
    }

    public String getRuta() {
        return ruta;
    }

    public Map<Class<?>, String> getRutas() {
        return rutas;
    }

    /**
     * 
     * Este método modifica la propiedad {@code ruta}.
     * 
     * @param ruta el nombre del fichero .log
     */
    public void establecerRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * 
     * Este método permite asociar por cada clase un .log distinto.
     * 
     * @param clazz la clase asociada a la ruta
     * @param ruta el nombre del fichero .log
     */
    public void añadirRutaPorClase(Class<?> clazz, String ruta) {
        if (rutas.containsKey(clazz)) rutas.replace(clazz, ruta);
        rutas.put(clazz, ruta);
    }
}
