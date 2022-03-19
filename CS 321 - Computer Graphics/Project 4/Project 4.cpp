/*
NAME:		Montana Singer
PROJECT:	Project 4
COURSE:	    CS 321
INSTRUCTOR:	Beomjin Kim
DUE DATE:	November 5, 2020
*/

#include <iostream>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <GL/freeglut.h>

#define M_PI 3.14159265358979323846

// Function prototypes.
void keyboard(unsigned char k, int x, int y);
void mouse(int button, int state, int x, int y);
void mouseMotion(int x, int y);
void initializeCoordinateArray();
void display(void);
void init_window(int argc, char** argv);
void other_init();
void translate(double x, double y, double z);
void rotate(double angle, double x, double y, double z);
void scale(double x, double y, double z);
void translateViewport(double moveX, double moveY);
void scaleViewport(double scaleFactor);
void drawObject();

int* coordinateArray;             // Global variable holding the array containing jump commands and coordinates.
int numberOfArrayItems = 0;       // Global variable holds the size of the dynamically allocated array.
const int jumpKey = 3000;         // Used to identify when a J or jump occurs in the data file.
double objectHeight = 0;          // Used in the translate method to move by a percentage of the height
double objectWidth = 0;           // Used in the translate method to move by a percentage of the width

double xMin = -1200;              // 
double xMax = 1200;               //
double yMin = -1200;              // Define the maximum viewing volume/world coordinates along the axes
double yMax = 1200;               //
double zMin = -2100;              //
double zMax = 2100;               //

double objectMaxX = 0;            //
double objectMinX = 0;            //
double objectMaxY = 0;            // Define the object's maximum and minimum vertices
double objectMinY = 0;            // (Used to calculate the center of the object)
double objectMinZ = 0;            //
double objectMaxZ = 0;            //

double objectCenterX = 0;         //
double objectCenterY = 0;         // Define the center vertices of the drawn object
double objectCenterZ = 0;         //

double windowWidth = 600;         // Width of the window
double windowHeight = 400;        // Height of the window

int multipleViewports = 0;        // Flag used to determine if user presses 'V' or 'v' for multiple viewports

double viewportWidth = 300;       // Used to change viewport size (initialized to initial size)
double viewportHeight = 200;      // Used to change viewport size (initialized to initial size)
double viewportX = 120;           // Viewport location (bottom left corner)
double viewportY = 100;           // Viewport location (bottom left corner)
double borderMoveX = 0;           // Used to move the viewport by the specified value
double borderMoveY = 0;           // Used to move the viewport by the specified value

double perspectiveZFar = 6100;   // Z far value in perspective projection

double translateByX = 0;          //
double translateByY = 0;          // Position reset for gluLookAt() when displaying different elevations
double translateByZ = 0;          //

// The following variables are used to rotate the object with the mouse
enum
{
    UP = 1,        // Mouse release
    DOWN = 2,      // Mouse press
};

int mouseState = UP;              // Used to get mouse state
int oldX = 0;                     // Old/previous X coordinate of mouse
int oldY = 0;                     // Old/previous Y coordinate of mouse
double rotateX = 0;               // Degree to rotate object about X-axis
double rotateY = 0;               // Degree to rotate object about Y-axis

// Main method.
int main(int argc, char* argv[])
{
    // Controls for program.
    printf("Program controls:\n"
        "\tPress 'q' to quit the program.\n"
        "\tPress 'I' or 'i' to reset position.\n"
        "\tPress 'U' to translate object up.\n"
        "\tPress 'D' to translate object down.\n"
        "\tPress 'L' to translate object left.\n"
        "\tPress 'R' to translate object right.\n"
        "\tPress '+' to increase object size.\n"
        "\tPress '-' to decrease object size.\n"
        "\tPress 'X' to rotate about x-axis by positive 15 degrees.\n"
        "\tPress 'x' to rotate about x-axis by negative 15 degrees.\n"
        "\tPress 'Y' to rotate about y-axis by positive 15 degrees.\n"
        "\tPress 'y' to rotate about y-axis by negative 15 degrees.\n"
        "\tPress 'Z' to rotate about z-axis by positive 15 degrees.\n"
        "\tPress 'z' to rotate about z-axis by negative 15 degrees.\n\n"

        "\tPress 'u' to translate viewport up.\n"
        "\tPress 'd' to translate viewport down.\n"
        "\tPress 'l' to translate viewport left.\n"
        "\tPress 'r' to translate viewport right.\n"
        "\tPress 'E' to increase viewport size.\n"
        "\tPress 'e' to decrease viewport size.\n"
        "\tPress 'P' or 'p' to switch to a perspective projection.\n"
        "\tPress 'O' or 'o' to switch to a parallel orthographic projection: Front elevation.\n"
        "\tPress 'T' or 't' to switch to a parallel orthographic projection: Top elevation.\n"
        "\tPress 'S' or 's' to switch to a parallel orthographic projection: Side elevation.\n"
        "\tPress 'V' or 'v' to show object in 4 different viewports.\n"
        "\tPress 'F' or 'f' to move COP away from object.\n"
        "\tPress 'N' or 'n' to move COP towards object.\n\n"

        "\tClick and drag with the mouse to also rotate the object.\n\n");

    initializeCoordinateArray();        // Initialize the int array containing the jump commands and coordinate values.
    init_window(argc, argv);            // Initialize window and other parameters.
    other_init();                       // Other initializations.
    glutKeyboardFunc(keyboard);         // Handle keyboard presses.
    glutMouseFunc(mouse);               // Handle mouse clicking events.
    glutMotionFunc(mouseMotion);        // Handle mouse movement.
    glutDisplayFunc(display);           // Set display callback function.
    glutMainLoop();                     // Start the glut event loop.

    return 0;

} // End main method.

// Handles program controls.
void keyboard(unsigned char k, int x, int y)
{
    // Get 10% of object height and width
    double tenPercentOfHeight = objectHeight / 10.0;
    double tenPercentOfWidth = objectWidth / 10.0;

    // Get 10% of viewport height and width
    double tenPercentOfViewportHeight = viewportHeight / 10.0;
    double tenPercentOfViewportWidth = viewportWidth / 10.0;

    // Get the amount to move the Center of Projection
    double COPMovementFactor = (objectCenterZ - (-2100.0)) / 10.0;

     switch (k)
    {
    // Translations 
    case 'U':
        translate(0, tenPercentOfHeight, 0);
        break;
    case 'D':
        translate(0, -tenPercentOfHeight, 0);
        break;
    case 'R':
        translate(tenPercentOfWidth, 0, 0);
        break;
    case 'L':
        translate(-tenPercentOfWidth, 0, 0);
        break;

    // Viewport translations
    case 'u':
        translateViewport(0, tenPercentOfViewportHeight);
        break;
    case 'd':
        translateViewport(0, -tenPercentOfViewportHeight);
        break;
    case 'r':
        translateViewport(tenPercentOfViewportWidth, 0);
        break;
    case 'l':
        translateViewport(-tenPercentOfViewportWidth, 0);
        break;

    // Viewport scaling
    case 'E':
        scaleViewport(tenPercentOfViewportHeight);
        break;
    case 'e':
        scaleViewport(-tenPercentOfViewportHeight);
        break;

    // Change to perspective projection
    case 'P':
    case 'p':
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        gluPerspective(60.0, 1.0, 1.0, perspectiveZFar);

        glMatrixMode(GL_MODELVIEW);
        gluLookAt(0.0, 0.0, 2500.0,
                  0.0, 0.0, 0.0,
                  0.0, 1.0, 0.0);
        break;

    // Parallel orthographic projection: Front view
    case 'O':
    case 'o':
        translateByZ = 1000.0;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        glMatrixMode(GL_MODELVIEW);
        gluLookAt(-translateByX, -translateByY, translateByZ,
                            0.0,           0.0,          0.0,
                            0.0,           1.0,          0.0);
        // Flush values
        translateByY = 0.0;
        translateByX = 0.0;

        break;

    // Parallel orthographic projection: Top view
    case 'T':
    case 't':
        translateByY = 1000.0;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        glMatrixMode(GL_MODELVIEW);
        gluLookAt(-translateByX, translateByY, -translateByZ,
                            0.0,          0.0,          0.0,
                            0.0,          0.0,         -1.0);
        // Flush values
        translateByX = 0.0;
        translateByZ = 0.0;

        break;

    // Parallel orthographic projection: Side view
    case 'S':
    case 's':
        translateByX = -1000.0;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        glMatrixMode(GL_MODELVIEW);
        gluLookAt(translateByX, -translateByY,  -translateByZ,
                           0.0,           0.0,           0.0,
                           0.0,           1.0,           0.0);
        // Flush values
        translateByY = 0.0;
        translateByZ = 0.0;

        break;

    // Display object with multiple views in different Viewports
    case 'V':
    case 'v':
        // Flag. If multipleViewports != 0, then display the 4 viewports
        multipleViewports = 4;
        break;

    // Move COP away from object
        // Aka move object toward -z
    case 'F':
    case 'f':
        perspectiveZFar = perspectiveZFar + COPMovementFactor;

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        gluPerspective(60.0, 1.0, 1.0, perspectiveZFar);

        glMatrixMode(GL_MODELVIEW);
        translate(0, 0, -COPMovementFactor);
        break;

    // Move COP toward object
        // Aka move object toward +z
    case 'N':
    case 'n':
        glMatrixMode(GL_MODELVIEW);
        translate(0, 0, COPMovementFactor);
        break;

    // Rotations
    case 'x':
        rotate(-15, 1, 0, 0);
        break;
    case 'X':
        rotate(15, 1, 0, 0);
        break;
    case 'y':
        rotate(-15, 0, 1, 0);
        break;
    case 'Y':
        rotate(15, 0, 1, 0);
        break;
    case 'z':
        rotate(-15, 0, 0, 1);
        break;
    case 'Z':
        rotate(15, 0, 0, 1);
        break;

    // Scaling
    case '+':
        scale(1.11, 1.11, 1.11);
        break;
    case '-':
        scale(0.9, 0.9, 0.9);
        break;

    // Reset position
    case 'I':
    case 'i':
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        translateByX = 0;
        translateByY = 0;
        translateByZ = 0;
        break;

    // Quit program
    case 'Q':
    case 'q':
        exit(0);
    }

    glutPostRedisplay();
}

// Handles user mouse clicking (press and release).
void mouse(int button, int state, int x, int y)
{
    if (state == GLUT_DOWN)
    {
        switch (button)
        {
        case GLUT_LEFT_BUTTON:
            mouseState = DOWN;
            oldX = x;
            oldY = y;
            break;
        }
    }

    else if (state == GLUT_UP)
    {
        mouseState = UP;
    }
}

// Handles user mouse movement.
void mouseMotion(int x, int y)
{
    if (mouseState == DOWN)
    {
        rotateX = ((oldY - y) * 180.0) / 100.0;
        rotateY = ((oldX - x) * 180.0) / 100.0;

        glutPostRedisplay();
    }

    oldX = x;
    oldY = y;
}

// Initialize the array containing jump commands and coordinates to be drawn.
void initializeCoordinateArray()
{
    char inputFilename[100] = ""; // Pointer to input file name.

    printf("Input file name: \n");
    scanf("%s", &inputFilename);

    // Open file.
    FILE* inputFile = fopen(inputFilename, "r");
    if (inputFile == NULL)
    {
        printf("Error: File not found\n");
        exit(0);
    }

    char buffer[200];               // Buffer for reading a line in the file.
    int x = 0;                      // Holder for the first variable on the line (x coordinate).
    int y = 0;                      // Holder for the second variable on the line (y coordinate).
    int z = 0;                      // Holder for the third variable on the line (z coordinate).
    int sscanfResult = 0;           // Temporary variable to hold the number of integers sscanf finds (used to total the number of coordinates).
    int numberOfLines = 0;
    int numberOfCoordinates = 0;
    numberOfArrayItems = 0;     // Used to determine the array size including J or j for jump commands.

// Read the file line by line.
    while (fgets(buffer, sizeof(buffer), inputFile) != NULL)
    {
        // Flush the numbers (necessary for the line counter to work).
        x = 0;
        y = 0;
        z = 0;

        // Get the first 3 numbers.
        sscanfResult = sscanf_s(buffer, "%d %d %d", &x, &y, &z);
        numberOfCoordinates = numberOfCoordinates + sscanfResult;

        if (buffer[0] == 'J' || buffer[0] == 'j')
        {
            numberOfArrayItems++;
        }

        // Count the number of lines with coordinate values.
        if (sscanfResult != 0)
        {
            numberOfLines++;
            numberOfArrayItems = numberOfArrayItems + 3;
        }

        sscanfResult = 0;

    } // End while loop.

    fclose(inputFile);

    // Dynamically allocate array to the number of coordinates.
    coordinateArray = (int*)malloc(numberOfArrayItems * sizeof(int));

    // Error checking for memory allocation.
    if (coordinateArray == NULL)
    {
        printf("Memory for array not allocated.");
        exit(0);
    }

    // Open file again for reading from the beginning.
    FILE* inputFile_2 = fopen(inputFilename, "r");
    if (inputFile_2 == NULL)
    {
        printf("File not found\n");
        exit(0);
    }

    int i = 0;                      // Counter variable.
    char buffer_2[100];             // Buffer for reading 1 line.
    int sscanfResult_2 = 0;         // Used like above.

    // Store all coordinates into array.
    while (fgets(buffer_2, sizeof(buffer_2), inputFile_2) != NULL)
    {
        // Get the first 3 numbers.
        sscanfResult_2 = sscanf_s(buffer_2, "%d %d %d", &coordinateArray[i], &coordinateArray[i + 1], &coordinateArray[i + 2]);

        if (buffer_2[0] == 'J' || buffer_2[0] == 'j')
        {
            coordinateArray[i] = jumpKey;
            i++;
        }

        if (sscanfResult_2 != 0)
        {
            i = i + 3;
        }

        sscanfResult_2 = 0;

    } // End while loop.

    // Close file.
    fclose(inputFile_2);

}

// Refresh callback function
void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);

    // Draw 1 viewport
    if (multipleViewports == 0)
    {
        glViewport(viewportX, viewportY, viewportWidth, viewportHeight);

        // Draw viewport border
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();

        glColor3f(1.0f, 0.0f, 0.0f);
        glBegin(GL_LINE_LOOP);
        glVertex3d(-0.999, -0.999, -1);
        glVertex3d(1, -0.999, -1);
        glVertex3d(1, 1, -1);
        glVertex3d(-0.999, 1, -1);
        glEnd();
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopMatrix();

        drawObject();
    }


    // Draw 4 viewports
    else
    {
        // Viewport 1: top left
        // Parallel orthographic: Top View
        glViewport(0, windowHeight / 2, windowWidth / 2, windowHeight / 2);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        rotate(90, 1, 0, 0);

        drawObject();

        // Viewport 2: Top Right
        // Perspective Projection
        glViewport(windowWidth / 2, windowHeight / 2, windowWidth / 2, windowHeight / 2);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(60.0, 1, 1, 4201.0);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        translate(0, 0, -2100);

        drawObject();

        // Viewport 3: Bottom Right
        // Parallel orthographic: Side View
        glViewport(windowWidth / 2, 0, windowWidth / 2, windowHeight / 2);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        rotate(90, 0, 1, 0);

        drawObject();

        // Viewport 4: Bottom Left
        // Parallel orthographic: Front View
        glViewport(0, 0, windowWidth / 2, windowHeight / 2);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        drawObject();

    }
    
    // Mouse rotation
    rotate(rotateX, 1, 0, 0);
    rotate(rotateY, 0, 1, 0);

    // Forces execution of all presently buffered commands
    glFlush();
}

// GLUT Window Initialization
void init_window(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB);       // Sets the initial display mode
    glutInitWindowSize(600, 400);        // Set the initial size and position of the window
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Project 4");       // Create the window

}

// Handles other initializations
void other_init()
{
    glClearColor(1.0, 1.0, 1.0, 1.0);             // White background
                                                  
    // View setup                                 
    glMatrixMode(GL_PROJECTION);                  
    glLoadIdentity();                             
    glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);  // Sets up the clipping planes for the window.
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}

// Move the viewport by the specified amount
void translateViewport(double moveX, double moveY)
{
    // Move right
    if (moveX > 0)
    {
        if (viewportX < 300)
        {
            borderMoveX = moveX;

            viewportX = viewportX + moveX;
            glViewport(viewportX, viewportY, viewportWidth, viewportHeight);

            translate(-moveX * 8, 0, 0);
        }
    }

    // Move left
    else if (moveX < 0)
    {
        if (viewportX > 0)
        {
            borderMoveX = moveX;

            viewportX = viewportX + moveX;
            glViewport(viewportX, viewportY, viewportWidth, viewportHeight);

            translate(-moveX * 8, 0, 0);
        }
    }

    // Move up
    if (moveY > 0)
    {
        if (viewportY < 200)
        {
            borderMoveY = moveY;

            viewportY = viewportY + moveY;
            glViewport(viewportX, viewportY, viewportWidth, viewportHeight);

            translate(0, -moveY * 12, 0);
        }
    }

    // Move down
    else if (moveY < 0)
    {
        if (viewportY > 0)
        {
            borderMoveY = moveY;

            viewportY = viewportY + moveY;
            glViewport(viewportX, viewportY, viewportWidth, viewportHeight);

            translate(0, -moveY * 12, 0);
        }
    }
}

// Make the viewport bigger or smaller 
void scaleViewport(double scaleFactor)
{
    // Maintain aspect ratio
    double currentViewportWidth = viewportWidth;
    double currentViewportHeight = viewportHeight;
    double halfOfScaleFactor = (scaleFactor / 2.0);

    // Increase viewport
    if (scaleFactor > 0)
    {
        viewportWidth = viewportWidth + scaleFactor;
        viewportHeight = (currentViewportHeight / currentViewportWidth) * (currentViewportWidth + scaleFactor);
        viewportX = viewportX - halfOfScaleFactor;
        viewportY = viewportY - halfOfScaleFactor;

        scale(0.925, 0.925, 0.925);
    }
    
    // Decrease viewport
    else if (scaleFactor < 0)
    {
        if (viewportHeight >= 10)
        {
            viewportWidth = viewportWidth + scaleFactor;
            viewportHeight = (currentViewportHeight / currentViewportWidth) * (currentViewportWidth + scaleFactor);
            viewportX = viewportX - halfOfScaleFactor;
            viewportY = viewportY - halfOfScaleFactor;
            scale(1.085, 1.085, 1.085);
        }
    }
}

// Translate by multiplying the current matrix by a translation matrix
void translate(double x, double y, double z)
{
    // Values inserted column-wise
    double translationMatrix[16] = { 1, 0, 0, 0,
                                    0, 1, 0, 0,
                                    0, 0, 1, 0,
                                    x, y, z, 1 };

    // Multiply current matrix (MODEL_VIEW) by the translation matrix
    glMultMatrixd(translationMatrix);

}

// Rotate by multiplying the current matrix by a rotation matrix
void rotate(double angle, double x, double y, double z)
{
    objectCenterX = (objectMaxX + objectMinX) / 2;
    objectCenterY = (objectMaxY + objectMinY) / 2;
    objectCenterZ = (objectMaxZ + objectMinZ) / 2;

    translate(objectCenterX, objectCenterY, objectCenterZ);

    // cos and sin use radians, so convert degrees to radians
    double degreeToRadian = angle;
    degreeToRadian = (degreeToRadian * (M_PI / 180.0));

    double c = cos(degreeToRadian);
    double s = sin(degreeToRadian);

    // Elements to go into the rotation matrix
    // Written separately for clarity with formulas
    double m0 = ((x * x) * (1 - c) + c);
    double m1 = ((y * x) * (1 - c) + (z * s));
    double m2 = ((x * z) * (1 - c) - (y * s));
    double m3 = 0.0;

    double m4 = ((x * y) * (1 - c) - (z * s));
    double m5 = ((y * y) * (1 - c) + c);
    double m6 = ((y * z) * (1 - c) + (x * s));
    double m7 = 0.0;

    double m8 = ((x * z) * (1 - c) + (y * s));
    double m9 = ((y * z) * (1 - c) - (x * s));
    double m10 = ((z * z) * (1 - c) + c);
    double m11 = 0.0;

    double m12 = 0.0;
    double m13 = 0.0;
    double m14 = 0.0;
    double m15 = 1.0;

    // Values inserted column-wise
    double rotationMatrix[16] = { m0,  m1,  m2,  m3,
                                  m4,  m5,  m6,  m7,
                                  m8,  m9,  m10, m11,
                                  m12, m13, m14, m15 };

    // Multiply MODEL_VIEW matrix by the rotation matrix
    glMultMatrixd(rotationMatrix);

    translate(-objectCenterX, -objectCenterY, -objectCenterZ);
}

// Scale by multiplying the current matrix by a scale matrix
void scale(double x, double y, double z)
{
    objectCenterX = (objectMaxX + objectMinX) / 2;
    objectCenterY = (objectMaxY + objectMinY) / 2;
    objectCenterZ = (objectMaxZ + objectMinZ) / 2;

    translate(objectCenterX, objectCenterY, objectCenterZ);

    // Values inserted column-wise
    double scalingMatrix[16] = { x, 0, 0, 0,
                                 0, y, 0, 0,
                                 0, 0, z, 0,
                                 0, 0, 0, 1 };
    
    // Multiply MODEL_VIEW matrix by scaling matrix
    glMultMatrixd(scalingMatrix);

    translate(-objectCenterX, -objectCenterY, -objectCenterZ);

}

// Draw the object specified by the file of vertices
void drawObject()
{
    // Set color to black.
    glColor3f(0.0f, 0.0f, 0.0f);
    glLineWidth(1);

    glBegin(GL_LINE_STRIP);

    // Loop through array of coordinates and draw them
    // Jump (glEnd) whenever there is a jump command
    for (int i = 0; i < numberOfArrayItems;)
    {
        // If a J for jump is present, jump.
        if (coordinateArray[i] == jumpKey)
        {
            glEnd();
            glBegin(GL_LINE_STRIP);
            i++;
        }

        else
        {
            // Instantiate the max height and width of object
            // This is used in the translate() method
            if (coordinateArray[i] > objectWidth)
            {
                objectWidth = coordinateArray[i];
            }

            if (coordinateArray[i + 1] > objectHeight)
            {
                objectHeight = coordinateArray[i + 1];
            }

            // Get object dimensions for calculating center
            if (coordinateArray[i] > objectMaxX)
            {
                objectMaxX = coordinateArray[i];
            }

            if (coordinateArray[i] < objectMinX)
            {
                objectMinX = coordinateArray[i];
            }

            if (coordinateArray[i + 1] > objectMaxY)
            {
                objectMaxY = coordinateArray[i + 1];
            }

            if (coordinateArray[i + 1] < objectMinY)
            {
                objectMinY = coordinateArray[i + 1];
            }

            if (coordinateArray[i + 2] > objectMaxZ)
            {
                objectMaxZ = coordinateArray[i + 2];
            }

            if (coordinateArray[i + 2] < objectMinZ)
            {
                objectMinZ = coordinateArray[i + 2];
            }

            // Draw vertices
            glVertex3i(coordinateArray[i], coordinateArray[i + 1], coordinateArray[i + 2]);
            i = i + 3;
        }

    }

    glEnd();
}