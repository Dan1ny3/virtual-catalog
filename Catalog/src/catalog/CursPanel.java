package catalog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class CursPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public CursPanel() {
        setLayout(new BorderLayout());

        // Configurarea tabelei
        String[] columnNames = {"ID", "Denumire", "Profesor"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panoul cu butoane
        JPanel buttonPanel = new JPanel();
        JButton listButton = new JButton("Listare Cursuri");
        buttonPanel.add(listButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ascultător de acțiuni
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Curs> cursuri = Curs.getAllCursuri();
                    tableModel.setRowCount(0); // Golește tabelul
                    for (Curs curs : cursuri) {
                        tableModel.addRow(new Object[]{
                            curs.getId(), curs.getDenumire(), curs.getProfesor().getNume()
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

