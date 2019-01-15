/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphanalyzer.cangraph;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author AMUTHAN
 */
public class GraphBuild {
    Canvas canvas;
    GraphicsContext g;
    ArrayList<Double>minpoints,maxpoints;
    ArrayList<XYPoints>points;
    double minix,maxix,miniy,maxiy;
    public HashMap<Double,Double> xmatrix,ymatrix;
    XYPoints def=new XYPoints();
    boolean enablezeroed; 
    String labelx,labely;
    
   
    public GraphBuild(){
        canvas=new Canvas(600,600);
        g=canvas.getGraphicsContext2D();
        minpoints=new ArrayList<>();
        maxpoints=new ArrayList<>();
        points=new ArrayList<>();
        formXandY();
        xmatrix=new HashMap<>();
        ymatrix=new HashMap<>();
        g.strokeRect(5, 5, 595, 595);
        //XandYunits();
        
        
        
    } 
    public void add(XYPoints n){
      points.add(n);
    }
    public void remove(XYPoints n){
      points.remove(n);
    }

    private void formXandY() {
      //  XandYunits();
      g.setStroke(Color.color(0,0,0));
      g.setLineWidth(2);  
      g.strokeRect(100, 100, 400, 400);// FOR MAIN FRAME
      g.setLineWidth(0.5);
      g.setLineDashes(2.00);
      for(int i=1;i<11;i++){
         int j=100+40*i; 
         g.strokeLine(100,j, 500,j);//sublines
           /*co ordinates for horizontal sublines (100,140,500,140)(100,180,500,180)
           (100,220,500,220)(100,260,500,260)(100,300,500,300)(100,340,500,340)
           (100,380,500,380)(100,420,500,420)(100,460,500,460)*/
         
      }
      for(int k=1;k<11;k++){
         int l=100+40*k; 
         g.strokeLine(l,100,l,500);//sublines
           /*co ordinates for horizontal sublines (140,100,140,500)(180,100,180,500)
           (220,100,220,500)(260,100,260,500)(300,100,300,500)(340,100,340,500)
           (380,100,380,500)(420,100,420,500)(460,100,460,500)*/
         
      }
     //XandYunits();//default x y
    }
    
    public void Scalexy(){
        findminmax();
        setXYMatrix();
        XandYunits();
     }
    public void finalize(){  
    Repaint();
    for(int i=0;i<points.size();i++){
         linedraw(points.get(i));}
    }

    
   //parse the geometrical keys and points values   
    private void setXYMatrix(){
      
          double unitx=(-minix+maxix)/400;
          double currx=minix;
          HashMap<Double,Double> xmat=new HashMap<>();
          HashMap<Double,Double> ymat=new HashMap<>();
          //System.out.println("min of x"+currx);
          for(int i=0;i<401;i++){
             xmat.put(i+100.00,currx);
             currx=currx+unitx;
           }
          xmatrix=xmat;
          double unity=(-miniy+maxiy)/400;
          double curry=miniy;
         // System.out.println("min of y"+curry);
          for(int i=400;i>-1;i--){
             ymat.put(i+100.00,curry);
             curry=curry+unity;
            }
          ymatrix=ymat;
          
          //XandYunits(p);
     }
    
    private void findminmax(){
      
      //new code
      double minx,miny,maxx,maxy;
      minx=points.get(0).getMinX();
      miny=points.get(0).getMinY();
      maxx=points.get(0).getMaxX();
      maxy=points.get(0).getMaxY();
      for(int i=0;i<points.size();i++){
       if(points.get(i).getMinX()<minx){minx=points.get(i).getMinX();}
       if(points.get(i).getMinY()<miny){miny=points.get(i).getMinY();}
       if(points.get(i).getMaxX()>maxx){maxx=points.get(i).getMaxX();}
       if(points.get(i).getMaxY()>maxy){maxy=points.get(i).getMaxY();}
      }
      minix=minx;maxix=maxx;miniy=miny;maxiy=maxy;
      //System.out.println("minix"+minix+"maxix"+maxix+"miniy"+miniy+"maxiy"+maxiy);
      if(enablezeroed){
         double tx=minix,ty=miniy,bx=maxix,by=maxiy;
         if(minix<0){tx=-minix;}if(miniy<0){ty=-miniy;}if(maxix<0){bx=-maxix;}if(maxiy<0){by=-maxiy;}
         if(tx>bx){ maxix=tx;}else{minix=-bx;}
         if(ty>by){ maxiy=ty;}else{miniy=-by;}
      }
      
     // minix=minix-(minix/5);miniy=miniy-(miniy/5);maxix=maxix+(maxix/5);maxiy=maxiy+(maxiy/5);
      
      
    }
    
      private void XandYunits(){
      unitsPaint();    
      double unitx=(-minix+maxix)/10;
      double currx=minix;
      g.setStroke(Color.color(0, 0, 0,0.6));
      for(int i=0;i<11;i++){
      int l=100+40*i; 
      g.strokeText(String.format("%.3f",def.getXvalueforbuild(xmatrix,Double.valueOf(l))),l, 515);
      currx=currx+unitx;
      }
      double unity=(-miniy+maxiy)/10;
      double curry=miniy;
      for(int i=10;i>=0 ;i--){
      int l=100+40*i;
      g.strokeText(String.format("%.3f",def.getYvalueforbuild(ymatrix,Double.valueOf(l))), 80, l);
      curry=curry+unity;
      } }
    
    public Canvas getGraph(){
        return canvas;
    }
    public void setenableZero(boolean b){
       enablezeroed=b;
       
    }
     private void Repaint(){
       g.clearRect(0,0,600,600);
       formXandY();
       g.strokeRect(5, 5, 595, 595);
       formXandY();
       Scalexy(); 
       drawzero();
       
    }
    private void drawzero(){
    
    if(minix<=0){
        //FOR GET A THICK LINE SO 2 TIMES
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5); 
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        double xp=def.getAxisXforbuild(0.00,xmatrix);
        g.strokeLine(xp,100, xp,500);
    }
    if(miniy<=0){
        //FOR GET A THICK LINE SO 2 TIMES
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        double yp=def.getAxisYforbuild(0.00,ymatrix);
        g.strokeLine(100,yp,500,yp);
    }
    
     }
    
     
    
    
     private void unitsPaint(){
      g.setLineWidth(0.5);
      Font t=new Font("Arial",8);
      g.setFont(t);
      g.setLineDashes(0);
      g.setTextAlign(TextAlignment.CENTER);
     }

     
     
     
     
    private void linedraw(XYPoints p) {
      ArrayList<Double> x=p.getX();
      ArrayList<Double> y=p.getY();
      
      
      if(p.isZeroscaled){x.remove(x.size()-1);y.remove(y.size()-1);}
      
      
      if(p.horiOrdered){
      x=sorterByX(x,y).get(0);
      y=sorterByX(x,y).get(1);
      
      }
      if(p.verticalOrdered){
      x=sorterByY(x,y).get(0);
      y=sorterByY(x,y).get(1);
      }
      
      p.setOrderedX(x);
      p.setOrderedY(y);
      
      int iterate=x.size()-1;
      p.betweenpoints.clear();
      for(int i=0;i<iterate;i++){
          int next=i+1;
          g.setStroke(p.getColor());
          g.setLineWidth(1.5);
          
          
          double xx=def.getAxisXforbuild(x.get(i),xmatrix);
          double yy=def.getAxisYforbuild(y.get(i),ymatrix);
         double xxx=def.getAxisXforbuild(x.get(next),xmatrix);
          double yyy=def.getAxisYforbuild(y.get(next),ymatrix);
          p.BetweenAndSlope(x.get(i),y.get(i),x.get(next),y.get(next));
          g.strokeLine(xx, yy,xxx,yyy);
          g.setStroke(Color.color(0,0,0));
      }
    }  private ArrayList<ArrayList> sorterByX(ArrayList<Double> arr1,ArrayList<Double> arr2){
        ArrayList<ArrayList>sortedX=new ArrayList<>(); 
        for(int i=0;i<arr1.size();i++){
         for(int j=arr1.size()-1;j>i;j--){
            if(arr1.get(i)>arr1.get(j)){
            double temp=arr1.get(i);
            arr1.set(i,arr1.get(j));
            arr1.set(j, temp);
            
            double temp2=arr2.get(i);
            arr2.set(i,arr2.get(j));
            arr2.set(j, temp2);
            } }} 
        sortedX.add(arr1);
        sortedX.add(arr2);
        return sortedX;
        
    }

    private ArrayList<ArrayList> sorterByY(ArrayList<Double> arr1, ArrayList<Double> arr2) {
         ArrayList<ArrayList>sortedY=new ArrayList<>(); 
        for(int i=0;i<arr2.size();i++){
         for(int j=arr2.size()-1;j>i;j--){
            if(arr2.get(i)>arr2.get(j)){
            double temp=arr1.get(i);
            arr1.set(i,arr1.get(j));
            arr1.set(j, temp);
            
            double temp2=arr2.get(i);
            arr2.set(i,arr2.get(j));
            arr2.set(j, temp2);
            }  }
         
        } 
        sortedY.add(arr1);
        sortedY.add(arr2);
        return sortedY;
    }
    
    
    
    
    
}
