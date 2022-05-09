<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<div>
		<h1>${boardDetail.title }</h1>
		<br>
		<div>
			<span> 글번호 : <i id="id">${boardDetail.id }</i>
			</span> <span> 작성자 : <i>${boardDetail.user.username }</i>
			</span>
		</div>
	</div>

	<hr>

	<div>
		<div>${boardDetail.content }</div>
	</div>
	<hr>

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${boardDetail.user.id == principal.user.id}">
		<a href="/board/${boardDetail.id}/updateForm"class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>

</div>
<br>
<br>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
