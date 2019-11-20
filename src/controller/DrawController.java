package controller;

import model.ImageUtils;

/**
 * The controller for draw Method.
 *
 * @Author Yang Bao && YiMing Chu
 */
public class DrawController {
  /**
   * the method to draw horizontalRainbow.
   */
  public void drawHorizontalRainbow() {
    ImageUtils.drawHorizontalRainrow(700, 700);
  }

  /**
   * the method to draw vertical rainbow.
   */
  public void drawVerticalRainbow() {
    ImageUtils.drawVerticalRainbow(700, 700);
  }

  /**
   * the method to draw checkedboard.
   */
  public void drawCheckBoard() {
    ImageUtils.drawCheckBoard(800, 800);
  }
}
