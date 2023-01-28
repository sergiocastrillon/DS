package e3.LoginStrategies;

import e3.DataBase;
import e3.LoginStrategy;
import e3.MfaStrategy;
import e3.User;

import java.util.HashMap;

public class PhoneStrategy implements LoginStrategy {
    private final DataBase users; // Se permite añadir usuarios porque se guarda como "puntero" a database
    // asi que las modificaciones desde fuera se añaden directamente a la clase
    public PhoneStrategy(DataBase users){
        this.users = users;
    }

    @Override
    public boolean validateId(String id) {
        if(id.length() != 9) return false;
        for(int i = 0; i < id.length(); i++) {
            char e = id.charAt(i);
            if((e >= 48 && e<= 57)) continue;
            else return false;
        }
        return true;
    }

    @Override
    public boolean authenticatePassword(String id, String password) {
        HashMap<String,User> m;
        m = users.getPhoneMap();
        if(m.containsKey(id)){
            return m.get(id).checkPassword(password);
        }
        return false;
    }


    @Override
    public MfaStrategy getMFAMethod(String id) {
        HashMap<String,User> m;
        m = users.getPhoneMap();
        return m.get(id).getMFAMethod();
    }

    @Override
    public boolean setMFAMethod(String id, MfaStrategy m) {
        HashMap<String,User> map;
        map = users.getPhoneMap();
        if(map.containsKey(id)){
            User u = map.get(id);
            u.changeMFAMethod(m);
            return true;
        }else return false;
    }
}
