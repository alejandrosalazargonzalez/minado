package es.alejandrosalazargonzalez.minado.controller;

import es.alejandrosalazargonzalez.minado.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.minado.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class LoginController extends AbstractController {

    @FXML private Button aceptarButton;
    @FXML private Button registrarButton;
    @FXML private Button listarUsuarios;
    @FXML private Button recuperarButton;
    @FXML private TextField userEmailTextField;
    @FXML private PasswordField passwordField;
    @FXML private Text errorText;

    /**
     * cambia a la pantalla de inicio
     */
    @FXML
    public void loginAceptarOnClick() {
        if (!comprobarTextField(userEmailTextField)) {
            errorText.setText("Usuario no puede estar vacio");
            return;
        }
        if (!comprobarTextField(passwordField)) {
            errorText.setText("Contraseña no puede estar vacio");
            return;
        }
        UsuarioEntity usuario = getUsuarioServiceModel().obtenerUsuarioPorUsuario(userEmailTextField.getText());
        if (usuario == null) {
            errorText.setText("el usuario no existe");
            return;
        }
        if (!(usuario.getContrasenia().equals(passwordField.getText()))) {
            errorText.setText("error en usuario o contraseña");
            return;
        }
        setUsuarioActual(usuario);
        cambiarPantalla(aceptarButton, "inicio");
    }

    /**
     * cambia a la pantalla de registrar
     */
    @FXML
    protected void loginToRegistrarOnClick()  {
        cambiarPantalla(registrarButton, "registrar");
    }

    @FXML
    public void loginToListarOnClick(){
        cambiarPantalla(listarUsuarios, "listarUsuarios");
    }
    /**
     * cambia a la pantalla de recuperar
     */
    @FXML
    protected void loginToRecuperarOnClick() {
        cambiarPantalla(recuperarButton, "recuperar");
    }

    
}