package github.kcs.board.vo;

public class UserVO {
	private Integer seq;
	private String id;
	private String password;
	private String joinDate;
	
	public UserVO() {
	}
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

	@Override
	public String toString() {
		return "UserVO [seq=" + seq + ", id=" + id + ", password=" + password + ", joinDate=" + joinDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}

	/*
	 * TODO  equals 와 hashCode 구현을 잘 해놓으면 프로그래밍이 더 간결해집니다.
	 *       equality 와 identity라는 이야기를 해야합니다.
	 */

	@Override
	public boolean equals(Object obj) {
		/*
		 * equality 구현을 해줍니다. 
		 */
		if ( obj.getClass() == UserVO.class) {
			UserVO user0 = this;
			UserVO user1 = (UserVO) obj;
			return user0.seq.equals(user1.seq) ; // == 로 비교하면 identity를 비교합니다. Integer로 참조타입입니다.
			
		} else {
			return false;
		}
		
	}
	
	
	
	
}
