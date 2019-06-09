/*
Author: Lin Qi
*/

int level = 0;
int leng= 400;  //original length of segment
void koch(int x1, int y1, int x2, int y2,
                int level) {
  if (level == 0) {
    line(x1,y1,x2,y2);
    return;
  }
  //divide the line segment into three segments of equal length
  //draw an equilateral triangle that has the middle segment as its base and points outward
  //then we get 3 points of the new triangle: a , b , c
  else {     
    int xa= (int)round(x1+(x2-x1)/3.0);   
    int ya= (int)round(y1+(y2-y1)/3.0);
    int xb= (int)round((xa+x2)/2.0);
    int yb= (int)round((ya+y2)/2.0);
    int len=(int)(leng/pow(3,this.level-level+1));  //length of new segment
    float angle = atan2((y1-y2),(x2-x1)) + PI/3;   //the c point is outward
    int xc= (int)round(xa + len*cos(angle));
    int yc= (int)round(ya - len*sin(angle));
    // create the new 4 segments
    koch(x1,y1,xa,ya,level-1);
    koch(xa,ya,xc,yc,level-1);
    koch(xc,yc,xb,yb,level-1);
    koch(xb,yb,x2,y2,level-1);}
}



void setup() {
  size(800,800);
  frameRate(1); 
}

void draw() {
  background(255);  
  translate(200,400);  
  koch(0,0,200,round(-200*sqrt(3)),level);
  koch(200,round(-200*sqrt(3)),400,0,level);
  koch(400,0,0,0,level);
  // the segments have direction: 1 to 2, 2 to 3, 3 to 1
  // if a segment is backward( like 1 to 3 ),  we need an another koch 
  //function, in which the angle should be "- PI/3" instead of "+ PI/3" 
  level++;
  if (level == 7)
    level = 0;
}
