package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.OrderDetail;

public class OrderDetailDAO extends DBContext {

    public ArrayList<OrderDetail> getOrderDetailsByOrderItemID(int orderItemID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails WHERE OrderItemID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderItemID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderDetailID(rs.getInt("OrderDetailID"));
                od.setOrderItemID(rs.getInt("OrderItemID"));
                od.setProductID(rs.getInt("ProductID"));
                od.setWarrantyDetailID(rs.getInt("WarrantyDetailID"));
                od.setUnitPrice(rs.getInt("UnitPrice"));
                od.setWarrantyPrice(rs.getInt("WarrantyPrice"));
                od.setStatus(rs.getInt("Status"));
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetails (OrderItemID, ProductID, WarrantyDetailID, UnitPrice, WarrantyPrice, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderDetail.getOrderItemID());

            if (orderDetail.getProductID() != 0) {
                ps.setInt(2, orderDetail.getProductID());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setInt(3, orderDetail.getWarrantyDetailID());
            ps.setInt(4, orderDetail.getUnitPrice());
            ps.setInt(5, orderDetail.getWarrantyPrice());
            ps.setInt(6, orderDetail.getStatus());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
