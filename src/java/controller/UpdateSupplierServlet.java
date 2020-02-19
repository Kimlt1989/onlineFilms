/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dao.SupplierDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;
import model.Categories;
import model.Suppliers;

/**
 *
 * @author KIM
 */
public class UpdateSupplierServlet extends HttpServlet {

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
         MultipartFormDataRequest mrequest = null;
        try {
           mrequest = this.createMultipartFormDataRequest(request);
            String bsubmit = mrequest.getParameter("bsubmit");
            if (bsubmit == null) {
                bsubmit = "";
            } else {
                Hashtable files = mrequest.getFiles();
//                if (!files.isEmpty()) {
                    int oldSupId = Integer.parseInt(mrequest.getParameter("oldSupId"));
                    SupplierDAO supDAO = new SupplierDAO();
                    Suppliers oldGame = supDAO.findExactlySupplier(oldSupId);
//                    String appPath = request.getRealPath("/images/Suppliers");
//                    String oldFileUrl1 = appPath + "/" + oldGame.getCat_image();
//                    File oldFile1 = new File(oldFileUrl1);
//                    oldFile1.delete();

//                    UploadBean uploadBean = new UploadBean();
//                    uploadBean.setFolderstore(appPath);
//                    uploadBean.store(mrequest, "upload1");
//                    UploadFile file1 = (UploadFile) files.get("upload1");
//                    String image = file1.getFileName();

                    String name = mrequest.getParameter("name");
                    String address = mrequest.getParameter("address");
                    Suppliers suppliers = new Suppliers(name, address);
                    boolean result = supDAO.updateSupplier(name,address, mrequest.getParameter("oldSupId"));
                    if (result) {
                        out.write("<script type='text/javascript'>\n");
                        out.write("alert('Done');\n");
                        out.write("setTimeout(function(){window.location.href='adminManageSuppliers.jsp'},1000);");
                        out.write("</script>\n");
                    } else {
                        out.write("<script type='text/javascript'>\n");
                        out.write("alert('Done');\n");
                        out.write("setTimeout(function(){window.location.href='adminManageSuppliers.jsp'},1000);");
                        out.write("</script>\n");
                    }
//                }
            }
        } catch (Exception e) {
            System.out.println(e);
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
    
    
    private MultipartFormDataRequest createMultipartFormDataRequest(HttpServletRequest request)
            throws UploadException, IOException {
        if (MultipartFormDataRequest.isMultipartFormData(request)) {
            return new MultipartFormDataRequest(request);
        }
        return null;
    }
}
