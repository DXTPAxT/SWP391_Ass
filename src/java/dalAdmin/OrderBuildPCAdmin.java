package dalAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.BuildPCAdmin;
import models.OrderCate;
import models.Products;

public class OrderBuildPCAdmin extends DBAdminContext {

    // 1. Lấy danh sách đơn hàng Build PC theo trạng thái
    public List<OrderCate> getOrdersByStatus(int status) {
        List<OrderCate> list = new ArrayList<>();

        String sql = """
        SELECT 
            o.OrderID, o.OrderCode, o.Product_Type,
            o.FullName AS Consignee, o.PhoneNumber, o.Note, o.OrderDate,
            o.Address AS OrderAddress, o.TotalAmount, o.Status, o.PaymentStatusID,

            customer.UserID AS CustomerUserID, customer.FullName AS CustomerName,

            prepareStaff.UserID AS PrepareStaffID, prepareStaff.FullName AS PrepareStaffName, op.PrepareTime,

            shipper.UserID AS ShipperID, shipper.FullName AS ShipperName, shipper.PhoneNumber AS ShipperPhone,         
            s.ShipTime, s.Note AS ShipNote,

            warrantyStaff.UserID AS WarrantyStaffID, warrantyStaff.FullName AS WarrantyStaffName, wa.AssignedDate

        FROM Orders o
        JOIN Users customer ON o.CustomerID = customer.UserID

        LEFT JOIN OrderPreparements op ON o.OrderID = op.OrderID
        LEFT JOIN Users prepareStaff ON op.UserID = prepareStaff.UserID

        LEFT JOIN Shipping s ON o.OrderID = s.OrderID
        LEFT JOIN Users shipper ON s.ShipperID = shipper.UserID

        LEFT JOIN WarrantyAssignments wa ON o.OrderID = wa.OrderID
        LEFT JOIN Users warrantyStaff ON wa.UserID = warrantyStaff.UserID

        WHERE o.Product_Type = 1 AND o.Status = ?
        ORDER BY o.OrderDate DESC;
        """;

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderCate order = new OrderCate();

                    order.setOrderID(rs.getInt("OrderID"));
                    order.setOrderCode(rs.getString("OrderCode"));
                    order.setProduct_Type(rs.getInt("Product_Type"));
                    order.setFullName(rs.getNString("Consignee"));
                    order.setPhoneNumber(rs.getNString("PhoneNumber"));
                    order.setNote(rs.getNString("Note"));
                    order.setOrderDate(rs.getTimestamp("OrderDate"));
                    order.setAddress(rs.getString("OrderAddress"));
                    order.setTotalAmount(rs.getInt("TotalAmount"));
                    order.setStatus(rs.getInt("Status"));
                    order.setPaymentStatusID(rs.getInt("PaymentStatusID"));

                    order.setCustomerID(rs.getInt("CustomerUserID"));
                    order.setCustomerName(rs.getString("CustomerName"));

                    order.setStaffID(rs.getInt("PrepareStaffID"));
                    order.setStaffName(rs.getString("PrepareStaffName"));
                    order.setPrepareTime(rs.getTimestamp("PrepareTime"));

                    order.setShipperID(rs.getInt("ShipperID"));
                    order.setShipperName(rs.getString("ShipperName"));
                    order.setShipperPhone(rs.getString("ShipperPhone"));
                    order.setShipTime(rs.getDate("ShipTime"));
                    order.setShipNote(rs.getNString("ShipNote"));

                    order.setWarrantyStaffID(rs.getInt("WarrantyStaffID"));
                    order.setWarrantyStaffName(rs.getString("WarrantyStaffName"));
                    order.setWarrantyAssignedDate(rs.getTimestamp("AssignedDate"));

                    list.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2. Lấy danh sách linh kiện BuildPC trong 1 đơn hàng
    public List<BuildPCAdmin> getBuildPCItemsByOrderID(int orderID) {
        List<BuildPCAdmin> list = new ArrayList<>();

        String sql = """
        SELECT 
            obi.OrderBuildPCItemID,
            obi.BuildPCID,
            obd.OrderBuildPCDetailID,
            obd.CategoryID,
            c.CategoryName,
            c.ImageURL,
            c.Price,
            c.Status,
            c.BrandComID,
            bc.ComponentID,
            b.BrandName,
            obp.WarrantyDetailID,
            wd.Price AS WarrantyPrice,
            w.Description AS WarrantyDesc

        FROM Order_BuildPCItems obi
        JOIN Order_BuildPCDetails obd ON obi.OrderBuildPCItemID = obd.OrderBuildPCItemID
        JOIN Categories c ON obd.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        JOIN Brands b ON bc.BrandID = b.BrandID
        LEFT JOIN Order_BuildPC_Products obp ON obd.OrderBuildPCDetailID = obp.OrderBuildPCDetailID
        LEFT JOIN WarrantyDetails wd ON obp.WarrantyDetailID = wd.WarrantyDetailID
        LEFT JOIN Warranties w ON wd.WarrantyID = w.WarrantyID

        WHERE obi.OrderID = ?
        ORDER BY obi.OrderBuildPCItemID, obd.OrderBuildPCDetailID;
    """;

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            try (ResultSet rs = ps.executeQuery()) {
                int currentQueue = 0;
                int lastItemID = -1;

                while (rs.next()) {
                    int currentItemID = rs.getInt("OrderBuildPCItemID");

                    // Nếu gặp OrderBuildPCItemID mới thì tăng số thứ tự
                    if (currentItemID != lastItemID) {
                        currentQueue++;
                        lastItemID = currentItemID;
                    }

                    BuildPCAdmin item = new BuildPCAdmin();

                    item.setBuildPCIterm(currentItemID);
                    item.setBuildPCID(rs.getInt("BuildPCID"));
                    item.setCateID(rs.getInt("CategoryID"));
                    item.setCateName(rs.getString("CategoryName"));
                    item.setImgURL(rs.getString("ImageURL"));
                    item.setPrice(rs.getInt("Price"));
                    item.setStatus(rs.getInt("Status"));
                    item.setComponentID(rs.getInt("ComponentID"));
                    item.setBrandName(rs.getString("BrandName"));
                    item.setWarrantyDetailID(rs.getInt("WarrantyDetailID"));
                    item.setWarrantyPrice(rs.getInt("WarrantyPrice"));
                    item.setWarrantyDesc(rs.getString("WarrantyDesc"));

                    item.setQueue(currentQueue); // Gán số thứ tự (queue)

                    list.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 3. Cập nhật trạng thái đơn hàng
    public void updateOrderStatus(int orderID, int status) {
        String updateOrderSQL = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        String updateBuildPCSQL = """
        UPDATE Build_PC 
        SET Status = ? 
        WHERE BuildPCID IN (
            SELECT BuildPCID FROM Order_BuildPCItems WHERE OrderID = ?
        )
    """;

        try (Connection conn = connection; PreparedStatement psOrder = conn.prepareStatement(updateOrderSQL); PreparedStatement psBuildPC = conn.prepareStatement(updateBuildPCSQL)) {

            // Cập nhật đơn hàng
            psOrder.setInt(1, status);
            psOrder.setInt(2, orderID);
            psOrder.executeUpdate();

            // Cập nhật Build_PC tương ứng
            psBuildPC.setInt(1, status);
            psBuildPC.setInt(2, orderID);
            psBuildPC.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrderStatus2(int orderID, int status) {
        String updateOrderSQL = "UPDATE Orders SET Status = ? WHERE OrderID = ?";

        try (Connection conn = connection; PreparedStatement psOrder = conn.prepareStatement(updateOrderSQL)) {

            psOrder.setInt(1, status);
            psOrder.setInt(2, orderID);
            psOrder.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Gán nhân viên chuẩn bị
    public void insertOrderPreparement(int userID, int orderID) {
        String sql = "INSERT INTO OrderPreparements (UserID, OrderID, PrepareTime) VALUES (?, ?, CURRENT_DATE)";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Gán shipper và ngày giao hàng
    public boolean insertShipping(int shipperID, int orderID, java.sql.Date shipTime) {
        String sql = "INSERT INTO Shipping (OrderID, ShipperID, ShipTime) VALUES (?, ?, ?)";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ps.setInt(2, shipperID);
            if (shipTime != null) {
                ps.setDate(3, shipTime);
            } else {
                ps.setNull(3, Types.DATE);
            }
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // check inventory

    public List<BuildPCAdmin> getAllOrderBuildPCItemsByOrderID(int orderID) {
        List<BuildPCAdmin> list = new ArrayList<>();

        String sql = """
        SELECT 
            obi.OrderBuildPCItemID,
                
            bpi.BuildPCID,
            c.CategoryName,
            bpi.Price,
            c.Inventory,
            c.Queue,
            bpi.Status
        FROM Order_BuildPCItems obi
        JOIN Build_PC bp ON obi.BuildPCID = bp.BuildPCID
        JOIN Build_PC_Items bpi ON bpi.BuildPCID = bp.BuildPCID
        JOIN Categories c ON bpi.CategoryID = c.CategoryID
        WHERE obi.OrderID = ?;
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BuildPCAdmin item = new BuildPCAdmin();

                    item.setOrderBuildPCItemID(rs.getInt("OrderBuildPCItemID"));
                    item.setBuildPCID(rs.getInt("BuildPCID"));
                    item.setCateName(rs.getString("CategoryName"));
                    item.setPrice(rs.getInt("Price"));
                    item.setInventory(rs.getInt("Inventory"));
                    item.setQueue(rs.getInt("Queue"));
                    item.setStatus(rs.getInt("Status"));

                    list.add(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    //update inventory
    public void updateQueueForBuildPCOrder(int orderID) {
        String sql = """
        SELECT c.CategoryID, c.Inventory
        FROM Order_BuildPCItems obi
        JOIN Build_PC_Items bpi ON obi.BuildPCID = bpi.BuildPCID
        JOIN Categories c ON bpi.CategoryID = c.CategoryID
        WHERE obi.OrderID = ?
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();

            Map<Integer, Integer> queueMap = new HashMap<>();

            while (rs.next()) {
                int categoryId = rs.getInt("CategoryID");
                int inventory = rs.getInt("Inventory");

                if (inventory < 1) {
                    // Mặc định mỗi linh kiện 1 chiếc cho 1 BuildPC → tăng queue lên 1
                    queueMap.put(categoryId, queueMap.getOrDefault(categoryId, 0) + 1);
                }
            }

            // Cập nhật Queue cho từng Category thiếu hàng
            for (Map.Entry<Integer, Integer> entry : queueMap.entrySet()) {
                int categoryId = entry.getKey();
                int missingQty = entry.getValue();

                String updateSQL = "UPDATE Categories SET Queue = Queue + ? WHERE CategoryID = ?";
                try (PreparedStatement psUpdate = conn.prepareStatement(updateSQL)) {
                    psUpdate.setInt(1, missingQty);
                    psUpdate.setInt(2, categoryId);
                    psUpdate.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillProductsForBuildPCDetail(int detailID) {
        String getUnassignedSQL = """
        SELECT OrderBuildPCProductID 
        FROM Order_BuildPC_Products 
        WHERE OrderBuildPCDetailID = ? AND ProductID IS NULL
    """;

        String getProductsSQL = """
        SELECT p.ProductID 
        FROM Products p
        JOIN Imports i ON p.ImportID = i.ImportID
        WHERE i.CategoryID = (
            SELECT CategoryID 
            FROM Order_BuildPCDetails 
            WHERE OrderBuildPCDetailID = ?
        ) AND p.Status = 1
        LIMIT ?
    """;

        String updateSQL = """
        UPDATE Order_BuildPC_Products 
        SET ProductID = ? 
        WHERE OrderBuildPCProductID = ?
    """;

        String markUsedSQL = """
        UPDATE Products 
        SET Status = 0 
        WHERE ProductID = ?
    """;

        try (Connection conn = connection; PreparedStatement psGetUnassigned = conn.prepareStatement(getUnassignedSQL); PreparedStatement psGetProducts = conn.prepareStatement(getProductsSQL); PreparedStatement psUpdate = conn.prepareStatement(updateSQL); PreparedStatement psMarkUsed = conn.prepareStatement(markUsedSQL)) {

            List<Integer> buildProductIDs = new ArrayList<>();

            psGetUnassigned.setInt(1, detailID);
            try (ResultSet rs = psGetUnassigned.executeQuery()) {
                while (rs.next()) {
                    buildProductIDs.add(rs.getInt("OrderBuildPCProductID"));
                }
            }

            if (buildProductIDs.isEmpty()) {
                return;
            }

            psGetProducts.setInt(1, detailID);
            psGetProducts.setInt(2, buildProductIDs.size());

            List<Integer> productIDs = new ArrayList<>();
            try (ResultSet rs = psGetProducts.executeQuery()) {
                while (rs.next()) {
                    productIDs.add(rs.getInt("ProductID"));
                }
            }

            for (int i = 0; i < Math.min(buildProductIDs.size(), productIDs.size()); i++) {
                int obpID = buildProductIDs.get(i);
                int productID = productIDs.get(i);

                psUpdate.setInt(1, productID);
                psUpdate.setInt(2, obpID);
                psUpdate.addBatch();

                psMarkUsed.setInt(1, productID);
                psMarkUsed.addBatch();
            }

            psUpdate.executeBatch();
            psMarkUsed.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isBuildPCDetailCompleted(int buildPCDetailID, int expectedQuantity) {
        String sql = """
        SELECT COUNT(*) AS cnt 
        FROM Order_BuildPC_Products 
        WHERE OrderBuildPCDetailID = ? AND ProductID IS NOT NULL
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, buildPCDetailID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("cnt");
                    return count >= expectedQuantity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private int getAssignedBuildPCProductCount(int buildPCDetailID, Connection conn) {
        String sql = """
        SELECT COUNT(*) 
        FROM Order_BuildPC_Products 
        WHERE OrderBuildPCDetailID = ? AND ProductID IS NOT NULL
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, buildPCDetailID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Products> getProductsByBuildPCDetailID(int buildPCDetailID) {
        List<Products> list = new ArrayList<>();

        String sql = """
        SELECT 
            p.ProductID, 
            p.ProductCode,
            w.WarrantyPeriod,
            w.Description,
            bp.Start,
            bp.End
        FROM Order_BuildPC_Products bp
        JOIN Products p ON bp.ProductID = p.ProductID
        JOIN WarrantyDetails wd ON bp.WarrantyDetailID = wd.WarrantyDetailID
        JOIN Warranties w ON wd.WarrantyID = w.WarrantyID
        WHERE bp.OrderBuildPCDetailID = ?
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, buildPCDetailID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Products p = new Products();
                    p.setProductID(rs.getInt("ProductID"));
                    p.setProductCode(rs.getString("ProductCode"));
                    p.setWarrantyPeriod(rs.getInt("WarrantyPeriod"));
                    p.setWarrantyDescription(rs.getString("Description"));
                    p.setStart(rs.getDate("Start"));
                    p.setEnd(rs.getDate("End"));
                    list.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateBuildPCOrderStatusIfComplete(int orderID) {
        String checkSql = """
        SELECT COUNT(*) AS MissingCount
        FROM Order_BuildPCDetails d
        JOIN Order_BuildPC_Products p ON d.OrderBuildPCDetailID = p.OrderBuildPCDetailID
        WHERE d.OrderID = ? AND p.ProductID IS NULL
    """;

        String updateSql = "UPDATE Orders SET Status = 3 WHERE OrderID = ?";

        try (Connection conn = new DBAdminContext().connection; PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
            checkPs.setInt(1, orderID);
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next() && rs.getInt("MissingCount") == 0) {
                    try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                        updatePs.setInt(1, orderID);
                        updatePs.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderCate getOrderByID(int orderID) {
        OrderCate order = null;

        String sql = """
        SELECT 
            o.OrderID,
            o.OrderCode,
            o.Product_Type,
            o.FullName AS Consignee,
            o.PhoneNumber,
            o.Note,                  
            o.OrderDate,
            o.Address AS OrderAddress,
            o.TotalAmount,
            o.Status,
            o.PaymentStatusID,

            customer.UserID AS CustomerUserID,
            customer.FullName AS CustomerName,

            prepareStaff.UserID AS PrepareStaffID,
            prepareStaff.FullName AS PrepareStaffName,
            op.PrepareTime,

            shipper.UserID AS ShipperID,
            shipper.FullName AS ShipperName,
            shipper.PhoneNumber AS ShipperPhone,         
            s.ShipTime,
            s.Note AS ShipNote,

            warrantyStaff.UserID AS WarrantyStaffID,
            warrantyStaff.FullName AS WarrantyStaffName,
            wa.AssignedDate

        FROM Orders o
        JOIN Users customer ON o.CustomerID = customer.UserID

        LEFT JOIN OrderPreparements op ON o.OrderID = op.OrderID
        LEFT JOIN Users prepareStaff ON op.UserID = prepareStaff.UserID

        LEFT JOIN Shipping s ON o.OrderID = s.OrderID
        LEFT JOIN Users shipper ON s.ShipperID = shipper.UserID

        LEFT JOIN WarrantyAssignments wa ON o.OrderID = wa.OrderID
        LEFT JOIN Users warrantyStaff ON wa.UserID = warrantyStaff.UserID

        WHERE o.Product_Type = 1 AND o.OrderID = ?
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new OrderCate();

                    // Order info
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setOrderCode(rs.getString("OrderCode"));
                    order.setProduct_Type((Integer) rs.getObject("Product_Type"));
                    order.setFullName(rs.getNString("Consignee"));
                    order.setPhoneNumber(rs.getNString("PhoneNumber"));
                    order.setNote(rs.getNString("Note"));
                    order.setOrderDate(rs.getTimestamp("OrderDate"));
                    order.setAddress(rs.getString("OrderAddress"));
                    order.setTotalAmount(rs.getInt("TotalAmount"));
                    order.setStatus(rs.getInt("Status"));
                    order.setPaymentStatusID(rs.getInt("PaymentStatusID"));

                    // Customer
                    order.setCustomerID(rs.getInt("CustomerUserID"));
                    order.setCustomerName(rs.getString("CustomerName"));

                    // Staff chuẩn bị
                    order.setStaffID(rs.getInt("PrepareStaffID"));
                    order.setStaffName(rs.getString("PrepareStaffName"));
                    order.setPrepareTime(rs.getTimestamp("PrepareTime"));

                    // Shipper
                    order.setShipperID(rs.getInt("ShipperID"));
                    order.setShipperName(rs.getString("ShipperName"));
                    order.setShipperPhone(rs.getString("ShipperPhone"));
                    order.setShipTime(rs.getDate("ShipTime"));
                    order.setShipNote(rs.getNString("ShipNote"));

                    // Warranty staff
                    order.setWarrantyStaffID(rs.getInt("WarrantyStaffID"));
                    order.setWarrantyStaffName(rs.getString("WarrantyStaffName"));
                    order.setWarrantyAssignedDate(rs.getTimestamp("AssignedDate"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    public void updateOrderDetailsWarrantyDates() {
        String sql = """
        UPDATE OrderDetails od
        JOIN OrderItems oi ON od.OrderItemID = oi.OrderItemID
        JOIN Orders o ON oi.OrderID = o.OrderID
        JOIN Shipping s ON s.OrderID = o.OrderID
        JOIN WarrantyDetails wd ON wd.WarrantyDetailID = od.WarrantyDetailID
        JOIN Warranties w ON wd.WarrantyID = w.WarrantyID
        SET 
            od.`Start` = s.ShipTime,
            od.`End` = DATE_ADD(s.ShipTime, INTERVAL w.WarrantyPeriod MONTH)
        WHERE s.ShipTime IS NOT NULL
          AND od.`Start` IS NULL
          AND o.Product_Type = 1
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            int rows = ps.executeUpdate();
            System.out.println("✅ Đã cập nhật " + rows + " dòng trong OrderDetails (Build PC).");

        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật ngày bảo hành trong OrderDetails:");
            e.printStackTrace();
        }
    }

    public List<Products> getPendingWarrantyProductsByOrderId(int orderId) {
        List<Products> productList = new ArrayList<>();
        String sql = """
        SELECT p.ProductCode, p.Status
        FROM Products p
        JOIN OrderDetails od ON p.ProductID = od.ProductID
        JOIN OrderItems oi ON od.OrderItemID = oi.OrderItemID
        JOIN Orders o ON oi.OrderID = o.OrderID
        WHERE (p.Status = 3 OR p.Status = 4)
          AND o.Product_Type = 1
          AND o.OrderID = ?
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Products product = new Products();
                    product.setProductCode(rs.getString("ProductCode"));
                    product.setStatus(rs.getInt("Status"));
                    productList.add(product);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    public boolean updateProductStatusByCode(String productCode, int status) {
        String sql = "UPDATE Products SET Status = ? WHERE ProductCode = ?";
        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, status);
            ps.setString(2, productCode);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasApprovedProductInOrder(int orderID) {
        String sql = """
        SELECT 1
        FROM Products p
        JOIN OrderDetails od ON p.ProductID = od.ProductID
        JOIN OrderItems oi ON od.OrderItemID = oi.OrderItemID
        JOIN Orders o ON oi.OrderID = o.OrderID
        WHERE o.OrderID = ? AND o.Product_Type = 1 AND p.Status = 4
        LIMIT 1
    """;

        try (Connection conn = new DBAdminContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true nếu có ít nhất 1 sản phẩm đã approved
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        OrderBuildPCAdmin dao = new OrderBuildPCAdmin();
        int testOrderID = 8; // Thay bằng OrderID thật đang có trong DB

        List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(testOrderID);
        System.out.println("🧪 Tổng số dòng: " + items.size());

        for (BuildPCAdmin item : items) {
            System.out.println("➡️ OrderBuildPCItemID: " + item.getBuildPCIterm());
            System.out.println("   Category: " + item.getCateName());
            System.out.println("   Price: " + item.getPrice());
            System.out.println("   ComponentID: " + item.getComponentID());
            System.out.println("   Brand: " + item.getBrandName());
            System.out.println("   WarrantyDesc: " + item.getWarrantyDesc());
            System.out.println("-----------------------------");
        }
    }

}
