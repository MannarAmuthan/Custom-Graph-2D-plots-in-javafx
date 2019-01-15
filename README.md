# Custom-Graph-2D-plots-in-javafx
A custom graph library , to draw  clean 2D plots in a JavaFX canvas.

#How to use
        
        //create the objects for both classes
        
        GraphBuild c=new GraphBuild();
        XYPoints xy=new XYPoints();
        
        xy.add(7,-1);xy.add(-7,2);xy.add(-10,-15);
        xy.add(-10,-15);xy.add(70,-10); //add the points of x and y
        
        //set the line allignement in graph
        xy.setHorizontal(true);
        
        //plot the 2d graph
        c.Scalexy(xy);
        c.linedraw(xy);
        
        Canvas thisCanvas=c.getCanvas();
        
        //refer response 1
        --------------------------------------------------------------------------
        
        
        
        //if
        xy.setVertical(true);
        
        //refer response 2
        --------------------------------------------------------------------------
        
        
        
        
        //if you add
        c.enableZero();
        
        //refer response 3 for with this parameter
        //refer response 4 for without this parameter
        
        --------------------------------------------------------------------------
        
        
        //you can use it with Luiz Felix's parser library
        
        JParser f=JParser.getInstance();
        
        try {
            for(int i=0;i<100;i++){
            f.setVariable(i*0.1);
            f.compileExpression("sin(x)");
            
            }   
        } catch (Exception ex) {
        }
        
        //refer reponse 5
        
        
        --------------------------------------------------------------------------
        
        note: this code is for CanvasAmuthan-updated project folder
        
        # For accessing geometrical features in the canvas
        
        XYPoints one=new XYPoints();
        a.add(one);
        one.add(12, 18); one.add(-19,34);one.add(50,-50);
       
        a.finalize();
        System.out.println(one.getXforY(8.00));
        System.out.println(one.getYforX(-1.2));
        
        //outputs...
        
        
        [2.357142857142861]
        [24.81290322580645, 12.330434782608705]
        
        //refer responseTwoUpdated
        
        -----------------------------------------------------------------
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
      
        //refer response 8
        
        
        ----------------------------------------------------------------------------
        
        For updated library project CanvasAmuthan-UPDATED
        
                XYPoints one=new XYPoints();
        XYPoints two=new XYPoints();
        XYPoints three=new XYPoints();
        
        one.add(1,1);one.add(2,2);one.add(3,3);
        one.setColor(XYPoints.Colors.RED);
        one.setHorizontal(true);
        
        two.add(-2,-5.7);two.add(6.4,-0.99);two.add(-6,-6);
        two.setColor(XYPoints.Colors.BLUE);
        two.setVertical(true);
        
        three.setColor(XYPoints.Colors.YELLOW);
        ArrayList<Double> xpoints=new ArrayList<>();
        xpoints.add(10.00);xpoints.add(20.00);xpoints.add(30.00);
        ArrayList<Double> ypoints=new ArrayList<>();
        ypoints.add(-10.00);ypoints.add(-20.00);ypoints.add(-30.00);
        three.setX(xpoints);three.setY(ypoints);
        three.setHorizontal(true);
        
        a.add(two);
        a.add(three);
        a.add(one);
        
        a.finalize();
        
        //refere responseoneUpdated
        
        -----------------------------------------------------------------------------

        
        For pie-chart
        
        PieBuild p=new PieBuild();
        c= p.getGraph();
        piePoints p1=new piePoints("amuthan",5,Color.RED);
        piePoints p2=new piePoints("deva",3.1,Color.GREEN);
        piePoints p3=new piePoints("giri",6.2,Color.BLUE);
        piePoints p4=new piePoints("amuthan",1.3,Color.BLUEVIOLET);
        piePoints p5=new piePoints("deva",10,Color.CORNSILK);
        piePoints p6=new piePoints("giri",8.6,Color.AQUAMARINE);
        p.add(p1);p.add(p2);p.add(p3);p.add(p4);p.add(p5);p.add(p6);
        p.finalize();
        
        refer response pieResponse
        -----------------------------------------------------------
        
        
