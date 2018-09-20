<%@ page contentType="text/html; charset=UTF-8"%>
	
	<div id="left_menu">
    <script>
    function login_chk(){
    	if(window.document.f.id.value==""){
    		alert('아이디를 입력하세요.');
    		f.id.focus();
    		return false;
    	}
    	if($.trim($('#pwd').val())==""){
    		alert('비번을 입력하세요.');
    		$('#pwd').val('').focus(); //공백처리 후 포커스
    		return false;
    	}
    }
    
    function pwd_find(){
    	window.open("./pwd_Find.do","비번찾기","width=300px,height=300px,scrollbars=yes");
    }
    
    </script>
    <form name="f" method="post" action="member_Login_ok.do" onsubmit="return login_chk()">
      <table id="login_t">
        <tr>
          <th>아이디</th>
          <td><input type="text" name="id" id="id" class="input_box" size="14"></td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td><input type="password" name="pass" id="pwd" size="14" class="input_box"></td>
        </tr>
      </table>
      <div id="login_menu">
        <input type="submit" value="로그인" class="input_b">
        <input type="reset" value="취소" class="input_b" onclick="">
        <input type="button" value="비번찾기" class="input_b" onclick="pwd_find()">
      </div><!-- login_menu 끝 -->
    </form>
    </div><!-- left_menu 끝 -->
