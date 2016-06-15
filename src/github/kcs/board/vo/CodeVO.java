package github.kcs.board.vo;

public class CodeVO {
	private String cdDvsId;
	private String cdId;
	private String cdNm;
	
	public CodeVO(String cdDvsId, String cdId, String cdNm) {
	    this.cdDvsId = cdDvsId;
	    this.cdId = cdId;
	    this.cdNm = cdNm;
	}
	
	public String getCdDvsId() {
		return cdDvsId;
	}
	public void setCdDvsId(String cdDvsId) {
		this.cdDvsId = cdDvsId;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	@Override
	public String toString() {
		return "CodeVO [cdDvsId=" + cdDvsId + ", cdId=" + cdId + ", cdNm=" + cdNm + "]";
	}
	
	
	
}
