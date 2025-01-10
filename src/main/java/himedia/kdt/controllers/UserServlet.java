package himedia.kdt.controllers;

import java.io.IOException;

import himedia.kdt.myhome.dao.UserDao;
import himedia.kdt.myhome.dao.UserDaoImpl;
import himedia.kdt.myhome.vo.UserVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/users")
public class UserServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
		
		if ("joinform".equals(actionName)) {
			//	가입 폼 보여주기
			RequestDispatcher rd = 
					req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);
		} else if ("joinsuccess".equals(actionName)) {
			RequestDispatcher rd = 
					req.getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);
		} else if ("loginform".equals(actionName)) {
			String result = req.getParameter("result");
			if ("fail".equals(result)) {
				req.setAttribute("error_msg", "로그인에 실패했습니다.");
			}
			RequestDispatcher rd = 
					req.getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		} else if ("logout".equals(actionName)) {
			HttpSession session = req.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			resp.sendRedirect(req.getContextPath());
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
				
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
		
		if ("join".equals(actionName)) {
			//	가입 작업 수행
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			UserVo userVo = new UserVo(name, password, email, gender);
			
			UserDao dao = new UserDaoImpl(dbUser, dbPass);
			
			boolean success = dao.insert(userVo);
			
			if (success) {
				resp.sendRedirect(
						req.getContextPath() + "/users?a=joinsuccess" );
			} else {
				resp.getWriter().println("<h1>Error</h1>");
			}
		} else if ("login".equals(actionName)) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			UserDao dao = new UserDaoImpl(dbUser, dbPass);
			UserVo vo =dao.getUserByIdAndPassword(email, password);	//	사용자 정보 찾기
			
			System.out.println("Login User: " + vo);
			
			if (vo != null) {	//	사용자 정보 찾음
				HttpSession session = req.getSession();
				session.setAttribute("authUser", vo);
				resp.sendRedirect(req.getContextPath());
			} else {
				//	로그인 실패했을 때의 처리
				resp.sendRedirect(
						req.getContextPath() + "/users?a=loginform&result=fail");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
}
