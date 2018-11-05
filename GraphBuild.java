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
    
   
    public GraphBuild(){
        canvas=new Canvas(600,600);
        g=canvas.getGraphicsContext2D();
        minpoints=new ArrayList<>();
        maxpoints=new ArrayList<>();
        formXandY();
        //XandYunits();
        g.strokeRect(5, 5, 595, 595);
        
        
    }   

    private void formXandY() {
      g.setLineWidth(2);  
      g.strokeRect(100, 100, 400, 400);// FOR MAIN FRAME
      g.setLineWidth(0.5);
      g.setStroke(Color.color(0, 0, 0,0.6));
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
    
    public void Scalexy(XYPoints p){
         setXYMatrix(p);
     }

    
   //parse the geometrical keys and points values   
    void setXYMatrix(XYPoints p){
          double unitx=(-p.getMinX()+p.getMaxX())/400;
          double currx=p.getMinX();
          HashMap<Double,Double> xmat=new HashMap<>();
          HashMap<Double,Double> ymat=new HashMap<>();
          System.out.println("min of x"+currx);
          for(int i=0;i<401;i++){
             xmat.put(i+100.00,currx);
             currx=currx+unitx;
           }
          p.setAllx(xmat);
          double unity=(-p.getMinY()+p.getMaxY())/400;
          double curry=p.getMinY();
          System.out.println("min of y"+curry);
          for(int i=400;i>-1;i--){
             ymat.put(i+100.00,curry);
             curry=curry+unity;
            }
          p.setAlly(ymat);
          
          XandYunits(p);
     }
    
    void XandYunits(XYPoints p){
      redraw();  
      unitsPaint();
      g.setStroke(Color.color(0, 0, 0,0.6));
      for(int i=0;i<11;i++){
      int l=100+40*i; 
      g.strokeText(String.format("%.4f",p.getXvalue(Double.valueOf(l))), l, 515);
      }
      
      for(int i=0;i<11 ;i++){
      int l=100+40*i;
      g.strokeText(String.format("%.4f",p.getYvalue(Double.valueOf(l))), 80, l);
      }
      drawzero(p);
      //linedraw(p);
      
    }
    
    public double getXval(XYPoints p,Double key){
         return p.getXvalue(key);
    }
    public double getYval(XYPoints p,Double key){
        return p.getYvalue(key);
    }
    public double getXaxis(XYPoints p,Double val){
        return p.getAxisX(val);
    }
    public double getYaxis(XYPoints p,Double val){
        return p.getAxisY(val);
    }
    
    public Canvas getGraph(){
        return canvas;
    }
    public void enableZero(XYPoints p){
       g.clearRect(0,0,600,600);
       redraw();
       p.enableAxis();
       formXandY();
       Scalexy(p); 
       drawzero(p);
       
    }
    public void redraw(){
    formXandY();
    g.strokeRect(5, 5, 595, 595);
    }
    
    
    public void drawzero(XYPoints p){
    
    if(p.getMinX()<=0){
        //FOR GET A THICK LINE SO 2 TIMES
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        double xp=getXaxis(p,0.00);
        g.strokeLine(xp,100, xp,500);
    }
    if(p.getMinY()<=0){
        //FOR GET A THICK LINE SO 2 TIMES
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        g.setStroke(Color.color(0,0,0,1));
        g.setLineWidth(1.5);
        double yp=getYaxis(p,0.00);
        g.strokeLine(100,yp,500,yp);
    }
    
     }
    
     
    
    
    
    
     private void XandYunits() {
      unitsPaint();
      for(int i=1;i<10;i++){
      int l=100+40*i; 
      g.strokeText(String.valueOf(i), l, 515);
      }
      for(int i=1;i<10;i++){
      int l=100+40*i; 
      g.strokeText(String.valueOf(i), 80, l);
      }
    }
     
     void unitsPaint(){
      g.setLineWidth(0.5);
      Font t=new Font("Arial",8);
      g.setFont(t);
      g.setLineDashes(0);
      g.setTextAlign(TextAlignment.CENTER);
     }

    public void linedraw(XYPoints p) {
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
      
      
      int iterate=x.size()-1;
      for(int i=0;i<iterate;i++){
          int next=i+1;
          g.setStroke(Color.color(0,1,0,1));
          g.setLineWidth(1);
         
          System.out.println(x.size());
          double xx=getXaxis(p,x.get(i));
          double yy=getYaxis(p,y.get(i));
          double xxx=getXaxis(p,x.get(next));
          double yyy=getYaxis(p,y.get(next));
          g.strokeLine(xx, yy,xxx,yyy);
      }
      
      
      
    }
    
    
    
    public ArrayList<ArrayList> sorterByX(ArrayList<Double> arr1,ArrayList<Double> arr2){
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
            }
            
           
         }
         
        } 
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
            }
            
           
         }
         
        } 
        sortedY.add(arr1);
        sortedY.add(arr2);
        return sortedY;
    }
}
