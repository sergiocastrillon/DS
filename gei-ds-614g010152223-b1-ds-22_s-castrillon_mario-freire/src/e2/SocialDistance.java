package e2;

public class SocialDistance {
    public static char [][] seatingPeople ( char [][] layout ) {


        // Comprobamos que el array dado cumpla las condiciones
        if(layout == null) throw new IllegalArgumentException("Null layout not allowed\n");
        int rows = layout.length;
        int col = layout[0].length; // Todas las filas tienen la misma longitud
        for(int i = 0; i < rows; i++){
            // Comprobamos la longitud de las filas
            if(layout[i].length != col) throw new IllegalArgumentException("Invalid layout\n");
            else{
                for(int n = 0; n < col;n++){
                    if(layout[i][n]!='A' && layout[i][n]!='.') throw new IllegalArgumentException("Invalid characters\n");
                    else{
                        // Sentamos a gente en los sitios libres
                        if(layout[i][n]=='A') layout[i][n] = '#';
                    }
                }
            }

        }


        // movements se mantiene a true si en la última iteración se modificó layout
        boolean movements = true;
        while(movements){

            // Creamos una copia del array para cumplir la condición de que "todos se levantan o se
            // sientan a la vez"
            char [][] layoutCopy = new char[rows][col];
            // No existe una función de copia de arrays bidimensionales??
            for(int i = 0; i < rows; i++)
                for(int n=0; n<col; n++)
                    System.arraycopy(layout[i], 0, layoutCopy[i], 0, col);
                  //layoutCopy[i][n]= layout[i][n];

            movements = false;
            // Buscamos gente sentada con 4 o más personas alrededor
            for(int i = 0; i < rows; i++){
                for(int n = 0; n < col;n++){
                    if(layoutCopy[i][n] == '#'){ // Si encontramos a una persona sentada
                        int cont = 0;
                        // Bucles que recorren las 8 posiciones contiguas a la actual
                        // (si está en algún borde evita salirse del array)
                        for(int x = i - 1; (x <= i + 1) && (x < rows); x++){
                            for(int y = n - 1; (y <= n + 1) && (y < col); y++){
                                // Si x o y es -1 estamos fuera del array
                                // Comprobamos si hay alguien sentado y que no estemos comparando un valor con si mismo
                                if(y != -1 && x != -1 && layoutCopy[x][y]=='#' && (x!=i || y!=n)) cont+=1;
                            }
                        }
                        if(cont >= 4){ // Si el contador es 4 o mayor la persona tiene que levantarse
                            layout[i][n] = 'A';
                            movements = true;
                        }
                    }
                }
            }


            // Copia del array
            for(int i = 0; i < rows; i++)
                for(int n=0; n<col; n++)
                    System.arraycopy(layout[i], 0, layoutCopy[i], 0, col);


            for (int i = 0; i < rows; i++) {
                for (int n = 0; n < col; n++) {
                    if (layoutCopy[i][n]=='A') { // Buscamos sitios libres
                        // Mientras sit sea true podemos sentar a una persona en esta posición
                        boolean sit = true;
                        for (int x = i - 1; (x <= i + 1) && (x < rows); x++) {
                            for (int y = n - 1; (y <= n + 1) && (y < col); y++) {
                                // Comprobaciones para no salirse del array
                                if (y != -1 && x != -1 && layoutCopy[x][y]=='#') {
                                    // Si encontramos a alguien sentando alrededor ya no se puede sentar
                                    // aumentamos la x y la y para salir del bucle.
                                    x = i + 1;
                                    y = n + 1;
                                    sit = false;

                                }
                            }
                        }
                        if(sit){
                            layout[i][n] = '#';
                            movements = true;
                        }
                    }
                }
            }
        }

        return layout;
    }
}
// Si una mesa en inicio marcada con una A queda vacía debe volver a guardarse la A
