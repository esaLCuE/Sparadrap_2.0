package Swing;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.Client.clients;

public class ConsulterClient extends JFrame {
    private JComboBox clientsBox;
    private JButton precedentButton;
    private JButton quitterButton;
    private JButton ajoutClientButton;
    private JButton voirClientButton;
    private JButton suppClientButton;
    private JPanel contentPane;

    public ConsulterClient(){
        this.setTitle("Choix du client");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameCli = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        Accueil.ajoutHistorique(this);

        for (int i =0; i<clients.size();i++){
            clientsBox.addItem(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
        }

        ajoutClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                CreerClient creerCli = new CreerClient();
                creerCli.setVisible(true);
            }
        });

        voirClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientChoisi = (String) clientsBox.getSelectedItem();
                String secSocTemp = clientChoisi.replaceAll("^.*(- )","");
                int id=0;
                for (int i =0; i<clients.size();i++){
                    if (secSocTemp.equals(clients.get(i).getSecuSociale())){
                        id = i;
                        break;
                    }
                }
                setVisible(false);

                PageClient pageCli = new PageClient(id);
                pageCli.setVisible(true);
            }
        });

        precedentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Accueil.precedent();

            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Accueil.precedent();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accueil.quitterProgramme();
            }
        });

    }
}
