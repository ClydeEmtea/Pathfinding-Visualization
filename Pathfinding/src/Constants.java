import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public interface Constants {

    // Constants used throughout the program
    int WIDTH = 1600;
    int HEIGHT = 800;
    int DELAY = 10;
    int NODE_SIZE = 20;
    int NODES_X = WIDTH / NODE_SIZE;
    int NODES_Y = HEIGHT / NODE_SIZE;
    int STEPS_PER_FRAME = 4;

    Color darkGray = new Color(40, 40, 40);
    Color gray = new Color(100, 100, 100);
    Color lightGray = new Color(200, 200, 200);
    Color lightBlue = new Color(142,199,220);
    Color green = new Color(189,255,172);
    Color darkBlue = new Color(0,82,120);
    Color red = new Color(150, 50, 50);

    static int[][] createMap(int height, int width) {
        int[][] maze = new int[height][width]; // Generate a maze with dimensions height x width

        for (int i = 0; i < height; i++) { // Fill the maze with walls
            Arrays.fill(maze[i], 1);
        }

        createMaze(maze, 0, 0);  // Start generating maze from the top-left corner

        return maze;
    }

    static void createMaze(int[][] maze, int x, int y) {
        maze[y][x] = 0;  // Mark current cell as empty

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Define directions to move in
        shuffleArray(directions); // Randomize the order of directions

        for (int[] direction : directions) { // Loop through each direction
            int dx = direction[0]; // Get the x coordinate of the direction
            int dy = direction[1]; // Get the y coordinate of the direction
            int nx = x + dx * 2; // Get the x coordinate of the cell 2 steps away from the current cell
            int ny = y + dy * 2; // Get the y coordinate of the cell 2 steps away from the current cell

            if (0 <= nx && nx < maze[0].length && 0 <= ny && ny < maze.length && maze[ny][nx] == 1) { // Check if the cell 2 steps away is within the maze and is a wall
                maze[ny - dy][nx - dx] = 0;  // Knock down the wall between current and next cell
                createMaze(maze, nx, ny);  // Recursively visit next cell
            }
        }
    }

    // Method that shuffles a 2D array
    static void shuffleArray(int[][] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int[] temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}


