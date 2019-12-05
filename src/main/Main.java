package main;

import java.io.IOException;

import javax.swing.*;

import controller.BlurController;
import controller.DitherController;
import controller.GreyScaleController;
import controller.MosaicController;
import controller.SepicaToneController;
import controller.SharpenController;
import model.FileUtils;
import model.Image;
import view.SwingFeaturesFrame;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) return;
    String script = args[0];
    if (script.equals("-interactive")) {
      SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
      SwingFeaturesFrame frame = new SwingFeaturesFrame();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      try {
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (UnsupportedLookAndFeelException e) {
        // handle exception
      } catch (ClassNotFoundException e) {
        // handle exception
      } catch (InstantiationException e) {
        // handle exception
      } catch (IllegalAccessException e) {
        // handle exception
      } catch (Exception e) {
      }
    } else {
      String path = args[1];
      Image img = (Image) FileUtils.load(path);
      String output = "";
      if (script.equals("-blur")) {
        BlurController controller = new BlurController();
        img = (Image) controller.blur(img);
        output = "blured";
      } else if (script.equals("-grey")) {
        GreyScaleController controller = new GreyScaleController();
        img = (Image) controller.greyScale(img);
        output = "greyed";
      } else if (script.equals("-sepicaTone")) {
        SepicaToneController controller = new SepicaToneController();
        img = (Image) controller.sepicaTone(img);
        output = "sepicaToned";
      } else if (script.equals("-sharpen")) {
        SharpenController controller = new SharpenController();
        img = (Image) controller.sharpen(img);
        output = "sharpened";
      } else if (script.equals("-dither")) {
        DitherController controller = new DitherController();
        img = (Image) controller.dither(img);
        output = "dithered";
      } else if (script.equals("-mosaic")) {
        MosaicController controller = new MosaicController();
        img = (Image) controller.mosaic(img, 1000);
        output = "mosaiced";
      }
      try {
        FileUtils.save(img, output + ".jpg");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
