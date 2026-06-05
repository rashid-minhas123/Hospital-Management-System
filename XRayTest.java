
class XRayTest extends LabTest {
    private static final long serialVersionUID = 8L;
    XRayTest(String id, String pid, String date) { super(id, "X-Ray", pid, date); }
    public String generateReport() {
        return "=== X-RAY REPORT ===\nTest ID    : " + testID + "\nPatient ID : " + patientID
             + "\nDate       : " + date + "\n------------------------"
             + "\nBody Part  : " + r1 + "\nNotes      : " + r2
             + "\n====================";
    }
}
