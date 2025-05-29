package utils;

import java.util.HashMap;
import javax.swing.JFrame;

public class ScreenManager {
    private static HashMap<String, JFrame> screens = new HashMap<String, JFrame>();

    public static void register(String name, JFrame frame) {
        screens.put(name, frame);
    }

    public static JFrame getScreen(String name) {
        JFrame frame = screens.get(name);
        if (frame == null) {
            throw new IllegalArgumentException("Screen not registered: " + name);
        }
        return frame;
    }

    public static void show(String name) {
        getScreen(name).setVisible(true);
    }

    public static void hide(String name) {
        getScreen(name).setVisible(false);
    }
}
