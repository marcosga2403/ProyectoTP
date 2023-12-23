import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author      Taller de Progamación
 * @version     1.0
 */
public class PlanetExpress {
    private final int maxPortes;
    private final int maxNaves;
    private final int maxClientes;
    private final int maxEnviosPorCliente;
    private ListaPuertosEspaciales listaPuertosEspaciales;
    private final int maxPuertosEspaciales;
    private ListaNaves listaNaves;
    private ListaClientes listaClientes;
    private ListaPortes listaPortes;


    /**
     * TODO: Rellene el constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
    }

    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        this.listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos,this.maxPuertosEspaciales);
        this.listaNaves = ListaNaves.leerNavesCsv(ficheroNaves, this.maxNaves);
        this.listaPortes = ListaPortes.leerPortesCsv(ficheroPortes, this.maxPortes, listaPuertosEspaciales, listaNaves);
        this.listaClientes = ListaClientes.leerClientesCsv(ficheroClientes, maxClientes, maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios, listaPortes, listaClientes);
    }


    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        this.listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
        this.listaNaves.escribirNavesCsv(ficheroNaves);
        this.listaPortes.escribirPortesCsv(ficheroPortes);
        this.listaClientes.escribirClientesCsv(ficheroClientes);
        this.listaPortes.getPorte(1).generarListaEnvios(ficheroEnvios);
    }

    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }

    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }

    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }

    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * @param teclado
     * @return
     */
    public ListaPortes buscarPorte(Scanner teclado) {
        String puertoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen: ");
        String puertoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino: ");
        Fecha fecha = Utilidades.leerFecha(teclado, "Fecha de Salida:");

        return listaPortes.buscarPortes(puertoOrigen, puertoDestino, fecha);
    }


    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * @param teclado
     * @param rand
     * @param porte
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        Envio envio = null;
        char seleccionarBillete = Utilidades.leerLetra(teclado, "¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?", 'n', 'n');
        if(seleccionarBillete == 'e') {
            String emailCliente = Utilidades.leerCadena(teclado, "Email del cliente:");
            Cliente clienteCase3 = this.listaClientes.buscarClienteEmail(emailCliente);
            while (clienteCase3 == null) {
                System.out.println("Email no encontrado.");
                emailCliente = Utilidades.leerCadena(teclado, "Email del cliente:");
                clienteCase3 = this.listaClientes.buscarClienteEmail(emailCliente);
            }
            envio = Envio.altaEnvio(teclado,rand,porte,this.listaClientes.buscarClienteEmail(emailCliente));
            porte.ocuparHueco(envio);
        }
        else {
            Cliente cliente = Cliente.altaCliente(teclado, this.listaClientes, this.maxEnviosPorCliente);
            if(this.maxClientesAlcanzado()){
                System.out.println("Numero maximo de clientes alcanzado, no se pudo crear el cliente.");
            }
            else{
                this.insertarCliente(cliente);
            }
            envio = Envio.altaEnvio(teclado,rand,porte,this.listaClientes.buscarClienteEmail(cliente.getEmail()));
            porte.ocuparHueco(envio);
        }
        System.out.println("Envio " + envio.getLocalizador() + " creado correctamente.");
    }


    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        int opcion = teclado.nextInt();
        switch (opcion){
            case 0:
                System.out.println("1. Alta de Porte");;
                break;
            case 1:
                System.out.println("2. Alta de Cliente");
                break;
            case 2:
                System.out.println("3. Buscar Porte");
                break;
            case 3:
                System.out.println("4. Mostrar envíos de un cliente");
                break;
            case 4:
                System.out.println("5. Generar lista de envíos");
                break;
            case 5:
                System.out.println("0. Salir");
                break;
        }
        return opcion;
    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10 Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            return;
        }

        /* CAMPOS QUE PROVIENEN DE LA CONFIGURACION DEL MAIN */
        int maxPuertosEspaciales = Integer.parseInt(args[0]);
        int maxNaves = Integer.parseInt(args[1]);
        int maxPortes = Integer.parseInt(args[2]);
        int maxClientes = Integer.parseInt(args[3]);
        int maxEnviosPorCliente = Integer.parseInt(args[4]);

        String ficheroPuertos = args[5];
        String ficheroNaves = args[6];
        String ficheroPortes = args[7];
        String ficheroClientes = args[8];
        String ficheroEnvios = args[9];
        /* FIN DE LOS CAMPOS DEL MAIN */

        /* CREAMOS EL NEGOCIO DE PLANET EXPRESS */
        PlanetExpress planetExpress = new PlanetExpress(maxPuertosEspaciales, maxNaves, maxPortes, maxClientes, maxEnviosPorCliente);
        /* CARGAMOS LOS DATOS DEL NEGOCIO CON LOS FICHEROS */
        planetExpress.cargarDatos(ficheroPuertos, ficheroNaves, ficheroPortes, ficheroClientes, ficheroEnvios);

        /* EJECUCION */
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;
        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 1:     // TODO: Alta de Porte
                    Porte porte = Porte.altaPorte(teclado, new Random(), planetExpress.listaPuertosEspaciales, planetExpress.listaNaves , planetExpress.listaPortes );
                    planetExpress.insertarPorte(porte);
                    break;
                case 2:     // TODO: Alta de Cliente
                    Cliente cliente = Cliente.altaCliente(teclado, planetExpress.listaClientes, planetExpress.maxEnviosPorCliente);
                    if(planetExpress.maxClientesAlcanzado()){
                        System.out.println("Numero maximo de clientes alcanzado, no se pudo crear el cliente.");
                    }
                    else{
                        planetExpress.insertarCliente(cliente);
                    }
                    break;
                case 3:     // TODO: Buscar Porte
                    ListaPortes listaPortes = planetExpress.buscarPorte(teclado);
                    listaPortes.listarPortes();

                    String codigoPorte = Utilidades.leerCadena(teclado, "Seleccione un porte:");
                    Porte porteCase3 = listaPortes.buscarPorte(codigoPorte);
                    while (codigoPorte != "CANCELAR" || porteCase3 == null){
                        if(codigoPorte == "CANCELAR"){
                            break;
                        }
                        else{
                            if(porteCase3 == null){
                                System.out.println("Porte no encontrado.");
                                codigoPorte = Utilidades.leerCadena(teclado, "Seleccione un porte:");
                                porteCase3 = listaPortes.buscarPorte(codigoPorte);
                            }
                        }
                    }

                    planetExpress.contratarEnvio(teclado, new Random(), porteCase3);

                    break;
                case 4:     // TODO: Listado de envíos de un cliente
                    String emailCliente = Utilidades.leerCadena(teclado, "Email del cliente:");
                    String localizadorEnvioCliente = Utilidades.leerCadena(teclado, "Seleccione un envio:");
                    Cliente clienteCase4 = planetExpress.listaClientes.buscarClienteEmail(emailCliente);
                    ListaEnvios listaEnvios = clienteCase4.getListaEnvios();
                    Envio envio = listaEnvios.buscarEnvio(localizadorEnvioCliente);
                    while(envio == null){
                        System.out.println("Localizador incorrecto");
                        localizadorEnvioCliente = Utilidades.leerCadena(teclado, "Seleccione un envio:");
                        clienteCase4 = planetExpress.listaClientes.buscarClienteEmail(emailCliente);
                        listaEnvios = clienteCase4.getListaEnvios();
                        envio = listaEnvios.buscarEnvio(localizadorEnvioCliente);
                    }

                    char cancelarOfactura = Utilidades.leerLetra(teclado, "¿Cancelar envío (c), o generar factura (f)?", 'c','f');
                    if(cancelarOfactura == 'c'){

                    }
                    else {
                        String nombreFichero = Utilidades.leerCadena(teclado, "Nombre del fichero:");
                        boolean ok = envio.generarFactura(nombreFichero);
                        if(ok){
                            System.out.println("Factura generada correctamente.");
                        }
                        else{
                            System.out.println("Factura no generada.");
                        }
                    }
                    break;
                case 5:     // TODO: Lista de envíos de un porte


                    break;
            }
        } while (opcion != 0);


    }
}
