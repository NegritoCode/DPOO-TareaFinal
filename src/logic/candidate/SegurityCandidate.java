package logic.candidate;

public class SegurityCandidate extends Candidate {
    private String physicalEfficiencyScores;
    private String medicalRecordNumber;

    public SegurityCandidate(String cid, String branch, String name, char sex, String address, String phone,
            String schoolLevel, String speciality, int xpYears,
            String physicalEfficiencyScores, String medicalRecordNumber) {
        super(cid, branch, name, sex, address, phone, schoolLevel, speciality, xpYears);
        setPhysicalEfficiencyScores(physicalEfficiencyScores);
        setMedicalRecordNumber(medicalRecordNumber);
    }

    public String getPhysicalEfficiencyScores() {
        return physicalEfficiencyScores;
    }

    public void setPhysicalEfficiencyScores(String physicalEfficiencyScores) {
        this.physicalEfficiencyScores = physicalEfficiencyScores.trim();
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber.trim();
    }
}