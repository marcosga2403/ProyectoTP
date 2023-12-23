/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class PuertoEspacial {

    private String nombre;
    private String codigo;
    private double radio;
    private double azimut;
    private double polar;
    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre
     * @param codigo
     * @param radio
     * @param azimut
     * @param polar
     * @param numMuelles
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public double getRadio() {
        return radio;
    }
    public double getAzimut() {
        return azimut;
    }

    public double getPolar() {
        return polar;
    }
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino
     * @return
     */
    public double distancia(PuertoEspacial destino) {

        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double x1 = radio*Math.sin(azimut)*Math.cos(polar);
        double x2 = destino.radio*Math.sin(destino.azimut)*Math.cos(destino.polar);
        double y1 = radio*Math.sin(azimut)*Math.sin(polar);
        double y2 = destino.radio*Math.sin(destino.azimut)*Math.sin(destino.polar);
        double z1 = radio * Math.cos(azimut);
        double z2 = destino.radio * Math.cos(destino.azimut);

        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        double distancia = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2));
        return distancia;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return this.nombre+", en  ("+this.radio+" "+this.azimut+" "+this.polar
                +"), con "+this.numMuelles+" muelles";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)
     */
    /* En el enunciado de la practica aparece el parentesis pegado al nombre como Mercury Station(MS-1) */
    public String toStringSimple() {
        return this.nombre + "(" + this.codigo + ")";
    }
}
