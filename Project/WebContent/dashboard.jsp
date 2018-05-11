<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="beans.Post" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>User Dashboard</title>
		<meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
		<meta name="author" content="Yihua Shi, Xueyan Wu">

		<!-- Mobile Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Favicon -->
		<link rel="shortcut icon" href="images/favicon.ico">

		<!-- Web Fonts -->
		<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:700,400,300' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=PT+Serif' rel='stylesheet' type='text/css'>

		<!-- Bootstrap core CSS -->
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

		<!-- Font Awesome CSS -->
		<link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

		<!-- Fontello CSS -->
		<link href="fonts/fontello/css/fontello.css" rel="stylesheet">

		<!-- Plugins -->
		<link href="plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
		<link href="css/animations.css" rel="stylesheet">
		<link href="plugins/owl-carousel/owl.carousel.css" rel="stylesheet">
		<link href="plugins/owl-carousel/owl.transitions.css" rel="stylesheet">
		<link href="plugins/hover/hover-min.css" rel="stylesheet">		
		
		<!-- The Project's core CSS file -->
		<link href="css/style.css" rel="stylesheet" >
		<!-- The Project's Typography CSS file, includes used fonts -->
		<!-- Used font for body: Roboto -->
		<!-- Used font for headings: Raleway -->
		<link href="css/typography-default.css" rel="stylesheet" >
		<!-- Color Scheme (In order to change the color scheme, replace the blue.css with the color scheme that you prefer)-->
		<link href="css/skins/light_blue.css" rel="stylesheet">
		
		<!-- Custom css --> 
		<link href="css/custom.css" rel="stylesheet">
		
		<!-- Jquery files -->
		<script type="text/javascript" src="plugins/jquery.min.js"></script>
		
		<!-- Datatable -->
		<link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	</head>


	
	<!-- body classes:  -->
	<!-- "boxed": boxed layout mode e.g. <body class="boxed"> -->
	<!-- "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1"> -->
	<!-- "transparent-header": makes the header transparent and pulls the banner to top -->
	<!-- "gradient-background-header": applies gradient background to header -->
	<!-- "page-loader-1 ... page-loader-6": add a page loader to the page (more info @components-page-loaders.html) -->
	<body class="no-trans    ">

		<!-- scrollToTop -->
		<!-- ================ -->
		<div class="scrollToTop circle"><i class="icon-up-open-big"></i></div>
		
		<!-- page wrapper start -->
		<!-- ================ -->
		<div class="page-wrapper">
		
			<!-- navigation bar start -->
			<%@ include file ="navigation.jsp" %>
			<!-- navigation bar end -->

			<!-- breadcrumb start -->
			<!-- ================ -->
			<div class="breadcrumb-container">
				<div class="container">
					<ol class="breadcrumb">
						<li><i class="fa fa-home pr-10"></i><a href="index.jsp">Home</a></li>
						<li class="active">Dashboard</li>
					</ol>
				</div>
			</div>
			<!-- breadcrumb end -->

			<!-- main-container start -->
			<!-- ================ -->
			<section class="main-container">

				<div class="container">
					<div class="row">

						<!-- main start -->
						<!-- ================ -->
						<div class="main col-md-12">

							<!-- page-title start -->
							<!-- ================ -->
							<h1 class="page-title">Dashboard</h1>
							<h4 style="float: right;" class="col-md-3">Welcome! <%=usr%></h4>
							<div class="separator-2"></div>
							<!-- page-title end -->
							<div class="row">
								<!-- Tabs start -->
								<!-- ========== -->
								
								<!-- Nav tabs -->
								<ul class="nav nav-tabs style-2" role="tablist">
									<li class="active"><a href="#h3tab1" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-file-text pr-10"></i>Postings</a></li>
									<li class=""><a href="#h3tab2" role="tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-star pr-10"></i>Favorites</a></li>
									<li class=""><a href="#h3tab3" role="tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-cog pr-10"></i>Your Profile</a></li>
								</ul>
								<!-- Tab panes -->
								<div class="tab-content">
									<div class="tab-pane active" id="h3tab1">
										<div class="row">
											<div class="col-md-12">
				
											<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
										        <thead>
										            <tr>
										                <th>Posting Title</th>
										                <th>Area & Category</th>
										                <th>Status</th> 
										                <th>Created</th>
										                <th>Modified</th>
										            </tr>
										        </thead>
										        <tbody>
										        
											<%   	
											   	 int count = (Integer)request.getAttribute("count");
											   	 ArrayList titleResult = (ArrayList)request.getAttribute("postings");
											 	 
											     for(int i=0;i<count;i++){
											    	Post p = (Post)titleResult.get(i);
											    	out.println("<tr>");
											    	out.println("<td>");
											      	out.println("<a href=\"itemsWanted?pid="+p.getPid()+"\">"+p.getTitle()+"</a>");
											    	out.println("</td>");
											    	
											    	out.println("<td>");
											      	out.println(p.getType());
											    	out.println("</td>");
											    	
											    	out.println("<td>");
											      	out.println(p.getStatus());
											    	out.println("</td>");
											    	
													out.println("<td>");
											      	out.println(p.getC_date());
											    	out.println("</td>");
											    	
													out.println("<td>");
											      	out.println(p.getM_date());
											    	out.println("</td>");
											        out.println("</tr>");  
											      }
											%>   
											
										        </tbody>
										    </table>
											</div>
		    
										</div>
										<script>
										    $('#example').DataTable({
										    	"paging":   true,
										        "ordering": true,
										        "info":     false,
										        "searching": true
										    });
										</script>

										<a href="#" class="btn btn-default margin-clear">Read more</a>
									</div>
									
									<div class="tab-pane" id="h3tab2">
										<div class="row">
											<div class="col-md-6">
												<ul class="list-icons">
													<li><i class="icon-check pr-10"></i> Lorem ipsum dolor sit amet maiores</li>
													<li><i class="icon-check pr-10"></i> Consectetur adipisicing elit</li>
													<li><i class="icon-check pr-10"></i> Aut earum numquam hic placeat</li>
													<li><i class="icon-check pr-10"></i> Fugiat rem consequatur neque</li>
												</ul>
											</div>
											<div class="col-md-6">
												<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere hic qui non placeat ad explicabo dignissimos amet iusto veniam!</p>
												<p>Quo expedita tempore modi minima at adipisci saepe excepturi alias consequuntur sunt asperiores enim ut assumenda hic vitae, odio deleniti illo, veniam quas!</p>
											</div>
										</div>
										<a href="page-services.html" class="btn btn-gray margin-clear">Read more</a>
									</div>
									<div class="tab-pane" id="h3tab3">
										<div class="row">
											<div class="col-md-10">
												<form class="form-horizontal" role="form">
													<div class="form-group has-feedback">
														<label class="col-sm-3 control-label">Email </label>
														<div class="col-sm-8">
															<input type="email" class="form-control" id="inputEmail" placeholder="Email" value=<%=usr%> disabled>
															<i class="fa fa-envelope form-control-feedback"></i>
														</div>
													</div>
										
										
										
									<a class="btn btn-gray btn-animated collapsed" data-toggle="collapse" href="#collapseContent" aria-expanded="false" aria-controls="collapseContent">Change Password <i class="fa fa-plus"></i></a>
									<div class="collapse" id="collapseContent" aria-expanded="false">
										<div class="row">
											<div class="form-group has-feedback">
												<label for="inputPassword" class="col-sm-3 control-label">Old Password <span class="text-danger small">*</span></label>
												<div class="col-sm-8">
													<input type="password" class="form-control" id="inputPassword" placeholder="Old Password" required="">
													<i class="fa fa-lock form-control-feedback"></i>
												</div>
											</div>
											<div class="form-group has-feedback">
												<label for="password1" class="col-sm-3 control-label">New Password <span class="text-danger small">*</span></label>
												<div class="col-sm-8">
													<input type="password" class="form-control" id="password1" placeholder="New Password" required="">
													<i class="fa fa-lock form-control-feedback"></i>
												</div>
											</div>
											<div class="form-group has-feedback">
												<label for="password2" class="col-sm-3 control-label">Confirm your password <span class="text-danger small">*</span></label>
												<div class="col-sm-8">
													<input type="password" class="form-control" id="password2" placeholder="Confirm Password" required="">
													<i class="fa fa-lock form-control-feedback"></i>
												</div>
											</div>
										</div>
									</div>										
										
																					
													<div class="form-group">
														<div class="col-sm-offset-3 col-sm-8">
															<button type="submit" class="btn btn-group btn-default btn-animated">Update Profile<i class="fa fa-check"></i></button>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								<!-- Tabs end -->
							</div>
						</div>
						<!-- main end -->

					</div>
				</div>
			</section>
			<!-- main-container end -->
			
		</div>
		<!-- page-wrapper end -->

		<!-- JavaScript files placed at the end of the document so the pages load faster -->
		<!-- ================================================== -->
		<!-- Jquery and Bootstap core js files -->
		<script type="text/javascript" src="plugins/jquery.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<!-- Modernizr javascript -->
		<script type="text/javascript" src="plugins/modernizr.js"></script>
		<!-- Magnific Popup javascript -->
		<script type="text/javascript" src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
		<!-- Appear javascript -->
		<script type="text/javascript" src="plugins/waypoints/jquery.waypoints.min.js"></script>
		<!-- Count To javascript -->
		<script type="text/javascript" src="plugins/jquery.countTo.js"></script>
		<!-- Parallax javascript -->
		<script src="plugins/jquery.parallax-1.1.3.js"></script>
		<!-- Contact form -->
		<script src="plugins/jquery.validate.js"></script>
		<!-- Owl carousel javascript -->
		<script type="text/javascript" src="plugins/owl-carousel/owl.carousel.js"></script>
		<!-- SmoothScroll javascript -->
		<script type="text/javascript" src="plugins/jquery.browser.js"></script>
		<script type="text/javascript" src="plugins/SmoothScroll.js"></script>
		<!-- Initialization of Plugins -->
		<script type="text/javascript" src="js/template.js"></script>
		<!-- Custom Scripts -->
		<script type="text/javascript" src="js/custom.js"></script>

	</body>
</html>
