import java.util.HashMap;

public class Cache {
	double width;
	double height;
	double height2;
	double width2;
	double width4;
	double heightMax;
	HashMap<String, Double> interpolation;

	Cache(double ratio, double width, double height, double speed, double amplitude) {
	    this.width = ratio * width;
	    this.height = ratio * height;
	    this.height2 = height / 2;
	    this.width2 = width / 2;
	    this.width4 = width / 4;
	    this.heightMax = this.height2 - 4;
	    this.interpolation = new HashMap<String, Double>();
	    interpolation.put("speed", speed);
	    interpolation.put("amplitude", amplitude);
	}
}