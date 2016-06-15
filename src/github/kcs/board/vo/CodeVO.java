package github.kcs.board.vo;

public class CodeVO {
	private int cdDvsId;
	private String cdNm;
	
	public CodeVO(int cdDvsId, String cdNm) {
	    this.cdDvsId = cdDvsId;
	    this.cdNm = cdNm;
	}
	
	public int getCdDvsId() {
		return cdDvsId;
	}
	public void setCdDvsId(int cdDvsId) {
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
