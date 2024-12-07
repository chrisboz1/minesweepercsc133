#version 410 core

layout (location=0) in vec3 aPos;
layout (location=1) in vec2 aTexCoords;

uniform mat4 uProjMatrix;
uniform mat4 uViewMatrix;

// Declare an output variable to pass texture coordinates to the fragment shader
out vec2 fTexCoords;

void main()
{
    fTexCoords = aTexCoords; // Pass input texture coordinates to the output variable
    gl_Position = uProjMatrix * uViewMatrix * vec4(aPos, 1.0); // Compute position
}
