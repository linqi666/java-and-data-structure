PShape earth;
PShape moon;
PImage img1;
PImage img2;

void setup() {
  size(800,800,P3D);
  PImage img1 = loadImage("earth.jpg");
  PImage img2 = loadImage("moon.jpg");
  noStroke();
  sphereDetail(1000);
  earth = createShape(SPHERE, 100);
  moon = createShape(SPHERE, 25);
  earth.setTexture(img1);
  moon.setTexture(img2);
  frameRate(60);
}

float orbit = 0;

final float DEG2RAD = PI/180;
void draw() {
  background(0);
  pushMatrix();
  translate(width/2, height/2, -500);
  rotateZ(23.5 * DEG2RAD);
  rotateY(orbit*28.5);
  shape(earth); 
  orbit+=0.003;
  popMatrix();
  pushMatrix();
  translate(width/2, height/2, -500);
  rotateZ(5.6 * DEG2RAD);
  rotateY(orbit);  
  translate(400,0,0);
  rotateY(-orbit);  
  shape(moon);
  popMatrix();
    
}
