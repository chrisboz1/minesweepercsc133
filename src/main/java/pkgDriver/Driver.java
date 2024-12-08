package pkgDriver;




import pkgBackendEngine.SlMSBoard;
import pkgSlRenderEngine.SlRenderer;
import pkgSlRenderEngine.XYMouseListener;
import pkgSlUtils.SlWindowManager;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import static pkgDriver.SlSpot.*;

public class Driver {
    public static void main(String[] my_args) throws IOException {
        SlMSBoard board = new SlMSBoard(9, 14, 7);
//        board.printBoard();
//        System.out.println("");
//        board.printCellScores();


//        SlRenderer renderEngine = new SlRenderer(board);
//        System.out.println("test");
        SlWindowManager test = new SlWindowManager();
        test.initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, WINDOW_TITLE);
        SlRenderer renderEngine = new SlRenderer(board, test);
//        pkgSlUtils.SlWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, WINDOW_TITLE);
        renderEngine.initOpenGL(test);
        renderEngine.renderBoard();
    }
}