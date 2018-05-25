package api;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class ConversorData {

    static public Date converterData(String formato, String data) throws ParseException
    {
        return new Date(new SimpleDateFormat(formato).parse(data).getTime());
    }

    static public Time converterHorario(String formato, String horario) throws ParseException
    {
        return new Time(new SimpleDateFormat(formato).parse(horario).getTime());
    }

    static private String converterString(String formato, Object obj) throws ParseException
    {
        SimpleDateFormat formatador = new SimpleDateFormat();
        formatador.applyPattern(formato);
        return formatador.format(obj);
    }

    static public String converterString(String formato, Time horario) throws ParseException
    {
        return converterString(formato, (Object) horario);
    }

    static public String converterString(String formato, Date data) throws ParseException
    {
        return converterString(formato, (Object) data);
    }
}
