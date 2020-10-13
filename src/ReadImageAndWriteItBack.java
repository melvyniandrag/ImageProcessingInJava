import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class ReadImageAndWriteItBack{
	public static void main(String[] args) throws IOException {
		System.out.println("Reading image file . . .");
		BufferedImage input = ImageIO.read(ReadImageAndWriteItBack.class.getResource("resources/greenman.png"));
		
		if(input.getAlphaRaster() == null) {
			System.err.println("[Unsupported Image Format] Image doesn't have an alpha channel. Exiting.");
			return;
		}
		
		System.out.println("Turning image file into a 2d int array . . .");
		int[][] imageAsArray = turnInto2DArray(input);
		
		System.out.println("Writing array to a new image file . . .");
		writeImage(imageAsArray);
		
		System.out.println("Done!");
	}
	
	private static int[][] turnInto2DArray(BufferedImage image){
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
	 * Simple little function to turn a 2D array of pixel values 
	 * back into a .png image file.
	 * 
	 * @param pixelArray - a 2D array containing the pixel data for an image file.
	 */
	public static void writeImage(int[][] pixelArray) {
		String path = "output.png";
		
		BufferedImage image = new BufferedImage(pixelArray[0].length, pixelArray.length, BufferedImage.TYPE_INT_ARGB);
		
		for (int row = 0; row < pixelArray.length; row++) {
			for (int col = 0; col < pixelArray[row].length; col++) {
				image.setRGB(col, row , pixelArray[row][col]);
			}
		}
		
		File ImageFile = new File(path);
		try {
			ImageIO.write(image, "png", ImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
