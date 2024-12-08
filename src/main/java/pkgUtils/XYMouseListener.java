package pkgUtils;


import static org.lwjgl.glfw.GLFW.*;

public class XYMouseListener {
    private static XYMouseListener my_instance;
    private double scrollX, scrollY;
    double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;
    public static double mouseX;
    public static double mouseY;

    public XYMouseListener() {
        this.scrollX = 0.0f;
        this.scrollY = 0.0f;
        this.xPos = 0.0f;
        this.yPos = 0.0f;
        this.lastX = 0.0f;
        this.lastY = 0.0f;
        this.isDragging = false;
    }

    public static XYMouseListener get() {
        if (my_instance == null) {
            my_instance = new XYMouseListener();
        }
        return my_instance;
    }

    public static void mousePosCallback(long my_window, double pos_x, double pos_y) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = pos_x;
        get().yPos = pos_y;
        get().isDragging = get().mouseButtonPressed[0] ||
                get().mouseButtonPressed[1] ||
                get().mouseButtonPressed[2];

        // Store the mouse position
        mouseX = pos_x;
        mouseY = pos_y;
    }

    public static void mouseButtonCallback(long my_window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseButtonDownReset(int button) {
        get().mouseButtonPressed[button] = false;
    }

    public static void mouseScrollCallback(long my_window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX() {
        return (float) get().xPos;
    }

    public static float getY() {
        return (float) get().yPos;
    }

    public static float getDeltaX() {
        return (float) (get().lastX - get().xPos);
    }

    public static float getDeltaY() {
        return (float) (get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float) get().scrollX;
    }

    public static float getScrollY() {
        return (float) get().scrollY;
    }

    public static boolean isDragging() {
        return (boolean) get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
    public static void setMouseButtonCallback(long window) {
        glfwSetMouseButtonCallback(window, (windowHandle, button, action, mods) -> {
            // Query current mouse position from GLFW when button is pressed/released
            double[] xPos = new double[1];
            double[] yPos = new double[1];
            glfwGetCursorPos(windowHandle, xPos, yPos);  // Get mouse position in window space

            XYMouseListener.mouseX = xPos[0];
            XYMouseListener.mouseY = yPos[0];
            if (action == GLFW_PRESS) {
                // Mouse button pressed
                System.out.println("Button " + button + " pressed at (" + xPos[0] + ", " + yPos[0] + ")");
                mouseX = xPos[0];
                mouseY = yPos[0];
            } else if (action == GLFW_RELEASE) {
                // Mouse button released
                System.out.println("Button " + button + " released");
            }
        });
    }
    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }


    public static void setCursorPosCallback(long window) {
        glfwSetCursorPosCallback(window, (windowHandle, xpos, ypos) -> {
            get().xPos = xpos;  // Update the x-position in XYMouseListener
            get().yPos = ypos;  // Update the y-position in XYMouseListener
        });
    }

}