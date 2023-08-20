import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Listens for keyboard events
public class MyKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // Check the pressed key and perform corresponding actions
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> {
                // Toggle simulation start/stop if end node is set and simulation is not running
                if (App.isEnd && !App.simulating) SimulationHandler.startSim();
                else if (App.simulating) SimulationHandler.stopSim();
            }
            case KeyEvent.VK_C -> {
                // Delete all nodes if not simulating
                if (!App.simulating) NodeController.deleteAllNodes();
            }
            case KeyEvent.VK_R -> {
                // Create a map if not simulating
                if (!App.simulating) NodeController.createMap();
            }
            case KeyEvent.VK_ESCAPE -> {
                // Clear non-start, non-end, and non-wall nodes if not simulating
                if (!App.simulating) NodeController.clear();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
