package db;

import db.MySQL;
import java.sql.*;

public class DAO
{
    protected Connection mysql;
    
    public DAO() throws SQLException
    {
        mysql = MySQL.conectar("clinica_db", "clinica", "clinica123");
    }

    void close() throws SQLException
    {
        mysql.close();
    }
    
    @Override
    public void finalize() throws Throwable
    {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
