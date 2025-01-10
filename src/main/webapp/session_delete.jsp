<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
//	개별 세션 삭제
/*
session.removeAttribute("sess1");
session.removeAttribute("sess2");
*/

//	전체 세션 무효화
session.invalidate();
response.sendRedirect("session_read.jsp");
%>
