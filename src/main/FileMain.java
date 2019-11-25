package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import controller.BlurController;
import controller.GreyScaleController;
import controller.SepicaToneController;
import controller.SharpenController;
import model.FileUtils;
import model.Image;
import model.Pixel;

/**
 * Main method to manipulate the file.
 */
public class FileMain {

  private static Pixel[][] pixels;
  private static Image img = null;

  public static void main(String[] args) throws IOException {
    File file = new File("res/script.txt");
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    String str = null;
    while ((str = bufferedReader.readLine()) != null) {
      String[] arr = str.trim().split(" ");
      if (arr[0].equals("load")) {
        img = (Image) FileUtils.load("res/" + arr[1]);
      } else if (arr[0].equals("blur")) {
        BlurController controller = new BlurController();
        img = (Image) controller.blur(img);
      } else if (arr[0].equals("grey")) {
        GreyScaleController controller = new GreyScaleController();
        img = (Image) controller.greyScale(img);
      } else if (arr[0].equals("sepicaTone")) {
        SepicaToneController controller = new SepicaToneController();
        img = (Image) controller.sepicaTone(img);
      } else if (arr[0].equals("sharpen")) {
        SharpenController controller = new SharpenController();
        img = (Image) controller.sharpen(img);
      } else if (arr[0].equals("save")) {
        FileUtils.save(img, "res/" + arr[1]);
      }
    }
    bufferedReader.close();
  }
}
