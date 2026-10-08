package main.java.com.inversiones.magdezis.login.magdezis.config;

//nunca exponer credenciales en github
public class Credentials {
    
    public static final String DATA_BASE = "login_magdezis_in4bm";
    public static final String URL_DB = "jdbc:mysql://localhost:3306/login_magdezis_in4bm?useSSL=false&serverTimezone=UTC";
    public static final String PASS_DB = "$DmynM4A";
    public static final String USER_DB = "IN4BM";
    
    
    /*
    public static final String DATA_BASE = System.getenv("DATA_BASE");
    public static final String URL_DB = System.getenv("URL_MYSQL_DB")+DATA_BASE;
    public static final String PASS_DB = System.getenv("PASS_MYSQL_DB");
    public static final String USER_DB = System.getenv("USER_MYSQL_DB");
    */
    
}

