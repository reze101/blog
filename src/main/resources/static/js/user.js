/**
 * 
 */

let index = {
	init: function() {
		$("#btn-save").on("click", () => {		//this를 바인딩 하기 위해 화살표 함수 사용  
			this.save();
		});
//		$("#btn-login").on("click", () => {		//this를 바인딩 하기 위해 화살표 함수 사용  
//			this.login();
//		});
		$("#btn-update").on("click", () => {		//this를 바인딩 하기 위해 화살표 함수 사용  
			this.update();
		});
	},

	save: function() {
		//alert('user save function');
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};

		//console.log(data);

		//ajax 호출시 default : 비동기 호출
		//ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청 
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	//js 오브젝트를 json객체로 변경 , json data는 http body에 담김 
			contentType: "application/json; charset=utf-8",	//http header Mimetype 적기 (보내는 데이터의 body 타입)
			dataType: "json"		//응답된 데이터의 타입이 json이라면 javascript오브젝트로 변환시킴 
		}).done(function(response) {	//응답된 데이터의 타입이 json이라면 javascript오브젝트로(response) 변환시킴 
			//응답 결과 성공시 수행 
			//console.log(response);
			alert("회원가입 성공");
			location.href = "/";

		}).fail(function(error) {
			//응답 결과 실패시 수행 
			alert(JSON.stringify(error));

		});
	},

/*
	login: function() {
		//alert('user save function');
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
		};

		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),	//js 오브젝트를 json객체로 변경 , json data는 http body에 담김 
			contentType: "application/json; charset=utf-8",	//http header Mimetype 적기 (보내는 데이터의 body 타입)
			dataType: "json"		//응답된 데이터의 타입이 json이라면 javascript오브젝트로 변환시킴 
		}).done(function(response) {	//응답된 데이터의 타입이 json이라면 javascript오브젝트로(response) 변환시킴 
			alert("로그인 성공");
			location.href = "/";

		}).fail(function(error) {
			//응답 결과 실패시 수행 
			alert(JSON.stringify(error));

		});
	}
	
*/	

update: function() {
		let data = {
			id:$('#id').val(),
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),	//js 오브젝트를 json객체로 변경 , json data는 http body에 담김 
			contentType: "application/json; charset=utf-8",	//http header Mimetype 적기 (보내는 데이터의 body 타입)
			dataType: "json"		//응답된 데이터의 타입이 json이라면 javascript오브젝트로 변환시킴 
		}).done(function(response) {	//응답된 데이터의 타입이 json이라면 javascript오브젝트로(response) 변환시킴 
			//응답 결과 성공시 수행 
			//console.log(response);
			alert("회원수정 성공");
			location.href = "/";

		}).fail(function(error) {
			//응답 결과 실패시 수행 
			alert(JSON.stringify(error));

		});
	},
}
index.init();
