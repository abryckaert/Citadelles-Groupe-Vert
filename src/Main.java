import application.Configuration;
import application.Interface;
import application.Jeu;
import modele.*;
import test.*;

import javax.swing.*;
import javax.xml.xpath.XPathFactoryConfigurationException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
}

