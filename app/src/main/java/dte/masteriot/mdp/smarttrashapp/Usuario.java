package dte.masteriot.mdp.smarttrashapp;

public class Usuario {
    private String Name;
    private String Password;

    public Usuario(String Name, String Password){
        this.Name = Name;
        this.Password = Password;
    }

    public String getName(){
        return Name;
    }
    public String getPassword(){
        return Password;
    }

}
