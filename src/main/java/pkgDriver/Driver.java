package pkgDriver;




import pkgSlRenderEngine.SlRenderer;

import java.io.IOException;

import static pkgDriver.SlSpot.*;

public class Driver {
    public static void main(String[] my_args) throws IOException {
        SlRenderer renderEngine = new SlRenderer();
        pkgSlUtils.SlWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, WINDOW_TITLE);
        renderEngine.initOpenGL(pkgSlUtils.SlWindowManager.get());
        //renderEngine.initRender();
        renderEngine.renderBoard();
    } // public static void main(String[] my_args)
} // public class csc133Driver(...)