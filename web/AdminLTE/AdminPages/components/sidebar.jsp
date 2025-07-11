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
                    <li><a href="${param.ctx}/BrandAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert new Brand</a></li>  
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
                    <li><a href="${param.ctx}/ComAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert new Component</a></li>
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
                    <li><a href="${param.ctx}/BrandComAdmin"><i class="fa fa-circle-o"></i>View Bra-Com</a></li>   
                    <li><a href="${param.ctx}/BrandComAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert new Brand-Com</a></li>
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
                    <li><a href="${param.ctx}/CateAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert new Category</a></li>
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
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Warranty</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/WarrantyAdmin"><i class="fa fa-circle-o"></i>View Warranty</a></li> 
                    <li><a href="${param.ctx}/WarrantyAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert Warranty</a></li> 
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Warranty Detail</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/WDA"><i class="fa fa-circle-o"></i>View Warranty Detail</a></li> 
                    <li><a href="${param.ctx}/WDA?service=insert"><i class="fa fa-circle-o"></i>Insert Warranty Detail</a></li> 
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Order Category</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">    
                    <li><a href="${param.ctx}/OrderAdmin?service=listCate"><i class="fa fa-circle-o"></i>View All Order</a></li>
                    <li><a href="${param.ctx}/OrderAdmin"><i class="fa fa-circle-o"></i>Pending Order</a></li>
                    <li><a href="${param.ctx}/OrderBuildPCAdmin"><i class="fa fa-circle-o"></i>Order Category Items</a></li>
                    <!-- don thay the neu phai doi hang cho khach -->
                    <li><a href="${param.ctx}/OrderAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert Replacement Order </a></li> 
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Order Build PC</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${param.ctx}/OrderAdmin?service=listBuildPC"><i class="fa fa-circle-o"></i>View All Order</a></li>
                    <li><a href="${param.ctx}/OrderAdmin"><i class="fa fa-circle-o"></i>Pending Order</a></li>
                    <li><a href="${param.ctx}/OrderBuildPCAdmin"><i class="fa fa-circle-o"></i>Order Build PC Items</a></li>
                    <!-- don thay the neu phai doi hang cho khach -->
                    <li><a href="${param.ctx}/OrderAdmin?service=insert"><i class="fa fa-circle-o"></i>Insert Replacement Order </a></li> 
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
                    <li><a href="${param.ctx}/bloga"><i class="fa fa-circle-o"></i>View Blog</a></li>
                    <li><a href="${param.ctx}/BlogAdmin&service=insert"><i class="fa fa-circle-o"></i>Insert new Blog</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-users"></i> <span>User</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                          
                    <li><a href="${param.ctx}/Admin/user?type=customer"><i class="fa fa-circle-o"></i>View Customers</a></li>   
                    <li><a href="${param.ctx}/Admin/user?type=sale"><i class="fa fa-circle-o"></i>View Sales</a></li>   
                    <li><a href="${param.ctx}/Admin/user?type=shipper"><i class="fa fa-circle-o"></i>View Shippers</a></li>   
                    <li><a href="${param.ctx}/Admin/user/add"><i class="fa fa-circle-o"></i>Create new user</a></li>
                </ul>
            </li>  
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>Build PC</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">                               
                    <li><a href="${pageContext.request.contextPath}/BuildPC_ListCate?service=list">
                            <i class="fa fa-circle-o"></i> View PC</a>
                    </li>

                    <li><a href="${param.ctx}/AdminLTE/AdminPages/pages/forms/BuildPCAdmin.html"><i class="fa fa-circle-o"></i>Creative new PC    </a></li>
                </ul>
            </li>
            <li>
                <a href="${param.ctx}/NotificationServlet?service=showSendForm">
                    <i class="fa fa-bell"></i> <span>Send Notification</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>