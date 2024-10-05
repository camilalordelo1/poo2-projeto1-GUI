import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen app = new Screen();
            app.setVisible(true);
        });
    }
}