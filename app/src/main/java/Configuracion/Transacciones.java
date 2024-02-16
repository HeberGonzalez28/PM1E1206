package Configuracion;

public class Transacciones {

    public static final String DBName = "DBContactos";

    public static final String TableContactos = "contactos";

    public static final String id = "id";

    public static final String pais = "pais";

    public static final String nombre = "nombre";

    public static final String telefono = "telefono";

    public static final String nota = "nota";

    public static final String imagen = "imagen";

    public static final String CreateTableContactos = "Create table "+ TableContactos +" ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT, nombre TEXT, telefono TEXT, nota TEXT, "+
            "imagen BLOB )";

    public static final String DropTableContactos = "DROP TABLE IF EXISTS " + TableContactos;

    public static final String SelectAllContactos = "SELECT * FROM " + TableContactos;

}
