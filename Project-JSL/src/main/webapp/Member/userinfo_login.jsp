<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='userlogin.css'>
    <link href="https://fonts.googleapis.com/css2?family=Exo+2:wght@500;600&family=Lato&display=swap" rel="stylesheet">   
    <script>
	function send(){
		if(login.userid.value==""){
			alert("아이디를 입력해주세요");
			login.userid.focus();
			return;
		}else if(!checkEngNum(login.userid.value)){
			alert("아이디는 영문, 숫자만 입력가능합니다");
			login.userid.focus();
			return;
		}
		if(login.password.value==""){
			alert("비밀번호를 입력해주세요");
			login.password.focus();
			return;
		}else if(!checkEngNum(login.password.value)){
			alert("비밀번호는 영문, 숫자만 입력가능합니다");
			login.password.focus();
			return;
		}
		login.submit();
	}
	
	function checkEngNum(str){
		lower_str = str.toLowerCase();
		
		for(i=0; i<lower_str.length; i++){
			ch = lower_str.charAt(i);
			if(((ch < 'a') || (ch > 'z')) && ((ch < '0') || (ch > '9'))){
				return 0;
			}
			return 1;
		}
	}
</script>
</head>
<body>
    
    <div class="wrapper">
        <a href="javascript:history.back()"><ion-icon class="btn-close" name="close"></ion-icon></a>
            <form name="login" method="post" action="/Member/userinfo_login.do" >
            <h1>Login</h1>
            
            <div class="input-box">
                <span class="icon">
                    <ion-icon name="person"></ion-icon>
                </span>
                <input name="userid" type="text" placeholder="UserId" required>
                
            </div>
            <div class="input-box">
                <span class="icon">
                    <ion-icon name="lock-closed"></ion-icon>
                </span>
                <input name="password" type="password" placeholder="Password" required>
                
            </div>

            <div class="remember-forgot">
                <label><input type="checkbox">Remember me</label>
                <a href="#">Forgot password?</a>
            </div>

            <button type="submit" class="btn">Login</button>
            
            <div class="register-link">
                <p>Don't have an account
                    <a href="/Member/userinfo_insert.do">Register</a></p>
            </div>
        </form>
    </div>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
</html>