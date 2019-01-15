package com.biz.cbt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerVO {

	private int aQuestionNum;	// 문제번호
	private int answerNum;		// 답안지를 섞고난 후 새롭게 설정되는 정답 번호
	private int myAnswerNum;	// 시험을 보면서 내가 입력한 정답 번호
	private String strSolve;	// 답안지를 섞고난 후 새롭게 설정되는 정답문
	
}
