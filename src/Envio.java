import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */

public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador
     * @param porte
     * @param cliente
     * @param fila
     * @param columna
     * @param precio
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }

    public String getLocalizador() {
        return localizador;
    }
    public Porte getPorte() {
        return porte;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }

    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        String columna ="";
        switch (getColumna()){
            case 0:
                columna = "A";
                break;
            case 1:
                columna = "B";
                break;
            case 2:
                columna = "C";
                break;
            case 3:
                columna = "D";
                break;
            case 4:
                columna = "E";
                break;
            case 5:
                columna = "F";
                break;
            case 6:
                columna = "G";
                break;
            case 7:
                columna = "H";
                break;
            case 8:
                columna = "I";
                break;
            case 9:
                columna = "J";
                break;
            case 10:
                columna = "K";
                break;
            case 11:
                columna = "L";
                break;
            case 12:
                columna = "M";
                break;
            case 13:
                columna = "N";
                break;
            case 14:
                columna = "Ñ";
                break;
            case 15:
                columna = "O";
                break;
            case 16:
                columna = "P";
                break;
            case 17:
                columna = "Q";
                break;
            case 18:
                columna = "R";
                break;
            case 19:
                columna = "S";
                break;
            case 20:
                columna = "T";
                break;
            case 21:
                columna = "U";
                break;
            case 22:
                columna = "V";
                break;
            case 23:
                columna = "W";
                break;
            case 24:
                columna = "X";
                break;
            case 25:
                columna = "Y";
                break;
            case 26:
                columna = "Z";
                break;
        }
        return getFila()+columna;
    }
    public double getPrecio() {
        return precio;
    }

    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
        return "Envio " + getLocalizador() + " para Porte " + getPorte() + " de " + porte.getOrigen() + " " + porte.getSalida() +
                " a " + porte.getDestino() + " en hueco " + getHueco() + " por " + getPrecio();
    }
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        return cliente.cancelarEnvio(getLocalizador());
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */

    public boolean generarFactura(String fichero) {
        System.out.println("-----------------------------------------------------");
        System.out.println("--------- Factura del envio"+getLocalizador()+" ---------");
        System.out.println("-----------------------------------------------------");
        System.out.println("Porte: "+getPorte());
        System.out.println("Origen: "+porte.getOrigen());
        System.out.println("Destino: "+porte.getDestino());
        System.out.println("Salida: "+porte.getSalida());
        System.out.println("Llegada: "+porte.getLlegada());
        System.out.println("Cliente: "+getCliente());
        System.out.println("Hueco: "+getHueco());
        System.out.println("Precio: "+getPrecio());
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            pw.println("-----------------------------------------------------");
            pw.println("--------- Factura del envio " + getLocalizador()+ " ---------");
            pw.println("-----------------------------------------------------");
            pw.println("Porte:" +getPorte());
            pw.println("Origen: "+porte.getOrigen());
            pw.println("Destino: "+porte.getDestino());
            pw.println("Salida: "+porte.getSalida());
            pw.println("Llegada: "+porte.getLlegada());
            pw.println("Cliente: "+getCliente());
            pw.println("Hueco: "+getHueco());
            pw.println("Precio: "+getPrecio());
        } catch (FileNotFoundException e) {
            return false;
        }
        finally {
            pw.close();
        }
        return true;
    }



    /**
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @param idPorte
     * @return
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder();
        for(int i = 0; i < 9; i++){
            char letra = (char) (rand.nextInt(26)+'A');
            localizador.append(letra);
        }
        String localizadorGenerado = idPorte + localizador.toString();

        return localizadorGenerado;
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param porte
     * @param cliente
     * @return Envio para el porte y cliente especificados
     */

    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {

        String localizador = generarLocalizador(rand,porte.getID());
        int filaDelHueco = Integer.parseInt(Utilidades.leerCadena(teclado, "Fila del hueco:"));
        int columnaDelHueco = Integer.parseInt(Utilidades.leerCadena(teclado, "Columna del hueco:"));
        int precioDelEnvio = Integer.parseInt(Utilidades.leerCadena(teclado, "Precio del envío:"));

        return new Envio(localizador, porte, cliente, filaDelHueco, columnaDelHueco, precioDelEnvio);
    }
}