import java.io.Serializable;


class LabTest implements Serializable {
    private static final long serialVersionUID = 5L;
    protected String testID, testName, patientID, date, status = "Pending";
    protected String r1 = "", r2 = "", r3 = "";
    LabTest(String id, String name, String pid, String date) {
        this.testID = id; this.testName = name; this.patientID = pid; this.date = date;
    }
    public void setResults(String a, String b, String c) {
        r1 = a; r2 = b; r3 = c; status = "Done";
    }
    public String getTestID()    { return testID; }
    public String getTestName()  { return testName; }
    public String getPatientID() { return patientID; }
    public String getDate()      { return date; }
    public String getStatus()    { return status; }
    public String generateReport() { return "Report: " + testName; }
    public Object[] toRow()      { return new Object[]{testID, testName, patientID, date, status}; }
}
