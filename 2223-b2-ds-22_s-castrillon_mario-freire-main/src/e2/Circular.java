package e2;

import java.util.Iterator;

class Circular implements Iterator<String> {

    private int posicion;
    TVRealityList list;
    private boolean remove;

    // Recordar que list es de clase TVRealityList por tanto no tiene metodos propios de una lista
    public Circular(TVRealityList list){
        posicion = -1;
        this.list = list;
        remove = true;
    }

    @Override
    // Debería iterar infinitamente hasta que solo quede 1 elemento??
    public boolean hasNext() {
        //return posicion < list.getLength();
        return list.getLength() > 1;
    }

    @Override
    public String next() {
        remove = false;
        posicion++;
        if(posicion >= list.getLength()) posicion = 0;
        return list.getElement(posicion);


    }

    @Override
    public void remove(){
        if(remove) throw new IllegalStateException();
        list.deleteElement(posicion);
        posicion--;
        remove = true;
    }
}