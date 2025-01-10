package himedia.kdt.myhome.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

//	@WebFilter("/*") => css를 text/html로 읽어서 mysite.css를 못 불러오는 문제 발생
//	해결방법 1. cookie/* 만 filter를 통과하게끔 코드 수정
//	해결방법 2. cookie 내부에 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
//	직접 설정 후 filter 삭제

@WebFilter("/cookie/*")
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		chain.doFilter(req, resp);
		
	}

}
