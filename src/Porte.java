import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class  Porte {
    private boolean[][] huecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     *
     * @param id
     * @param nave
     * @param origen
     * @param muelleOrigen
     * @param salida
     * @param destino
     * @param muelleDestino
     * @param llegada
     * @param precio
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
        this.id = id;
        this.nave = nave;
        this.origen = origen;
        this.muelleOrigen = muelleOrigen;
        this.salida = salida;
        this.destino = destino;
        this.muelleDestino = muelleDestino;
        this.llegada = llegada;
        this.precio = precio;
        this.huecos = new boolean[nave.getFilas()][nave.getColumnas()];
        listaEnvios = new ListaEnvios(nave.getFilas() + nave.getColumnas());
    }

    public String getID() {
        return id;
    }
    public Nave getNave(){
        return nave;
    }
    public PuertoEspacial getOrigen() {
        return origen;
    }
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    public Fecha getSalida(){
        return salida;
    }
    public PuertoEspacial getDestino() {
        return destino;
    }
    public int getMuelleDestino() {
        return muelleDestino;
    }
    public Fecha getLlegada() {
        return llegada;
    }
    public double getPrecio() {
        return precio;
    }


    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecosLibres = 0;
        for (int i = 0 ; i < huecos.length;i++){
            for(int j = 0; j < huecos[0].length ;j++) {
                if (huecos[i][j]==false){
                    huecosLibres++;
                }
            }
        }
        return huecosLibres;
    }

    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
        int huecosLibres = numHuecosLibres();
        if (huecosLibres==0) {
            return true;
        }
        return false;
    }

    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return huecos[fila][columna];
    }

    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        for (int i = 0; i < listaEnvios.getOcupacion(); i++){
            if (listaEnvios.getEnvio(i).getFila() == fila && listaEnvios.getEnvio(i).getColumna() == columna){
                return listaEnvios.getEnvio(i);
            }
        }
        return null;
    }


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio
     * @return
     */
    public boolean ocuparHueco(Envio envio) {
        if (huecoOcupado(envio.getFila() , envio.getColumna())) {
            return false;
        }
        listaEnvios.insertarEnvio(envio);
        huecos[envio.getFila()][envio.getColumna()] = true;
        return true;
    }

    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        Envio envio = this.listaEnvios.buscarEnvio(localizador);
        huecos[envio.getFila()][envio.getColumna()] = false;
        listaEnvios.eliminarEnvio(localizador);
        return false;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "Porte " + getID() + " de " + getOrigen() + getDestino() + getSalida() + " a " + getDestino() + getLlegada() +
                " en " + getNave() + " por " + getPrecio() + ", huecos libres : " + numHuecosLibres();
    }


    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte " + getID() + " de " + getOrigen() + getSalida() + " a " + getDestino() + getLlegada();
    }


    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        if(this.origen.getCodigo().equals(codigoOrigen) && this.destino.getCodigo().equals(codigoDestino) && (this.salida.coincide(fecha)== true || this.llegada.coincide(fecha) == true)) {
            return true;
        }else return false;
    }


    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < huecos[i].length; j++) {
                System.out.print(" " + huecos[i][j]);
            }
            System.out.print("  ");
        }
    }

    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(fichero));
            for (int i = 0; i < listaEnvios.getOcupacion(); i++){
                pw.println(listaEnvios.getEnvio(i).toString());
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
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        int intervalo = 10;
        return "PM" + rand.nextInt(intervalo) + rand.nextInt(intervalo) + rand.nextInt(intervalo) + rand.nextInt(intervalo);
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves, ListaPortes portes) {

        if(!portes.estaLlena()) {
            String id;
            do {
                id = generarID(rand);
                System.out.println("");
            } while (portes.buscarPorte(id) == null);

            System.out.println("Ingrese código de puerto Origen:");
            String codePuertoOrigen = teclado.nextLine();
            PuertoEspacial puertoEspacialSeleccionadoOrigen = puertosEspaciales.buscarPuertoEspacial(codePuertoOrigen);
            while (puertoEspacialSeleccionadoOrigen == null) {
                System.out.println("Código de puerto no encontrado.\n");
                System.out.print("Ingrese código de puerto Origen:");
                codePuertoOrigen = teclado.nextLine();
                puertoEspacialSeleccionadoOrigen = puertosEspaciales.buscarPuertoEspacial(codePuertoOrigen);
            }

            int numMuelles = puertoEspacialSeleccionadoOrigen.getMuelles();
            System.out.println("Ingrese el muelle de Origen (1 -" + numMuelles + "):");
            int muelleOrigen = teclado.nextInt();
            while (muelleOrigen > numMuelles || muelleOrigen <= 0) {
                System.out.println("Ingrese el muelle de Origen (1 -" + numMuelles + "):");
                muelleOrigen = teclado.nextInt();
            }

            System.out.println("Ingrese código de puerto Destino:");
            String codePuertoDestino = teclado.nextLine();
            PuertoEspacial puertoEspacialSeleccionadoDestino = puertosEspaciales.buscarPuertoEspacial(codePuertoDestino);
            while (puertoEspacialSeleccionadoDestino == null) {
                System.out.println("Código de puerto no encontrado.\n");
                System.out.print("Ingrese código de puerto Destino:");
                codePuertoDestino = teclado.nextLine();
                puertoEspacialSeleccionadoDestino = puertosEspaciales.buscarPuertoEspacial(codePuertoDestino);
            }

            int numMuellesTerminalDestino = puertoEspacialSeleccionadoDestino.getMuelles();
            System.out.println("Ingrese Terminal Destino (1 -" + numMuellesTerminalDestino + "):");
            int terminalDestino = teclado.nextInt();
            while (terminalDestino > numMuelles || terminalDestino <= 0) {
                System.out.println("Ingrese Terminal Destino (1 -" + numMuellesTerminalDestino + "):");
                terminalDestino = teclado.nextInt();
            }

            naves.mostrarNaves();

            System.out.println("Ingrese matrícula de la nave:");
            String matricula = teclado.nextLine();
            double distanciaEntrePuertos = puertoEspacialSeleccionadoOrigen.distancia(puertoEspacialSeleccionadoDestino);
            while (naves.buscarNave(matricula) == null || (naves.buscarNave(matricula) != null && naves.buscarNave(matricula).getAlcance() < distanciaEntrePuertos)) {
                if (naves.buscarNave(matricula) == null) {
                    System.out.println("Matricula de avión no encontrada:");
                } else {
                    System.out.println("Avión selecionado con alcance insuficiente:");
                }
                System.out.println("Ingrese matrícula de la nave:");
                matricula = teclado.nextLine();
            }
            Nave nave = naves.buscarNave(matricula);

            Fecha fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida:");
            Fecha fechaLLegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada:");
            while (fechaLLegada.anterior(fechaSalida)) {
                System.out.println("Llegada debe ser posterior a salida.");
                fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida:");
                fechaLLegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada:");
            }

            System.out.println("Ingrese precio de pesaje:");
            double precioPesaje = teclado.nextDouble();
            while (precioPesaje > 0){
                System.out.println("Ingrese precio de pesaje:");
                precioPesaje = teclado.nextDouble();
            }
            System.out.println("Porte "+id+ " creado correctamente.");
            return new Porte(id,nave,puertoEspacialSeleccionadoOrigen,muelleOrigen,
                    fechaSalida,puertoEspacialSeleccionadoDestino
                    ,terminalDestino,fechaLLegada,precioPesaje);
        }
        else {
            System.out.println("No se pueden dar de alta más vuelos.");
        return null;
        }
    }
}
