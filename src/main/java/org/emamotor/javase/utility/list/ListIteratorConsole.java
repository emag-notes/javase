package org.emamotor.javase.utility.list;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author emag
 */
public class ListIteratorConsole extends JFrame implements ActionListener {

    private String _player = "";
    private JLabel _jLabel;

    private JButton[] jButtons = { new JButton("prev"), new JButton("next") };

    private ListIterator _listIterator;

    public ListIteratorConsole() {
        super("ListIterator console");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String players[] =
                {
                        "Player 01",
                        "Player 02",
                        "Player 03",
                        "Player 04",
                        "Player 05",
                        "Player 06",
                        "Player 07",
                        "Player 08",
                        "Player 09",
                        "Player 10",
                };

        List japan = Arrays.asList(players);

        _listIterator = japan.listIterator();

        Box box = new Box(BoxLayout.Y_AXIS);
        Dimension size = new Dimension(100, 20);

        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i].setMinimumSize(size);
            jButtons[i].setPreferredSize(size);
            jButtons[i].setMaximumSize(size);
            jButtons[i].setBackground(Color.black);
            jButtons[i].setForeground(Color.black);
            jButtons[i].addActionListener(this);
            box.add(jButtons[i]);
        }

        _jLabel = new JLabel("Players");
        _jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        _jLabel.setForeground(Color.black);
        _jLabel.setPreferredSize(new Dimension(150, 40));

        getContentPane().add(box, BorderLayout.WEST);
        getContentPane().add(_jLabel, BorderLayout.CENTER);

        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(jButtons[0])) {
            if (_listIterator.hasPrevious()) {
                _player = _listIterator.previous().toString();
                _jLabel.setText(_player);
            } else {
                _jLabel.setText("No prev player");
            }
        } else if (e.getSource().equals(jButtons[1])) {
            if (_listIterator.hasNext()) {
                _player = _listIterator.next().toString();
                _jLabel.setText(_player);
            } else {
                _jLabel.setText("No next player");
            }
        }
    }

    public static void main(String... args) {
        new ListIteratorConsole().setVisible(true);
    }

}
