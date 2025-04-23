
package es.alejandrosalazargonzalez.minado.controller;


import es.alejandrosalazargonzalez.minado.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class RecuperarController extends AbstractController{

    @FXML
    public Text userEmailText;
    @FXML
    private TextField emailTextField;
    @FXML
    public Button enviarButton;
    @FXML
    private Text errorText;
    @FXML
    private Button regresarButton;

    /**
     * va a la pantalla del perfil
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
     * va a la pantalla de las opciones
     */
    @FXML
    public void recuperarToLoginOnClick(){
        cambiarPantalla(regresarButton, "app-init", "recuperar" );
    }
}
