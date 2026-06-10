import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;


class PatientPanel extends BasePanel {
    private JTextField nameF    = HMS.field();
    private JTextField ageF     = HMS.field();
    private JTextField cnicF    = HMS.field();
    private JTextField diseaseF = HMS.field();

    PatientPanel(HospitalManager hm) {
        super(hm);

        JPanel formCard = makeCard("Register New Patient");
        JPanel grid = buildForm(
            new String[]{"Full Name", "Age", "CNIC / ID", "Diagnosis"},
            new JComponent[]{nameF, ageF, cnicF, diseaseF}
        );

        JButton saveBtn = HMS.btnSuccess("  + Register Patient  ");
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnRow.setOpaque(false);
        btnRow.add(saveBtn);
        saveBtn.addActionListener(e -> save());

        JPanel formContent = new JPanel(new BorderLayout(0, 10));
        formContent.setOpaque(false);
        formContent.add(grid,   BorderLayout.CENTER);
        formContent.add(btnRow, BorderLayout.SOUTH);
        formCard.add(formContent, BorderLayout.CENTER);

        JPanel tableCard = makeCard("Registered Patients");
        JScrollPane sp = buildTable("Patient ID", "Name", "Age", "CNIC", "Diagnosis");
        tableCard.add(sp, BorderLayout.CENTER);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(formCard, BorderLayout.CENTER);

        add(top,       BorderLayout.NORTH);
        add(tableCard, BorderLayout.CENTER);
        reloadTable();
    }

    private void save() {
        String name = nameF.getText().trim();
        String ageS = ageF.getText().trim();
        String cnic = cnicF.getText().trim();
        String dis  = diseaseF.getText().trim();
        if (name.isEmpty() || ageS.isEmpty() || cnic.isEmpty() || dis.isEmpty()) {
            HMS.err(this, "Please fill in all fields."); return;
        }
        int age;
        try { age = Integer.parseInt(ageS); }
        catch (NumberFormatException ex) { HMS.err(this, "Age must be a whole number."); return; }
        if (age <= 0 || age > 150) { HMS.err(this, "Enter a valid age (1-150)."); return; }

        Patient p = new Patient(hm.nextPatientID(), name, age, cnic, dis);
        hm.addPatient(p);
        FileManager.saveData(hm);
        nameF.setText(""); ageF.setText(""); cnicF.setText(""); diseaseF.setText("");
        reloadTable();
        HMS.info(this, "Patient registered!  ID: " + p.getPatientID());
    }

    void reloadTable() {
        model.setRowCount(0);
        for (Patient p : hm.getPatients()) model.addRow(p.toRow());
    }
}
