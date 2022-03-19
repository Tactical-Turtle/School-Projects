/*
NAME:		Montana Singer
PROJECT:	Project 2B
COURSE:	    CS 321
INSTRUCTOR:	Beomjin Kim
DUE DATE:	September 22, 2020
*/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <GL/freeglut.h>

// Function prototypes
void keyboard(unsigned char k, int x, int y);
void initializeCoordinateArray();
void initializeRandomCoordinateArray();
void display(void);
void init_window(int argc, char** argv);
void other_init();

int* coordinateArray;           // Global variable holding the array containing jump commands and coordinates.
int* randomCoordinateArray;     // Global variable holding the source coordinates for the morphing animation.
int numberOfArrayItems = 0;     // Global variable holds the size of the dynamically allocated array. Necessary for drawing the objects with a for loop.
const int jumpKey = 3000;       // Constant used to identify when a J or jump occurs in the data file. Number is outside of coordinate range.
int inputChar;                  // Used to identify which display is selected


// Main method.
int main(int argc, char* argv[])
{
    // Controls for program.
    printf("Program controls:\n"
        "\tPress 'q' to quit the program.\n"
        "\tEnter 'e' for morph animation + transformations.\n"
        "\t\tNote: decrease sleep amount to increase transformation speed.\n"
        "\t\tPress 'd' to move right.\n"
        "\t\tPress 'a' to move left.\n"
        "\t\tPress 'w' to move up.\n"
        "\t\tPress 's' to move down.\n"
        "\t\tPress 'r' to rotate 10 degrees right.\n"
        "\t\tPress 't' to rotate 10 degrees left.\n"
        "\t\tPress 'f' to increase size.\n"
        "\t\tPress 'g' to decrease size.\n"
        "\tEnter 'p' for special display.\n\n");

    // Get user input
    printf("Enter a character\n");
    inputChar = getchar();
    if ((inputChar != 'q') && (inputChar != 'e') && (inputChar != 'p'))
    {
        printf("Non-valid character entered. Exiting");
        exit(0);
    }

    else if (inputChar == 'q')
    {
        printf("Exiting...");
        exit(0);
    }

    // Initialize the int array containing the jump commands and coordinate values.
    initializeCoordinateArray();

    initializeRandomCoordinateArray();

    // Initialize window and other parameters.
    init_window(argc, argv);

    // Other initializations.
    other_init();

    // Handle keyboard presses.
    glutKeyboardFunc(keyboard);

    // Set display callback function.
    glutDisplayFunc(display);

    // Start the glut event loop.
    glutMainLoop();

    free(coordinateArray);
    free(randomCoordinateArray);

    return 0;

} // End main method.

// When the user hits 'q' on their keyboard, quit program.
void keyboard(unsigned char k, int x, int y)
{
    switch (k)
    {
        case 'd':
            glTranslatef(200, 0, 0);
            break;
        case 'a':
            glTranslatef(-200, 0, 0);
            break;
        case 'w':
            glTranslatef(0, 200, 0);
            break;
        case 's':
            glTranslatef(0, -200, 0);
            break;
        case 'r':
            glRotatef(-10, 0, 0, 1);
            break;
        case 't':
            glRotatef(10, 0, 0, 1);
            break;
        case 'f':
            glScalef(1.2, 1.2, 1.2);
            break;
        case 'g':
            glScalef(0.8, 0.8, 0.8);
            break;
        case 'q':
            exit(0);
    }
        
    glutPostRedisplay();

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
        printf("File not found\n");
        exit(0);
    }

    char buffer[200];           // Buffer for reading a line in the file.
    int x = 0;                  // Holder for the first variable on the line (x coordinate).
    int y = 0;                  // Holder for the second variable on the line (y coordinate).
    int z = 0;                  // Holder for the third variable on the line (z coordinate).
    int sscanfResult = 0;       // Temporary variable to hold the number of integers sscanf finds (used to total the number of coordinates).
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

    int i = 0;                  // Counter variable.
    char buffer_2[100];         // Buffer for reading 1 line.
    int sscanfResult_2 = 0;     // Used like above.

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

// Initialize the array containing random coordinates.
void initializeRandomCoordinateArray()
{
    int upperLimit = 2026;      // Upper limit for random coordinate generation.
    int lowerLimit = -2026;     // Lower limit for random coordinate generation.
    int randomNumber = 0;       // Stores random number.

    // Dynamically allocate array to store the random coordinates.
    randomCoordinateArray = (int*)malloc(numberOfArrayItems * sizeof(int));

    // Error checking for memory allocation.
    if (randomCoordinateArray == NULL)
    {
        printf("Memory for random coordinate array not allocated.");
        exit(0);
    }

    // Copy coordinate array to random coordinate array.
    for (int i = 0; i < numberOfArrayItems; i++)
    {
        randomCoordinateArray[i] = coordinateArray[i];
    }

    // Override current coordinate values with random values
    for (int i = 0; i < numberOfArrayItems; i++)
    {
        // If coordinate is not a jump command (3000), override with random coordinate.
        if (randomCoordinateArray[i] != jumpKey)
        {
            randomNumber = (rand() % (upperLimit - lowerLimit + 1)) + lowerLimit;
            randomCoordinateArray[i] = randomNumber;
        }

    }
}

// Refresh callback function.
void display(void) 
{
    /*************************************************************************
    *      Morphing animation + transformations.
    *************************************************************************/
    if (inputChar == 'e')
    {
        const int intermediateSteps = 10;                                // Holds the number of steps between source and target point.
        const int sleepTime = 100;                                       // Amount of time between displaying frames (in milliseconds). Decrease number to increase response time when transforming.
        int* constX = (int*)malloc(numberOfArrayItems * sizeof(int));    // Constant rate of change for X values between source and target point for each vertex.
        int* constY = (int*)malloc(numberOfArrayItems * sizeof(int));    // Constant rate of change for Y values between source and target point for each vertex.

        // Memory allocation error checking
        if (constX == NULL)
        {
            printf("Memory for array constX not allocated.");
            exit(0);
        }

        if (constY == NULL)
        {
            printf("Memory for array constY not allocated.");
            exit(0);
        }

        // Initialize rate of change for each vertex
        for (int i = 0; i < numberOfArrayItems; i++)
        {
            constX[i] = ((randomCoordinateArray[i] - coordinateArray[i]) / intermediateSteps);
            constY[i] = ((randomCoordinateArray[i + 1] - coordinateArray[i + 1]) / intermediateSteps);
        }

        glClear(GL_COLOR_BUFFER_BIT);

        // Set color to blue
        glColor3f(0.0f, 0.0f, 1.0f);

        // Display initial frame
        glBegin(GL_LINE_STRIP);

        // Display initial frame.
        for (int i = 0; i < numberOfArrayItems;)
        {
            // If a J for jump is present, jump.
            if (randomCoordinateArray[i] == jumpKey)
            {
                glEnd();
                glBegin(GL_LINE_STRIP);
                i++;
            }

            else
            {
                glVertex2i(randomCoordinateArray[i], randomCoordinateArray[i + 1]);
                i = i + 3;
            }

        }

        glEnd();
        glFlush();
        Sleep(sleepTime);
        glClear(GL_COLOR_BUFFER_BIT);


        // Loop to display the rest of the frames
        for (int i = 0; i < intermediateSteps; i++)
        {
            glBegin(GL_LINE_STRIP);

            for (int j = 0; j < numberOfArrayItems;)
            {
                if (randomCoordinateArray[j] == jumpKey)
                {
                    glEnd();
                    glBegin(GL_LINE_STRIP);
                    j++;
                }

                else
                {
                    randomCoordinateArray[j] = randomCoordinateArray[j] - (constX[j]);
                    randomCoordinateArray[j + 1] = randomCoordinateArray[j + 1] - (constY[j]);

                    glVertex2i(randomCoordinateArray[j], randomCoordinateArray[j + 1]);
                    j = j + 3;
                }

            }

            glEnd();
            glFlush();
            Sleep(sleepTime);

            // Don't clear the screen on the last iteration.
            if (i != (intermediateSteps - 1))
            {
                glClear(GL_COLOR_BUFFER_BIT);
            }

        }

        // Forces execution of all presently buffered commands.
        glFlush();

        // Free both arrays.
        free(constX);
        free(constY);

    } //End morphing animation + transformations



     /************************************************************************* 
     *      Display object moving from left to right. 
     *      When it hits the edge of the screen, move to other side.
     *************************************************************************/
    else if (inputChar == 'p')
    {
        const int sleepTimer = 25;          // Milliseconds to pause between displaying.

        glClear(GL_COLOR_BUFFER_BIT);

        // Set color to blue.
        glColor3f(0.0f, 0.0f, 1.0f);

        // Display initial frame.
        glBegin(GL_LINE_STRIP);

        // Display object initially.
        for (int i = 0; i < numberOfArrayItems;)
        {
            // If a J for jump is present, jump.
            if (randomCoordinateArray[i] == jumpKey)
            {
                glEnd();
                glBegin(GL_LINE_STRIP);
                i++;
            }

            else
            {
                glVertex2i(coordinateArray[i], coordinateArray[i + 1]);
                i = i + 3;
            }
            
        }

        glEnd();
        glFlush();
        Sleep(1000);
        glClear(GL_COLOR_BUFFER_BIT);


        int iteration = 0;          //Current iteration or frame.
        int maxIterations = 406;    // Max iterations or frames to display.
        while (iteration < maxIterations)
        {
            for (int i = 0; i < numberOfArrayItems;)
            {
                if (randomCoordinateArray[i] == jumpKey)
                {
                    glEnd();
                    glBegin(GL_LINE_STRIP);
                    i++;
                }

                else
                {
                    // Move along x axis.
                    coordinateArray[i] = coordinateArray[i] + 20;
                    
                    // If reaches end of screen, go to other side
                    if (coordinateArray[i] >= 2026)
                    {
                        coordinateArray[i] = coordinateArray[i] * -1;
                    }

                    glVertex2i(coordinateArray[i], coordinateArray[i + 1]);
                    i = i + 3;
                }

            }

            glEnd();
            glFlush();
            Sleep(sleepTimer);

            // Don't clear on last iteration
            if (iteration != (maxIterations - 1));
            {
                glClear(GL_COLOR_BUFFER_BIT);
            }

            iteration++;
        }

        //glFlush();
    }
}

// GLUT Window Initialization.
void init_window(int argc, char** argv) 
{
    // GLUT initialization.
    glutInit(&argc, argv);

    // Sets the initial display mode.
    glutInitDisplayMode(GLUT_RGB);

    // Set the initial size and position of the window.
    glutInitWindowSize(500, 400);
    glutInitWindowPosition(0, 0);

    // Create the window
    glutCreateWindow("Project 2B");
}

// Other initializations.
void other_init() 
{

    // White background.
    glClearColor(1.0, 1.0, 1.0, 1.0);

    // View setup.
    glMatrixMode(GL_PROJECTION);

    // Reset identity matrix.
    glLoadIdentity();

    // Sets up the clipping planes for the window.
    glOrtho(-2100, 2100, -2100, 2100, -1, 1);

    glMatrixMode(GL_MODELVIEW);

    // Loads the identity matrix.
    glLoadIdentity();
}