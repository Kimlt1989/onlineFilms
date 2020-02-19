/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
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

import dao.GameDAO;


import model.Categories;

/**
 *
 * @author Tieuphieu
 */
public class UpdateCategoriesServlet extends HttpServlet {

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
                if (!files.isEmpty()) {
                    int oldGameId = Integer.parseInt(mrequest.getParameter("oldGameId"));
                    CategoryDAO gameDAO = new CategoryDAO();
                    Categories oldGame = gameDAO.findExactlyCategory(oldGameId);
                    String appPath = request.getRealPath("/images/Genres");
                    String oldFileUrl1 = appPath + "/" + oldGame.getCat_image();
                    File oldFile1 = new File(oldFileUrl1);
                    oldFile1.delete();

                    UploadBean uploadBean = new UploadBean();
                    uploadBean.setFolderstore(appPath);
                    uploadBean.store(mrequest, "upload1");
                    UploadFile file1 = (UploadFile) files.get("upload1");
                    String image = file1.getFileName();

                    String name = mrequest.getParameter("name");
                    String des = mrequest.getParameter("description");
                    Categories categories = new Categories(name, des, image);
                    boolean result = gameDAO.updateCategory(name,des,image, mrequest.getParameter("oldGameId"));
                    if (result) {
                        out.write("<script type='text/javascript'>\n");
                        out.write("alert('Done');\n");
                        out.write("setTimeout(function(){window.location.href='adminManageCategories.jsp'},1000);");
                        out.write("</script>\n");
                    } else {
                        out.write("<script type='text/javascript'>\n");
                        out.write("alert('Done');\n");
                        out.write("setTimeout(function(){window.location.href='adminManageCategories.jsp'},1000);");
                        out.write("</script>\n");
                    }
                }
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
