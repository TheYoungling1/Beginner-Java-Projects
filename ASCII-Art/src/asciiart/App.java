package asciiart;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("src/asciiart/ascii-pineapple.jpg");
        System.out.println("File exists? " + file.exists());

        Game image = new Game(file);

        System.out.println("Image size: " + image.getWidth() + " x " + image.getHeight());
        //System.out.println(image.getRGB(1, 1));
        RGB[][] rgbMatrix = image.getRGBMatrix();
        int[][] brightnessMatrix = RGB.RGBToBrightness(rgbMatrix);
        //System.out.println(brightnessMatrix[1][1]);
        Game.printAscii(brightnessMatrix);
    }
}
