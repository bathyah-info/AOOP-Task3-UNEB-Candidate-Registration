import java.util.Arrays;
import uneb.config.ExamConfiguration;
import uneb.registration.Candidate;
import uneb.registration.RegistrationBatch;

public class Main {
    public static void main(String[] args) {
        System.out.println("  UNEB CANDIDATE REGISTRATION - CLASS & OBJECT LIFECYCLE TRACE");
        System.out.println();

        System.out.println("--- PHASE 1: ONCE-PER-APPLICATION STATIC INITIALIZATION ---");
        System.out.println("  [Main] About to reference ExamConfiguration for the first time...");
        ExamConfiguration.printConfigurationSummary();
        System.out.println("  [Main] Note: Static block ran once; no Candidate instances exist yet.");
        System.out.println();

        System.out.println("--- PHASE 2: PER-OBJECT INIT (INSTANCE BLOCK -> CONSTRUCTOR) ---");
        RegistrationBatch batchA = new RegistrationBatch("BATCH-KLA-01", "Kampala High School");

        System.out.println("  [Main] Creating Candidate #1 (3-arg constructor: 8 subjects)...");
        Candidate c1 = new Candidate("Amina Nabirye", "KLA01", 8);

        System.out.println("  [Main] Creating Candidate #2 (2-arg constructor -> delegates to 3-arg with 8 subjects)...");
        Candidate c2 = new Candidate("Joel Mukasa", "KLA01");

        System.out.println("  [Main] Creating Candidate #3 (invalid 12 subjects to test batch validation)...");
        Candidate c3Invalid = new Candidate("Samuel Kato", "KLA01", 12);

        batchA.addCandidate(c1);
        batchA.addCandidate(c2);
        batchA.addCandidate(c3Invalid);
        batchA.confirmAllCandidates();
        System.out.println();

        System.out.println("--- PHASE 3: SECOND SCHOOL BATCH & MULTI-BATCH FEE TOTALS ---");
        RegistrationBatch batchB = new RegistrationBatch("BATCH-WKS-02", "Wakiso Secondary");
        Candidate c4 = new Candidate("Faith Apio", "WKS02", 9);
        Candidate c5 = new Candidate("Brian Ochieng", "WKS02", 7);
        batchB.addCandidate(c4);
        batchB.addCandidate(c5);
        batchB.confirmAllCandidates();
        System.out.println();

        System.out.println("--- PHASE 4: REGISTRATION & FEE SUMMARY REPORTS ---");
        batchA.printBatchReport();
        System.out.println();
        batchB.printBatchReport();
        System.out.println();

        double grandTotal = RegistrationBatch.sumBatchFees(Arrays.asList(batchA, batchB));
        System.out.printf("Grand Total Registration Fees Across Batches: UGX %.2f%n", grandTotal);
    }
}