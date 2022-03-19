#include "index.h"
#include "scoring.h"
#include <math.h>
#include <string.h>

int targetOccursNumTimes(page* pageToCheck, char* target)
{
	int targetLength, index;
	tnode* targetTrie;
	tnode* cursor;
	char buffer[MAX_WORDSIZE];

	targetLength = strlen(target);
	targetTrie = pageToCheck->trie;
	cursor = targetTrie;
	index = 0;

	for(index = 0; index < MAX_WORDSIZE; index++)
	{
		buffer[index] = '\0';
	}

	index = 0;

	while(cursor->links[target[index] - 'a'] != NULL && index < targetLength)
	{
		buffer[index] = target[index];
		cursor = cursor->links[target[index] - 'a'];
		index++;
	}

	if(!strcmp(buffer,target))
	{
		return cursor->frequency;
	}

	return 0;
}

//Returns the number of docs that the target occurs in
int targetOccursInNumDocs(pageIndex* indexedPages, char* target)
{
	int sum, numDocs, index;

	sum = 0;
	numDocs = indexedPages->numPages;
	index = 0;

	for(index = 0; index<numDocs; index++)
	{
		if(targetOccursNumTimes(indexedPages->pages[index], target))
		{
			sum++;
		}
	}

	return sum;
}

//Compute the TF-IDF value
double computeTFIDF(double tf, double idf)
{
	return tf * idf;
}

// Computes the IDF
double computeIDF(int numIndexedDocs, int numContainedTarget)
{
	return log((1.0 + numIndexedDocs) / (1.0 + numContainedTarget));
}

//Computes the TF
double computeTF(int targetOccurs, int wordsInDoc)
{
	if(wordsInDoc == 0.0)
	{
		return 0;
	}
	return ( (double) targetOccurs ) / ( (double) wordsInDoc );
}

//Sorts the indexed pages by their score
void sortIndexedPages(pageIndex* indexedPages, int low, int high)
{
	int pivot, index1, index2;
	double pivotScore;
	page* temp;

	if(low < high)
	{
		pivot = low;
		pivotScore = indexedPages->pages[pivot]->pageScore;
		index1 = low;
		index2 = high;

		while(index1 < index2)
		{
			//Indexes to swap
			while(indexedPages->pages[index1]->pageScore <= pivotScore && index1<high)
			{
				index1++;
			}

			while(indexedPages->pages[index2]->pageScore > pivotScore && index2>=low)
			{
				index2--;
			}

			//If index 1 is lower than index 2 
			//Swap the values
			if(index1 < index2)
			{
				temp = indexedPages->pages[index1];
				indexedPages->pages[index1] = indexedPages->pages[index2];
				indexedPages->pages[index2] = temp;
			}
		}

		//Swap at the pivot to index 2
		temp = indexedPages->pages[pivot];
		indexedPages->pages[pivot] = indexedPages->pages[index2];
		indexedPages->pages[index2] = temp;

		//Recursively sort lower portion
		sortIndexedPages(indexedPages,low,index2-1); 

		//Recursively sort top portion
		sortIndexedPages(indexedPages,index2+1,high); 
	}
}