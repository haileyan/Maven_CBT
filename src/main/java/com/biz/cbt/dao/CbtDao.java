package com.biz.cbt.dao;

import java.util.List;

// Mybatis를 통해 주석을 설정
import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

import com.biz.cbt.sql.CbtSql;
import com.biz.cbt.vo.CbtVO;

public interface CbtDao {
	
	// Select 주석에 CbtSql 클래스에서 Final로 선언해둔 변수를 불러옴
	@Select(CbtSql.CB_FINDBYNUM)			
	// 원하는 문제 번호를 변수로 받아 해당하는 문제를 찾기위해 Oracle에서 Select문을 실행하기 위한 SQL문
	public CbtVO findByNum(String cb_num);
	
	
	@Insert(CbtSql.CB_INSERT)
	public int insert(CbtVO vo);	// Oracle에서 Insert를 실행하기 위한 SQL문
	
	@Update(CbtSql.CB_UPDATE)
	public int update(CbtVO vo);	// Oracle에서 Update를 실행하기 위한 SQL문
	
	@Delete(CbtSql.CB_DELETE)
	// 삭제하고자 하는 문제 번호를 변수로 받아 해당하는 문제를 찾아 Oracle에서 Delete를 실행하기 위한 SQL문
	public int delete(String cb_num);	
	
	@Select(CbtSql.CB_DISPLAYQ)
	public CbtVO displayQ(int cb_num);	// 원하는 문제를 변수로 받아 해당 문제를 찾아 보여주기 위한(SELECT) SQL문
}
