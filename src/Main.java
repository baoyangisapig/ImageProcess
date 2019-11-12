import java.io.IOException;

import controller.DrawController;
import controller.ImageController;

/**
 * This is the main method for testing the controllers and all operations included.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    ImageController c1=new ImageController();
    double[][] filter=new double[][]{{1.0/16.0,1.0/8.0,1.0/16.0},{1.0/8.0,1.0/4.0,1.0/8.0},{1.0/16.0,1.0/8.0,1.0/16.0}};
    c1.blur(filter,"./res/panda.jpg","./res/panda_blur.jpg");

    double[][] filter1=new double[][]{{-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0},{-1.0/8.0,1.0/4.0,1.0/4.0,
            1.0/4.0,-1.0/8.0},{-1.0/8.0,1.0/4.0,1.0,1.0/4.0,-1.0/8.0},{-1.0/8,1.0/4.0,1.0/4.0,1.0/4.0,-1.0/8.0},
            {-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0}};
    c1.sharpen(filter1,"./res/panda.jpg","./res/panda_sharpen.jpg");

    double[][] filter2=new double[][]{{0.2126,0.7152,0.0722},{0.2126,0.7152,0.0722},{0.2126,0.7152,0.0722}};
    c1.greyScale(filter2,"./res/panda.jpg","./res/panda_greyScale.jpg");

    double[][] filter3=new double[][]{{0.393,0.769,0.189},{0.349,0.686,0.168},{0.272,0.534,0.131}};
    c1.sepicaTone(filter3,"./res/panda.jpg","./res/panda_sepicaTone.jpg");

    new DrawController().drawCheckBoard();
    new DrawController().drawHorizontalRainrow();
    new DrawController().drawVerticalRainbow();
  }
}
