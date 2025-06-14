package controllerAdmin;

import dalAdmin.BraComAdminDAO;
import dalAdmin.BrandAdminDAO;
import dalAdmin.ComponentAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.BraComs;

public class BraComAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "list";
            }
            BraComAdminDAO dao = new BraComAdminDAO();
            dao.updateBrandComQuantitiesFromCategories();
            if (service.equals("list")) {
                List<BraComs> braComList = dao.getAllBraComs();
                request.setAttribute("braCom", braComList);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewBraCom.jsp").forward(request, response);
            } else if (service.equals("listbybrand")) {
                int id = Integer.parseInt(request.getParameter("brandID"));
                List<BraComs> braComList = dao.getBraComsByBrandID(id);
                request.setAttribute("braCom", braComList);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewBraCom.jsp").forward(request, response);
            } else if (service.equals("listbycom")) {
                int id = Integer.parseInt(request.getParameter("componentID"));
                List<BraComs> braComList = dao.getBraComsByComponentID(id);
                request.setAttribute("braCom", braComList);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewBraCom.jsp").forward(request, response);
            } else if (service.equals("insert")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    BrandAdminDAO brandDAO = new BrandAdminDAO();
                    ComponentAdminDAO componentDAO = new ComponentAdminDAO();

                    request.setAttribute("brands", brandDAO.getAllBrands());
                    request.setAttribute("components", componentDAO.getAllComponent());

                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertBrandCom.jsp").forward(request, response);
                } else {
                    String brandRaw = request.getParameter("brandID");
                    String componentRaw = request.getParameter("componentID");

                    String error = null;

                    try {
                        int brandID = Integer.parseInt(brandRaw);
                        int componentID = Integer.parseInt(componentRaw);
                        int quantity = 0; 

                        
                        if (dao.existsBrandComponentPair(brandID, componentID)) {
                            error = "This brand-component combination already exists.";
                        }

                        if (error != null) {
                            BrandAdminDAO brandDAO = new BrandAdminDAO();
                            ComponentAdminDAO componentDAO = new ComponentAdminDAO();

                            request.setAttribute("brands", brandDAO.getAllBrands());
                            request.setAttribute("components", componentDAO.getAllComponent());
                            request.setAttribute("error", error);
                            request.setAttribute("brandID", brandRaw);
                            request.setAttribute("componentID", componentRaw);
                            request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertBrandCom.jsp").forward(request, response);
                            return;
                        }

                        // Nếu hợp lệ thì insert
                        BraComs newBraCom = new BraComs(brandID, componentID, quantity);
                        dao.insertBraCom(newBraCom);
                        response.sendRedirect("BraComAdmin?service=list");

                    } catch (NumberFormatException e) {
                        error = "Invalid selection.";
                        BrandAdminDAO brandDAO = new BrandAdminDAO();
                        ComponentAdminDAO componentDAO = new ComponentAdminDAO();

                        request.setAttribute("brands", brandDAO.getAllBrands());
                        request.setAttribute("components", componentDAO.getAllComponent());
                        request.setAttribute("error", error);
                        request.setAttribute("brandID", brandRaw);
                        request.setAttribute("componentID", componentRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertBrandCom.jsp").forward(request, response);
                    }
                }
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "BraCom Management Servlet";
    }
}
