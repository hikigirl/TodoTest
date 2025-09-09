package com.test.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.todo.model.TodoDAO;
import com.test.todo.model.TodoDTO;

@WebServlet(value = "/checkok.do")
public class CheckOk extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//CheckOk.java
		
		//1. 
		String seq = req.getParameter("seq");
		String state = req.getParameter("state");
		
		//2. DB작업(DAO)
		TodoDAO dao = new TodoDAO();
		//의뢰
		//dao.check(seq, state);
		TodoDTO dto = new TodoDTO();
		dto.setSeq(seq);
		dto.setState(state);
		
		int result = dao.check(dto); //성공하면 1, 실패하면 0
		
		//3.
		req.setAttribute("result", result);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/checkok.jsp");
		dispatcher.forward(req, resp);
	}
}