import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        FileReaderApp app = new FileReaderApp(); 
        app.setVisible(true);
    });
}


public class FileReaderApp extends JFrame implements ActionListener 
{
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private JMenuItem openFileMenuItem, closeFileMenuItem, exitMenuItem;

    public FileReaderApp() {

        setTitle("Leitor de Arquivos de Texto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 


        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");


        openFileMenuItem = new JMenuItem("Abrir Arquivo");
        closeFileMenuItem = new JMenuItem("Fechar Arquivo");
        exitMenuItem = new JMenuItem("Sair");


        fileMenu.add(openFileMenuItem);
        fileMenu.add(closeFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);


        menuBar.add(fileMenu);
        setJMenuBar(menuBar);


        openFileMenuItem.addActionListener(this);
        closeFileMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

 
        fileChooser = new JFileChooser();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openFileMenuItem) {

            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
  
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    textArea.read(reader, null); 
                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == closeFileMenuItem) {
        
            textArea.setText("");
        } else if (e.getSource() == exitMenuItem) {
            
            System.exit(0);
        }
    }

    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(() -> {
            FileReaderApp app = new FileReaderApp();
            app.setVisible(true);
        });
    }
}
