import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

// ── USMAN ────────────────────────────────────────────────────
// File Handling — saves and loads all data to/from hms_data.dat
// Uses ObjectOutputStream to save, ObjectInputStream to load.
// Every class must implement Serializable for this to work.
// ─────────────────────────────────────────────────────────────

class FileManager {
    private static final String FILE = "hms_data.dat";

    @SuppressWarnings("unchecked")
    public static void loadData(HospitalManager hm) {
        File f = new File(FILE);
        if (!f.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            hm.setPatients    ((ArrayList<Patient>)     in.readObject());
            hm.setDoctors     ((ArrayList<Doctor>)      in.readObject());
            hm.setAppointments((ArrayList<Appointment>) in.readObject());
            hm.setLabTests    ((ArrayList<LabTest>)     in.readObject());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Could not load data: " + e.getMessage(), "Load Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void saveData(HospitalManager hm) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(hm.getPatients());
            out.writeObject(hm.getDoctors());
            out.writeObject(hm.getAppointments());
            out.writeObject(hm.getLabTests());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Could not save data: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
