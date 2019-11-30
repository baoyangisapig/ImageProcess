package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import controller.BlurController;
import controller.DitherController;
import controller.GreyScaleController;
import controller.MosaicController;
import controller.SepicaToneController;
import controller.SharpenController;
import model.FileUtils;
import model.Image;
import model.ImageUtils;

/**
 * This class opens the main window, that has different elements illustrated in it. It also doubles
 * up as all the listeners for simplicity. Such a design is not recommended in general.
 */

public class SwingFeaturesFrame extends JFrame implements ActionListener, ItemListener, ListSelectionListener {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;

  JLabel imageLabel;

  private String originPath;
  private Image image;
  private Image processedImage;

  /**
   * Swing Frame for the operations.
   */
  public SwingFeaturesFrame() {
    super();
    setTitle("Swing features");
    setSize(400, 400);


    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);
    imageLabel = new JLabel();
    JScrollPane pane = new JScrollPane(imageLabel);

    pane.setPreferredSize(new Dimension(400, 400));
    imagePanel.add(pane);


    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);


    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    //file blur
    JPanel blurPanel = new JPanel();
    blurPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blurPanel);
    JButton blurButton = new JButton("Blur the image");
    blurButton.setActionCommand("blur file");
    blurButton.addActionListener(this);
    blurPanel.add(blurButton);
    //file sharpen

    JPanel Panel1 = new JPanel();
    Panel1.setLayout(new FlowLayout());
    dialogBoxesPanel.add(Panel1);
    JButton Button1 = new JButton("Sharpen the image");
    Button1.setActionCommand("sharpen file");
    Button1.addActionListener(this);
    Panel1.add(Button1);
    //file sepia
    JPanel Panel2 = new JPanel();
    Panel2.setLayout(new FlowLayout());
    dialogBoxesPanel.add(Panel2);
    JButton Button2 = new JButton("Sepia the image");
    Button2.setActionCommand("sepia file");
    Button2.addActionListener(this);
    Panel2.add(Button2);

    //file greyscale
    JPanel Panel3 = new JPanel();
    Panel2.setLayout(new FlowLayout());
    dialogBoxesPanel.add(Panel3);
    JButton Button3 = new JButton("greyscale the image");
    Button3.setActionCommand("greyscale file");
    Button3.addActionListener(this);
    Panel3.add(Button3);

    //mosaics
    JPanel Panel4 = new JPanel();
    Panel4.setLayout(new FlowLayout());
    dialogBoxesPanel.add(Panel4);
    JButton Button4 = new JButton("mosaic the image");
    Button4.setActionCommand("mosaic file");
    Button4.addActionListener(this);
    Panel4.add(Button4);

    //dither
    JPanel Panel5 = new JPanel();
    Panel5.setLayout(new FlowLayout());
    dialogBoxesPanel.add(Panel5);
    JButton Button5 = new JButton("dither file");
    Button5.setActionCommand("dither file");
    Button5.addActionListener(this);
    Panel5.add(Button5);

    //generate
    JPanel Panel6 = new JPanel();
    Panel6.setLayout(new FlowLayout());
    dialogBoxesPanel.add(Panel6);
    JButton Button6 = new JButton("generate file");
    Button6.setActionCommand("generate file");
    Button6.addActionListener(this);
    Panel6.add(Button6);
  }

  /**
   * operations beased on the item selected.
   * @param arg0 the item selected.
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
      case "Open file": {

        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          originPath = f.getAbsolutePath();
          image = (model.Image) FileUtils.load(originPath);
          imageLabel.setIcon(new ImageIcon(originPath));
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String[] strs = f.getAbsolutePath().split("\\\\");
          int len = strs.length;
          String path = "./res/" + strs[len - 1];
          try {
            FileUtils.save(processedImage, path);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      break;
      case "blur file": {
        BlurController blurController = new BlurController();
        processedImage = (model.Image) blurController.blur(image);
        String[] strs = originPath.split("\\\\");
        int len = strs.length;
        String path = "./res/temp/" + strs[len - 1].split("\\.")[0] + "blur.jpg";
        try {
          FileUtils.save(processedImage, path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(path));
      }
      break;

      case "sharpen file": {
        System.out.println("sharpen");
        SharpenController sharpenController = new SharpenController();
        processedImage = (model.Image) sharpenController.sharpen(image);
        String[] strs = originPath.split("\\\\");
        int len = strs.length;
        String path = "./res/temp/" + strs[len - 1].split("\\.")[0] + "sharpen.jpg";
        try {
          FileUtils.save(processedImage, path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(path));
      }
      break;

      case "sepia file": {
        System.out.println("sepia");
        SepicaToneController sepicaToneController = new SepicaToneController();
        processedImage = (model.Image) sepicaToneController.sepicaTone(image);
        String[] strs = originPath.split("\\\\");
        int len = strs.length;
        String path = "./res/temp/" + strs[len - 1].split("\\.")[0] + "sepia.jpg";
        try {
          FileUtils.save(processedImage, path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(path));
      }
      break;

      case "greyscale file": {
        System.out.println("greyscale");
        GreyScaleController greyScaleController = new GreyScaleController();
        processedImage = (model.Image) greyScaleController.greyScale(image);
        String[] strs = originPath.split("\\\\");
        int len = strs.length;
        String path = "./res/temp/" + strs[len - 1].split("\\.")[0] + "greyscale.jpg";
        try {
          FileUtils.save(processedImage, path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(path));
      }
      break;

      case "mosaic file": {
        System.out.println("mosaic");
        MosaicController mosaicController = new MosaicController();
        processedImage = (model.Image) mosaicController.mosaic(image, 1000);
        String[] strs = originPath.split("\\\\");
        int len = strs.length;
        String path = "./res/temp/" + strs[len - 1].split("\\.")[0] + "mosaic.jpg";
        try {
          FileUtils.save(processedImage, path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(path));
      }
      break;

      case "generate file": {
        System.out.println("generate");
        ImageUtils.drawCheckBoard(800, 800);
        imageLabel.setIcon(new ImageIcon("./res/check_board.png"));
      }
      break;

      case "dither file": {
        System.out.println("dither");
        DitherController ditherController = new DitherController();
        processedImage = (model.Image) ditherController.dither(image);
        String[] strs = originPath.split("\\\\");
        int len = strs.length;
        String path = "./res/temp/" + strs[len - 1].split("\\.")[0] + "dither.jpg";
        try {
          FileUtils.save(processedImage, path);
        } catch (IOException e) {
          e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(path));
      }
      break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
  }
}
