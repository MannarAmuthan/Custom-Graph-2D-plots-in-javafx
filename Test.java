/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphanalyzer;

import graphanalyzer.cangraph.GraphBuild;
import graphanalyzer.cangraph.XYPoints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import parser.JParser;

/**
 *
 * @author AMUTHAN
 */
public class Test {
   
    Test(){
       
    }
     VBox getLayOut(){
        VBox v=new VBox();
        GraphBuild c=new GraphBuild();
        XYPoints xy=new XYPoints();
        JParser f=JParser.getInstance();
        
        try {
            for(int i=0;i<100;i++){
            f.setVariable(i*0.1);
            f.compileExpression("sin(x)");
            xy.add(i,f.evaluate());
            f.compileExpression("x");
            xy.add(i,f.evaluate());
            }   
        } catch (Exception ex) {
        }
         
        //c.enableZero(xy);
        xy.setHorizontal(true);
        c.Scalexy(xy);
        
        c.linedraw(xy);
        //System.out.println(xy.sortAndGetX(xy.allX,1234.00));
        
        
        v.getChildren().add(c.getGraph());
        return v;
     
     }
    
    
    
}
