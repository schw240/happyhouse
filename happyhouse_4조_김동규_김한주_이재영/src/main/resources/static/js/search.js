$(document).ready(function () {
	
	$("#aptname").keyup(function(key) {
		var key = $("#aptname").val();
		if(key.length >= 3) {
			$.ajax({
            	url: 'http://localhost/happyhouse/map/auto',
            	data: {'key': key},
              	type: 'POST',
              	dataType: 'json',
              	success: function (aptList) {
//              		$(".dropdown-menu").empty()
              		for(let i = 0; i < aptList.length; i++) {
//              			$(".dropdown-menu").append("<div class='dropdown-item' role = \"button\">" + aptList[i].aptName + "</div>")
              			$(".dropdown-item" + i).text(aptList[i].aptName)
              		}
              	}, 
              	error: function(request, status, error) {
              		console.log("status : " + request.status + "\tmsg : " + error);
              	}
			});
		} else {
			$(".dropdown-item0").text("")
			$(".dropdown-item1").text("")
			$(".dropdown-item2").text("")
		}
    });
	
	$(".dropdown-item0").click(function () {
//		alert($(this).text())
		$("#aptname").val($(this).text())
		$(".dropdown-item0").text("")
		$(".dropdown-item1").text("")
		$(".dropdown-item2").text("")
	})
	$(".dropdown-item1").click(function () {
//		alert($(this).text())
		$("#aptname").val($(this).text())
		$(".dropdown-item0").text("")
		$(".dropdown-item1").text("")
		$(".dropdown-item2").text("")
	})
	$(".dropdown-item2").click(function () {
//		alert($(this).text())
		$("#aptname").val($(this).text())
		$(".dropdown-item0").text("")
		$(".dropdown-item1").text("")
		$(".dropdown-item2").text("")
	})
	
})