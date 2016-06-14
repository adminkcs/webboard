package github.kcs.board;

import github.kcs.board.dao.PostDao;
import github.kcs.board.dao.UserDao;

public class BoardContext {

    private UserDao userDao ;
    private PostDao postDao ;
    
    private int defaultPageSize ;
    
    public UserDao getUserDao() {
        return userDao;
    }
    
    public PostDao getPostDao () {
        return postDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        
    }

    public void setPostDao(PostDao dao) {
        this.postDao = dao;
        
    }
    
    public int getDefaultPageSize() {
        return this.defaultPageSize;
    }
    public void setDefaultPageSize (int pageSize) {
        this.defaultPageSize = pageSize;
    }
}
