/*
NAME:		Montana Singer
PROJECT:	Project 5
COURSE:	    CS 321
INSTRUCTOR:	Beomjin Kim
DUE DATE:	November 24, 2020
*/

#include <iostream>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <GL/freeglut.h>

// Function prototypes
void freeModelResources(struct obj_model_t* model);
int mallocModel(struct obj_model_t* model);
int getModelData(FILE* file, struct obj_model_t* model);
int storeModelData(FILE* file, struct obj_model_t* model);
int readObjectFile(const char* filename, struct obj_model_t* model);
void drawModel(struct obj_model_t* model);
void set_up_light(const char* filename);
void cleanup();
void other_init();
void display();
void keyboard(unsigned char key, int x, int y);
void setupInitialView();
void init_window(int argc, char** argv);
void programControls();

char inputFilename[100] = ""; // OBJ input file name.
int rotateObjectsFlag = 0;	  // Flag to indicate rotating an object
int rotateLightFlag = 0;	  // Flag to indicate rotating a positional light source

static GLfloat objRotateAngle = 0.0;		// Angle to rotate an object
static GLfloat lightRotateAngle = 0.0;		// Angle to rotate a positional light source

// Properties for directional light GL_LIGHT0 
GLfloat  ambient_light0[] = { 0.5, 0.5, 0.5, 1.0f };
GLfloat  diffuse_light0[] = { 0.5, 0.5, 0.5, 1.0f };
GLfloat  specular_light0[] = { 0.5, 0.5, 0.5, 1.0f };
GLfloat  light_position0[] = { 1.0, 1.0, 1.0, 0.0 };

// Properties for positional light GL_LIGHT1 
GLfloat  ambient_light1[] = { 0.4f, 0.4f, 0.4f, 1.0f };
GLfloat  diffuse_light1[] = { 0.6f, 0.6f, 0.6f, 1.0f };
GLfloat  specular_light1[] = { 0.6f, 0.6f, 0.6f, 1.0f };
GLfloat  light_position1[] = { 0.0, 0.0, 100.0, 1.0 };

// Properties for GL_LIGHT2
// Blue spotlight with narrow angle
GLfloat diffuse_light2[] = { 0.2, 0.4, 0.9, 1.0 };
GLfloat specular_light2[] = { 0.9f, 0.9f, 0.9f, 1.0f };
GLfloat light_position2[] = { 0.0, 0.0, 2.0, 1.0 };
GLfloat spot_direction2[] = { 0.0, 0.0, -100.0 };

// Properties for GL_LIGHT3
// Red spotlight with wide angle
GLfloat diffuse_light3[] = { 1.0, 0.2, 0.6, 1.0 };
GLfloat specular_light3[] = { 0.6f, 0.6f, 0.6f, 1.0f };
GLfloat light_position3[] = { 0.3, -0.5, 4.0, 1.0 };
GLfloat spot_direction3[] = { 0.3, -0.5, -2.0 };

GLint	 exponent = 0;		// Light exponent
GLfloat	 cutoff = 10.0;		// Light cutoff angle

// Object material properties (copper)
GLfloat  material_ambient[] = { 0.19125, 0.0735, 0.0225 };
GLfloat  material_diffuse[] = { 0.7038, 0.27048, 0.0828 };
GLfloat  material_specular[] = { 0.256777, 0.137622, 0.086014 };
//GLfloat  material_emissive[] = { 0.72, 0.45, 0.20 };	// Use this for copper emissive light
GLfloat  material_emissive[] = { 0.0, 0.0, 0.0 };		// Emissive blue light to easily distinguish increase/decrease
GLint	 material_shininess = 12.8;


double objectMaxX = 0;            //
double objectMinX = 0;            //
double objectMaxY = 0;            // Define the object's maximum and minimum vertices
double objectMinY = 0;            // (Used to calculate the center of the object)
double objectMinZ = 0;            //
double objectMaxZ = 0;            //

double objectCenterX = 0;         //
double objectCenterY = 0;         // Define the center vertices of the drawn object
double objectCenterZ = 0;         //

 // Vectors 
typedef float vec3_t[3];
typedef float vec4_t[4];

// Vertex values
struct obj_vertex_t
{
	vec4_t xyzw;
};

// Texture coordinate values
struct obj_texCoord_t
{
	vec3_t uvw;
};

// Normal vector values
struct obj_normal_t
{
	vec3_t ijk;
};

// Polygon
struct obj_face_t
{
	GLenum type;        // Primitive type (GL_TRIANGLE etc.)
	int num_elems;      // Number of vertices 

	int* vert_indices;  // Vertex indices 
	int* uvw_indices;   // Texture coordinate indices 
	int* norm_indices;  // Normal vector indices 
};

// Object model structure 
struct obj_model_t
{
	int num_vertices;                  // Number of vertices 
	int num_textureCoordinates;        // Number of texture coords
	int num_normals;                   // Number of normal vectors 
	int num_faces;                     // Number of polygons 

	int has_texCoords;                 // Used to check if object has texture coordinates
	int has_normals;                   // Used to check if object has normal vectors

	struct obj_vertex_t* list_vertices;     // List of vertices
	struct obj_texCoord_t* list_texCoords;  // List of texture coordinates
	struct obj_normal_t* list_normals;      // List of normal vectors
	struct obj_face_t* list_faces;          // List of model polygons
};

// Object model 
struct obj_model_t objfile;

// Free resources allocated for the model
void freeModelResources(struct obj_model_t* model)
{
	int i; // Counter variable

	if (model)
	{
		if (model->list_vertices)
		{
			free(model->list_vertices);
			model->list_vertices = NULL;
		}

		if (model->list_texCoords)
		{
			free(model->list_texCoords);
			model->list_texCoords = NULL;
		}

		if (model->list_normals)
		{
			free(model->list_normals);
			model->list_normals = NULL;
		}

		if (model->list_faces)
		{
			for (i = 0; i < model->num_faces; ++i)
			{
				if (model->list_faces[i].vert_indices)
				{
					free(model->list_faces[i].vert_indices);
				}

				if (model->list_faces[i].uvw_indices)
				{
					free(model->list_faces[i].uvw_indices);
				}

				if (model->list_faces[i].norm_indices)
				{
					free(model->list_faces[i].norm_indices);
				}
			}

			free(model->list_faces);
			model->list_faces = NULL;
		}
	}
}

// Allocate resources for the model after first pass
int mallocModel(struct obj_model_t* model)
{
	if (model->num_vertices)
	{
		model->list_vertices = (struct obj_vertex_t*)
			malloc(sizeof(struct obj_vertex_t) * model->num_vertices);

		if (!model->list_vertices)
		{
			return 0;
		}
	}

	if (model->num_textureCoordinates)
	{
		model->list_texCoords = (struct obj_texCoord_t*)
			malloc(sizeof(struct obj_texCoord_t) * model->num_textureCoordinates);

		if (!model->list_texCoords)
		{
			return 0;
		}
	}

	if (model->num_normals)
	{
		model->list_normals = (struct obj_normal_t*)
			malloc(sizeof(struct obj_normal_t) * model->num_normals);

		if (!model->list_normals)
		{
			return 0;
		}
	}

	if (model->num_faces)
	{
		model->list_faces = (struct obj_face_t*)
			calloc(model->num_faces, sizeof(struct obj_face_t));

		if (!model->list_faces)
		{
			return 0;
		}
	}

	return 1;
}

// Load an OBJ model from file
// Get the number of triangles/vertices/texture coordinates for allocating buffers
int getModelData(FILE* file, struct obj_model_t* model)
{
	int v, t, n;
	char buffer[256];

	while (!feof(file))
	{
		// Read whole line 
		fgets(buffer, sizeof(buffer), file);

		switch (buffer[0])
		{
		case 'v':
		{
			if (buffer[1] == ' ')
			{
				// Vertex 
				model->num_vertices++;
			}

			else if (buffer[1] == 't')
			{
				// Texture coordinates
				model->num_textureCoordinates++;
			}

			else if (buffer[1] == 'n')
			{
				// Normal vector 
				model->num_normals++;
			}

			else
			{
				printf("Unknown token \"%s\"! (ignoring)\n", buffer);
			}

			break;
		}

		case 'f':
		{
			// Face 
			if (sscanf(buffer + 2, "%d/%d/%d", &v, &n, &t) == 3)
			{
				model->num_faces++;
				model->has_texCoords = 1;
				model->has_normals = 1;
			}

			else if (sscanf(buffer + 2, "%d//%d", &v, &n) == 2)
			{
				model->num_faces++;
				model->has_texCoords = 0;
				model->has_normals = 1;
			}

			else if (sscanf(buffer + 2, "%d/%d", &v, &t) == 2)
			{
				model->num_faces++;
				model->has_texCoords = 1;
				model->has_normals = 0;
			}

			else if (sscanf(buffer + 2, "%d", &v) == 1)
			{
				model->num_faces++;
				model->has_texCoords = 0;
				model->has_normals = 0;
			}

			else
			{
				fprintf(stderr, "Error: found face with no vertex\n");
			}

			break;
		}

		default:
			break;
		}
	}

	// Check if information is valid 
	if ((model->has_texCoords && !model->num_textureCoordinates) ||
		(model->has_normals && !model->num_normals))
	{
		fprintf(stderr, "Error: contradiction between collected information\n");
		return 0;
	}

	if (!model->num_vertices)
	{
		fprintf(stderr, "Error: no vertex found\n");
		return 0;
	}
	return 1;
}

// Load OBJ model from file
// Read model data and load data
int storeModelData(FILE* file, struct obj_model_t* model)
{
	// Polygon data
	struct obj_vertex_t* pVertex = model->list_vertices;
	struct obj_texCoord_t* pTextureCoordinate = model->list_texCoords;
	struct obj_normal_t* pNormal = model->list_normals;
	struct obj_face_t* pFace = model->list_faces;
	char buffer[128], * pBuffer;
	int i;

	while (!feof(file))
	{
		// Read whole line
		fgets(buffer, sizeof(buffer), file);

		switch (buffer[0])
		{
		case 'v':
		{
			// Vertex
			if (buffer[1] == ' ')
			{
				if (sscanf(buffer + 2, "%f %f %f %f", &pVertex->xyzw[0], &pVertex->xyzw[1], &pVertex->xyzw[2], &pVertex->xyzw[3]) != 4)
				{
					if (sscanf(buffer + 2, "%f %f %f", &pVertex->xyzw[0], &pVertex->xyzw[1], &pVertex->xyzw[2]) != 3)
					{
						fprintf(stderr, "Error reading vertex data\n");
						return 0;
					}

					else
					{
						pVertex->xyzw[3] = 1.0;
					}

					//// Max X vertex
					//if (pVertex->xyzw[0] > objectMaxX)
					//{ objectMaxX = pVertex->xyzw[0]; }
					//
					//// Min X vertex
					//if (pVertex->xyzw[0] < objectMinX)
					//{ objectMinX = pVertex->xyzw[0]; }
					//
					//// Max Y vertex
					//if (pVertex->xyzw[0] > objectMaxY)
					//{ objectMaxY = pVertex->xyzw[1]; }
					//
					//// Min Y vertex
					//if (pVertex->xyzw[0] < objectMinY)
					//{ objectMinY = pVertex->xyzw[1]; }
					//
					//// Max Z vertex
					//if (pVertex->xyzw[0] > objectMaxZ)
					//{ objectMaxZ = pVertex->xyzw[2]; }
					//
					//// Min Z vertex
					//if (pVertex->xyzw[0] < objectMinZ)
					//{ objectMinZ = pVertex->xyzw[2]; }
				}

				pVertex++;
			}

			// Texture coordinates
			else if (buffer[1] == 't')
			{
				if (sscanf(buffer + 2, "%f %f %f", &pTextureCoordinate->uvw[0], &pTextureCoordinate->uvw[1], &pTextureCoordinate->uvw[2]) != 3)
				{
					if (sscanf(buffer + 2, "%f %f", &pTextureCoordinate->uvw[0], &pTextureCoordinate->uvw[1]) != 2)
					{
						if (sscanf(buffer + 2, "%f", &pTextureCoordinate->uvw[0]) != 1)
						{
							fprintf(stderr, "Error reading texture coordinates!\n");
							return 0;
						}

						else
						{
							pTextureCoordinate->uvw[1] = 0.0;
							pTextureCoordinate->uvw[2] = 0.0;
						}
					}

					else
					{
						pTextureCoordinate->uvw[2] = 0.0;
					}
				}

				pTextureCoordinate++;
			}

			// Normal vector 
			else if (buffer[1] == 'n')
			{
				if (sscanf(buffer + 2, "%f %f %f", &pNormal->ijk[0], &pNormal->ijk[1], &pNormal->ijk[2]) != 3)
				{
					fprintf(stderr, "Error reading normal vectors\n");
					return 0;
				}

				pNormal++;
			}

			break;

		} // End vertices case

		// Face
		case 'f':
		{
			pBuffer = buffer;
			pFace->num_elems = 0;

			// Count face vertices
			while (*pBuffer)
			{
				if (*pBuffer == ' ')
				{
					pFace->num_elems++;
				}

				pBuffer++;
			}

			// Select primitive type 
			if (pFace->num_elems < 3)
			{
				fprintf(stderr, "Error: a face must have at least 3 vertices\n");
				return 0;
			}

			else if (pFace->num_elems == 3)
			{
				pFace->type = GL_TRIANGLES;
			}

			else if (pFace->num_elems == 4)
			{
				pFace->type = GL_QUADS;
			}

			else
			{
				pFace->type = GL_POLYGON;
			}

			// Allocate memory for vertices
			pFace->vert_indices = (int*)malloc(sizeof(int) * pFace->num_elems);

			if (model->has_texCoords)
			{
				pFace->uvw_indices = (int*)malloc(sizeof(int) * pFace->num_elems);
			}

			if (model->has_normals)
			{
				pFace->norm_indices = (int*)malloc(sizeof(int) * pFace->num_elems);
			}

			// Read face data 
			pBuffer = buffer;
			i = 0;

			for (i = 0; i < pFace->num_elems; ++i)
			{
				pBuffer = strchr(pBuffer, ' ');
				pBuffer++; // Skip space 

				// Read vertices
				if (sscanf(pBuffer, "%d/%d/%d", &pFace->vert_indices[i], &pFace->uvw_indices[i], &pFace->norm_indices[i]) != 3)
				{
					if (sscanf(pBuffer, "%d//%d", &pFace->vert_indices[i], &pFace->norm_indices[i]) != 2)
					{
						if (sscanf(pBuffer, "%d/%d", &pFace->vert_indices[i], &pFace->uvw_indices[i]) != 2)
						{
							sscanf(pBuffer, "%d", &pFace->vert_indices[i]);
						}
					}
				}

				// Indices must start at 0 
				pFace->vert_indices[i]--;

				if (model->has_texCoords)
					pFace->uvw_indices[i]--;

				if (model->has_normals)
					pFace->norm_indices[i]--;
			}

			pFace++;
			break;

		} // End faces case
		} // End switch statement

	} //End while loop

	printf("Second pass: read\n");
	printf("   * %li vertices\n", pVertex - model->list_vertices);
	printf("   * %li texture coords.\n", pTextureCoordinate - model->list_texCoords);
	printf("   * %li normal vectors\n", pNormal - model->list_normals);
	printf("   * %li faces\n", pFace - model->list_faces);

	return 1;
}

// Load OBJ model from file
int readObjectFile(const char* filename, struct obj_model_t* model)
{
	FILE* fp;

	fp = fopen(filename, "r");
	if (!fp)
	{
		fprintf(stderr, "Error: couldn't open \"%s\"\n", filename);
		return 0;
	}

	// Reset model data 
	memset(model, 0, sizeof(struct obj_model_t));

	// First pass: read model info 
	if (!getModelData(fp, model))
	{
		fclose(fp);
		return 0;
	}

	rewind(fp);

	// Memory allocation
	if (!mallocModel(model))
	{
		fclose(fp);
		freeModelResources(model);
		return 0;
	}

	// Second pass: store model data 
	if (!storeModelData(fp, model))
	{
		fclose(fp);
		freeModelResources(model);
		return 0;
	}

	fclose(fp);
	return 1;
}

// Draw OBJ model.
void drawModel(struct obj_model_t* model)
{
	int i, j;

	for (i = 0; i < model->num_faces; ++i)
	{
		glBegin(model->list_faces[i].type);
		for (j = 0; j < model->list_faces[i].num_elems; ++j)
		{
			if (model->has_texCoords)
			{
				glTexCoord3fv(model->list_texCoords[model->list_faces[i].uvw_indices[j]].uvw);
			}

			if (model->has_normals)
			{
				glNormal3fv(model->list_normals[model->list_faces[i].norm_indices[j]].ijk);
			}

			glVertex4fv(model->list_vertices[model->list_faces[i].vert_indices[j]].xyzw);
		}

		glEnd();
	}
}

// Initialize and enable the lights and their properties
// Also set object material properties
void set_up_light(const char* filename)
{
	/* Initialize OpenGL context */
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	glShadeModel(GL_SMOOTH);

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	// Directional light 0
	glLightfv(GL_LIGHT0, GL_AMBIENT, ambient_light0);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse_light0);
	glLightfv(GL_LIGHT0, GL_SPECULAR, specular_light0);
	glLightfv(GL_LIGHT0, GL_POSITION, light_position0);
	glEnable(GL_LIGHT0);

	// Positional light 1
	glLightfv(GL_LIGHT1, GL_AMBIENT, ambient_light1);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse_light1);
	glLightfv(GL_LIGHT1, GL_SPECULAR, specular_light1);
	glLightfv(GL_LIGHT1, GL_POSITION, light_position1);
	glEnable(GL_LIGHT1);

	// Blue spot LIGHT2
	glLightfv(GL_LIGHT2, GL_DIFFUSE, diffuse_light2);
	glLightfv(GL_LIGHT2, GL_SPECULAR, specular_light2);

	glLoadIdentity();
	glLightfv(GL_LIGHT2, GL_POSITION, light_position2);
	glLightf(GL_LIGHT2, GL_SPOT_CUTOFF, cutoff);
	glLoadIdentity();
	glLightfv(GL_LIGHT2, GL_SPOT_DIRECTION, spot_direction2);
	glLighti(GL_LIGHT2, GL_SPOT_EXPONENT, exponent);
	glEnable(GL_LIGHT2);

	// Red spot LIGHT3
	glLightfv(GL_LIGHT3, GL_DIFFUSE, diffuse_light3);
	glLightfv(GL_LIGHT3, GL_SPECULAR, specular_light3);

	glLoadIdentity();
	glLightfv(GL_LIGHT3, GL_POSITION, light_position3);
	glLightf(GL_LIGHT3, GL_SPOT_CUTOFF, cutoff);
	glLightfv(GL_LIGHT3, GL_SPOT_DIRECTION, spot_direction3);

	glLighti(GL_LIGHT3, GL_SPOT_EXPONENT, exponent);
	glEnable(GL_LIGHT3);

	// Set Material properties to follow glColor values
	glMaterialfv(GL_FRONT, GL_AMBIENT, material_ambient);
	glMaterialfv(GL_FRONT, GL_DIFFUSE, material_diffuse);
	glMaterialfv(GL_FRONT, GL_SPECULAR, material_specular);
	glMaterialfv(GL_FRONT, GL_EMISSION, material_emissive);
	glMateriali(GL_FRONT, GL_SHININESS, material_shininess);

	glEnable(GL_DEPTH_TEST);
	glEnable(GL_LIGHTING);
}

// Free object resources
void cleanup()
{
	freeModelResources(&objfile);
}

// Initialize perspective view (frustum) for objects
void other_init()
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	// Cube frustum
	if (strcmp(inputFilename, "cube.obj") == 0)
	{
		glFrustum(-2, 2, -2, 2, 2, 4);
	}

	// Teapot frustum
	else if (strcmp(inputFilename, "teapot.obj") == 0)
	{
		glFrustum(-8, 8, -8, 8, 10, 50);
	}

	// Lamp frustum
	else if (strcmp(inputFilename, "lamp.obj") == 0)
	{
		glFrustum(-7, 7, -7, 7, 7, 15);
	}

	// Gangster frustum
	else if (strcmp(inputFilename, "gangster.obj") == 0)
	{
		glFrustum(-1.3, 1.3, -1.3, 1.3, 1, 10);
	}

	// Car frustum
	else if (strcmp(inputFilename, "alfaRomeo147.obj") == 0)
	{
		glFrustum(-25, 25, -25, 25, 20, 100);
	}

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

// Refresh callback function
void display()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glMatrixMode(GL_MODELVIEW);

	// If rotating the object
	if (rotateObjectsFlag == 1)
	{
		objRotateAngle += 5.0;
		glPushMatrix();
		//glTranslated(objectCenterX, objectCenterY, objectCenterZ);
		glRotated(objRotateAngle, 0.0, 1.0, 0.0);
		//glTranslated(-objectCenterX, -objectCenterY, -objectCenterZ);

		// Draw model
		drawModel(&objfile);
		glPopMatrix();
		rotateObjectsFlag = 0;
	}

	// NOT rotating object
	if (rotateObjectsFlag == 0)
	{
		glPushMatrix();
		//glTranslated(objectCenterX, objectCenterY, objectCenterZ);
		glRotated(objRotateAngle, 0.0, 1.0, 0.0);
		//glTranslated(-objectCenterX, -objectCenterY, -objectCenterZ);
		// Draw model
		drawModel(&objfile);
		glPopMatrix();
	}

	// Rotating the light
	if (rotateLightFlag == 1)
	{
		lightRotateAngle += 5.0;
		glPushMatrix();
		glRotated(lightRotateAngle, 0.0, 1.0, 0.0);
		glLightfv(GL_LIGHT1, GL_POSITION, light_position1);
		glLightfv(GL_LIGHT2, GL_POSITION, light_position3);
		glPopMatrix();

		rotateLightFlag = 0;
	}

	glFlush();
}

// Handle keyboard presses
void keyboard(unsigned char key, int x, int y)
{
	switch (key)
	{
	case '1':
		glEnable(GL_LIGHT0);	break;
	case '2':
		glEnable(GL_LIGHT1);	break;
	case '3':
		glEnable(GL_LIGHT2);	break;
	case '4':
		glEnable(GL_LIGHT3);	break;

	// Disable lights individually
	case 'Z': case 'z': glDisable(GL_LIGHT0); break;
	case 'X': case 'x': glDisable(GL_LIGHT1); break;
	case 'C': case 'c': glDisable(GL_LIGHT2); break;
	case 'V': case 'v': glDisable(GL_LIGHT3); break;

	// Disable all lights
	case '0':
		glDisable(GL_LIGHT0);
		glDisable(GL_LIGHT1);
		glDisable(GL_LIGHT2);
		glDisable(GL_LIGHT3);
		break;

	// Increase ambient light (directional LIGHT0)
	case 'A':
		if (ambient_light0[0] <= 1.0 && ambient_light0[1] <= 1.0 && ambient_light0[2] <= 1.0)
		{
			ambient_light0[0] += 0.1f;
			ambient_light0[1] += 0.1f;
			ambient_light0[2] += 0.1f;
			glLightfv(GL_LIGHT0, GL_AMBIENT, ambient_light0);
		}
		break;

	// Decrease ambient light (directional LIGHT0)
	case 'a':
		if (ambient_light0[0] >= 0.0 && ambient_light0[1] >= 0.0 && ambient_light0[2] >= 0.0)
		{
			ambient_light0[0] -= 0.1f;
			ambient_light0[1] -= 0.1f;
			ambient_light0[2] -= 0.1f;
			glLightfv(GL_LIGHT0, GL_AMBIENT, ambient_light0);
		}
		break;

	// Increase diffuse light (directional LIGHT0)
	case 'D':
		if (diffuse_light0[0] <= 1.0 && diffuse_light0[1] <= 1.0 && diffuse_light0[2] <= 1.0)
		{
			diffuse_light0[0] += 0.1f;
			diffuse_light0[1] += 0.1f;
			diffuse_light0[2] += 0.1f;
			glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse_light0);
		}
		break;

	// Decrease diffuse light (directional LIGHT0)
	case 'd':
		if (diffuse_light0[0] >= 0.0 && diffuse_light0[1] >= 0.0 && diffuse_light0[2] >= 0.0)
		{
			diffuse_light0[0] -= 0.1f;
			diffuse_light0[1] -= 0.1f;
			diffuse_light0[2] -= 0.1f;
			glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse_light0);
		}
		break;

	// Increase specular component of blue spotlight (LIGHT2)
	case 'S':
		if (specular_light2[0] <= 1.0 && specular_light2[1] <= 1.0 && specular_light2[2] <= 1.0)
		{
			specular_light2[0] += 0.1f;
			specular_light2[1] += 0.1f;
			specular_light2[2] += 0.1f;
			glLightfv(GL_LIGHT2, GL_SPECULAR, specular_light2);
		}
		break;

	// Decrease specular component of blue spotlight (LIGHT2)
	case 's':
		if (specular_light2[0] >= 0.0 && specular_light2[1] >= 0.0 && specular_light2[2] >= 0.0)
		{
			specular_light2[0] -= 0.1f;
			specular_light2[1] -= 0.1f;
			specular_light2[2] -= 0.1f;
			glLightfv(GL_LIGHT2, GL_SPECULAR, specular_light2);
		}
		break;

	// Increase cutoff angle (LIGHT2 and LIGHT3)
	case '+':
		if (cutoff >= 90.0) cutoff = 180.0;
		else if ((cutoff < 90.0) && (cutoff >= 1))cutoff += 4.0;
		glLightf(GL_LIGHT2, GL_SPOT_CUTOFF, cutoff);
		glLightf(GL_LIGHT3, GL_SPOT_CUTOFF, cutoff);
		break;

	// Decrease cutoff angle (LIGHT2 and LIGHT3)
	case '-':
		if (cutoff >= 180.0) cutoff = 89.0;
		else if (cutoff >= 5.0) cutoff -= 4.0;
		glLightf(GL_LIGHT2, GL_SPOT_CUTOFF, cutoff);
		glLightf(GL_LIGHT3, GL_SPOT_CUTOFF, cutoff);
		break;

	// Increase shininess of object
	case 'H':
		if (material_shininess >= 0.0)
		{
			material_shininess -= 5.0;
		}
		glMateriali(GL_FRONT, GL_SHININESS, material_shininess);
		break;

	// Decrease shininess of object
	case 'h':
		if (material_shininess <= 120.0)
		{
			material_shininess += 5.0;
		}
		glMateriali(GL_FRONT, GL_SHININESS, material_shininess);
		break;

	// Increase emissive light of object (blue color)
	case 'E':
		if (material_emissive[2] <= 1.0)
		{
			material_emissive[2] += 0.1f;
			glMaterialfv(GL_FRONT, GL_EMISSION, material_emissive);
		}
		break;

	// Decrease emissive light of object (blue color)
	case 'e':
		if (material_emissive[2] >= 0.0)
		{
			material_emissive[2] -= 0.1f;
			glMaterialfv(GL_FRONT, GL_EMISSION, material_emissive);
		}
		break;

	// Rotate object
	case 'O':
	case 'o':
		objectCenterX = (objectMaxX + objectMinX) / 2.0;
		objectCenterY = (objectMaxY + objectMinY) / 2.0;
		objectCenterZ = (objectMaxZ + objectMinZ) / 2.0;
		rotateObjectsFlag = 1;
		break;

	// Rotate positional light
	case 'P':
	case 'p':
		rotateLightFlag = 1;
		break;

	// Increase spotlight exponent
	case 'T':
		if (exponent < 100) exponent += 5;
		glLighti(GL_LIGHT2, GL_SPOT_EXPONENT, exponent);
		glLighti(GL_LIGHT3, GL_SPOT_EXPONENT, exponent);
		break;

	// Decrease spotlight exponent
	case 't':
		if (exponent >= 5) exponent -= 5;
		glLighti(GL_LIGHT2, GL_SPOT_EXPONENT, exponent);
		glLighti(GL_LIGHT3, GL_SPOT_EXPONENT, exponent);
		break;

	// Quit program
	case 'Q':
	case 'q':
		exit(1);

	}

	glutPostRedisplay();
}

// Initializes gluLookAt for each object
void setupInitialView()
{
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	// Cube lookAt
	if (strcmp(inputFilename, "cube.obj") == 0)
	{
		gluLookAt(0, 0, 4, 0, 0, 0, 0, 1, 0);
	}

	// Teapot lookAt
	else if (strcmp(inputFilename, "teapot.obj") == 0)
	{
		gluLookAt(0, 0, 40, 0, 0, 0, 0, 1, 0);
	}

	// Lamp lookAt
	else if (strcmp(inputFilename, "lamp.obj") == 0)
	{
		gluLookAt(0, 0, 10, 0, 0, 0, 0, 1, 0);
	}

	// Gangster lookAt
	else if (strcmp(inputFilename, "gangster.obj") == 0)
	{
		gluLookAt(0, 0, 4, 0, 0, 0, 0, 1, 0);
	}

	// Car lookAt
	else if (strcmp(inputFilename, "alfaRomeo147.obj") == 0)
	{
		gluLookAt(0, 0, 115, 0, 0, 0, 0, 1, 0);
	}
}

// GLUT Window Initialization
void init_window(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DEPTH);
	glutInitWindowSize(640, 480);
	glutCreateWindow("Project 5");
}

// Display program controls
void programControls()
{
	// Controls for program.
	printf("Program controls:\n"
		"\tPress '1' to turn on directional light (LIGHT0).\n"
		"\tPress '2' to turn on positional light (LIGHT1).\n"
		"\tPress '3' to turn on positional light (LIGHT2).\n"
		"\tPress '4' to turn on positional light (LIGHT3).\n\n"

		"\tPress 'A' to increase ambient light of LIGHT0.\n"
		"\tPress 'a' to decrease ambient light of LIGHT0.\n\n"

		"\tPress 'D' to increase diffuse light of LIGHT0.\n"
		"\tPress 'd' to decrease diffuse light of LIGHT0.\n\n"

		"\tPress 'S' to increase specular light of LIGHT2.\n"
		"\tPress 's' to decrease specular light of LIGHT2.\n\n"

		"\tPress '+' to increase cutoff angle of LIGHT2 and LIGHT3.\n"
		"\tPress '-' to decrease cutoff angle of LIGHT2 and LIGHT3.\n\n"

		"\tPress 'H' to increase shininess of object.\n"
		"\tPress 'h' to decrease shininess of object.\n\n"

		"\tPress 'E' to increase emmissive light of object.\n"
		"\tPress 'e' to decrease emmissive light of object.\n\n"

		"\tPress 'P' or 'p' to rotate positional light (LIGHT1 and LIGHT2) about the Y-axis.\n"
		"\tPress 'O' or 'o' to rotate objects around the Y-axis.\n\n"

		"\tPress 'Z' or 'z' to disable LIGHT0.\n"
		"\tPress 'X' or 'x' to disable LIGHT1.\n"
		"\tPress 'C' or 'c' to disable LIGHT2.\n"
		"\tPress 'V' or 'v' to disable LIGHT3.\n\n"

		"\tPress 'T' to increase spotlight exponent (LIGHT2 and LIGHT3).\n"
		"\tPress 't' to decrease spotlight exponent (LIGHT2 and LIGHT3).\n\n");
}

// Main function
int main(int argc, char* argv[])
{
	programControls();
	printf("Input file name: \n");
	scanf("%s", &inputFilename);

	init_window(argc, argv);
	atexit(cleanup);
	set_up_light(inputFilename);

	// Load OBJ model file 
	if (!readObjectFile(inputFilename, &objfile))
	{
		exit(EXIT_FAILURE);
	}

	other_init();
	glutDisplayFunc(display);
	glutKeyboardFunc(keyboard);
	setupInitialView();

	glutMainLoop();

	return 0;
}
