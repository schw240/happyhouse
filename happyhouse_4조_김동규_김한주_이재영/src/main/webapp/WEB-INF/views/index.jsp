<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Kakao Map</title>
<link rel="shortcut icon" href="img/favicon.ico" />
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/apt.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous" />


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=25fdf682e8691d8145d06898a8ffa361&libraries=services"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=25fdf682e8691d8145d06898a8ffa361&libraries=LIBRARY"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<script type="text/javascript" src="js/search.js"></script>


</head>

<body>
	<!-- 네비 위 로그인과 회원정보-->
<!-- 	<div class="header-container"> -->
	<div class="header-container">
		<!-- header-top start -->
		<!-- classes:  -->
		<!-- "dark": dark version of header top e.g. class="header-top dark" -->
		<!-- "colored": colored version of header top e.g. class="header-top colored" -->
		<!-- ================ -->
		<div class="header-top dark">
			<div class="container">
				<div class="row">
					<div class="col-3 col-sm-6 col-lg-9">
						<!-- header-top-first start -->
						<!-- ================ -->
						<div class="header-top-first clearfix"></div>
						<!-- header-top-first end -->
					</div>
					<div class="col-9 col-sm-6 col-lg-3">

						<!-- header-top-second start -->
						<!-- ================ -->
						<div id="header-top-second" class="clearfix">

							<!-- header top dropdowns start -->
							<!-- ================ -->
							<div class="header-top-dropdown text-right">
								<c:if test="${!empty userinfo}">
									<div class="btn-group" id="info">
										<a href="${root}/user/logout"
											class="btn btn-default btn-sm" style="color: #ffffff;"><i
											class="fa fa-user pr-2" style="color: #ffffff;"></i> logout</a>
									</div>

									<div class="btn-group" id="logout">
										<a href="/signupForm.do"
											class="btn btn-default btn-sm mustLogin"
											style="color: #ffffff;" data-toggle="modal"
											data-target="#infoModal"><i
											class="fa fa-user pr-2" style="color: #ffffff;"></i> 회원정보</a>
									</div>
								</c:if>
								<c:if test="${empty userinfo}">
									<div class="btn-group" id="signup">
										<a href="/signupForm.do" class="btn btn-default btn-sm"
											style="color: #ffffff;" data-toggle="modal"
											data-target="#signUpModal"><i
											class="fa fa-user pr-2" style="color: #ffffff;"></i> Sign Up</a>
									</div>
									<div class="btn-group" id="login">
										<button id="header-top-drop-2" type="button"
											class="btn dropdown-toggle btn-default btn-sm dropdown-toggle--no-caret"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" style="color: #ffffff;">
											<i class="fa fa-lock pr-2"></i> Login
										</button>
										<ul
											class="dropdown-menu dropdown-menu-right dropdown-animation"
											aria-labelledby="header-top-drop-2"
											style="background-color: #212529;">
											<li>
												<form class="login-form margin-clear" action=""
													method="post" id="login-form"
													style="width: 260px; padding: 0;">
													<input type="hidden" name="action" value="login" />
													<div class="form-group has-feedback" style="width: 258;">
														<label class="control-label" style="color: #ffffff;">아
															이 디</label> <input type="text" class="form-control"
															placeholder="" name="userid" id="loginId"> <i
															class="fa fa-user form-control-feedback"></i>
													</div>
													<div class="form-group has-feedback">
														<label class="control-label" style="color: #ffffff;">비밀번호</label>
														<input type="password" class="form-control" placeholder=""
															name="password" id="loginPassword"> <i
															class="fa fa-lock form-control-feedback"></i>
													</div>
													<!-- <button type="button" class="btn btn-gray btn-sm" style = "background-color: #e1e1e1;" onclick="login()">
                                로 그 인</button> -->
													<button type="button" class="btn btn-gray btn-sm"
														style="background-color: #e1e1e1;"id="loginBtn" >로그인</button>
													<ul class='pagination'>
														<!--
                                <li><a href="#" class="btn btn-default btn-sm">아이디
                                    찾기</a></li>
                                -->
														<li><a href="passwordSearchForm.do"
															class="btn btn-default btn-sm"
															style="color: #ffffff; background-color: #00000033;">비밀번호
																찾기</a></li>
													</ul>
												</form>
											</li>
										</ul>
									</div>
								</c:if>



							</div>
							<!--  header top dropdowns end -->
						</div>
						<!-- header-top-second end -->
					</div>
				</div>
			</div>
		</div>
		<header class="header fixed fixed-desktop clearfix">
			<div class="container">
				<div class="row">
					<div class="col-md-auto hidden-md-down">
						<!-- header-first start -->
						<!-- ================ -->
						<div class="header-first clearfix">

							<!-- logo -->
							<div id="logo" class="logo">
								<a href="#"><img id="logo_img" src="./img/logo.jpg"
									width='150' alt="The SSAFY"></a>
							</div>
						</div>
					</div>
					<div class="col-lg-10 ml-auto">

						<!-- header-second start -->
						<!-- ================ -->
						<div class="header-second clearfix">

							<!-- main-navigation start -->
							<!-- classes: -->
							<!-- "onclick": Makes the dropdowns open on click, this the default bootstrap behavior e.g. class="main-navigation onclick" -->
							<!-- "animated": Enables animations on dropdowns opening e.g. class="main-navigation animated" -->
							<!-- ================ -->
							<div class="main-navigation main-navigation--mega-menu  animated">
								<nav class="navbar navbar-expand-lg navbar-light p-0">
									<div class="navbar-brand clearfix hidden-lg-up"></div>

									<!-- header dropdown buttons -->
									<div
										class="header-dropdown-buttons hidden-lg-up p-0 ml-auto mr-3">
										<div class="btn-group">

											<ul
												class="dropdown-menu dropdown-menu-right dropdown-animation"
												aria-labelledby="header-drop-3">
												<li>
													<form role="search" class="search-box margin-clear">
														<div class="form-group has-feedback">
															<input type="text" class="form-control"
																placeholder="Search"> <i
																class="fa fa-search form-control-feedback"></i>
														</div>
													</form>
												</li>
											</ul>

										</div>
									</div>
									<!-- header dropdown buttons end -->

									<button class="navbar-toggler" type="button"
										data-toggle="collapse" data-target="#navbar-collapse-1"
										aria-controls="navbar-collapse-1" aria-expanded="false"
										aria-label="Toggle navigation">
										<span class="navbar-toggler-icon"></span>
									</button>

									<div class="collapse navbar-collapse" id="navbar-collapse-1">
										<ul class="navbar-nav ml-xl-auto">
											<li class="nav-item dropdown  mega-menu mega-menu--wide">
												<a href="#" class="nav-link " id="first-dropdown"
												aria-haspopup="true" aria-expanded="false">공지사항</a>
											</li>
											<li class="nav-item dropdown mega-menu mega-menu--wide">
												<!-- <a href="foodBestList.do?id=n" class="nav-link dropdown-toggle" id="second-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">영양 정보</a> -->
												<a href="#" class="nav-link" id="second-dropdown"
												aria-haspopup="true" aria-expanded="false">오늘의 뉴스</a>
											</li>
											<li class="nav-item dropdown mega-menu mega-menu--wide">
												<!-- <a href="foodBestList.do?id=n" class="nav-link dropdown-toggle" id="second-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">영양 정보</a> -->
												<a href="${root}/userList.jsp" class="nav-link"
												id="second-dropdown" aria-haspopup="true"
												aria-expanded="false">회원 검색</a>
											</li>

											<li
												class="mustLogin nav-item dropdown  mega-menu mega-menu--wide">
												<a href="#" class="nav-link " id="first-dropdown"
												aria-haspopup="true" aria-expanded="false">주변 탐방</a>
											</li>
											<li
												class="mustLogin nav-item dropdown  mega-menu mega-menu--wide">
												<a href="#" class="nav-link " id="first-dropdown"
												aria-haspopup="true" aria-expanded="false">관심 지역 설정</a>
											</li>
											<li
												class="mustLogin nav-item dropdown  mega-menu mega-menu--wide">
												<a href="#" class="nav-link " id="first-dropdown"
												aria-haspopup="true" aria-expanded="false">관심 지역 둘러보기</a>
											</li>

										</ul>
										<!-- 돋보기 아이콘 -->
										<button type="button"
											class="btn dropdown-toggle dropdown-toggle--no-caret"
											id="header-drop-3" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">
											<i class="fa fa-search"></i>
										</button>
										<!-- main-menu end -->
									</div>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- header end -->
		<!-- <nav class="navbar navbar-expand-sm bg-white">
      
      <a class="navbar-brand" href="#"
        ><img src="./img/logo.jpg" alt="" srcset="" width="150"/></a
      >

      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#collapsibleNavbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      
      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
        <ul class="navbar-nav col-lg-10 ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">공지사항</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">오늘의 뉴스</a>
          </li>
          <li class="nav-item mustLogin">
            <a class="nav-link" href="#">주변 탐방</a>
          </li>
          <li class="nav-item mustLogin">
            <a class="nav-link" href="#">관심지역 설정</a>
          </li>
          <li class="nav-item mustLogin">
            <a class="nav-link" href="#">관심지역 둘러보기</a>
          </li>
        </ul>
      </div>
    </nav> -->
		<!--     <div style="width: 100%; height: 300px; background-color: lightgray; padding: 0;"><img src="./img/happyhouse.JPG"> -->
		<!--     </div> -->

		<!--   	  <div class="container-fluid mt-1 mb-5"> -->
		<!--       <div class="row col-lg-12"> -->
		<!--         중앙 컨텐츠 영역 -->
		<!--         <div class="col-lg-12"> -->
		<!--           Kakao Map start -->
		<!--           <form id="writeform" method="post" action="SearchServlet"> -->
		<!--            <select name="act"> -->
		<!--         	<option value="dong">동검색</option> -->
		<!--         	<option value="apt">아파트검색</option> -->
		<!--     		</select> -->
		<!--           	<div class="text-center mb-2, form-group"> -->
		<!--            		<label for="search">검색</label> -->
		<!--                 <input type="text" name="search" id="search" size="10"> -->
		<!--                 <button type="button" id="registerBtn"  -->
		<!--                 	class="btn btn-outline-primary">검색 -->
		<!--                 </button> -->
		<!--           	</div> -->
		<!--           </form> -->
		<!--         </div> -->
		<!--        </div> -->
		<!--       </div> -->

		<!--           <div class="container-fluid"> -->
		<!--             <div class="row"> -->
		<!--               <div id="aptlist" style="overflow: scroll; display: none; padding: 15px;" class="col-lg-4" > -->
		<!--                 <div id="aptTextArea">	 -->
		<!--                 </div> -->
		<!--               </div> -->
		<!--               <div id="mapArea" class="col-lg-12"> -->
		<!--                 <div id="map" style="width: 100%; height: 300px; margin: auto"> -->
		<!--                 </div> -->
		<!--               </div> -->
		<!--             </div> -->
		<!--           </div> -->
		<!--           Kakao Map end -->


		<!-- 모달창 -->
		<!-- Button to Open the Modal -->
		<!-- The Modal -->
		<div class="modal" id="signUpModal">
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="form-block p-30 light-gray-bg border-clear">
						<h2 class="title">회원가입</h2>
					</div>

					<form id="registerForm" class="form-horizontal" action=""
						method="post">
						<input type="hidden" name="action" value="join" />
						<div class="form-group has-feedback row">
							<label for="inputName"
								class="col-md-3 control-label text-md-right col-form-label">아이디
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="userid"
									placeholder="아이디를 입력하세요" required name="userid">
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputLastName"
								class="col-md-3 control-label text-md-right col-form-label">비밀번호
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="password"
									placeholder="영문 숫자 포함 6자리 이상" required name="password">
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputUserName"
								class="col-md-3 control-label text-md-right col-form-label">이름
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="name"
									placeholder="이름을 입력하세요" required name="name">
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="address"
								class="col-md-3 control-label text-md-right col-form-label">주소
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="address"
									placeholder="주소를 입력하세요" required name="address">
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputPassword"
								class="col-md-3 control-label text-md-right col-form-label">전화번호
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="phone"
									placeholder="010-xxx-xxxx" required name="phone">
							</div>
						</div>

						<div class="form-group row">
							<div class="ml-md-auto col-md-9">
								<!--  <button type="button"
                  class="btn btn-group btn-warning btn-animated">
                  등록 <i class="fa fa-check"></i>
                </button>  -->
								<Button type="button"
									class="btn btn-group btn-warning btn-animated" id="joinBtn">등록</Button>
							</div>
						</div>
					</form>


				</div>
			</div>
		</div>
		<div class="modal" id="infoModal">
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="form-block p-30 light-gray-bg border-clear">
						<h2 class="title">회원 정보 확인</h2>
					</div>

					<form class="form-horizontal" action="user" method="post">
						<input type="hidden" name="action" value="update" />
						<div class="form-group has-feedback row">
							<label for="inputName"
								class="col-md-3 control-label text-md-right col-form-label">아이디
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<label class="info_label">${userinfo.userid}</label> <input id="modifyid"
									type="text" class="info_input" name="userid" value="${userinfo.userid}" />
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputLastName"
								class="col-md-3 control-label text-md-right col-form-label">비밀번호
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<label class="info_label" class="info_input">${userinfo.password}</label>
								<input type="text" class="info_input" name="password" id="modifypassword"
									value="${userinfo.password}" />
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputUserName"
								class="col-md-3 control-label text-md-right col-form-label">이름
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<label class="info_label" class="info_input">${userinfo.name}</label>
								<input type="text" class="info_input" name="nane" id="modifyname"
									value="${userinfo.name}" />
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputEmail"
								class="col-md-3 control-label text-md-right col-form-label">주소
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<label class="info_label" class="info_input">${userinfo.address}</label>
								<input type="text" class="info_input" name="address" id="modifyaddress"
									value="${userinfo.address}" />
							</div>
						</div>
						<div class="form-group has-feedback row">
							<label for="inputPassword"
								class="col-md-3 control-label text-md-right col-form-label">전화번호
								<span class="text-danger small">*</span>
							</label>
							<div class="col-md-8">
								<label class="info_label">${userinfo.phone}</label> <input
									type="text" class="info_input" name="phone" id="modifyphone"
									value="${userinfo.phone}" />
							</div>
						</div>

						<div class="form-group row">
							<div class="ml-md-auto col-md-9">
								<!-- <button type="button" 
                  class="btn btn-group btn-warning btn-animated">
                  수정 <i class="fa fa-check"></i>
                </button> -->
								<button type="button" id="updateButton"
									class="btn btn-group btn-warning btn-animated">수정</button>
								<button type="button" id="beforeUpdate"
									class="btn btn-group btn-warning btn-animated"
									data-target="#updateModal">
									수정 <i class="fa fa-check"></i>
								</button>
								<button type="button" id="delete"
									class="btn btn-group btn-warning btn-animated"
									data-target="#updateModal">
									삭제 <i class="fa fa-check"></i>
								</button>
							</div>
						</div>
					</form>


				</div>
			</div>
		</div>





		<!-- 검색화면 -->
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px; padding: 100px;">
				<div class="card-body">
					시도 : <select id="sido">
						<option value="0">선택</option>
					</select> 구군 : <select id="gugun">
						<option value="0">선택</option>
					</select> 읍면동 : <select id="dong">
						<option value="0">선택</option>
					</select>
					<input type="text" placeholder="아파트이름 ex) 롯데캐슬" id="aptname" class="dropdown-toggle" data-toggle="dropdown" />
					<div class="dropdown-menu">
      <div class="dropdown-item0" role="button"></div>
      <div class="dropdown-item1" role="button"></div>
      <div class="dropdown-item2" role="button"></div>
    </div>
					<button type="button" id="aptSearchBtn">검색</button>
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="150">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th>건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
					<div id="map" style="width: 100%; height: 500px;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5a0ddd48299a11df3f4a5c1736cc60f0&libraries=services"></script>
					<script type="text/javascript" src="js/map.js"></script>
			<script type="text/javascript">
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){					
					$.get(root + "/happyhouse" + "/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
				$(document).on("change", "#sido", function() {
					$.get(root + "/happyhouse" + "/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get(root + "/happyhouse" + "/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#dong", function() {
					$.get(root + "/happyhouse" + "/map/apt"
							,{dong: $("#dong").val()}
							,function(data, status){
								console.log(data.length)
								$("tbody").empty();
								let idx = 0;
								for(let i = 0; i < data.length; i++) {
									$("tbody").append("<tr><td>" + data[i].aptCode + "</td><td>" + data[i].aptName + "</td><td>" + data[i].sidoName + data[i].gugunName + data[i].dongName + data[i].jibun + "</td><td>" + data[i].buildYear + "</td><td>" + data[i].recentPrice + "</td></tr>")
								}
								
// 								var tc = new Array();
// 								var html = '';
								$.each(data, function(index, vo) {
// 									console.log(vo.aptCode, vo.aptName)
// 									let str = `
// 										<tr class="${colorArr[index%3]}">
// 											<td>${vo.aptCode}</td>
// 											<td>${vo.aptName}</td>
// 											<td>${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}</td>
// 											<td>${vo.buildYear}</td>
// 											<td>${vo.recentPrice}</td>
// 										</tr>
// 									`;
									

// 									$("tbody").append(str);
// 									console.log(vo.aptCode)
// 									tc.push({a: vo.aptCode, b: vo.aptName, c: vo.sidoName, d: vo.gugunName, e: vo.dongName, f: vo.jibun, g: vo.buildYear, h: vo.recentPrice})
// // 									for(key in tc){
// // 										console.log(tc[key])
// 										html += '<tr>';
// 										html += '<td>'+tc[key].a+'</td>';
// 										html += '<td>'+tc[key].b+'</td>';
										
// 										html += '<td>'+tc[key].c + ' ' + tc[key].d + ' ' + tc[key].e + ' ' + tc[key].f + '</td>';
										
// 										html += '<td>'+tc[key].g+'</td>';
// 										html += '<td>'+tc[key].h+'</td>';
// 										html += '</tr>';
// // 									}
// 									$("#searchResult").append(html)
								});
								displayMarkers(data);
							}
							, "json"
					);
				});
				
				$(document).on("click", "#aptSearchBtn", function() {
					var param = {
							serviceKey:'Ur7nNqGxvuGAogyV9044US98LEK+y6SLSJIdS+ia1GFHAAMIY6uv+LBh39CKeFfhj5sjjlgVfUGJ+ODgfMYjtA==',
							pageNo:encodeURIComponent('1'),
							numOfRows:encodeURIComponent('10'),
							LAWD_CD:encodeURIComponent($("#gugun").val()),
							DEAL_YMD:encodeURIComponent('202110')
					};
					$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
							,param
							,function(data, status){
								var items = $(data).find('item');
								var jsonArray = new Array();
								items.each(function() {
									var jsonObj	= new Object();
									jsonObj.aptCode = $(this).find('일련번호').text();
									jsonObj.aptName = $(this).find('아파트').text();
									jsonObj.dongCode = $(this).find('법정동읍면동코드').text();
									//jsonObj.dongName = $(this).find('').text();
									//jsonObj.sidoName = $(this).find('').text();
									//jsonObj.gugunName = $(this).find('').text();
									jsonObj.buildYear = $(this).find('건축년도').text();
									jsonObj.jibun = $(this).find('지번').text();
									jsonObj.recentPirce = $(this).find('거래금액').text();
										
									jsonObj = JSON.stringify(jsonObj);
									//String 형태로 파싱한 객체를 다시 json으로 변환
									jsonArray.push(JSON.parse(jsonObj));
								});
								console.log(jsonArray);
								//displayMarkers(jsonArray);
							}
							, "xml"
					);
// 					검색버튼을 누르면 api를 사용해서 실제 전국에 있는 아파트들을 가져올 수 있음
//					문제는 마커를 찍으려면 json만 되므로 xml을 json으로 바꿔줘야함
// 					검색버튼 누르면 입력된 아파트 이름 값을 가져와서 select문으로 가격 이용해서 아파트 명 검색해서 해당 아파트만 아래에 나오게하기


// 					 $("#in").keyup(function() {
// 				        $("#out").val( $(this).val() )
// 				    });
						
					$(document).on("click", "#aptSearchBtn", function() {
						// apt에 보내서
						$.get(root + "/happyhouse" + "/map/aptname"
								,{aptname: $("#aptname").val()} 
								,function(data, status){
									$("tbody").empty();
									let idx = 0;
									for(let i = 0; i < data.length; i++) {
										$("tbody").append("<tr><td>" + data[i].aptCode + "</td><td>" + data[i].aptName + "</td><td>" + data[i].sidoName + data[i].gugunName + data[i].dongName + data[i].jibun + "</td><td>" + data[i].buildYear + "</td><td>" + data[i].recentPrice + "</td></tr>")
									}
// 									$("tbody").empty();
// 									var tc = new Array();
// 									var html = '';
// 									$.each(data, function(index, vo) {
// 										console.log(vo.aptCode, vo.aptName)
//	 									let str = `
//	 										<tr class="${colorArr[index%3]}">
//	 											<td>${vo.aptCode}</td>
//	 											<td>${vo.aptName}</td>
//	 											<td>${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}</td>
//	 											<td>${vo.buildYear}</td>
//	 											<td>${vo.recentPrice}</td>
//	 										</tr>
//	 									`;
//	 									$("tbody").append(str);
// 										tc.push({a: vo.aptCode, b: vo.aptName, c: vo.sidoName, d: vo.gugunName, e: vo.dongName, f: vo.jibun, g: vo.buildYear, h: vo.recentPrice})
// 										for(key in tc){
// 											html += '<tr class="${colorArr[index%3]}">';
// 											html += '<td>'+tc[key].a+'</td>';
// 											html += '<td>'+tc[key].b+'</td>';
											
// 											html += '<td>'+tc[key].c + ' ' + tc[key].d + ' ' + tc[key].e + ' ' + tc[key].f + '</td>';
											
// 											html += '<td>'+tc[key].g+'</td>';
// 											html += '<td>'+tc[key].h+'</td>';
// 											html += '</tr>';
// 										}
// 										$("#searchResult").append(html)
// 									});
									displayMarkers(data);
								}
								, "json"
						);
					});
					
					
					// 
					
					/* var xhr = new XMLHttpRequest();
					var url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
					var queryParams = '?' + encodeURIComponent('serviceKey') + '='+encodeURIComponent(' API KEY ');
					queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); 
					queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); 
					queryParams += '&' + encodeURIComponent('LAWD_CD') + '=' + encodeURIComponent($("#gugun").val()); 
					queryParams += '&' + encodeURIComponent('DEAL_YMD') + '=' + encodeURIComponent('202110'); 
					xhr.open('GET', url + queryParams);
					xhr.onreadystatechange = function () {
					    if (this.readyState == 4) {
					    	console.log(this.responseXML);
					        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
					    }
					};

					xhr.send(''); */
				});
				</script>
				</div>
			</div>
		</section>


		<footer id="footer" class="clearfix ">
			<div class="footer">
				<div class="container">
					<div class="footer-inner">
						<div class="row">
							<div class="col-md-3">
								<div class="footer-content">
									<img alt="" src="img/ssafy_logo.png" width="100">
								</div>
							</div>
							<div class="col-md-8">
								<div class="footer-content">
									<h2 class="title">Find Us</h2>
									<div class="separator-2"></div>
									<ul class="list-icons">
										<li><i class="fa fa-map-marker pr-3 text-default"></i>(SSAFY)
											서울시 강남구 테헤란로 멀티스퀘어</li>
										<li><i class="fa fa-phone pr-3 text-default"></i>
											1544-9001</li>
										<li><a href="#"><i class="fa fa-envelope pr-3"></i>admin@ssafy.com</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="subfooter">
				<div class="container">
					<div class="subfooter-inner">
						<div class="row">
							<div class="col-md-12">
								<p class="text-center">Copyright by SSAFY. All rights
									reserved.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
</body>
</html>