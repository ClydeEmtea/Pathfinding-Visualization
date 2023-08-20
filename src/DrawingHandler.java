import java.awt.*;

public class DrawingHandler {

    // Draws all nodes on the grid
    public static void drawNodes(Graphics g){
        for (Node[] nodes1 : App.nodes) {
            for (Node node : nodes1) {
                node.draw(g);
            }
        }
    }

    // Draws instructions on the screen
    public static void drawInstructions(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Press R to generate a maze", 10, 20);
        g.drawString("Press C to clear the map", 10, 40);
        g.drawString("Press SPACE to start/stop the simulation", 10, 60);
        g.drawString("Press and hold the left mouse button to draw walls", 10, 80);
        g.drawString("Press the right mouse button to delete walls", 10, 100);
    }

    // Draws the simulation results on the screen
    public static void drawResults(Graphics g) {
        g.setColor(Constants.darkGray);
        g.fillRect(0, 0, 200, 50);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        if (!App.success) {
            g.drawString("No path found!", 10, 20);
        } else {
            g.drawString("Pathfinding complete!", 10, 20);
            g.drawString("Distance: " + App.endNode.getGCost(), 10, 40);
        }
    }
}
