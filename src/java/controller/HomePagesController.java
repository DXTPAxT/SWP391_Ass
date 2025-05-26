/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.BrandByCategoriesName;
import models.Categories;
import models.Products;

/**
 *
 * @author PC
 */
@WebServlet(name="HomePagesController", urlPatterns={"/HomePages"})
public class HomePagesController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // how many product in one page
        final int PAGE_SIZE = 3; 
        int page = 1;

        String pageParam = request.getParameter("page");
    if (pageParam != null) {
        try {
            page = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            page = 1;
        }
    }

    int start = (page - 1) * PAGE_SIZE;

    CategoryDAO dao = new CategoryDAO();
    // take categories List
    List<Categories> Categories= dao.getCategoriesName();
    // take brand
    List<BrandByCategoriesName> BWCN = dao.getBrandWithCategoryName();
    // take PC
    List<Products> pcProducts = dao.GetCata2(start, PAGE_SIZE);
    int totalPC = dao.countTotalProducts();
    int totalPagesPC = (int) Math.ceil(totalPC * 1.0 / PAGE_SIZE);

    // take Laptop 
    List<Products> laptopProducts = dao.GetCata3(start, PAGE_SIZE);
    int totalLaptop = dao.countTotalProducts();
    int totalPagesLaptop = (int) Math.ceil(totalLaptop * 1.0 / PAGE_SIZE);

    // sent to jsp
    request.setAttribute("categories",Categories );
    request.setAttribute("BrandWithCategoryName", BWCN);
    request.setAttribute("pcProducts", pcProducts);
    request.setAttribute("totalPagesPC", totalPagesPC);

    request.setAttribute("laptopProducts", laptopProducts);
    request.setAttribute("totalPagesLaptop", totalPagesLaptop);

    request.setAttribute("currentPage", page);

    request.getRequestDispatcher("/ShopPages/Pages/homepages.jsp").forward(request, response);
            } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
