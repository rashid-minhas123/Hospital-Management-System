
class BloodTest extends LabTest {
    private static final long serialVersionUID = 6L;
    BloodTest(String id, String pid, String date) { super(id, "Blood Test", pid, date); }
    public String generateReport() {
        return "=== BLOOD TEST REPORT ===\nTest ID    : " + testID + "\nPatient ID : " + patientID
             + "\nDate       : " + date + "\n------------------------"
             + "\nHemoglobin : " + r1 + "\nWBC        : " + r2 + "\nRBC        : " + r3
             + "\n========================";
    }
}
