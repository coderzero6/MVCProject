<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<div class="clear"></div>

<div id="article">
  <%@ include file="../include/login.jsp" %>
  <div id="article_c">
    <!-- 관리자 로그인 입력화면 구성 영역 -->
    <div id="aIndex_wrap">
      <h2 class="aIndex_title">관리자 로그인</h2>
      <form method="post" action="admin_login_ok.do" onsubmit="return adminLogin_check()">
        <table id="aIndex_t">
          <tr>
            <th>관리자 아이디</th>
            <td><input name="admin_id" id="admin_id" class="admin_box"/></td>
          </tr>
          <tr>
            <th>관리자 비번</th>
            <td><input type="password" name="admin_pwd" id="admin_pwd" class="admin_box"/></td>
          </tr>
        </table>
        <div id="aIndex_menu">
          <input type="submit" value="로그인" class="admin_b"/>
          <input type="reset" value="취소" class="admin_b">
        </div><!-- aIndex_menu 끝 -->
      </form>
    </div><!-- aIndex_wrap 끝 -->
  </div><!-- article_c 끝 -->
</div><!-- article 끝 --> 

<div class="clear"></div>
<%@ include file="../include/footer.jsp" %>