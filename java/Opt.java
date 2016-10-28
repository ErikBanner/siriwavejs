import java.awt.Color;

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
		new Definition(-2, 1.0f, 0.1f),
		new Definition(-6, 1.0f, 0.2f),
		new Definition(4, 1.0f, 0.4f),
		new Definition(2, 1.0f, 0.6f),
		new Definition(1, 1.5f, 1.0f)
	};

	Opt(double width, 
		double height) {
		this.width = width;
		this.height = height;
	}
}