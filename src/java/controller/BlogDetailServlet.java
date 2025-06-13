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
            try {
                int Post_id = Integer.parseInt(Post_id_raw);
                postDetail = dao.getPostById(Post_id);
            } catch (NumberFormatException e) {

            }

            request.setAttribute("post", postDetail);

            List<Blog_Cate> categories = dao.getAllBlogCategory();
            List<Post> top5Posts = dao.getTop5NewestPosts();

            request.setAttribute("blog_categories", categories);

            request.setAttribute("top5Posts", top5Posts);
            request.getRequestDispatcher("ShopPages/Pages/blog_detail.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        @Override
        public String getServletInfo() {
            return "Blog Detail Servlet";
        }
    }
