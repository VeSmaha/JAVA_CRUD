package com.veronica.classes;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
@Entity

public class Usuario{
 
   
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
protected Long idUser;
protected String user;
protected String senhaCriptografada;


public Usuario(String user, String senha) {
    this.user = user;
    this.senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());
}

public Usuario() {
}

@Override
public String toString() {
    return "----Usuario---- \nidUser=" + idUser + "\n user=" + user;
}

public String getUser() {
    return user;
}

public String getSenhaCriptografada() {
    return senhaCriptografada;
}

public void setSenhaCriptografada(String senhaCriptografada) {
    this.senhaCriptografada = BCrypt.hashpw(senhaCriptografada, BCrypt.gensalt());
}

public void setUser(String user) {
    this.user = user;
}


public Long getIdUser() {
    return idUser;
}

}

