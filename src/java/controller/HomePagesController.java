package controller;

import dal.CategoryDAO;
import models.BrandByCategoriesName;
import models.Categories;
import models.Products;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePagesController", urlPatterns = {"/HomePages"})
public class HomePagesController extends HttpServlet {

    private static final int PAGE_SIZE = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int pagePC = parsePageParam(request.getParameter("pagePC"));
        int pageLaptop = parsePageParam(request.getParameter("pageLaptop"));

        int startPC = (pagePC - 1) * PAGE_SIZE;
        int startLaptop = (pageLaptop - 1) * PAGE_SIZE;

        CategoryDAO dao = new CategoryDAO();

        //take catagories
        List<Categories> categories = dao.getCategoriesName();
        // take brand
        List<BrandByCategoriesName> brandList = dao.getBrandWithCategoryName();

        // PC
        List<Products> pcProducts = dao.GetCataByCategory(1, startPC, PAGE_SIZE);
        int totalPC = dao.countTotalProducts(1);
        int totalPagesPC = (int) Math.ceil(totalPC * 1.0 / PAGE_SIZE);

        // Laptop
        List<Products> laptopProducts = dao.GetCataByCategory(2, startLaptop, PAGE_SIZE);
        int totalLaptop = dao.countTotalProducts(2);
        int totalPagesLaptop = (int) Math.ceil(totalLaptop * 1.0 / PAGE_SIZE);

        // sent to PC
        request.setAttribute("categories", categories);
        request.setAttribute("BrandWithCategoryName", brandList);

        request.setAttribute("pcProducts", pcProducts);
        request.setAttribute("totalPagesPC", totalPagesPC);
        request.setAttribute("currentPagePC", pagePC);

        request.setAttribute("laptopProducts", laptopProducts);
        request.setAttribute("totalPagesLaptop", totalPagesLaptop);
        request.setAttribute("currentPageLaptop", pageLaptop);

        request.getRequestDispatcher("/ShopPages/Pages/homepages.jsp").forward(request, response);
    }

    private int parsePageParam(String param) {
        try {
            return (param != null) ? Integer.parseInt(param) : 1;
        } catch (NumberFormatException e) {
            return 1;
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
        return "Home page controller with category-based pagination";
    }
}
