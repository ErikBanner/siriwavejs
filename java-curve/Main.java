import javax.swing.JFrame;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Robot;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Siri Wave");
        final Opt opt = new Opt(500, 400);
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
                if (runCount % 15000000 == 0) {
                    view.repaint();
                }
            }
        }

    }

}