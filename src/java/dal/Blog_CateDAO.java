package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.sql.Date;
import java.util.List;
import javax.lang.model.util.Types;
import models.Blog_Cate;
import models.Comment;
import models.Post;
import models.SaleEvents;

public class Blog_CateDAO extends DBContext {

    public List<Blog_Cate> getAllBlogCategory() {
        List<Blog_Cate> list = new ArrayList<>();
        String sql = "SELECT Bc_id, Bc_name FROM Blogs_category";

        try {
            Connection conn = connection;
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

    public List<Post> getTop5NewestPosts() {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT TOP 5 * FROM Post ORDER BY Updated_date DESC";

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
                post.setStatus(rs.getInt("status"));
                list.add(post);
            }
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
                post.setStatus(rs.getInt("status"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT p.*, c.Bc_name FROM Post p JOIN Blogs_category c ON p.Bc_id = c.Bc_id";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();

                post.setPost_id(rs.getInt("Post_id"));
                post.setTitle(rs.getString("Title"));
                post.setAuthor(rs.getString("Author"));
                post.setUpdated_date(rs.getTimestamp("Updated_date"));
                post.setContent(rs.getString("Content"));
                post.setBc_name(rs.getString("Bc_name"));
                post.setBc_id(rs.getInt("Bc_id"));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setBrief(rs.getString("Brief"));
                post.setAdd_id(rs.getInt("Add_id"));
                post.setStatus(rs.getInt("status"));

                list.add(post);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Comment> getCommentsByPostId(int Post_id) {
        List<Comment> list = new ArrayList<>();
        String query = "SELECT c.*, u.FullName FROM Comment c JOIN Users u ON c.UserID = u.UserID WHERE c.Post_id = ? ORDER BY c.CreatedAt ASC";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, Post_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("CommentID"),
                        rs.getInt("Post_id"),
                        rs.getInt("UserID"),
                        rs.getString("CommentText"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getObject("ParentCommentID") != null ? rs.getInt("ParentCommentID") : null);
                comment.setFullName(rs.getString("FullName"));
                list.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addComment(Comment c) {
        String query = "INSERT INTO Comment (Post_id, UserID, CommentText, ParentCommentID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, c.getPost_id());
            ps.setInt(2, c.getUserID());
            ps.setString(3, c.getCommentText());
            if (c.getParentCommentID() != null) {
                ps.setInt(4, c.getParentCommentID());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPost(Post p) {
    String sql = "INSERT INTO Post (Title, Author, Updated_date, Content, Bc_id, Thumbnail, Brief, Add_id, Status) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, p.getTitle());
        st.setString(2, p.getAuthor());
        st.setDate(3, new java.sql.Date(p.getUpdated_date().getTime()));
        st.setString(4, p.getContent());
        st.setInt(5, p.getBc_id());
        st.setString(6, p.getThumbnail());
        st.setString(7, p.getBrief());
        st.setInt(8, p.getAdd_id());
        st.setInt(9, p.getStatus());

        st.executeUpdate();
    } catch (Exception e) {
        System.err.println("SQL Error during insertPost: " + e.getMessage());
        e.printStackTrace(); // optional: can log to file instead
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
                p.setStatus(rs.getInt("status"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePost(Post p) {
    String sql = "UPDATE Post SET Title=?, Author=?, Updated_date=?, Content=?, Bc_id=?, Thumbnail=?, Brief=?, Status=? "
               + "WHERE Post_id=?";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, p.getTitle());
        st.setString(2, p.getAuthor());
        st.setDate(3, new java.sql.Date(p.getUpdated_date().getTime()));
        st.setString(4, p.getContent());
        st.setInt(5, p.getBc_id());
        st.setString(6, p.getThumbnail());
        st.setString(7, p.getBrief());
        st.setInt(8, p.getStatus());
        st.setInt(9, p.getPost_id());
        st.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


//   
    public List<Post> getPostsByStatus(int status) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM Post WHERE status = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, status);
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
                post.setStatus(rs.getInt("status"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updatePostStatus(int postID, int status) {
        String sql = "UPDATE Post SET status = ? WHERE Post_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, status);
            ps.setInt(2, postID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPostsSortedByTitle(boolean ascending) {
        List<Post> list = new ArrayList<>();
        String order = ascending ? "ASC" : "DESC";
        String sql = "SELECT p.*, c.Bc_name FROM Post p JOIN Blogs_category c ON p.Bc_id = c.Bc_id ORDER BY p.Title " + order;

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("Post_id"));
                post.setTitle(rs.getString("Title"));
                post.setAuthor(rs.getString("Author"));
                post.setUpdated_date(rs.getTimestamp("Updated_date"));
                post.setContent(rs.getString("Content"));
                post.setBc_name(rs.getString("Bc_name"));
                post.setBc_id(rs.getInt("Bc_id"));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setBrief(rs.getString("Brief"));
                post.setAdd_id(rs.getInt("Add_id"));
                post.setStatus(rs.getInt("status"));
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Post> getPostsByAuthorRole(String roleName) {
        List<Post> list = new ArrayList<>();
        String query = "SELECT p.* FRSOM Post p "
                + "JOIN Users u ON p.Add_id = u.UserID "
                + "JOIN Roles r ON u.RoleID = r.RoleID "
                + "WHERE r.RoleName = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post(
                        rs.getInt("Post_id"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getTimestamp("Updated_date"),
                        rs.getString("Content"),
                        rs.getString("Bc_name"),
                        rs.getInt("Bc_id"),
                        rs.getString("Thumbnail"),
                        rs.getString("Brief"),
                        rs.getInt("Add_id"),
                        rs.getInt("status")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
                    post.setStatus(rs.getInt("status"));

                    list.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Post> searchPostsByTitle(String keyword) throws SQLException {
        List<Post> result = new ArrayList<>();
        String sql = "SELECT * FROM Post WHERE Title LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("Post_id"));
                post.setTitle(rs.getString("Title"));
                post.setAuthor(rs.getString("Author"));
                post.setUpdated_date(rs.getTimestamp("Updated_date"));
                post.setContent(rs.getString("Content"));
                post.setBc_id(rs.getInt("Bc_id"));
                post.setBc_name(rs.getString("Bc_name"));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setBrief(rs.getString("Brief"));
                post.setAdd_id(rs.getInt("Add_id"));
                post.setStatus(rs.getInt("status"));

                result.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Post> getPostsByCategorySorted(int bc_id, String sortOrder) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM Post WHERE Bc_id = ?";

        if ("latest".equalsIgnoreCase(sortOrder)) {
            sql += " ORDER BY Updated_date DESC";
        } else if ("oldest".equalsIgnoreCase(sortOrder)) {
            sql += " ORDER BY Updated_date ASC";
        } // nếu sortOrder là null hoặc "default" thì không thêm ORDER BY

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
                post.setStatus(rs.getInt("status"));

                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String getStockStatusByCategoryID(int categoryID) {
        String sql = "SELECT Quantity, RestockExpected FROM Inventory WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int qty = rs.getInt("Quantity");
                boolean restock = rs.getBoolean("RestockExpected");
                if (qty > 0) {
                    return "in_stock";
                }
                if (qty == 0 && restock) {
                    return "restocking";
                }
                if (qty == 0 && !restock) {
                    return "discontinued";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "unknown";
    }

    public List<SaleEvents> getSaleEventsByCategory(int categoryID) {
        List<SaleEvents> saleEvents = new ArrayList<>();
        String query = "SELECT * FROM SaleEvents WHERE CategoryID = ?";
        String stockQuery = "SELECT Quantity, RestockExpected FROM Inventory WHERE CategoryID = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(query); PreparedStatement stockStmt = connection.prepareStatement(stockQuery)) {
            stmt.setInt(1, categoryID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SaleEvents event = new SaleEvents();
                event.setEventID(rs.getInt("EventID"));
                event.setCategoryID(rs.getInt("CategoryID"));
                event.setPost_id(rs.getInt("Post_id"));
                event.setStartDate(rs.getDate("StartDate"));
                event.setEndDate(rs.getDate("EndDate"));
                event.setDiscountPercent(rs.getDouble("DiscountPercent"));
//                event.setStatus(rs.getInt("Status"));
                event.setCreatedBy(rs.getInt("CreatedBy"));
                event.setApprovedBy(rs.getObject("ApprovedBy") != null ? rs.getInt("ApprovedBy") : null);

                // Check tồn kho
                stockStmt.setInt(1, event.getCategoryID());
                ResultSet stockRs = stockStmt.executeQuery();
                int stock = -99;
                if (stockRs.next()) {
                    int quantity = stockRs.getInt("Quantity");
                    boolean restock = stockRs.getBoolean("RestockExpected");
                    if (quantity > 0) {
                        stock = 1;
                    } else if (quantity == 0 && restock) {
                        stock = 0;
                    } else if (quantity == 0 && !restock) {
                        stock = -1;
                    }
                }
                event.setStock(stock);

                saleEvents.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleEvents;
    }

    // Add a sale event
    public void addSaleEvent(SaleEvents event) {
        String query = "INSERT INTO SaleEvents (CategoryID, Post_id, StartDate, EndDate, DiscountPercent, CreatedBy, ApprovedBy) VALUES (?, ?, ?, ?, ?,  ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, event.getCategoryID());
            stmt.setInt(2, event.getPost_id());
            stmt.setDate(3, new java.sql.Date(event.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(event.getEndDate().getTime()));
            stmt.setDouble(5, event.getDiscountPercent());
            stmt.setInt(6, event.getCreatedBy());
            stmt.setInt(7, event.getApprovedBy());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update sale event status
    public void updateSaleEventStatus(int eventID, int status) {
        String query = "UPDATE SaleEvents SET Status = ? WHERE EventID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, status);
            stmt.setInt(2, eventID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check sale event status based on dates
    private int checkEventStatus(SaleEvents event) {
        Date currentDate = new Date(System.currentTimeMillis());
        if (currentDate.before(event.getStartDate())) {
            return 2; // Chưa bắt đầu
        } else if (currentDate.after(event.getEndDate())) {
            return 3; // Đã kết thúc
        } else {
            return 1; // Đang hoạt động
        }
    }

    public static void main(String[] args) {
        Blog_CateDAO dao = new Blog_CateDAO();
        //        List<Post> list = dao.getAllPost();
        //        for (Post o : list){
        //            System.out.println(o);
        //        }
//                System.out.println(dao.searchPostsByTitle("laptop"));

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
//                  System.out.println(dao.getPostById(1));
//        Post p = new Post();
//        p.setPost_id(1); // ID của bài viết cần cập nhật
//        p.setTitle("Top 5 Budget Laptops for Students in 2025");
//        p.setAuthor("NinhTT");
//        p.setUpdated_date(new java.sql.Timestamp(System.currentTimeMillis())); // ngày hiện tại
//        p.setContent("Choosing the right laptop as a student can be tough. In this article, we review the top 5 budget laptops in 2025 that balance price and performance.");
//        p.setBc_id(2); // ID danh mục blog mới (đảm bảo tồn tại)
//        p.setThumbnail("https://i.ytimg.com/vi/pKFc0wedO_M/maxresdefault.jpg");
//        p.setBrief("A quick guide to the best affordable laptops for students.");
//        p.setAdd_id(1); // ID admin (đảm bảo tồn tại)
//        p.setStatus(1);
//
//        dao.updatePost(p);
//        System.out.println("Đã cập nhật bài viết thành công.");
//        int postIdToDelete = 1; // ID của bài viết cần xoá
//        dao.deletePost(postIdToDelete);
//
//        System.out.println("Đã xoá bài viết có ID = " + postIdToDelete);
//                System.out.println(dao.getPostsByCategoryId(1));
    }

}
