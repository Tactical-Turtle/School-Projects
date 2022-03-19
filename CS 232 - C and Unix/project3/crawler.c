#include "crawler.h"

#define bufSize 1000

/*
 * returns 1 if the list starting at pNode contains the address addr,
 *    and returns 0 otherwise
 */
int contains(const listNode *pNode, const char *addr)
{
	if(pNode->link == NULL)
	{
		if(!strcmp(addr,pNode->addr))
		{
			return 1;
		}

		else
		{
			return 0;
		}
	}

	else
	{
		if(!strcmp(addr,pNode->addr))
		{
			return 1;
		}

		else
		{
			return contains(pNode->link,addr);
		}
	}
}

/*
 * inserts the address addr as a new listNode at the end of
 *    the list
 */
void insertBack(listNode *pNode, const char *addr)
{
	if(pNode->link == NULL)
	{ 
		listNode *newNode;
		newNode = malloc(sizeof(listNode)); // makes the new node on the heap (pointer for convince)
		
		if(newNode == NULL)
		{
			return;
		}

		pNode->link = newNode; 
		strncpy(newNode->addr, addr, MAX_ADDR_LENGTH); 
		newNode->link = NULL; 
	}
	else
	{
		insertBack(pNode->link, addr); 
	}
}

/*
 * prints the addresses from pNode to the end of the list,
 *   one on each line
 */
void printAddresses(const struct listNode *pNode)
{
	printf("%s\n",pNode->addr); 

	if(pNode->link != NULL)
	{
		printAddresses(pNode->link);    
	}
}

/*
 * frees the memory associated with this node and all subsequent nodes
 */
void destroyList(listNode *pNode)
{
	if(pNode->link == NULL)
	{ 
		free(pNode); 
	}

	else
	{
		listNode *next = pNode->link;
		free(pNode); 
		destroyList(next); 
	}
}

/* Credit: Prof. Briton Wolfe */
int getLink(const char* srcAddr, char* link, const int maxLinkLength)
{
	char buffer[bufSize];

	int numLinks = 0;

	FILE *pipe;

	snprintf(buffer, bufSize, "curl -s \"%s\" | python getLinks.py", srcAddr);

	pipe = popen(buffer, "r");
	if(pipe == NULL){
		fprintf(stderr, "ERROR: could not open the pipe for command %s\n",
				buffer);
		return 0;
	}

	fscanf(pipe, "%d\n", &numLinks);

	if(numLinks > 0){
		int linkNum;
		double r = (double)rand() / ((double)RAND_MAX + 1.0);

		for(linkNum=0; linkNum<numLinks; linkNum++){
			fgets(buffer, bufSize, pipe);

			if(r < (linkNum + 1.0) / numLinks){
				break;
			}
		}

		strncpy(link, buffer, maxLinkLength);
		link[maxLinkLength-1] = '\0';

		{
			char* pNewline = strchr(link, '\n');
			if(pNewline != NULL){
				*pNewline = '\0';
			}
		}
	}

	pclose(pipe);

	if(numLinks > 0){
		return 1;
	}
	else{
		return 0;
	}
}