#include "imageloader.h"

// Loads texture data
GLuint loadTexture(Image* image) 
{
    GLuint *textures = new GLuint[1];
    glGenTextures(1, textures); 
    glBindTexture(GL_TEXTURE_2D, *textures);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, image->width, image->height,  0, GL_RGB, GL_UNSIGNED_BYTE, image->pixels); 
    return *textures;
}

GLuint concreteTexture;        // Textures from .bmp files 
GLuint paperTexture;           // Textures from .bmp files
GLuint woodTexture;            // Textures from .bmp files
GLuint grassTexture;           // Textures from .bmp files
GLuint redTexture;             // Textures from .bmp files
GLuint abstractTexture;        // Textures from .bmp files
GLuint stoneTexture;           // Textures from .bmp files
GLuint skyTexture;             // Textures from .bmp files
GLuint floorTexture;           // Textures from .bmp files
GLuint cloudsTexture;          // Textures from .bmp files
GLuint nightSkyTexture;        // Textures from .bmp files
GLuint waterTexture;           // Textures from .bmp files
GLuint wood2Texture;           // Textures from .bmp files
GLuint wood3Texture;           // Textures from .bmp files

// Initialize textures
void initTextures() 
{
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_COLOR_MATERIAL);
    glEnable(GL_BLEND); 
    glEnable(GL_NORMALIZE);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 

    // Load texture to corresponding variable
    Image* concreteFile = loadBMP("images/concrete.bmp");
    concreteTexture = loadTexture(concreteFile);
    delete concreteFile;

    Image* paperImage = loadBMP("images/paper.bmp");
    paperTexture = loadTexture(paperImage);
    delete paperImage;

    Image* woodImage = loadBMP("images/wood.bmp");
    woodTexture = loadTexture(woodImage);
    delete woodImage;

    Image* grassImage = loadBMP("images/grass.bmp");
    grassTexture = loadTexture(grassImage);
    delete grassImage;

    Image* redImage = loadBMP("images/red.bmp");
    redTexture = loadTexture(redImage);
    delete redImage;

    Image* abstractImage = loadBMP("images/abstract.dib");
    abstractTexture = loadTexture(abstractImage);
    delete abstractImage;

    Image* stoneImage = loadBMP("images/stone.dib");
    stoneTexture = loadTexture(stoneImage);
    delete stoneImage;

    Image* skyImage = loadBMP("images/sky.bmp");
    skyTexture = loadTexture(skyImage);
    delete skyImage;

    Image* floorImage = loadBMP("images/floor.bmp");
    floorTexture = loadTexture(floorImage);
    delete floorImage;

    Image* cloudsImage = loadBMP("images/clouds.bmp");
    cloudsTexture = loadTexture(cloudsImage);
    delete cloudsImage;

    Image* nightSkyImage = loadBMP("images/nightsky.dib");
    nightSkyTexture = loadTexture(nightSkyImage);
    delete nightSkyImage;

    Image* waterImage = loadBMP("images/water.bmp");
    waterTexture = loadTexture(waterImage);
    delete waterImage;

    Image* wood2Image = loadBMP("images/wood2.bmp");
    wood2Texture = loadTexture(wood2Image);
    delete wood2Image;

    Image* wood3Image = loadBMP("images/wood3.bmp");
    wood3Texture = loadTexture(wood3Image);
    delete wood3Image;

    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
}