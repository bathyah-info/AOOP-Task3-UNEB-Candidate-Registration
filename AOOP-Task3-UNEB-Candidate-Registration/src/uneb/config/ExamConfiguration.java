package uneb.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
}