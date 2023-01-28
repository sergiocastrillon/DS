package e3;

// Como se especifica en el ejercicio LoginStrategy (y MFAStragegy) pueden ser declaradas como clases abstractas
// de esta manera LoginStrategy contiene el map
public interface LoginStrategy {


    boolean validateId(String id);
    boolean authenticatePassword(String id, String password);


    MfaStrategy getMFAMethod(String id);

    boolean setMFAMethod(String id, MfaStrategy m);

}
