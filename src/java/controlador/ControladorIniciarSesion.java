/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import conexion.Conexion;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import logicadenegocios.Estudiante;
import javax.sql.DataSource;
import logicadenegocios.Usuario;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "login", urlPatterns = {"/login"})

public class ControladorIniciarSesion extends HttpServlet {
    UsuarioDAO dao = new UsuarioDAO();
    Usuario modelo;
    Connection conexion;
    PreparedStatement ps;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorIniciarSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorIniciarSesion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      String usuario = request.getParameter("usuario").toString();
      String password = request.getParameter("password").toString();
      String tipo = request.getParameter("tipo").toString();
      if (!"".equals(usuario) && !"".equals(password)){  
            try {
               modelo = new Usuario(usuario,password,tipo);
               Usuario usuarioActual = dao.iniciarSesion(modelo);
               if(usuarioActual != null ){
                 if (tipo == "A"){
                   RequestDispatcher rd = request.getRequestDispatcher("/agregarEstudiante.html");
                   rd.include(request, response);
                   response.setContentType("text/html");  
                   out.println("<script type=\"text/javascript\">");  
                   out.println("alert('Se ha registrado el estudiante exitosamente');");  
                   out.println("</script>");
                 } else {
                   RequestDispatcher rd = request.getRequestDispatcher("/reservarSala.html");
                   rd.include(request, response);
                   response.setContentType("text/html");  
                   out.println("<script type=\"text/javascript\">");  
                   out.println("alert('Bienvenido');");  
                   out.println("</script>");
                 }
                } else {
                  response.setContentType("text/html");  
                   out.println("<script type=\"text/javascript\">");  
                   out.println("alert('Se ha registrado el estudiante exitosamente');");  
                   out.println("</script>");
               }
               } catch (SQLException ex) {
                Logger.getLogger(ControladorIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
        

      }else{
         out.println("<script type=\"text/javascript\">");  
         out.println("alert('Por favor no deje espacios en blanco');");  
         out.println("</script>");
      }
 }
    
     
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}