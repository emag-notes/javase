package org.emamotor.javase.utility.collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author emag
 */
public class HashtableConsole extends JFrame implements ActionListener {

    private JButton[] _radiohead = {
            new JButton("Thom"),
            new JButton("Johnny"),
            new JButton("Edward"),
            new JButton("Colin"),
            new JButton("Phil"),
    };

    private JLabel _jlabel;

    private Hashtable _hashtable = new Hashtable();

    private JButton _display;
    private JButton _initialize;

    private Vector _vector = new Vector();

    public HashtableConsole() {
        super("Hashtable console");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        _hashtable.put("Thom",   "Yorke");
        _hashtable.put("Johnny", "Greenwood");
        _hashtable.put("Edward", "O'Brien");
        _hashtable.put("Colin",  "Greenwood");
        _hashtable.put("Phil",   "Selway");

        Color backColor[] =
                { Color.green, Color.blue, Color.black, Color.yellow, Color.red };

        Dimension size = new Dimension(150, 50);

        for ( int i = 0; i < _radiohead.length; i++ ) {
            _radiohead[i].setBackground(backColor[i]);
            _radiohead[i].setForeground(Color.black);
            _radiohead[i].setMinimumSize(size);
            _radiohead[i].setPreferredSize(size);
            _radiohead[i].setMaximumSize(size);
            _radiohead[i].addActionListener(this);
        }

        Box topBox = new Box(BoxLayout.X_AXIS);
        getContentPane().add(topBox, BorderLayout.NORTH);

        topBox.add(Box.createHorizontalStrut(80));
        topBox.add(_radiohead[0]);
        topBox.add(Box.createHorizontalGlue());
        topBox.add(_radiohead[1]);
        topBox.add(Box.createHorizontalStrut(80));

        Box centerBox = new Box(BoxLayout.X_AXIS);
        getContentPane().add(centerBox, BorderLayout.CENTER);

        centerBox.add(Box.createHorizontalStrut(5));
        centerBox.add(_radiohead[2]);
        centerBox.add(Box.createHorizontalGlue());
        centerBox.add(_radiohead[3]);
        centerBox.add(Box.createHorizontalGlue());
        centerBox.add(_radiohead[4]);
        centerBox.add(Box.createHorizontalStrut(5));

        _jlabel = new JLabel("Radiohead");
        _jlabel.setHorizontalAlignment(SwingConstants.CENTER);
        _jlabel.setPreferredSize(new Dimension(460, 50));
        getContentPane().add(_jlabel, BorderLayout.SOUTH);

        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String key = ((JButton) e.getSource()).getText();
        _jlabel.setText((String) _hashtable.get(key));

    }

    public static void main(String... args) {
        new HashtableConsole().setVisible(true);
    }

}
