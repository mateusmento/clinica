package api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.PacienteDAO;
import db.Paciente;


@WebServlet(name = "cadastrar-paciente", urlPatterns = {"/cadastrar-paciente"})
public class CadastrarPaciente extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try {
            PacienteDAO dao = new PacienteDAO();
            String login = request.getParameter("login");
            
            if (dao.getPaciente(login) == null){
                Paciente paciente = new Paciente();
                paciente.setNome(request.getParameter("nome"));
                paciente.setLogin(login);
                paciente.setSenha(request.getParameter("senha"));
                dao.inserir(paciente);
                request.getSession().setAttribute("msg", "Conta criada com sucesso");
            } else {
                request.getSession().setAttribute("msg", "Login de conta já existe");
            }

            response.sendRedirect("/");

        } catch(Exception e) {
        }
    }
}
