package e2;

public class SocialDistance {
    public static char [][] seatingPeople ( char [][] layout ) {
        // Comprobamos que el array dado cumpla las condiciones
        if(layout == null) throw new IllegalArgumentException("Null layout not allowed\n");
        int rows = layout.length;
        int col = layout[0].length; // Todas las filas tienen la misma longitud
        for(int i = 0; i < rows; i++){
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
            movements = false;
            // Creamos una copia del array para cumplir la condición de que "todos se levantan o se
            // sientan a la vez"
            char [][] layoutCopy = new char[rows][col];
            // No existe una función de copia de arrays bidimensionales??
            for(int i = 0; i < rows; i++)
                for(int n=0; n<col; n++)
                    System.arraycopy(layout[i], 0, layoutCopy[i], 0, col);
                  //layoutCopy[i][n]= layout[i][n];


            // Buscamos gente sentada con 4 o más personas alrededor
            for(int i = 0; i < rows; i++){
                for(int n = 0; n < col;n++){
                    if(layoutCopy[i][n] == '#'){ // Si encontramos a una persona sentada
                        int cont = 0;
                        // Bucles que van desde p-1 a p+1 (si está en algún borde evita salirse del array)
                        for(int x = i - 1; (x <= i + 1) && (x < rows); x++){
                            // Si x es -1 estamos fuera del array, aumentamos uno la x
                            if(x==-1) x+=1;
                            for(int y = n - 1; (y <= n + 1) && (y < col); y++){
                                // Si y es -1 estamos fuera del array, aumentamos uno la y
                                if(y==-1) y+=1;
                                if(layoutCopy[x][y]=='#') cont+=1;
                                // Comprobamos que no estemos comparando un valor con si mismo
                                if(x==i && y==n) cont-=1;
                            }
                        }
                        if(cont >= 4){
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
                for (int n = 0; n < col; n++){
                    if(layoutCopy[i][n]=='A'){
                        // Mientras sit sea true podemos sentar a una persona en esta posición
                        boolean sit = true;
                        for(int x = i - 1; (x <= i + 1) && (x < rows); x++) {
                            if(x==-1) x+=1;
                            for(int y = n - 1; (y <= n + 1) && (y < col); y++) {
                                if(y==-1) y+=1;
                                if(layoutCopy[x][y]=='#'){
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
