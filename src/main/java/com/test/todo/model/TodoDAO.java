package com.test.todo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//모든 데이터 조작하는 코드 담당(JDBC)
// - 각 업무별 메서드
public class TodoDAO {
	
	//메서드에서 공통으로 사용하므로 여기로 옮기고 private
	//멤버 변수
	private DBUtil util;
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	//생성자
	public TodoDAO() {
		try {
			util = new DBUtil();
			conn = util.open();
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//할일 목록 보기
	//public ResultSet list() {
	public List<TodoDTO> list() {	
		try {
			
			//위에서 DB접속까지 했으므로 쿼리 작업부터
			String sql = "SELECT * FROM tblTodo ORDER BY state ASC, seq DESC";
			rs = stat.executeQuery(sql);
			//ResultSet -> 다른 구조로 변환 -> JDBC가 아니어도 알 수 있는 구조
			
			
			//TodoDTO -> 테이블의 레코드 한줄
			//List<TodoDTO>: 레코드 n개 == 테이블 == ResultSet
			List<TodoDTO> list = new ArrayList<TodoDTO>();
			while (rs.next()) {
				//레코드 한줄을 복사 -> TodoDTO 1개
				TodoDTO dto = new TodoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTodo(rs.getString("todo"));
				dto.setState(rs.getString("state"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			//return rs;
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	//할일 등록하기
	//AddOK 서블릿이 할일을 줄테니 insert문을 실행
	public int add(String todo) {
		try {
			String sql = "INSERT INTO tblTodo (seq, todo, state, regdate) VALUES (seqTodo.nextVal, ?, DEFAULT, DEFAULT)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, todo);
			
			//executeUpdate() -> 성공하면 1, 실패하면 0을 돌려주므로 이용함
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	//CheckOk 서블릿이 할일을 줄테니 update문을 실행
	public int check(TodoDTO dto) {
		try {
			String sql = "UPDATE TBLTODO SET state = ? WHERE seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getState());
			pstat.setString(2, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//DelOk 서블릿이 할일을 줄테니 delete문을 실행
	public int del(String seq) {
		try {
			String sql = "DELETE FROM tbltodo WHERE seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
