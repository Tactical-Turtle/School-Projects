/*
NAME:		Montana Singer
PROJECT:	Project 1
COURSE:	    CS 321
INSTRUCTOR:	Beomjin Kim
DUE DATE:	September 3, 2020 
*/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>


//
//Main method contains the entire program.
//
int main(int argc, char* argv[])
{
    char inputFilename[100] = ""; //Pointer to input file name.

    printf("Input file name: \n");
    scanf("%s", &inputFilename);

    //Open file.
    FILE* inputFile = fopen(inputFilename, "r");
    if (inputFile == NULL)
    {
        printf("File not found\n");
        return 2;
    }

    char buffer[200];       //Buffer for reading a line in the file.
    int x = 0;              //Holder for the first variable on the line (x coordinate).
    int y = 0;              //Holder for the second variable on the line (y coordinate).
    int z = 0;              //Holder for the third variable on the line (z coordinate).
    int sscanfResult = 0;   //Temporary variable to hold the number of integers sscanf finds (used to total the number of coordinates).
    int numberOfLines = 0;
    int numberOfCoordinates = 0;

    //Read the file line by line.
    while (fgets(buffer, sizeof(buffer), inputFile) != NULL)
    {
        //Flush the numbers (necessary for the line counter to work).
        x = 0;
        y = 0;
        z = 0;

        //Get the first 3 numbers.
        sscanfResult = sscanf_s(buffer, "%d %d %d", &x, &y, &z);
        numberOfCoordinates = numberOfCoordinates + sscanfResult;

        //Count the number of lines with coordinate values.
        if (sscanfResult != 0)
        {
            numberOfLines++;
        }

        sscanfResult = 0;

    } //End while loop.

    fclose(inputFile);

    //Dynamically allocate array to the number of coordinates.
    int* coordinateArray;
    coordinateArray = (int*)malloc(numberOfCoordinates * sizeof(int));

    //Error checking for memory allocation.
    if (coordinateArray == NULL)
    {
        printf("Memory for array not allocated.");
        return 3;
    }
    
    //Open file again for reading from the beginning.
    FILE* inputFile_2 = fopen(inputFilename, "r");
    if (inputFile_2 == NULL)
    {
        printf("File not found\n");
        return 4;
    }

    int i = 0;                  //Counter variable.
    char buffer_2[100];         //Buffer for reading 1 line.
    int sscanfResult_2 = 0;     //Used like above

    //Store all coordinates into array.
    while (fgets(buffer_2, sizeof(buffer_2), inputFile_2) != NULL)
    {
        //Get the first 3 numbers.
        sscanfResult_2 = sscanf_s(buffer_2, "%d %d %d", &coordinateArray[i], &coordinateArray[i+1], &coordinateArray[i+2]);
        
        if (sscanfResult_2 != 0)
        {
            i = i + 3;
        }

        sscanfResult_2 = 0;

    } //End while loop.

    fclose(inputFile_2);

    //Get input file name and add different file extension (.out).
    char* outputFileName;
    outputFileName = strtok(inputFilename, ".");
    strcat(outputFileName, ".out");

    //Create output file.
    FILE* outputFile;
    outputFile = fopen(outputFileName, "w");

    if (outputFile == NULL)
    {
        printf("Unable to create file.\n");
        return 5;
    }

    double reverseX = 0;         //
    double reverseY = 0;         //Hold values of coordinates (going in reverse).
    double reverseZ = 0;         //

    double* meanX = &reverseX;   //
    double* meanY = &reverseY;   //Assign pointers.
    double* meanZ = &reverseZ;   //

    //Print the array backwards to the file.
    for (int i = numberOfCoordinates - 1; i > 0;)
    {
        *meanX = *meanX + coordinateArray[i-2];
        *meanY = *meanY + coordinateArray[i-1];
        *meanZ = *meanZ + coordinateArray[i];

        fprintf(outputFile, "%5d.0 %5d.0 %5d.0\n", coordinateArray[i-2], coordinateArray[i-1], coordinateArray[i]);
        i = i - 3;
    }

    //Print mean values to file.
    fprintf(outputFile, "Mean X-Coordinate =  %6.1f\n", (*meanX / numberOfLines));
    fprintf(outputFile, "Mean Y-Coordinate =  %6.1f\n", (*meanY / numberOfLines));
    fprintf(outputFile, "Mean Z-Coordinate =  %6.1f\n", (*meanZ / numberOfLines));

    //Free memory
    free(coordinateArray);

} //End main method.

