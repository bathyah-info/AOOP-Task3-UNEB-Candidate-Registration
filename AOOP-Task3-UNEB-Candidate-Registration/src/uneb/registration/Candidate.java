package uneb.registration;

import java.util.concurrent.atomic.AtomicInteger;
import uneb.config.ExamConfiguration;

/**
 * This class represents a single UNEB examination candidate and demonstrates per-object
 * initialization order.
 * <p><b>Per-Object Execution Order:</b> Every time {@code new Candidate(...)} runs,
 * instance fields and the instance initialization block execute <b>before</b> the
 * invoked constructor body—assigning a unique candidate number, default
 * {@code PENDING_VERIFICATION} status, and a provisional examination centre first,
 * which the constructor then tailors to the candidate's school and subject count.</p>
 */



public class Candidate {
    private static final AtomicInteger CANDIDATE_SEQUENCE = new AtomicInteger(1000);

    private final String candidateNumber;
    private String fullName;
    private String schoolCode;
    private String examinationCentre;
    private String registrationStatus;
    private int subjectCount;
    private double registrationFee;

    // This executes every time a new Candidate object is created, BEFORE the constructor body runs
    {
        int seq = CANDIDATE_SEQUENCE.incrementAndGet();
        this.candidateNumber = "UNEB-" + ExamConfiguration.EXAM_YEAR + "-" + seq;
        this.registrationStatus = "PENDING_VERIFICATION";
        this.examinationCentre = ExamConfiguration.assignDefaultCentreCode("UNASSIGNED");
        this.subjectCount = 7;
        this.registrationFee = ExamConfiguration.calculateRegistrationFee(this.subjectCount);

        System.out.println("[INSTANCE INIT BLOCK - Candidate] Initialized defaults for "
                + this.candidateNumber + " -> status=" + this.registrationStatus
                + ", provisionalCentre=" + this.examinationCentre);
    }

    public Candidate(String fullName, String schoolCode, int subjectCount) {
        this.fullName = fullName;
        this.schoolCode = schoolCode;
        this.subjectCount = subjectCount;
        this.examinationCentre = ExamConfiguration.assignDefaultCentreCode(schoolCode);
        this.registrationFee = ExamConfiguration.calculateRegistrationFee(subjectCount);

        System.out.println("[CONSTRUCTOR - Candidate] Completed registration setup for '"
                + this.fullName + "' (" + this.candidateNumber + ") at centre "
                + this.examinationCentre + " | Fee: UGX " + String.format("%.2f", this.registrationFee));
    }

    public Candidate(String fullName, String schoolCode) {
        this(fullName, schoolCode, 8);
    }

    public void confirmRegistration() {
        this.registrationStatus = "CONFIRMED";
        System.out.println("[STATUS UPDATE] Candidate " + candidateNumber + " (" + fullName
                + ") is now " + registrationStatus + " at " + examinationCentre + ".");
    }

    public void printCandidateSummary() {
        System.out.printf("  %-18s | %-18s | %-18s | Subjects: %d | Fee: UGX %9.2f | %s%n",
                candidateNumber, fullName, examinationCentre, subjectCount, registrationFee, registrationStatus);
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getExaminationCentre() {
        return examinationCentre;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public int getSubjectCount() {
        return subjectCount;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }
}
