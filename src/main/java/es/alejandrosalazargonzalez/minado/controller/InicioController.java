package es.alejandrosalazargonzalez.minado.controller;

import es.alejandrosalazargonzalez.minado.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class InicioController extends AbstractController{
    
    @FXML private Button editarButton;
    @FXML private Button jugarButton;
    @FXML private Button regresarButton;

    /**
     * va a la pantalla registrar
     */
    @FXML
    public void inicioToRegistrarOnClick(){
        cambiarPantalla(editarButton, "registrar");
    }

    /**
     * va a la pantalla de partida
     */
    @FXML
    public void inicioToPartidaOnClick(){
        cambiarPantalla(jugarButton, "juego");
    }

    /**
     * va a la pantalla de login
     */
    @FXML
    public void inicioToLoginOnClick(){
        cambiarPantalla(regresarButton,"app-init");
    }
}
