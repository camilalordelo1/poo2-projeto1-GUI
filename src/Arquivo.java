import javax.swing.*;
import java.io.*;

public class Arquivo {
    private JTextArea textArea;
    private File arquivoAtual;

    public Arquivo(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void abrirArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            arquivoAtual = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoAtual))) {
                textArea.setText(""); // Limpa o texto anterior
                String linha;
                while ((linha = reader.readLine()) != null) {
                    textArea.append(linha + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void fecharArquivo() {
        if (arquivoAtual != null) {
            arquivoAtual = null; // Reseta o arquivo atual
            textArea.setText(""); // Limpa a área de texto
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo está aberto!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}
