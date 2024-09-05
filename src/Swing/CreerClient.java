package Swing;

import javax.swing.*;

public class CreerClient extends JFrame{
    private JPanel contentPane;
    private JButton retourButton;
    private JButton quitterButton;
    private JButton validerButton;
    private JTextField prenomField;
    private JTextField ndfField;
    private JTextField adresseField;
    private JTextField cpField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField secuSocField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton ajouterSpecialisteButton;
    private JList list1;
    private JComboBox jourBox;
    private JComboBox moisBox;
    private JComboBox anneeBox;

    public CreerClient() {
        this.setTitle("Création du client");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameCreCli = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        Accueil.ajoutHistorique(this);

    }
}
