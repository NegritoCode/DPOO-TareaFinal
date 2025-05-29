package utils;

import java.util.HashMap;
import javax.swing.JFrame;

public class Navigation {
    private static HashMap<String, JFrame> screens = new HashMap<String, JFrame>();
    
    private static String currentFrame;
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

    public static void goTo(String name) {
        getScreen(name).setVisible(true);
        if (currentFrame != null) {
            getScreen(currentFrame).setVisible(false);
        }
        currentFrame = name;
    }
}
