/**
 * 
 */

let index = {
	init: function() {
		$("#btn-save").on("click", () => {		//this를 바인딩 하기 위해 화살표 함수 사용  
			this.save();
		});
		$("#btn-delete").on("click", () => {		//this를 바인딩 하기 위해 화살표 함수 사용  
			this.deleteById();
		});
		$("#btn-update").on("click", () => {		//this를 바인딩 하기 위해 화살표 함수 사용  
			this.update();
		});
	},

	save: function() {
		//alert('user save function');
		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		};
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),	//js 오브젝트를 json객체로 변경 , json data는 http body에 담김 
			contentType: "application/json; charset=utf-8",	//http header Mimetype 적기 (보내는 데이터의 body 타입)
			dataType: "json"		//응답된 데이터의 타입이 json이라면 javascript오브젝트로 변환시킴 
		}).done(function(response) {	//응답된 데이터의 타입이 json이라면 javascript오브젝트로(response) 변환시킴 
			//응답 결과 성공시 수행 
			alert("글 등록 성공");
			location.href = "/";

		}).fail(function(error) {
			//응답 결과 실패시 수행 
			alert(JSON.stringify(error));

		});
	},

	deleteById: function() {
		let id = $('#id').text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"		//응답된 데이터의 타입이 json이라면 javascript오브젝트로 변환시킴 
		}).done(function(response) {	//응답된 데이터의 타입이 json이라면 javascript오브젝트로(response) 변환시킴 
			//응답 결과 성공시 수행 
			alert("글 삭제 성공");
			location.href = "/";

		}).fail(function(error) {
			//응답 결과 실패시 수행 
			alert(JSON.stringify(error));

		});
	},

	update: function() {
		let id = $('#id').val();
		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		}

		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),	//js 오브젝트를 json객체로 변경 , json data는 http body에 담김 
			contentType: "application/json; charset=utf-8",	//http header Mimetype 적기 (보내는 데이터의 body 타입)
			dataType: "json"		//응답된 데이터의 타입이 json이라면 javascript오브젝트로 변환시킴 
		}).done(function(response) {	//응답된 데이터의 타입이 json이라면 javascript오브젝트로(response) 변환시킴 
			//응답 결과 성공시 수행 
			alert("글 수정 성공");
			location.href = "/";

		}).fail(function(error) {
			//응답 결과 실패시 수행 
			alert(JSON.stringify(error));

		});
	},

}
index.init();
