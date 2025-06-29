package controllerAdmin;

import dalAdmin.BuildPCAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import models.Brands;
import models.BuildPCAdmin;
import models.BuildPCView;
import models.Categories;

public class BuildPC_ListCate extends HttpServlet {

    BuildPCAdminDAO dao = new BuildPCAdminDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        boolean ajax = "true".equalsIgnoreCase(request.getParameter("ajax"));

        if ("list".equals(service)) {
            List<BuildPCView> buildPCList = dao.getBuildPCSummaryView();
            request.setAttribute("buildPCList", buildPCList);
            request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/BuildPCList.jsp").forward(request, response);
            return;
        }

        if ("filter".equals(service)) {
            String componentID = request.getParameter("componentID");
            String keyword = request.getParameter("keyword");
            String brand_raw = request.getParameter("brand");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            String page = request.getParameter("page");
            int pageSize = 5;

            try {
                int comID = safeParseInt(componentID, -1);
                String key = keyword != null ? keyword.trim() : "";
                String brand = brand_raw != null ? brand_raw.trim() : "";
                int min = safeParseInt(minPrice, -1);
                int max = safeParseInt(maxPrice, Integer.MAX_VALUE);
                int pages = safeParseInt(page, 1);
                int start = (pages - 1) * pageSize;

                if (comID == -1) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "componentID không hợp lệ");
                    return;
                }

                int total = dao.countFilteredByComponent(comID, brand, min, max, key);
                int totalPages = (int) Math.ceil((double) total / pageSize);
                List<Categories> listFiltered = dao.getCategoriesFiltered2(comID, brand, key, min, max, start, pageSize);
                List<Brands> brands = dao.getBrandsByComponent(comID);

                request.setAttribute("products", listFiltered);
                request.setAttribute("brands", brands);
                request.setAttribute("componentID", componentID);
                request.setAttribute("currentPage", pages);
                request.setAttribute("totalPages", totalPages);

                if (ajax) {
                    request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/BuildPC_Cate_List.jsp").forward(request, response);
                } else {
                    response.sendRedirect("BuildPC.html");
                }
                return;

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý filter");
            }
        }

        if ("loadBuildPC".equals(service)) {
            int buildPCID = safeParseInt(request.getParameter("buildPCID"), -1);
            if (buildPCID == -1) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid BuildPCID");
                return;
            }

            try {
                List<BuildPCAdmin> items = dao.getBuildPCItemsByBuildPCID(buildPCID);
                response.setContentType("text/plain;charset=UTF-8");
                PrintWriter out = response.getWriter();

                for (BuildPCAdmin item : items) {
                    String line = item.getComponentID() + "|" + item.getCateID() + "|" + escape(item.getCateName()) + "|"
                            + escape(item.getBrandName()) + "|" + item.getPrice() + "|" + escape(item.getImgURL());
                    out.println(line);
                }
                out.flush();

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading BuildPC");
            }
            return;
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

        String service = request.getParameter("service");
        HttpSession session = request.getSession();
        models.User user = (models.User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/ComputerOnlineShop/Login");
            return;
        }

        if ("insert".equals(service)) {
            handleInsert(request, response, user);
            return;
        }

        if ("update".equals(service)) {
            handleUpdate(request, response, user);
            return;
        }

        if ("delete".equals(service)) {
            handleDelete(request, response);
            return;
        }

        processRequest(request, response);
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response, models.User user) throws IOException {
        try {
            String idsRaw = request.getParameter("categoryIDs");

            if (idsRaw == null || idsRaw.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu thông tin bắt buộc.");
                return;
            }

            String[] parts = idsRaw.split(",");
            if (parts.length != 6) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phải chọn đủ 6 linh kiện.");
                return;
            }

            List<Integer> categoryIDs = new ArrayList<>();
            for (String part : parts) {
                categoryIDs.add(Integer.parseInt(part.trim()));
            }

            String role = user.getRole() != null ? user.getRole().getRoleName() : "Customer";
            int userID = user.getUserId();

            if ("Admin".equalsIgnoreCase(role) && dao.isDuplicateBuildPC(categoryIDs, -1)) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Build PC với cấu hình này đã tồn tại.");
                return;
            }

            boolean success = dao.insertBuildPC(categoryIDs, userID);
            response.getWriter().write(success ? "OK" : "Thêm Build PC thất bại.");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống.");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response, models.User user) throws IOException {
        try {
            int buildPCID = safeParseInt(request.getParameter("buildPCID"), -1);
            String idsRaw = request.getParameter("categoryIDs");
            int newStatus = safeParseInt(request.getParameter("status"), 0);

            if (buildPCID == -1 || idsRaw == null || idsRaw.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu dữ liệu bắt buộc.");
                return;
            }

            String[] parts = idsRaw.split(",");
            if (parts.length != 6) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phải chọn đủ 6 linh kiện.");
                return;
            }

            List<Integer> categoryIDs = new ArrayList<>();
            for (String part : parts) {
                categoryIDs.add(Integer.parseInt(part.trim()));
            }

            String role = user.getRole() != null ? user.getRole().getRoleName() : "Customer";

            if (dao.isDuplicateBuildPC(categoryIDs, buildPCID) && !"Customer".equalsIgnoreCase(role)) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Build PC đã tồn tại.");
                return;
            }

            boolean success = dao.updateBuildPC(buildPCID, categoryIDs, newStatus, role);
            if (success) {
                response.getWriter().write("Cập nhật thành công.");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cập nhật thất bại hoặc dữ liệu không hợp lệ.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống.");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int buildPCID = safeParseInt(request.getParameter("buildPCID"), -1);
            if (buildPCID == -1) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid BuildPCID.");
                return;
            }

            boolean success = dao.deleteBuildPC(buildPCID);
            response.getWriter().write(success ? "Build PC deleted successfully." : "Deletion failed.");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while deleting.");
        }
    }

    private int safeParseInt(String value, int defaultVal) {
        if (value == null) {
            return defaultVal;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultVal;
        }
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ").replace("\r", " ");
    }

    @Override
    public String getServletInfo() {
        return "BuildPC List and Management Controller";
    }
}
