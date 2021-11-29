$(document).ready(function () {
	// 회원가입
    $("#joinBtn").click(function () {
        if (!$("#userid").val()) {
            alert("아이디 입력!!!");
            return;
        } else if (!$("#password").val()) {
            alert("비밀번호 입력!!!");
            return;
        } else if (!$("#name").val()) {
            alert("이름 입력!!!");
            return;
        } else if (!$("#address").val()) {
            alert("주소 확인!!!");
            return;
        } else {
            $("#registerForm").attr("action", "http://localhost/happyhouse/user/register").submit();
        }
    });
    
    // 로그인
    $("#loginBtn").click(function () {
    	if (!$("#loginId").val()) {
            alert("아이디 입력!!!");
            return;
        } else if (!$("#loginPassword").val()) {
            alert("비밀번호 입력!!!");
            return;
        } else {
        	$("#login-form").attr("action", "http://localhost/happyhouse/user/login").submit();
//        	login()
//    	    $("#logout").css("display", "inline");
//		    $("#info").css("display", "inline");
//		    $("#login").css("display", "none");
//		    $("#signup").css("display", "none");
//		    $(".mustLogin").css("display", "inline");
        }
    	
    })
    
    // 업데이트 창 눌렀을 때 수정창으로 바꾸기
    $("#beforeUpdate").click(function () {
    	$(".info_label").css("display", "none");
    	$(".info_input").css("display", "inline");
    	$("#delete").css("display", "inline");
    	$("#updateButton").css("display", "inline");
    	$("#beforeUpdate").css("display", "none");
    	
    })
    
    // 모달 닫힐때 다시 정보 보기 모드로
    $("#infoModal").on('hidden.bs.modal', function () {
    	$(".info_label").css("display", "inline");
		$(".info_input").css("display", "none");
		$("#delete").css("display", "none");
		$("#updateButton").css("display", "none");
		$("#beforeUpdate").css("display", "inline");
    })
    
    // 업데이트 버튼 클릭
    $("#updateButton").click(function () {
    	let modifyinfo = JSON.stringify({
			"userid" : $("#modifyid").val(), 
			"password" : $("#modifypassword").val(), 
			"name" : $("#modifyname").val(), 
			"phone" : $("#modifyphone").val(), 
			"address" : $("#modifyaddress").val(), 
		   });
    	$.ajax({
			url:'http://localhost/happyhouse/user/update',  
			type:'PUT',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			data: modifyinfo,
			success:function(user) {
				$(".info_label").css("display", "inline");
				$(".info_input").css("display", "none");
				$("#delete").css("display", "none");
				$("#updateButton").css("display", "none");
				$("#beforeUpdate").css("display", "inline");
				location.reload()
			},
			error:function(xhr,status,msg){
				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
			}
		});
    	
    })
    
    // 삭제 버튼 클릭
    $("#delete").click(function () {
    	let userid = $("#modifyid").val()
    	$.ajax({
			url:'http://localhost/happyhouse/user/delete/' + userid,  
			type:'DELETE',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function() {
				location.reload()
			},
			error:function(xhr,status,msg){
				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
			}
		});
    })
    
//     login ajax 사용
    
//    function login() {
//    	let logininfo = JSON.stringify({
//    		"userid" : $("#loginId").val(),
//    		"password" : $("#loginPassword").val()
//    	})
//    	
//    	$.ajax({
//			url:'http://localhost/happyhouse/user/login',  
//			type:'POST',
//			contentType:'application/json;charset=utf-8',
//			dataType:'json',
//			data: logininfo,
//			success:function(user) {
//				alert(user.userid)
//			},
//			error:function(xhr,status,msg){
////				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
//			}
//		});
//    }
	
})

function update() {
  $(".info_label").css("display", "none");
  $(".info_input").css("display", "inline");
  $("#delete").css("display", "inline");
  $("#updateButton").css("display", "inline");
  $("#beforeUpdate").css("display", "none");
}