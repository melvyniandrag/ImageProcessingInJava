import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UtilityFunctions {
	/**
	 * Read the ARGB pixel values out of a BufferedImage and 
	 * store the values in an int[][]
	 * 
	 * @param image - a Buffered image
	 * @return an int[][] containing the ARGB data from the image input
	 */
	public static int[][] turnInto2DArray(BufferedImage image){
		final int width = image.getWidth();
		final int height = image.getHeight();
		int[][] result = new int[height][width];

		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				result[row][col] = image.getRGB(col, row);
			}
		}
		return result;
	}
	
	
	/**
	 * Write a 2D array of ARGB pixel values to a .png image file.
	 * 
	 * @param pixelArray - a 2D array containing the pixel data for an image file.
	 * @return void
	 */
	public static void writeImage(int[][] pixelArray, String outputFileName) {
		BufferedImage image = new BufferedImage(pixelArray[0].length, pixelArray.length, BufferedImage.TYPE_INT_ARGB);
		for (int row = 0; row < pixelArray.length; row++) {
			for (int col = 0; col < pixelArray[row].length; col++) {
				image.setRGB(col, row , pixelArray[row][col]);
			}
		}
		File ImageFile = new File(outputFileName);
		try {
			ImageIO.write(image, "png", ImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
