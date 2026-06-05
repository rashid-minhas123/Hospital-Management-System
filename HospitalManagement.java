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
