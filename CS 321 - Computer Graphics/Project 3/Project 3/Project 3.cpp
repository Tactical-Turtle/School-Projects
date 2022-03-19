/*
NAME:		Montana Singer
PROJECT:	Project 3
COURSE:	    CS 321
INSTRUCTOR:	Beomjin Kim
DUE DATE:	October 13, 2020
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

int* coordinateArray;           // Global variable holding the array containing jump commands and coordinates.
int numberOfArrayItems = 0;     // Global variable holds the size of the dynamically allocated array.
const int jumpKey = 3000;       // Used to identify when a J or jump occurs in the data file.
double objectHeight = 0;        // Used in the translate method to move by a percentage of the height
double objectWidth = 0;         // Used in the translate method to move by a percentage of the width

// The following variables are used to rotate the object with the mouse
enum 
{
    UP = 1,        // Mouse release
    DOWN = 2,      // Mouse press
};

int mouseState = UP;    // Used to get mouse state
int oldX = 0;           // Old/previous X coordinate of mouse
int oldY = 0;           // Old/previous Y coordinate of mouse
double rotateX = 0;     // Degree to rotate object about X-axis
double rotateY = 0;     // Degree to rotate object about Y-axis

// Main method.
int main(int argc, char* argv[])
{
    // Controls for program.
    printf("Program controls:\n"
        "\tPress 'q' to quit the program.\n"
        "\tPress 'I' or 'i' to reset position.\n"
        "\tPress 'U' or 'u' to translate up.\n"
        "\tPress 'D' or 'd' to translate down.\n"
        "\tPress 'L' or 'l' to translate left.\n"
        "\tPress 'R' or 'r' to translate right.\n"
        "\tPress '+' to increase size.\n"
        "\tPress '-' to decrease size.\n"
        "\tPress 'X' to rotate about x-axis by positive 15 degrees.\n"
        "\tPress 'x' to rotate about x-axis by negative 15 degrees.\n"
        "\tPress 'Y' to rotate about y-axis by positive 15 degrees.\n"
        "\tPress 'y' to rotate about y-axis by negative 15 degrees.\n"
        "\tPress 'Z' to rotate about z-axis by positive 15 degrees.\n"
        "\tPress 'z' to rotate about z-axis by negative 15 degrees.\n\n"
        "\tClick and drag with the mouse to also rotate the object.\n\n");

    // Initialize the int array containing the jump commands and coordinate values.
    initializeCoordinateArray();

    // Initialize window and other parameters.
    init_window(argc, argv);

    // Other initializations.
    other_init();

    // Handle keyboard presses.
    glutKeyboardFunc(keyboard);

    // Handle mouse clicking events.
    glutMouseFunc(mouse);

    // Handle mouse movement.
    glutMotionFunc(mouseMotion);

    // Set display callback function.
    glutDisplayFunc(display);

    // Start the glut event loop.
    glutMainLoop();

    return 0;

} // End main method.

// Handles program controls.
void keyboard(unsigned char k, int x, int y)
{
    // Get 10% of object height and width
    double tenPercentOfHeight = objectHeight / 10.0;
    double tenPercentOfWidth = objectWidth / 10.0;

    switch (k)
    {
    // Translations 
    case 'U':
    case 'u':
        translate(0, tenPercentOfHeight, 0);
        break;
    case 'D':
    case 'd':
        translate(0, -tenPercentOfHeight, 0);
        break;
    case 'R':
    case 'r':
        translate(tenPercentOfWidth, 0, 0);
        break;
    case 'L':
    case 'l':
        translate(-tenPercentOfWidth, 0, 0);
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
        scale(1.1, 1.1, 1.1);
        break;
    case '-':
        scale(0.9, 0.9, 0.9);
        break;

    // Reset position
    case 'I':
    case 'i':
        glLoadIdentity();
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

    // Set color to blue.
    glColor3f(0.0f, 0.0f, 1.0f);
    glLineWidth(3);

    // Draw coordinate axes
    glBegin(GL_LINES);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(1500.0f, 0.0f, 0.0f);

        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 1500.0f, 0.0f);

        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 1500.0f);
    glEnd();

    // Draw axis labels
    glRasterPos3f(1500, 0, 0);
    glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, 'X');
    glRasterPos3f(0, 1500, 0);
    glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, 'Y');
    glRasterPos3f(0, 0, 1500);
    glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, 'Z');


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

            // Draw vertices
            glVertex3i(coordinateArray[i], coordinateArray[i + 1], coordinateArray[i + 2]);
            i = i + 3;
        }

    }

    glEnd();

    // Mouse rotation
    rotate(rotateX, 1, 0, 0);
    rotate(rotateY, 0, 1, 0);

    // Forces execution of all presently buffered commands
    glFlush();
}

// GLUT Window Initialization
void init_window(int argc, char** argv)
{
    // GLUT initialization
    glutInit(&argc, argv);

    // Sets the initial display mode
    glutInitDisplayMode(GLUT_RGB);

    // Set the initial size and position of the window
    glutInitWindowSize(500, 500);
    glutInitWindowPosition(0, 0);

    // Create the window
    glutCreateWindow("Project 3");
}

// Handles other initializations
void other_init()
{
    // White background
    glClearColor(1.0, 1.0, 1.0, 1.0);

    // View setup
    glMatrixMode(GL_PROJECTION);

    // Reset identity matrix
    glLoadIdentity();

    // Sets up the clipping planes for the window.
    glOrtho(-2100, 2100, -2100, 2100, -2100, 2100);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}

// Translate by multiplying the current matrix by a translation matrix
void translate(double x, double y, double z)
{
    // Values inserted column-wise
    double translationMatrix[16] = {1, 0, 0, 0, 
                                    0, 1, 0, 0, 
                                    0, 0, 1, 0,
                                    x, y, z, 1};

    // Multiply current matrix (MODEL_VIEW) by the translation matrix
    glMultMatrixd(translationMatrix);

}

// Rotate by multiplying the current matrix by a rotation matrix
void rotate(double angle, double x, double y, double z)
{
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
    
    double m8  = ((x * z) * (1 - c) + (y * s));
    double m9  = ((y * z) * (1 - c) - (x * s));
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
}

// Scale by multiplying the current matrix by a scale matrix
void scale(double x, double y, double z)
{
    // Values inserted column-wise
    double scalingMatrix[16] = { x, 0, 0, 0,
                                 0, y, 0, 0,
                                 0, 0, z, 0,
                                 0, 0, 0, 1 };

    // Multiply MODEL_VIEW matrix by scaling matrix
    glMultMatrixd(scalingMatrix);

}