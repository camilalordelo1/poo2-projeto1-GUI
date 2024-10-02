import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionListener;

class UserInterface extends JFrame {

    private final JTextArea textArea;
    private final JFileChooser fileChooser;
    private final JPanel animatedBackgroundPanel; // Panel for applying background color

    public UserInterface() {
        initializeFrame();
        textArea = createTextArea();
        fileChooser = new JFileChooser();
        animatedBackgroundPanel = createAnimatedBackgroundPanel();

        JScrollPane scrollPane = createScrollPane();
        JPanel textPanel = createTextPanel(scrollPane);
        JLabel statusLabel = new JLabel("Status: Ready");
        JPanel statusBar = createStatusBar(statusLabel);
        JMenuBar menuBar = createMenuBar(statusLabel);  // Corrigido: statusLabel é um JLabel

        animatedBackgroundPanel.add(textPanel, BorderLayout.CENTER);
        animatedBackgroundPanel.add(statusBar, BorderLayout.SOUTH);
        add(animatedBackgroundPanel);
        setJMenuBar(menuBar);
    }

    private void initializeFrame() {
        setTitle("Java Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center window
    }

    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));  // Padding
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));  // Set max size
        return scrollPane;
    }

    private JPanel createTextPanel(JScrollPane scrollPane) {
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(20, 20, 20, 20));  // External margin
        textPanel.add(scrollPane, BorderLayout.CENTER);
        return textPanel;
    }

    private JPanel createAnimatedBackgroundPanel() {
        JPanel animatedBackgroundPanel = new JPanel(new BorderLayout());
        animatedBackgroundPanel.setBackground(Color.WHITE);  // Initial background color
        return animatedBackgroundPanel;
    }

    private JPanel createStatusBar(JLabel statusLabel) {
        JPanel statusBar = new JPanel();
        statusBar.add(statusLabel);
        return statusBar;
    }


    private JMenuBar createMenuBar(JLabel statusLabel) {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open File", e -> openFile(statusLabel));  // Corrigido: statusLabel é JLabel
        addMenuItem(fileMenu, "Close File", e -> closeFile(statusLabel));  // Corrigido
        addMenuItem(fileMenu, "Exit", e -> System.exit(0));

        // Settings menu
        JMenu settingsMenu = new JMenu("Settings");
        addMenuItem(settingsMenu, "Defaults", e -> adjustDefaults());
        addMenuItem(settingsMenu, "Colors", e -> changeColors());
        addMenuItem(settingsMenu, "Speed", e -> adjustSpeed());

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        addMenuItem(helpMenu, "Help", e -> displayHelp());
        addMenuItem(helpMenu, "About", e -> displayAbout());

        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void addMenuItem(JMenu menu, String label, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(action);
        menu.add(menuItem);
    }

    private void openFile(JLabel statusLabel) {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
                statusLabel.setText("Status: File '" + file.getName() + "' loaded");
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Status: Error loading file");
            }
        }
    }

    private void closeFile(JLabel statusLabel) {
        textArea.setText("");
        statusLabel.setText("Status: No file opened");
    }

    private void displayHelp() {
        JOptionPane.showMessageDialog(this, "This is the help section of the application!", "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayAbout() {
        JOptionPane.showMessageDialog(this, "Application version 1.0\n", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void adjustDefaults() {
        // Logic for adjusting animation defaults
    }


    private void adjustSpeed() {
        // Logic for adjusting animation speed
    }

    private void changeColors() {
        Color newColor = JColorChooser.showDialog(this, "Choose background color", animatedBackgroundPanel.getBackground());
        if (newColor != null) {
            textArea.setBackground(newColor);
        }
    }
}
