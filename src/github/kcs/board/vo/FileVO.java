package github.kcs.board.vo;

public class FileVO {

    private Integer seq;
    // private String midDir;
    private String originFileName; // ddd.pdf
    private String uniqueFileName;
    
//    private Integer postingPK;
    private Long fileSize; // as bytes

    public FileVO(Integer seq, String originFileName, String uniqueFileName, Long fileSize) {
    super();
    this.seq = seq;
    this.originFileName = originFileName;
    this.uniqueFileName = uniqueFileName;
    this.fileSize = fileSize;
    }
    
    

    public FileVO(String originFileName, String uniqueFileName, Long fileSize) {
        super();
        this.originFileName = originFileName;
        this.uniqueFileName = uniqueFileName;
        this.fileSize = fileSize;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getUniqueFileName() {
        return uniqueFileName;
    }

    public void setUniqueFileName(String uniqueFileName) {
        this.uniqueFileName = uniqueFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }



    @Override
    public String toString() {
        return "FileVO [seq=" + seq + ", originFileName=" + originFileName + ", uniqueFileName=" + uniqueFileName
                + ", fileSize=" + fileSize + "]";
    }
    
    
    
}
