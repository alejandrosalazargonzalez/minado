package es.alejandrosalazargonzalez.minado.controller;

import es.alejandrosalazargonzalez.minado.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author: alejandrosalazargonzalez
 * @version: 1.0.0
 */
public class RecuperarController extends AbstractController {

    @FXML
    private Button enviarButton;
    @FXML
    private Button regresarButton;
    @FXML
    private TextField emailTextField;
    @FXML
    private Text errorText;

    /**
     * busca al usuario y muestra su contraseña si existe el usuario
     */
    @FXML
    public void enviarOnClick() {
        if (emailTextField.getText() == null || emailTextField.getText().isEmpty()) {
            errorText.setText("no puedes dejar el campo vacio");
            return;
        }
        if (getUsuarioServiceModel().obtenerUsuarioPorEmail(emailTextField.getText()) == null) {
            errorText.setText("no hay usuarios registrados con ese email");
            return;
        }
        errorText.setText("Su contraseña es: "
                + getUsuarioServiceModel().obtenerUsuarioPorEmail(emailTextField.getText()).getContrasenia());
    }

    /**
     * Cambia a la pantalla de log in
     */
    @FXML
    public void recuperarToLoginOnClick() {
        cambiarPantalla(regresarButton, "app-init");
    }
}