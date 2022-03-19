#include <GL/glew.h>
#include <GL/glut.h>
#include <GL/freeglut.h>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "objects.h"
#include "camera.h"
#include "shadow.h"
#include "light.h"
#include "mirror.h"
#include <math.h>      

#define M_PI 3.14159265358979323846

// Used in pop-up menu for clarity
enum 
{
    M_NONE,
    M_LIGHT_MOTION,
    M_LIGHT,
    M_POSITIONAL_LIGHT,
    M_SHADOWS,
    M_REFLECTION,
    M_FLOOR_SHADOW_OBJ,
    M_TEXTURES,
    M_SWAP_TEXTURES,
    M_TOGGLE_FLOOR,
    M_CAMERA0,
    M_CAMERA1,
    M_CAMERA2,
    M_CAMERA3,
    M_EXIT
};

// When window is resized
void handleResize(int w, int h) 
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);

    glLoadIdentity();
    gluPerspective(45.0, (double)w / (double)h, 1.0, 200.0); 
}

// Draw table, cube, and mirror
void drawAllObjects() 
{
    drawTable();
    glassCube();
    drawWallMirror();
}

// Main draw function
void drawScene() 
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    glMatrixMode(GL_MODELVIEW); 
    glLoadIdentity();

    gluLookAt(cameraX, cameraY, cameraZ, 
              cameraX + cameraDirectionX, 1.0f, cameraZ + cameraDirectionZ, 
              0.0f, 1.0f, 0.0f);
    drawMirrors();
    drawWall();
    glEnable(GL_CULL_FACE);
    glCullFace(GL_FRONT);

    drawAllObjects();
    glCullFace(GL_BACK);
    drawAllObjects();
    glDisable(GL_CULL_FACE);
    glFlush();

    int start, end;

    // Reposition the moving light
    light_position0[0] = 20 * cos(lightAngle);
    light_position0[1] = 20 * sin(lightAngle);
    light_position0[2] = lightHeight;
    light_position0[3] = 0.0;

    shadowMatrix(floorShadow, floorPlane, light_position0);

    // Update moving light location
    glPushMatrix();
    glLightfv(GL_LIGHT0, GL_POSITION, light_position0);

    if (renderShadow) 
    {
        // Draw individual shadows for each object
        // Table, table legs, cube
        glEnable(GL_STENCIL_TEST);
        glStencilFunc(GL_ALWAYS, 3, 0xffffffff);
        glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);
    }

    // Draw top of floor for use with shadow display
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    glColor4f(0.7f, 0.0f, 0.0f, 0.3f);

    drawFloor();
    glDisable(GL_BLEND);

    if (FloorShadow) 
    {
        floorShadowObj();

        // Shadows all combined
        glStencilFunc(GL_LESS, 2, 0xffffffff);
        glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);

        // Render shadow color on floor
        // 50% alpha value
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_LIGHTING);
        glColor4f(0.0, 0.0, 0.0, 0.5);

        glPushMatrix();
        glMultMatrixf((GLfloat*)floorShadow);
        floorShadowObj();
        glPopMatrix();

        glDisable(GL_BLEND);
        glEnable(GL_LIGHTING);

        glDisable(GL_STENCIL_TEST);
    }

    glPushMatrix();
    glDisable(GL_LIGHTING);

    // Draw sphere at moving light location
    glColor3f(1.0, 0.5, 0.0);
    glTranslatef(light_position0[0], light_position0[1], light_position0[2]);
    glutSolidSphere(3.0, 75, 75);

    glEnable(GL_LIGHTING);
    glPopMatrix();

    glPopMatrix();

    glutSwapBuffers();
}

// Handles the pop-up menu functionality
static void popupMenuHandler(int value) 
{
    switch (value) 
    {
        case M_LIGHT_MOTION:
            animation = 1 - animation;
            if (animation) { glutIdleFunc(moveLightIdle); }
            else { glutIdleFunc(NULL); }
            break;

        case M_LIGHT:
            lightSwitch = !lightSwitch;
            if (lightSwitch) { glEnable(GL_LIGHT0); }
            else { glDisable(GL_LIGHT0); }
            break;

        case M_POSITIONAL_LIGHT:
            posLightSwitch = !posLightSwitch;
            if (posLightSwitch) { glEnable(GL_LIGHT1); }
            else { glDisable(GL_LIGHT1); }
            break;

        case M_SHADOWS:
            renderShadow = 1 - renderShadow;
            break;

        case M_FLOOR_SHADOW_OBJ:
            FloorShadow = 1 - FloorShadow;
            break;

        case M_TEXTURES:
            if (toggleTextures == 0)
            { toggleTextures = 1; }
            else if (toggleTextures == 1)
            { toggleTextures = 0; }
            break;

        case M_SWAP_TEXTURES:
            if (swapTextures == 0)
            { swapTextures = 1; }
            else if (swapTextures == 1)
            { swapTextures = 2; }
            else if (swapTextures == 2)
            { swapTextures = 0; }
            break;

        case M_TOGGLE_FLOOR:
            if (toggleFloorTexture == 0)
            { toggleFloorTexture = 1; }
            else if (toggleFloorTexture == 1)
            { toggleFloorTexture = 0; }
            break;

        case M_CAMERA0:
            cameraAngle = 0.0;
            cameraX = -1.0f;
            cameraY = 1.0f;
            cameraZ = 13.0f;
            cameraDirectionX = 0.0f;
            cameraDirectionZ = -1.0f;
            break;
        case M_CAMERA1:
            cameraX = -15.0f;
            cameraY = 8.0f;
            cameraZ = 15.0f;
            cameraDirectionX = +15.0f;
            cameraDirectionZ = -15.0f;
            break;
        case M_CAMERA2:
            cameraX = 15.0f;
            cameraY = 0.0f;
            cameraZ = 15.0f;
            cameraDirectionX = -15.0f;
            cameraDirectionZ = -15.0f;
            break;
        case M_CAMERA3:
            cameraX = 15.0f;
            cameraY = 8.0f;
            cameraZ = -15.0f;
            cameraDirectionX = -15.0f;
            cameraDirectionZ = 15.0f;
            break;

        case M_EXIT:
            exit(0);
          
        break;
    }

    glutPostRedisplay();
}

// Used to toggle the idle animation (sun day/night cycle)
static void visible(int vis) 
{
    if (vis == GLUT_VISIBLE) 
    {
        if (animation)
        {
            glutIdleFunc(moveLightIdle);
        }
    }
    else 
    {
        if (!animation)
        {
            glutIdleFunc(NULL);
        }
    }
}

// Handle keyboard presses
void keyboard(unsigned char key, int x, int y) 
{
    switch (key)
    {
        case 'Q': 
        case 'q':
            exit(0); 
        case ',':
            cameraX = -15.0f;
            cameraY = 8.0f;
            cameraZ = 15.0f;
            cameraDirectionX = +15.0f;
            cameraDirectionZ = -15.0f;
            break;
        case '.':
            cameraX = 15.0f;
            cameraY = 0.0f;
            cameraZ = 15.0f;
            cameraDirectionX = -15.0f;
            cameraDirectionZ = -15.0f;
            break;
        case '/':
            cameraX = 15.0f;
            cameraY = 8.0f;
            cameraZ = -15.0f;
            cameraDirectionX = -15.0f;
            cameraDirectionZ = 15.0f;
            break;
        case 'N':
        case 'n':
            cameraAngle = 0.0;
            cameraX = -1.0f;
            cameraY = 1.0f;
            cameraZ = 13.0f;
            cameraDirectionX = 0.0f;
            cameraDirectionZ = -1.0f;
            break;
        case ' ':
            animation = 1 - animation;
            if (animation) { glutIdleFunc(moveLightIdle); }
            else { glutIdleFunc(NULL); }
            break;
        default:
            cameraX = -1.0f;
            cameraY = 1.0f;
            cameraZ = 13.0f;
            cameraDirectionX = 0.0f;
            cameraDirectionZ = -1.0f;
            break;

            glutPostRedisplay();
    }
}


// Display program controls
void printProgramControls()
{
    // Controls for program.
    printf("Program controls:\n"
        "\tPress ',' to switch to camera view 1.\n"
        "\tPress '.' to switch to another camera view 2.\n"
        "\tPress '/' to switch to another camera view 3.\n"
        "\tPress 'N' or 'n' reset camera view. \n"
        "\t     this view allows movement\n\n"

        "\tPress 'Up Arrow' to move camera forward.\n"
        "\tPress 'Down Arrow' to move camera backward.\n\n"
        "\tPress 'Left Arrow' to pan camera left.\n"
        "\tPress 'Right Arrow' to pan camera right.\n\n"
        "\tPress 'Spacebar' to toggle sun animation.\n"
        "\tPress 'Right Click' to open a pop-up menu.\n"
        "\tPress 'Q' or 'q' to exit");
}

// Main method
int main(int argc, char* argv[]) 
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH | GLUT_STENCIL | GLUT_MULTISAMPLE);
    glutInitWindowSize(720, 480); 
    glutCreateWindow("FinalProject");
    initTextures();

    positionalLight();
    sunLight();
    
    // Material properties
    glMaterialfv(GL_FRONT, GL_AMBIENT, material_ambient);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, material_diffuse);
    glMaterialfv(GL_FRONT, GL_SPECULAR, material_specular);
    glMaterialfv(GL_FRONT, GL_EMISSION, material_emissive);
    glMateriali(GL_FRONT, GL_SHININESS, material_shininess);

    glutDisplayFunc(drawScene);

    glutVisibilityFunc(visible);

    glutKeyboardFunc(keyboard);

    // Pop up menu
    int sub1, sub2, sub3, sub4; // ints to get the callback from the sub-menus
    sub1 = glutCreateMenu(popupMenuHandler);
    glutAddMenuEntry("Toggle: Light Motion", M_LIGHT_MOTION);
    glutAddMenuEntry("Toggle: Moving Light", M_LIGHT);
    glutAddMenuEntry("Toggle: Positional Light", M_POSITIONAL_LIGHT);

    sub2 = glutCreateMenu(popupMenuHandler);
    glutAddMenuEntry("Toggle: Other Shadows", M_SHADOWS);
    glutAddMenuEntry("Toggle: Base Shadows", M_FLOOR_SHADOW_OBJ);

    sub3 = glutCreateMenu(popupMenuHandler);
    glutAddMenuEntry("Toggle: Textures", M_TEXTURES);
    glutAddMenuEntry("Toggle: Floor Textures", M_TOGGLE_FLOOR);
    glutAddMenuEntry("Swap Textures", M_SWAP_TEXTURES);

    sub4 = glutCreateMenu(popupMenuHandler);
    glutAddMenuEntry("Reset Camera (view 0)", M_CAMERA0);
    glutAddMenuEntry("Camera View 1", M_CAMERA1);
    glutAddMenuEntry("Camera View 2", M_CAMERA2);
    glutAddMenuEntry("Camera View 3", M_CAMERA3);

    glutCreateMenu(popupMenuHandler);
    glutAddSubMenu("Lights", sub1);
    glutAddSubMenu("Shadows", sub2);
    glutAddSubMenu("Textures", sub3);
    glutAddSubMenu("Camera", sub4);
    glutAddMenuEntry("Exit", M_EXIT);
    glutAttachMenu(GLUT_RIGHT_BUTTON);

    glEnable(GL_CULL_FACE);
    glEnable(GL_DEPTH_TEST);

    glLineWidth(3.0);

    // Set camera view
    glMatrixMode(GL_PROJECTION);
    gluPerspective( 40.0f, 1.0f, 20.0f, 100.0f);
    glMatrixMode(GL_MODELVIEW);

    findPlane(floorPlane, floorVertices[1], floorVertices[2], floorVertices[3]);

    // Camera movement and resize
    glutSpecialFunc(handleSpecialKeypress);
    glutReshapeFunc(handleResize);
    glutTimerFunc(25, updateCamera, 0);

    printProgramControls();

    glutMainLoop();
    return 0;
}
