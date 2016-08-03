package github.kcs.board.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.IPostDao;
import github.kcs.board.vo.CodeVO;
import github.kcs.board.vo.FileVO;
import github.kcs.board.vo.PostVO;
import github.kcs.board.vo.UserVO;

public class ReplyAction implements IAction {
    
    private final static String ROOT_DIR = "D:/upload" ;
    
    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        ServletContext context = req.getServletContext();
        IPostDao postDao = (IPostDao) context.getAttribute("postDao");
        
        Integer parentSeq = null;
        String title = null;
        String content = null;
        int categoryFK = -1;
        FileVO file = null;
        ServletFileUpload upload = new ServletFileUpload();
        if ( ! ServletFileUpload.isMultipartContent(req) ) {
            return "redirect:" + req.getContextPath();
        }
        
        try {
            FileItemIterator itr = upload.getItemIterator(req);
            while ( itr.hasNext() ) {
                FileItemStream fis = itr.next(); // fis
                
                if ( fis.isFormField() ) {
                    // 이거는 input type text or textarea 
                    String fname = fis.getFieldName();
                    System.out.println("form field : " + fname);
                    InputStream in = fis.openStream();
                    String data = Streams.asString(in, "UTF-8"); // property에 따른 데이터를 실제로 읽어들입니다. 
                    if ( "title".equals(fname) ) {
                        title = data;
                    } else if ( "category".equals(fname)) {
                        categoryFK = Integer.parseInt(data);
                    } else if ( "content".equals(fname) ) {
                        content = data;
                    } else if ( "parent".equals(fname) ){
                        parentSeq = Integer.parseInt(data); // ?
                    } else {
                        System.out.println("what is this?: " + fname);
                    }
                    
                } else {
                    // 이거는 업로드한 파일 데이터
                    String fname = fis.getFieldName();
                    InputStream in = fis.openStream();
                    String realFileName = fis.getName();
                    String ufn = generateUFN( realFileName);
                    /*
                     * rfn --> ufn(unique file name) 을 바꿔주는 기능이 반드시 들어가야함.
                     */
                    FileOutputStream fos = new FileOutputStream(new File(ROOT_DIR, ufn));
                    long fsize = Streams.copy( in , fos, true); // file size as bytes
                    file = new FileVO(realFileName, ufn, fsize);
                    // fos.close();
                    
                    System.out.println("file field : " + fname);
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        /*
        String title = req.getParameter("title");  // null 
        String content = req.getParameter("content"); // null
        int categoryFK = WebUtil.Int(req, "category", 1); 
        String file = req.getParameter("f");
        */
        System.out.println(title + ", " + content + ", " );
        
        //파라미터를 변조할수 있기에 세션에서 받아옵니다.
        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        
        PostVO reply = new PostVO(null, title, content, 0, null, loginUser, new CodeVO(categoryFK, null));
        reply.setAttachedFile(file);
        postDao.reply(parentSeq, reply);
        
        
        return "redirect:" + req.getContextPath() + "/list";
    }

    private String generateUFN(String fname) {
        UUID uid = UUID.randomUUID();
        String ufn = uid.toString();
        return ufn;
    }


}
