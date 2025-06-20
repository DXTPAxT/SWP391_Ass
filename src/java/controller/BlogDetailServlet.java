package controller;

import dal.Blog_CateDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Blog_Cate;
import models.Comment;
import models.Post;

@WebServlet(name = "BlogDetailServlet", urlPatterns = {"/blogdetail"})
public class BlogDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Bc_id_raw = request.getParameter("Bc_id");
        String Post_id_raw = request.getParameter("Post_id");

        Blog_CateDAO dao = new Blog_CateDAO();

        try {

            int Bc_id = Integer.parseInt(Bc_id_raw);
            List<Post> postList = dao.getPostsByCategoryId(Bc_id);
            request.setAttribute("postList", postList);

        } catch (NumberFormatException e) {

            request.setAttribute("postList", null);
        }

        Post postDetail = null;
        List<Comment> comments = null;

        try {
            int Post_id = Integer.parseInt(Post_id_raw);
            postDetail = dao.getPostById(Post_id);
            comments = dao.getCommentsByPostId(Post_id);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        request.setAttribute("post", postDetail);
        request.setAttribute("comments", comments);
        List<Blog_Cate> categories = dao.getAllBlogCategory();
        List<Post> top5Posts = dao.getTop5NewestPosts();

        request.setAttribute("blog_categories", categories);

        request.setAttribute("top5Posts", top5Posts);
        request.getRequestDispatcher("ShopPages/Pages/blog_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        private int CommentID;
//    private int Post_id;
//    private int UserID;
//    private String CommentText;
//    private Timestamp CreatedAt;
//    private Integer ParentCommentID; 
        String Post_idRaw = request.getParameter("Post_id");
        String CommentText = request.getParameter("CommentText");
        String ParentCommentIDRaw = request.getParameter("ParentCommentID");
        int userId = 3;
        try {
            int Post_id = Integer.parseInt(Post_idRaw);
            Integer ParentCommentID = null;

            if (ParentCommentIDRaw != null && !ParentCommentIDRaw.isEmpty()) {
                ParentCommentID = Integer.parseInt(ParentCommentIDRaw);
            }

            Comment comment = new Comment();
            comment.setPost_id(Post_id);
            comment.setUserID(userId);
            comment.setCommentText(CommentText);
            comment.setParentCommentID(ParentCommentID);

            Blog_CateDAO dao = new Blog_CateDAO();

            dao.addComment(comment);

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        response.sendRedirect("blogdetail" + Post_idRaw);
    }

    @Override
    public String getServletInfo() {
        return "Blog Detail Servlet";
    }
}
