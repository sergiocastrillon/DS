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
        return this.angle0 == 90 || this.angle1 == 90 || this.angle2 == 90;
        // No hace falta comprobar si existen más angulos de 90 porque si existieran la suma de
        // eso dos angulos daria 180 y por tanto la suma de los 3 no podria ser 180
    }


    public boolean isAcute () {
        return this.angle0 < 90 && this.angle1 < 90 && this.angle2 < 90;
    }


    public boolean isObtuse () {
        return this.angle0 == angle1 && this.angle1 == this.angle2;
    }


    public boolean isEquilateral () {
        return false;
    }


    public boolean isIsosceles() {
        return false;
    }


    public boolean isScalene() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;

        if(getClass() != o.getClass()) return false;
        Triangle t = (Triangle) o;

        // Dividimos los posibles casos en tres casos principales
        // Despues si cumplen algun caso principal comprobamos las dos combinaciones posibles que quedan
        // tras el caso principal
        if (this.angle0 == t.angle0) { // Caso principal 1
            if (this.angle1 == t.angle1 && this.angle2 == t.angle2) return true;
            if (this.angle1 == t.angle2 && this.angle2 == t.angle1) return true;
        }

        if (this.angle0 == t.angle1) { // Caso principal 2
            if (this.angle1 == t.angle0 && this.angle2 == t.angle2) return true;
            if (this.angle1 == t.angle2 && this.angle2 == t.angle0) return true;
        }

        if (this.angle0 == t.angle2) { // Caso principal 3
            if (this.angle1 == t.angle1 && this.angle2 == t.angle0) return true;
            if (this.angle1 == t.angle0 && this.angle2 == t.angle1) return true;

        }

        return false;
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
