package e3.MFAStrategies;

import e3.MfaStrategy;

// Código de un solo uso, 8 dígitos, se generan dos letras aleatoriamente (se puede obtener por aplicación o como código de respaldo)
public class OTPStrategy implements MfaStrategy {
    @Override
    public String generateCode() {
        // a, b son las posiciones de las letras
        int a = (int) (Math.random()*7);
        int b = (int) (Math.random()*7);
        while(a == b) b = (int) (Math.random()*7);
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < 8; i++){
            if(i == a || i == b) ret.append((char) (Math.random()*(90-65+1)+65));
            else ret.append((int) (Math.random()*10));
        }
        return ret.toString();
    }


    /*public static void main(String [] args){
        OTPStrategy g = new OTPStrategy();
        System.out.println(g.generateCode());
    }*/
}
