	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='index.css'>
    <link href="https://fonts.googleapis.com/css2?family=Exo+2:wght@500;600&family=Lato&display=swap" rel="stylesheet">   
</head>
<body>
    <!--sidevbar-->
    <div class="sidebar">
        <h1 class="logo">Work Out <ion-icon class="btn-close" name="close-outline"></ion-icon></h1>
        <div class="menus">
            <a href="#"><ion-icon name="home-outline"></ion-icon>Home</a>
            <a href="#"><ion-icon name="bag-outline"></ion-icon>Shop</a>
            <label>
            <a href="#"><input type="checkbox" class="box"></ion-icon><ion-icon class="sun" name="sunny-outline"></ion-icon><ion-icon class="moon" name="moon-outline"></ion-icon>DisplayMode</a>
            </label>
            <a href="/board/board_write.jsp"><ion-icon name="barbell-outline"></ion-icon>Write</a>
            <a href="#"><ion-icon name="extension-puzzle-outline"></ion-icon>Service</a>
            <a href="#"><ion-icon name="settings-outline"></ion-icon>Setting</a>
        </div>
    <div class="profile">
        <img class="profile-img" src="/image/profile.jpg">
        <div class="proflie-name">
            <h4>Test님</h4>
            <p>환영합니다</p>
        </div>
    </div>
</div>    

<!--main-->
<span class="animateBg">
<div class="main">
   
    <div class="main-header">
        <ion-icon class="menu-bar " name="menu-outline"></ion-icon>
        <div class="search">
            <input type="text" placeholder="검색어를 입력해주세요">
            <button class="btn-search"><ion-icon name="search-outline"></ion-icon></button>
        </div>
    </div>
    <div class="filter-wrapper">
        <br>
        <div class="filter">
            <a href="index.jsp"><button class="btn-filter">공지사항</button></a>
            <a href="index2.jsp"><button class="btn-filter">오운완</button></a>
            <a href="index3.jsp"><button class="btn-filter">초급자 운동추천</button></a>
            <a href="index4.jsp"><button class="btn-filter">중급자 운동추천</button></a>
            <a href="index5.jsp"><button class="btn-filter">고급자 운동추천</button></a>
            <a href="index6.jsp"><button class="btn-filter" id="youtube">추천 유튜브</button></a>
        </div>
    </div>
    <div class="sort">
        <div class="sort-list">
            <select>
                <option value="0">모든글</option>
                <option value="1">최신순</option>
                <option value="2">오래된 순</option>
                <option value="2">좋아요 순</option>
                <option value="3">조회 순</option>
            </select>
        </div>
    </div>
    <div class="wrapper">
        <div class="card">
            <div class="card-left blue-bg">
                <img src="/image/profile.jpg">
            </div> 
            <div class="card-center">
                <h3 name="subject">안녕하세요</h3>
                <p class="card-name">글쓴이</p>
                <div class="card-sub">
                    <p>1분전</p>
                    <p>조회수: 5</p>
                </div>
            </div>
        </div>
    </div>
     <div class="wrapper">
        <div class="card">
            <div class="card-left blue-bg">
                <img src="/image/profile.jpg">
            </div> 
            <div class="card-center">
                <h3 name="subject">안녕하세요</h3>
                <p class="card-name">글쓴이</p>
                <div class="card-sub">
                    <p>1분전</p>
                    <p>조회수: 5</p>
                </div>
            </div>
        </div>
    </div>
    <div class="wrapper">
        <div class="card">
            <div class="card-left blue-bg">
                <img src="/image/profile.jpg">
            </div> 
            <div class="card-center">
                <h3 name="subject">안녕하세요</h3>
                <p class="card-name">글쓴이</p>
                <div class="card-sub">
                    <p>1분전</p>
                    <p>조회수: 5</p>
                </div>
            </div>
        </div>
    </div>

  
    
</div>
</span>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src="app.js"></script>

</html>