<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container m-4">
	<form>
		<input type="hidden" id="id" value="${principal.user.id }">
		<div class="form-group">
			<label for="username">user name:</label> <input type="text" readonly="readonly" class="form-control" value="${principal.user.username }" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control"  value="${principal.user.email }"  id="email">
		</div>

	</form>

	<button id="btn-update" class="btn btn-primary">회원수정 완료</button>
</div>

<script type="text/javascript"  src="/js/user.js">
</script>

<%@ include file="../layout/footer.jsp"%>


