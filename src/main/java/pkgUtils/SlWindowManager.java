package pkgSlUtils;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.*;
import pkgUtils.XYMouseListener;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class SlWindowManager {
    private static SlWindowManager instance = new SlWindowManager();
    private static long my_win = NULL;
    public static SlWindowManager window;



    private static void slWindowCreate(int win_width, int win_height, String title) {
        if(my_win == NULL) {
            my_win = glfwCreateWindow(win_width, win_height, title, NULL, NULL);
        }
    }
    public static SlWindowManager get() {
        if(instance == null) {
            instance = new SlWindowManager();
        }
        return instance;
    }
    public void destroyGlfwWindow() {
        glfwDestroyWindow(my_win);
    }
    public void swapBuffers() {
        glfwSwapBuffers(my_win);
    }
    public Boolean isGlfwWindowClosed() {
        return glfwWindowShouldClose(my_win);
    }
    public void updateContextToThis() {
        glfwMakeContextCurrent(my_win);
    }
    public void initGLFWWindow(int win_width, int win_height, String title) {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        slWindowCreate(win_width, win_height, title);
        if (my_win == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }  //  if (window == NULL)
        updateContextToThis();
        GL.createCapabilities();
        glfwDefaultWindowHints();
//        XYMouseListener.setMouseButtonCallback(my_win);

        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
        float CC_RED = 0.0f, CC_GREEN = 0.0f, CC_BLUE = 1.0f, CC_ALPHA = 1.0f;
        glClearColor(CC_RED, CC_GREEN, CC_BLUE, CC_ALPHA);

    }

    public double getX() {

        XYMouseListener.setMouseButtonCallback(my_win);
        return XYMouseListener.getMouseX();
    }
    public double getY() {

        XYMouseListener.setMouseButtonCallback(my_win);
        return XYMouseListener.getMouseY();
    }
//    public boolean  {
//        // Check if the mouse button was pressed and return the result
//        return XYMouseListener.mouseButtonDown(0);  // Ensure `isMouseButtonPressed` is implemented in XYMouseListener
//    }

    public long getLong() {
        return my_win;
    }
}  // public class Main