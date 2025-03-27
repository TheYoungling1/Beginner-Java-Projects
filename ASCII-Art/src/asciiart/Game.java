package asciiart;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.*;

public class Game {
    private BufferedImage image;
    private static String asciiChars = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

    public Game(File file) {
        try {
            image = ImageIO.read(file);
            
        } catch (IOException e){
            System.out.println("File open error");
        }
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public RGB getRGB(int pixelX, int pixelY) {
        int argb = image.getRGB(pixelX, pixelY);     
        //System.out.println(argb);
        //System.out.println(Integer.toHexString(argb));
        //System.out.println(new RGB(argb));
        return new RGB(argb);
    }

    public RGB[][] getRGBMatrix() {
        RGB[][] matrix = new RGB[getHeight()][getWidth()]; // rows x cols (y x x)
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                matrix[y][x] = getRGB(x, y);
            }
        }
        return matrix;
    }
    

    private static char brightnessToChar(int brightness) {
        int index = (int) ((1.0 - brightness / 255.0) * (asciiChars.length() - 1));
        return asciiChars.charAt(index);
    }

    public static void printAscii(int[][] brightnessMatrix) {
        for (int i = 0; i < brightnessMatrix.length; i++) {
            for (int j = 0; j < brightnessMatrix[i].length; j++) {
                System.out.print(Game.brightnessToChar(brightnessMatrix[i][j]));
                //System.out.print(Game.brightnessToChar(brightnessMatrix[i][j]));
                //System.out.print(Game.brightnessToChar(brightnessMatrix[i][j]));
            }
            System.out.println();
        }
    }
}
