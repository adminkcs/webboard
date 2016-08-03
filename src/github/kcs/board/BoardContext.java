package github.kcs.board;

import org.apache.ibatis.session.SqlSessionFactory;

import github.kcs.board.dao.FileDao;
import github.kcs.board.dao.IPostDao;
import github.kcs.board.dao.IUserDao;

public class BoardContext {

    private IUserDao userDao ;
    private IPostDao postDao ;
    
    private int defaultPageSize ;
    private FileDao fileDao;
    private SqlSessionFactory mybatisFactory;
    
    public IUserDao getUserDao() {
        return userDao;
    }
    
    public IPostDao getPostDao () {
        return postDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
        
    }

    public void setPostDao(IPostDao dao) {
        this.postDao = dao;
        
    }
    
    public int getDefaultPageSize() {
        return this.defaultPageSize;
    }
    public void setDefaultPageSize (int pageSize) {
        this.defaultPageSize = pageSize;
    }
    
    public FileDao getFileDao() {
        return fileDao;
    }
    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public SqlSessionFactory getMybatisFactory() {
        return this.mybatisFactory;
    }
    public void setMybatisFactory(SqlSessionFactory factory) {
        this.mybatisFactory = factory;
        
    }
}
