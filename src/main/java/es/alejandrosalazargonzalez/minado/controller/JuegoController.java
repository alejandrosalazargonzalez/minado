package es.alejandrosalazargonzalez.minado.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import java.util.Random;

import es.alejandrosalazargonzalez.minado.controller.abstractas.AbstractController;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class JuegoController extends AbstractController {

    private static final int FILAS = ConfiguracionPartida.filas;
    private static final int COLUMNAS = ConfiguracionPartida.columnas;
    private static final int MINAS = ConfiguracionPartida.minas;

    @FXML
    private GridPane grid;

    @FXML
    private Button nuevoJuegoBtn;

    @FXML
    private Button atrasButon;

    @FXML
    private Label mensajeLabel;

    private int[][] tablero;
    private boolean[][] descubiertas;
    private int celdasDescubiertas = 0;

    @FXML
    void initialize() {
        crearTablero(FILAS, COLUMNAS); 
    }

    /**
     * inicia una nueca partida con el tablero con los parametros marcados
     */
    @FXML
    void nuevoJuego() {
        mensajeLabel.setText("Nuevo juego iniciado!");
        crearTablero(FILAS, COLUMNAS);
    }

    /**
     * cambia a la panalla de configuracion
     */
    @FXML
    protected void onAtrasClick(){
        cambiarPantalla(atrasButon, "inicio", "play");
    }

    /**
     * metodo para crear el tablero con las filas y comlumnas marcadas
     * @param filas del tablero
     * @param columnas del tablero
     */
    private void crearTablero(int filas, int columnas) {
        grid.getChildren().clear(); 
        tablero = new int[filas][columnas];
        descubiertas = new boolean[filas][columnas];
        celdasDescubiertas = 0;

        colocarMinas(MINAS);
        contarMinasAlrededor();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Button btn = new Button();
                btn.setPrefSize(30, 30);
                final int fila = i;
                final int columna = j;
                btn.setOnAction(e -> manejarBoton(btn, fila, columna));
                grid.add(btn, j, i);
            }
        }
    }

    /**
     * Coloca las minas en el tablero
     * @param cantidadMinas cantidad de minas a colocar
     */
    private void colocarMinas(int cantidadMinas) {
        Random random = new Random();
        for (int i = 0; i < cantidadMinas; i++) {
            int fila;
            int col;
            do {
                fila = random.nextInt(FILAS);
                col = random.nextInt(COLUMNAS);
            } while (tablero[fila][col] == -1);
            tablero[fila][col] = -1;
        }
    }

    /**
     * cuenta las minas del tablero
     */
    private void contarMinasAlrededor() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == -1) continue;
                tablero[i][j] = contarMinasEnCelda(i, j);
            }
        }
    }

    /**
     * metodo para contar las minas alrededor de la celda
     * @param fila de la celda
     * @param columna de la celda
     * @return int
     */
    private int contarMinasEnCelda(int fila, int columna) {
        int contador = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                int nuevaFila = fila + x;
                int nuevaCol = columna + y;

                if (nuevaFila >= 0 && nuevaFila < tablero.length &&
                    nuevaCol >= 0 && nuevaCol < tablero[0].length &&
                    tablero[nuevaFila][nuevaCol] == -1) {
                    contador++;
                }
            }
        }
        return contador;
    }


    private void manejarBoton(Button btn, int fila, int columna) {
        if (descubiertas[fila][columna]) {
            return;
        }

        btn.setDisable(true);
        descubiertas[fila][columna] = true;

        if (tablero[fila][columna] == -1) {
            btn.setText("X");
            mensajeLabel.setText("¡Perdiste!");
            revelarTodo();
        } else if (tablero[fila][columna] == 0) {
            descubrirZonaVacia(fila, columna);
        }else {
            btn.setText(String.valueOf(tablero[fila][columna])); 
            celdasDescubiertas++;

            if (celdasDescubiertas == (FILAS * COLUMNAS - MINAS)) {
                mensajeLabel.setText("¡Ganaste!");
                revelarTodo();
            }
        }
    }

    /**
     * metodo para revelar todas las celdas del tablero
     */
    private void revelarTodo() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                Button btn = (Button) getNodeFromGridPane(i, j);
                btn.setDisable(true);
                if (tablero[i][j] == -1) {
                    btn.setText("X");
                } else {
                    btn.setText(String.valueOf(tablero[i][j]));
                }
            }
        }
    }

    /**
     * metodo para descubrir la zona vacia
     * @param fila de la celda
     * @param columna de la celda
     */
    private void descubrirCelda(int fila, int columna) {
        if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) return;
        if (descubiertas[fila][columna]) return;
    
        Button btn = (Button) getNodeFromGridPane(fila, columna);
        btn.setDisable(true);
        descubiertas[fila][columna] = true;
    
        if (tablero[fila][columna] == -1) {
            btn.setText("X");
        } else if (tablero[fila][columna] == 0) {
            btn.setText("");
        } else {
            btn.setText(String.valueOf(tablero[fila][columna]));
        }
    
        celdasDescubiertas++;
    }
    
    /**
     * metodo para descubrir la zona vacia
     * @param fila de la celda
     * @param columna de la celda
     */
    private void descubrirZonaVacia(int fila, int columna) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int nuevaFila = fila + x;
                int nuevaCol = columna + y;
    
                if (nuevaFila >= 0 && nuevaFila < FILAS &&
                    nuevaCol >= 0 && nuevaCol < COLUMNAS &&
                    !descubiertas[nuevaFila][nuevaCol]) {
    
                    descubrirCelda(nuevaFila, nuevaCol);
    
                    if (tablero[nuevaFila][nuevaCol] == 0) {
                        descubrirZonaVacia(nuevaFila, nuevaCol);
                    }
                }
            }
        }
    }
    

    /**
     * metodo para obtener el nodo del gridpane
     * @param fila de la celda
     * @param columna de la celda
     * @return Node
     */
    private Node getNodeFromGridPane(int fila, int columna) {
        for (Node node : grid.getChildren()) {
            if (GridPane.getRowIndex(node) == fila && GridPane.getColumnIndex(node) == columna) {
                return node;
            }
        }
        return null;
    }
}
