package edu.usta.configuracion;

import java.util.HashMap;
import java.util.Map;

public class Dominio {

    public final static Map<Integer, String> ARREGLO_CATEGORIAS = new HashMap<Integer, String>() {

        {
            put(0, "Seleccione categoría...");
            put(1, "A1");
            put(2, "A2");
            put(3, "B");
            put(4, "C");
            put(5, "D");

        }
    };

    public final static Map<Short, String> ARREGLO_TIPOS = new HashMap<Short, String>() {
        {
            put((short) 1, "Investigación");
            put((short) 2, "Revisión");
            put((short) 3, "Corto");
            put((short) 4, "Reporte de caso");
        }
    };
}
