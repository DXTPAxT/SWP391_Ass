/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.ComponentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Categories;
import models.Components;

/**
 *
 * @author Admin
 */
public class ComAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");

            if (service == null) {
                service = "list";
            }
            ComponentDAO dao = new ComponentDAO();
            dao.updateAllComponentQuantities();

            if (service.equals("list")) {
                String keyword = request.getParameter("search");                
                List<Components> components;
                if (keyword != null && !keyword.trim().isEmpty()) {
                    // Giả sử bạn có hàm getComponentByName(String name) trong DAO
                    components = dao.getAllComponent("SELECT * FROM Components WHERE ComponentName LIKE '" + keyword + "' ");
                } else {
                    components = dao.getAllComponent("SELECT * FROM Components");
                }

                request.setAttribute("data", components);
                request.setAttribute("search", keyword); // để hiển thị lại keyword trên input nếu cần
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewComponent.jsp").forward(request, response);
            } else if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int componentID = Integer.parseInt(request.getParameter("componentID"));
                    Components component = dao.searchComponentByID(componentID);
                    List<Components> components = dao.getAllComponent("SELECT * FROM Components");
                    request.setAttribute("data", components);
                    request.setAttribute("component", component);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateComponent.jsp").forward(request, response);
                } else {
                    int componentID = Integer.parseInt(request.getParameter("component_id"));
                    String componentName = request.getParameter("component_name");
                    int quantity = 0;
                    int status = Integer.parseInt(request.getParameter("status"));

                    Components component = new Components(componentID, componentName, quantity, status);
                    dao.updateComponent(component);

                    response.sendRedirect(request.getContextPath() + "/ComAdmin?service=list");
                }
            } else if (service.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Components> components = dao.getAllComponent("SELECT * FROM Components");
                    request.setAttribute("data", components);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertComponent.jsp").forward(request, response);
                } else {

                    String name = request.getParameter("component_name");
                    int Quantity = 0;
                    int Status = Integer.parseInt(request.getParameter("status"));

                    Components component = new Components(); // Giả sử có constructor rỗng
                    component.setComponentName(name);
                    component.setQuantity(Quantity);
                    component.setStatus(Status);

                    dao.insertComponent(component);

                    response.sendRedirect(request.getContextPath() + "/ComAdmin?service=list");
                }
            } else if (service.equals("changestatus")) {
                int componentID = Integer.parseInt(request.getParameter("componentID"));

                // Lấy component hiện tại
                Components com = dao.searchComponentByID(componentID);
                if (com != null) {
                    // Đảo ngược trạng thái: nếu đang là 1 thì đổi thành 0, ngược lại
                    int newStatus = (com.getStatus() == 1) ? 0 : 1;
                    dao.updateStatus(componentID, newStatus);
                }
                // Quay lại trang danh sách
                response.sendRedirect(request.getContextPath() + "/ComAdmin?service=list");
            }

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
        processRequest(request, response);
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
