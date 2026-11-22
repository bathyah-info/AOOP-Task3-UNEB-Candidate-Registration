package uneb.registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uneb.config.ExamConfiguration;

public class RegistrationBatch {
    private final String batchCode;
    private final String schoolName;
    private final List<Candidate> candidates = new ArrayList<>();

    public RegistrationBatch(String batchCode, String schoolName) {
        this.batchCode = batchCode;
        this.schoolName = schoolName;
    }

    public void addCandidate(Candidate candidate) {
        if (candidate != null) {
            candidates.add(candidate);
            System.out.println("[BATCH " + batchCode + "] Added " + candidate.getFullName()
                    + " (" + candidate.getCandidateNumber() + ") for " + schoolName + ".");
        }
    }

    public void confirmAllCandidates() {
        System.out.println("[BATCH " + batchCode + "] Confirming registrations for " + schoolName + "...");
        for (Candidate candidate : candidates) {
            candidate.confirmRegistration();
        }
    }

    public double calculateTotalBatchFees() {
        double total = 0.0;
        for (Candidate candidate : candidates) {
            total += candidate.getRegistrationFee();
        }
        return total;
    }

    public List<Candidate> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }

    public void printBatchReport() {
        System.out.println("--- BATCH REPORT: " + batchCode + " | School: " + schoolName
                + " | Year: " + ExamConfiguration.EXAM_YEAR + " ---");
        for (Candidate candidate : candidates) {
            candidate.printCandidateSummary();
        }
        System.out.printf("  Total Candidates: %d | Total Batch Fees: UGX %.2f%n",
                candidates.size(), calculateTotalBatchFees());
    }
}
