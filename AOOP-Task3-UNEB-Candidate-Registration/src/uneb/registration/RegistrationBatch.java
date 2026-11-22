package uneb.registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uneb.config.ExamConfiguration;

public class RegistrationBatch {
    private static final int MIN_SUBJECTS = 5;
    private static final int MAX_SUBJECTS = 10;

    private final String batchCode;
    private final String schoolName;
    private final List<Candidate> candidates = new ArrayList<>();

    public RegistrationBatch(String batchCode, String schoolName) {
        this.batchCode = batchCode;
        this.schoolName = schoolName;
    }

    public boolean addCandidate(Candidate candidate) {
        if (candidate == null) {
            System.out.println("[BATCH " + batchCode + "] Rejected null candidate entry.");
            return false;
        }
        int subjects = candidate.getSubjectCount();
        if (subjects < MIN_SUBJECTS || subjects > MAX_SUBJECTS) {
            System.out.println("[BATCH " + batchCode + "] Rejected " + candidate.getFullName()
                    + ": subject count (" + subjects + ") must be between "
                    + MIN_SUBJECTS + " and " + MAX_SUBJECTS + ".");
            return false;
        }
        for (Candidate existing : candidates) {
            if (existing.getCandidateNumber().equals(candidate.getCandidateNumber())) {
                System.out.println("[BATCH " + batchCode + "] Duplicate candidate number ignored: "
                        + candidate.getCandidateNumber());
                return false;
            }
        }
        candidates.add(candidate);
        System.out.println("[BATCH " + batchCode + "] Added " + candidate.getFullName()
                + " (" + candidate.getCandidateNumber() + ") for " + schoolName + ".");
        return true;
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

    public static double sumBatchFees(List<RegistrationBatch> batches) {
        double grandTotal = 0.0;
        if (batches == null) {
            return grandTotal;
        }
        for (RegistrationBatch batch : batches) {
            if (batch != null) {
                grandTotal += batch.calculateTotalBatchFees();
            }
        }
        return grandTotal;
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