import javax.swing.*;

// Main class for running the pathfinding application
public class Main extends JFrame {

    // Constructor
    Main() {
        this.setResizable(false); // Prevents window from being resized
        this.add(new App()); // Adds the App.java class to the JFrame
        this.setTitle("Pathfinding"); // Sets the title of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows the window to be closed
        this.pack(); // Sizes the window so that all its contents are at or above their preferred sizes
        this.setVisible(true); // Makes the window visible
        this.setLocationRelativeTo(null); // Centers the window
    }

    // Main method to start the application
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); // Enables OpenGL

        new Main(); // Create an instance of Main to start the application
    }
}
