package controllerAdmin;

import dalAdmin.BuildPCAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();

                StringBuilder json = new StringBuilder();
                json.append("[");
                for (int i = 0; i < items.size(); i++) {
                    BuildPCAdmin item = items.get(i);
                    json.append("{")
                            .append("\"componentID\":").append(item.getComponentID()).append(",")
                            .append("\"categoryID\":").append(item.getCateID()).append(",")
                            .append("\"categoryName\":\"").append(escapeJson(item.getCateName())).append("\",")
                            .append("\"brandName\":\"").append(escapeJson(item.getBrandName())).append("\",")
                            .append("\"price\":").append(item.getPrice()).append(",")
                            .append("\"imgURL\":\"").append(escapeJson(item.getImgURL())).append("\"")
                            .append("}");
                    if (i < items.size() - 1) {
                        json.append(",");
                    }
                }
                json.append("]");
                out.print(json.toString());
                out.flush();

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("[]");
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

        if ("insert".equals(service)) {
            handleInsert(request, response);
            return;
        }

        if ("update".equals(service)) {
            handleUpdate(request, response);
            return;
        }

        if ("delete".equals(service)) {
            handleDelete(request, response);
            return;
        }

        processRequest(request, response);
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String idsRaw = request.getParameter("categoryIDs");
            if (idsRaw == null || idsRaw.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No components selected");
                return;
            }

            String[] parts = idsRaw.split(",");
            List<Integer> categoryIDs = new ArrayList<>();
            for (String part : parts) {
                try {
                    categoryIDs.add(Integer.parseInt(part.trim()));
                } catch (NumberFormatException ignored) {
                }
            }

            if (categoryIDs.size() != 6) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Phải chọn đủ 6 linh kiện.");
                return;
            }
            if (dao.isDuplicateBuildPC(categoryIDs, -1)) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Build PC với 6 linh kiện này đã tồn tại.");
                return;
            }

            boolean success = dao.insertBuildPC(categoryIDs);
            response.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(success ? "OK" : "Insert failed");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error while inserting");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int buildPCID = safeParseInt(request.getParameter("buildPCID"), -1);
            String idsRaw = request.getParameter("categoryIDs");

            if (buildPCID == -1 || idsRaw == null || idsRaw.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required information for update.");
                return;
            }

            String[] parts = idsRaw.split(",");
            List<Integer> categoryIDs = new ArrayList<>();
            for (String part : parts) {
                try {
                    categoryIDs.add(Integer.parseInt(part.trim()));
                } catch (NumberFormatException ignored) {
                }
            }

            if (categoryIDs.size() != 6) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Exactly 6 components must be selected for update.");
                return;
            }

            if (dao.isDuplicateBuildPC(categoryIDs, buildPCID)) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Build PC này đã tồn tại.");
                return;
            }

            boolean success = dao.updateBuildPC(buildPCID, categoryIDs);
            response.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(success ? "Build PC updated successfully." : "Update failed.");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error occurred during update.");
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
            response.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(success ? "Build PC deleted successfully." : "Deletion failed.");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error occurred while deleting.");
        }
    }

    private int safeParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "")
                .replace("\t", "\\t");
    }

    @Override
    public String getServletInfo() {
        return "BuildPC List and Management Controller";
    }
}
