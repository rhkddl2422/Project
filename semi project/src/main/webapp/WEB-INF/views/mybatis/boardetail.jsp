<%-- <%@page import="test.BoardVO"%>
<%@page import="java.util.ArrayList"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상세보기</title>
<link rel="stylesheet" type="text/css"
	href="/tabi/resources/css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/tabi/resources/js/common.js"></script>
</head>
<body>
	<header>
	<div class="wrap">
		<div class="logo">
			<a href="/tabi">TABI</a>
		</div>
		<span class="toggle" onclick="openNav()"><span class="open">&#9776;</span><span
			class="close">X</span></span>
		<div class="menu">
			<ul>
				<li><a href="/tabi/newticketform">상품등록</a></li>
				<li><a href="/tabi/boardlist">리스트</a></li>
				<li><a href="/tabi/session/login">로그인</a></li>
				<li><a href="/tabi/mybatis/insertmember">회원가입</a></li>
			</ul>
		</div>
	</div><!--  end wrap -->
	</header>
	<div class="wrap">
		<h1>상세보기</h1>
	</div>
	<hr>
	<section class="detail">
	<div class="wrap">
		<div class="summary">
				<ul>
					<li><h2>제품명 :${ticket.t_name}</h2></li>
					<li>제품내용 : ${ticket.contents}</li>
					<li>사용기간 : ${ticket.bookdate}</li>
					
				</ul>

				
	
		        <ul>
		        <button type="button" class="order" ><a href="./orderresult">주문하기</a></button>
				<button type="button" class="order" ><a href="/tabi/mybatis/updateform?seq=${ticket.seq}">수정하기</a></button>
				<button type="button" class="order" ><a href="/tabi/mybatis/deleteticket?seq=${ticket.seq}">삭제하기</a></button>
				<button type="button" class="order" onclick="goBack()">뒤로가기</button>
				</ul>
	
		<!--top-->
		<hr>
		<ul><h3>제품설명</h3>
		<div class="contents">
			${ticket.contents }
		</div>
		</ul>
</div>
	    </div>
	</section>

	<hr>
	<footer>
	<div class="wrap">
		<div class="logo">TABI</div>
		<div class="content">
			<h1>(유)타비코리아</h1>
			<h5>
				서울시 역삼동 멀티캠퍼스 6층|<br>대표 : 구기영|사업자등록번호:211-88-68802
				사업자정보확인통신판매업신고:제 2018-서울서초-2635호|<br>개인정보담당자 :
				privacy@yogiyo.co.kr|<br>제휴문의 : partnership@deliveryhero.co.kr|<br>고객만족센터
				: support@yogiyo.co.kr|<br>호스트서비스사업자 : (주)심플렉스인터넷
			</h5>


		</div>
	</div>
	<!-- end wrap--> </footer>
</body>
</html>







