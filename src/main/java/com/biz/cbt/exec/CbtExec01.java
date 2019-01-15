package com.biz.cbt.exec;

import java.util.Scanner;

import com.biz.cbt.service.CbtService;

public class CbtExec01 {

	// CBT 프로그램을 실행하기 위한 메인 메서드
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CbtService cs = new CbtService();	// 메서드들이 담긴 Service 클래스를 이용하기 위해 선언
		Scanner scan = new Scanner(System.in);	
		
		while (true) {
			System.out.println("=========================================");
			System.out.println("1.문제입력 / 2. 문제풀이 / 0. 종료");
			System.out.println("-----------------------------------------");
			System.out.print("선택 >>");
			String strChoice = scan.nextLine();
			int intChoice = Integer.valueOf(strChoice);
			
			if (intChoice == 1) {	
				cs.settingQ();
			}

			if (intChoice == 2) {
				cs.displayQuestion();
			}

			if (intChoice == 0) {
				System.out.println("SEE YOU NEXT TIME:)");
				break;
			}
		}
		
	}

}
