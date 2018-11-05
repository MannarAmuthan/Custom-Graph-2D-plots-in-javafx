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
