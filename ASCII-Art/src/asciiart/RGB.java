package asciiart;

public class RGB {
    private int red;
    private int blue;
    private int green;

    public RGB(int argb) {
        red   = getRed(argb);
        green = getGreen(argb);
        blue  = getBlue(argb); 
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private int getRed(int argb) {
        return (argb >> 16) & 0xFF;
    }

    public int getRed() {
        return this.red;
    }

    private int getGreen(int argb) {
        return (argb >> 8) & 0xFF;
    }

    public int getGreen() {
        return this.green;
    }

    private int getBlue(int argb) {
        return (argb) & 0xFF;
    }

    public int getBlue() {
        return this.blue;
    }

    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")";
    }
    
    public static int[][] RGBToBrightness(RGB[][] rgb) {
        int[][] brightness = new int[467][700];
        for (int i = 0; i < rgb.length; i++) {
            for (int j = 0; j < rgb[i].length; j++) {
                RGB current = rgb[i][j];
                brightness[i][j] = (current.getBlue() + current.getGreen() + current.getRed()) / 3;
            }
        }

        return brightness;
    }

}
