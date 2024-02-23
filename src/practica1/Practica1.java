/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica1;

import java.util.Scanner;
import java.util.Random;

public class Practica1 {
    //scanners para pedir opciones
   public static Scanner inputData= new Scanner(System.in);
   public static Scanner inputmd= new Scanner(System.in);
   public static Scanner scanr= new Scanner(System.in);
   //tablero de numeracion y de penalizacion random
   public static String[][] tablero = new String[8][8];
   public static String[][] penalizaciones = new String[8][8];
   //nivel o tipo de penalizacion
   public static int niveldepenalizacion =-99;
   
   //movimientos del jugador y el jugador
   public static String jugador ="@";
   public static int posicion=1;
   public static int intentos=2;
   
    public static void main(String[] args) {
        int opcion;
        do{
        System.out.println("-----------Menú del Juego -----------");
        System.out.println("1. Iniciar juego");
        System.out.println("2. Reanudar juego");
        System.out.println("3. Mostrar informacion del estudiante");
        System.out.println("4. Salir del juego");
        System.out.print("elija una opcion del 1 al 4 :");
        opcion=inputData.nextInt();
        menu(opcion);
        } while (opcion != 4);
         System.out.println("Gracias por jugar");
    }
    
    public static void menu(int opcion){
    switch (opcion) {
                case 1:
                    System.out.println("juego iniciado");
                    llenartablero();
                    llenarpenalizaciones();
                    menudado();
                    imprimirtablero();
                     
                    break;
                case 2:
                    System.out.println("Reanudar juego");
                    // Lógica para pausar el juego
                    menudado();
                    break;
                case 3:
                     System.out.println("-----------#$# -----------");
                     System.out.println("----------- #-----------");
                    System.out.println("Nombre: PEDRO LUIS AVILA GOMEZ");
                    System.out.println("Carnet 202200239");
                    System.out.println("sección C");
                    
                     System.out.println("-----------# -----------");
                     System.out.println("-----------#$# -----------");
                   
                    break;
                case 4:
                    System.out.println("Salir del juego");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }

                  
    }
    public static void llenartablero(){
        
        int contador= 1;
        for (int i =0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j]=String.valueOf(contador);
               contador++;
            }
        }
        for (int i =0; i < penalizaciones.length; i++) {
            for (int j = 0; j < penalizaciones[i].length; j++) {
                penalizaciones[i][j]="";
               
            }
        }
        
}
    public static void llenarpenalizaciones(){
        Random random= new Random();
        int cantPenalizaciones;
        int posicionaleatoria;
        
        for(int i=0;  i < penalizaciones.length; i++){
            
            cantPenalizaciones=random.nextInt(3)+2;

            while(cantPenalizaciones != 0){
                
                while(true){
                posicionaleatoria=random.nextInt(penalizaciones[i].length);
                if(! penalizaciones[i][posicionaleatoria].contains("#")){
                 break;
                }
                }
                //del 0-7
                
                penalizaciones[i][posicionaleatoria]="#"+ penalizaciones[i][posicionaleatoria];
            cantPenalizaciones--;
            
            }
        }
        
        
    }
    public static void imprimirtablero(){
        //asigno un booleano para darle un sentido a cada fila para la numeracion del tablero
    boolean sentido=true;
    String casilla;
    //creo un objeto de tipo string que me dira el comportamiento de las casillas
    
    for (int i = tablero.length - 1; i >= 0; i--) {
            for (int j = tablero.length - 1; j >= 0; j--) {
                if (sentido == true) {
                    
                    //concatena el espacio donde va el signo del # a la par del numero del tablero
                    casilla=penalizaciones[i][j]+tablero[i][j];
                 System.out.printf("| %4s" , casilla );   
                    
                }else  {
                    casilla=penalizaciones[i][(tablero.length-1)-j]+tablero[i][(tablero.length-1)-j];
                 System.out.printf("| %4s" , casilla );
                }
                }
            //comportamiento del jugador
            System.out.println("|");
            
            for (int j = tablero.length - 1; j >= 0; j--) {
            
                
                if (sentido == true) {
                    if(posicion == Integer.parseInt( tablero[i][j])){
                    System.out.printf("| %4s" , jugador, " " );
                    
                        if (penalizaciones[i][j].contains("#")) {
                            niveldepenalizacion=i+1;
                            niveles();
                        }
                    
                    } else{
                   System.out.printf("| %4s" , " " );
                   }  
                    
                }else  {
                   if(posicion == Integer.parseInt( tablero[i][(tablero.length-1)-j])){
                    System.out.printf("| %4s" , jugador , " ");
                    if (penalizaciones[i][(tablero.length-1)-j].contains("#")) {
                            niveldepenalizacion=i+1;
                        }
                    } else{
                   System.out.printf("| %4s" , " " );
                   }
                }
                
        }
            System.out.println("|");
            
            sentido =!sentido;
            System.out.println("");
            
            }
            
        }
    public static void menudado(){
    String ele;
    Random random= new Random();
    int dado;
        do {    
            if (posicion>64) {
                System.out.println("FELICIDADES GANASTE!");
                break;
            }
             imprimirtablero();
             niveles();
             
            System.out.println("presione d para lanzar el dado");
            System.out.println("presione p para pausar el juego");
            ele=inputmd.nextLine();
            
            if (ele.equals("d")) {
                //dado
                dado=random.nextInt(6)+1;
                System.out.println("te mueves "+dado+ "  posiciones");   
                
                posicion+= dado;//la posicion se va autosumando conforme aumente , cuando el la suma llegue a 64 se termina el juego y que regrese al menu principal
            }else if (ele.equals("p")) {
                System.out.println("juego pausado");
            }
            else{
                System.out.println("opcion no valida, elija de nuevo");
            }
        } while (!ele.equals("p"));
    
    }   
    public static void niveles() {
       switch (niveldepenalizacion) {
           case 1:
           case 2:
               System.out.println("nivel facil de penalizacion");
               operacionesF();
               break;
           case 3:
           case 4:
           case 5:
               System.out.println("nivel INTERMEDIO de penalizacion");
               operacionesI();
               break;
           case 6:
           case 7:
           case 8:
               System.out.println("tu nivel es el dificil");
               operacionesD();
               break;
           default:
               break;
       }
        niveldepenalizacion=-99;
    }
    public static void operacionesF(){
        Random facil= new Random();
    double A;
    double B;
    double C;
    double a;
    double b;
    double c;
    int opcionFacil=facil.nextInt(3)+1;
    switch(opcionFacil){
        case 1  :
            
                   System.out.println("Ley de cosenos "
                           + "Opcion 1: Los valores dados son: \n" +
"Lado A = 15\n" +
"Lado C = 20\n" +
"Ángulo α = 25\n" +
"Encontrar los valores del lado B y los ángulos b y c");
                   

    A = 15.0;
    C = 20;
    a = 25;

        B = Math.sqrt(Math.pow(A, 2) + Math.pow(C, 2) - 2 *A *C * Math.cos(Math.toRadians(a)));
        b = Math.toDegrees(Math.acos((Math.pow(C, 2) + Math.pow(B, 2) - Math.pow(A, 2)) / (2 * C * B)));
        c = 180 - a - b;

        System.out.println("Ingrese el valor del lado B: ");
        double respuestaLadoB = scanr.nextDouble();
        System.out.println("Ingrese el valor del angulo b: ");
        double respuestaAnguloBeta = scanr.nextDouble();
        System.out.println("Ingrese el valor del angulo c: ");
        double respuestaAnguloGamma = scanr.nextDouble();

        if (respuestaLadoB == B && respuestaAnguloBeta == b && respuestaAnguloGamma == c) {
            System.out.println("Has logrado vencer la penalizacion, continua tu camino!");
            
            System.out.println(" las respuestas correctas son B=" +B +"ANGULO b ="+b+"ANGULO  c ="+c);
        } else {
            System.out.println("Respuestas incorrectas.  ");
            System.out.println(" las respuestas correctas son B=" +B +"ANGULO b ="+b+"ANGULO c ="+c);
        }
        break;
        
        case 2:
        
     System.out.println("Ley de cosenos "
                           + "Opcion 2: Los valores dados son:\n" +
"Lado B = 10\n" +
"Lado C = 25\n" +
"angulo b = 30");
                   

        B = 10.0;
        C = 25;
        b = 30;

        A = Math.sqrt(Math.pow(B, 2) + Math.pow(C, 2) - 2 *B *C * Math.cos(Math.toRadians(b)));
        a = Math.toDegrees(Math.acos((Math.pow(C, 2) + Math.pow(B, 2) - Math.pow(A, 2)) / (2 * C * B)));
        c = 180 - a - b;

        System.out.println("Ingrese el valor del lado A: ");
        double respuestaLadoB2 = scanr.nextDouble();
        System.out.println("Ingrese el valor del angulo a: ");
        double respuestaAnguloBeta2 = scanr.nextDouble();
        System.out.println("Ingrese el valor del angulo c: ");
        double respuestaAnguloGamma2 = scanr.nextDouble();

        if (respuestaLadoB2 == A && respuestaAnguloBeta2 == a && respuestaAnguloGamma2 == c) {
            System.out.println("Has logrado vencer la penalizacion, continua tu camino!");
            
            System.out.println(" las respuestas correctas son A = " +A +"ANGULO a = " +a+ "  ANGULO c ="+c);
        } else {
            System.out.println("Respuestas incorrectas. ");
            System.out.println(" las respuestas correctas son B=" +B +"ANGULO b ="+b+"ANGULO c ="+c);
        }
        break;
        
        case 3:
        
        System.out.println("Ley de cosenos "
                           + "Opción 3: Los valores dados son:\n" +
"Lado A = 18\n" +
"Lado B = 25\n" +
"Ángulo c = 30\n" +
"Encontrar los valores del lado C y los angulos a y b");
                   

    A = 18.0;
    B = 25;
    c = 30;

        C = Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2) - 2 *A *B * Math.cos(Math.toRadians(c)));
        a = Math.toDegrees(Math.acos((Math.pow(B, 2) + Math.pow(C, 2) - Math.pow(A, 2)) / (2 * C * B)));
        b = 180 - c - a;

        System.out.println("Ingrese el valor del lado C: ");
        double respuestaLadoB3 = scanr.nextDouble();
        System.out.println("Ingrese el valor del angulo a: ");
        double respuestaAnguloBeta3 = scanr.nextDouble();
        System.out.println("Ingrese el valor del angulo b: ");
        double respuestaAnguloGamma3 = scanr.nextDouble();

        if (respuestaLadoB3 == C && respuestaAnguloBeta3 == a && respuestaAnguloGamma3 == b) {
            System.out.println("Has logrado vencer la penalizacion, continua tu camino!");
            
            System.out.println(" las respuestas correctas son C=" +C +"ANGULO a ="+A+"ANGULO b ="+b);
        } else {
            System.out.println("Respuestas incorrectas.");
            System.out.println(" las respuestas correctas son C=" +C +"ANGULO a ="+A+"ANGULO b ="+b);
        }
        break;
    }
    }
    
    
    public static void operacionesI(){
     Random inter= new Random();
    int opcioni=inter.nextInt(3)+1;
    switch(opcioni){
        case 1  :
              Scanner scanner = new Scanner(System.in);
              System.out.println("opcion 1:");
        // matrices predefinidas
        int[][] matrizA = {{7, 48, 5, 0,1}, {57, 8, 4,6,14}, {0, 5, 6,78,15},{21, 14, 8,19,54},{32, 20, 26,47,12}};
        int[][] matrizB = {{9, 5, 2, 1,8}, {4, 2, 3,47,8}, {48, 55, 32,19,6},{7, 56, 32,14,8},{32, 87, 0,1,7}};

        // mostrar matrices al usuario
        System.out.println("Matriz A:");
        mostrarMatriz(matrizA);

        System.out.println("Matriz B:");
        mostrarMatriz(matrizB);

        // matriz resultado (Suma de A y B)
        int[][] matrizResultado = sumarMatrices(matrizA, matrizB);

        // obtener la respuesta del usuario
        System.out.println("Ingrese la matriz resultante (5x5):");
        int[][] respuestaUsuario = asignarMatriz("", scanner);

        // comparar matrices
        if (sonMatricesIguales(matrizResultado, respuestaUsuario)) {
            System.out.println("Felicidades! La respuesta es correcta. Has ganado!");
            mostrarMatriz(matrizResultado);
        } else {
            System.out.println(" la respuesta es incorrecta. Has perdido.");
            System.out.println("La respuesta correcta es:");
            mostrarMatriz(matrizResultado);
        }

        
break;
        case 2  :
              Scanner scanner2 = new Scanner(System.in);
              System.out.println("opcion 2:");
        // Matrices predefinidas
        int[][] matrizA2 = {{4, 9, 7, 45,18}, {7, 51, 26,8,38}, {48, 26, 37,21,19},{1, 0, 6,8,1},{2, 19, 55,25,15}};
        int[][] matrizB2 = {{0, 2, 15, 1,66}, {21, 48, 62,7,33}, {4, 88, 0,68,4},{25, 18, 24,7,55},{24, 15, 36,5,98}};

        // mostrar matrices al jugador
        System.out.println("Matriz A:");
        mostrarMatriz(matrizA2);

        System.out.println("Matriz B:");
        mostrarMatriz(matrizB2);

        // matriz resultado (Suma de A y B)
        int[][] matrizResultado2 = sumarMatrices(matrizA2, matrizB2);

        // obtener la respuesta del usuario
        System.out.println("Ingrese la matriz resultante (5x5):");
        int[][] respuestaUsuario2 = asignarMatriz("", scanner2);

        // comparar matrices
        if (sonMatricesIguales(matrizResultado2, respuestaUsuario2)) {
            System.out.println("Felicidades! La respuesta es correcta. Has ganado!");
            mostrarMatriz(matrizResultado2);
        } else {
            System.out.println(" la respuesta es incorrecta. Has perdido.");
            System.out.println("La respuesta correcta es:");
            mostrarMatriz(matrizResultado2);
        }

       
break;
        case 3  :
              Scanner scanner3 = new Scanner(System.in);

        // Matrices predefinidas
        int[][] matrizA3 = {{0, 1, 15, 5,36}, {1, 78, 65,32,4}, {48, 66, 39,0,55},{14, 98, 63,20,15},{11, 39, 84,7,1}};
        int[][] matrizB3 = {{78, 25, 66, 48,98}, {0, 45, 2,3,1}, {2, 9, 14,10,20},{35, 87, 65,2,32},{25, 8, 4,9,39}};

        // mostrar matrices al jugador
        System.out.println("opcion 3:");
        System.out.println("Matriz A:");
        mostrarMatriz(matrizA3);

        System.out.println("Matriz B:");
        mostrarMatriz(matrizB3);

        // matriz resultado (Suma de A y B)
        int[][] matrizResultado3 = sumarMatrices(matrizA3, matrizB3);

        // Obtener la respuesta del usuario
        System.out.println("Ingrese la matriz resultante (5x5):");
        int[][] respuestaUsuario3 = asignarMatriz("", scanner3);

        // Comparar matrices
        if (sonMatricesIguales(matrizResultado3, respuestaUsuario3)) {
            System.out.println("Felicidades! La respuesta es correcta. Has ganado!");
            mostrarMatriz(matrizResultado3);
        } else {
            System.out.println(" la respuesta es incorrecta. Has perdido.");
            System.out.println("La respuesta correcta es:");
            mostrarMatriz(matrizResultado3);
        }

        
break;
    }
    }
    
    // Metodo para asignar manualmente los valores de una matriz
    private static int[][] asignarMatriz(String mensaje, Scanner scannerm) {
        System.out.println(mensaje);
        int[][] matriz = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("Ingrese el elemento en la posicion (%d, %d): ", i + 1, j + 1);
                matriz[i][j] = scannerm.nextInt();
            }
        }

        return matriz;
    }
    // Método para sumar dos matrices
    private static int[][] sumarMatrices(int[][] matrizA, int[][] matrizB) {
        int[][] resultado = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }

        return resultado;
    }
    
    // Metodo para mostrar la matriz en la suma
    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
   public static boolean sonMatricesIguales(int[][] matrizA, int[][] matrizB) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrizA[i][j] != matrizB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    } 
   
    public static void mostrarMatriz2(int[][] matriz2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz2[i][j] + " ");
            }
            System.out.println();
        }
    }
    // Metodo para comparar dos matrices
    
    public static boolean sonMatricesIguales2(int[][] matrizA21, int[][] matrizB21) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrizA21[i][j] != matrizB21[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    public static void operacionesD(){
     Random di= new Random();
    int opcionD=di.nextInt(3)+1;
    switch(opcionD){
        case 1  :
            System.out.println("problema dificil1 1");
            Scanner scannerdi = new Scanner(System.in);

        // Matrices predefinidas pero no las que son jajaja me dio ansiedad
       int[][] matrizA21 = {
                {10, 20, 30, 40},
                {5, 15, 25, 35},
                {2, 4, 6, 8},
                {1, 3, 5, 7}
        };

        int[][] matrizB21 = {
                {2, 1, 2, 1},
                {1, 2, 1, 2},
                {2, 1, 2, 1},
                {1, 2, 1, 2}
        };
        // Mostrar matrices al usuario
        System.out.println("Matriz A:");
        mostrarMatriz2(matrizA21);

        System.out.println("Matriz B:");
        mostrarMatriz2(matrizB21);

        // Matriz resultado (División de A por B)
        int[][] matrizResultado21 = dividirMatrices(matrizA21, matrizB21);

        // Obtener la respuesta del usuario
        System.out.println("Ingrese la matriz resultante (4x4):");
        int[][] respuestaUsuario = asignarMatriz2("", scannerdi);

        // Comparar matrices
        if (sonMatricesIguales2(matrizResultado21, respuestaUsuario)) {
            System.out.println("Felicidades! La respuesta es correcta. Has ganado!");
            mostrarMatriz2(matrizResultado21);
        } else {
            System.out.println("la respuesta es incorrecta. Has perdido.");
            System.out.println("La respuesta correcta es:");
            mostrarMatriz2(matrizResultado21);
        }
            break;
        case 2:
             System.out.println("problema dificil 2");
             Scanner scannerdi2 = new Scanner(System.in);

        // Matrices predefinidas pero no las que son jajaja me dio ansiedad
       int[][] matrizA22 = {
                {10, 20, 30, 40},
                {5, 15, 25, 35},
                {2, 4, 6, 8},
                {1, 3, 5, 7}
        };

        int[][] matrizB22 = {
                {1, 1, 2, 1},
                {1, 2, 1, 2},
                {2, 1, 2, 1},
                {1, 2, 1, 2}
        };
        // Mostrar matrices al usuario
        System.out.println("Matriz A:");
        mostrarMatriz2(matrizA22);

        System.out.println("Matriz B:");
        mostrarMatriz2(matrizB22);

        // Matriz resultado (División de A por B)
        int[][] matrizResultado22 = dividirMatrices(matrizA22, matrizB22);

        // Obtener la respuesta del usuario
        System.out.println("Ingrese la matriz resultante (4x4):");
        int[][] respuestaUsuario2 = asignarMatriz2("", scannerdi2);

        // Comparar matrices
        if (sonMatricesIguales2(matrizResultado22, respuestaUsuario2)) {
            System.out.println("Felicidades! La respuesta es correcta. Has ganado!");
            mostrarMatriz2(matrizResultado22);
        } else {
            System.out.println("la respuesta es incorrecta. Has perdido.");
            System.out.println("La respuesta correcta es:");
            mostrarMatriz2(matrizResultado22);
        }
             break;
           
    }
    }
    private static int[][] asignarMatriz2(String mensaje, Scanner scannerd) {
        System.out.println(mensaje);
        int[][] matriz2 = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("Ingrese el elemento en la posicion (%d, %d): ", i + 1, j + 1);
                matriz2[i][j] = scannerd.nextInt();
            }
        }

        return matriz2;
    }
     // Método para dividir dos matrices
    private static int[][] dividirMatrices(int[][] matrizA21, int[][] matrizB21) {
        int[][] resultado2 = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                resultado2[i][j] = matrizA21[i][j] / matrizB21[i][j];
            }
        }

        return resultado2;
    }

    }
    

