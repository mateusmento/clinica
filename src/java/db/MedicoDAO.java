package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;


public class MedicoDAO extends DAO 
{

    public MedicoDAO() throws SQLException
    {
    }

    public ArrayList<String> getEspecialidades() throws SQLException
    {
        String query = "select especialidade from medico group by especialidade";
        ArrayList<String> especialidades = new ArrayList<String>();
        
        Statement sql = mysql.createStatement();
        ResultSet res = sql.executeQuery(query);

        while (res.next()) {
            String esp = res.getString("especialidade");
            if (esp != null) especialidades.add(esp);
        }

        return especialidades;
    }
    
    public Medico getMedicoPorId(int id) throws SQLException
    {
        String query = "select * from medico where id = ?";
        Medico medico = null;
        PreparedStatement sql = mysql.prepareStatement(query);
        sql.setInt(1, id);
        ResultSet res = sql.executeQuery();
        while(res.next()){
            medico = new Medico();
            medico.setNome(res.getString("nome"));
            medico.setEspecialidade(res.getString("especialidade"));
            medico.setId(res.getInt("id"));
        }

        return medico;
    }
    
    public ArrayList<Medico> getMedicosPorEspecialidade(String especialidade) throws SQLException
    {
        String query = "select * from medico where especialidade = ?";
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        
        PreparedStatement sql = mysql.prepareStatement(query);
        sql.setString(1, especialidade);
        ResultSet res = sql.executeQuery();
        while(res.next()){
            Medico medico = new Medico();
            medico.setNome(res.getString("nome"));
            medico.setEspecialidade(res.getString("especialidade"));
            medico.setId(res.getInt("id"));
            medicos.add(medico);
        }

        return medicos;
    }
}
