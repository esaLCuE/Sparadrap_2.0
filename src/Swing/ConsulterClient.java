package Swing;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.Client.clients;
import static classes.FonctionsCommunes.*;

public class ConsulterClient extends JFrame {
    private JComboBox clientsBox;
    private JButton precedentButton;
    private JButton quitterButton;
    private JButton ajoutClientButton;
    private JButton voirClientButton;
    private JButton suppClientButton;
    private JPanel contentPane;

    public ConsulterClient(){
        JFrame frameCli = this;

        this.setTitle("Choix du client");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);

        ajoutHistorique(this);

        for (int i =0; i<clients.size();i++){
            clientsBox.addItem(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
        }

        if (clients.isEmpty()){
            suppClientButton.setEnabled(false);
            voirClientButton.setEnabled(false);
        }

        ajoutClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                CreerClient creerCli = new CreerClient();
                creerCli.setVisible(true);
                if (!clients.isEmpty()) {
                    suppClientButton.setEnabled(true);
                    voirClientButton.setEnabled(true);
                }
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

        suppClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!clients.isEmpty()) {
                    int input = JOptionPane.showConfirmDialog(null, "Supprimer " + clientsBox.getSelectedItem() + " ?",
                            "Suppression", JOptionPane.YES_NO_OPTION);
                    if (input == JOptionPane.YES_OPTION) {
                        int id = clientsBox.getSelectedIndex();
                        clients.remove(id);
                        majClients();
                    }
                } if (clients.isEmpty()) {
                    suppClientButton.setEnabled(false);
                    voirClientButton.setEnabled(false);
                }
            }
        });

        precedentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                precedent();

            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                precedent();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitterProgramme();
            }
        });

    }

    public void majClients(){
        clientsBox.removeAllItems();
        for (int i =0; i<clients.size();i++){
            clientsBox.addItem(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
        }
    }
}
