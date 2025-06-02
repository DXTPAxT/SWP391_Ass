package controller;

import dal.CategoriesDAO;
import models.BrandByComponentName;
import models.Brands;
import models.Categories;
import models.Components;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePagesController", urlPatterns = {"/HomePages"})
public class HomePagesController extends HttpServlet {

    private static final int PAGE_SIZE = 6;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int pagePC = parsePageParam(request.getParameter("pagePC"));
        int pageLaptop = parsePageParam(request.getParameter("pageLaptop"));

        int startPC = (pagePC - 1) * PAGE_SIZE;
        int startLaptop = (pageLaptop - 1) * PAGE_SIZE;

        CategoriesDAO dao = new CategoriesDAO();

        List<Components> components = dao.GetAllComponents();
        List<BrandByComponentName> brandComponentList = dao.getBrandInSiteComponents();
        List<Brands> listBrand = dao.getBrands();

        List<Categories> pcProducts = dao.getCategoriesByComponentName(1, startPC, PAGE_SIZE);
        int totalPC = dao.countTotalProducts(1);
        int totalPagesPC = (int) Math.ceil((double) totalPC / PAGE_SIZE);

        List<Categories> laptopProducts = dao.getCategoriesByComponentName(2, startLaptop, PAGE_SIZE);
        int totalLaptop = dao.countTotalProducts(2);
        int totalPagesLaptop = (int) Math.ceil((double) totalLaptop / PAGE_SIZE);

        request.setAttribute("Components", components);
        request.setAttribute("BrandWithComponent", brandComponentList);
        request.setAttribute("listBrand", listBrand);

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
