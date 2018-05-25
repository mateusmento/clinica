package api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import db.*;



@WebServlet(name = "listar-medicos", urlPatterns = {"/listar-medicos"})
public class ListarMedicos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String especialidade = (String) request.getParameter("especialidade");
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");

        String json = "[";
        
        try {
            MedicoDAO medicodao = new MedicoDAO();
            ArrayList<Medico> medicos = medicodao.getMedicosPorEspecialidade(especialidade);

            for (int i = 0; i < medicos.size(); i++) {
                Medico medico = medicos.get(i);
                json += "{";
                json += "\"id\": " + medico.getId() + ",";
                json += "\"nome\": \"" + medico.getNome() + "\",";
                json += "\"especialidade\": \"" + medico.getEspecialidade() + "\"";
                json += "}";
                if (i != medicos.size() - 1) json += ",";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ListarMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        json += "]";
        
        writer.print(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    }

}
