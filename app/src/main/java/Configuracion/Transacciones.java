package Configuracion;

public class Transacciones {

    public static final String DBName = "DBQuizPerson";

    public static final String TablePersonas = "contactos";

    public static final String id = "id";

    public static final String pais = "pais";

    public static final String nombre = "nombre";

    public static final String telefono = "telefono";

    public static final String nota = "nota";

    public static final String imagen = "imagen";

    private static final String CREATE_TABLE = "CREATE TABLE " + TablaContactos + " (" +
             id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            pais + " TEXT, " +
            nombre + " TEXT, " +
            telefono + " TEXT, " +
            nota + " TEXT, " +
            imagen + " BLOB)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS " + TablaContactos + ";";

    public static final String SelectAllPersonas = "SELECT * FROM " + TablaContactos + ";";

}
