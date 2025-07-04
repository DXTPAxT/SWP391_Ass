/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
<<<<<<<< HEAD:src/java/controllerAdmin/SaleEventsServlet.java
package controllerAdmin;

import dal.Blog_CateDAO;
import dal.CategoriesDAO;
========
package controller.cart;

import dal.CartItemDAO;
>>>>>>>> 71f394f28186d560ddb9462dffd6c06e467f07d6:src/java/controller/cart/DeleteSelectedCartItems.java
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Categories;
import models.Post;
import models.SaleEvents;

/**
 *
 * @author PC ASUS
 */
<<<<<<<< HEAD:src/java/controllerAdmin/SaleEventsServlet.java
@WebServlet(name = "SaleEventsServlet", urlPatterns = {"/saleevents"})
public class SaleEventsServlet extends HttpServlet {
========
public class DeleteSelectedCartItems extends HttpServlet {
>>>>>>>> 71f394f28186d560ddb9462dffd6c06e467f07d6:src/java/controller/cart/DeleteSelectedCartItems.java

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
<<<<<<<< HEAD:src/java/controllerAdmin/SaleEventsServlet.java
            out.println("<title>Servlet SaleEventsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleEventsServlet at " + request.getContextPath() + "</h1>");
========
            out.println("<title>Servlet DeleteSelectedCartItems</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteSelectedCartItems at " + request.getContextPath() + "</h1>");
>>>>>>>> 71f394f28186d560ddb9462dffd6c06e467f07d6:src/java/controller/cart/DeleteSelectedCartItems.java
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
<<<<<<<< HEAD:src/java/controllerAdmin/SaleEventsServlet.java
        String categoryIdParam = request.getParameter("categoryID");
        int categoryID = (categoryIdParam != null && !categoryIdParam.isEmpty()) ? Integer.parseInt(categoryIdParam) : 1;

        Blog_CateDAO dao = new Blog_CateDAO();
        CategoriesDAO daoc = new CategoriesDAO();
        List<SaleEvents> saleEvents = dao.getSaleEventsByCategory(categoryID);

        List<Post> activePosts = dao.getPostsByStatus(1);

        List<Categories> categories = daoc.getAllCategoriesPaginated(1, Integer.MAX_VALUE);

        request.setAttribute("saleEvents", saleEvents);
        request.setAttribute("activePosts", activePosts);
        request.setAttribute("categories", categories);
        request.setAttribute("selectedCategoryID", categoryID);

        // Forward to JSP
        request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/saleEvents.jsp").forward(request, response);
    }

========
        processRequest(request, response);
    }

>>>>>>>> 71f394f28186d560ddb9462dffd6c06e467f07d6:src/java/controller/cart/DeleteSelectedCartItems.java
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
<<<<<<<< HEAD:src/java/controllerAdmin/SaleEventsServlet.java
//        processRequest(request, response);
========
        try {
            String selectedItems = request.getParameter("selectedItems");
            if (selectedItems == null || selectedItems.isEmpty()) {
                response.getWriter().write("fail");
                return;
            }

            String[] ids = selectedItems.split(",");
            CartItemDAO dao = new CartItemDAO();
            boolean allDeleted = true;

            for (String idStr : ids) {
                int id = Integer.parseInt(idStr.trim());
                boolean deleted = dao.deleteCartItem(id);
                if (!deleted) {
                    allDeleted = false;
                    break; // dừng ngay nếu 1 cái fail
                }
            }

            response.getWriter().write(allDeleted ? "success" : "fail");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
>>>>>>>> 71f394f28186d560ddb9462dffd6c06e467f07d6:src/java/controller/cart/DeleteSelectedCartItems.java
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
