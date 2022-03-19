#include "texture.h"

float roomSize = 15.0f;             // Size of the room
float tableTopWidth = 2.0f;         // Width of table top from center
float tableTopLength = 4.0f;        // Length of table top from center
float tableHeight = 1.7f;           // Height of table top from center
float tableThickness = 0.3f;        // Top of the table thickness
float legSize = 0.3f;               // Size of table leg

float cubeX = 0.0f;                 // Cube X position
float cubeY = 0.0f;                 // Cube Y position
float cubeZ = 0.0f;                 // Cube Z position
float cubeSize = 1.5f;              // Cube size

float wallOffset = 0.01f;           // Offset for wall placement

static int toggleTextures = 1;      // Flag for toggling textures off/on
static int toggleFloorTexture = 1;  // Flag for toggling floor textures off/on
static int swapTextures = 0;        // Flag for swapping textures (3 hard coded presets)

static GLfloat floorPlane[4];       // Array for the floor plane
static GLfloat floorShadow[4][4];   // Array for the floor shadow

// Draws the top of the table
void drawTableTop() 
{
    // Texture coords 0 and 1
    float min = 0.0f;
    float max = 1.0f;

    glPushMatrix(); // 1 set where to start the current object transformation
    glTranslatef(0.0f, -2.0f, 0.0f); // move downwards to lie on the floor

    glBegin(GL_QUADS);

    // Bottom
    glTexCoord2f(min, min);
    glVertex3f(-tableTopWidth, tableHeight, tableTopLength);
    glTexCoord2f(max, min);
    glVertex3f(-tableTopWidth, tableHeight, -tableTopLength);
    glTexCoord2f(max, max);
    glVertex3f(tableTopWidth, tableHeight, -tableTopLength);
    glTexCoord2f(min, max);
    glVertex3f(tableTopWidth, tableHeight, tableTopLength);

    // Front
    glTexCoord2f(min, min);
    glVertex3f(-tableTopWidth, tableHeight + tableThickness, tableTopLength);
    glTexCoord2f(max, min);
    glVertex3f(-tableTopWidth, tableHeight, tableTopLength);
    glTexCoord2f(max, max);
    glVertex3f(tableTopWidth, tableHeight, tableTopLength);
    glTexCoord2f(min, max);
    glVertex3f(tableTopWidth, tableHeight + tableThickness, tableTopLength);

    // Back
    glTexCoord2f(min, min);
    glVertex3f(tableTopWidth, tableHeight + tableThickness, -tableTopLength);
    glTexCoord2f(max, min);
    glVertex3f(tableTopWidth, tableHeight, -tableTopLength);
    glTexCoord2f(max, max);
    glVertex3f(-tableTopWidth, tableHeight, -tableTopLength);
    glTexCoord2f(min, max);
    glVertex3f(-tableTopWidth, tableHeight + tableThickness, -tableTopLength);

    // Right
    glTexCoord2f(min, min);
    glVertex3f(tableTopWidth, tableHeight + tableThickness, tableTopLength);
    glTexCoord2f(max, min);
    glVertex3f(tableTopWidth, tableHeight, tableTopLength);
    glTexCoord2f(max, max);
    glVertex3f(tableTopWidth, tableHeight, -tableTopLength);
    glTexCoord2f(min, max);
    glVertex3f(tableTopWidth, tableHeight + tableThickness, -tableTopLength);

    // Left
    glTexCoord2f(max, max);
    glVertex3f(-tableTopWidth, tableHeight + tableThickness, tableTopLength);
    glTexCoord2f(min, max);
    glVertex3f(-tableTopWidth, tableHeight + tableThickness, -tableTopLength);
    glTexCoord2f(min, min);
    glVertex3f(-tableTopWidth, tableHeight, -tableTopLength);
    glTexCoord2f(max, min);
    glVertex3f(-tableTopWidth, tableHeight, tableTopLength);

    // Top
    glTexCoord2f(min, min);
    glVertex3f(-tableTopWidth, tableHeight + tableThickness, tableTopLength);
    glTexCoord2f(min, max);
    glVertex3f(tableTopWidth, tableHeight + tableThickness, tableTopLength);
    glTexCoord2f(max, max);
    glVertex3f(tableTopWidth, tableHeight + tableThickness, -tableTopLength);
    glTexCoord2f(max, min);
    glVertex3f(-tableTopWidth, tableHeight + tableThickness, -tableTopLength);
    glEnd();

    glPopMatrix();
}


// Draw the first leg of the table
void firstLeg() 
{
    glPushMatrix(); 
    glTranslatef(0.0f, -2.0f, 0.0f); 

    // Texture coords 0 and 1
    float min = 0.0f;
    float max = 1.0f;

    glBegin(GL_QUADS);
    // Front
    glNormal3f(0, 0, 1);
    glTexCoord2f(min, min);
    glVertex3f(-tableTopWidth + legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(max, min);
    glVertex3f(-tableTopWidth + legSize, 0.0f, tableTopLength - legSize);
    glTexCoord2f(max, max);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, tableTopLength - legSize);
    glTexCoord2f(min, max);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, tableTopLength - legSize);

    // Back
    glNormal3f(0, 0, -1);
    glTexCoord2f(1, 1);
    glVertex3f(-tableTopWidth + legSize, tableHeight, tableTopLength - legSize - legSize);
    glTexCoord2f(0, 1);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, tableTopLength - legSize - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(-tableTopWidth + legSize, 0.0f, tableTopLength - legSize - legSize);

    // Right
    glNormal3f(1, 0, 0);
    glTexCoord2f(0, 1);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, tableTopLength - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 1);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, tableTopLength - legSize - legSize);

    // Left
    glNormal3f(-1, 0, 0);
    glTexCoord2f(1, 1);
    glVertex3f(-tableTopWidth + legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(0, 1);
    glVertex3f(-tableTopWidth + legSize, tableHeight, tableTopLength - legSize - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(-tableTopWidth + legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(-tableTopWidth + legSize, 0.0f, tableTopLength - legSize);

    glEnd();
    glPopMatrix();
}


// Draw the second leg of the table
void secondLeg() 
{
    glPushMatrix(); 
    glTranslatef(0.0f, -2.0f, 0.0f); 

    glBegin(GL_QUADS);

    // Front
    glNormal3f(0, 0, 1);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, tableTopLength - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, tableTopLength - legSize);

    //Back
    glNormal3f(0, 0, -1);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, tableTopLength - legSize - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, tableTopLength - legSize - legSize);

    // Left
    glNormal3f(-1, 0, 0);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, tableTopLength - legSize - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, tableTopLength - legSize);

    // Right
    glNormal3f(1, 0, 0);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, tableTopLength - legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, tableTopLength - legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, tableTopLength - legSize - legSize);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, tableTopLength - legSize - legSize);

    glEnd();
    glPopMatrix();
}

// Draw the third table leg
void thirdLeg() 
{
    // Texture coords 0 and 1
    float min = 0.0f;
    float max = 1.0f;

    glPushMatrix(); 
    glTranslatef(0.0f, -2.0f, 0.0f); 

    glBegin(GL_QUADS);

    // Front
    glNormal3f(0, 0, 1);
    glTexCoord2f(min, min);
    glVertex3f(-tableTopWidth + legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(max, min);
    glVertex3f(-tableTopWidth + legSize, 0.0f, -tableTopLength + legSize);
    glTexCoord2f(max, max);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, -tableTopLength + legSize);
    glTexCoord2f(min, max);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, -tableTopLength + legSize);

    // Back
    glNormal3f(0, 0, -1);
    glTexCoord2f(1, 1);
    glVertex3f(-tableTopWidth + legSize, tableHeight, -tableTopLength + legSize + legSize);
    glTexCoord2f(0, 1);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, -tableTopLength + legSize + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(-tableTopWidth + legSize, 0.0f, -tableTopLength + legSize + legSize);

    // Right
    glNormal3f(1, 0, 0);
    glTexCoord2f(0, 1);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, -tableTopLength + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(-tableTopWidth + legSize + legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 1);
    glVertex3f(-tableTopWidth + legSize + legSize, tableHeight, -tableTopLength + legSize + legSize);

    // Left
    glNormal3f(-1, 0, 0);
    glTexCoord2f(1, 1);
    glVertex3f(-tableTopWidth + legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(0, 1);
    glVertex3f(-tableTopWidth + legSize, tableHeight, -tableTopLength + legSize + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(-tableTopWidth + legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(-tableTopWidth + legSize, 0.0f, -tableTopLength + legSize);

    glEnd();
    glPopMatrix();
}

// Draw the fourth table leg
void fourthLeg() 
{
    glPushMatrix(); 
    glTranslatef(0.0f, -2.0f, 0.0f);

    glBegin(GL_QUADS);

    // Front
    glNormal3f(0, 0, 1);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, -tableTopLength + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, -tableTopLength + legSize);

    // Back
    glNormal3f(0, 0, -1);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, -tableTopLength + legSize + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, -tableTopLength + legSize + legSize);

    // Left
    glNormal3f(-1, 0, 0);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize - legSize, tableHeight, -tableTopLength + legSize + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize - legSize, 0.0f, -tableTopLength + legSize);

    // Right
    glNormal3f(1, 0, 0);
    glTexCoord2f(0, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, -tableTopLength + legSize);
    glTexCoord2f(0, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, -tableTopLength + legSize);
    glTexCoord2f(1, 0);
    glVertex3f(tableTopWidth - legSize, 0.0f, -tableTopLength + legSize + legSize);
    glTexCoord2f(1, 1);
    glVertex3f(tableTopWidth - legSize, tableHeight, -tableTopLength + legSize + legSize);

    glEnd();
    glPopMatrix();
}

// Setup the glass cube texture coordinates
void glassCubeTexture() 
{
    // Texture coords 0 and 1
    float min = 0.0f;
    float max = 1.0f;

    glPushMatrix();

    glTranslatef(0.3f, 0.0f, -2.3f);
    glBegin(GL_QUADS);

    // Front
    glTexCoord2f(0, 0);
    glVertex3f(cubeX, cubeY, cubeZ);
    glTexCoord2f(1, 0);
    glVertex3f(cubeX + cubeSize, cubeY, cubeZ);
    glTexCoord2f(1, 1);
    glVertex3f(cubeX + cubeSize, cubeY + cubeSize, cubeZ);
    glTexCoord2f(0, 1);
    glVertex3f(cubeX, cubeY + cubeSize, cubeZ);

    // Right
    glTexCoord2f(min, min);
    glVertex3f(cubeX + cubeSize, cubeY, cubeZ);
    glTexCoord2f(max, min);
    glVertex3f(cubeX + cubeSize, cubeY, cubeZ - cubeSize);
    glTexCoord2f(max, max);
    glVertex3f(cubeX + cubeSize, cubeY + cubeSize, cubeZ - cubeSize);
    glTexCoord2f(min, max);
    glVertex3f(cubeX + cubeSize, cubeY + cubeSize, cubeZ);

    // Back
    glTexCoord2f(min, min);
    glVertex3f(cubeX + cubeSize, cubeY, cubeZ - cubeSize);
    glTexCoord2f(max, min);
    glVertex3f(cubeX, cubeY, cubeZ - cubeSize);
    glTexCoord2f(max, max);
    glVertex3f(cubeX, cubeY + cubeSize, cubeZ - cubeSize);
    glTexCoord2f(min, max);
    glVertex3f(cubeX + cubeSize, cubeY + cubeSize, cubeZ - cubeSize);

    // Left
    glTexCoord2f(min, min);
    glVertex3f(cubeX, cubeY, cubeZ - cubeSize);
    glTexCoord2f(max, min);
    glVertex3f(cubeX, cubeY, cubeZ);
    glTexCoord2f(max, max);
    glVertex3f(cubeX, cubeY + cubeSize, cubeZ);
    glTexCoord2f(min, max);
    glVertex3f(cubeX, cubeY + cubeSize, cubeZ - cubeSize);

    // Top
    glTexCoord2f(min, min);
    glVertex3f(cubeX, cubeY + cubeSize, cubeZ);
    glTexCoord2f(max, min);
    glVertex3f(cubeX + cubeSize, cubeY + cubeSize, cubeZ);
    glTexCoord2f(max, max);
    glVertex3f(cubeX + cubeSize, cubeY + cubeSize, cubeZ - cubeSize);
    glTexCoord2f(min, max);
    glVertex3f(cubeX, cubeY + cubeSize, cubeZ - cubeSize);

    // Bottom
    glTexCoord2f(min, min);
    glVertex3f(cubeX, cubeY, cubeZ);
    glTexCoord2f(min, max);
    glVertex3f(cubeX, cubeY, cubeZ - cubeSize);
    glTexCoord2f(max, max);
    glVertex3f(cubeX + cubeSize, cubeY, cubeZ - cubeSize);
    glTexCoord2f(max, min);
    glVertex3f(cubeX + cubeSize, cubeY, cubeZ);

    glEnd();
    glPopMatrix();
}

// Draw the floor shadow
static void floorShadowObj(void) 
{
    drawTableTop();
    firstLeg();
    secondLeg();
    thirdLeg();
    fourthLeg();
    glassCubeTexture();
}

// Array of floor vertices
static GLfloat floorVertices[4][3] = 
{
        { -roomSize, -2.0f, roomSize },
        { roomSize, -2.0f, roomSize },
        { roomSize, -2.0f, -roomSize },
        { -roomSize, -2.0f, -roomSize },
};

// Draw the floor
static void drawFloor(void) 
{

    if (toggleFloorTexture == 1)
    {
        glEnable(GL_TEXTURE_2D);
       
        if (swapTextures == 0)
        { glBindTexture(GL_TEXTURE_2D, grassTexture); }

        else if (swapTextures == 1)
        { glBindTexture(GL_TEXTURE_2D, floorTexture); }
        
        else if (swapTextures == 2)
        { glBindTexture(GL_TEXTURE_2D, waterTexture); }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    glBegin(GL_QUADS);
    glNormal3f(0, 1, 0);
    glColor3f(0.90f, 0.90f, 0.90f);
    glTexCoord2f(0, 0);
    glVertex3fv(floorVertices[0]);
    glTexCoord2f(5, 0);
    glVertex3fv(floorVertices[1]);
    glTexCoord2f(5, 5);
    glVertex3fv(floorVertices[2]);
    glTexCoord2f(0, 5);
    glVertex3fv(floorVertices[3]);
    glEnd();

}


// Draw the sky above the floor
void drawSky() 
{

    if (toggleTextures == 1)
    {
        glEnable(GL_TEXTURE_2D);

        if (swapTextures == 0)
        { glBindTexture(GL_TEXTURE_2D, skyTexture);   }

        else if (swapTextures == 1)
        { glBindTexture(GL_TEXTURE_2D, cloudsTexture); }

        else if (swapTextures == 2)
        { glBindTexture(GL_TEXTURE_2D, nightSkyTexture); }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    if (toggleTextures == 0)
    {
        glDisable(GL_TEXTURE_2D);
    }

    glBegin(GL_QUADS);
    glNormal3f(0, 1, 0);
    glColor4f(1, 1, 1, 0.6f);
    glTexCoord2f(0, 0);
    glVertex3f(-roomSize, 8.0f, -roomSize);
    glTexCoord2f(1, 0);
    glVertex3f(roomSize, 8.0f, -roomSize);
    glTexCoord2f(1, 1);
    glVertex3f(roomSize, 8.0f, roomSize);
    glTexCoord2f(0, 1);
    glVertex3f(-roomSize, 8.0f, roomSize);

    glEnd();
    glDisable(GL_TEXTURE_2D);
    glDisable(GL_BLEND);

}


// Draw walls
void drawWall() 
{ 
    drawSky();

    if (toggleTextures == 1)
    {
        glEnable(GL_TEXTURE_2D);

        if (swapTextures == 0)
        { glBindTexture(GL_TEXTURE_2D, stoneTexture);  }

        else if (swapTextures == 1)
        { glBindTexture(GL_TEXTURE_2D, abstractTexture); }

        else if (swapTextures == 2)
        { glBindTexture(GL_TEXTURE_2D, paperTexture); }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }
    
    glBegin(GL_QUADS);
    glNormal3f(0, 1, 0);

    // Left wall
    glTexCoord2f(0, 0);
    glVertex3f(-roomSize - wallOffset, -2.0f, -roomSize);
    glTexCoord2f(5, 0);
    glVertex3f(-roomSize - wallOffset, -2.0f, roomSize);
    glTexCoord2f(5, 5);
    glVertex3f(-roomSize - wallOffset, 8.0f, roomSize);
    glTexCoord2f(0, 5);
    glVertex3f(-roomSize - wallOffset, 8.0f, -roomSize);

    // Right wall
    glTexCoord2f(0, 0);
    glVertex3f(roomSize + wallOffset, -2.0f, roomSize);
    glTexCoord2f(5, 0);
    glVertex3f(roomSize + wallOffset, -2.0f, -roomSize);
    glTexCoord2f(5, 5);
    glVertex3f(roomSize + wallOffset, 8.0f, -roomSize);
    glTexCoord2f(0, 5);
    glVertex3f(roomSize + wallOffset, 8.0f, roomSize);
    glEnd();

    glDisable(GL_TEXTURE_2D);
}

// Draw the wall mirror
void drawWallMirror() 
{
    drawSky();

    if (toggleTextures == 1)
    {
        glEnable(GL_TEXTURE_2D);

        if (swapTextures == 0)
        { glBindTexture(GL_TEXTURE_2D, stoneTexture); }

        else if (swapTextures == 1)
        { glBindTexture(GL_TEXTURE_2D, abstractTexture); }

        else if (swapTextures == 2)
        { glBindTexture(GL_TEXTURE_2D, paperTexture); }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    glBegin(GL_QUADS);
    glNormal3f(0, 1, 0);

    // Left wall
    glTexCoord2f(0, 0);
    glVertex3f(-roomSize - wallOffset, -2.0f, -roomSize);
    glTexCoord2f(5, 0);
    glVertex3f(-roomSize - wallOffset, -2.0f, roomSize);
    glTexCoord2f(5, 5);
    glVertex3f(-roomSize - wallOffset, 8.0f, roomSize);
    glTexCoord2f(0, 5);
    glVertex3f(-roomSize - wallOffset, 8.0f, -roomSize);

    // Right wall
    glTexCoord2f(0, 0);
    glVertex3f(roomSize + wallOffset, -2.0f, roomSize);
    glTexCoord2f(5, 0);
    glVertex3f(roomSize + wallOffset, -2.0f, -roomSize);
    glTexCoord2f(5, 5);
    glVertex3f(roomSize + wallOffset, 8.0f, -roomSize);
    glTexCoord2f(0, 5);
    glVertex3f(roomSize + wallOffset, 8.0f, roomSize);
    glEnd();

    glDisable(GL_TEXTURE_2D);
}

// Draw the glass cube
void glassCube() 
{
    if (toggleTextures == 1)
    {
        glEnable(GL_TEXTURE_2D);

        glBindTexture(GL_TEXTURE_2D, concreteTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
    glassCubeTexture();
    glDisable(GL_BLEND);
    glDisable(GL_TEXTURE_2D);

}


// Draw the full table
void drawTable() 
{
    if (toggleTextures == 1)
    {
        glEnable(GL_TEXTURE_2D);

        if (swapTextures == 0)
        { glBindTexture(GL_TEXTURE_2D, woodTexture); }

        else if (swapTextures == 1)
        { glBindTexture(GL_TEXTURE_2D, wood2Texture); }

        else if (swapTextures == 2)
        { glBindTexture(GL_TEXTURE_2D, wood3Texture);   }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }

    drawTableTop();
    firstLeg();
    secondLeg();
    thirdLeg();
    fourthLeg();

    glDisable(GL_TEXTURE_2D);
}

