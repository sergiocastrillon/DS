package e3;


public class LoginScreen {
    LoginStrategy loginStrategy;

    public LoginScreen(LoginStrategy loginStrategy){
        this.loginStrategy = loginStrategy;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
// ...
    }

    public String authetication(String id, String password){
        if(loginStrategy.validateId(id) && loginStrategy.authenticatePassword(id, password)){

            return loginStrategy.getMFAMethod(id).generateCode();
        }
        return null;
    }

    public boolean setMfaStrategy(String id, MfaStrategy m){
        if(!loginStrategy.validateId(id)) return false;
        return loginStrategy.setMFAMethod(id,m);
    }
}
