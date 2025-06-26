package controllerAdmin;

import dalAdmin.BuildPCAdminDAO;
import models.BuildPCView;
import java.util.List;

public class TestBuildPC2 {

 
       public static void main(String[] args) {
        BuildPCAdminDAO dao = new BuildPCAdminDAO();
        
        List<BuildPCView> buildPCList = dao.getBuildPCSummaryView();
        
        for (BuildPCView b : buildPCList) {
            System.out.println("BuildPCID: " + b.getBuildPCID());
            System.out.println("MainBoard: " + b.getMainBoard());
            System.out.println("CPU: " + b.getCpu());
            System.out.println("GPU: " + b.getGpu());
            System.out.println("RAM: " + b.getRam());
            System.out.println("SSD: " + b.getSsd());
            System.out.println("Case: " + b.getPcCase());
            System.out.println("Price: " + b.getPrice());
            System.out.println("Status: " + b.getStatus());
            System.out.println("UserID: " + b.getUserID());
            System.out.println("FullName: " + b.getFullName());
            System.out.println("Role: " + b.getRole());
            System.out.println("-----------------------------");
        }
    }
}
