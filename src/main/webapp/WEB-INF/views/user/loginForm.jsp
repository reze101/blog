<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-4">
	<form action="/auth/loginProc" method="post">

		<div class="form-group">
			<label for="username">user name:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
		<!--
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
-->
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=60af53dd62335119b0e681ffbcc766ee&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"> <img
			src="/image/kakao_login_button.png" height="38px" />
		</a>
	</form>

</div>

<script type="text/javascript" src="/js/user.js">
	
</script>
<%@ include file="../layout/footer.jsp"%>


