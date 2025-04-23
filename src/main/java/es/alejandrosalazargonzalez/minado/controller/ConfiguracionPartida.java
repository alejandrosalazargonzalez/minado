package es.alejandrosalazargonzalez.minado.controller;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class ConfiguracionPartida {
    public static int filas = 10;
    public static int columnas = 10;
    public static int minas = 10;

    /**
     * setea las filas columnas y minas del juego
     * @param f filas
     * @param c columnas
     * @param m minas
     */
    public static void set(int f, int c, int m) {
        filas = f;
        columnas = c;
        minas = m;
    }
}
