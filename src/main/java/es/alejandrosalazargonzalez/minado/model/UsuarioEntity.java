package es.alejandrosalazargonzalez.minado.model;

import java.util.Objects;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class UsuarioEntity {

    private String usuario;
    private String email;
    private String contrasenia;
    private int puntos;
    private int idNivel;

    /**
     * Constructor vacio
     */
    public UsuarioEntity() {
    }

    /**
     * Constructor completo
     *
     * @param usuario     del usuario
     * @param email       del usuario
     * @param contrasenia del usuario
     * @param puntos      del usuario
     * @param nivel       del usuario
     * @throws Exception
     */
    public UsuarioEntity(String usuario, String email, String contrasenia) throws ExceptionInInitializerError{
        if (!email.contains("@") || !email.contains(".") ) {
            throw new ExceptionInInitializerError("El email debe tener un formato correcto");
        }
        this.usuario = usuario;
        this.email = email;
        this.contrasenia = contrasenia;
        this.puntos = 0;
        this.idNivel = 1;
    }

    //Getters y Setters
    public String getUsuario(){
        return this.usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) throws ExceptionInInitializerError {
        if (!email.contains("@") || !email.contains(".com") ) {
            throw new ExceptionInInitializerError("El email debe tener un formato correcto");
        }
        this.email = email;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getNivel(){
        return this.idNivel;
    }

    public void setNivel(int nivel){
        this.idNivel = nivel;
    }

    public int getPuntos(){
        return this.puntos;
    }

    public void setPuntos(int puntos){
        this.puntos = puntos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity usuarioModel = (UsuarioEntity) o;
        return Objects.equals(email, usuarioModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return getUsuario() +
                ", " + getEmail() +
                ", " + getPuntos() +
                ", " + getNivel();
    }

}
