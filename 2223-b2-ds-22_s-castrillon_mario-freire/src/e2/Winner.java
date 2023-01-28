package e2;

import java.util.Iterator;

public class Winner {

    // Recibe una k que representa el salto y un objecto de tipo TVRealityList y devuelve el participante
    // resultante de aplicar el salto con el recorrido que tuviera seleccionado TVRealityList
    public static String selectCandidate(TVRealityList TVlist, int k){
        // Salto 0 no tiene sentido porque se cuenta el primer elemento, es decir con salto 0 tendrías que borrar
        // el elemento anterior al 1 (o en arrays el 0) lo cual no se puede hacer. Es decir con salto 1 borrarías el
        // primer elemento al igual que con salto 3 el primer elemento que borras es el 3 así que no puede haber k = 0
        if(k<1) throw new IllegalArgumentException("k debe ser mayor que 0");
        if(k > TVlist.getLength()) throw new IllegalArgumentException("k no debe ser mayor que n");


        Iterator<String> it = TVlist.iterator();
        int cont = 0;
        while(it.hasNext()){
            it.next();
            cont++;
            if(cont == k) {
                it.remove();
                cont = 0;
            }
        }
        return TVlist.getElement(0);
    }


// Devuelve el participante resultante calculado con un valor de k aleatorio entre 1 y el numero de participantes
    public static String selectCandidate(TVRealityList TVlist){
        int k = (int) (Math.random() * TVlist.getLength() + 1);
        return selectCandidate(TVlist,k);
    }
}
