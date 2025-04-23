package catalog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Catalog extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public Catalog() {
        setTitle("Catalog Școlar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels for students, professors, courses, and grades
        JPanel elevPanel = new ElevPanel();
        JPanel ProfesorPanel = new ProfesorPanel();
        JPanel CursPanel = new CursPanel();
        JPanel NotaPanel = new NotaPanel();

        cardPanel.add(elevPanel, "Elevi");
        cardPanel.add(ProfesorPanel, "Profesori");
        cardPanel.add(CursPanel, "Cursuri");
        cardPanel.add(NotaPanel, "Note");

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigare");

        JMenuItem eleviMenuItem = new JMenuItem("Elevi");
        eleviMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Elevi");
            }
        });

        JMenuItem profesoriMenuItem = new JMenuItem("Profesori");
        profesoriMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Profesori");
            }
        });

        JMenuItem cursuriMenuItem = new JMenuItem("Cursuri");
        cursuriMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Cursuri");
            }
        });

        JMenuItem noteMenuItem = new JMenuItem("Note");
        noteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Note");
            }
        });

        menu.add(eleviMenuItem);
        menu.add(profesoriMenuItem);
        menu.add(cursuriMenuItem);
        menu.add(noteMenuItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        add(cardPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Catalog().setVisible(true);
            }
        });
    }
}
