<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.board2.*,model.member2.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='/Style/style1.css'>
    <link href="https://fonts.googleapis.com/css2?family=Exo+2:wght@500;600&family=Lato&display=swap" rel="stylesheet">   
    <script>
	function search(){
		if(board.key.value==""){
			alert("검색어를 입력해주세요");
			board.key.focus();
			return;
		}
		board.submit();
	}
	function send(){
		alert("로그인후 이용 가능합니다");
		
	}
</script>
</head>
<body>
    <!--sidevbar-->
    <div class="sidebar">
        <h1 class="logo">Work Out <ion-icon class="btn-close" name="close-outline"></ion-icon></h1>
        <div class="menus">
            <a href="/Board/board_list.do?page=${page}"><ion-icon name="home-outline"></ion-icon>Home</a>
            <a href="#"><ion-icon name="bag-outline"></ion-icon>Shop</a>
            <label>
            <a href="#"><input type="checkbox" class="box"></ion-icon><ion-icon class="sun" name="sunny-outline"></ion-icon><ion-icon class="moon" name="moon-outline"></ion-icon>DisplayMode</a>
            </label>
            <%if(session.getAttribute("user")==null){ %>
            <a href="javascript:send()"><ion-icon name="barbell-outline"></ion-icon>Write</a>
            <%}else{%> 
            <a href="/Board/board_write.do?page=${page}"><ion-icon name="barbell-outline"></ion-icon>Write</a>
            <%}%>
            <a href="#"><ion-icon name="extension-puzzle-outline"></ion-icon>Service</a>
            <a href="#"><ion-icon name="settings-outline"></ion-icon>Setting</a>
        </div>
        <%if(session.getAttribute("user")==null){ %>
        <div class="profile">
        <a class="login" href="/Member/userinfo_login.do"><ion-icon name="enter"></ion-icon></a>
        <div class="proflie-name">
            <h4></h4>
            <p></p>
        </div>
    </div>
</div>    
            <%}else{%> 
    <div class="profile">
        <img class="profile-img" src="/image/profile.jpg">
        <div class="proflie-name">
            <h4>${user.userid}님</h4>
            <p>환영합니다</p>
            <p><a href="/Member/userlogin_out.do"><ion-icon name="exit"></ion-icon></a></p>
            
        </div>
    </div>
</div>    
<%}%>
<!--main-->
<span class="animateBg">
<div class="main">
   
    <div class="main-header">
    
        <ion-icon class="menu-bar " name="menu-outline"></ion-icon>
        <form class="form-box" name="board" method="get" action="/Board/board_list.do">	
        <div class="search">
         <select class="select"name="search">
		   <option value="subject"<c:if test="${search=='subject'}">selected </c:if>>글제목</option>
		   <option value="name"<c:if test="${search=='name'}">selected </c:if>>작성자</option>
	       <option value="contents"<c:if test="${search=='contents'}">selected </c:if>>글내용</option>
	   </select> 
	   
            <input type="text" placeholder="검색어를 입력해주세요"  name="key" value="${key}">
            <c:if test="${empty(boardid)}">
            </c:if>
            <c:if test="${!empty(boardid)}">
            <input type="hidden" name="boardid" value="${boardid}">
            </c:if>
            <a href="javascript:search()" ><button class="btn-search"><ion-icon name="search-outline"></ion-icon></button></a>
        </div>
        </form>
    </div>
    <div class="filter-wrapper">
        <br>
        <div class="filter">
            <a href="/Board/board_list.do?page=${page}"><button class="btn-filter">전체 게시판</button></a>
            <a href="/Board/board_list.do?page=${page}&boardid=1"><button class="btn-filter">자유게시판</button></a>
            <a href="/Board/board_list.do?page=${page}&boardid=2"><button class="btn-filter">초급자 운동공유</button></a>
            <a href="/Board/board_list.do?page=${page}&boardid=3"><button class="btn-filter">중급자 운동공유</button></a>
            <a href="/Board/board_list.do?page=${page}&boardid=4"><button class="btn-filter">고급자 운동공유</button></a>
            <a href="/Board/board_list.do?page=${page}&boardid=5"><button class="btn-filter" id="youtube">추천 유튜브</button></a>
        </div>
    </div>
    
    <c:if test="${empty(list)}">
      <h3>등록된글이 없습니다</h3>
    </c:if>
    <c:if test="${!empty(list)}">
     
    <c:forEach var="board" items="${list}">
    <div class="wrapper">
        <div class="card">
            <div class="card-left blue-bg">
                <img src="/image/profile.jpg">
            </div> 
            <div class="card-center">
                <h3 name="subject"><a class="list" href="/Board/board_view.do?idx=${board.idx}&page=${page}">${board.subject}</a></h3>
                <p class="card-name">${user.userid}</p>
                <div class="card-sub">
                    <p>${board.regdate.substring(0,10)}</p>
                    <p>${board.readcnt}</p>
                </div>
            </div>
        </div>
    </div>
    <c:set var="listcount" value="${listcount-1}"/>
	</c:forEach>
    </c:if>
    
     <div align="center">${pageSkip}</div>

  
    
</div>
</span>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src="/Style/app.js"></script>

</html>