package e2;

import java.util.Iterator;

class Rebote implements Iterator<String> {

    private int posicion;
    TVRealityList list;
    private boolean remove = true;

    private boolean inverse;
    public Rebote(TVRealityList list){
        posicion = -1;
        this.list = list;
        inverse = false;
    }


    @Override
    public boolean hasNext() {
        return list.getLength() > 1;
    }

    @Override
    public String next() {
        remove = false;
        if(!inverse){
            posicion++;
            if(posicion >= list.getLength()){
                inverse = true;
                posicion = posicion - 2;
            }
        }else{
            posicion--;
            if(posicion <= -1){
                inverse = false;
                // Al hacer posicion relativo a la posicion actual evitamos problemas con el remove
                posicion = posicion + 2;
            }
        }
        return list.getElement(posicion);
    }

    @Override
    public void remove(){
        if(remove) throw new IllegalStateException();
        list.deleteElement(posicion);
        // Si inverse es falso y no estamos en la ultima posicion decrementamos posicion para contar bien
        // si estamosen la ultima posicion entonces dejamos posicion como está para seguir contando bien
        if((!inverse && !(posicion > list.getLength() - 1)) || posicion == 0) posicion--;

        remove = true;
    }
}
