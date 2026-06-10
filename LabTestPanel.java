import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

class LabTestPanel extends BasePanel {
    private JComboBox<Patient> patCB  = HMS.combo();
    private JComboBox<String>  typeCB = HMS.comboStr("Blood Test", "Urine Test", "X-Ray");
    private JTextField dateF  = HMS.field();
    private JTextField f1 = HMS.field(), f2 = HMS.field(), f3 = HMS.field();
    private JLabel     l1 = HMS.lbl("—"), l2 = HMS.lbl("—"), l3 = HMS.lbl("—");

    LabTestPanel(HospitalManager hm) {
        super(hm);
        dateF.setText("YYYY-MM-DD");
        typeCB.addActionListener(e -> updateLabels());
        updateLabels();

        JPanel assignCard = makeCard("Assign Lab Test");
        JPanel assignGrid = buildForm(
            new String[]{"Patient", "Test Type", "Date"},
            new JComponent[]{patCB, typeCB, dateF}
        );
        JButton assignBtn = HMS.btnSuccess("  Assign Test  ");
        assignBtn.addActionListener(e -> assign());
        JPanel ab = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        ab.setOpaque(false); ab.add(assignBtn);
        JPanel assignContent = new JPanel(new BorderLayout(0, 10));
        assignContent.setOpaque(false);
        assignContent.add(assignGrid, BorderLayout.CENTER);
        assignContent.add(ab,         BorderLayout.SOUTH);
        assignCard.add(assignContent, BorderLayout.CENTER);

        JPanel resCard = makeCard("Enter Results  (select a test row first)");
        JPanel resGrid = new JPanel(new GridBagLayout());
        resGrid.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);
        gc.fill   = GridBagConstraints.HORIZONTAL;
        JLabel[] lbls = {l1, l2, l3};
        JTextField[] flds = {f1, f2, f3};
        for (int i = 0; i < 3; i++) {
            gc.gridx = 0; gc.gridy = i; gc.weightx = 0; resGrid.add(lbls[i], gc);
            gc.gridx = 1; gc.weightx = 1;               resGrid.add(flds[i], gc);
        }
        JButton submitBtn = HMS.btnPrimary("  Submit Results  ");
        submitBtn.addActionListener(e -> submitResults());
        JPanel sb = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        sb.setOpaque(false); sb.add(submitBtn);
        JPanel resContent = new JPanel(new BorderLayout(0,10));
        resContent.setOpaque(false);
        resContent.add(resGrid, BorderLayout.CENTER);
        resContent.add(sb,      BorderLayout.SOUTH);
        resCard.add(resContent, BorderLayout.CENTER);

        JPanel top = new JPanel(new GridLayout(1, 2, 12, 0));
        top.setOpaque(false);
        top.add(assignCard);
        top.add(resCard);

        JPanel tableCard = makeCard("Lab Tests");
        JScrollPane sp = buildTable("Test ID", "Type", "Patient ID", "Date", "Status");
        table.getColumnModel().getColumn(4).setCellRenderer(HMS.statusRenderer());
        tableCard.add(sp, BorderLayout.CENTER);

        add(top,       BorderLayout.NORTH);
        add(tableCard, BorderLayout.CENTER);
        reloadTable();
    }

    private void updateLabels() {
        String t = (String) typeCB.getSelectedItem();
        if ("Blood Test".equals(t)) {
            l1.setText("Hemoglobin"); l2.setText("WBC Count"); l3.setText("RBC Count");
        } else if ("Urine Test".equals(t)) {
            l1.setText("pH Value");   l2.setText("Glucose");   l3.setText("Protein");
        } else {
            l1.setText("Body Part");  l2.setText("Findings");  l3.setText("N/A");
        }
    }

    public void refreshCombos() {
        patCB.removeAllItems();
        for (Patient p : hm.getPatients()) patCB.addItem(p);
    }

    private void assign() {
        Patient p = (Patient) patCB.getSelectedItem();
        if (p == null) { HMS.err(this, "Register a patient first."); return; }
        String type = (String) typeCB.getSelectedItem();
        String date = dateF.getText().trim();
        if (date.isEmpty() || date.equals("YYYY-MM-DD")) { HMS.err(this, "Enter a valid date."); return; }

        String id = hm.nextTestID();
        LabTest t;
        if      ("Blood Test".equals(type)) t = new BloodTest(id, p.getPatientID(), date);
        else if ("Urine Test".equals(type)) t = new UrineTest(id, p.getPatientID(), date);
        else                                t = new XRayTest (id, p.getPatientID(), date);

        hm.addLabTest(t);
        FileManager.saveData(hm);
        reloadTable();
        HMS.info(this, "Lab test assigned!  ID: " + id);
    }

    private void submitResults() {
        int row = table.getSelectedRow();
        if (row < 0) { HMS.err(this, "Select a test row from the table first."); return; }
        String testID = (String) model.getValueAt(row, 0);
        String status = (String) model.getValueAt(row, 4);
        if ("Done".equals(status)) { HMS.err(this, "Results already submitted for this test."); return; }

        for (LabTest t : hm.getLabTests()) {
            if (t.getTestID().equals(testID)) {
                t.setResults(f1.getText().trim(), f2.getText().trim(), f3.getText().trim());
                hm.updateLabReport(t);
                FileManager.saveData(hm);
                reloadTable();
                f1.setText(""); f2.setText(""); f3.setText("");
                HMS.info(this, "Results saved successfully!");
                return;
            }
        }
        HMS.err(this, "Test not found.");
    }

    void reloadTable() {
        model.setRowCount(0);
        for (LabTest t : hm.getLabTests()) model.addRow(t.toRow());
    }
}
