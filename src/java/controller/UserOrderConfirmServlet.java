/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDetailDAO;
import dao.UserOrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrderDetail;
import model.UserOrder;

/**
 *
 * @author KIM
 */
public class UserOrderConfirmServlet extends HttpServlet {

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
              String confirmId = request.getParameter("confirm");
           if(confirmId != null){
               String[] list = confirmId.split("/");
               double price = Double.parseDouble(list[1]);
               UserOrderDAO userOrderDAO = new UserOrderDAO();
               int intConfirmId = Integer.parseInt(list[0]);
               UserOrder userOrder = userOrderDAO.findExactlyUserOrder(intConfirmId);
               userOrder.setConfirm(1);
               userOrderDAO.update(userOrder, list[0]);
               OrderDetail orderDetail = new OrderDetail(1, price, intConfirmId);
               OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
               orderDetailDAO.addNewOrderDetail(orderDetail);
               request.setAttribute("orderDetail", orderDetail);
               request.setAttribute("userOrder", userOrder);
               request.getRequestDispatcher("printReceipt.jsp").forward(request, response);
           }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
