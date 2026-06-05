import java.io.Serializable;

class Appointment implements Serializable {
    private static final long serialVersionUID = 4L;
    private String id, date, status = "Booked";
    private Patient patient;
    private Doctor  doctor;
    Appointment(String id, Patient p, Doctor d, String date) {
        this.id = id; this.patient = p; this.doctor = d; this.date = date;
    }
    public void cancel()        { status = "Cancelled"; }
    public String getApptID()   { return id; }
    public Patient getPatient() { return patient; }
    public Doctor  getDoctor()  { return doctor; }
    public String getDate()     { return date; }
    public String getStatus()   { return status; }
    public Object[] toRow()     { return new Object[]{id, patient.getName(), doctor.getName(), date, status}; }
}
