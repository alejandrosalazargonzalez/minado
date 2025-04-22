package es.alejandrosalazargonzalez.minado.controller;

import es.alejandrosalazargonzalez.minado.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class InicioController extends AbstractController{

    @FXML
    public Text userText;
    @FXML
    private TextField userTextField;
    @FXML
    public ComboBox dificultadBox;
    @FXML
    private Text minasText;
    @FXML
    private TextField nivelTextField;
    @FXML
    private Button regresarButton;
    @FXML
    public Button jugarButton;
    
    @FXML private TextField filasField;
    @FXML private TextField columnasField;
    @FXML private TextField minasField;

    
    /**
     * cambia las columnas a las personalizadas
     */
    @FXML
    private void onDificultadChange() {
        String seleccion = (String) dificultadBox.getValue();
        boolean personalizada = "Personalizada".equals(seleccion);
        
        filasField.setDisable(!personalizada);
        columnasField.setDisable(!personalizada);
        minasField.setDisable(!personalizada);
    }

    @FXML
    private void inicioToPartidaOnClick(){
        String seleccion = (String) dificultadBox.getValue();

        int filas = 0, columnas = 0, minas = 0;
    
        switch (seleccion) {
            case "Fácil":
                filas = 8; columnas = 8; minas = 10;
                break;
            case "Medio":
                filas = 12; columnas = 12; minas = 20;
                break;
            case "Difícil":
                filas = 16; columnas = 16; minas = 40;
                break;
            case "Personalizada":
                try {
                    filas = Integer.parseInt(filasField.getText());
                    columnas = Integer.parseInt(columnasField.getText());
                    minas = Integer.parseInt(minasField.getText());
                    
                    if (filas < 1 || columnas < 1 || minas < 1 || minas >= filas * columnas) {
                        return;
                    }
                } catch (NumberFormatException e) {
                    return;
                }
                break;
            default:
                return;
        }
    
        ConfiguracionPartida.set(filas, columnas, minas);
        cambiarPantalla(jugarButton, "juego", "app-init");
        cambiarPantalla(jugarButton, "juego", "app-init");
    }


    @FXML
    private void inicioToLoginOnClick(){
        cambiarPantalla(regresarButton, "app-init", "app-init");
    }

}
