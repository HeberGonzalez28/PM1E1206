package Configuracion;

public class Transapais {

    //nombre de la BD
    public static final String BDname="PM012024";

    //Creacion de las tablas de base de datos
    public static final String TablePais = "Pais";

    // Creacion de los campos de base de datos
    public static final String id = "id";
    public static final String codigo = "codigo";
    public static final String nombre = "nombre";

    public static final String CreateTablePais = "CREATE TABLE "+ TablePais +"("+
        "id INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT, nombre TEXT)";

    public static final String SelectAllPais = "SELECT * FROM " + TablePais;

    public static final String DropTablePais = "DROP TABLE IF EXISTS " + TablePais;
}
