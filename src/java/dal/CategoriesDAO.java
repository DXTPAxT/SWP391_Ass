    package dal;

    import java.sql.*;
    import java.util.*;
    import java.util.logging.*;
    import models.BrandByComponentName;
    import models.Brands;
    import models.BuildPCView;
    import models.Categories;
    import models.Components;

    public class CategoriesDAO extends DBContext {

        private static final Logger LOGGER = Logger.getLogger(CategoriesDAO.class.getName());

        private Categories extractCategory(ResultSet rs) throws SQLException {
            Categories category = new Categories(
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName"),
                    rs.getInt("ComponentID"),
                    rs.getInt("BrandID"),
                    rs.getString("BrandName"),
                    rs.getInt("Quantity"),
                    rs.getInt("Price"),
                    rs.getString("Description"),
                    rs.getInt("Status")
            );
            try {
                category.setComponentName(rs.getString("ComponentName"));
            } catch (SQLException e) {
                // Optionally log or ignore if not present
            }
            return category;
        }

        private List<Object> buildFilter(
                StringBuilder sql,
                String componentName, String brandName,
                Integer minPrice, Integer maxPrice,
                String keyword) {

            List<Object> params = new ArrayList<>();
            if (componentName != null && !componentName.isEmpty()) {
                sql.append(" AND comp.ComponentName = ? ");
                params.add(componentName);
            }
            if (brandName != null && !brandName.isEmpty()) {
                sql.append(" AND b.BrandName = ? ");
                params.add(brandName);
            }
            if (minPrice != null) {
                sql.append(" AND c.Price >= ? ");
                params.add(minPrice);
            }
            if (maxPrice != null) {
                sql.append(" AND c.Price <= ? ");
                params.add(maxPrice);
            }
            if (keyword != null && !keyword.isEmpty()) {
                sql.append(" AND LOWER(c.CategoryName) LIKE ? ");
                params.add("%" + keyword.toLowerCase() + "%");
            }
            return params;
        }

        public List<Brands> getAllBrands() {
            String sql = "SELECT BrandID, BrandName, Quantity, Status FROM Brands";
            List<Brands> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Brands(
                            rs.getInt("BrandID"),
                            rs.getString("BrandName"),
                            rs.getInt("Quantity"),
                            rs.getInt("Status")
                    ));
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 2. Lấy danh sách brand theo component (dùng bảng BrandComs)
        public List<BrandByComponentName> getBrandsGroupedByComponent() {
            String sql = """
                SELECT DISTINCT
                  bc.ComponentID,
                  comp.ComponentName,
                  b.BrandName
                FROM BrandComs bc
                JOIN Components comp ON bc.ComponentID = comp.ComponentID
                JOIN Brands b ON bc.BrandID = b.BrandID
                """;
            List<BrandByComponentName> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new BrandByComponentName(
                            rs.getInt("ComponentID"),
                            rs.getString("ComponentName"),
                            rs.getString("BrandName")
                    ));
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 3. Lấy toàn bộ Components
        public List<Components> getAllComponents() {
            String sql = "SELECT * FROM Components";
            List<Components> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Components(
                            rs.getInt("ComponentID"),
                            rs.getString("ComponentName"),
                            rs.getInt("Quantity"),
                            rs.getInt("Status")
                    ));
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 4. Lấy category theo ID (kèm thông tin brand & component)
        public List<Categories> getCategoryByID(int id) {
            String sql = """
                SELECT
                  c.*,
                  bc.ComponentID,
                  bc.BrandID,
                  b.BrandName,
                  comp.ComponentName
                FROM Categories c
                JOIN BrandComs bc ON c.BrandComID   = bc.BrandComID
                JOIN Brands b ON bc.BrandID = b.BrandID
                JOIN Components comp ON bc.ComponentID = comp.ComponentID
                WHERE c.CategoryID = ?
                """;
            List<Categories> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractCategory(rs));
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 5. Phân trang theo component
        public List<Categories> getCategoriesByComponent(int componentId, int start, int size) {
            String sql = """
                SELECT
                  c.*,
                  bc.ComponentID,
                  bc.BrandID,
                  b.BrandName,
                  comp.ComponentName
                FROM Categories c
                JOIN BrandComs bc ON c.BrandComID   = bc.BrandComID
                JOIN Brands b ON bc.BrandID = b.BrandID
                JOIN Components comp ON bc.ComponentID = comp.ComponentID
                WHERE bc.ComponentID = ? and  c.Status IN (1, 2) 
                ORDER BY c.CategoryID 
                OFFSET ? ROWS FETCH NEXT ? ROWS ONLY 
                """;
            List<Categories> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, componentId);
                ps.setInt(2, start);
                ps.setInt(3, size);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractCategory(rs));
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 6. Phân trang tất cả categories
        public List<Categories> getAllCategoriesPaginated(int page, int size) {
            String sql = """
                SELECT
                  c.*,
                  bc.ComponentID,
                  bc.BrandID,
                  b.BrandName,
                  comp.ComponentName
                FROM Categories c
                JOIN BrandComs bc ON c.BrandComID   = bc.BrandComID
                JOIN Brands b ON bc.BrandID  = b.BrandID
                JOIN Components comp ON bc.ComponentID = comp.ComponentID
                ORDER BY c.CategoryID
                OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                """;
            List<Categories> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, (page - 1) * size);
                ps.setInt(2, size);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractCategory(rs));
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 7. Đếm tổng số products theo component
        public int countTotalProducts(int componentId) {
            String sql = """
                SELECT COUNT(*)
                FROM Categories c
                JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
                WHERE bc.ComponentID = ?
                """;
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, componentId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return 0;
        }

        // 8. Đếm tất cả categories
        public int countAllCategories() {
            String sql = "SELECT COUNT(*) FROM Categories";
            try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return 0;
        }

        // 9. Lọc nâng cao với phân trang
        public List<Categories> getCategoriesFiltered(
                String componentName, String brandName,
                Integer minPrice, Integer maxPrice,
                String keyword, int start, int size) {

            StringBuilder sql = new StringBuilder("""
                SELECT
                  c.*,
                  bc.ComponentID,
                  bc.BrandID,
                  b.BrandName,
                  comp.ComponentName
                FROM Categories c
                JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
                JOIN Brands b ON bc.BrandID= b.BrandID
                JOIN Components comp  ON bc.ComponentID = comp.ComponentID
                WHERE c.Status IN (1, 2)
                """);
            List<Object> params = buildFilter(sql, componentName, brandName, minPrice, maxPrice, keyword);
            sql.append(" ORDER BY c.CategoryID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            params.add(start);
            params.add(size);

            List<Categories> list = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
                setParams(ps, params);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractCategory(rs));
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        // 10. Đếm kết quả lọc
        public int countFiltered(
                String componentName, String brandName,
                Integer minPrice, Integer maxPrice,
                String keyword) {

            StringBuilder sql = new StringBuilder("""
                SELECT COUNT(*)
                FROM Categories c
                JOIN BrandComs bc ON c.BrandComID= bc.BrandComID
                JOIN Brands b ON bc.BrandID = b.BrandID
                JOIN Components comp ON bc.ComponentID = comp.ComponentID
                WHERE 1=1
                """);
            List<Object> params = buildFilter(sql, componentName, brandName, minPrice, maxPrice, keyword);

            try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
                setParams(ps, params);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return 0;
        }

        private void setParams(PreparedStatement ps, List<Object> params) throws SQLException {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
        }

        // BuilDPC_ListCate
        public List<Categories> getCategoriesFiltered2(
                int componentID, String brand, String keyword,
                int minPrice, int maxPrice, int start, int size) {

            List<Categories> list = new ArrayList<>();
            StringBuilder sql = new StringBuilder("""
            SELECT 
              c.*, 
              bc.ComponentID, 
              bc.BrandID, 
              b.BrandName, 
              comp.ComponentID
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
            JOIN Brands b ON bc.BrandID = b.BrandID
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            WHERE  bc.ComponentID = ?
        """);

            List<Object> params = new ArrayList<>();
            params.add(componentID);

            if (brand != null && !brand.trim().isEmpty()) {
                sql.append(" AND b.BrandName = ? ");
                params.add(brand.trim());
            }
            if (keyword != null && !keyword.trim().isEmpty()) {
                sql.append(" AND LOWER(c.CategoryName) LIKE ? ");
                params.add("%" + keyword.trim().toLowerCase() + "%");
            }
            if (minPrice >= 0) {
                sql.append(" AND c.Price >= ? ");
                params.add(minPrice);
            }
            if (maxPrice < Integer.MAX_VALUE) {
                sql.append(" AND c.Price <= ? ");
                params.add(maxPrice);
            }

            sql.append(" ORDER BY c.CategoryID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            params.add(start);
            params.add(size);

            try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractCategory(rs));
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }

            return list;
        }

        public int countFilteredByComponent(int componentID, String brand, int minPrice, int maxPrice, String keyword) {
            StringBuilder sql = new StringBuilder("""
            SELECT COUNT(*)
            FROM Categories c
            JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
            JOIN Brands b ON bc.BrandID = b.BrandID
            WHERE bc.ComponentID = ? AND c.Status IN (1, 2)
        """);
            List<Object> params = new ArrayList<>();
            params.add(componentID);

            if (brand != null && !brand.isEmpty()) {
                sql.append(" AND b.BrandName = ?");
                params.add(brand);
            }
            if (minPrice >= 0) {
                sql.append(" AND c.Price >= ?");
                params.add(minPrice);
            }
            if (maxPrice < Integer.MAX_VALUE) {
                sql.append(" AND c.Price <= ?");
                params.add(maxPrice);
            }
            if (keyword != null && !keyword.isEmpty()) {
                sql.append(" AND LOWER(c.CategoryName) LIKE ?");
                params.add("%" + keyword.toLowerCase() + "%");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return 0;
        }

        public List<Brands> getBrandsByComponent(int componentID) {
            List<Brands> list = new ArrayList<>();
            String sql = """
            SELECT DISTINCT b.BrandID, b.BrandName, b.Quantity, b.Status
            FROM BrandComs bc
            JOIN Brands b ON bc.BrandID = b.BrandID
            WHERE bc.ComponentID = ?
        """;
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, componentID);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(new Brands(
                                rs.getInt("BrandID"),
                                rs.getString("BrandName"),
                                rs.getInt("Quantity"),
                                rs.getInt("Status")
                        ));
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            return list;
        }

        public List<BuildPCView> getBuiltPCsForCustomer() {
            List<BuildPCView> list = new ArrayList<>();
            String sql = """
            SELECT 
                bp.BuildPCID,
                MAX(CASE WHEN bc.ComponentID = 2 THEN c.CategoryName END) AS MainBoard,
                MAX(CASE WHEN bc.ComponentID = 3 THEN c.CategoryName END) AS CPU,
                MAX(CASE WHEN bc.ComponentID = 4 THEN c.CategoryName END) AS GPU,
                MAX(CASE WHEN bc.ComponentID = 5 THEN c.CategoryName END) AS RAM,
                MAX(CASE WHEN bc.ComponentID = 6 THEN c.CategoryName END) AS SSD,
                MAX(CASE WHEN bc.ComponentID = 7 THEN c.CategoryName END) AS PCCase,
                SUM(bpi.Price) AS Price,
                MAX(bp.Status) AS Status,
                u.UserID,
                u.FullName,
                r.RoleName
            FROM Build_PC bp
            JOIN Build_PC_Items bpi ON bp.BuildPCID = bpi.BuildPCID
            JOIN Categories c ON bpi.CategoryID = c.CategoryID
            JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
            JOIN Users u ON bp.UserID = u.UserID
            JOIN Roles r ON u.RoleID = r.RoleID
            WHERE bc.ComponentID BETWEEN 2 AND 7
             AND r.RoleID = 1
            GROUP BY bp.BuildPCID, u.UserID, u.FullName, r.RoleName
            ORDER BY bp.BuildPCID
            """;

            try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BuildPCView b = new BuildPCView();
                    b.setBuildPCID(rs.getInt("BuildPCID"));
                    b.setMainBoard(rs.getString("MainBoard"));
                    b.setCpu(rs.getString("CPU"));
                    b.setGpu(rs.getString("GPU"));
                    b.setRam(rs.getString("RAM"));
                    b.setSsd(rs.getString("SSD"));
                    b.setPcCase(rs.getString("PCCase"));
                    b.setPrice(rs.getInt("Price"));
                    b.setStatus(rs.getInt("Status"));
                    b.setUserID(rs.getInt("UserID"));
                    b.setFullName(rs.getString("FullName"));
                    b.setRole(rs.getString("RoleName"));
                    list.add(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        public List<Categories> getCategoriesInBuildPC(int buildPCID) {
            List<Categories> list = new ArrayList<>();
            String sql = """
            SELECT c.CategoryID, c.CategoryName, b.BrandName, c.Price, c.ImageURL, comp.ComponentID
            FROM Build_PC_Items bi
            JOIN Categories c ON bi.CategoryID = c.CategoryID
            JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
            JOIN Brands b ON bc.BrandID = b.BrandID
            JOIN Components comp ON bc.ComponentID = comp.ComponentID
            WHERE bi.BuildPCID = ?
        """;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, buildPCID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Categories c = new Categories();
                    c.setCategoryID(rs.getInt("CategoryID"));
                    c.setCategoryName(rs.getString("CategoryName"));
                    c.setBrandName(rs.getString("BrandName"));
                    c.setPrice(rs.getInt("Price"));
                    c.setImgURL(rs.getString("ImageURL"));
                    c.setComponentID(rs.getInt("ComponentID"));
                    list.add(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

  public boolean insertBuildPCToCart(List<Integer> categoryIDs, int userID) {
    if (categoryIDs == null || categoryIDs.size() != 6) {
        System.out.println("⚠️ Chưa đủ 6 linh kiện.");
        return false;
    }

    String insertCartSQL = """
        INSERT INTO Cart_Build_PC (UserID, Price, Status)
        VALUES (?, ?, 1)
    """;

    String insertItemSQL = """
        INSERT INTO Cart_Build_PC_Items (CartBuildPCID, CategoryID, Price, WarrantyDetailID, Status)
        VALUES (?, ?, ?, ?, 1)
    """;

    String getInfoSQL = """
        SELECT TOP 1
            c.Price AS ProductPrice,
            wd.WarrantyDetailID,
            wd.Status AS WarrantyStatus,
            wd.Price AS WarrantyPrice
        FROM Categories c
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        JOIN WarrantyDetails wd ON bc.BrandComID = wd.BrandComID
        WHERE c.CategoryID = ?
        ORDER BY wd.Price ASC
    """;

    try {
        connection.setAutoCommit(false);

        int totalPrice = 0;
        List<int[]> itemData = new ArrayList<>(); // [CategoryID, ItemTotal, WarrantyID]

        PreparedStatement psInfo = connection.prepareStatement(getInfoSQL);

        for (int categoryID : categoryIDs) {
            psInfo.setInt(1, categoryID);

            try (ResultSet rs = psInfo.executeQuery()) {
                if (rs.next()) {
                    int productPrice = rs.getInt("ProductPrice");
                    int warrantyID = rs.getInt("WarrantyDetailID");
                    int warrantyPrice = rs.getInt("WarrantyPrice");
                    int warrantyStatus = rs.getInt("WarrantyStatus");

                    int actualWarrantyPrice = (warrantyStatus == 1) ? warrantyPrice : 0;
                    int itemTotal = productPrice + actualWarrantyPrice;
                    totalPrice += itemTotal;

                    itemData.add(new int[]{categoryID, itemTotal, warrantyID});
                } else {
                    System.out.println("❌ Không tìm thấy dữ liệu cho CategoryID: " + categoryID);
                    connection.rollback();
                    return false;
                }
            }
        }

        PreparedStatement psCart = connection.prepareStatement(insertCartSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        psCart.setInt(1, userID);
        psCart.setInt(2, totalPrice);
        psCart.executeUpdate();

        ResultSet rsCart = psCart.getGeneratedKeys();
        if (!rsCart.next()) {
            System.out.println("❌ Không lấy được CartBuildPCID.");
            connection.rollback();
            return false;
        }
        int cartBuildPCID = rsCart.getInt(1);

        PreparedStatement psItem = connection.prepareStatement(insertItemSQL);
        for (int[] item : itemData) {
            psItem.setInt(1, cartBuildPCID);
            psItem.setInt(2, item[0]);
            psItem.setInt(3, item[1]);
            psItem.setInt(4, item[2]);
            psItem.addBatch();
        }
        psItem.executeBatch();

        connection.commit();
        System.out.println("✅ Thêm Build PC vào giỏ hàng thành công.");
        return true;

    } catch (Exception e) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return false;
}

    }
