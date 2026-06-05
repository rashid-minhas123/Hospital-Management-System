
class UrineTest extends LabTest {
    private static final long serialVersionUID = 7L;
    UrineTest(String id, String pid, String date) { super(id, "Urine Test", pid, date); }
    public String generateReport() {
        return "=== URINE TEST REPORT ===\nTest ID    : " + testID + "\nPatient ID : " + patientID
             + "\nDate       : " + date + "\n------------------------"
             + "\npH         : " + r1 + "\nGlucose    : " + r2 + "\nProtein    : " + r3
             + "\n========================";
    }
}
