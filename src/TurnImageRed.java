import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TurnImageRed{
	/**
	 * Read a png image into a BufferedImage.
	 * If the image is missing an alpha channel, return an error. We want ARGB, not RGB.
	 * Turn the BufferedImage into an int[][] of pixel values.
	 * Convert every pixel in the int[][] into a red pixel.
	 * Write the int[][] out to a new png image.
	 */
	public static void main(String[] args){
		BufferedImage input = null;
		
		try {
			input = ImageIO.read(TurnImageRed.class.getResource("resources/greenman.png"));
		} catch ( IOException e) {
			System.err.println(e.getMessage());
			return;
		}
		
		if(input.getAlphaRaster() == null) {
			System.err.println("[Unsupported Image Format] Image doesn't have an alpha channel. Exiting.");
			return;
		}
		int[][] imageAsArray = UtilityFunctions.turnInto2DArray(input);
		
		turnAllPixelsRed(imageAsArray);
		
		UtilityFunctions.writeImage(imageAsArray, "output/the_whole_thing_is_red.png");
	}
	
	/**
	 * Little function that takes a 2D array of ARGB pixel data and turns all the pixels red.
	 * 
	 * @param image
	 * @return void - the input is modified in place
	 */
	private static void turnAllPixelsRed(int[][] inputArray){
	
		for(int row = 0; row < inputArray.length; row++) {
			for(int col = 0; col < inputArray[row].length; col++) {
				inputArray[row][col] = 0xffff0000;
			}
		}
	}

	
}
