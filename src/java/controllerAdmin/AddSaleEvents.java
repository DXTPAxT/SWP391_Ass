/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.Blog_CateDAO;
import dal.CategoriesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Categories;
import models.Post;
import models.SaleEvents;

/**
 *
 * @author User
 */
@WebServlet(name = "AddSaleEvents", urlPatterns = {"/addsale"})
public class AddSaleEvents extends HttpServlet {

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
            out.println("<title>Servlet AddSaleEvents</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSaleEvents at " + request.getContextPath() + "</h1>");
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
        try {
            Blog_CateDAO dao = new Blog_CateDAO();
            CategoriesDAO daoca = new CategoriesDAO();
// Lấy danh sách category (có thể dùng từ Inventory hoặc CategoriesDAO nếu cần)
            List<Categories> categories = daoca.getAllCategoriesPaginated(1, Integer.MAX_VALUE);
            // Lấy danh sách bài viết đang hoạt động
            List<Post> activePosts = dao.getPostsByStatus(1);

            // Gửi dữ liệu sang trang thêm sale
            request.setAttribute("categories", categories);
            request.setAttribute("activePosts", activePosts);
            // Chuyển sang trang form thêm sự kiện
            request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/insertSaleEvents.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi khi tải dữ liệu cho form thêm sự kiện.");
            request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/insertSaleEvents.jsp").forward(request, response);
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
//        processRequest(request, response);
        try {
            // Lấy và ép kiểu dữ liệu từ form
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            int postID = Integer.parseInt(request.getParameter("postID"));
            int createdBy = Integer.parseInt(request.getParameter("createdBy"));
            String approvedByStr = request.getParameter("approvedBy");
            Integer approvedBy = (approvedByStr != null && !approvedByStr.isEmpty()) ? Integer.parseInt(approvedByStr) : null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(request.getParameter("startDate"));
            Date endDate = sdf.parse(request.getParameter("endDate"));

            double discountPercent = Double.parseDouble(request.getParameter("discountPercent"));

            // Tạo đối tượng SaleEvents
            SaleEvents event = new SaleEvents();
            event.setCategoryID(categoryID);
            event.setPost_id(postID);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event.setDiscountPercent(discountPercent);
            event.setStatus(2);
            event.setCreatedBy(createdBy);
            event.setApprovedBy(approvedBy);

            Blog_CateDAO dao = new Blog_CateDAO();
            dao.addSaleEvent(event);

            // Chuyển hướng sau khi thêm thành công
            response.sendRedirect(request.getContextPath() + "/saleevents?categoryID=" + categoryID);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra khi thêm sự kiện khuyến mãi.");
            request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/insertSaleEvents.jsp").forward(request, response);
        }
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
