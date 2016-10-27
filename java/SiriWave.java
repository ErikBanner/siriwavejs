import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class SiriWave extends JPanel {
	double phase = 0;
	boolean run = false;
	Cache cache;
	double width;
	double height;
	double ratio;
	double amplitude;
	double speed;
	int frequency;
	double speedInterpolationSpeed;
	double amplitudeInterpolationSpeed;
	SiriWaveCurve[] curves;
	Definition[] definitions;

	SiriWave(Opt opt) {
		this.width = opt.width;
		this.height = opt.height;
		this.ratio = opt.ratio;
		this.amplitude = opt.amplitude;
		this.speed = opt.speed;
		this.frequency = opt.frequency;
		this.speedInterpolationSpeed = opt.speedInterpolationSpeed;
		this.amplitudeInterpolationSpeed = opt.amplitudeInterpolationSpeed;
		this.cache = new Cache(opt.ratio, opt.width, opt.height, this.speed, this.amplitude);
		this.curves = new SiriWaveCurve[opt.definitions.length];
		this.definitions = opt.definitions;

		setSize(new Dimension((int) this.cache.width, (int) this.cache.height));
		setMinimumSize(new Dimension((int) this.cache.width, (int) this.cache.height));
		setPreferredSize(new Dimension((int) this.cache.width, (int) this.cache.height));
		setOpaque(true);

		addSiriWaveCurves();
	}

	private void addSiriWaveCurves() {
		for (int i=0; i<curves.length; i++) {
			curves[i] = new SiriWaveCurve(this, definitions[i]);
		}
	}

	private void interpolate(String str) {
		double increment = str.equals("speed") ? speedInterpolationSpeed : amplitudeInterpolationSpeed;
		double speedOrAmplitude = str.equals("speed") ? this.speed : this.amplitude;
		
		if (Math.abs(this.cache.interpolation.get(str) - speedOrAmplitude) <= increment) {
			if (str.equals("speed")) this.speed = this.cache.interpolation.get("speed");
			else this.amplitude = this.cache.interpolation.get("amplitude");
		} else {
			if (this.cache.interpolation.get(str) > speedOrAmplitude) {
				if (str.equals("speed")) this.speed += increment;
				else this.amplitude += increment;
			} else {
				if (str.equals("speed")) this.speed -= increment;
				else this.amplitude -= increment;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;

	    g2.setRenderingHint(
	        RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	    interpolate("speed");
	    interpolate("amplitude");

	    addSiriWaveCurves();
	    for (int i=0; i<this.curves.length; i++) {
	    	BasicStroke stroke = new BasicStroke(definitions[i].lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	    	g2.setStroke(stroke);
	    	g2.setColor(new Color(0.0f, 0.0f, 0.0f, definitions[i].opacity));	    	
	    	g2.drawPolyline(curves[i].xpoints, curves[i].ypoints, curves[i].npoints);
	    }

	    this.phase = (this.phase + Math.PI * this.speed) % (2 * Math.PI);
	}

	public void setSpeed(double v) {
		this.cache.interpolation.put("speed", v);
	}

	public void setAmplitude(double v) {
		this.cache.interpolation.put("amplitude", Math.max(Math.min(v, 1), 0));
	}
}