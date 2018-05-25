package db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySQL
{

    private MySQL()
    {
    }

    public static Connection conectar(String banco, String usuario, String senha) throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "jdbc:mysql://localhost:3306/" + banco;
        Connection conn = null;
        conn = DriverManager.getConnection(url, usuario, senha);
        return conn;
    }
}
