//package pkgSlRenderEngine;
//
//import org.joml.Vector4f;
//import org.lwjgl.BufferUtils;
//import org.lwjgl.opengl.GL;
//import pkgBackendEngine.SlMSBoard;
//import pkgDriver.SlSpot;
//
//import java.nio.IntBuffer;
//
//import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL20.*;
//import static org.lwjgl.opengl.GL30.*;
//
//public class SlRenderBoard {
//
//    private SlMSBoard board;  // Reference to the SlMSBoard instance
//    private SlShaderObject my_shader;
//
//    private final int positionStride = 3;
//    private final int texCoordStride = 2;
//    private final int vertexStride = 5;
//    private float[] vertexData;
//
//    // Constructor - accepts a reference to the board
//    public SlRenderBoard(SlMSBoard board) {
//        this.board = board;
//        this.my_shader = new SlShaderObject();  // Assuming a shader class is available
//    }
//
//    // Initialize OpenGL objects (VAO, VBO, etc.)
//    public void initRender() {
//        // Set up the vertex data for the tiles (x, y, z, u, v)
//        fillVertexData();
//
//        // Create and bind VAO
//        int vaoID = glGenVertexArrays();
//        glBindVertexArray(vaoID);
//
//        // Create and bind VBO
//        int vboID = glGenBuffers();
//        glBindBuffer(GL_ARRAY_BUFFER, vboID);
//        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW());
//
//        // Set up the vertex attribute pointers
//        glVertexAttribPointer(0, positionStride, GL_FLOAT, false, vertexStride * Float.BYTES, 0);
//        glVertexAttribPointer(1, texCoordStride, GL_FLOAT, false, vertexStride * Float.BYTES, positionStride * Float.BYTES);
//        glEnableVertexAttribArray(0);
//        glEnableVertexAttribArray(1);
//
//        // Initialize the shader
//        my_shader.compile_shader();
//        my_shader.set_shader_program();
//    }
//
//    // Fill vertex data for each tile
//    private void fillVertexData() {
//        // Generate a flat array of vertex data
//        int numRows = board.getNumRows();
//        int numCols = board.getNumCols();
//        vertexData = new float[numRows * numCols * 4 * vertexStride];
//
//        int index = 0;
//        for (int row = 0; row < numRows; row++) {
//            for (int col = 0; col < numCols; col++) {
//                // Get cell position (x, y)
//                float x = col * 1.0f;  // Adjust scale as needed
//                float y = row * 1.0f;  // Adjust scale as needed
//
//                // Add vertices for the current tile
//                vertexData[index++] = x;         // x1
//                vertexData[index++] = y;         // y1
//                vertexData[index++] = 0.0f;      // z (flat)
//                vertexData[index++] = 0.0f;      // u (texture coord)
//                vertexData[index++] = 0.0f;      // v (texture coord)
//
//                vertexData[index++] = x;         // x2
//                vertexData[index++] = y + 1.0f;  // y2
//                vertexData[index++] = 0.0f;      // z
//                vertexData[index++] = 0.0f;      // u
//                vertexData[index++] = 1.0f;      // v
//
//                vertexData[index++] = x + 1.0f;  // x3
//                vertexData[index++] = y + 1.0f;  // y3
//                vertexData[index++] = 0.0f;      // z
//                vertexData[index++] = 1.0f;      // u
//                vertexData[index++] = 1.0f;      // v
//
//                vertexData[index++] = x + 1.0f;  // x4
//                vertexData[index++] = y;         // y4
//                vertexData[index++] = 0.0f;      // z
//                vertexData[index++] = 1.0f;      // u
//                vertexData[index++] = 0.0f;      // v
//            }
//        }
//    }
//
//    // Render the board using the shader and OpenGL
//    public void renderBoard() {
//        // Bind the shader program and load projection and view matrices
//        SlCamera camera = new SlCamera();
//        my_shader.loadMatrix4f("uProjMatrix", camera.getProjectionMatrix());
//        my_shader.loadMatrix4f("uViewMatrix", camera.getViewMatrix());
//
//        // Render each tile based on the board state
//        for (int row = 0; row < board.getNumRows(); row++) {
//            for (int col = 0; col < board.getNumCols(); col++) {
//                renderTile(row, col);
//            }
//        }
//    }
//
//    // Render an individual tile based on its (row, col) position
//    private void renderTile(int row, int col) {
//        SlSpot cell = board.getCell(row, col);
//
//        // Set tile color based on the cell type (for example, mines are red)
//        Vector4f color = getCellColor(cell);
//
//        // Pass the color to the shader
//        my_shader.loadVector4f("COLOR_FACTOR", color);
//
//        // Draw the tile
//        glDrawArrays(GL_TRIANGLE_FAN, (row * board.getNumCols() + col) * 4, 4);
//    }
//
//    // Determine color based on the cell type (e.g., mine, gold, empty)
//    private Vector4f getCellColor(SlSpot cell) {
//        switch (cell.getCellType()) {
//            case MINE:
//                return new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);  // Red for mines
//            case GOLD:
//                return new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);  // Yellow for gold
//            case EMPTY:
//            default:
//                return new Vector4f(0.5f, 0.5f, 0.5f, 1.0f);  // Gray for empty
//        }
//    }
//}
//
