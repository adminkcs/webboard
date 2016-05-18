package dao;

import github.kcs.board.vo.UserVO;

public class Equality {

	public static void main(String[] args) {
		UserVO james1 = new UserVO(1000, "james", "1111", "dkdkdk");

		UserVO james2 = new UserVO(1000, "james", "1111", "dkdkdk");
		
		
		System.out.println(
				james1
				.equals(james2)); // equality 를 판별하는 구현이 없습니다.
		
	}
}
