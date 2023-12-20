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
public class ListaEnvios {
    private Envio[] envios;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaEnvios(int capacidad) {
        this.envios = new Envio[capacidad];
    }
    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < envios.length; i++){
            if (envios[i] == null){
                return i;
            }
        }
        return envios.length;
    }
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        for(int i = 0; i < envios.length; i++){
            if(envios[i] == null){
                return false;
            }
        }return true;
    }

	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i){
        return envios[i];
    }


    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        for (int i = 0; i<envios.length; i++){
            if(envios[i] == null){
                envios[i] = envio;
                envios[i].getPorte().ocuparHueco(envio);
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        for (int i= 0; i< envios.length; i++){
            if (envios[i].getLocalizador().equals(localizador)){
                return envios[i];
            }
        }
        return null;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte
     * @param fila
     * @param columna
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        for (int i = 0; i< envios.length; i++){
            if ((envios[i].getColumna() == columna) && (envios[i].getFila() == fila) && (envios[i].getPorte().getID().equals(idPorte))){
                return envios[i];
            }
        }
        return null;
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
        for (int i = 0; i < envios.length; i++){
            if (envios[i].getLocalizador().equals(localizador)){
                envios[i].cancelar();
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        for (int i =0; i<= envios.length; i++){
            System.out.println(envios[i].toString());
        }
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        String localizador;
        do {
            System.out.println(mensaje);
            localizador = teclado.nextLine();

        }while (buscarEnvio(localizador)==null);
        Envio envio = buscarEnvio(localizador);
        return envio;
    }



    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero
     * @return
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        try {
            FileWriter fileWriter = new FileWriter(fichero, true);
            pw = new PrintWriter(fileWriter);
            for (int i = 0; i < envios.length; i++){
                pw.println(envios[i].toString());
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (pw != null){
                pw.close();
            }
        }
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios
     * @param portes
     * @param clientes
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(ficheroEnvios));
            sc.useDelimiter(";");
            while (sc.hasNext()){
                String localizador = sc.next();
                Porte porte = portes.buscarPorte(sc.next());
                Cliente cliente = clientes.buscarClienteEmail(sc.next());
                int fila = sc.nextInt();
                int columnas = sc.nextInt();
                double precio = sc.nextDouble();
                Envio envio = new Envio(localizador, porte, cliente, fila, columnas, precio);
                insertarEnvio(envio);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de envíos");
        } finally {

        }
    }
}
