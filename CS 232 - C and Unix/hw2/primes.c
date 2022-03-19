/* File: primes.c */
/* Author: Britton Wolfe */
/* Date: July 16, 2009 */
/* This program outputs all the primes in the range given */
/* by the command line arguments */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main(int argc, const char** argv){

    int lowerBound, upperBound;

    //Variables for for loop
    int i, count, primeNumber;

    if(argc != 3){
        fprintf(stderr, "USAGE: %s lowerBound upperBound\n", argv[0]);
        return -1;
    }
  
    lowerBound = atoi(argv[1]);
    upperBound = atoi(argv[2]);
  
    if(lowerBound < 1 || upperBound < 1){
        fprintf(stderr, "ERROR: both the lowerBound (%d) and the upperBound (%d)"
	          " must be positive.\n", lowerBound, upperBound);
        return -2;
    }
  
    {
        /* TODO: fill in the code that outputs the prime numbers */
        /*   in the range [lowerBound,upperBound] in ascending order */
        for(primeNumber = lowerBound; primeNumber <= upperBound; primeNumber++)
        {
            count = 0;
            for (i = 2; i <= primeNumber/2; i++)
            {
                if(primeNumber%i == 0)
                {
                    count++;
                    break;
                }
                
            } //End inner for loop

            if(count == 0 && primeNumber != 1)
            {
                printf(" %d \n", primeNumber);
            }

        } //End outer for loop

    }

    return 0;
}
