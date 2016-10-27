import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class SiriWaveCurve extends Polygon {
	// CacheObject[] globAttenuationEquationCache;
	SiriWave controller;
	Definition definition;

	// public class CacheObject {
	// 	double val;

	// 	CacheObject(double val) { this.val = val; }
	// }

	SiriWaveCurve(SiriWave controller, Definition definition) {
		super();
		this.controller = controller;
		this.definition = definition;

		addPoints();
	}

	public double globAttenuationEquation(double x) {
		// int intX = (int) Math.round(x);
		// if (globAttenuationEquationCache[intX] == null) 
		// 	globAttenuationEquationCache[intX] = new CacheObject(Math.pow(4 / (4 + Math.pow(x, 4)), 4));
		// return globAttenuationEquationCache[intX].val;
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