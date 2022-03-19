#include <GL/freeglut.h>

static int animation = 1;          // Idle function toggle flag
static float lightAngle = 0.0;     // Moving light angle
static float lightHeight = 0;      // Moving light height
int lightMoving = 0;               // Moving light toggle flag

static GLboolean lightSwitch = GL_TRUE;         // Moving light toggle
static GLboolean posLightSwitch = GL_TRUE;      // Moving light position toggle

// Light properties
static GLfloat light_position0[4];
GLfloat diffuse_light0[] = { 1.0f, 1.0f, 0.8f, 0.5f };
GLfloat  ambient_light0[] = { 0.25f, 0.25f, 0.25f, 1.0f };
GLfloat  specular_light0[] = { 0.775f, 0.775f, 0.775f, 1.0f };

GLfloat  ambient_light1[] = { 0.25f, 0.25f, 0.25f, 1.0f };
GLfloat  diffuse_light1[] = { 0.4f, 0.4f, 0.4f, 1.0f };
GLfloat  specular_light1[] = { 0.775f, 0.775f, 0.775f, 1.0f };
GLfloat  light_position1[] = { 0.0, 0.0, 0.0, 1.0 };

// Object material properties (chrome)
GLfloat  material_ambient[] = { 0.25, 0.25, 0.25 };
GLfloat  material_diffuse[] = { 0.4, 0.4, 0.4 };
GLfloat  material_specular[] = { 0.775, 0.775, 0.775 };
GLfloat  material_emissive[] = { 0.0, 0.0, 0.0 };
GLint	 material_shininess = 12.8;

// Create and enable positional light with attenuation that follows the camera
static void positionalLight()
{
    // Omnidirectional light follows camera center
    const GLfloat constAtten = 1.0f;
    glLightfv(GL_LIGHT1, GL_AMBIENT, ambient_light1);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse_light1);
    glLightfv(GL_LIGHT1, GL_SPECULAR, specular_light1);
    glLightfv(GL_LIGHT1, GL_POSITION, light_position1);
    glLightf(GL_LIGHT1, GL_CONSTANT_ATTENUATION, 1.0);
    glLightf(GL_LIGHT1, GL_LINEAR_ATTENUATION, 0.045);
    glLightf(GL_LIGHT1, GL_QUADRATIC_ATTENUATION, 0.0075);
    glEnable(GL_LIGHT1);
}


// Create and enable sun positional light
static void sunLight()
{
    // Enable light0
    glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, 1);
    glLightfv(GL_LIGHT0, GL_AMBIENT, ambient_light0);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse_light0);
    glLightfv(GL_LIGHT0, GL_SPECULAR, specular_light0);
    glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, 1.0f);
    glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, 0.045f);
    glLightf(GL_LIGHT0, GL_QUADRATIC_ATTENUATION, 0.0075f);
    glEnable(GL_LIGHT0);
    glEnable(GL_LIGHTING);
}

// Moves the light
static void moveLightIdle(void) 
{
	if (!lightMoving) 
	{
		lightAngle += 0.005f;
	}

	glutPostRedisplay();
}