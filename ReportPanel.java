import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

class ReportPanel extends BasePanel {
    private JTextField searchF = HMS.field();

    ReportPanel(HospitalManager hm) {
        super(hm);

        JPanel searchCard = makeCard("Search Patient Reports");
        JPanel searchRow = new JPanel(new BorderLayout(10, 0));
        searchRow.setOpaque(false);
        searchRow.add(HMS.lbl("Patient ID :"), BorderLayout.WEST);
        searchRow.add(searchF,                 BorderLayout.CENTER);
        JButton searchBtn = HMS.btnPrimary("  Search  ");
        searchBtn.addActionListener(e -> search());
        searchF.addActionListener(e -> search());
        searchRow.add(searchBtn, BorderLayout.EAST);
        searchCard.add(searchRow, BorderLayout.CENTER);

        JPanel tableCard = makeCard("Reports");
        JScrollPane sp = buildTable("#", "Report Preview");
        table.getColumnModel().getColumn(0).setMaxWidth(45);
        tableCard.add(sp, BorderLayout.CENTER);

        JButton viewBtn = HMS.btnPrimary("  View Full Report  ");
        viewBtn.addActionListener(e -> viewFull());
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnRow.setOpaque(false);
        btnRow.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        btnRow.add(viewBtn);
        tableCard.add(btnRow, BorderLayout.SOUTH);

        add(searchCard, BorderLayout.NORTH);
        add(tableCard,  BorderLayout.CENTER);
    }

    private void search() {
        String id = searchF.getText().trim();
        if (id.isEmpty()) { HMS.err(this, "Enter a Patient ID (e.g. P1)."); return; }
        Patient p = hm.findPatient(id);
        if (p == null)    { HMS.err(this, "No patient found with ID: " + id); return; }
        model.setRowCount(0);
        ArrayList<String> rpts = p.getReports();
        if (rpts.isEmpty()) { HMS.info(this, "No reports found for patient " + id + "."); return; }
        int i = 1;
        for (String r : rpts) {
            String preview = r.split("\n")[0];
            model.addRow(new Object[]{i++, preview});
        }
    }

    private void viewFull() {
        int row = table.getSelectedRow();
        if (row < 0) { HMS.err(this, "Select a report row first."); return; }
        Patient p = hm.findPatient(searchF.getText().trim());
        if (p == null || row >= p.getReports().size()) return;

        String report = p.getReports().get(row);
        JTextArea area = new JTextArea(report, 16, 44);
        area.setFont(HMS.F_MONO);
        area.setEditable(false);
        area.setBackground(new Color(245, 248, 255));
        area.setForeground(new Color(20, 70, 180));
        area.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JScrollPane sp = HMS.scroll(area);
        JOptionPane.showMessageDialog(this, sp, "Full Report — " + p.getName(),
            JOptionPane.PLAIN_MESSAGE);
    }
}
