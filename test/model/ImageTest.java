package model;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageTest {

  @Test
  public void blur() throws IOException {
    double[][] filter=new double[][]{{1.0/16.0,1.0/8.0,1.0/16.0},{1.0/8.0,1.0/4.0,1.0/8.0},{1.0/16.0,1.0/8.0,1.0/16.0}};
    Image image=new Image(400,400,FileUtils.getImagePixel("./src/model/timg.jpeg"));
    Image image1=(Image) image.blur(filter);
    FileUtils.output(image1.getPixels(),"./src/model/fxxk.png");
  }

  @Test
  public void sharpen() throws IOException {
    double[][] filter=new double[][]{{-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0},{-1.0/8.0,1.0/4.0,1.0/4.0,
            1.0/4.0,-1.0/8.0},{-1.0/8.0,1.0/4.0,1.0,1.0/4.0,-1.0/8.0},{-1.0/8,1.0/4.0,1.0/4.0,1.0/4.0,-1.0/8.0},
            {-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0,-1.0/8.0}};

    Image image=new Image(400,400,FileUtils.getImagePixel("./src/model/timg.jpeg"));
    Image image1=(Image) image.sharpen(filter);
    FileUtils.output(image1.getPixels(),"./src/model/fxxk.png");
  }

  @Test
  public void greyScale() throws IOException {
    double[][] filter=new double[][]{{0.2126,0.7152,0.0722},{0.2126,0.7152,0.0722},{0.2126,0.7152,0.0722}};
    Image image=new Image(400,400,FileUtils.getImagePixel("./src/model/timg.jpeg"));
    Image image1=(Image) image.greyScale(filter);
    FileUtils.output(image1.getPixels(),"./src/model/fxxk.png");
  }

  @Test
  public void sepicaTone() throws IOException {
    double[][] filter=new double[][]{{0.393,0.769,0.189},{0.349,0.686,0.168},{0.272,0.534,0.131}};
    Image image=new Image(400,400,FileUtils.getImagePixel("./src/model/timg.jpeg"));
    Image image1=(Image) image.sepicaTone(filter);
    FileUtils.output(image1.getPixels(),"./src/model/fxxk.png");
  }
}