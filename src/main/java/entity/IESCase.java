package entity;


import java.sql.Timestamp;
import java.util.Date;

public class IESCase
{
    private Long caseId;
    private String caseNum;
    private String programCd;
    private String caseStatusCd;
    private String countyCd;
    private String officeCd;
    private Date applicationDt;
    private String activeYn;
    private String createdBy;
    private Timestamp createdTs;
    private String updatedBy;
    private Timestamp updatedTs;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getProgramCd() {
        return programCd;
    }

    public void setProgramCd(String programCd) {
        this.programCd = programCd;
    }

    public String getCaseStatusCd() {
        return caseStatusCd;
    }

    public void setCaseStatusCd(String caseStatusCd) {
        this.caseStatusCd = caseStatusCd;
    }

    public String getCountyCd() {
        return countyCd;
    }

    public void setCountyCd(String countyCd) {
        this.countyCd = countyCd;
    }

    public String getOfficeCd() {
        return officeCd;
    }

    public void setOfficeCd(String officeCd) {
        this.officeCd = officeCd;
    }

    public Date getApplicationDt() {
        return applicationDt;
    }

    public void setApplicationDt(Date applicationDt) {
        this.applicationDt = applicationDt;
    }

    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Timestamp updatedTs) {
        this.updatedTs = updatedTs;
    }
}
