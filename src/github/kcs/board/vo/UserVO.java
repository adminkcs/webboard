package github.kcs.board.vo;

public class UserVO {
	private Integer seq;
	private String id;
	private String password;
	private String joinDate;
	
	public UserVO(Integer seq, String id, String password, String joinDate) {
		this.seq = seq;
		this.id = id;
		this.password = password;
		this.joinDate = joinDate;
	}
	
	/**
	 *  
	 * @param id
	 * @param password
	 */
	public UserVO ( String id, String password ) {
		// insert 용 생성자
		this.id = id;
		this.password = password;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
	
	
}
