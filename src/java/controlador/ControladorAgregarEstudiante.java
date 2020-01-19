/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import conexion.Conexion;
import dao.EstudianteDAO;
import static dao.EstudianteDAO.conexion;
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

/**
 *
 * @author Raquel Rojas
 */
@WebServlet(name = "addEstudiante", urlPatterns = {"/addEstudiante"})

public class ControladorAgregarEstudiante extends HttpServlet {
    EstudianteDAO dao = new EstudianteDAO();
    Estudiante modelo;
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
            out.println("<title>Servlet ControladorAgregarEstudiante</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAgregarEstudiante at " + request.getContextPath() + "</h1>");
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
      String carnet = request.getParameter("carnetEstudiante").toString();
      String nombre = request.getParameter("nombreEstudiante").toString();
      String email = request.getParameter("emailEstudiante").toString();
      String tel = request.getParameter("telEstudiante").toString();
      String carrera = request.getParameter("carrera").toString();
      if (!"".equals(request.getParameter("carnetEstudiante")) && !"".equals(nombre)
          && !"".equals(email) && !"".equals(tel)){  
        if (existeCarnet(carnet, request) == false){
            try {
              int carnetInt = Integer.parseInt(carnet);
              Estudiante modelo = new Estudiante(carnetInt, nombre, carrera, email,tel);
              dao.agregarEstudiante(modelo);
              RequestDispatcher rd = request.getRequestDispatcher("/agregarEstudiante.html");
              rd.include(request, response);
              response.setContentType("text/html");  
              out.println("<script type=\"text/javascript\">");  
              out.println("alert('Se ha registrado el estudiante exitosamente');");  
              out.println("</script>");
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAgregarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else{
          RequestDispatcher rd = request.getRequestDispatcher("/agregarEstudiante.html");
          rd.include(request, response);
          response.setContentType("text/html");  
          out.println("<script type=\"text/javascript\">");  
          out.println("alert('Ya existe un estudiante registrado con ese carnet');");  
          out.println("</script>");
        }
      }else{
         RequestDispatcher rd = request.getRequestDispatcher("/agregarEstudiante.html");
         rd.include(request, response);
         response.setContentType("text/html");  
         out.println("<script type=\"text/javascript\">");  
         out.println("alert('Por favor no deje espacios en blanco');");  
         out.println("</script>");
      }
 }
    
    
  public boolean existeCarnet(String pCarnet,HttpServletRequest request ){
    conexion = Conexion.conectarMySQL();
     String sql = ("select * from Estudiante where carnet = '" +request.getParameter("carnetEstudiante") + "'");
    try {
      ps = (com.mysql.jdbc.PreparedStatement)conexion.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      if(rs.next()){
        if ( rs != null){
          return true;
        }
      }  
    } catch (SQLException ex) {
      Logger.getLogger(ControladorAgregarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
    }return false;
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