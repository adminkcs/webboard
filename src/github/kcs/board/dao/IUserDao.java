package github.kcs.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import github.kcs.board.vo.UserVO;

public interface IUserDao {

    /**
     * 모든 게시판 사용자들을 불러옵니다.
     * @return
     */
    List<UserVO> findAll();

    /**
     * 주어진 id와 password 에 일치하는 사용자 정보를 읽어서 반환합니다.
     * @param id
     * @param password
     * @return
     */
    UserVO login(@Param ("uid") String id, @Param("pw") String password);

    // UserVO ling( UserVO newUser ) ;
    
    /**
     * 특정 사용자를 찾습니다.
     * @param userSeq
     * @return
     */
    UserVO findBySeq(Integer userSeq);

    void insertUser(@Param ("uid") String name, @Param ("pw") String password);

}