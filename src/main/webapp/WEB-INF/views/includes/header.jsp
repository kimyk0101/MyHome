<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="header">
      <h1>My Homepage</h1>
      <ul>
      	<li><a href="<%= request.getContextPath() %>/users?a=joinform">회원가입</a>
      	<li><a href="<%= request.getContextPath() %>/users?a=loginform">로그인</a>
      </ul>
    </div> <!-- /header -->