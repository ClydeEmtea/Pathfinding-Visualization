import javax.swing.*;

public class Main extends JFrame {

    Main() { // Constructor
        this.setResizable(false); // Prevents window from being resized
        this.add(new App()); // Adds the App.java class to the JFrame
        this.setTitle("Pathfinding"); // Sets the title of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows the window to be closed
        this.pack(); // Sizes the window so that all its contents are at or above their preferred sizes
        this.setVisible(true); // Makes the window visible
        this.setLocationRelativeTo(null); // Centers the window
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); // Enables OpenGL

        new Main();
    }
}