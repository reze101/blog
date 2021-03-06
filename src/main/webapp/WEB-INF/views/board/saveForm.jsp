<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="title">title</label> <input type="text" class="form-control" placeholder="title" id="title">
		</div>
		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">글 등록</button>

</div>


<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script type="text/javascript"  src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
