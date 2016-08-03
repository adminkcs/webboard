package github.kcs.board.vo;

public class CodeVO {
	private Integer cdDvsId;
	private String cdNm;
	
	public CodeVO(Integer cdDvsId, String cdNm) {
	    this.cdDvsId = cdDvsId;
	    this.cdNm = cdNm;
	}
	
	public Integer getCdDvsId() {
		return cdDvsId;
	}
	public void setCdDvsId(Integer cdDvsId) {
		this.cdDvsId = cdDvsId;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

    @Override
    public String toString() {
        return "CodeVO [cdDvsId=" + cdDvsId + ", cdNm=" + cdNm + "]";
    }
	
	
	
}
