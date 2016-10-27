import java.awt.Polygon;

public class SiriWaveCurve extends Polygon {
	SiriWave controller;
	Definition definition;

	SiriWaveCurve(SiriWave controller, Definition definition) {
		super();
		this.controller = controller;
		this.definition = definition;

		addPoints();
	}

	public double globAttenuationEquation(double x) {
		return Math.pow(4 / (4 + Math.pow(x, 4)), 4);
	}

	public double getX(double i) {
		return this.controller.cache.width2 + i * this.controller.cache.width4;
	}

	public double getY(double i) {
		double att = (this.controller.cache.heightMax * this.controller.amplitude) / this.definition.attenuation;
		return this.controller.cache.height2 + globAttenuationEquation(i) * att * Math.sin(this.controller.frequency * i - this.controller.phase);
	}

	public void addPoints() {
		for (double i = -2.0; i <= 2.0; i += 0.01) {
			double y = getY(i);
			if (Math.abs(i) >= 1.9)  y = this.controller.cache.height2;
			addPoint((int) getX(i), (int) y);
		}
	}
}