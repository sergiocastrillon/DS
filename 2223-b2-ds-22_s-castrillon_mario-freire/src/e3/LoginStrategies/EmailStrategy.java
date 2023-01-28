package e3.LoginStrategies;

import e3.DataBase;
import e3.LoginStrategy;
import e3.MfaStrategy;
import e3.User;

import java.util.HashMap;

public class EmailStrategy implements LoginStrategy {

    private final DataBase users; // Se permite añadir usuarios porque se guarda como "puntero" a database
    // asi que las modificaciones desde fuera se añaden directamente a la clase
    public EmailStrategy(DataBase users){
        this.users = users;
    }

    public boolean validateId(String id) {
        int arrpos = id.indexOf('@');
        // @ debe existir y debe haber algún carácter delante de él
        if(arrpos != -1 && id.lastIndexOf('@') == arrpos && arrpos > 0){
            // Punto separando dominio después del arroba y el correo no puede acabar en punto
            if(id.lastIndexOf('.') > arrpos && id.lastIndexOf('.') <id.length() - 1){
                for(int i = 0; i < id.length(); i++){
                    char e = id.charAt(i);
                    // Comprobamos que todos los carácteres o sean numeros o letras
                    if((e >= 48 && e <= 57) || (e >= 65 && e<= 90) || (e>=97 && e<=122) || e == '.' || e == '@'){
                        continue;
                    }else return false;
                }
                return true; // Si ha pasado todos los checks devolvemos true
            }
        }
        return false;
    }

    @Override
    public boolean authenticatePassword(String id, String password) {
        HashMap<String,User> m;
        m = users.getEmailMap();
        if(m.containsKey(id)){
            return m.get(id).checkPassword(password);
        }
        return false;
    }

    @Override
    public MfaStrategy getMFAMethod(String id) {
        HashMap<String,User> m;
        m = users.getEmailMap();
        return m.get(id).getMFAMethod();
    }

    @Override
    public boolean setMFAMethod(String id, MfaStrategy m) {
        HashMap<String,User> map;
        map = users.getEmailMap();
        if(map.containsKey(id)){
            User u = map.get(id);
            users.setMFAMethod(u.getEmail(),u.getPhone(),u.getUsername(),m);
            return true;
        }else return false;
    }
}
