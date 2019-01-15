/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphanalyzer.cangraph;

import javafx.scene.paint.Color;

/**
 *
 * @author AMUTHAN
 */
public class piePoints {
    String name;
    double value;
    double percentage,degrees;
    Color color;
    
    public piePoints(String name,double value,Color c){
    this.name=name;
    this.value=value;
    this.color=c;
    }
     public piePoints(){}

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
