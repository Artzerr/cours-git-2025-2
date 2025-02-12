package org.oxyl;

public class Main {
    public static void main(String [] args){
        System.out.println("\nIt works");
    }
}

class Cercle implements Figure {
    private double centreX;
    private double centreY;
    private double rayon;

    public Cercle(double centreX, double centreY, double rayon) {
        if (rayon < 0)
        {
            rayon=0;
        }
            this.centreX = centreX;
            this.centreY = centreY;
            this.rayon = rayon;
    }

    public Cercle() {
        this(0.0, 0.0, 1.0);
    }

    public Cercle(Cercle c) {
        this(c.centreX, c.centreY, c.rayon);
    }

    public void deplacer(double distanceX, double distanceY) {
        this.centreX += distanceX;
        this.centreY += distanceY;
    }

    public boolean isGrand()
    {
        return (this.rayon > 100.0);
    }

    public void redimensionner(double f)
    {
        if (f<0) {f=0;}
        this.rayon *= f;
    }

    public void tourner(double theta){}
    //pas de methode rotation evidemment
}

class Rectangle implements Figure {
    private double centreX, centreY, longueur, largeur, angle;

    public Rectangle(double centreX, double centreY,double longueur, double largeur, double angle) {
        if (longueur < 0) {longueur=0;}
        if (largeur < 0) {largeur=0;}

        this.centreX = centreX;
        this.centreY = centreY;
        this.longueur = longueur;
        this.largeur = largeur;
        this.angle = angle;
    }

    public Rectangle() {
        this(0.0,0.0,1.0,1.0,0.0);
    }

    public Rectangle(Rectangle r) {
        this(r.centreX,r.centreY,r.longueur,r.largeur,r.angle);
    }

    public void deplacer(double distanceX, double distanceY) {
        this.centreX += distanceX;
        this.centreY += distanceY;
    }

    public boolean isCarre (){
        return(this.largeur == this.longueur);
    }

    public void redimensionner(double f) {
        if (f<0) {f=0;}
        this.longueur *= f;
        this.largeur *= f;
    }

    public void tourner(double angleTheta) {
        this.angle += angleTheta;
    }
}

class Triangle implements Figure {
    private double x1, y1, x2, y2, x3, y3;

    public Triangle (double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public Triangle() {
        this(0,0,0,1,0.5,0.5);
    }

    public Triangle (Triangle t) {
        this(t.x1,t.y1,t.x2,t.y2,t.x3,t.y3);
    }
    public void deplacer(double distanceX, double distanceY) {
        this.x1 += distanceX;
        this.y1 += distanceY;
        this.x2 += distanceX;
        this.y2 += distanceY;
        this.x3 += distanceX;
        this.y3 += distanceY;
    }

    private double arrondir(double valeur) {
        return Math.round(valeur * 100.0) / 100.0;
    }

    public void tourner(double angleTheta) {
        double barycentreX, barycentreY;
        barycentreX = (this.x1+this.x2+this.x3)/3;
        barycentreY = (this.y1+this.y2+this.y3)/3;

        double theta = Math.toRadians(angleTheta);
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        double x1Rotated = barycentreX + ( x1 -barycentreX) * cosTheta - (y1 - barycentreY) * sinTheta;
        double y1Rotated = barycentreY + ( x1 -barycentreX) * sinTheta + (y1 - barycentreY) * cosTheta;

        double x2Rotated = barycentreX + (x2 - barycentreX) * cosTheta - (y2 - barycentreY) * sinTheta;
        double y2Rotated = barycentreY + (x2 - barycentreX) * sinTheta + (y2 - barycentreY) * cosTheta;

        double x3Rotated = barycentreX + (x3 - barycentreX) * cosTheta - (y3 - barycentreY) * sinTheta;
        double y3Rotated = barycentreY + (x3 - barycentreX) * sinTheta + (y3 - barycentreY) * cosTheta;

        this.x1 = arrondir(x1Rotated);
        this.y1 = arrondir(y1Rotated);
        this.x2 = arrondir(x2Rotated);
        this.y2 = arrondir(y2Rotated);
        this.x3 = arrondir(x3Rotated);
        this.y3 = arrondir(y3Rotated);
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public boolean isEquilateral() {

        double coteA = distance(x1, y1, x2, y2);
        double coteB = distance(x2, y2, x3, y3);
        double coteC = distance(x3, y3, x1, y1);

        coteA = arrondir(coteA);
        coteB = arrondir(coteB);
        coteC = arrondir(coteC);

        return coteA == coteB && coteB == coteC;
    }

}

class Point {
    private double x,y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return Double.compare(point.x, x) == 0
                && Double.compare(point.y, y) == 0;
    }

    public double calculerDistance(Point p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

}

class CercleAvecPoint extends AUnCentre implements Figure{
    //private Point centre;
    private double rayon;

    public CercleAvecPoint(Point centre, double rayon) {
        if (rayon<0) {rayon=0;}
        this.centre = centre;
        this.rayon = rayon;
    }

    public CercleAvecPoint() {
        this.centre = new Point(0, 0);
        this.rayon = 1;
    }
    public CercleAvecPoint(CercleAvecPoint c) {
        this(c.centre, c.rayon);
    }

    public void deplacer(double distanceX, double distanceY) {
        this.centre = new Point(this.centre.getX() + distanceX, this.centre.getY() + distanceY);
        //Il n'y a pas besoin de supprimer l'ancien Point centre afin d'éviter les fuites de mémoire comme en C/C++ ?
    }

    public boolean isGrand () {
        return this.rayon>100;
    }

    public void redimensionner(double f){
        if (f<0) {f=0;}
        this.rayon *= f;
    }

    public void tourner(double theta){}
    //toujours pas de méthode tourner

}

class RectangleAvecPoint extends AUnCentre implements Figure{
    //private Point centre;
    private double longueur,largeur, angle;

    public RectangleAvecPoint(Point centre, double longueur, double largeur, double angle) {

        if (longueur<0) {longueur=0;}
        if (largeur<0) {largeur=0;}

        this.centre = centre;
        this.longueur = longueur;
        this.largeur = largeur;
        this.angle = angle;
    }

    public RectangleAvecPoint() {
        this.centre = new Point(0, 0);
        this.longueur = 1;
        this.largeur = 1;
        this.angle = 0;
    }

    public RectangleAvecPoint(RectangleAvecPoint r) {
        this.centre = new Point(r.centre.getX(), r.centre.getY());
        this.longueur = r.longueur;
        this.largeur = r.largeur;
        this.angle = r.angle;
    }

    public void deplacer(double distanceX, double distanceY) {
        this.centre = new Point(this.centre.getX() + distanceX, this.centre.getY() + distanceY);
    }

    public boolean isCarre() {
        return this.largeur==this.longueur;
    }
    public void redimensionner(double f) {
        if (f<0) {f=0;}
        this.longueur *= f;
        this.largeur *= f;
    }

    public void tourner(double angleTheta) {
        this.angle += angleTheta;
    }
}

class TriangleAvecPoint implements Figure{
    private Point point1,point2,point3;

    public TriangleAvecPoint(Point p1, Point p2, Point p3) {
        this.point1 = p1;
        this.point2 = p2;
        this.point3 = p3;
    }
    public TriangleAvecPoint() {
        this.point1 = new Point(0, 0);
        this.point2 = new Point(0, 1);
        this.point3 = new Point(0.5, 0.5);
    }

    public TriangleAvecPoint(TriangleAvecPoint t) {
        this.point1 = t.point1;
        this.point2 = t.point2;
        this.point3 = t.point3;
    }

    public void deplacer(double distanceX, double distanceY) {
        this.point1 = new Point(this.point1.getX() + distanceX, this.point1.getY() + distanceY);
        this.point2 = new Point(this.point2.getX() + distanceX, this.point2.getY() + distanceY);
        this.point3 = new Point(this.point3.getX() + distanceX, this.point3.getY() + distanceY);
    }

    private double arrondir(double valeur) {
        return Math.round(valeur * 100.0) / 100.0;
    }

    public void tourner(double angleTheta) {
        Point barycentre = new Point((this.point1.getX() + this.point2.getX() + this.point3.getX()) / 3,(this.point1.getY() + this.point2.getY() + this.point3.getY()) / 3);

        double theta = Math.toRadians(angleTheta);
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        Point point1rotated = new Point(arrondir(barycentre.getX() + (point1.getX() - barycentre.getX()) * cosTheta - (point1.getY() - barycentre.getY()) * sinTheta), arrondir(barycentre.getY() + (point1.getX() - barycentre.getX()) * sinTheta + (point1.getY() - barycentre.getY()) * cosTheta));
        Point point2rotated = new Point(arrondir(barycentre.getX() + (point2.getX() - barycentre.getX()) * cosTheta - (point2.getY() - barycentre.getY()) * sinTheta), arrondir(barycentre.getY() + (point2.getX() - barycentre.getX()) * sinTheta + (point2.getY() - barycentre.getY()) * cosTheta));
        Point point3rotated = new Point(arrondir(barycentre.getX() + (point3.getX() - barycentre.getX()) * cosTheta - (point3.getY() - barycentre.getY()) * sinTheta), arrondir(barycentre.getY() + (point3.getX() - barycentre.getX()) * sinTheta + (point3.getY() - barycentre.getY()) * cosTheta));

        this.point1 = point1rotated;
        this.point2 = point2rotated;
        this.point3 = point3rotated;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2 ) + Math.pow(p2.getY()-p1.getY(), 2));
    }
    public boolean isEquilateral() {

        double coteA = distance(this.point1, this.point2);
        double coteB = distance(this.point2, this.point3);
        double coteC = distance(this.point3, this.point1);

        coteA = arrondir(coteA);
        coteB = arrondir(coteB);
        coteC = arrondir(coteC);

        return coteA==coteB && coteA==coteC;
    }
 }

 //commentaire 1
 //commentaire 2
//commentaire 5
//Commentaire 6
//Commentaire 88
//commentaire798754
//commentaire798754zijfozjf
//commentaire798754zeorzehrzoe
//commentaire mergeFastForward
//commentaire mergeFastForward2

//git stash pop ici
//git stash pop 2
//Steziehfifzfb