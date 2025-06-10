<% String ctx = request.getContextPath(); %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${param.ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="img-circle" alt="User">
            </div>
            <div class="pull-left info">
                <p>Alexander Pierce</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Brand</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/BrandAdmin"><i class="fa fa-circle-o"></i>View Brand</a></li>
                    <li><a href="${param.ctx}/BrandAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Brand</a></li>  
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Component</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/ComAdmin"><i class="fa fa-circle-o"></i>View Component</a></li>
                    <li><a href="${param.ctx}/ComAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Component</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>BraCom</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/BraComAdmin"><i class="fa fa-circle-o"></i>View Bra-Com</a></li>   
                    <li><a href="${param.ctx}/BraComAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Brand-Com</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Category</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/CateAdmin"><i class="fa fa-circle-o"></i>View Category</a></li>
                    <li><a href="${param.ctx}/CateAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Category</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Import</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/Import"><i class="fa fa-circle-o"></i>View Import</a></li>   
                    <li><a href="${param.ctx}/Import&service=insert"><i class="fa fa-circle-o"></i>Insert new Import</a></li>   
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Product</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/ProductAdmin"><i class="fa fa-circle-o"></i>View Product</a></li> 
                    <li><a href="${param.ctx}/ProductAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Product</a></li>   
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Order</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/OrderAdmin"><i class="fa fa-circle-o"></i>View Order</a></li>
                    <li><a href="${param.ctx}/OrderAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Order</a></li> 
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Feedback</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/FeedBackAdmin"><i class="fa fa-circle-o"></i>View Feedback</a></li> 
                    <li><a href="${param.ctx}/FeedBackAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Feedback</a></li>                                         

                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Blogs</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/BlogAdmin"><i class="fa fa-circle-o"></i>View Blog</a></li>
                    <li><a href="${param.ctx}/BlogAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Blog</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>User</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/Admin/user"><i class="fa fa-circle-o"></i>View Users</a></li>                                  
                    <li><a href="${param.ctx}/Admin/user/add"><i class="fa fa-circle-o"></i>Create new user</a></li>                              
                </ul>
            </li>      
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>