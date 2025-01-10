<%@page import="himedia.kdt.myhome.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	<div id="header">
      <h1>My Homepage</h1>
      <ul>
      	<c:choose>
      		<c:when test="${empty authUser }">
      			<li><a href="<%= request.getContextPath() %>/users?a=joinform">회원가입</a>
      			<li><a href="<%= request.getContextPath() %>/users?a=loginform">로그인</a>
      		</c:when>
      		<c:otherwise>
      			<li>${sessionScope.authUser.name }님, 안녕하세요.</li>
      			<li><a href="<%= request.getContextPath() %>/users?a=logout">로그아웃</a>
      		</c:otherwise>
      	</c:choose>
      </ul>
    </div> <!-- /header -->