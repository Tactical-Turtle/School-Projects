// Draw objects for the reflection
void drawObjectsforMirror() 
{
	drawTable();
	glassCube();
	drawWallMirror();
	drawFloor();
}

// Draw the mirrors
void drawMirrors() 
{
	// Current color
	GLint buffers = GL_NONE;
	glGetIntegerv(GL_DRAW_BUFFER, &buffers);	

	glClearStencil(0);
	glEnable(GL_STENCIL_TEST);

	// Disable drawing to color buffer
	glColorMask(0, 0, 0, 0); 
	glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);	
	glStencilFunc(GL_ALWAYS, 1, 0xffffffff);	

	// Stencil mask
	glDisable(GL_DEPTH_TEST);
	glBegin(GL_QUADS);

	// Back wall
	glNormal3f(0.0f, 0.0f, 1.0f);
	glVertex3f(-roomSize, -2.0f, -roomSize);
	glVertex3f(roomSize, -2.0f, -roomSize);
	glVertex3f(roomSize, 8.0f, -roomSize);
	glVertex3f(-roomSize, 8.0f, -roomSize);
	glEnd();

	// Front wall
	glBegin(GL_QUADS);
	glVertex3f(-roomSize, 8.0f, roomSize);
	glVertex3f(roomSize, 8.0f, roomSize);
	glVertex3f(roomSize, -2.0f, roomSize);
	glVertex3f(-roomSize, -2.0f, roomSize);
	glEnd();

	// Enable drawing to color buffer
	glDrawBuffer((GLenum)buffers);
	glColorMask(1, 1, 1, 1); 
	glStencilFunc(GL_EQUAL, 1, 0xffffffff); 

	glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	

	glEnable(GL_DEPTH_TEST);
	glPushMatrix();

	for (float k = 2.0f; k < 20; k = k + 2) 
	{
		// Invert scene
		glScalef(1.0f, 1.0f, -1.0f);
		glTranslatef(0.0f, 0.0f, k * roomSize);
		drawObjectsforMirror();
	}

	glPopMatrix();

	glDisable(GL_STENCIL_TEST);
	glDrawBuffer(GL_NONE);

	glDrawBuffer((GLenum)buffers);
}