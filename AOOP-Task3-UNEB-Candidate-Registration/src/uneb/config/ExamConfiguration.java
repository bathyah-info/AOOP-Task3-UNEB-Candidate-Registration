package uneb.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


// Holds central UNEB examination settings for the entire application lifecycle.
public class ExamConfiguration {
    public static final int EXAM_YEAR;
    public static final double BASE_REGISTRATION_FEE;
    public static final double PER_SUBJECT_FEE;
    public static final String DEFAULT_CENTRE_PREFIX;
    public static final Map<String, String> GRADING_POLICY;
    private static boolean initialized = false;

    // Executes ONCE when ExamConfiguration is first loaded into the JVM
    static {
        System.out.println("[STATIC BLOCK - ExamConfiguration] Loading UNEB central configuration once...");
        EXAM_YEAR = 2026;
        BASE_REGISTRATION_FEE = 85000.0;
        PER_SUBJECT_FEE = 12000.0;
        DEFAULT_CENTRE_PREFIX = "UNEB-CTR-";

        Map<String, String> scale = new LinkedHashMap<>();
        scale.put("D1 - D2", "Distinction (High achievement)");
        scale.put("C3 - C6", "Credit (Pass with merit)");
        scale.put("P7 - P8", "Pass (Minimum satisfactory pass)");
        scale.put("F9", "Fail (Ungraded / Repeat)");
        GRADING_POLICY = Collections.unmodifiableMap(scale);

        initialized = true;
        System.out.println("[STATIC BLOCK - ExamConfiguration] Central settings loaded for exam year "
                + EXAM_YEAR + " (initialized=" + initialized + ").");
    }

    private ExamConfiguration() {
        // Utility configuration class; prevent instantiation
    }

    // Computes the total registration fee for a candidate based on subject count.
    public static double calculateRegistrationFee(int subjectCount) {
        int safeSubjects = Math.max(0, subjectCount);
        return BASE_REGISTRATION_FEE + (safeSubjects * PER_SUBJECT_FEE);
    }
// Builds a default UNEB centre code from a school code.
    public static String assignDefaultCentreCode(String schoolCode) {
        String cleanCode = (schoolCode == null || schoolCode.isBlank()) ? "GEN001" : schoolCode.trim().toUpperCase();
        return DEFAULT_CENTRE_PREFIX + cleanCode;
    }
// Prints the loaded exam year, fee schedule, and grading scale to standard output.
    public static void printConfigurationSummary() {
        System.out.println("=== UNEB CENTRAL CONFIGURATION (" + EXAM_YEAR + ") ===");
        System.out.printf("Base Registration Fee: UGX %.2f%n", BASE_REGISTRATION_FEE);
        System.out.printf("Per-Subject Fee:       UGX %.2f%n", PER_SUBJECT_FEE);
        System.out.println("Grading Policy:");
        for (Map.Entry<String, String> entry : GRADING_POLICY.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}