import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Opt {
	double width;
	double height;
	double ratio = 1;
	double amplitude = 1;
	double speed = 0.2;
	int frequency = 6;
	Color color = Color.BLACK;
	double speedInterpolationSpeed = 0.005;
	double amplitudeInterpolationSpeed = 0.05;
	Definition[] definitions = new Definition[]{
		new Definition(-2, 1, 0.1),
		new Definition(-6, 1, 0.2),
		new Definition(4, 1, 0.4),
		new Definition(2, 1, 0.6),
		new Definition(1, 1.5f, 1)
	};

	Opt(double width, 
		double height) {
		this.width = width;
		this.height = height;
	}
}