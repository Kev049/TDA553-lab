package userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    // To keep track of a single car's position

    ArrayList<String> vehicleModelNames;
    HashMap <String, Point> positionMap;
    HashMap <String, BufferedImage> imageMap;
    

    Point volvoPoint = new Point();
    Point saabPoint = new Point();
    Point scaniaPoint = new Point();

    // TODO: Make this general for all cars
    void moveit(int x, int y, String modelName){
        if (positionMap.containsKey(modelName)) {
            positionMap.put(modelName, new Point(x, y));
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<String> vehicleModelNames) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        
        this.vehicleModelNames = vehicleModelNames;
        this.positionMap = new HashMap<>();
        this.imageMap = new HashMap<>();

        System.out.println(vehicleModelNames);

        for (String modelName : vehicleModelNames) {
            positionMap.put(modelName, new Point());
            

            try {
                String path = "pics/" + modelName + ".jpg";
                BufferedImage image = ImageIO.read(DrawPanel.class.getResourceAsStream(path));
                imageMap.put(modelName, image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageMap.get("Volvo240"), positionMap.get("Volvo240").x, positionMap.get("Volvo240").y, null); // see javadoc for more info on the parameters
        g.drawImage(imageMap.get("Saab95"), positionMap.get("Saab95").x, positionMap.get("Saab95").y, null);
        g.drawImage(imageMap.get("Scania"), positionMap.get("Scania").x, positionMap.get("Scania").y, null);
    }

}
