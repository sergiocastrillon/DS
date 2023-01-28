package e3;

import java.util.HashMap;

public class DataBase {
    // La base de datos impide ciertas operaciones sobre el map a la vez que permite modificarlo desde fuera
    // del loginStrategy permitiendo añadir usuarios sin crear un nuevo objeto de loginStrategy
    private final HashMap<String,User> emailm = new HashMap<>();
    private final HashMap<String,User> phonem = new HashMap<>();
    private final HashMap<String,User> usernamem = new HashMap<>();

    // Si ya existe un email, un telefono o un nombre de usuario como el del nuevo usuario entonces devuelves false
    // y no lo añades
    public boolean addUser(User user){
        if(emailm.containsKey(user.getEmail()) || phonem.containsKey(user.getPhone()) ||
                usernamem.containsKey(user.getUsername())){
            return false;
        }
        int cont = 0;
        if(user.getEmail() != null){
            emailm.put(user.getEmail(),user);
            cont++;
        }
        if(user.getUsername() != null){
            usernamem.put(user.getUsername(),user);
            cont++;
        }
        if(user.getPhone() != null){
            phonem.put(user.getPhone(),user);
            cont++;
        }
        return cont != 0;
    }

    public boolean deleteUser(String id, String pass){
        User e = emailm.get(id);
        User s = usernamem.get(id);
        User p = phonem.get(id);

        if(e != null){
            if(!e.checkPassword(pass)) return false;
            if(e.getPhone() != null) phonem.remove(e.getPhone());
            if(e.getUsername() != null) usernamem.remove(e.getUsername());
            emailm.remove(id);
        }

        if(s != null){
            if(!s.checkPassword(pass)) return false;
            if(s.getEmail() != null) emailm.remove(s.getEmail());
            if(s.getPhone() != null) phonem.remove(s.getPhone());
            usernamem.remove(id);
        }

        if(p != null){
            if(!p.checkPassword(pass)) return false;
            if(p.getEmail() != null) emailm.remove(p.getEmail());
            if(p.getUsername() != null) usernamem.remove(p.getUsername());
            phonem.remove(id);
        }
        return e != null || s != null || p != null;
    }

    public HashMap<String,User> getEmailMap(){
        return new HashMap<>(emailm);
    }

    public HashMap<String,User> getUsernameMap(){
        return new HashMap<>(usernamem);
    }

    public HashMap<String,User> getPhoneMap(){
        return new HashMap<>(phonem);
    }

    public void setMFAMethod(String email,String phone,String username,MfaStrategy m){
        if(email != null) emailm.get(email).changeMFAMethod(m);
        if(phone != null) phonem.get(phone).changeMFAMethod(m);
        if(username != null) usernamem.get(username).changeMFAMethod(m);
    }

}
