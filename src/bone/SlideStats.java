package bone;

public class SlideStats {

	public static final int[] PositionDistances =
		{	00, /* Zeroth - Ignore */
			34, /* First */
			43, /* Second */
			52, /* Third */
			31, /* Fourth */
			74, /* Fifth */
			82, /* Sixth */
			95  /* Seventh */
		};
	
	/** Returns slide position number nearest current centimeter distance */
	public static int getNearestSlidePosition(int centimeters) {
		int minimumMiss = 200;
		int nearestSlidePos = 1;
		for (int i = 0; i < 8; i++) {
			int miss = getPositionDistance(i, centimeters);
			if (miss < minimumMiss) {
				minimumMiss = miss;
				nearestSlidePos = i;
			}
		}
		return nearestSlidePos;
	}
	
	/** Returns difference between the real distance of slidePosition and centimeters */
	public static int getPositionDistance(int slidePosition, int centimeters) {
		return Math.abs(PositionDistances[slidePosition] - centimeters);
	}
	
	public static int getFirstOffset(int centimeters) {
		int cur = getPositionDistance(1, centimeters);
		if (cur < 2) cur = 0;
		return cur;
	}
}
