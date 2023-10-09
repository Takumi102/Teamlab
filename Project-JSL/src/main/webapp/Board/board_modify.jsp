<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>Page Title</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel='stylesheet' href='/Style/board.css'>
<link
   href="https://fonts.googleapis.com/css2?family=Exo+2:wght@500;600&family=Lato&display=swap"
   rel="stylesheet">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
   function send() {
      alert("글을 등록합니다");
      board.submit();
   }
   function check() {
      //직업(select)
      if (check.boardid.selectedIndex == 0) {
         alert("게시판을 선택하세요")
         check.boardid.focus();
         return;
      }

      check.action = "/Board/board_modify.do";
      check.submit();
   }
</script>
</head>
<%@ include file="toastUIEditorLib.jsp"%>
<body>
   <div>
      <h1>글쓰기</h1>
      <form name="board" method="post" onsubmit="submitForm(this); return false;">
         <input type="hidden" name="page" value="${page}">
         <input type="hidden" name="contents" >
         <div class="subject">
            <select name="boardid" class="board">
               <option>게시판을 선택하세요</option>
               <option value="1">자유 게시판</option>
               <option value="2">초급자 운동추천</option>
               <option value="3">중급자 운동추천</option>
               <option value="4">고급자 운동추천</option>
            </select> <input type="text" name="subject" placeholder="제목을 입력하세요" value="${board.subject}">

         </div>

         <!-- 에디터를 적용할 요소 (컨테이너) -->
         <div class="toast-ui-editor">
            <script type="text/x-template"></script>
            ${board.contents}
         </div>
         <div class="btn-insert">
            <button class="btn">등록</button>
            <button class="btn">
               <a href="/Board/board_list.do?page=${page}">취소</a>
            </button>
         </div>
      </form>
   </div>

</body>
<script type="module"
   src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
   src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</html>