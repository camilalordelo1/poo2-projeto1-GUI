import javax.swing.*;

public class Main {

    /*public static void main(String[] args){
        new Screen();
    }*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface app = new UserInterface();
            app.setVisible(true);
        });
    }

}