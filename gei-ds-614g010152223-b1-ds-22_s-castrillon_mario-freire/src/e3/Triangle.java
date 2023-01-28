package e3;

import java.util.Arrays;
import java.util.Objects;

record Triangle(int angle0, int angle1, int angle2) {
    public Triangle {
        if (angle0 + angle1 + angle2 != 180) throw new IllegalArgumentException("Angles not sum 180\n");
        if (angle0 < 0 || angle1 < 0 || angle2 < 0) throw new IllegalArgumentException("Angles must be greater than 0\n");
    }

    public Triangle (Triangle t) {
        this(t.angle0,t.angle1,t.angle2);
    }


    public boolean isRight () {
        return angle0 == 90 || angle1 == 90 || angle2 == 90;
        // No hace falta comprobar si existen más angulos de 90 porque si existieran la suma de
        // eso dos angulos daria 180 y por tanto la suma de los 3 no podria ser 180
    }


    public boolean isAcute () {
        return angle0 < 90 && angle1 < 90 && angle2 < 90;
    }


    public boolean isObtuse () {
        return angle0 > 90 || angle1 > 90 || angle2 > 90;
    }


    public boolean isEquilateral () {
        return angle0 == angle1 && angle1 == angle2;
    }


    public boolean isIsosceles() {
        if(angle0 == angle1) return angle0 != angle2;
        if(angle0 == angle2) return angle0 != angle1;
        if(angle1 == angle2) return angle1 != angle0;
        return false;
    }


    public boolean isScalene() {
        return angle0 != angle1 && angle0 != angle2 && angle1 != angle2;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;

        if(getClass() != o.getClass()) return false;
        Triangle t = (Triangle) o;

        // Si los triangulos son iguales los ángulos han de quedar ordenados de manera que los dos primeros ángulos
        // del array sean los dos ángulos más pequeños de los dos triángulos, si no coinciden no pueden ser iguales
        int[] angles = {this.angle0,this.angle1,this.angle2,t.angle0,t.angle1,t.angle2};
        Arrays.sort(angles);
        return angles[0]==angles[1] && angles[2]==angles[3] && angles[4] == angles[5];
    }


    @Override
    public int hashCode () {
        // La multiplicación garantiza que los elementos que son iguales
        // según el equals tienen el mismo hash y que los triangulos dintintos tienden
        // a hashes diferentes
        // Usar Objets.hash pasándole los ángulos por separado no garantiza el equals
        return Objects.hash(angle0*angle1*angle2);
    }

}
