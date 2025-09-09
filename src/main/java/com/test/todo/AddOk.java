package com.test.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.RequiresStatement;

import com.test.todo.model.TodoDAO;

@WebServlet(value = "/addok.do")
public class AddOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//AddOk.java
		
		//1. 인코딩 & 데이터 가져오기
		req.setCharacterEncoding("UTF-8");
		String todo = req.getParameter("todo");
		
		//2. 데이터 작업 -> DAO
		TodoDAO dao = new TodoDAO();
		int result = dao.add(todo); 
		//매개변수로 todo를 넘긴 후 결과값을 돌려받는다(숫자가 무난해서 숫자로)
		//성공하면 1, 실패하면 0을 받음
		
		//3. jsp 호출, result값을 같이 전달
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/addok.jsp");
		dispatcher.forward(req, resp);
	}
}