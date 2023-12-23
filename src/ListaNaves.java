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
public class ListaNaves {
    private Nave[] naves;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {
        this.naves = new Nave[capacidad];
    }

    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        int cont=0;
        for(int i = 0;i<naves.length;i++){
            if(naves[i]!=null){
                cont++;
            }
        }
        return cont;
    }

    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        int cont=0;
        for(int i = 0;i<naves.length;i++){
            if(naves[i]!=null){
                cont++;
            }
        }
        if(cont==naves.length-1){
            return true;
        }
        else return false;
    }

	// TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        for(int i = 0;i<naves.length;i++){
            if(naves[i]==null){
                naves[i]=nave;
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        for (int i = 0;i<naves.length;i++){
            if(naves[i].getMatricula()==matricula){
                return naves[i];
            }
        }
        return null;

    }

    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for(int i =0;i<naves.length;i++){
            if(naves[i]!=null){
                System.out.println(naves[i].toString());
            }
        }

    }



    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        String matricula;
        do {
            System.out.println(mensaje);
            matricula = teclado.nextLine();
        }while (buscarNave(matricula)==null);
        Nave nave= buscarNave(matricula);
        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(nombre));
            for (int i = 0; i < naves.length; i++) {
                pw.println(naves[i].toString());
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        Scanner sc = null;
        ListaNaves listaNaves = new ListaNaves(capacidad);
        try {
            sc = new Scanner(new File(fichero));
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String marca = sc.next().trim();
                String modelo = sc.next();
                String matricula = sc.next();
                String filas = sc.next();
                String columnas = sc.next();
                String alcance = sc.next();
                listaNaves.insertarNave(new Nave(marca, modelo, matricula, Integer.parseInt(columnas), Integer.parseInt(filas), Double.parseDouble(alcance)));
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaNaves;
    }
}
