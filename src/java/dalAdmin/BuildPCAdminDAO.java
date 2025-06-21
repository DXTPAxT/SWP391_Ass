/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.BuildPCAdmin;
import models.BuildPCView;
import models.Components;

/**
 *
 * @author PC
 */
public class BuildPCAdminDAO extends DBAdminContext {

    public List<Components> getALLComponent() {
        List<Components> list = new ArrayList<>();
        String sql = "Select *From Components ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Components c = new Components(
                        rs.getInt("ComponentID"),
                        rs.getString("ComponentName"),
                        rs.getInt("Quantity"),
                        rs.getInt("Status")
                );
                list.add(c);
            }

        } catch (Exception e) {
        }
        return list;
    }

 public List<BuildPCView> getBuildPCSummaryView() {
    List<BuildPCView> list = new ArrayList<>();
    String sql = """
        SELECT 
            bp.BuildPCID,
            MAX(CASE WHEN bc.ComponentID = 2 THEN c.CategoryName END) AS MainBoard,
            MAX(CASE WHEN bc.ComponentID = 3 THEN c.CategoryName END) AS CPU,
            MAX(CASE WHEN bc.ComponentID = 4 THEN c.CategoryName END) AS GPU,
            MAX(CASE WHEN bc.ComponentID = 5 THEN c.CategoryName END) AS RAM,
            MAX(CASE WHEN bc.ComponentID = 6 THEN c.CategoryName END) AS SSD,
            MAX(CASE WHEN bc.ComponentID = 7 THEN c.CategoryName END) AS CASE_,
            SUM(bpi.Price) AS Price,
            MAX(bp.Status) AS Status
        FROM 
            Build_PC bp
        JOIN Build_PC_Items bpi ON bp.BuildPCID = bpi.BuildPCID
        JOIN Categories c ON bpi.CategoryID = c.CategoryID
        JOIN BrandComs bc ON c.BrandComID = bc.BrandComID
        WHERE bc.ComponentID BETWEEN 2 AND 7
        GROUP BY bp.BuildPCID
        ORDER BY bp.BuildPCID
    """;

    try (
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            BuildPCView b = new BuildPCView();
            b.setBuildPCID(rs.getInt("BuildPCID"));
            b.setMainBoard(rs.getString("MainBoard"));
            b.setCpu(rs.getString("CPU"));
            b.setGpu(rs.getString("GPU"));
            b.setRam(rs.getString("RAM"));
            b.setSsd(rs.getString("SSD"));
            b.setPcCase(rs.getString("CASE_")); // lưu ý alias là CASE_ thay vì CASE (SQL keyword)
            b.setPrice(rs.getInt("Price"));
            b.setStatus(rs.getInt("Status"));
            list.add(b);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

}
