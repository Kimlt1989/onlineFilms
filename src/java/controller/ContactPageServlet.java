/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;
import testingmail.Mail1;


public class ContactPageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String message = "";
            String isSubmit = request.getParameter("submitContact");
            if (isSubmit == null) {
                response.sendRedirect("contact.jsp");
                return;
            } else {
         
                
                 String host = "mail.saigontech.edu.vn";
                String to = "kimlt07@saigontech.edu.vn";
                String from = request.getParameter("from");
                String subject = request.getParameter("subject");
                String messageText = request.getParameter("body");
                String fromPassword = request.getParameter("fromPassword");
                Mail1 mail = new Mail1();
                String result = mail.sendEmail(from, to, subject, messageText, fromPassword);
                message +=("<BR/>" + result);
                
                message +=("<BR/>Mail was sent to: " + to);
                message +=("<BR/>From email: " + from);
                message += ("<BR/>Using host: " + host + "."+"<BR/>");
                message += " Thank you! Your mail will reply soon!";
                request.setAttribute("message", message);
            request.getRequestDispatcher("contact.jsp").forward(request, response);
//                out.write("<script type='text/javascript'>\n");
//                out.write("alert(' Thank you! Your mail will reply soon! ");
//                out.write("setTimeout(function(){window.location.href='index.jsp'},1000);");
//                out.write("</script>\n");

            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
