package es.alejandrosalazargonzalez.minado.controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
public class Registrar extends AbstractController{
    
    @FXML private Button regresarButton;
    @FXML private TextField userTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordField2;
    @FXML private TextField emailTextField1;
    @FXML private TextField emailTextField11;
    @FXML private Text errorText;
    /**
     * dependiendo del valor de regresar va a una pantalla o a otra
     */
    @FXML
    public void regresarOnClick(){
        cambiarPantalla(regresarButton,"app-init");
    }

    @FXML
    public void guardarUsuarioOnClick(){
        if (!comprobarRegistrar()) {
            return;
        }
        UsuarioEntity nuevoUsuario = new UsuarioEntity(userTextField.getText(), emailTextField1.getText(),passwordField.getText());
        ArrayList<UsuarioEntity> usuarioEntityList;
        try {
            usuarioEntityList = getUsuarioServiceModel().obtenerUsarios();
        if (usuarioEntityList.contains(nuevoUsuario)) {
            errorText.setText("Ya hay una cuenta registrada con ese correo");
            return;
        }
        if (getUsuarioServiceModel().obtenerUsuarioPorUsuario(userTextField.getText()) != null) {
            errorText.setText("Ya hay una cuenta registrada con ese usuario");
            return;
        }
            getUsuarioServiceModel().addUsuario(nuevoUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            errorText.setText("error no controlado");
        }
        regresarOnClick();
    }

    /**
     * comprueba que los campos sean validos
     * @return true/false
     */
    private boolean comprobarRegistrar(){
        if(!comprobarTextField(userTextField)){
            errorText.setText("Usuario no puede estar vacio");
            return false;
        }
        if(!comprobarTextField(passwordField)){
            errorText.setText("Contrasenia no puede estar vacio");
            return false;
        }
        if(!comprobarTextField(passwordField2)){
            errorText.setText("Repetir contrasenia no puede estar vacio");
            return false;
        }
        if (!passwordField.getText().equals(passwordField2.getText()) ) {
            errorText.setText("La contrasenia repetida debe ser igual");
            return false;
        }
        if(!comprobarTextField(emailTextField1)){
            errorText.setText("El correo no puede estar vacio");
            return false;
        }
        if(!comprobarTextField(emailTextField11)) {
            errorText.setText("Correo repetir de los valores puede estar vacio");
            return false;
        }
        if (!emailTextField1.getText().equals(emailTextField11.getText()) ) {
            errorText.setText("Los correos deben ser iguales");
            return false;
        }
        return true;
    }
}
