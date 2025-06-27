/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.CategoriesDAO;
import dal.DBContext;
import java.util.Arrays;
import java.util.List;

public class TestBuildPC {

    public static void main(String[] args) {
        DBContext db = new DBContext();

        if (!db.isConnected()) {
            System.out.println("❌ Kết nối CSDL thất bại, dừng test.");
            return;
        }

        CategoriesDAO dao = new CategoriesDAO();

        // Thay các CategoryID sau thành ID thực tế trong bảng Categories với 6 linh kiện khác nhau (MainBoard, CPU, GPU, RAM, SSD, Case)
        List<Integer> categoryIDs = Arrays.asList(3, 10, 5, 42, 1, 2);

        int userID = 1; // Thay bằng UserID hợp lệ trong bảng Users

        boolean success = dao.insertBuildPCToCart(categoryIDs, userID);

        if (success) {
            System.out.println("✅ Thêm Build PC vào giỏ hàng thành công.");
        } else {  
            System.out.println("❌ Thêm Build PC vào giỏ hàng thất bại.");
        }
    }
}
