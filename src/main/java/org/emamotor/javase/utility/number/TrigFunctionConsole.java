package org.emamotor.javase.utility.number;

import javax.swing.*;
import java.awt.*;

/**
 * @author emag
 */
public class TrigFunctionConsole extends JFrame {

    public TrigFunctionConsole(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new DrawGraph(), BorderLayout.CENTER);
        pack();
    }

    public static void main(String... args) {

        TrigFunctionConsole frame = new TrigFunctionConsole("Trig Function Console");
        frame.setSize(500, 300);
        frame.setVisible(true);
        
    }

    class DrawGraph extends JComponent {

        // point of origin
        private int X0 =  30;
        private int Y0 = 150;

        @Override
        public void paint(Graphics g) {

            Dimension dimension = getSize();
            g.setColor(Color.black);
            g.fillRect(0, 0, dimension.width, dimension.height);
            g.setColor(Color.WHITE);
            g.setFont(new Font(null, Font.ITALIC, 13));

            drawAxis(g);

            drawFunction(g, new DegreeToCosFunction(), 0, 360, Color.RED);
            drawFunction(g, new DegreeToSinFunction(), 0 ,360, Color.PINK);
        }

        private void drawAxis(Graphics g) {

            // x-axis
            g.drawLine(this.X0, this.Y0, 480, this.Y0);
            g.drawString("x", 420, 165);

            // y-axis
            g.drawLine(this.X0, 260, this.X0, 10);
            g.drawString("y", 10, 10);

        }

        private void drawFunction(Graphics g, Function function, int x_min, int x_max, Color color) {

            g.setColor(color);

            int[] x = new int[x_max - x_min + 1];
            int[] y = new int[x_max - x_min + 1];

            for (int n = x_min; n <= x_max; n++) {
                x[n] = this.X0 + n;
                y[n] = this.Y0 - (int) (function.f(n) * 100);
            }

            g.drawPolyline(x, y, 360);
            g.drawString(function.getName(), x[x.length-1] + 10, y[y.length -1]);

        }

    }
}

interface Function {
    String getName();
    double f(double x);
}

class DegreeToCosFunction implements Function {

    @Override
    public String getName() {
        return "cos";
    }

    @Override
    public double f(double x) {
        return Math.cos(Math.toRadians(x));
    }
}

class DegreeToSinFunction implements Function {

    @Override
    public String getName() {
        return "sin";
    }

    @Override
    public double f(double x) {
        return Math.sin(Math.toRadians(x));
    }
}
