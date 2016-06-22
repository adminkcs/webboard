package github.kcs.board.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.util.Streams;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.FileDao;
import github.kcs.board.vo.FileVO;

public class FileDownloadAction implements IAction {

    private String ROOT_DIR = "d:/upload";
    @Override
    public String proccess(BoardContext btx, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // "/f/4443"
        FileDao dao = btx.getFileDao();
        
        String fullUri = req.getRequestURI(); // /webboard/f/3322
        String paramPK = fullUri.substring(fullUri.lastIndexOf('/')+1);
        Integer pk = Integer.parseInt ( paramPK) ;
        /*
         * 1. db에 있는 파일 메타 정보를 읽어들입니다.
         */
        FileVO file = dao.findBySeq(pk);
        /*
         * 2. 실제 파일의 물리적 위치를 얻어냅니다.
         */
        File realFile = new File(ROOT_DIR, file.getUniqueFileName() );
        
        System.out.println("다운로드 주비: " + realFile);
        /*
         * 3-1. 다운로드하는 파일에 대한 정보를 http 헤더에 적어줍니다.
         *      - 파일의 크기
         *      - 파일의 이름
         *      - MIME type 설정(IE때문에)
         */
        res.setContentLength(file.getFileSize().intValue());
        res.setContentType("application/octet-stream");  // 서버가 브라우저한테 데이터를 보내는데 이게 무슨 타입인지 나는 모름. 그러니까 브라우저 니가 잘 알아서 해라.
        res.setHeader("Content-Disposition", "attacment; filename=" + file.getOriginFileName());//  file.getOriginFileName()
        /*
         * 3-2. 클라이언트 출력 스트림으로 파일 내용을 써내려 보냅니다.
         */
        FileInputStream fis = new FileInputStream(realFile);
        OutputStream out = res.getOutputStream();
        Streams.copy(fis, out, false);
        
        
        return null;
    }

}
