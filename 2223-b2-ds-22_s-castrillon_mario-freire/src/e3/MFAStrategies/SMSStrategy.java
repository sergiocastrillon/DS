package e3.MFAStrategies;

import e3.MfaStrategy;

// Código de 7 dígitos empezado y terminado por letra mayuscula y minuscula y el resto numeros
public class SMSStrategy implements MfaStrategy {
    @Override
    public String generateCode() {
        StringBuilder ret = new StringBuilder();


        for(int i = 0; i < 7; i++){
            if(i==0 || i == 6){
                if(((int) (Math.random()*10)) % 2 == 0){ // Elegimos mayuscula o minuscula
                    ret.append((char) (Math.random()*(90-65+1)+65));
                }else{
                    ret.append((char) (Math.random()*(122-97+1)+97));
                }
            }else{
                ret.append((int) (Math.random()*10));
            }

            // ret.append();
        }

        return ret.toString();
    }

    /*public static void main(String [] args){
        SMSStrategy g = new SMSStrategy();
        System.out.println(g.generateCode());
    }*/
}
