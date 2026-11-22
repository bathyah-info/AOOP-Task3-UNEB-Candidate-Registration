import java.util.Arrays;
import uneb.config.ExamConfiguration;
import uneb.registration.Candidate;
import uneb.registration.RegistrationBatch;

public class Main {
    public static void main(String[] args) {
        // 1. Once-per-application static initialization happens when ExamConfiguration loads
        ExamConfiguration.printConfigurationSummary();
        System.out.println();

        // 2. Per-object initialization: instance init block runs before constructor on each 'new'
        RegistrationBatch batchA = new RegistrationBatch("BATCH-KLA-01", "Kampala High School");
        Candidate c1 = new Candidate("Amina Nabirye", "KLA01", 8);
        Candidate c2 = new Candidate("Joel Mukasa", "KLA01");
        batchA.addCandidate(c1);
        batchA.addCandidate(c2);
        batchA.confirmAllCandidates();
        System.out.println();

        RegistrationBatch batchB = new RegistrationBatch("BATCH-WKS-02", "Wakiso Secondary");
        Candidate c3 = new Candidate("Faith Apio", "WKS02", 9);
        batchB.addCandidate(c3);
        batchB.confirmAllCandidates();
        System.out.println();

        batchA.printBatchReport();
        batchB.printBatchReport();
        double grandTotal = RegistrationBatch.sumBatchFees(Arrays.asList(batchA, batchB));
        System.out.printf("Grand Total Registration Fees Across Batches: UGX %.2f%n", grandTotal);
    }
}