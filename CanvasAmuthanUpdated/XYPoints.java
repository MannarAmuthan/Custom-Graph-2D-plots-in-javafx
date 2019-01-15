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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.paint.Color;

/**
 *
 * @author AMUTHAN
 */
public class XYPoints {
    
    public ArrayList<Double> x,y,xunits,yunits,orderedX,orderedY;
    public ArrayList<ArrayList>betweenpoints;
    public HashMap<Double,Double> allX,allY;
    Colors color;
    public enum Colors{
    GREEN,RED,BLUE,WHITE,YELLOW
    }
    boolean isZeroscaled=false,verticalOrdered=false,horiOrdered=false;
   public XYPoints(){
      x=new ArrayList<>();
      y=new ArrayList<>();
      xunits=new ArrayList<>();
      yunits=new ArrayList<>();
      allX=new HashMap<>();
      allY=new HashMap<>();
      betweenpoints=new ArrayList<>();
      
      //default minimum point
      
    }

    public void setX(ArrayList<Double> x) {
        this.x = x;
    }

    public void setY(ArrayList<Double> y) {
        this.y = y;
    }
   
   // SETTERS AND GETTERS OF BEHAVIOURS, SET OR GET BY ANY USER CLASS
    public void setColor(XYPoints.Colors c){
      color=c;
    }
    public Color getColor(){
    if(color==XYPoints.Colors.RED){return Color.color(1, 0, 0);}
    if(color==XYPoints.Colors.BLUE){return Color.color(0, 0, 1);}
    if(color==XYPoints.Colors.WHITE){return Color.color(1, 1, 1);}
    if(color==XYPoints.Colors.YELLOW){return Color.color(1, 1, 0);}
    else{return Color.color(0,1,0);}
    }
    public void add(double x1,double y1){
       x.add(x1);y.add(y1);
    }
    public void remove(double x1,double y1){
       x.remove(x1);y.remove(y1);
    }
    public ArrayList<Double> getX(){
        return x;
    }
    public ArrayList<Double> getY(){
        return y;
    }
    public double getMinX(){
     return Collections.min(x);
    }
    public double getMinY(){
     return Collections.min(y);
    }
    public double getMaxX(){
     return Collections.max(x);
    }
    public double getMaxY(){
     return Collections.max(y);
    }

   //BEHAVIOUR OF XYPOINTS, WHETHER HORI OR VERTICAL, SET BY USER
   public void setVertical(boolean dot){
     verticalOrdered=dot;
     horiOrdered=!dot;
   }
   public void setHorizontal(boolean dot){
     horiOrdered=dot;
     verticalOrdered=!dot;
   }
   //SETTERS AND GETTERS , YOU CAN GET THE ORDEREDX VALUES(HORIZONTALLY OR VERTICULARLY)
   //SET BY THE GRAPHBUILD CLASS
   public void setOrderedX(ArrayList<Double> list){
      this.orderedX=list;  
   }
   public void setOrderedY(ArrayList<Double> list){
      this.orderedY=list;  
   }
   public ArrayList<Double> getOrderedX(){
   return orderedX;
   }
   public ArrayList<Double> getOrderedY(){
   return orderedY;
   }
   
   
   
   
   public Double getNearest(HashMap<Double,Double> mp,Double key){
       
       double minDiff=Double.MAX_VALUE;
       Double nearest=null;
       for(Double ke:mp.keySet()){
       double diff=Math.abs((double)key-(double)ke);
       if(diff<minDiff){
         nearest=ke;
         minDiff=diff;
       }
       }
       return nearest;
       }
   
   
     public void enableAxis(){
         isZeroscaled=true;
      add(-(getMaxX()+getMaxX()),-(getMaxY()+getMaxY()));
     }
     
     
     //IF AXIS IS NOT MATCHING,WE HAVE TO RETURN NEAREST THING
     
     public double sortAndGetX(HashMap<Double,Double> mp, Double val){
         
     Map <Double,Double> map= new TreeMap <Double,Double> (mp);
     Set s=map.entrySet();
     Iterator i=s.iterator();
     double curr = 0,prev=0.00;
     while(i.hasNext()){
     Map.Entry me=(Map.Entry)i.next();
     curr=(double) me.getValue();
     
     if(prev<val&&curr>val){if((val-prev)>(curr-val)){
          return curr;
       }else{
          return prev;
        } 
     
     }
     //System.out.println(prev+" "+curr);
     prev=curr;
     }
     //if all are failed, then user asking maximum value so we return the last point
     return curr;
     }
     public double sortAndGetY(HashMap<Double,Double> mp, Double val){
         
     Map <Double,Double> map= new TreeMap <Double,Double> (mp);
     Set s=map.entrySet();
     Iterator i=s.iterator();
     double curr = 0,prev=0.00,firstgeo=0.0;
     int first=0;
     while(i.hasNext()){
         
     first=first+1;
     Map.Entry me=(Map.Entry)i.next();
     curr=(double) me.getValue();
     if(first==1.0){firstgeo=curr;}
     if(prev>val&&curr<val){if((prev-val)>(val-curr)){
          return curr;
       }else{
          return prev;
        } 
     
     }
   // System.out.println(prev+" "+curr);
     prev=curr;
     }
    //if all failed, the user probably asking maximum value,so
     return firstgeo;
     }

     
     
     
    public void display(HashMap<Double, Double> mp) {
        
        Map <Double,Double> map= new TreeMap <Double,Double> (mp);
     Set s=map.entrySet();
     Iterator i=s.iterator();
     double key = 0,val=0.00;
     while(i.hasNext()){
     Map.Entry me=(Map.Entry)i.next();
     key=(double) me.getKey();
     val=(double) me.getValue();
     System.out.println(key+" "+val);
     
     }
    
    }
    
    //slope functions
    
    public void BetweenAndSlope(double x1,double y1,double x2,double y2){
        double slope=(y2-y1)/(x2-x1);
        ArrayList<Double>temp=new ArrayList<>();
        temp.add(x1);temp.add(y1);temp.add(x2);temp.add(y2);temp.add(slope);
        betweenpoints.add(temp);
    
    }
    public void showSlopes(){
    for(int i=0;i<betweenpoints.size();i++){
      for(int j=0;j<betweenpoints.get(i).size();j++)
      {
        System.out.print(" "+betweenpoints.get(i).get(j));
        }  
      System.out.println();
    }}
    
    public ArrayList<Double> getYforX(double x){
        ArrayList<Double> ret=new ArrayList<>();
      for(int i=0;i<betweenpoints.size();i++){
      ArrayList<Double> currList=betweenpoints.get(i);
      if(currList.get(0)==x){
         ret.add(currList.get(1));
      }
      if(currList.get(2)==x){
         ret.add(currList.get(3));
      }
      
      if(currList.get(0)<x&&x<currList.get(2)||currList.get(0)>x&&x>currList.get(2)){
        double yval=currList.get(3)-(currList.get(4)*(currList.get(2)-x));//m=(y2-y1)/(x2-x1), so we have to find y1
        ret.add(yval);
      }
    }  Set<Double> r=new LinkedHashSet<>(ret); 
       ret.clear();
       ret.addAll(r);
        return ret;
    }
    public ArrayList<Double> getXforY(double y){
        ArrayList<Double> ret=new ArrayList<>();
      for(int i=0;i<betweenpoints.size();i++){
      ArrayList<Double> currList=betweenpoints.get(i);
      if(currList.get(1)==y){
         ret.add(currList.get(0));
      }
      if(currList.get(3)==y){
         ret.add(currList.get(2));
      }
      if(currList.get(1)<y&&y<currList.get(3)||currList.get(1)>y&&y>currList.get(3)){
        double xval=currList.get(2)-((currList.get(3)-y)/currList.get(4));//m=(y2-y1)/(x2-x1), so we have to find y1
        
        
        ret.add(xval);
      }
    }
       Set<Double> r=new LinkedHashSet<>(ret); 
       ret.clear();
       ret.addAll(r);
      return ret;
    }

    
    
    //Exculsively made for use the tools of this class
   public double getAxisXforbuild(Double val,HashMap<Double,Double> mapx){
   for(Double key:mapx.keySet()){
      if(mapx.get(key).equals(val)){
         return key;
      }
      }
      return getAxis(mapx,sortAndGetX(mapx,val));
   }
    public double getAxisYforbuild(Double val,HashMap<Double,Double> mapy){
   for(Double key:mapy.keySet()){
      if(mapy.get(key).equals(val)){
         return key;
      }
      }
      return getAxis(mapy,sortAndGetY(mapy,val));
   }
    public double getAxis(HashMap<Double,Double> map,Double val){
    for(Double key:map.keySet()){
      if(map.get(key).equals(val)){
         return key;
      }
      
    }
    return 0;
    }
    public Double getXvalueforbuild(HashMap<Double,Double> mapx,Double key){
       if(mapx.get(key)!=null){
         return mapx.get(key);
       }
       else{
         return getValue(mapx,getNearest(mapx,key));
      }
     
      
   }
   public Double getYvalueforbuild(HashMap<Double,Double> mapy,Double key){
    if(mapy.get(key)!=null){
         return mapy.get(key);
       }
       else{
         return getValue(mapy,getNearest(mapy,key));
      }
     
   }
    public double getValue(HashMap<Double,Double> map,Double key){
     if(map.get(key)!=null){
         return map.get(key);
       }
   
    return 0;
    }
    
}

    
    



   

