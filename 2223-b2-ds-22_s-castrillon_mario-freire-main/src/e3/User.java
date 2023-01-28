package e3;

public class User {
    private final String username;

    private final String email;

    private final String phone;
    private String pass;
    private MfaStrategy MFAMethod;

    public User(String email,String username,String phone, String pass, MfaStrategy MFAMethod){
        // Un usuario puede tener distintos ids aunque no tiene porque tener obligatoriamente los tres metodos
        // solo está obligado a tener uno.
        if((username == null && email == null && phone == null) || pass == null || MFAMethod == null)
            throw new IllegalArgumentException();
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.MFAMethod = MFAMethod;

    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public MfaStrategy getMFAMethod(){
        return MFAMethod;
    }

    public void changeMFAMethod(MfaStrategy MFAMethod){
        this.MFAMethod = MFAMethod;
    }

    // Devuelve true si la contraseña coincide
    public boolean checkPassword(String password){
        return password.equals(pass);
    }


}
