package e2;

import java.util.ArrayList;
import java.util.Iterator;

public class TVRealityList implements Iterable<String> {
    private ArrayList <String> listS;
    private boolean circular;

    public TVRealityList(ArrayList<String> listS){
        this.listS = new ArrayList<>(listS);
        circular = true;
    }

    public void changeList(ArrayList<String> listS){
        this.listS = new ArrayList<>(listS);
    }
    public void setIterator(String iterator){
        if(iterator.equalsIgnoreCase("circular")) this.circular = true;
        else if(iterator.equalsIgnoreCase("rebote")) this.circular = false;
        else throw new IllegalArgumentException("Solo se puede iterar como 'circular' o como 'rebote'");

    }
// A las clases de tipo iterator le pasamos TVRealityList para permitir que la implementación de lista que hay pueda
    // cambiar, es decir, mientras los métodos getLength, deleteElement, etc sigan funcionando como deben, podemos
    // cambiar como funciona la lista dentro de TVRealityList
    @Override
    public Iterator<String> iterator() { // Devuelta del iterador
        if(circular) return new Circular(this);
        else return new Rebote(this);
    }

    int getLength(){
        return listS.size();
    }

    void deleteElement(int i){
        listS.remove(i);
    }


    void addElement(String i){
        listS.add(i);
    }

    String getElement(int i){
        return listS.get(i);
    }

}


