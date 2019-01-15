/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphanalyzer.cangraph;


import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.shape.ArcType;

/**
 *
 * @author AMUTHAN
 */
public class PieBuild {
    Canvas canvas;
    GraphicsContext g;
    ArrayList<piePoints>pies;
    double total=0;

    
    
   public PieBuild(){
      canvas=new Canvas(420,600);
      g=canvas.getGraphicsContext2D();
      pies=new ArrayList<>();
      //arc 
      //third parameter represents which angle to start,
      //fourth angle how much it tobe extended
      
      //g.strokeRect(5,5, 595, 595);
      
    }
   public double getTotal() {
        return total;
    }
   public void add(piePoints p){
     pies.add(p);
   }
   public void remove(piePoints p){
   pies.remove(p);
   }
   public void finalize(){
    doCalculate();
    draw();
   }
   
   private void doCalculate(){
    
    
    for(int i=0;i<pies.size();i++){
        total=pies.get(i).getValue()+total;
     }
    
    for(int i=0;i<pies.size();i++){
         double degrees=(pies.get(i).getValue()/total)*360;
         pies.get(i).setDegrees(degrees);
         double perc=(pies.get(i).getDegrees()/360)*100;
         pies.get(i).setPercentage(perc);
         //System.out.println(pies.get(i).getName()+" "+pies.get(i).getDegrees()+" "+pies.get(i).getPercentage());
    }
    
    
   }
   private void draw(){
       double start=0,next=0;
        for(int i=0;i<pies.size();i++){
             start=start+next;
             g.setFill(pies.get(i).getColor());
             next=pies.get(i).getDegrees();
             g.fillArc(10,10, 400, 400, start, next, ArcType.ROUND);
        }
   }
   public Canvas getGraph(){
        return canvas;
    }

    
}
