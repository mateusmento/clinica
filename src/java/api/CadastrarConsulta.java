package api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebServlet(name = "cadastrar-consulta", urlPatterns = {"/cadastrar-consulta"})
public class CadastrarConsulta extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
        int medico_id = Integer.parseInt(request.getParameter("medico_id"));
        String convenio = request.getParameter("convenio");
        String data = request.getParameter("data");
        String horario = request.getParameter("horario");

        Paciente paciente = (Paciente) request.getSession().getAttribute("paciente");

        if (paciente == null) enviarPaginaErro("Você não está logado", request, response);
        
        Consulta consulta = new Consulta();
        consulta.setMedicoId(medico_id);
        consulta.setPacienteId(paciente.getId());
        consulta.setConvenio(convenio);

        try {
            consulta.setData(data);
            consulta.setHorario(horario);

            ConsultaDAO consultadao = new ConsultaDAO();
            consultadao.inserir(consulta);

            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (Throwable ex) {
            enviarPaginaErro(ex.toString(), request, response);
        }
    }
    
    private void enviarPaginaErro(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("errorMsg", msg);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
