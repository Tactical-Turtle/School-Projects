float cameraAngle = 0.0f;			// Camera angle
float cameraDirectionX = 0.0;		// Camera direction X
float cameraDirectionZ = -1.0f;		// Camera direction Z
float cameraX = -1.0f;				// Camera X postion
float cameraY = 1.0f;				// Camera Y postion
float cameraZ = 13.0f;				// Camera Z postion
float updateAngle = 0.0f;			// Update the camera angle by the amount

// Camera movement with arrow keys
void handleSpecialKeypress(int key, int x, int y) 
{
	float fraction = 0.8f;

	switch (key) 
	{
		case GLUT_KEY_LEFT:
			cameraAngle -= 0.1f;
			cameraDirectionX = sin(cameraAngle);
			cameraDirectionZ = -cos(cameraAngle);
			break;
		case GLUT_KEY_RIGHT:
			cameraAngle += 0.1f;
			cameraDirectionX = sin(cameraAngle);
			cameraDirectionZ = -cos(cameraAngle);
			break;
		case GLUT_KEY_UP:
			cameraX += cameraDirectionX * fraction;
			cameraZ += cameraDirectionZ * fraction;
			break;
		case GLUT_KEY_DOWN:
			cameraX -= cameraDirectionX * fraction;
			cameraZ -= cameraDirectionZ * fraction;
			break;
	}
}

// Update camera angle
void updateCamera(int value) 
{
	updateAngle += 1.0f;

	if (updateAngle > 360) 
	{
		updateAngle -= 360;
	}

	glutPostRedisplay();
	glutTimerFunc(25, updateCamera, 0);
}
