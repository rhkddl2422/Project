<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세페이지</title>
<link rel="stylesheet" type="text/css" href="/tabi/resources/css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/tabi/resources/js/common.js"></script>
</head>
<body>
<header>
<div class="wrap">
<div class="logo"><a href="/tabi">TABI</a></div>
<span class="toggle" onclick="openNav()"><span class="open">&#9776;</span><span class="close">X</span></span>
<div class="menu"><ul><li><a href="/tabi/newticketform">상풍등록</a></li>
<li><a href="./boardlist">리스트</a></li>
<li><a href="/tabi/session/login">로그인</a></li><li><a href="/tabi/mybatis/insertmember">회원가입</a></li></ul></div></div>
</header>
<div class="wrap"><h1>회원가입</h1></div>
<hr>
<section class="register">
<div class="wrap">

<form action="http://localhost:8080/tabi/mybatis/insertmember" method="post" >
<label>아이디 : </label> <input type=text   name="userid" ><br>
<label>패스워드 : </label><input type="password"  name="password" ><br>
<label>이름 : </label><input type=text    name="name" ><br>
<label>전화번호 : </label><input type="text"   name="phone" ><br>
   <input type=submit value="회원가입">
</form>

</div>
</section>
<hr>
<footer><div class="wrap">
<div class="logo">TABI</div>
<div class="content"><h1>(유)타비코리아</h1>
<h5>서울시 역삼동 멀티캠퍼스 6층|<br>대표 : 구기영|사업자등록번호:211-88-68802 사업자정보확인통신판매업신고:제 2018-서울서초-2635호|<br>개인정보담당자 : privacy@yogiyo.co.kr|<br>제휴문의 : partnership@deliveryhero.co.kr|<br>고객만족센터 : support@yogiyo.co.kr|<br>호스트서비스사업자 : (주)심플렉스인터넷</h5>


</div>
</div><!-- end wrap-->
</footer>
</body>
</html>