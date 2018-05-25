package db;

import java.sql.*;
import java.util.*;
import java.util.logging.*;


public class ConsultaDAO extends DAO
{
    public ConsultaDAO() throws SQLException
    {
    }
    
    public boolean inserir(Consulta consulta) throws SQLException
    {
        String script = "insert into consulta values (null, ?, ?, ?, ?, ?)";
        
        PreparedStatement sql = mysql.prepareStatement(script);
        sql.setInt(1, consulta.getPacienteId());
        sql.setInt(2, consulta.getMedicoId());
        sql.setDate(3, consulta.getData());
        sql.setTime(4, consulta.getHorario());
        sql.setString(5, consulta.getConvenio());
        return sql.executeUpdate() != 0;
    }

    public ArrayList<Consulta> getConsultasPorPaciente(Paciente paciente) throws SQLException
    {
        String query = "select id, paciente_id, medico_id, data, horario, convenio from consulta where paciente_id = ?";
        ArrayList<Consulta> arr = new ArrayList<Consulta>();

        PreparedStatement sql = mysql.prepareStatement(query);
        sql.setInt(1, paciente.getId());
        ResultSet res = sql.executeQuery();

        while (res.next())
        {
            Consulta consulta = new Consulta();
            consulta.setMedicoId(res.getInt("medico_id"));
            consulta.setPacienteId(res.getInt("paciente_id"));
            consulta.setConvenio(res.getString("convenio"));
            consulta.setData(res.getDate("data"));
            consulta.setHorario(res.getTime("horario"));
            arr.add(consulta);
        }       
        
        return arr;
    }
    
}
