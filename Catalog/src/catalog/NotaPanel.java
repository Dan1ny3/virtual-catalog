package catalog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class NotaPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public NotaPanel() {
        setLayout(new BorderLayout());

        // Configurarea tabelei
        String[] columnNames = {"ID", "Elev", "Curs", "Nota", "Data"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panoul cu butoane
        JPanel buttonPanel = new JPanel();
        JButton listButton = new JButton("Listare Note");
        buttonPanel.add(listButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ascultător de acțiuni
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Nota> note = Nota.getAllNote();
                    tableModel.setRowCount(0); // Golește tabelul
                    for (Nota nota : note) {
                        tableModel.addRow(new Object[]{
                            nota.getId(), nota.getElev().getNume(), nota.getCurs().getDenumire(),
                            nota.getNota(), nota.getData()
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
