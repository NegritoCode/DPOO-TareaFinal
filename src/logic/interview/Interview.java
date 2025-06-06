package logic.interview;

public class Interview {
    private String monthlyId;
    private String dayId;
    private String candidateCid;
    private String companyId;
    private String offerId;

    public Interview(String monthlyId, String dayId, String candidateCid,
            String companyId, String offerId) {

        setMonthlyId(monthlyId);
        setDayId(dayId);
        setCandidateCid(candidateCid);
        setCompanyId(companyId);
        setOfferId(offerId);
    }

    public String getMonthlyId() {
        return monthlyId;
    }

    public void setMonthlyId(String monthlyId) {
        this.monthlyId = monthlyId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayID) {
        this.dayId = dayID;
    }

    public String getCandidateCid() {
        return candidateCid;
    }

    public void setCandidateCid(String candidateCid) {
        this.candidateCid = candidateCid;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

}
