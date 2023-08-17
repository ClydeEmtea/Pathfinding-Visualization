import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    App app;

    public MyKeyListener(App app) {
        this.app = app;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> {
                if (app.isEnd && !app.simulating) app.startSim();
                else if (app.simulating) app.stopSim();
            }
            case KeyEvent.VK_C -> {
                if (!app.simulating) app.deleteAllNodes();
            }
            case KeyEvent.VK_R -> {
                if (!app.simulating) app.createMap();
            }
            case KeyEvent.VK_ESCAPE -> {
                if (!app.simulating) app.clear();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
