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
        for (int i= 0; i< this.getOcupacion(); i++){
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
        ListaPortes listaPortes = new ListaPortes(portes.length);
        for (int i = 0; i<portes.length; i++){
            if ((portes[i].getOrigen().getCodigo().equals(codigoOrigen) && (portes[i].getDestino().getCodigo().equals(codigoDestino)) && (portes[i].getSalida().coincide(fecha)))){
                listaPortes.insertarPorte(portes[i]);
            }
        }
        return listaPortes;
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i = 0; i < this.getOcupacion(); i++){
            System.out.println("Porte " + portes[i].getID()+ " de " + portes[i].getOrigen().toStringSimple() + " M" + portes[i].getOrigen().getMuelles() + " ("
                + portes[i].getSalida().toString() + ") a " + portes[i].getDestino().toStringSimple() + " M" + portes[i].getDestino().getMuelles() + " ("
                    + portes[i].getLlegada().toString() + ")");
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
            for (int i = 0; i < this.getOcupacion(); i++){
                pw.println(portes[i].toString());
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        finally {
            pw.close();
        }
        return true;
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
                String id = sc.next().trim();
                String matricula = sc.next();
                String codOrigen = sc.next();
                String muelleOrigen = sc.next();
                String stringSalida = sc.next();
                int salidaNumDia = Integer.parseInt(stringSalida.substring(0,2));
                int salidaNumMes = Integer.parseInt(stringSalida.substring(3,5));
                int salidaNumAnio = Integer.parseInt(stringSalida.substring(6,10));
                int salidaNumHora = Integer.parseInt(stringSalida.substring(11,13));
                int salidaNumMin = Integer.parseInt(stringSalida.substring(14,16));
                Fecha salida = new Fecha(salidaNumDia, salidaNumMes, salidaNumAnio, salidaNumHora, salidaNumMin, 0);
                String codDestino = sc.next();
                String muelleDestino = sc.next();;
                String stringLlegada = sc.next();
                int salidaNumDia2 = Integer.parseInt(stringLlegada.substring(0,2));
                int salidaNumMes2 = Integer.parseInt(stringLlegada.substring(3,5));
                int salidaNumAnio2 = Integer.parseInt(stringLlegada.substring(6,10));
                int salidaNumHora2 = Integer.parseInt(stringLlegada.substring(11,13));
                int salidaNumMin2 = Integer.parseInt(stringLlegada.substring(14,16));
                Fecha llegada = new Fecha(salidaNumDia2, salidaNumMes2, salidaNumAnio2, salidaNumHora2, salidaNumMin2, 0);
                String precio = sc.next();

                Nave nave = naves.buscarNave(matricula);
                PuertoEspacial puertoEspacialOrigen = puertosEspaciales.buscarPuertoEspacial(codOrigen);
                PuertoEspacial puertoEspacialDestino = puertosEspaciales.buscarPuertoEspacial(codDestino);

                Porte porte = new Porte(id, nave,  puertoEspacialOrigen, Integer.parseInt(muelleOrigen), salida, puertoEspacialDestino,
                        Integer.parseInt(muelleDestino), llegada, Double.parseDouble(precio));

                listaPortes.insertarPorte(porte);
            }
        } catch (Exception e) {
            return null;
        }
        return listaPortes;
    }
}
