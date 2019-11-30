package view;

import javax.swing.*;

/**
 * This example shows the different user interface elements in Java Swing.
 * Please use these examples as guidelines only to see how to use them. This
 * example has not been designed very well, it is only meant to illustrate code
 * snippets.
 *
 * Feel free to try out different modifications to see how the program changes
 */

public class SwingFeatures {
  /**
   * main method for the SwingFeature.
   * @param args arguments.
   */
  public static void main(String[] args) {
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

  }

}
