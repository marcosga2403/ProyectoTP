import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPortes(int capacidad) {
        this.portes = new Porte[capacidad];
    }
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < portes.length; i++){
            if (portes[i] == null){
                return i;
            }
        }
        return portes.length;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        for(int i = 0; i < portes.length; i++){
            if(portes[i] == null){
                return false;
            }
        }return true;
    }

	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        for (int i = 0; i<portes.length; i++){
            if(portes[i] == null){
                portes[i] = porte;
                return true;
            }
        }
        return false;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        for (int i= 0; i< portes.length; i++){
            if (portes[i].getID().equals(id)){
                return portes[i];
            }
        }
        return null;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha){
        for (int i = 0; i<portes.length; i++){
            if ((portes[i].getOrigen().equals(codigoOrigen) && (portes[i].getDestino().equals(codigoDestino)) && (portes[i].getLlegada().equals(fecha)))){
            }
        }
        return new ListaPortes(portes.length);
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i =0; i<= portes.length; i++){
            System.out.println(portes[i].toString());
        }
    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado
     * @param mensaje
     * @param cancelar
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        String id;
        cancelar = null;
        do {

            System.out.println(mensaje);
            id = teclado.nextLine();
        }while (buscarPorte(id)==null);
        Porte porte = null;

        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero
     * @return
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(fichero));
            for (int i = 0; i < portes.length; i++){
                pw.println(portes[i].toString());
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero
     * @param capacidad
     * @param puertosEspaciales
     * @param naves
     * @return
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        Scanner sc = null;
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try {
            sc = new Scanner(new File(fichero));
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String id = sc.next();
                String matricula = sc.next();
                String codOrigen = sc.next();
                int muelleOrigen = sc.nextInt();
                String stringSalida = sc.next();
                int salidaNumDia = stringSalida.indexOf(0,2);
                int salidaNumMes = stringSalida.indexOf(3,5);
                int salidaNumAnio = stringSalida.indexOf(6,9);
                int salidaNumHora = stringSalida.indexOf(11,13);
                int salidaNumMin = stringSalida.indexOf(14,15);
                Fecha salida = new Fecha(salidaNumDia, salidaNumMes, salidaNumAnio, salidaNumHora, salidaNumMin, 0);
                String codDestino = sc.next();
                int muelleDestino = sc.nextInt();;
                String stringLlegada = sc.next();
                int salidaNumDia2 = stringLlegada.indexOf(0,2);
                int salidaNumMes2 = stringLlegada.indexOf(3,5);
                int salidaNumAnio2 = stringLlegada.indexOf(6,9);
                int salidaNumHora2 = stringLlegada.indexOf(11,13);
                int salidaNumMin2 = stringLlegada.indexOf(14,15);
                Fecha llegada = new Fecha(salidaNumDia2, salidaNumMes2, salidaNumAnio2, salidaNumHora2, salidaNumMin2, 0);
                int precio = sc.nextInt();
                listaPortes.insertarPorte(new Porte(id,naves.buscarNave(matricula),
                        puertosEspaciales.buscarPuertoEspacial(codOrigen),muelleOrigen,salida,
                        puertosEspaciales.buscarPuertoEspacial(codDestino),muelleDestino,llegada,precio));
            }
        } catch (Exception e) {
            return null;
        }
        return listaPortes;
    }
}
