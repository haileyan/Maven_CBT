package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.biz.cbt.dao.CbtDao;
import com.biz.cbt.db.CbtSqlFactory;
import com.biz.cbt.vo.AnswerVO;
import com.biz.cbt.vo.CbtVO;

public class CbtService {

	SqlSessionFactory sessionFactory; 	// Dao 클래스와 연결하기 위한 세션팩토리
	Scanner scan;						// 답안을 입력하기 위한 스캐너
	List<String> cList; 				// 답안을 섞어서 넣은 리스트
	List<String> aList; 				// 문제풀이자의 답안
	List<AnswerVO> sList;				// 진짜해답
	int FinalScore;						// 최종스코어를 담은 변수

	// CbtService 클래스의 생성자
	public CbtService() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		cList = new ArrayList();
		aList = new ArrayList();
		sList = new ArrayList();
		FinalScore = 0;

		CbtSqlFactory cbtFactory = new CbtSqlFactory();
		this.sessionFactory = cbtFactory.getSessionFactory();
	}

	// 문제입력 관련한 메뉴를 셋업하기 위한 메서드(등록, 수정, 삭제, 종료)
	public void settingQ() {
		// TODO Auto-generated method stub

		SqlSession session = sessionFactory.openSession();
		CbtDao dao = session.getMapper(CbtDao.class);

		while (true) {
			System.out.println("====================================================");
			System.out.println("1.문제등록 / 2. 문제수정 / 3. 문제삭제 / 0. 종료");
			System.out.println("----------------------------------------------------");
			System.out.print("선택 >>");
			String strChoice = scan.nextLine();
			int intChoice = Integer.valueOf(strChoice);

			if (intChoice == 1) {
				this.insertQ();
			}

			if (intChoice == 2) {
				this.editQ();
			}

			if (intChoice == 3) {
				this.deleteQ();
			}

			if (intChoice == 0) {
				System.out.println("SEE YOU NEXT TIME:)");
				break;
			}
		}
	}

	// enrollQ 메서드를 통해 리턴 받은 vo로 Oracle에 문제 INSERT를 실행하기 위한 클래스
	public void insertQ() {
		CbtVO vo = this.enrollQ(); // 각 문제의 정보를 담은 CbtVO클래스를 사용하기 위해 vo 변수로 선언
		if (vo == null) return;	   // vo에 데이터가 담겨있지 않으면 return한다

		SqlSession session = sessionFactory.openSession();	// Oracle에서 Insert를 실행하기 위해 세션팩토리를 통해
		CbtDao dao = session.getMapper(CbtDao.class);		// dao클래스와 연결해준다

		int ret = dao.insert(vo);	// Dao클래스에 선언해둔 Insert문을 실행하기 위해 변수에 담아줌
		session.commit();			// Insert 실행후 commit, close 실행
		session.close();

		if (ret > 0) {				// 데이터(값)이 정상적으로 담겨있다면 등록이 완료되었음을 콘솔에 출력
			System.out.println(">> 문제가 정상적으로 등록 되었습니다 <<");
		} else {
			System.out.println(" ! 문제가 등록되지 않았습니다 !");
		}
	}

	// 문제등록을 선택했을 때 개별 문제를 CbtVO의 각 변수들에 담아서 리턴하기 위한 클래스
	public CbtVO enrollQ() {

		CbtVO vo = new CbtVO();  // 변수를 설정하기 위해 미리 선언해둔 CbtVO 클래스를 vo로 새롭게 선언(Clear)

		System.out.println("======================================");

		System.out.print("문제번호 >>");
		String strQN = scan.nextLine();
		int intQN = Integer.valueOf(strQN);

		System.out.print("문제입력 >>");
		String strQ = scan.nextLine();

		System.out.print("답안1번 >>");
		String strC1 = scan.nextLine();

		System.out.print("답안2번 >>");
		String strC2 = scan.nextLine();

		System.out.print("답안3번 >>");
		String strC3 = scan.nextLine();

		System.out.print("답안4번 >>");
		String strC4 = scan.nextLine();

		System.out.print("정답문항 >>");
		String strA = scan.nextLine();
		int intStrA = Integer.valueOf(strA);

		System.out.print("해답 >>");
		String strS = scan.nextLine();

		vo.setCb_num(intQN);
		vo.setCb_question(strQ);
		vo.setCb_c1(strC1);
		vo.setCb_c2(strC2);
		vo.setCb_c3(strC3);
		vo.setCb_c4(strC4);
		vo.setCb_answer(intStrA);
		vo.setCb_solve(strS);

		return vo;	// Dao클래스에 선언한 Insert문을 실행하기 위해서 vo로 리턴해준다
	}

	// SettingQ 메서드에서 문제 수정을 선택했을 때, 
	// enrollQ 메서드를 통해 리턴 받은 vo로 Oracle에 문제 UPDATE를 실행하기 위한 클래스
	public void editQ() {
		CbtVO vo = this.enrollQ();	// 각 문제의 정보를 담은 CbtVO클래스를 사용하기 위해 vo 변수로 선언
		if (vo == null)	return;		// vo에 데이터가 담겨있지 않으면 return한다

		SqlSession session = sessionFactory.openSession();	// Oracle에서 Update를 실행하기 위해 세션팩토리를 통해
		CbtDao dao = session.getMapper(CbtDao.class);		// dao클래스와 연결해준다

		int ret = dao.update(vo);	// Dao클래스에 선언해둔 Update문을 실행하기 위해 변수에 담아줌
		session.commit();			// Update 실행후 commit, close 실행
		session.close();

		if (ret > 0) {				// 데이터(값)이 정상적으로 담겨있다면 수정이 완료되었음을 콘솔에 출력
			System.out.println(">> 문제가 정상적으로 수정 되었습니다 <<");
		} else {
			System.out.println(" ! 문제가 수정되지 않았습니다 !");
		}
	}

	// SettingQ 메서드에서 문제 삭제을 선택했을 때, Dao클래스에 선언해 둔 Delete문을 실행하기 위한 클래스
	public void deleteQ() {

		System.out.println("======================================");
		System.out.println("문제번호를 입력해주세요");
		System.out.println("--------------------------------------");
		System.out.print("문제번호 >>");
		String cb_num = scan.nextLine();	// 스캐너를 통해 찾고자 하는 문제 번호 입력

		// 입력한 번호의 문제를 viewQuestion 메서드를 통해 찾아와 그 정보를 vo변수에 담아줌
		CbtVO vo = this.viewQuestion(cb_num);

		// 번호를 입력하지 않았을 경우
		if (vo == null) {
			System.out.println("! 문제번호가 잘못 되었습니다 !");
			return;
		} else {
			System.out.println(vo);		// 입력한 번호에 해당하는 문제의 정보가 담긴 vo를 삭제 전 다시 보여줌
			System.out.print("정말로 삭제 하시겠습니까?(YES/NO) >>");
			String word = scan.nextLine();
			if (word.equals("YES")) {	// 삭제를 정말로 원하는 경우
				SqlSession session = sessionFactory.openSession();	// Oracle에서 Delete를 실행하기 위해 세션팩토리를 통해
				CbtDao dao = session.getMapper(CbtDao.class);		// dao클래스와 연결해준다

				int ret = dao.delete(cb_num);	// Dao클래스에 선언해둔 Update문을 실행하기 위해 변수에 담아줌
				session.commit();				// Delete 실행후 commit, close 실행
				session.close();

				if (ret > 0) {					// 데이터(값)이 정상적으로 담겨있다면 삭제가 완료되었음을 콘솔에 출력
					System.out.println(">> 문제가 정상적으로 삭제 되었습니다 <<");
				} else {
					System.out.println(" ! 문제가 삭제되지 않았습니다 !");
				}
			} else {
				return;
			}

		}
	}

	// 입력 받은 문제 번호(cb_num)를 변수로 삼아 해당하는 문제를 보여주기 위한 클래스(Dao클래스의 Select문 실행)
	public CbtVO viewQuestion(String cb_num) {

		SqlSession session = sessionFactory.openSession(); // Oracle에서 Select하기 위해 세션팩토리를 통해
		CbtDao dao = session.getMapper(CbtDao.class);	   // dao클래스와 연결해준다

		CbtVO vo = dao.findByNum(cb_num);				   // Dao클래스의 Select문 실행하여 vo변수에 담기
		return vo;										   // vo를 리턴
	}

	// 문제풀이를 실행하기 위한 클래스
	public void displayQuestion() {

		AnswerVO s = new AnswerVO();
		SqlSession session = sessionFactory.openSession();	
		CbtDao dao = session.getMapper(CbtDao.class);

		System.out.println("******************시험을 시작합니다******************");
		System.out.println("!다시 한번 문제를 풀고 싶으면 'AGAIN'을 입력하세요!");
		System.out.println("!시험 중단을 원하시면 답안란에 'END'를 입력하세요!");
		System.out.println("-----------------------------------------------------");
		
		for (int i = 1; i < 21; i++) {	// 총 20문항이 담겨있기 때문에 20번 반복하는 for문 설정
			s = new AnswerVO();
			CbtVO vo = dao.displayQ(i);		// Dao클래스에 선언해두었던 Select문을 실행하기 위해 변수로 선언
											// 입력되는 i(1-20)값을 변수로 해당 문항을 보여준다 
			String qs = vo.getCb_question(); // 문제문
			String solve = vo.getCb_solve(); // 해답문
			
			// 선택지 섞기
			String[] Choices = { vo.getCb_c1(), vo.getCb_c2(), vo.getCb_c3(), vo.getCb_c4() };
			cList = Arrays.asList(Choices);	 // 배열을 리스트로 변환하고
			Collections.shuffle(cList); 	 // 셔플을 사용하여 선택지를 섞는다

			// 화면에 문제 출력
			System.out.print("문제" + (i) + ". ");
			System.out.println(qs);
			System.out.println("\t(1)" + Choices[0]);
			System.out.println("\t(2)" + Choices[1]);
			System.out.println("\t(3)" + Choices[2]);
			System.out.println("\t(4)" + Choices[3]);
			System.out.print("답안>>");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------------------------");
			
			// 'END'를 입력하면 정답+오답리스트와 점수를 보여주고 종료
			if (input.equals("END")) {
				this.showAnwerList();	// 정답+오답리스트를 보여주는 메서드 실행
				FinalScore = this.setScore();	// 점수를 셋팅하는 메서드를 실행하여 최종스코어를 변수에 담기
				System.out.println("----------------------------------------------");
				System.out.println(">> 시험이 중단됩니다 <<");
				System.out.println("최종 점수>> " + FinalScore + " / 100");
				return;					// 현 메서드 종료
			}
			
			// 'AGAIN'을 입력하면 이전 문제로 다시 돌아가서 계속함
			if (input.equals("AGAIN")) {
				i--;
				continue;
			}
			
			// 선택지(1번-4번)을 벗어 났을 경우 이전 문제로 다시 돌아가 입력하기
			if(Integer.valueOf(input) > 4) {
				System.out.println("! 답안을 다시 입력하세요 !");
				i--;
				continue;
			}
			
			// 섞인 답안에서 진짜 답안과 일치하는 것을 찾아 답안 리스트에 셋팅하기
			for (int a = 0; a < 4; a++) {		// 선택지는 1-4번까지 있기 때문에 4번 실행하는 for문 설정
				if (Choices[a].equals(solve)) {	// 섞여버린 선택지와 원래 해답문이 일치하는 것을 발견할 경우에
					int index = a + 1;			// 해당하는 선택지의 위치값(0-3)에 +1를 더해서 새롭게 답안번호로 설정
					s.setAnswerNum(index);		// 답안번호가 담긴 변수를 미리 선언해둔 변수에 셋팅
					s.setStrSolve(solve);		// 답안문도 셋팅
					s.setMyAnswerNum(Integer.valueOf(input));	// 내가 입력한 답을 변수에 셋팅
					s.setAQuestionNum(i);  		// 문제 번호
					sList.add(s);				// AnswerVO 리스트 담아준다
			}
		}
			// 5,10,15번 문항을 풀었을 때마다 정답+오답리스트를 보여줌
			// (20번을 풀면 어짜피 시험이 끝나기 때문에 20번 일 경우는 빼준다)
			if (i == 5 || i  == 10 || i == 15) {
				System.out.println("----------------------------------------------");
				this.showAnwerList();
				continue;
			} 
	}	// 모든 문제를 다 풀고난 후에
		this.showAnwerList();			// 정답+오답 리스트를 보여주고
		FinalScore = this.setScore();	// 점수를 셋팅하는 메서드를 실행해서 최종성적를 담은 변수를 설정
		System.out.println("******************시험을 종료합니다******************");
		System.out.println("최종 점수>> " + FinalScore + " / 100");
		System.out.println("수고 하셨습니다:)");
}

	// 내가 입력한 답+정답 리스트를 보여주기 위한 메서드
	public void showAnwerList() {
		// 각 변수가 담겨있는 AnswerVO를 사용함
		for(AnswerVO a : sList) { 
			int myAnswer = a.getMyAnswerNum();	// 내가 입력한 답
			int realAnswer = a.getAnswerNum();	// 진짜 정답
			int qNum = a.getAQuestionNum();		// 문제 번호
			System.out.print(qNum + ". ");
			System.out.print(myAnswer);
			
			// 정답인지 아닌지 확인하기 
			if(myAnswer == realAnswer) {		
				System.out.println(" / (O)");	// 정답일 경우 맞았다고 콘솔에 출력
			} else {
				// 정답이 아닌 경우에 진짜 정답 번호와 해당하는 선택지를 콘솔에 출력
				System.out.println(" / (X) " + "*" + realAnswer + " " + a.getStrSolve());
			}
		}
	}

	// 점수를 셋팅하기 위한 메서드
	public int setScore() {
		int Score = 0;
		for(AnswerVO a : sList) {
			int myAnswer = a.getMyAnswerNum();	// 내가 입력한 정답
			int realAnswer = a.getAnswerNum();	// 진짜 정답
			if(myAnswer == realAnswer) {
				Score += 5;						// 맞았을 경우에 +5점
			} else {
				Score += 0;						// 틀렸을 경우 점수 없음
			}
		}
		return Score;	// 전체 문항의 답을 확인하고 최종 성적 값을 리턴
	}
}
