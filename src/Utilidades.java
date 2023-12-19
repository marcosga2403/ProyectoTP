import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do {
            System.out.println(mensaje);
            numero = teclado.nextInt();

        }while (numero<minimo||numero>maximo);

        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;
        do {
            System.out.println(mensaje);
            numero = teclado.nextInt();

        }while (numero<minimo||numero>maximo);


        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
        do {
            System.out.println(mensaje);
            numero = teclado.nextInt();

        }while (numero<minimo||numero>maximo);


        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;
        do {
            System.out.println(mensaje);
            letra = teclado.next().charAt(0);
        }while (letra<minimo||letra>maximo);


        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
       do {
           System.out.println(mensaje);
           dia = teclado.nextInt();
           mes = teclado.nextInt();
           anio = teclado.nextInt();
       }while ((mes>12||mes<0)||(numeroDeDiasMes(mes)==31 && dia>31||dia<0)||(mes==2 && dia>28||dia<0)||(esBisiesto(anio)==true && mes==2 && dia>29||dia<0)||(numeroDeDiasMes(mes)==30 && dia>30||dia<0));



        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        int hora;
        int minuto;
        int segundo;
        do {
            System.out.println(mensaje);
             dia = teclado.nextInt();
             mes = teclado.nextInt();
             anio = teclado.nextInt();
             hora = teclado.nextInt();
             minuto = teclado.nextInt();
             segundo = teclado.nextInt();
        }while ((mes>12||mes<0)||(numeroDeDiasMes(mes)==31 && dia>31||dia<0)||(mes==2 && dia>28||dia<0)||(esBisiesto(anio)==true && mes==2 && dia>29||dia<0)||(numeroDeDiasMes(mes)==30 && dia>30||dia<0)||(hora>24||hora<0)||(minuto > 60 || minuto < 0)||(segundo>60||segundo<60) );



        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }
    public static boolean esBisiesto (int anio){
        if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)))
            return true;
        else
            return false;
    }
    public static int numeroDeDiasMes(int mes) {

        int numeroDias = -1;

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numeroDias = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numeroDias = 30;
                break;
        }
        return numeroDias;
    }
    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}
