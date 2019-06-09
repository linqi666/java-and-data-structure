/*
 author: lin qi
 */
void setup() {
  size(800,800);
}

void draw() {
  // x = cos(15*t), y = cos(13*t);
  translate(width/2, height/2);
  scale(width/3, height/3);
  strokeWeight(0);
  float step=0.00001;
  for (float t = 0; t < 2*PI; t+= step){
    float x=cos(15*t);
    float y=cos(13*t);
    point(x,y);}
  
}
