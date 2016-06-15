package github.kcs.board.vo;
/**
 * 글번호    제목     조회수     작성일   작성자      
 * 
 * create table posts (
 *   seq pk,
 *   
 *   title varchar2(20),
 *   
 *  );
 *  select * from posts where... limit 10, 20
 *  
 *  List<PostVO> posts = .... ;
 *  
 *  ResultSet rs = ptmst.executeQuery();
 *  List<PostVO> posts = new ArrayList<>();
 *  while ( rs.next() ) {
 *       PostVO p = new PostVO ( rs.getsss, sss. );
 *       posts.add( p );
 *  }
 *  return posts;
 *  
 * @author wise-itech
 *
 */
/**
 * @author wise-itech
 *
 */
public class PostVO {
	private Integer seq;
	private String title;
	private Integer viewCount;
	private String creationTime;
	private String content ;
//	private String writer;
	private UserVO writer;
	private CodeVO category;
	
	public PostVO(Integer seq, String title, String content, Integer viewCount, String creationTime, UserVO writer, CodeVO category) {
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.creationTime = creationTime;
		this.writer = writer;
		this.category = category;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public String getTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public UserVO getWriter() {
		return writer;
	}
	public void setWriter(UserVO writer) {
		this.writer = writer;
	}
	
	public CodeVO getCategory() {
		return category;
	}
	public void setCategory(CodeVO category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "PostVO [seq=" + seq + ", title=" + title + ", viewCount=" + viewCount + ", creationTime=" + creationTime
				+ ", content=" + content + ", writer=" + writer + ", category=" + category + "]";
	}
	
	
}
