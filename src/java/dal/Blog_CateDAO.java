package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Blog_Cate;
import models.Post;

public class Blog_CateDAO extends DBContext {

    public List<Blog_Cate> getAllBlogCategory() {
        List<Blog_Cate> list = new ArrayList<>();
        String sql = "SELECT Bc_id, Bc_name FROM Blogs_category";

        try {
            Connection conn = connection; // sử dụng connection từ DBContext
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Bc_id");
                String name = rs.getString("Bc_name");
                list.add(new Blog_Cate(id, name));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM Post";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();

                post.setPost_id(rs.getInt("Post_id"));
                post.setTitle(rs.getString("Title"));
                post.setAuthor(rs.getString("Author"));
                post.setUpdated_date(rs.getTimestamp("Updated_date"));
                post.setContent(rs.getString("Content"));
                post.setBc_id(rs.getInt("Bc_id"));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setBrief(rs.getString("Brief"));
                post.setAdd_id(rs.getInt("Add_id"));

                list.add(post);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        Blog_CateDAO dao = new Blog_CateDAO();
//        System.out.println(dao.getAllBlogCategory());
        System.out.println(dao.getAllPost());
    }

}
