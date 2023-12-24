import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPuertosEspaciales(int capacidad) {
        this.lista = new PuertoEspacial[capacidad];
    }

    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < lista.length; i++){
            if (lista[i] == null){
                return i;
            }
        }
        return lista.length;
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        for(int i = 0; i < lista.length; i++){
            if(lista[i] == null){
                return false;
            }
        }return true;
    }

	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        for (int i = 0; i< lista.length; i++){
            if(lista[i] == null){
                lista[i] = puertoEspacial;
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        for (int i= 0; i < lista.length; i++){
            if (lista[i].getCodigo().equals(codigo)){
                return lista[i];
            }
        }
        System.out.println("Puerto con codigo " + codigo + " no encontrado.");
        return null;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        String codigo;
        do {
            System.out.println(mensaje);
            codigo = teclado.nextLine();
        }while (buscarPuertoEspacial(codigo)==null);
        PuertoEspacial puertoEspacial = buscarPuertoEspacial(codigo);
        return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(nombre));
            for (int i = 0; i < this.getOcupacion(); i++){
                pw.println(lista[i].toString());
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (pw != null){
                pw.close();
            }
        }
        return true;
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fichero));
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                /* Uso .trim() que es un metodo de la clase string para borrar los espacios en blanco y que no lea el \r\n de cada linea del fichero */
                String nombre = sc.next().trim();
                String codigo = sc.next();
                String radio = sc.next();
                String azimut = sc.next();
                String polar = sc.next();
                String muelles = sc.next();
                PuertoEspacial puerto = new PuertoEspacial(nombre, codigo, Double.parseDouble(radio), Double.parseDouble(azimut), Double.parseDouble(polar), Integer.parseInt(muelles));
                listaPuertosEspaciales.insertarPuertoEspacial(puerto);
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaPuertosEspaciales;
    }
}
