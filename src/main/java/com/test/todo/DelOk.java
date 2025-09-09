package com.test.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.todo.model.TodoDAO;

@WebServlet(value = "/delok.do")
public class DelOk extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DelOk.java
		//1. 
		String seq = req.getParameter("seq");
		
		//2.
		TodoDAO dao = new TodoDAO();
		int result = dao.del(seq);
		
		//3. 
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/delok.jsp");
		dispatcher.forward(req, resp);
	}
}