package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
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

    public List<Post> getPostsByCategoryId(int bc_id) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM Post WHERE Bc_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bc_id);
            ResultSet rs = ps.executeQuery();
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

    public void insertPost(Post p) {
        String sql = "INSERT INTO Post (Title, Author, Updated_date, Content, Bc_id, Thumbnail, Brief, Add_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, p.getTitle());
            st.setString(2, p.getAuthor());
            st.setDate(3, new java.sql.Date(p.getUpdated_date().getTime()));
            st.setString(4, p.getContent());
            st.setInt(5, p.getBc_id());
            st.setString(6, p.getThumbnail());
            st.setString(7, p.getBrief());
            st.setInt(8, p.getAdd_id());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Post getPostById(int id) {
        String sql = "SELECT * FROM Post WHERE Post_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Post p = new Post();
                p.setPost_id(rs.getInt("Post_id"));
                p.setTitle(rs.getString("Title"));
                p.setAuthor(rs.getString("Author"));
                p.setUpdated_date(rs.getTimestamp("Updated_date"));
                p.setContent(rs.getString("Content"));
                p.setBc_id(rs.getInt("Bc_id"));
                p.setThumbnail(rs.getString("Thumbnail"));
                p.setBrief(rs.getString("Brief"));
                p.setAdd_id(rs.getInt("Add_id"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePost(Post p) {
        String sql = "UPDATE Post SET Title=?, Author=?, Updated_date=?, Content=?, Bc_id=?, Thumbnail=?, Brief=?, Add_id=? "
                + "WHERE Post_id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, p.getTitle());
            st.setString(2, p.getAuthor());
            st.setDate(3, new java.sql.Date(p.getUpdated_date().getTime()));
            st.setString(4, p.getContent());
            st.setInt(5, p.getBc_id());
            st.setString(6, p.getThumbnail());
            st.setString(7, p.getBrief());
            st.setInt(8, p.getAdd_id());
            st.setInt(9, p.getPost_id());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int id) {
        String sql = "DELETE FROM Post WHERE Post_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countAllPosts() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Post";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error counting posts: " + e.getMessage());
        }

        return count;
    }

    public List<Post> getPostsByPage(int index) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM Post \n"
                + "ORDER BY Post_id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, (index - 1) * 4);

            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        Blog_CateDAO dao = new Blog_CateDAO();
        //        List<Post> list = dao.getAllPost();
        //        for (Post o : list){
        //            System.out.println(o);
        //        }
        //        System.out.println(dao.getAllBlogCategory());
        //        System.out.println(dao.getAllPost());
        //        System.out.println(dao.countAllPosts());
        //        Post p = new Post();
        //        p.setTitle("Giới thiệu Laptop mới");
        //        p.setAuthor("Nguyễn Văn A");
        //        p.setUpdated_date(new Timestamp(System.currentTimeMillis())); // ngày hiện tại
        //        p.setContent("Nội dung chi tiết về sản phẩm mới...");
        //        p.setBc_id(1); // id danh mục blog (phải tồn tại trong DB)
        //        p.setThumbnail("images/laptop-new.jpg");
        //        p.setBrief("Laptop hiệu suất cao, giá tốt.");
        //        p.setAdd_id(1); // id admin người đăng (phải tồn tại trong DB)
        //
        //        dao.insertPost(p); // thực hiện thêm
        //        System.out.println("Đã chèn bài viết thành công.");
        //          System.out.println(dao.getPostById(1));
//        Post p = new Post();
//        p.setPost_id(1); // ID của bài viết cần cập nhật
//        p.setTitle("Cập nhật tiêu đề bài viết");
//        p.setAuthor("Nguyễn Văn B");
//        p.setUpdated_date(new java.sql.Timestamp(System.currentTimeMillis())); // ngày hiện tại
//        p.setContent("Nội dung đã được cập nhật...");
//        p.setBc_id(2); // ID danh mục blog mới (đảm bảo tồn tại)
//        p.setThumbnail("images/updated-thumbnail.jpg");
//        p.setBrief("Tóm tắt nội dung mới.");
//        p.setAdd_id(1); // ID admin (đảm bảo tồn tại)
//
//        dao.updatePost(p);
//        System.out.println("Đã cập nhật bài viết thành công.");
//        int postIdToDelete = 1; // ID của bài viết cần xoá
//        dao.deletePost(postIdToDelete);
//
//        System.out.println("Đã xoá bài viết có ID = " + postIdToDelete);
                System.out.println(dao.getPostsByCategoryId(1));

    }

}
