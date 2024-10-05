import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen extends JFrame {
    private JTextArea textArea;
    private AnimationPanel animationPanel;
    private Arquivo arquivo;

    public Screen() {
        setTitle("Título da Aplicação");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu arquivoMenu = new JMenu("Arquivo");
        JMenuItem abrirItem = new JMenuItem("Abrir");
        JMenuItem fecharItem = new JMenuItem("Fechar");
        JMenuItem sairItem = new JMenuItem("Sair");

        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(true);
        textArea.setBackground(Color.WHITE);

        arquivo = new Arquivo(textArea);

        abrirItem.addActionListener(e -> arquivo.abrirArquivo());
        fecharItem.addActionListener(e -> arquivo.fecharArquivo());
        sairItem.addActionListener(e -> System.exit(0));

        arquivoMenu.add(abrirItem);
        arquivoMenu.add(fecharItem);
        arquivoMenu.addSeparator();
        arquivoMenu.add(sairItem);

        JMenu configuracaoMenu = new JMenu("Configuração");
        JMenuItem animacaoItem = new JMenuItem("Animação");
        JMenuItem velocidadeItem = new JMenuItem("Velocidade");
        JMenuItem corItem = new JMenuItem("Cor");

        configuracaoMenu.add(animacaoItem);
        configuracaoMenu.add(velocidadeItem);
        configuracaoMenu.add(corItem);

        animacaoItem.addActionListener(e -> animationPanel.toggleAnimation());

        velocidadeItem.addActionListener(e -> {
            String[] opcoes = {"Lento", "Médio", "Rápido"};
            int escolha = JOptionPane.showOptionDialog(this,
                    "Escolha a velocidade da animação:",
                    "Velocidade",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[1]);

            switch (escolha) {
                case 0 -> animationPanel.setSpeed(100);
                case 1 -> animationPanel.setSpeed(50);
                case 2 -> animationPanel.setSpeed(20);
            }
        });

        corItem.addActionListener(e -> {
            Color novaCor = JColorChooser.showDialog(null, "Escolha uma cor", textArea.getBackground());
            if (novaCor != null) {
                textArea.setBackground(novaCor);
            }
        });

        JMenu ajudaMenu = new JMenu("Ajuda");
        JMenuItem comoItem = new JMenuItem("Como usar");
        JMenuItem sobreItem = new JMenuItem("Sobre");
        ajudaMenu.add(comoItem);
        ajudaMenu.add(sobreItem);

        menuBar.add(arquivoMenu);
        menuBar.add(configuracaoMenu);
        menuBar.add(ajudaMenu);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        animationPanel = new AnimationPanel();
        add(animationPanel, BorderLayout.CENTER);

        add(scrollPane, BorderLayout.NORTH);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                scrollPane.setPreferredSize(new Dimension(getWidth() - 20, 225));
                scrollPane.revalidate();
            }
        });
        setVisible(true);
    }
}