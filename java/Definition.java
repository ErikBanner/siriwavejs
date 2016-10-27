import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Definition {
	int attenuation;
	float lineWidth;
	float opacity;

	Definition(int attenuation, float lineWidth, float opacity) {
		this.attenuation = attenuation;
		this.lineWidth = lineWidth;
		this.opacity = opacity;
	}

}