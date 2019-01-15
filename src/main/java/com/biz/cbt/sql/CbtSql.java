package com.biz.cbt.sql;

// Oracle에서 Sql을 실행하기 위한 주석을 선언하기 위해 Final 변수 깔끔하게 선언해둠
public class CbtSql {

	public static final String CB_DISPLAYQ
	 = " SELECT cb_question, cb_c1, cb_c2, cb_c3, cb_c4, cb_solve FROM tbl_cbt WHERE cb_num = #{cb_num} ";
	
	public static final String CB_FINDBYNUM 
	 = " SELECT * FROM tbl_cbt WHERE cb_num = #{cb_num} ";
	
	public static final String CB_INSERT
	 = " INSERT INTO tbl_cbt VALUES (#{cb_num},"
	 		+ " #{cb_question}, #{cb_c1}, #{cb_c2}, #{cb_c3}, #{cb_c4}, #{cb_answer}, #{cb_solve}) ";
	
	public static final String CB_UPDATE
	 = " UPDATE tbl_cbt SET cb_question = #{cb_question}, cb_c1 = #{cb_c1}, cb_c2 = #{cb_c2}, "
	 		+ " cb_c3 = #{cb_c3}, cb_c4 = #{cb_c4},"
	 		+ " cb_answer = #{cb_answer}, cb_solve = #{cb_solve} WHERE cb_num = #{cb_num} ";
	
	public static final String CB_DELETE
	 = " DELETE FROM tbl_cbt WHERE cb_num = #{cb_num} ";

}
