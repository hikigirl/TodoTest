package com.test.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Data Transfer Object

@Getter
@Setter
@ToString
public class TodoDTO {
	private String seq;
	private String todo;
	private String state;
	private String regdate;
	
}
