package org.emamotor.javase.utility.collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.Vector;

/**
 * @author emag
 */
public class StackConsole extends JFrame implements ActionListener {

    private JButton[] _continent = {
            new JButton("Eurasia"),
            new JButton("North America"),
            new JButton("Africa"),
            new JButton("Australia"),
            new JButton("South America"),
    };
    private JButton _display;
    private JButton _initialize;

    private Stack _stack = new Stack();

    public StackConsole() {
        super("Stack console");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Color[] backColor = { Color.green, Color.blue,  Color.black, Color.yellow, Color.red };

        Dimension size = new Dimension(150, 50);

        for ( int i = 0; i < _continent.length; i++ ) {
            _continent[i].setBackground(backColor[i]);
            _continent[i].setForeground(Color.black);
            _continent[i].setMinimumSize(size);
            _continent[i].setPreferredSize(size);
            _continent[i].setMaximumSize(size);
            _continent[i].addActionListener(this);
        }

        Box topBox = new Box(BoxLayout.X_AXIS);
        getContentPane().add(topBox, BorderLayout.NORTH);

        topBox.add(Box.createHorizontalStrut(80));
        topBox.add(_continent[0]);
        topBox.add(Box.createHorizontalGlue());
        topBox.add(_continent[1]);
        topBox.add(Box.createHorizontalStrut(80));

        Box centerBox = new Box(BoxLayout.X_AXIS);
        getContentPane().add(centerBox, BorderLayout.CENTER);

        centerBox.add(Box.createHorizontalStrut(5));
        centerBox.add(_continent[2]);
        centerBox.add(Box.createHorizontalGlue());
        centerBox.add(_continent[3]);
        centerBox.add(Box.createHorizontalGlue());
        centerBox.add(_continent[4]);
        centerBox.add(Box.createHorizontalStrut(5));

        _display = new JButton("Display");
        _display.addActionListener(this);

        _initialize = new JButton("Initialize");
        _initialize.addActionListener(this);

        Box bottomBox = new Box(BoxLayout.X_AXIS);
        getContentPane().add(bottomBox, BorderLayout.SOUTH);

        bottomBox.add(Box.createHorizontalStrut(60));
        bottomBox.add(_display);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(_initialize);
        bottomBox.add(Box.createHorizontalStrut(60));

        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // When "Display" is pushed, display texts.
        if ( e.getSource().equals(_display) ) {
            for ( int i = 0, size = _stack.size(); i < size; i++ ) {
                JButton button = (JButton) _stack.pop();
                System.out.println(button.getText());
            }
        }

        // When "Initialize" is pushed, all texts are removed.
        else if ( e.getSource().equals(_initialize)) {
            _stack.clear();
        }

        // 5 texts is stored into _vector in order of being pushed.
        else {
            for ( int i = 0; i < _continent.length; i++) {
                if ( e.getSource().equals(_continent[i]) ) {
                    _stack.push(_continent[i]);
                    break;
                }
            }
        }
    }

    public static void main(String... args) {
        new StackConsole().setVisible(true);
    }

}
