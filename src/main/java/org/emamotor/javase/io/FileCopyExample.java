package org.emamotor.javase.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Yoshimasa Tanabe
 */
public class FileCopyExample extends JFrame implements ActionListener {

    private JButton from = new JButton("Copy from");
    private JButton to = new JButton("Copy to");
    private JButton copy = new JButton("Copy");
    private JLabel fromLabel = new JLabel("Copy Src.");
    private JLabel toLabel = new JLabel("Copy Dist.");
    private JLabel copyLabel = new JLabel();

    private JRadioButton eucFrom = new JRadioButton("EUC-JP");
    private JRadioButton sjisFrom = new JRadioButton("SJIS", true);
    private JRadioButton iso2022jpFrom = new JRadioButton("ISO2022JP");
    private JRadioButton eucTo = new JRadioButton("EUC-JP");
    private JRadioButton sjisTo = new JRadioButton("SJIS", true);
    private JRadioButton iso2022jpTo = new JRadioButton("ISO2022JP");

    private JFileChooser chooser = new JFileChooser();

    private String fromCharCode = "SJIS";
    private String toCharCode = "SJIS";

    private File fromFile = null;
    private File toFile = null;

    public FileCopyExample() {
        super("File Copy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        getContentPane().setLayout(new GridLayout(6, 1));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        ButtonGroup fromGroup = new ButtonGroup();
        ButtonGroup toGroup = new ButtonGroup();

        fromGroup.add(eucFrom);
        fromGroup.add(sjisFrom);
        fromGroup.add(iso2022jpFrom);

        toGroup.add(eucTo);
        toGroup.add(sjisTo);
        toGroup.add(iso2022jpTo);

        panel1.add(from);
        panel1.add(eucFrom);
        panel1.add(sjisFrom);
        panel1.add(iso2022jpFrom);

        panel2.add(to);
        panel2.add(eucTo);
        panel2.add(sjisTo);
        panel2.add(iso2022jpTo);

        getContentPane().add(panel1);
        getContentPane().add(fromLabel);
        getContentPane().add(panel2);
        getContentPane().add(toLabel);
        getContentPane().add(copy);
        getContentPane().add(copyLabel);

        from.addActionListener(this);
        to.addActionListener(this);
        copy.addActionListener(this);
        eucFrom.addActionListener(this);
        sjisFrom.addActionListener(this);
        iso2022jpFrom.addActionListener(this);
        iso2022jpTo.addActionListener(this);
        eucTo.addActionListener(this);
        sjisTo.addActionListener(this);
        iso2022jpTo.addActionListener(this);

        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new TextFilter());
    }

    public static void main(String[] args) {
        new FileCopyExample().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(from)) {
            setFromFile();
        } else if (e.getSource().equals(to)) {
            setToFile();
        } else if (e.getSource().equals(copy)) {
            copyFile();
        } else if (e.getSource().equals(eucFrom)) {
            fromCharCode = "EUC-JP";
        } else if (e.getSource().equals(sjisFrom)) {
            fromCharCode = "SJIS";
        } else if (e.getSource().equals(iso2022jpFrom)) {
            fromCharCode = "ISO2022JP";
        } else if (e.getSource().equals(eucTo)) {
            toCharCode = "EUC-JP";
        } else if (e.getSource().equals(sjisTo)) {
            toCharCode = "SJIS";
        } else if (e.getSource().equals(iso2022jpTo)) {
            toCharCode = "ISO2022JP";
        }
    }

    private void setFromFile() {
        int retrunVal = chooser.showOpenDialog(this);
        if (retrunVal == JFileChooser.APPROVE_OPTION) {
            fromFile = chooser.getSelectedFile();
            fromLabel.setText(fromFile.getAbsolutePath());
        }
    }

    private void setToFile() {
        int retrunVal = chooser.showOpenDialog(this);
        if (retrunVal == JFileChooser.APPROVE_OPTION) {
            toFile = chooser.getSelectedFile();
            toLabel.setText(toFile.getAbsolutePath());
        }
    }

    private void copyFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fromFile), fromCharCode));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(toFile), toCharCode));) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            writer.close();
            reader.close();

            copyLabel.setText("Copy Complete!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
