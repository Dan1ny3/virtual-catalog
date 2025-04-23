package catalog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ProfesorPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ProfesorPanel() {
        setLayout(new BorderLayout());

        // Configurarea tabelei
        String[] columnNames = {"ID", "Nume", "Prenume"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panoul cu butoane
        JPanel buttonPanel = new JPanel();
        JButton listButton = new JButton("Listare Profesori");
        buttonPanel.add(listButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ascultător de acțiuni
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Profesor> profesori = Profesor.getAllProfesori();
                    tableModel.setRowCount(0); // Golește tabelul
                    for (Profesor profesor : profesori) {
                        tableModel.addRow(new Object[]{
                            profesor.getId(), profesor.getNume(), profesor.getPrenume()
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
