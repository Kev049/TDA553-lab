package userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    ArrayList<String> vehicleModelNames;
    HashMap<String, Point> positionMap;
    HashMap<String, BufferedImage> vehicleImageMap;

    void moveVehicle(int x, int y, String modelName) {
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
        this.vehicleImageMap = new HashMap<>();

        for (String modelName : vehicleModelNames) {
            positionMap.put(modelName, new Point());
            try {
                String path = "pics/" + modelName + ".jpg";
                BufferedImage image = ImageIO.read(DrawPanel.class.getResourceAsStream(path));
                vehicleImageMap.put(modelName, image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (String modelName : vehicleModelNames) {
            Point pos = positionMap.get(modelName);
            g.drawImage(vehicleImageMap.get(modelName), pos.x, pos.y, null);
        }
    }

}
