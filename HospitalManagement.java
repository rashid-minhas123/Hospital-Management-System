import java.io.Serializable;



class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String name, cnic;
    protected int age;
    Person(String name, int age, String cnic) {
        this.name = name; this.age = age; this.cnic = cnic;
    }
    public String getName() { return name; }
    public int    getAge()  { return age; }
    public String getCnic() { return cnic; }
}
import java.io.Serializable;
import java.util.ArrayList;



class Patient extends Person {
    private static final long serialVersionUID = 2L;
    private String id, disease;
    private ArrayList<String> reports = new ArrayList<>();
    Patient(String id, String name, int age, String cnic, String disease) {
        super(name, age, cnic); this.id = id; this.disease = disease;
    }
    // Getters (ENCAPSULATION — read private fields)
    public String getPatientID()           { return id; }
    public String getDisease()             { return disease; }
    public ArrayList<String> getReports()  { return reports; }
    // Setter (ENCAPSULATION — change private field safely)
    public void setDisease(String d)       { this.disease = d; }
    // Utility
    public void addReport(String r)        { reports.add(r); }
    public Object[] toRow()                { return new Object[]{id, name, age, cnic, disease}; }
    public String toString()               { return id + " — " + name; }
}

import java.io.Serializable;



class Doctor extends Person {
    private static final long serialVersionUID = 3L;
    private String id, spec, schedule;
    Doctor(String id, String name, int age, String cnic, String spec, String sched) {
        super(name, age, cnic); this.id = id; this.spec = spec; this.schedule = sched;
    }
    public String getDoctorID()  { return id; }
    public String getSpec()      { return spec; }
    public String getSchedule()  { return schedule; }
    public Object[] toRow()      { return new Object[]{id, name, age, spec, schedule}; }
    public String toString()     { return id + " — " + name + " (" + spec + ")"; }
}
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
