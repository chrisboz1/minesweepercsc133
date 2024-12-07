package pkgSlRenderEngine;

import org.joml.Matrix4f;
import org.joml.Vector3f;


import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static pkgDriver.SlSpot.*;

public class SlCamera {
//    private Vector3f lookFrom; // Camera position
//    private Vector3f lookAt;   // Point the camera looks at
//    private Vector3f lookUp;   // Up direction of the camera
//    private Matrix4f viewMatrix;
//    private Matrix4f projectionMatrix;
//
//    // Constructor with default values
//    public SlCamera() {
//        // Default: Camera at origin, looking down negative Z-axis, Y-axis is up
//        this.lookFrom = new Vector3f(0.0f, 0.0f, 1.0f);
//        this.lookAt = new Vector3f(0.0f, 0.0f, -1.0f);
//        this.lookUp = new Vector3f(0.0f, 1.0f, 0.0f);
//
//        // Matrices
//        this.viewMatrix = new Matrix4f();
//        this.projectionMatrix = new Matrix4f();
//
//        updateViewMatrix(); // Initialize view matrix
//    }
//
//    // Method to update the view matrix
//    private void updateViewMatrix() {
//        // lookAt is relative to lookFrom: must add lookFrom to defaultLookAt
//        Vector3f target = new Vector3f();
//        lookAt.add(lookFrom, target); // target = lookFrom + lookAt
//        viewMatrix.identity();        // Reset to identity matrix
//        viewMatrix.lookAt(lookFrom, target, lookUp); // JOML helper method
//    }
//
//    // Method to set an orthographic projection matrix
//    public void setOrthographic(float left, float right, float bottom, float top, float near, float far) {
//        projectionMatrix.identity();  // Reset to identity matrix
//        projectionMatrix.ortho(left, right, bottom, top, near, far); // Set orthographic projection
//    }
//
//    // Private getter for the view matrix
//    private Matrix4f getViewMatrix() {
//        return viewMatrix;
//    }
//
//    // Private getter for the projection matrix
//    private Matrix4f getProjectionMatrix() {
//        return projectionMatrix;
//    }
//
//    // Allow updating the camera position (lookFrom)
//    public void setLookFrom(Vector3f position) {
//        this.lookFrom.set(position);
//        updateViewMatrix();
//    }
//
//    // Allow updating the target direction (lookAt)
//    public void setLookAt(Vector3f direction) {
//        this.lookAt.set(direction);
//        updateViewMatrix();
//    }
//
//    // Allow updating the up vector
//    public void setLookUp(Vector3f up) {
//        this.lookUp.set(up);
//        updateViewMatrix();
//    }
//
//    // Convenience method to pass matrices to OpenGL
//    public float[] getViewMatrixAsFloatArray() {
//        float[] matrixArray = new float[16];
//        viewMatrix.get(matrixArray);
//        return matrixArray;
//    }
//
//    public float[] getProjectionMatrixAsFloatArray() {
//        float[] matrixArray = new float[16];
//        projectionMatrix.get(matrixArray);
//        return matrixArray;
//    }
private Vector3f lf = new Vector3f(0f, 0f, 100f);
    private Vector3f la = new Vector3f(0f, 0f, -1.0f);
    private Vector3f up = new Vector3f(0f, 1.0f, 0f);

    public Matrix4f getProjectionMatrix() {
        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.identity();
        projectionMatrix.ortho(FRUSTUM_LEFT, FRUSTUM_RIGHT, FRUSTUM_BOTTOM, FRUSTUM_TOP, Z_NEAR, Z_FAR);
        return projectionMatrix;
    }
    public Matrix4f getViewMatrix() {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.identity();
        viewMatrix.lookAt(lf, la.add(lf), up);
        return viewMatrix;
    }
}
