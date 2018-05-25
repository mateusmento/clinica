package db;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;


public class PacienteDAO extends DAO
{
    public PacienteDAO() throws SQLException
    {
    }

    
    public Paciente getPaciente(String login) throws SQLException
    {
        String query = "select * from paciente where login = ?";
        Paciente paciente = null;
        
        PreparedStatement sql = mysql.prepareStatement(query);
        sql.setString(1, login);
        ResultSet res = sql.executeQuery();

        if (res.next()) {
            String nome = res.getString("nome");
            String senha = res.getString("senha");
            int id = res.getInt("id");
            paciente = new Paciente();
            paciente.setNome(nome);
            paciente.setLogin(login);
            paciente.setSenha(senha);
            paciente.setId(id);
        }
       
        return paciente;
    }

}
