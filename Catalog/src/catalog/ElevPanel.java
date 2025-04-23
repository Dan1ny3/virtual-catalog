package catalog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ElevPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ElevPanel() {
        setLayout(new BorderLayout());

        // Configurarea tabelei
        String[] columnNames = {"ID", "Nume", "Prenume", "Clasa", "Data Nașterii"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panoul cu butoane
        JPanel buttonPanel = new JPanel();
        JButton listButton = new JButton("Listare Elevi");
        buttonPanel.add(listButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ascultător de acțiuni
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Elev> elevi = Elev.getAllElevi();
                    tableModel.setRowCount(0); // Golește tabelul
                    for (Elev elev : elevi) {
                        tableModel.addRow(new Object[]{
                            elev.getId(), elev.getNume(), elev.getPrenume(),
                            elev.getClasa(), elev.getDataNasterii()
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
