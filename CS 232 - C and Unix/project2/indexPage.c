/* File: indexPage.c */
/* Author: Britton Wolfe */
/* Date: September 3rd, 2010 */

/* This program indexes a web page, printing out the counts of words on that page */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX_CHARACTERS 300000
#define MAX_URL 1000


// Self referencial structure to represent a trie
typedef struct trie 
{
    int countWord;
    int countNode; //count the number node
    char letter;  
    struct trie ** node; //arr nodes
}trie;

trie* indexPage(const char* url);

int addWordOccurrence(const char* word, const int wordLength, trie* root);

// Changed return type to a char pointer
char* printTrieContents(trie root, char* buffer);

int freeTrieMemory(trie* root);

int getText(const char* srcAddr, char* buffer, const int bufSize);



int main(int argc, char** argv)
{
    if(argc != 2)
    {
        fprintf(stderr, "USAGE: 'URL'\n");
        return 0;
    }

    trie* root;
    root = indexPage(argv[1]); 
    
    char* buffer = malloc(10);
    
    buffer[0] = '\0';
    
    buffer = printTrieContents(*root, buffer);
    
    //Free trie
    free(buffer);
    freeTrieMemory(root);

    return 0;
}

trie* indexPage(const char* url)
{
    trie * root;
    
    //Malloc trie
    root = malloc(sizeof(trie)); 
    
    root->countWord = 0;
    root->countNode = 0;
    root->letter = ' ';
    root->node = NULL;
    
    //Stores webpage contents
    char resultBuffer[MAX_CHARACTERS]; 
    getText(url, resultBuffer, MAX_CHARACTERS);
    
    printf("%s\n", url);
    
    //Counter variable
    int i = 0;
    
    //resultBuffer
    while(i < MAX_CHARACTERS && resultBuffer[i] != '\0')
    {
        if (isalpha(resultBuffer[i]))
        { 
            resultBuffer[i] = tolower(resultBuffer[i]); 
        }

        else
        {
            resultBuffer[i] = ' ';
        }
        
        //Increment until max character size (300,000)
        i++; 
    }
    
    //Reset counter
    i=0;
    
    char* token;
    token = strtok(resultBuffer, " ");

    //Scan each word in resultBuffer
    while(token != NULL)
    {
        printf("\t%s\n", token);
        addWordOccurrence(token, strlen(token), root);
        token = strtok (NULL, " ");
    }

    return root;

}

int addWordOccurrence(const char* word, const int wordLength, trie* root)
{
    int result = 0;
    int specificNode; 
    
    if ((*word == root->letter) && (wordLength == 1))
    {
        root->countWord++;
        return 1;
    }
    
    if (root->letter == ' ' && root->countNode == 0)
    {
        //Skip and do nothing
    }
    
    //Check nodes to current letter word
    //Pass current word and length
    else if (root->letter == ' ')
    {
        for(specificNode = 0; specificNode < root->countNode; specificNode++)
        {
            if(*word == (root->node[specificNode])->letter && wordLength > 0)
            {
                result = addWordOccurrence((word), (wordLength), root->node[specificNode]);
            
                if (result == 1)
                {
                    return 1;
                }
            }
        
        }
    
    }
    
    else
    { 
        //If current matches, check next letter node, pass the word less current letter and length-1 match
        for(specificNode = 0; specificNode < root->countNode; specificNode++)
        {
            if(*(word+1) == (root->node[specificNode])->letter && wordLength > 0 && result == 0)
            {
                result = addWordOccurrence((word+1), (wordLength-1), root->node[specificNode]);
            
                if (result == 1)
                {
                    return 1;
                }
            }
        }
    }
    
    
    if (root->letter == ' ')
    {
        struct trie* newNode;
        root->countNode++;
        
        root->node = realloc(root->node, sizeof(trie) * root->countNode); 
        
        newNode = malloc(sizeof(trie)); 
        
        newNode->countWord = 0;
        newNode->countNode = 0;
        newNode->node = NULL;
        newNode->letter = word[0];
        
        root->node[root->countNode-1] = newNode;
        result = addWordOccurrence((word),(wordLength), newNode);
        
            if (result == 1)
            {
                return 1;
            }
    }
    
    
    else
    {
        struct trie* newNode;
        root->countNode++;
        
        root->node = realloc(root->node, sizeof(trie) * root->countNode);
        
        newNode = malloc(sizeof(trie));
        
        newNode->countWord = 0;
        newNode->countNode = 0;
        newNode->node = NULL;
        newNode->letter = word[1];

        root->node[root->countNode-1] = newNode;
        result = addWordOccurrence((word + 1), (wordLength - 1), newNode);
        
            if (result == 1)
            {
                return 1;
            }
    }
    
    return result;
}

char* printTrieContents(trie root, char* buffer)
{
    int length;

    //If valid character
    if (root.letter != ' ' && root.letter != '\0')
    {
        length = strlen(buffer) + 2;
        buffer = realloc(buffer,sizeof(char) * length);
        buffer[length - 2] = root.letter;
        buffer[length - 1] = '\0';

        //If word count > 0
        if (root.countWord > 0 && strlen(buffer) > 0)
        {
            printf("%s: %d\n", buffer, root.countWord);
        }
    }

    if (root.countNode > 0)
    {
        int i;

        trie* temp = NULL;

        //Sort
        for(i = 0; i < root.countNode - 1; i++)
        {
            int j = i;
            for (j = i + 1; j < root.countNode; j++)
            {
                if (root.node[i]->letter > root.node[j]->letter)
                {
                    temp = root.node[i];
                    root.node[i] = root.node[j];
                    root.node[j] = temp;
                }
            }
        }

        //Loop over node in node array
        for (i = 0; i < root.countNode; i++)
        {
            buffer = printTrieContents(*(root.node[i]), buffer);
        }

    }

    //Remove last char
    length = strlen(buffer);
    buffer[length - 1] = '\0';
    return buffer;
  
}

int freeTrieMemory(trie* root)
{
    while (root->countNode > 0)
    {
        if (freeTrieMemory((root->node[root->countNode - 1])) == 1)
        {
            root->countNode--;
        }

        else
        {
            printf("Error freeing nodes");
        }
    }
    
    free(root->node);
    free(root);
    
    return 1;
}

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