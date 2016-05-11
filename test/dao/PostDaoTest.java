package dao;

import java.util.List;

import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.PostVO;

public class PostDaoTest {

	public static void main(String[] args) {
		PostDao dao = new PostDao();
		List<PostVO> two = dao.findAll();
		System.out.println(two);
		
	}
}
