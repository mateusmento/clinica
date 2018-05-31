package api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import db.*;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            
            PacienteDAO dao = new PacienteDAO();
            Paciente paciente = dao.getPaciente(login);
            
            if (paciente != null && senha.equals(paciente.getSenha())) {
                request.getSession().setAttribute("paciente", paciente);
            } else {
                request.getSession().setAttribute("msg", "Login ou senha errado.");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("/");
    }
}
