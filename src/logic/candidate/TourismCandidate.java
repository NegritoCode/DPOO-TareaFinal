package logic.candidate;

public class TourismCandidate extends Candidate {
    private String languageCertificate;

    public TourismCandidate(String cid, String branch, String name, char sex, String address, String phone,
            String schoolLevel, String speciality, int xpYears, String languageCertificate) {
        super(cid, branch, name, sex, address, phone, schoolLevel, speciality, xpYears);
        setLanguageCertificate(languageCertificate);
    }

    public String getLanguageCertificate() {
        return languageCertificate;
    }

    public void setLanguageCertificate(String languageCertificate) {
        this.languageCertificate = languageCertificate.trim();
    }
}
