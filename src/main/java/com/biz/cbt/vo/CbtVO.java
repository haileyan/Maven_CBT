package com.biz.cbt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CbtVO {

	private int cb_num; 		// 문제번호
	private String cb_question;	// 문제문
	private String cb_c1;		// 선택지1
	private String cb_c2;	    // 선택지2
	private String cb_c3;		// 선택지3
	private String cb_c4;		// 선택지4
	private int cb_answer;		// 정답
	private String cb_solve;	// 정답인 선택지
}
