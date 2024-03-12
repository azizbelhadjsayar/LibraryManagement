/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Barcode;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import java.awt.Dimension; 
import java.awt.EventQueue; 
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import org.opencv.core.Core; 
import org.opencv.core.Mat; 
import org.opencv.core.MatOfByte; 
import org.opencv.imgcodecs.Imgcodecs; 
import org.opencv.videoio.VideoCapture; 

/**
 *
 * @author LENOVO
 */
public class Barcode_Scanner extends JFrame{
    private JLabel cameraScreen;
    private VideoCapture capture;
    private Mat image;
    private boolean shouldStop = false; // Variable de contrôle pour arrêter le thread
    private String barcodeResult; // Variable pour stocker le résultat du scan
    
    public Barcode_Scanner() {
        setLayout(null);
        
        cameraScreen = new JLabel();
        cameraScreen.setBounds(0, 0, 640, 480);
        add(cameraScreen);
        
        setSize(new Dimension(640,560));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public String getBarcodeResult() {
        return barcodeResult;
    }
    
    public void startCamera() {
        capture = new VideoCapture(0);
        image = new Mat();
        byte[] imageData;
        
        ImageIcon icon;
        while(!shouldStop) {
            capture.read(image);
            final MatOfByte buf = new MatOfByte();
            Imgcodecs.imencode(".jpg", image, buf);
            imageData = buf.toArray();
            icon = new ImageIcon(imageData);
            cameraScreen.setIcon(icon);
            scanBarcode(imageData);
            /*
            if(clicked ) {
                try {
                    BufferedImage bufferedImage = createBufferedImage(imageData);
                    BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
                    BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
                    MultiFormatReader reader = new MultiFormatReader();
                    Result result = reader.decode(bitmap);

                    // Show barcode content using JOptionPane
                    JOptionPane.showMessageDialog(null, "Barcode: " + result.getText());
                } catch (Exception e) {
                    // Handle exception if barcode is not found or an error occurs
                    e.printStackTrace();
                } finally {
                    clicked = false;
                }
            }
            */
        }
    }
    
    private BufferedImage createBufferedImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return ImageIO.read(bis);
    }
    
    private void scanBarcode (byte[] img) {
        try {
            BufferedImage bufferedImage = createBufferedImage(img);
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);

            // Show barcode content using JOptionPane
            //JOptionPane.showMessageDialog(null, "Barcode: " + result.getText());
            //this.setVisible(false);
            barcodeResult = "Barcode: " + result.getText();
            shouldStop=true;
            this.setVisible(false);
        }
        catch (Exception e) {
            barcodeResult = null;
        }
    }
    
    
    public void stopScanningg() {
        shouldStop = true;
    }

    // Méthode pour récupérer le résultat du scan

    
    
   public static void startScanning () {
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
               Barcode_Scanner camera = new Barcode_Scanner();
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       camera.startCamera();
                   }
               }).start();
           }
       });
   }
}
