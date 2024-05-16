import java.awt.*;

// Provides constants used throughout the program
public interface Constants {

    // Dimensions and timing
    int WIDTH = 1600;           // Width of the window
    int HEIGHT = 800;           // Height of the window
    int DELAY = 10;             // Timer delay in milliseconds
    int NODE_SIZE = 16;         // Size of each grid node
    int NODES_X = WIDTH / NODE_SIZE;    // Number of nodes along the X-axis
    int NODES_Y = HEIGHT / NODE_SIZE;   // Number of nodes along the Y-axis
    int STEPS_PER_FRAME = 20;   // Number of simulation steps per frame update
    boolean DIAGONAL_MOVEMENT = false;   // Whether diagonal movement is allowed

    // Colors
    Color darkGray = new Color(40, 40, 40);    // Dark gray color
    Color lightGray = new Color(200, 200, 200);  // Light gray color
    Color lightBlue = new Color(142, 199, 220);  // Light blue color
    Color green = new Color(189, 255, 172);      // Green color
    Color darkBlue = new Color(50, 100, 170);    // Dark blue color
    Color red = new Color(150, 50, 50);          // Red color
}
