<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>Sign In / Sign Up</title>
		<meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
		<meta name="author" content="Xueyan Wu, Yihua Shi">

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
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
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
						<li><i class="fa fa-home pr-10"></i><a href="home.jsp">Home</a></li>
						<li class="active">Page Sign Up</li>
					</ol>
				</div>
			</div>
			<!-- breadcrumb end -->

			<!-- main-container start -->
			<!-- ================ -->
			<div class="main-container dark-translucent-bg" style="background-image:url('images/background-img-6.jpg');">
				<div class="container">
					<div class="row">
						
						<div class="main object-non-visible" data-animation-effect="fadeInUpSmall" data-effect-delay="100">
						
							<!-- hidden bool value to indicate the existence of error message (for incorrect sign up inputs) -->
							<input type="hidden" id="msgExists" value=<%= request.getAttribute("signupMessage")==null?false:true %>>
							
							<!-- log in start -->
							<!-- ============ -->
							<div id="sign-in-box" class="form-block center-block p-30 light-gray-bg border-clear">
								<h2 class="title" style="display:inline">Sign In</h2>
								<a id="change2signup" style="float:right; cursor:pointer">Register a new account</a>
								<!-- 
									<jsp:useBean id="user" scope="request" class="beans.User"></jsp:useBean>
								 -->
								<form class="form-horizontal" role="form" method="post" action="Login">
									<div class="form-group has-feedback">
										<label for="loginEmail" class="col-sm-3 control-label">Email <span class="text-danger small">*</span></label>
										<div class="col-sm-8">
											<input type="email" class="form-control" id="loginEmail" name="loginEmail" placeholder="Email" required>
											<i class="fa fa-envelope form-control-feedback"></i>
										</div>
									</div>
									<div class="form-group has-feedback">
										<label for="loginPassword" class="col-sm-3 control-label">Password <span class="text-danger small">*</span></label>
										<div class="col-sm-8">
											<input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="Password" required>
											<i class="fa fa-lock form-control-feedback"></i>
										</div>
									</div>
									<div class="message text-danger">
										<%= request.getAttribute("loginMessage")==null?"":request.getAttribute("loginMessage")+" Please try again." %>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<button type="submit" class="btn btn-group btn-default btn-animated">Sign In <i class="fa fa-check"></i></button>
										</div>
									</div>		
								</form>
							</div>
							<!-- log in end -->
							
							
							<!-- sign up start -->
							<!-- ================ -->
							<div id="sign-up-box" class="form-block center-block p-30 light-gray-bg border-clear" style="display:none">
								<h2 class="title" style="display: inline">Sign Up</h2>
								<a id="change2signin" style="float:right; cursor:pointer">Already have an account? Sign in here</a>
								
								<form class="form-horizontal" role="form" method="post" action="Signup">
									<input type="hidden" name="action" value="signup" />	
									<div class="form-group has-feedback">
										<label for="signupEmail" class="col-sm-3 control-label">Email <span class="text-danger small">*</span></label>
										<div class="col-sm-8">
											<input type="email" class="form-control" id="signupEmail" name="signupEmail" value="" placeholder="Email Address" required>
											<i class="fa fa-envelope form-control-feedback"></i>
										</div>
									</div>
									<div class="form-group has-feedback">
										<label for="signupPassword" class="col-sm-3 control-label">Password <span class="text-danger small">*</span></label>
										<div class="col-sm-8">
											<input type="password" class="form-control" id="signupPassword" name="password" value="" placeholder="Your Password" required>
											<i class="fa fa-lock form-control-feedback"></i>
										</div>
									</div>
									<div class="form-group has-feedback">
										<label class="col-sm-3 control-label">Confirm your password <span class="text-danger small">*</span></label>
										<div class="col-sm-8">
											<input type="password" class="form-control" id="repeatPassword" name="repeatPassword" value="" placeholder="Confirm Password" required>
											<i class="fa fa-lock form-control-feedback"></i>
										</div>
									</div>
									<div class="message text-danger">
										<%= request.getAttribute("signupMessage")==null?"":request.getAttribute("signupMessage")+" Please try again." %>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<div class="checkbox">
												<label>
													<input type="checkbox" required> Accept our <a href="#">privacy policy</a> and <a href="#">customer agreement</a>
												</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<button type="submit" class="btn btn-group btn-default btn-animated">Sign Up <i class="fa fa-check"></i></button>
										</div>
									</div>
								</form>
							</div>
							<!-- sign up end -->
						
						</div>
						<!-- main end -->
					</div>
				</div>
			</div>
			<!-- main-container end -->
			
			<!-- footer top start -->
			<!-- ================ -->
			<div class="dark-bg footer-top">
				<div class="container">
					<div class="row">
						<br><br><br>
					</div>
				</div>
			</div>
			<!-- footer top end -->
			
		</div>
		<!-- page-wrapper end -->
		
		<script>
			//jquery function to change sign-up/sign-in window
			$("#change2signin").click(function(){
				$("#sign-up-box").hide();
				$("#sign-in-box").show();
			});
			$("#change2signup").click(function(){
				$("#sign-in-box").hide();
				$("#sign-up-box").show();
			});
			$(document).ready(function(){
				if($("#msgExists").val() == "true"){
					$("#sign-in-box").hide();
					$("#sign-up-box").show();
				}
			});
		</script>
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
