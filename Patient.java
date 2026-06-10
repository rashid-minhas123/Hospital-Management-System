import java.io.Serializable;
import java.util.ArrayList;

// ── ABDULLAH + ABDURREHMAN ────────────────────────────────────
// OOP Concept: INHERITANCE — Patient extends Person
// OOP Concept: ENCAPSULATION — all fields are private
//   Private fields cannot be accessed from outside directly.
//   Use getters to READ and setters to CHANGE them.
// ─────────────────────────────────────────────────────────────

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
