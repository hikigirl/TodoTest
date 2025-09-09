DROP SEQUENCE seqTodo;
DROP TABLE tblTodo;

CREATE TABLE tblTodo(
	seq NUMBER PRIMARY KEY,
	todo varchar2(100) NOT NULL,
	state char(1) DEFAULT 'n' NOT NULL,
	regdate DATE DEFAULT sysdate NOT NULL
);

CREATE SEQUENCE seqTodo;

INSERT INTO tblTodo (seq, todo, state, regdate) VALUES (seqTodo.nextVal, '컴퓨터 포맷하기', DEFAULT, DEFAULT);
INSERT INTO tblTodo (seq, todo, state, regdate) VALUES (seqTodo.nextVal, '강아지 산책하기', DEFAULT, DEFAULT);
INSERT INTO tblTodo (seq, todo, state, regdate) VALUES (seqTodo.nextVal, '고양이 목욕하기', DEFAULT, DEFAULT);

SELECT * FROM tblTodo;

UPDATE TBLTODO SET state = ? WHERE seq = ?;