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

    private JFileChooser chooser = new JFileChooser();

    private File fromFile = null;
    private File toFile = null;

    public FileCopyExample() {
        super("File Copy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        getContentPane().setLayout(new GridLayout(6, 1));

        getContentPane().add(from);
        getContentPane().add(fromLabel);
        getContentPane().add(to);
        getContentPane().add(toLabel);
        getContentPane().add(copy);
        getContentPane().add(copyLabel);

        from.addActionListener(this);
        to.addActionListener(this);
        copy.addActionListener(this);

        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new PictureFilter());
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
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(fromFile));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(toFile));) {

            byte[] buffer = new byte[256];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            input.close();

            copyLabel.setText("Copy Complete!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
