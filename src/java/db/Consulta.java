package db;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import api.ConversorData;

public class Consulta {

    int medico_id, paciente_id;
    String convenio;
    Date data;
    Time horario;
    
    public Consulta()
    {
    }

    public int getMedicoId() {
        return medico_id;
    }

    public void setMedicoId(int medico_id) {
        this.medico_id = medico_id;
    }

    public int getPacienteId() {
        return paciente_id;
    }

    public void setPacienteId(int paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public Date getData() {
        return data;
    }

    public String getDataAsString(String formato) throws ParseException {
        return ConversorData.converterString(formato, data);
    }

    public void setData(String data) throws ParseException {
        System.out.println(data);
        this.data = ConversorData.converterData("yyyy-MM-dd", data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }
    
    public String getHorarioAsString(String formato)throws ParseException {
        return ConversorData.converterString(formato, horario);
    }
    
    public void setHorario(String horario) throws ParseException {
        this.horario = ConversorData.converterHorario("HH:mm", horario);
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }
}
