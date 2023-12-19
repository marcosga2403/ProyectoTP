import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaClientes(int capacidad) {
		this.clientes = new Cliente[capacidad];
		
    }
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        int cont=0;
        for(int i = 0;i<clientes.length;i++){
            if(clientes[i]!=null){
                cont++;
            }
        }
        return cont;
    }
    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        int cont=0;
        for(int i = 0;i<clientes.length;i++){
            if(clientes[i]!=null){
                cont++;
            }
        }
        if(cont==clientes.length-1){
            return true;
        }
        else return false;
    }
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente){
        for(int i = 0;i<clientes.length;i++){
            if(clientes[i]==null){
                clientes[i]=cliente;
                return true;
            }
        }
        return false;
    }
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email) {
        for (int i = 0;i<clientes.length;i++){
            if(clientes[i].getEmail()==email){
                return clientes[i];
            }
        }
        return null;
    }

    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        String email;
        do {
            System.out.println(mensaje);
            email = teclado.nextLine();
        }while (buscarClienteEmail(email)==null);
        Cliente cliente = buscarClienteEmail(email);
        return cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero
     * @return
     */
    public boolean escribirClientesCsv(String fichero) {


        try {



        } catch (FileNotFoundException e) {
            return false;
        } finally {

        }
        return true;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero
     * @param capacidad
     * @param maxEnviosPorCliente
     * @return lista de clientes
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {

        try {

        } catch (FileNotFoundException e) {
            return null;
        } finally {

        }
        return listaClientes;
    }
}