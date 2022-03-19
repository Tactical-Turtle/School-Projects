/*
NAME:		Montana Singer
PROJECT:	Project 2A
COURSE:	    CS 321
INSTRUCTOR:	Beomjin Kim
DUE DATE:	September 22, 2020
*/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <GL/freeglut.h>

// Function prototypes.
void keyboard(unsigned char k, int x, int y);
void initializeCoordinateArray();
void display(void);
void init_window(int argc, char** argv);
void other_init();

int* coordinateArray;           // Global variable holding the array containing jump commands and coordinates.
int numberOfArrayItems = 0;     // Global variable holds the size of the dynamically allocated array.
const int jumpKey = 3000;       // Used to identify when a J or jump occurs in the data file.

// Main method.
int main(int argc, char* argv[])
{
    // Controls for program.
    printf("Program controls:\n"
           "    Press 'q' to quit the program.\n\n");
    
    // Initialize the int array containing the jump commands and coordinate values.
    initializeCoordinateArray();

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

    return 0;

} // End main method.

// When the user hits 'q' on their keyboard, quit program.
void keyboard(unsigned char k, int x, int y)
{
    switch (k)
    {
        case 'q':
            exit(0);
    }
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
              glVertex2i(coordinateArray[i], coordinateArray[i + 1]);
              i = i + 3;
          }
    
      }
    
      glEnd();
    
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
    glutInitWindowSize(500, 400);
    glutInitWindowPosition(0, 0);

    // Create the window
    glutCreateWindow("Project 2A");
}

// This function handles some other initializations
void other_init() 
{
    // White background
    glClearColor(1.0, 1.0, 1.0, 1.0);

    // View setup
    glMatrixMode(GL_PROJECTION);

    // Reset identity matrix
    glLoadIdentity();

    // Sets up the clipping planes for the window.
    glOrtho(-2100, 2100, -2100, 2100, -1, 1);

    glMatrixMode(GL_MODELVIEW);

    // Loads the identity matrix
    glLoadIdentity();
}