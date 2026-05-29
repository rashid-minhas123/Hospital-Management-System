import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

// ============================================================
//  HOSPITAL MANAGEMENT SYSTEM — Single File Version
//  Save this file as: HMS.java
//  Compile: javac HMS.java
//  Run:     java HMS
//  Login:   username = admin   password = admin123
// ============================================================

public class HMS {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // ONE manager shared with all panels
            HospitalManager hm = new HospitalManager();
            FileManager.loadData(hm);

            // Login first
            LoginDialog login = new LoginDialog(null);
            login.setVisible(true);
            if (!login.isAuthenticated()) System.exit(0);

            // Main window
            JFrame frame = new JFrame("Hospital Management System");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(950, 650);
            frame.setLocationRelativeTo(null);

            // All panels share the same hm object
            PatientPanel     patientPanel     = new PatientPanel(hm);
            DoctorPanel      doctorPanel      = new DoctorPanel(hm);
            AppointmentPanel appointmentPanel = new AppointmentPanel(hm);
            LabTestPanel     labTestPanel     = new LabTestPanel(hm);
            ReportPanel      reportPanel      = new ReportPanel(hm);

            JTabbedPane tabs = new JTabbedPane();
            tabs.addTab("Patients",     patientPanel);
            tabs.addTab("Doctors",      doctorPanel);
            tabs.addTab("Appointments", appointmentPanel);
            tabs.addTab("Lab Tests",    labTestPanel);
            tabs.addTab("Reports",      reportPanel);

            // Refresh dropdowns when switching tabs
            tabs.addChangeListener(e -> {
                int i = tabs.getSelectedIndex();
                if (i == 2) appointmentPanel.refreshDropdowns();
                if (i == 3) labTestPanel.refreshPatients();
            });

            frame.add(tabs);

            // Save on close
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    FileManager.saveData(hm);
                    System.exit(0);
                }
            });

            frame.setVisible(true);
        });
    }
}


// ============================================================
//  DATA CLASSES
// ============================================================

// Abstract base — Patient and Doctor both extend this
class Person implements Serializable {
    private String name;
    private int    age;
    private String cnic;

    public Person(String name, int age, String cnic) {
        this.name = name;
        this.age  = age;
        this.cnic = cnic;
    }

    public String getName() { return name; }
    public int    getAge()  { return age;  }
    public String getCnic() { return cnic; }
}

// -----------------------------------------------------------
class Patient extends Person implements Serializable {
    private String            patientID;
    private String            disease;
    private ArrayList<String> reports = new ArrayList<>();

    public Patient(String id, String name, int age, String cnic, String disease) {
        super(name, age, cnic);
        this.patientID = id;
        this.disease   = disease;
    }

    public String            getPatientID() { return patientID; }
    public String            getDisease()   { return disease;   }
    public ArrayList<String> getReports()   { return reports;   }
    public void addReport(String r)         { reports.add(r);   }

    // Row for JTable display
    public Object[] toRow() {
        return new Object[]{ patientID, getName(), getAge(), getCnic(), disease };
    }

    // JComboBox shows this text
    @Override
    public String toString() { return patientID + " - " + getName(); }
}

// -----------------------------------------------------------
class Doctor extends Person implements Serializable {
    private String doctorID;
    private String spec;
    private String schedule;

    public Doctor(String id, String name, int age, String cnic, String spec, String schedule) {
        super(name, age, cnic);
        this.doctorID = id;
        this.spec     = spec;
        this.schedule = schedule;
    }

    public String getDoctorID() { return doctorID; }
    public String getSpec()     { return spec;     }
    public String getSchedule() { return schedule; }

    public Object[] toRow() {
        return new Object[]{ doctorID, getName(), getAge(), spec, schedule };
    }

    @Override
    public String toString() { return doctorID + " - " + getName() + " (" + spec + ")"; }
}

// -----------------------------------------------------------
class Appointment implements Serializable {
    private String  apptID;
    private Patient patient;
    private Doctor  doctor;
    private String  date;
    private String  status = "Booked";

    public Appointment(String id, Patient p, Doctor d, String date) {
        this.apptID  = id;
        this.patient = p;
        this.doctor  = d;
        this.date    = date;
    }

    public void cancel() { status = "Cancelled"; }

    public String  getApptID()  { return apptID;          }
    public Patient getPatient() { return patient;          }
    public Doctor  getDoctor()  { return doctor;           }
    public String  getDate()    { return date;             }
    public String  getStatus()  { return status;           }

    public Object[] toRow() {
        return new Object[]{ apptID, patient.getName(), doctor.getName(), date, status };
    }
}

// -----------------------------------------------------------
// Abstract base for all lab tests
class LabTest implements Serializable {
    private String testID;
    private String testName;
    private String patientID;
    private String date;
    private String status = "Pending";

    // Result fields — used by all three test types
    protected String r1 = "", r2 = "", r3 = "";

    public LabTest(String id, String name, String pid, String date) {
        this.testID    = id;
        this.testName  = name;
        this.patientID = pid;
        this.date      = date;
    }

    public void setResults(String r1, String r2, String r3) {
        this.r1 = r1; this.r2 = r2; this.r3 = r3;
        this.status = "Done";
    }

    public String getTestID()    { return testID;    }
    public String getTestName()  { return testName;  }
    public String getPatientID() { return patientID; }
    public String getDate()      { return date;      }
    public String getStatus()    { return status;    }

    // Each subclass overrides this to give a formatted report
    public String generateReport() { return "Report for " + testName; }

    public Object[] toRow() {
        return new Object[]{ testID, testName, patientID, date, status };
    }
}

// -----------------------------------------------------------
class BloodTest extends LabTest {
    public BloodTest(String id, String pid, String date) {
        super(id, "Blood Test", pid, date);
    }

    @Override
    public String generateReport() {
        return "=== BLOOD TEST REPORT ===\n" +
               "Test ID    : " + getTestID()    + "\n" +
               "Patient ID : " + getPatientID() + "\n" +
               "Date       : " + getDate()      + "\n" +
               "------------------------\n" +
               "Hemoglobin : " + r1             + "\n" +
               "WBC        : " + r2             + "\n" +
               "RBC        : " + r3             + "\n" +
               "========================";
    }
}

// -----------------------------------------------------------
class UrineTest extends LabTest {
    public UrineTest(String id, String pid, String date) {
        super(id, "Urine Test", pid, date);
    }

    @Override
    public String generateReport() {
        return "=== URINE TEST REPORT ===\n" +
               "Test ID    : " + getTestID()    + "\n" +
               "Patient ID : " + getPatientID() + "\n" +
               "Date       : " + getDate()      + "\n" +
               "------------------------\n" +
               "pH         : " + r1             + "\n" +
               "Glucose    : " + r2             + "\n" +
               "Protein    : " + r3             + "\n" +
               "========================";
    }
}

// -----------------------------------------------------------
class XRayTest extends LabTest {
    public XRayTest(String id, String pid, String date) {
        super(id, "X-Ray Test", pid, date);
    }

    @Override
    public String generateReport() {
        return "=== X-RAY TEST REPORT ===\n" +
               "Test ID    : " + getTestID()    + "\n" +
               "Patient ID : " + getPatientID() + "\n" +
               "Date       : " + getDate()      + "\n" +
               "------------------------\n" +
               "Body Part  : " + r1             + "\n" +
               "Notes      : " + r2             + "\n" +
               "========================";
    }
}


// ============================================================
//  LOGIC CLASSES
// ============================================================

class HospitalManager {
    private ArrayList<Patient>     patients     = new ArrayList<>();
    private ArrayList<Doctor>      doctors      = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<LabTest>     labTests     = new ArrayList<>();

    // --- Patient ---
    public void addPatient(Patient p) { patients.add(p); }

    public Patient findPatient(String id) {
        for (Patient p : patients)
            if (p.getPatientID().equalsIgnoreCase(id)) return p;
        return null;
    }

    // --- Doctor ---
    public void addDoctor(Doctor d) { doctors.add(d); }

    // --- Appointment ---
    public void addAppointment(Appointment a) { appointments.add(a); }

    public void cancelAppointment(String id) {
        for (Appointment a : appointments)
            if (a.getApptID().equals(id)) { a.cancel(); return; }
    }

    // --- Lab Test ---
    public void addLabTest(LabTest t) {
        labTests.add(t);
        Patient p = findPatient(t.getPatientID());
        if (p != null) p.addReport(t.generateReport());
    }

    // Update report after results are submitted
    public void updateReport(LabTest t) {
        Patient p = findPatient(t.getPatientID());
        if (p != null) p.addReport(t.generateReport());
    }

    // --- Getters and Setters (used by FileManager) ---
    public ArrayList<Patient>     getPatients()     { return patients;     }
    public ArrayList<Doctor>      getDoctors()      { return doctors;      }
    public ArrayList<Appointment> getAppointments() { return appointments; }
    public ArrayList<LabTest>     getLabTests()     { return labTests;     }

    public void setPatients(ArrayList<Patient> p)         { patients     = p; }
    public void setDoctors(ArrayList<Doctor> d)           { doctors      = d; }
    public void setAppointments(ArrayList<Appointment> a) { appointments = a; }
    public void setLabTests(ArrayList<LabTest> t)         { labTests     = t; }

    // Simple ID generators
    public String newPatientID() { return "P" + (patients.size()     + 1); }
    public String newDoctorID()  { return "D" + (doctors.size()      + 1); }
    public String newApptID()    { return "A" + (appointments.size() + 1); }
    public String newTestID()    { return "T" + (labTests.size()     + 1); }
}

// -----------------------------------------------------------
class FileManager {
    private static final String FILE = "hms_data.dat";

    public static void saveData(HospitalManager hm) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(hm.getPatients());
            out.writeObject(hm.getDoctors());
            out.writeObject(hm.getAppointments());
            out.writeObject(hm.getLabTests());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Save failed: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadData(HospitalManager hm) {
        File f = new File(FILE);
        if (!f.exists()) return; // first run — no file yet

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            hm.setPatients    ((ArrayList<Patient>)     in.readObject());
            hm.setDoctors     ((ArrayList<Doctor>)      in.readObject());
            hm.setAppointments((ArrayList<Appointment>) in.readObject());
            hm.setLabTests    ((ArrayList<LabTest>)     in.readObject());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Load failed: " + e.getMessage());
        }
    }
}


// ============================================================
//  GUI CLASSES
// ============================================================

class LoginDialog extends JDialog {
    private JTextField     userField = new JTextField();
    private JPasswordField passField = new JPasswordField();
    private boolean        authenticated = false;
    private int            attempts = 0;

    public LoginDialog(JFrame parent) {
        super(parent, "HMS Login", true);
        setSize(300, 180);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panel.add(new JLabel("Username:")); panel.add(userField);
        panel.add(new JLabel("Password:")); panel.add(passField);

        JButton btn = new JButton("Login");
        btn.addActionListener(e -> checkLogin());
        panel.add(new JLabel()); panel.add(btn);

        // Also allow pressing Enter to login
        passField.addActionListener(e -> checkLogin());

        add(panel);
    }

    private void checkLogin() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());

        if ("admin".equals(user) && "admin123".equals(pass)) {
            authenticated = true;
            dispose();
        } else {
            attempts++;
            if (attempts >= 3) {
                JOptionPane.showMessageDialog(this, "Too many attempts. Exiting.");
                System.exit(0);
            }
            JOptionPane.showMessageDialog(this,
                "Wrong! " + (3 - attempts) + " attempt(s) left.");
            passField.setText("");
        }
    }

    public boolean isAuthenticated() { return authenticated; }
}

// -----------------------------------------------------------
class PatientPanel extends JPanel {
    private HospitalManager   hm;
    private JTextField        nameF    = new JTextField();
    private JTextField        ageF     = new JTextField();
    private JTextField        cnicF    = new JTextField();
    private JTextField        diseaseF = new JTextField();
    private DefaultTableModel model;

    public PatientPanel(HospitalManager hm) {
        this.hm = hm;
        setLayout(new BorderLayout(10, 10));
        add(buildForm(),  BorderLayout.NORTH);
        add(buildTable(), BorderLayout.CENTER);
        loadTable();
    }

    private JPanel buildForm() {
        JPanel p = new JPanel(new GridLayout(5, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder("Register New Patient"));

        p.add(new JLabel("Name:"));    p.add(nameF);
        p.add(new JLabel("Age:"));     p.add(ageF);
        p.add(new JLabel("CNIC:"));    p.add(cnicF);
        p.add(new JLabel("Disease:")); p.add(diseaseF);

        JButton btn = new JButton("Save Patient");
        btn.addActionListener(e -> save());
        p.add(new JLabel()); p.add(btn);
        return p;
    }

    private JScrollPane buildTable() {
        model = new DefaultTableModel(
            new String[]{"ID", "Name", "Age", "CNIC", "Disease"}, 0);
        return new JScrollPane(new JTable(model));
    }

    private void save() {
        String name = nameF.getText().trim();
        String age  = ageF.getText().trim();
        String cnic = cnicF.getText().trim();
        String dis  = diseaseF.getText().trim();

        if (name.isEmpty() || age.isEmpty() || cnic.isEmpty() || dis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields."); return;
        }
        int a;
        try { a = Integer.parseInt(age); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number."); return;
        }

        Patient pt = new Patient(hm.newPatientID(), name, a, cnic, dis);
        hm.addPatient(pt);
        FileManager.saveData(hm);

        nameF.setText(""); ageF.setText(""); cnicF.setText(""); diseaseF.setText("");
        loadTable();
        JOptionPane.showMessageDialog(this, "Patient saved! ID: " + pt.getPatientID());
    }

    public void loadTable() {
        model.setRowCount(0);
        for (Patient p : hm.getPatients()) model.addRow(p.toRow());
    }
}

// -----------------------------------------------------------
class DoctorPanel extends JPanel {
    private HospitalManager   hm;
    private JTextField        nameF  = new JTextField();
    private JTextField        ageF   = new JTextField();
    private JTextField        cnicF  = new JTextField();
    private JTextField        specF  = new JTextField();
    private JTextField        schedF = new JTextField();
    private DefaultTableModel model;

    public DoctorPanel(HospitalManager hm) {
        this.hm = hm;
        setLayout(new BorderLayout(10, 10));
        add(buildForm(),  BorderLayout.NORTH);
        add(buildTable(), BorderLayout.CENTER);
        loadTable();
    }

    private JPanel buildForm() {
        JPanel p = new JPanel(new GridLayout(6, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder("Add New Doctor"));

        p.add(new JLabel("Name:"));           p.add(nameF);
        p.add(new JLabel("Age:"));            p.add(ageF);
        p.add(new JLabel("CNIC:"));           p.add(cnicF);
        p.add(new JLabel("Specialization:")); p.add(specF);
        p.add(new JLabel("Schedule:"));       p.add(schedF);

        JButton btn = new JButton("Save Doctor");
        btn.addActionListener(e -> save());
        p.add(new JLabel()); p.add(btn);
        return p;
    }

    private JScrollPane buildTable() {
        model = new DefaultTableModel(
            new String[]{"ID", "Name", "Age", "Specialization", "Schedule"}, 0);
        return new JScrollPane(new JTable(model));
    }

    private void save() {
        String name  = nameF.getText().trim();
        String age   = ageF.getText().trim();
        String cnic  = cnicF.getText().trim();
        String spec  = specF.getText().trim();
        String sched = schedF.getText().trim();

        if (name.isEmpty() || age.isEmpty() || cnic.isEmpty()
                || spec.isEmpty() || sched.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields."); return;
        }
        int a;
        try { a = Integer.parseInt(age); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number."); return;
        }

        Doctor d = new Doctor(hm.newDoctorID(), name, a, cnic, spec, sched);
        hm.addDoctor(d);
        FileManager.saveData(hm);

        nameF.setText(""); ageF.setText(""); cnicF.setText("");
        specF.setText(""); schedF.setText("");
        loadTable();
        JOptionPane.showMessageDialog(this, "Doctor saved! ID: " + d.getDoctorID());
    }

    public void loadTable() {
        model.setRowCount(0);
        for (Doctor d : hm.getDoctors()) model.addRow(d.toRow());
    }
}

// -----------------------------------------------------------
class AppointmentPanel extends JPanel {
    private HospitalManager   hm;
    private JComboBox<Patient> patientBox = new JComboBox<>();
    private JComboBox<Doctor>  doctorBox  = new JComboBox<>();
    private JTextField         dateF      = new JTextField("YYYY-MM-DD");
    private DefaultTableModel  model;

    public AppointmentPanel(HospitalManager hm) {
        this.hm = hm;
        setLayout(new BorderLayout(10, 10));
        add(buildForm(),  BorderLayout.NORTH);
        add(buildTable(), BorderLayout.CENTER);
        loadTable();
    }

    private JPanel buildForm() {
        JPanel p = new JPanel(new GridLayout(4, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder("Book Appointment"));

        p.add(new JLabel("Patient:")); p.add(patientBox);
        p.add(new JLabel("Doctor:"));  p.add(doctorBox);
        p.add(new JLabel("Date:"));    p.add(dateF);

        JButton btn = new JButton("Book Appointment");
        btn.addActionListener(e -> book());
        p.add(new JLabel()); p.add(btn);
        return p;
    }

    private JScrollPane buildTable() {
        model = new DefaultTableModel(
            new String[]{"ID", "Patient", "Doctor", "Date", "Status"}, 0);
        JTable table = new JTable(model);

        // Click a row to cancel
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            String id     = (String) model.getValueAt(row, 0);
            String status = (String) model.getValueAt(row, 4);
            if ("Booked".equals(status)) {
                int c = JOptionPane.showConfirmDialog(this,
                    "Cancel appointment " + id + "?", "Cancel", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) {
                    hm.cancelAppointment(id);
                    FileManager.saveData(hm);
                    loadTable();
                }
            }
        });

        return new JScrollPane(table);
    }

    public void refreshDropdowns() {
        patientBox.removeAllItems();
        doctorBox.removeAllItems();
        for (Patient p : hm.getPatients()) patientBox.addItem(p);
        for (Doctor  d : hm.getDoctors())  doctorBox.addItem(d);
    }

    private void book() {
        Patient p = (Patient) patientBox.getSelectedItem();
        Doctor  d = (Doctor)  doctorBox.getSelectedItem();
        String date = dateF.getText().trim();

        if (p == null || d == null) {
            JOptionPane.showMessageDialog(this, "Add patients and doctors first."); return;
        }
        if (date.isEmpty() || date.equals("YYYY-MM-DD")) {
            JOptionPane.showMessageDialog(this, "Enter a valid date."); return;
        }

        String id   = hm.newApptID();
        Appointment a = new Appointment(id, p, d, date);
        hm.addAppointment(a);
        FileManager.saveData(hm);
        loadTable();
        JOptionPane.showMessageDialog(this, "Appointment booked! ID: " + id);
    }

    public void loadTable() {
        model.setRowCount(0);
        for (Appointment a : hm.getAppointments()) model.addRow(a.toRow());
    }
}

// -----------------------------------------------------------
class LabTestPanel extends JPanel {
    private HospitalManager   hm;
    private JComboBox<Patient> patientBox = new JComboBox<>();
    private JComboBox<String>  typeBox    =
        new JComboBox<>(new String[]{"Blood Test", "Urine Test", "X-Ray Test"});
    private JTextField dateF  = new JTextField("YYYY-MM-DD");
    private JTextField field1 = new JTextField();
    private JTextField field2 = new JTextField();
    private JTextField field3 = new JTextField();
    private JLabel     label1 = new JLabel();
    private JLabel     label2 = new JLabel();
    private JLabel     label3 = new JLabel();
    private DefaultTableModel model;
    private JTable labTable;

    public LabTestPanel(HospitalManager hm) {
        this.hm = hm;
        setLayout(new BorderLayout(10, 10));
        add(buildTop(),    BorderLayout.NORTH);
        add(buildTable(),  BorderLayout.CENTER);
        add(buildResult(), BorderLayout.SOUTH);
        setLabels(); // set initial field labels
    }

    private JPanel buildTop() {
        JPanel p = new JPanel(new GridLayout(4, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder("Assign Lab Test"));

        typeBox.addActionListener(e -> setLabels());

        p.add(new JLabel("Patient:"));   p.add(patientBox);
        p.add(new JLabel("Test Type:")); p.add(typeBox);
        p.add(new JLabel("Date:"));      p.add(dateF);

        JButton btn = new JButton("Assign Test");
        btn.addActionListener(e -> assign());
        p.add(new JLabel()); p.add(btn);
        return p;
    }

    private JPanel buildResult() {
        JPanel p = new JPanel(new GridLayout(4, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder(
            "Enter Results  (click a test row above first)"));

        p.add(label1); p.add(field1);
        p.add(label2); p.add(field2);
        p.add(label3); p.add(field3);

        JButton btn = new JButton("Submit Results");
        btn.addActionListener(e -> submitResults());
        p.add(new JLabel()); p.add(btn);
        return p;
    }

    private JScrollPane buildTable() {
        model    = new DefaultTableModel(
            new String[]{"Test ID", "Type", "Patient ID", "Date", "Status"}, 0);
        labTable = new JTable(model);
        return new JScrollPane(labTable);
    }

    // Change result field labels based on selected test type
    private void setLabels() {
        String t = (String) typeBox.getSelectedItem();
        if ("Blood Test".equals(t)) {
            label1.setText("Hemoglobin:"); label2.setText("WBC:"); label3.setText("RBC:");
        } else if ("Urine Test".equals(t)) {
            label1.setText("pH:"); label2.setText("Glucose:"); label3.setText("Protein:");
        } else {
            label1.setText("Body Part:"); label2.setText("Notes:"); label3.setText("(N/A)");
        }
    }

    public void refreshPatients() {
        patientBox.removeAllItems();
        for (Patient p : hm.getPatients()) patientBox.addItem(p);
    }

    private void assign() {
        Patient p = (Patient) patientBox.getSelectedItem();
        String  t = (String)  typeBox.getSelectedItem();
        String  d = dateF.getText().trim();

        if (p == null) {
            JOptionPane.showMessageDialog(this, "Register a patient first."); return;
        }

        String  id = hm.newTestID();
        LabTest test;
        if ("Blood Test".equals(t))      test = new BloodTest(id, p.getPatientID(), d);
        else if ("Urine Test".equals(t)) test = new UrineTest(id, p.getPatientID(), d);
        else                             test = new XRayTest (id, p.getPatientID(), d);

        hm.addLabTest(test);
        FileManager.saveData(hm);
        loadTable();
        JOptionPane.showMessageDialog(this, "Test assigned! ID: " + id);
    }

    private void submitResults() {
        int row = labTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Click a test row first."); return;
        }

        String testID = (String) model.getValueAt(row, 0);
        String status = (String) model.getValueAt(row, 4);

        if ("Done".equals(status)) {
            JOptionPane.showMessageDialog(this, "Results already submitted."); return;
        }

        for (LabTest t : hm.getLabTests()) {
            if (t.getTestID().equals(testID)) {
                t.setResults(field1.getText().trim(),
                             field2.getText().trim(),
                             field3.getText().trim());
                hm.updateReport(t);
                FileManager.saveData(hm);
                loadTable();
                field1.setText(""); field2.setText(""); field3.setText("");
                JOptionPane.showMessageDialog(this, "Results saved!");
                return;
            }
        }
    }

    public void loadTable() {
        model.setRowCount(0);
        for (LabTest t : hm.getLabTests()) model.addRow(t.toRow());
    }
}

// -----------------------------------------------------------
class ReportPanel extends JPanel {
    private HospitalManager   hm;
    private JTextField        searchF = new JTextField();
    private DefaultTableModel model;
    private JTable            reportTable;

    public ReportPanel(HospitalManager hm) {
        this.hm = hm;
        setLayout(new BorderLayout(10, 10));
        add(buildSearch(), BorderLayout.NORTH);
        add(buildTable(),  BorderLayout.CENTER);
        add(buildButton(), BorderLayout.SOUTH);
    }

    private JPanel buildSearch() {
        JPanel p = new JPanel(new GridLayout(2, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder("View Patient Reports"));

        JButton btn = new JButton("Search");
        btn.addActionListener(e -> search());
        searchF.addActionListener(e -> search()); // press Enter to search

        p.add(new JLabel("Enter Patient ID:")); p.add(searchF);
        p.add(new JLabel());                    p.add(btn);
        return p;
    }

    private JScrollPane buildTable() {
        model       = new DefaultTableModel(new String[]{"#", "Report Preview"}, 0);
        reportTable = new JTable(model);
        reportTable.setRowHeight(24);
        return new JScrollPane(reportTable);
    }

    private JPanel buildButton() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btn = new JButton("View Full Report");
        btn.addActionListener(e -> viewFull());
        p.add(btn);
        return p;
    }

    private void search() {
        String id = searchF.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a Patient ID."); return;
        }

        Patient p = hm.findPatient(id);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "No patient found with ID: " + id); return;
        }

        model.setRowCount(0);
        int i = 1;
        for (String r : p.getReports()) {
            model.addRow(new Object[]{ i++, r.split("\n")[0] }); // first line as preview
        }

        if (p.getReports().isEmpty())
            JOptionPane.showMessageDialog(this, "No reports yet for this patient.");
    }

    private void viewFull() {
        int row = reportTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a report row first."); return;
        }

        Patient p = hm.findPatient(searchF.getText().trim());
        if (p == null) return;

        String fullReport = p.getReports().get(row);
        JTextArea area = new JTextArea(fullReport);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setEditable(false);
        JOptionPane.showMessageDialog(this,
            new JScrollPane(area), "Full Report", JOptionPane.INFORMATION_MESSAGE);
    }
}