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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author AMUTHAN
 */
public class XYPoints {
    
    public ArrayList<Double> x,y,xunits,yunits;
    public HashMap<Double,Double> allX,allY;;
    boolean isZeroscaled=false,verticalOrdered=false,horiOrdered=false;
   public XYPoints(){
      x=new ArrayList<>();
      y=new ArrayList<>();
      xunits=new ArrayList<>();
      yunits=new ArrayList<>();
      allX=new HashMap<>();
      allY=new HashMap<>();
      //default minimum point
      
    }
    public void add(double x1,double y1){
       x.add(x1);y.add(y1);
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
    public void setYunits(ArrayList<Double> yu){
        yunits=yu;
    }
    public  void setXunits(ArrayList<Double> xu){
        xunits=xu;
    }
    public ArrayList<Double> getYunits(){
        return yunits;
    }
    public ArrayList<Double> getXunits(){
        return xunits;
    }
   public Double getMinXunit(){
       return Collections.min(xunits);
   } 
   public Double getMinYunit(){
         return Collections.min(yunits);
   }
   public Double getMaxXunit(){
        return Collections.max(xunits);
   } 
   public Double getMaxYunit(){
   return Collections.max(yunits);
   }
   public void setAllx(HashMap<Double,Double> xm){
      allX=xm;
   }
   public void setAlly(HashMap<Double,Double> ym){
      allY=ym;
   }
   
   //TO GET THE POINTS OF GEOMETRIC KEY
   public Double getXvalue(Double key){
       if(allX.get(key)!=null){
         return allX.get(key);
       }
       else{
         return getXvalue(getNearest(allX,key));
      }
     
      
   }
   public Double getYvalue(Double key){
    if(allY.get(key)!=null){
         return allY.get(key);
       }
       else{
         return getYvalue(getNearest(allY,key));
      }
     
   }
   
   
   public void setVertical(boolean dot){
     verticalOrdered=dot;
     horiOrdered=!dot;
   }
   public void setHorizontal(boolean dot){
     horiOrdered=dot;
     verticalOrdered=!dot;
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
   
     // TO GET GEOMETRIC VALUE FOR THE POINTS
     public double getAxisX(Double val){
      for(Double key:allX.keySet()){
      if(allX.get(key).equals(val)){
         return key;
      }
      }
      return getAxisX(sortAndGetX(allX,val));
     }
     
     
     public double getAxisY(Double val){
         for(Double key:allY.keySet()){
      if(allY.get(key).equals(val)){
         return key;
      }
      }
     return getAxisY(sortAndGetY(allY,val));
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
    }



   

