package org.emamotor.javase.io;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Yoshimasa Tanabe
 */
public class SimpleEditorExample extends JFrame implements ActionListener {

    private JTextArea textArea = new JTextArea();

    private Action cutAction = new DefaultEditorKit.CutAction();
    private Action copyAction = new DefaultEditorKit.CopyAction();
    private Action pasteAction = new DefaultEditorKit.PasteAction();

    private JButton open = new JButton("Open");
    private JButton save = new JButton("save");
    private JButton cut = new JButton(cutAction);
    private JButton copy = new JButton(copyAction);
    private JButton paste = new JButton(pasteAction);

    private JFileChooser chooser = new JFileChooser();

    public SimpleEditorExample() {
        super("SimpleEditor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        getContentPane().add(panel1, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.SOUTH);

        panel1.add(open);
        panel1.add(save);
        panel2.add(cut);
        panel2.add(copy);
        panel2.add(paste);

        open.addActionListener(this);
        save.addActionListener(this);
    }

    public static void main(String[] args) {
        new SimpleEditorExample().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(open)) {
            openFile();
        } else if (e.getSource().equals(save)) {
            saveFIle();
        }
    }

    private void openFile() {
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
                setTitle(file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFIle() {
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(chooser.getSelectedFile()));) {
                writer.write(textArea.getText());
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
