import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Siri Wave");
        final Opt opt = new Opt(600, 600);
        final SiriWave siriWave = new SiriWave(opt);
        frame.getContentPane().add(siriWave);
        final Ticker t = new Ticker(siriWave);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                t.done();
                frame.dispose();
            }
        });
        t.start();

        frame.setLocation(new Point(640, 0));
        frame.pack();
        frame.setVisible(true);
    }

    private static class Ticker extends Thread {

        private final Robot robot;

        public boolean update = true;
        private final SiriWave view;

        public Ticker(SiriWave siriWave) {
            view = siriWave;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }

        public void done() {
            update = false;
        }

        public void run() {
            int runCount = 0;
            while (update) {
                runCount++;
                if (runCount % 70000000 == 0) {
                    // System.out.println("Ran ticker " + runCount + " times");
                    Random random = new Random();
                    double randomScale = random.nextDouble();
                    view.setAmplitude(randomScale);
                    view.repaint();
                }
            }
        }

    }

}