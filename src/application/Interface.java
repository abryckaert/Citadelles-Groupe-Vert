package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
    private JTextField textField;
    private JButton playButton, rulesButton, quitButton;
    private JPanel panel;
    private JLabel rulesLabel;
    private Image backgroundImage;

    public Interface() {
        // Charger l'image de fond
        try {
            backgroundImage = new ImageIcon("C:\\Users\\lucil\\Documents\\GitHub\\Citadelles-Groupe-Vert\\src\\images\\background.png").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Création d'un JPanel avec une image de fond
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        // Utiliser BoxLayout pour aligner les éléments verticalement
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Créer un panneau pour le label et la zone de texte
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.setOpaque(false); // Rendre le panneau transparent

        JLabel label = new JLabel("Entrez votre nom:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField = new JTextField(20);
        textField.setMaximumSize(textField.getPreferredSize());

        textPanel.add(label);
        textPanel.add(textField);

        // Initialiser rulesLabel
        rulesLabel = new JLabel();
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton = new JButton("Jouer une partie");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jouerPartie();
            }
        });

        rulesButton = new JButton("Afficher les règles");
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherRegles();
            }
        });

        quitButton = new JButton("Quitter");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitter();
            }
        });

        // Ajouter les éléments au panneau principal
        panel.add(Box.createVerticalStrut(250));
        panel.add(textPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(playButton);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(rulesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(quitButton);
        panel.add(Box.createVerticalGlue());

        // Ajouter rulesLabel au panel
        panel.add(rulesLabel);

        // Ajouter le panel au JFrame
        this.add(panel);

        // Paramètres de la fenêtre
        this.setTitle("Citadelles");
        this.setSize(850, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrer la fenêtre
    }

    private void jouerPartie() {
        // La logique pour jouer une partie
        System.out.println("Jouer une partie");
        Jeu jeu = new Jeu();
    }

    private void afficherRegles() {
        // Définir le texte du label pour les règles
        String reglesText = "<html><div style='color:white; fontsize=40px;'>Citadelles est un jeu de plateau créé en 2000 par Bruno Faidutti<br>"
                + "aux éditions Edge Entertainment.<br><br>"
                + "Deux à huit joueurs s’affrontent pour construire le plus rapidement possible la plus prestigieuse<br>"
                + "cité. Pour cela, chaque joueur devra construire des quartiers, ayant chacun des coûts differents.<br><br>"
                + "Comme dans un jeu de rôle, chaque joueur doit se mettre dans la peau d’un personnage, à ceci<br>"
                + "près que les joueurs changent de personnage à chaque tour de jeu. Ces personnages ont chacun des<br>"
                + "pouvoirs particuliers : la meilleure stratégie est de choisir un personnage au bon moment du jeu.</div></html>";
        rulesLabel.setText(reglesText);
        rulesLabel.setVisible(true); // Rendre le label visible
        panel.revalidate();
        panel.repaint();
    }

    private void quitter() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Interface().setVisible(true);
        });
    }
}
