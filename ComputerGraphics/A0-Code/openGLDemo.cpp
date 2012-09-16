#include <stdio.h>
#include <GL/glut.h>
#include <math.h>

float angle = 0;
int stop = 0;

void DrawWorld()
{
  glMatrixMode( GL_PROJECTION );
  glLoadIdentity();
  glMatrixMode( GL_MODELVIEW );
  glLoadIdentity();
  
  // set the background colour to be used, then apply it
  glClearColor(0,0,0,0);
  glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  // rotate around the y-axis by 'angle' degrees
  glRotatef(angle,0,1,0);
  
  // draw a triangle
  glBegin( GL_TRIANGLES );
  
  glColor3f(0,1,0);
  glVertex3f( 0.0f, 0.5f, 0.0f );
  
  glColor3f(1,1,1);
  glVertex3f( -0.5f, -0.5f, 0.0f );
  
  glColor3f(1,1,1);
  glVertex3f( 0.5f, -0.5f, 0.0f );
  
  glEnd();
  
  // draw a second traingle
  glBegin( GL_TRIANGLES );
  
  glColor3f(1,0,0);
  glVertex3f( 0.0f, 0.5f, 0.0f );
  
  glColor3f(1,1,1);
  glVertex3f( 0.0f, -0.5f, -0.5f );
  
  glColor3f(1,1,1);
  glVertex3f( 0.0f, -0.5f, 0.5f );
  
  glEnd();
  
  glColor3f(0.8, 0.8,0.8);      // choose a light-grey color
  glTranslatef(0,0,0.25);        // move torus back slightly 
  glutSolidTorus(0.1,0.5,8,20); // draw a solid torus
  
  glColor3f(0,1,0);           // green 
  glTranslatef(1,0,0);        // move one unit to the right
  glutWireTeapot(0.4);        // draw a wireframe teapot of size 0.4

  glColor3f(1,1,0);           // yellow
  glTranslatef(0,0.5,0);      // move up
  glutWireTeapot(0.2);        // draw a wireframe teapot of size 0.2
  
  glutSwapBuffers();          // force the draw-buffer to be displayed
}

void Idle()
{
  if (stop == 0)
  {
	  angle += 1.0;
  }
  glutPostRedisplay();
}

void keyhit(unsigned char key, int x, int y)
{
  switch(key) {
  case 'a':
    printf("you hit the 'a' key\n");
    break;
  case 'q':
    exit(0);
    break;
  case ' ':
    stop ^= 1;
  }
}

int main(int argc, char **argv)
{
  // create window and rendering context
  glutInit( &argc, argv );
  glutInitDisplayMode( GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH );
  glutInitWindowSize( 640, 480 );
  glutCreateWindow( "openGLDemo" );
  glutDisplayFunc( DrawWorld );
  glutIdleFunc(Idle);
  glutKeyboardFunc(keyhit);
  glEnable(GL_DEPTH_TEST);
  
  // pass control over to GLUT
  glutMainLoop();
  
  return 0;       // never reached
}

