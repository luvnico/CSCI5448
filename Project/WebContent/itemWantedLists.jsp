<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="beans.Post" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Item Wanted List</title>
	<meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
	<meta name="author" content="htmlcoder.me">

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
</head>

<!-- body classes:  -->
<!-- "boxed": boxed layout mode e.g. <body class="boxed"> -->
<!-- "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1"> -->
<!-- "transparent-header": makes the header transparent and pulls the banner to top -->
<!-- "gradient-background-header": applies gradient background to header -->
<!-- "page-loader-1 ... page-loader-6": add a page loader to the page (more info @components-page-loaders.html) -->
<body class="no-trans">

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
					<li><i class="fa fa-home pr-10"></i><a href="index.html">Home</a></li>
					<li class="active">Items Wanted</li>
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
						<h1 class="page-title">Items Wanted</h1>
						<div class="separator-2"></div>
						<!-- page-title end -->
					
							<div class="section light-gray-bg">
								<div class="container">
									<div class="row">
										<div class="col-md-12 text-muted">
											<form role="form" method="GET" action="ItemWantedList">
												<div class="form-group has-feedback">
													<input type="text" class="form-control" name="search" placeholder="Search Posting for Wanted Items">
													<i class="fa fa-search form-control-feedback"></i>
												</div>
											</form>
										</div>
										<div class="col-md-12">
										<ul class="media-list">
										<c:forEach items="${postings}" var="p">
										  <li class="media">
										    <div class="media-left">
										      <time class="result-date" title="${p.m_date}">${p.m_date}</time>
										      <i class="fa fa-calendar"></i>
										    </div>
										    <div class="media-body">
												<h4 class="media-heading">
													<a href="publishedView?pid=${p.pid}">${p.title}</a>
												</h4>${p.desc} ...
											</div>
										  </li>
									     </c:forEach>
									     </ul>
										</div>
									</div>
								</div>
							</div>
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