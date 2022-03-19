#include "crawler.h"
#include "index.h"
#include <ctype.h>

pageIndex* indexPagesFromFile(const char* filename, int maxPages)
{
	page** pages; 
	pageIndex* indexedPages;

	FILE* indexFile;
	char url[MAX_ADDR_LENGTH];
	int maxHops, hopNum;
	listNode* listStart;
	int i;

	//File to Index
	indexFile = fopen(filename, "r");
	if(indexFile == NULL)
	{
		fprintf(stderr, "ERROR: Could not open the file\n");
		return NULL;
	}

	//Page index (array) 
	pages = malloc(sizeof(page*) * (maxPages + 1));

	if(pages == NULL)
	{
		fprintf(stderr, "ERROR: Could not allocate memory\n");
		fclose(indexFile);
		return NULL;
	}

	//Page index structure
	indexedPages = malloc(sizeof(pageIndex));

	if(indexedPages == NULL)
	{
		fprintf(stderr, "ERROR: COULD NOT ALLOCATE MEMORY\n");
		fclose(indexFile);
		free(pages);
		return NULL;
	}

	//Point the pages convenient pointer to this
	indexedPages->pages = pages; 

	//Make the list start
	listStart = malloc(sizeof(listNode));
	if(listStart == NULL)
	{
		fprintf(stderr, "ERROR: Could not allocate memory\n");
		fclose(indexFile);
		free(pages);
		free(indexedPages);
		return NULL;
	}

	listStart->link = NULL;

	//Indexed pages Initialization
	i = 0;
	while((fscanf(indexFile, "%1000s %d",url,&maxHops) == 2) && i<maxPages)
	{
		hopNum = 0;

		while(1)
		{ 
			//First URL
			if(i == 0)
			{
				strncpy(listStart->addr,url,MAX_ADDR_LENGTH);
				pages[i] = buildPage(url);

				if(pages[i] == NULL)
				{
					destroyList(listStart);
					fclose(indexFile);
					free(pages);
					destroyPageIndex(indexedPages);
					return NULL;
				}

				i++; 
			}

			//Next URL
			else
			{
				if(!contains(listStart,url))
				{
					insertBack(listStart,url);
					pages[i] = buildPage(url);

					if(pages[i] == NULL)
					{
						destroyList(listStart);
						fclose(indexFile);
						free(pages);
						destroyPageIndex(indexedPages);
						return NULL;
					}

					i++;
				}
			}

			hopNum++;

			if(hopNum <= maxHops && i < maxPages)
			{
				char nextURL[MAX_ADDR_LENGTH];

				if(getLink(url, nextURL, MAX_ADDR_LENGTH))
				{
					strncpy(url, nextURL, MAX_ADDR_LENGTH);
				}

				else
				{
					break;
				}
			}

			else
			{
				break;
			}
		}
	}

	indexedPages->numPages = i;
	fclose(indexFile);

	//Free list
	destroyList(listStart);

	return indexedPages;
}


page* buildPage(char* url)
{
	page* webPage;

	//Number of words in the page (holder)
	int numWords = 0;
	webPage = malloc(sizeof(page));

	if(webPage == NULL)
	{
		fprintf(stderr,"ERROR: COULD NOT ALLOCATE MEMORY");
		return NULL;
	}
	//Index the page
	//Retrieve the numWords
	webPage->trie = indexPage(url, &numWords);

	webPage->totalTerms = numWords;
	strncpy(webPage->url, url, MAX_ADDR_LENGTH);
	return webPage;
}


//Indexes page 
//Returns head of the trie
tnode* indexPage(const char* url,int* numWords)
{
	char wordBuffer[MAX_WORDSIZE]; 
	int bufferIndex = 0; 
	int wordBufferIndex = 0;
	char buffer[MAX_BUFFERSIZE]; 
	tnode *head;

	fprintf(stdout, "%s\n", url);

	getText(url, buffer, MAX_BUFFERSIZE);

	head = initializeNewNode('\0');

	if(head == NULL)
	{
		printf("Unable to create head node of page: %s", url);
		return NULL;
	}

	while(buffer[bufferIndex] != '\0')
	{

		if(buffer[bufferIndex] >= 'a' && buffer[bufferIndex] <= 'z')
		{
			wordBuffer[wordBufferIndex] = buffer[bufferIndex]; 
			wordBufferIndex++;
		}
		
		//Capital to lower case
		else if(buffer[bufferIndex] >= 'A' && buffer[bufferIndex] <= 'Z')
		{
			wordBuffer[wordBufferIndex] = tolower(buffer[bufferIndex]);
			wordBufferIndex++;
		}

		else
		{
			if(wordBufferIndex > 0)
			{ 
				wordBuffer[wordBufferIndex] = '\0'; 
				printf("\t%s\n", wordBuffer);

				if(addWordOccurrence(wordBuffer, strlen(wordBuffer), head))
				{
					printf("%s could not be added", wordBuffer);
				}

				(*numWords)++;
				wordBufferIndex = 0;
			}
		}

		bufferIndex++; 
	}

	return head; 
}


int addWordOccurrence(const char* word, const int wordLength, tnode* node)
{
	if(wordLength == 0)
	{ 
		node->frequency = node->frequency + 1; 
		return 0; 
	}

	else
	{
		int linkPos = word[0] - 'a';

		if(node->links[linkPos] == NULL)
		{ 
			tnode* newNode = initializeNewNode(word[0]);
			if(newNode == NULL)
			{
				printf("Node could not be allocated");
				return -1;
			}

			node->links[linkPos] = newNode;
			return addWordOccurrence(&word[1], wordLength - 1, newNode);
		}

		else
		{ 
			return addWordOccurrence(&word[1], wordLength - 1, node->links[linkPos]); 
		}
	}

}


int destroyTree(tnode* root)
{
	int linkIndex;
	for(linkIndex = 0; linkIndex < ALPHA_LENGTH; linkIndex++)
	{
		if(root->links[linkIndex] != NULL)
		{ 
			destroyTree(root->links[linkIndex]);
		}
	}

	free(root);
}


tnode* initializeNewNode(char value)
{
	int i;
	tnode* newNode = malloc(sizeof(tnode));
	if(newNode == NULL)
	{
		printf("New tnode could not be allocated");
		return NULL;
	}

	newNode->frequency = 0;
	newNode->value = value;

	for(i = 0; i < ALPHA_LENGTH; i++)
	{
		newNode->links[i] = NULL;
	}

	return newNode;
}



void destroyPageIndex(pageIndex* pages)
{
	int i;
	for(i = 0; i < pages->numPages; i++)
	{
		page* thisPage = pages->pages[i];
		if(thisPage == NULL)
		{
			fprintf(stderr, "ERROR: Page does not exist\n");
			return;
		}

		freePage(thisPage);
	}

	free(pages->pages);
	free(pages);
}


void freePage(page* thisPage)
{
	destroyTree(thisPage->trie);
	free(thisPage);
}

/* You should not need to modify this function */
int getText(const char* srcAddr, char* buffer, const int bufSize)
{
	FILE *pipe;
	int bytesRead;

	snprintf(buffer, bufSize, "curl -s \"%s\" | python getText.py", srcAddr);

	pipe = popen(buffer, "r");
	if(pipe == NULL)
	{
		fprintf(stderr, "ERROR: could not open the pipe for command %s\n", buffer);
		return 0;
	}

	bytesRead = fread(buffer, sizeof(char), bufSize-1, pipe);
	buffer[bytesRead] = '\0';

	pclose(pipe);

	return bytesRead;
}