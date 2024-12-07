package pkgDriver;




import pkgBackendEngine.SlMSBoard;
import pkgSlRenderEngine.SlRenderer;

import java.io.IOException;

import static pkgDriver.SlSpot.*;

public class Driver {
    public static void main(String[] my_args) throws IOException {
        SlMSBoard board = new SlMSBoard(3, 1, 3);
        board.printBoard();

//        SlRenderer renderEngine = new SlRenderer();
//        pkgSlUtils.SlWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, WINDOW_TITLE);
//        renderEngine.initOpenGL(pkgSlUtils.SlWindowManager.get());
//
//        renderEngine.renderBoard();
    }
}