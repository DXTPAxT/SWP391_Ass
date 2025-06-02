package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.BrandByComponentName;
import models.Brands;
import models.Categories;
import models.Components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        CategoriesDAO dao = new CategoriesDAO();

        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        List<Categories> list = new ArrayList<>();

        String componentName = request.getParameter("component");
        String brandName = request.getParameter("brand");
        String minStr = request.getParameter("minPrice");
        String maxStr = request.getParameter("maxPrice");
        Integer minPrice = null, maxPrice = null;

        if ("list".equals(service)) {
            list = dao.getAllCategories();
        } else if ("filter".equals(service)) {
            try {
                if (minStr != null && !minStr.isEmpty()) {
                    minPrice = Integer.parseInt(minStr);
                }
                if (maxStr != null && !maxStr.isEmpty()) {
                    maxPrice = Integer.parseInt(maxStr);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }    

            list = dao.getCategoriesFiltered(componentName, brandName, minPrice, maxPrice);

            // Truyền lại để giữ giá trị đã lọc trong UI
            request.setAttribute("currentComponent", componentName);
            request.setAttribute("currentBrand", brandName);
            request.setAttribute("minPrice", minStr);
            request.setAttribute("maxPrice", maxStr);
            request.setAttribute("currentService", "filter");
        }

        // Dữ liệu danh mục sản phẩm
        request.setAttribute("data", list);

        // Dữ liệu sidebar
        List<Components> components = dao.GetAllComponents();
        List<BrandByComponentName> brandComponentList = dao.getBrandInSiteComponents();
        List<Brands> listBrand = dao.getBrands();

        request.setAttribute("Components", components);
        request.setAttribute("BrandWithComponent", brandComponentList);
        request.setAttribute("listBrand", listBrand);

        request.getRequestDispatcher("ShopPages/Pages/ProductList.jsp").forward(request, response);
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
        return "Handles filtering product categories by component, brand, price.";
    }
}
