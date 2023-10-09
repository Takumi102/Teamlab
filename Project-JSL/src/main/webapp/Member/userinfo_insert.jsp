<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='userinsert.css'>
    <link href="https://fonts.googleapis.com/css2?family=Exo+2:wght@500;600&family=Lato&display=swap" rel="stylesheet">   
</head>
<script>
let validLoginId = '';

function send(){
	if(register.username.value==""){
		alert("이름을 입력해주세요");
		register.username.focus();
		return;
	}
	if(register.userid.value==""){
		alert("아이디를 입력해주세요");
		register.userid.focus();
		return;
	}else if(!checkEngNum(register.userid.value)){
		alert("아이디는 영문, 숫자만 입력가능합니다");
		register.userid.focus();
		return;
	}
	if(register.userid.value != validLoginId.value) {
		alert("해당 아이디는 중복된 아이디입니다");
		register.userid.value = '';
		register.userid.focus();
		return;
	}
	if(register.password.value==""){
		alert("비밀번호를 입력해주세요");
		register.password.focus();
		return;
	}else if(!checkEngNum(register.password.value)){
		alert("비밀번호는 영문, 숫자만 입력가능합니다");
		register.password.focus();
		return;
	}
	if(register.password.value!=register.repassword.value){
		alert("비밀번호를 확인해주세요");
		register.repassword.focus();
		return;
	}
	register.submit();
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




function passCheck(){
	var correctColor1 = "#009000";
	var wrongColor1 = "#ff0000"
	
	if(register.password.value==""){
		configMsg1.style.color = wrongColor1;
		configMsg1.innerHTML = "비밀번호를 입력해주세요";
	} else {
		configMsg1.innerHTML = '';
	}
	if(register.repassword.value==""){
		configMsg2.style.color = wrongColor1;
		configMsg2.innerHTML = "비밀번호를 입력해주세요";
	}
	if(register.repassword.value) {
		if(register.password.value==register.repassword.value){
			configMsg2.style.color = correctColor1;
			configMsg2.innerHTML = "비밀번호 일치";
		}else{
			configMsg2.style.color = wrongColor1;
			configMsg2.innerHTML = "비밀번호 불일치";
		}
	}
}

</script>
<body>
    
    <div class="wrapper">
        <a href="javascript:history.back()"><ion-icon class="btn-close" name="close"></ion-icon></a>
        <form method="post" name="register" action="/Member/userinfo_insert.do">
            <h1>Register</h1>
            
            <div class="input-box">
                <span class="icon">
                    <ion-icon name="person"></ion-icon>
                </span>
                <input name="name" type="text" placeholder="Name" required >
            </div>
            
            <div class="input-box">
                <span class="icon">
                    <ion-icon name="person"></ion-icon>
                </span>
                <input name="userid" type="text" placeholder="UserId" required ">
            </div>

            <div class="input-box">
                <span class="icon">
                    <ion-icon name="person"></ion-icon>
                </span>
                <input name="email" type="email" placeholder="Email" required>
            </div>

            <div class="input-box">
                <span class="icon">
                    <ion-icon name="lock-closed"></ion-icon>
                </span>
                <input name="password" type="password" placeholder="Password" required onblur="passCheck(this)">
                <div id="configMsg1"></div>
            </div>

            <div class="input-box">
                <span class="icon">
                    <ion-icon name="lock-closed"></ion-icon>
                </span>
                <input name="repassword" type="password" placeholder="Repassword" required onblur="passCheck(this)">
                <div id="configMsg2"></div>
            </div>


            <button tpye="submit" class="btn">Sign up</button>

            <div class="register-link">
                <p>Have an account?
                    <a href="#">Login</a></p>
            </div>
        </form>
    </div>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src='http://code.jquery.com/jquery-2.2.4.min.js'></script>
</html>