#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>国战三国志——管理系统</title>

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="../ico/favicon.ico" type="image/x-icon" />

<!-- Css files -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/jquery.mmenu.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../plugins/jquery-ui/css/jquery-ui-1.10.4.min.css"
	rel="stylesheet">
<link href="../css/style.min.css" rel="stylesheet">
<link href="../css/add-ons.min.css" rel="stylesheet">
<style>
footer {
	display: none;
}
</style>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/admin/login.js"></script>
</head>
<body>

	<div class="container-fluid content">
		<div class="row">
			<div id="content" class="col-sm-12 full">
				<div class="row">
					<div class="login-box">

						<div class="header">国战三国志</div>
						<div class="text-center">
							<!-- <li><div href="" class="fa fa-facebook facebook-bg"></div>国战</li>
							<li><div href="" class="fa fa-twitter twitter-bg"></div>三国</li>
							<li><div href="" class="fa fa-linkedin linkedin-bg"></div>志</li> -->
						</div>

						<div class="text-with-hr">
							<span>管理后台</span>
						</div>

						<form class="form-horizontal login" action="index.html"
							method="post">

							<fieldset class="col-sm-12">
								<div class="form-group">
									<div class="controls row">
										<div class="input-group col-sm-12">
											<input type="text" class="form-control" id="loginname"
												placeholder="请输入登录名" /> <span class="input-group-addon"><i
												class="fa fa-user"></i></span>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="controls row">
										<div class="input-group col-sm-12">
											<input type="password" class="form-control" id="password"
												placeholder="请输入登录密码" /> <span class="input-group-addon"><i
												class="fa fa-key"></i></span>
										</div>
									</div>
								</div>

								<!-- <div class="confirm">
									<input type="checkbox" name="remember" /> <label
										for="remember">Remember me</label>
								</div> -->

								<div class="row">

									<button type="submit" id="loginBtn" onclick="login()"
										class="btn btn-lg btn-primary col-xs-12">登录</button>

								</div>

							</fieldset>

						</form>

						<!-- <a class="pull-left" href="page-login.html${symbol_pound}">Forgot Password?</a>
						<a class="pull-right" href="page-register.html">Sign Up!</a> -->

						<div class="clearfix"></div>

					</div>
				</div>
				<!--/row-->

			</div>

		</div>
		<!--/row-->


		<!-- <div id="usage-blank">
			<ul>
				<li>
					<div class="title">Memory</div>
					<div class="bar">
						<div class="progress">
							<div class="progress-bar progress-bar-success" role="progressbar"
								aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"
								style="width: 25%"></div>
						</div>
					</div>
					<div class="desc">2GB of 8GB</div>
				</li>
				<li>
					<div class="title">HDD</div>
					<div class="bar">
						<div class="progress">
							<div class="progress-bar progress-bar-warning" role="progressbar"
								aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
								style="width: 75%"></div>
						</div>
					</div>
					<div class="desc">750GB of 1TB</div>
				</li>
				<li>
					<div class="title">SSD</div>
					<div class="bar">
						<div class="progress">
							<div class="progress-bar progress-bar-info" role="progressbar"
								aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"
								style="width: 30%"></div>
						</div>
					</div>
					<div class="desc">300GB of 1TB</div>
				</li>
				<li>
					<div class="title">Bandwidth</div>
					<div class="bar">
						<div class="progress">
							<div class="progress-bar progress-bar-danger" role="progressbar"
								aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
								style="width: 100%"></div>
						</div>
					</div>
					<div class="desc">50TB of 50TB</div>
				</li>
			</ul>
		</div> -->

	</div>
	<!--/container-->


	<!-- start: JavaScript-->
	<!--[if !IE]>-->

	<script src="../js/jquery-2.1.1.min.js"></script>

	<!--<![endif]-->

	<!--[if IE]>
	
		<script src="../js/jquery-1.11.1.min.js"></script>
	
	<![endif]-->

	<!--[if !IE]>-->

	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='../js/jquery-2.1.1.min.js'>"
						+ "<"+"/script>");
	</script>

	<!--<![endif]-->

	<!--[if IE]>
	
		<script type="text/javascript">
	 	window.jQuery || document.write("<script src='../js/jquery-1.11.1.min.js'>"+"<"+"/script>");
		</script>
		
	<![endif]-->
	<script src="../js/jquery-migrate-1.2.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>


	<!-- page scripts -->

	<!-- theme scripts -->
	<script src="../js/SmoothScroll.js"></script>
	<script src="../js/jquery.mmenu.min.js"></script>
	<script src="../js/core.min.js"></script>

	<!-- inline scripts related to this page -->
	<script src="../js/pages/login.js"></script>

	<!-- end: JavaScript-->
</body>
</html>
