<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
<!-- TODO: 현재 페이지에 적절한 CSS를 임포트하십시오. -->
<link type="text/css" 
	rel="stylesheet" 
	href="<%= request.getContextPath() %>/css/users.css"/>
<script src="<%= request.getContextPath() %>/javascript/users.js" /></script>
</head>
<body>
  <div id="container">
    <!-- div id="header">
      <h1>My Homepage</h1>
    </div --> <!-- /header -->
    <jsp:include page="/WEB-INF/views/includes/header.jsp">
    	<jsp:param name="param1" value="value1" />
    	<jsp:param name="param2" value="value2" />
    </jsp:include>
    <!-- div id="navigation">
      <ul>
        <li><a href="<%= request.getContextPath() %>/">My Home</a></li>
        <li><a href="<%= request.getContextPath() %>/guestbook">방명록</a></li>
        <li><a href="<%= request.getContextPath() %>/board">게시판</a></li>
      </ul>
	</div -->
	<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
	<div id="wrapper">
      <div id="content">
		
		<%
		String errorMsg = (String)request.getAttribute("error_msg");
		if (errorMsg != null) {
			%>
			<p style="color: darkred"><%= errorMsg %></p>
			<% 
		}
		%>
		<form id="login-form" 
        	name="loginform" 
        	method="POST" 
        	action="<%= request.getContextPath() %>/users">
        	<input type="hidden" name="a" value="login" /> 
        
        	<label class="block-label" for="email">이메일</label> 
        	<input id="email" name="email" type="text" value=""> 

        	<label class="block-label">패스워드</label> 
        	<input name="password" type="password" value="">

        	<input type="submit" value="로그인">
    	</form>
    
		</div>
	</div>
	<!-- div id="footer">
      <p>Copyright(c) 2025 Himedia All rights reserved. </p>
	</div -->
	<!-- 동적으로 반응할 필요가 없는 경우 @include -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>
  </div>
</body>
</html>

