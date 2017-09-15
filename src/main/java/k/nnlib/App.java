package k.nnlib;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.ejml.simple.*;

import GameBot.basicclass.Action;
import GameBot.basicclass.Actionvar;
import GameBot.basicclass.AlphaBetta;
import GameBot.basicclass.Game;
import GameBot.viergewinnt.VAction;
import GameBot.viergewinnt.VEvaluator;
import GameBot.viergewinnt.VState;
import GameBot.viergewinnt.VierGewinntFactory;
import k.MNIST.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void test( String[] args ) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException
    {
    	
    	
    	NNObj ob = NNFactory.restore_from_path("newNet");
    	
    
   double[][] inputs = {{1.4}};
    	

  
 
   
   
   
   
   for(int i = 0; i < 120; i++)
   {
	   inputs[0][0] = ((double) i)/20.0 - 3.0;
	   System.out.println(ob.compute(new SimpleMatrix(inputs)).get(0));
   }
   
  /*
   
   
    
    
    
  
   

    try {
    	
    	System.out.println(FuncL.class);
    	System.out.println(String.class);
		Class<?> c = Class.forName(FuncL.name);
		System.out.println(c.getName());
		
	
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
    
  
    int[] layers = {1,4,5,3};
    Random rand = new Random();
    
    
    double[][] inputs = new double[5000][1];
    double[][] outputs = new double[5000][3];
    for(int i = 0; i < 5000; i++)
    {
    	inputs[i][0] = (rand.nextDouble()-0.5)*10;
    	outputs[i][0] = Math.sin(inputs[i][0]);
    	outputs[i][1] = Math.cos(inputs[i][0]);
    	outputs[i][2] = Math.sin(Math.cos(inputs[i][0]));
    }

    SimpleMatrix inputts = new SimpleMatrix(inputs);
    SimpleMatrix outputts = new SimpleMatrix(outputs);

    
    double e = 0;
    for(int i = 0; i< 50 ; i++)
    {
    	e += NNTrainer.computeError(ob.compute(inputts.extractVector(true, i)), outputts.extractVector(true, i));
    	
    	
    }
    System.out.println("Error: "+e);
    
    NNTrainer.train(inputts, outputts, 0.25, ob);
    

    
    e = 0;
    for(int i = 0; i< 50 ; i++)
    {
    	e += NNTrainer.computeError(ob.compute(inputts.extractVector(true, i)), outputts.extractVector(true, i));
    	
    	
    }
    System.out.println("Error: "+e);
    
    
    
 String st = "newNet";
    
    
    try{
    NNFactory.save(ob,st);
    }
    catch(Exception er)
    {
    	
    }
    
    

 
    
    
    
    */
    
    
    }

    
    public static void play(String[] args) {
        Game game = VierGewinntFactory.createNewInstance(1);
       int i = 0;
        int it = 0;
      

        while(true) {
            System.out.print(game.state.toString());
            ArrayList<Action> ls = game.simulator.get_all_actions(game.state);

           
            for(i = 0; i < ls.size(); ++i) {
                System.out.println("possible:" + ((Action)ls.get(i)).act.row);
            }

            Scanner input = new Scanner(System.in);
            i = input.nextInt();
            int j = input.nextInt();
            if(i == -1) {
                return;
            }

            System.out.println("input: " + i);
            game.simulator.change_state(new VAction(new Actionvar(i, j)), game.state, it % 2 + 1);
            ++it;
        }
    }
    
    public static NNObj create_nn(String path) throws IOException
    {
    	
    	int[] key = {42,200,80,1};
    	
		NNObj ob = NNFactory.createSigmoidFFN(key);
		
		NNFactory.save(ob, path);
    	return ob;
    	
    }
    
    public static NNObj get_obj()
    {
    	return NNFactory.restore_from_path("VierGe");
    }
    
    
    public static ArrayList<ArrayList<SimpleMatrix>> load_data() throws IOException
    {
    	FileInputStream fstream = new FileInputStream("c4.data");
    	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    	String strLine;
    	
    	ArrayList<ArrayList<SimpleMatrix>>list = new ArrayList<ArrayList<SimpleMatrix>>();
    
    

    	//Read File Line By Line
    	while ((strLine = br.readLine()) != null)   {
    		double[][] tmp = new double[1][42];
    		double[][] tmp2 = new double[1][1];
    		int pos = 0;
    		
    		for( int i = 0; i < 42; i++)
    		{
    		pos = strLine.indexOf(",");
    		if(strLine.substring(0,pos).equals( "b")){
    			tmp[0][i] = 0;
    		}
    		else if (strLine.substring(0,pos).equals("x")){
    			tmp[0][i] = 1.0;
    			
    		}
    		else{
    			
    			tmp[0][i] = -1.0;
    		}	
    		
    		strLine = strLine.substring(pos+1);
    		}
    		
    		
    		if(strLine.equals("win"))
    		{
    			tmp2[0][0] = 2.0;
    		}
    		else if(strLine.equals( "loss"))
    		{
    			tmp2[0][0]= -2.0;
    		}
    		else if(strLine.equals("draw"))
    		{
    			tmp2[0][0] = 0.0;
    		}
    		else{
    			System.out.println("Etwasw ist falsch");
    			
    		}
    		
    		ArrayList<SimpleMatrix> m = new ArrayList<SimpleMatrix>();
    		
    		
    		ArrayList<SimpleMatrix> m1 = new ArrayList<SimpleMatrix>();
    		m1.add(new SimpleMatrix(tmp));
    		m1.add(new SimpleMatrix(tmp2));
    		
    		
    		
    	  // Print the content on the console
    	  list.add(m1);
    	}
    	

    	//Close the input stream
    	br.close();
    	
    	return list;
    	
    }
    
    public static void training(NNObj ob) throws IOException{
    	
    	//ArrayList<ArrayList<SimpleMatrix>> list = load_data();
    	
    
    	
    	VEvaluator ev = new VEvaluator(ob);
 
    	
    	//ob.compute(list.get(0).get(0)).print();
    	
    	//NNTrainer.train( new ArrayList<SimpleMatrix>(list.get(0).subList(0, 1)), new ArrayList<SimpleMatrix>(list.get(1).subList(0, 1)), 0.005, ob);
    	//NNTrainer.train( list.get(0), list.get(1), 0.01, ob);
    
    	//NNFactory.save(ob, "VierGe");
    	
    
    	
    	
    	  Game game = VierGewinntFactory.createNewInstance(1);
          int i = 0;
           int it = 0;
           AlphaBetta alphab = new AlphaBetta();
         
           Scanner input = new Scanner(System.in);
           while(true) {
               System.out.print(game.state.toString());
               ArrayList<Action> ls = game.simulator.get_all_actions(game.state);

              
               for(i = 0; i < ls.size(); ++i) {
               // System.out.println("possible:" + ((Action)ls.get(i)).act.row);
               }
               	
              if ( it %2  == 0)
               {
            	   float alpha = -500;
            	   float betta = +500;
            	  
            	   float tmp = -10;
            	   Action action = null;
            	
            	
            	   
            	   
            for( Action ac : game.simulator.get_all_actions(game.state))
            	{
            	game.state = game.simulator.change_state(ac, game.state, 1);
            	
            	//System.out.println("val:"+ev.evaluate(game.state));
            	tmp = alphab.perform_alphabetta(game, ev, 3, alpha, betta, 2);
            	 game.state = game.simulator.undo_change(ac, game.state);
            	if(tmp > alpha)
            	{
            		//System.out.println("juhuu" + tmp);
            		action = ac;
            		alpha = tmp;
            		
            	}
            	   }
            	   
            	   game.state = game.simulator.change_state(action, game.state, 1);
            	   ++it;
               }else{
            	  
            	   
            	   float alpha = -500;
            	   float betta = +500;
            	  
            	   float tmp = +50;
            	   Action action = null;
            	
            	
            	   
            	   
            for( Action ac : game.simulator.get_all_actions(game.state))
            	{
            	game.state = game.simulator.change_state(ac, game.state, 2);
            	
            	//System.out.println("val:"+ev.evaluate(game.state));
            	tmp = alphab.perform_alphabetta(game, ev, 3, alpha, betta, 1);
            	 game.state = game.simulator.undo_change(ac, game.state);
            	if(tmp < betta)
            	{
            		//System.out.println("juhuu" + tmp);
            		action = ac;
            		betta = tmp;
            		
            	}
            	   }
            	   
            	   game.state = game.simulator.change_state(action, game.state, 2);
            	   ++it;
              
              
            	   
            	   /*
               i = input.nextInt();
               int j = input.nextInt();
               if(i == -1) {
                  break;
                  
                  
               
               
              
               

               System.out.println("input: " + i);
               game.simulator.change_state(new VAction(new Actionvar(i, j)), game.state, it % 2 + 1);
               ++it;
               System.out.println(game.state.toString());
               
               */
               }
              
              
              if(game.state.gamestate != 0)
              {
            	  System.out.println("______________________________________________________________________________");
            	System.out.println(game.state.gamestate );  
            	 System.out.print(game.state.toString());
               break;
               
               }
               
               //input.close();
           
   
    	
    	
    }
    }
    	
    
    public static void main(String[] args) throws IOException, InterruptedException{
    
    	ArrayList<ArrayList<SimpleMatrix>> list = load_data();
    	NNObj ob = get_obj();
    	Collections.shuffle(list);
    	
    	
   	
    training(ob);
    	/*
    for( int i = 0; i <20; i++)
    	{
    		
    	System.out.println(ob.compute(list.get(i).get(0)).get(0));
    	
    	//System.out.println(list.get(1).get(i));
    	}
    	*/
    
    
 
    	
    	
  //  NNTrainer.train( new ArrayList<SimpleMatrix>(list.get(0).subList(0, 100)), new ArrayList<SimpleMatrix>(list.get(1).subList(0, 100)), 0.1, ob);
    	
 // Thread.sleep(20000);
    	//NNTrainer.train( list, 0.05, ob);
   /* 	System.out.println("GoOOOOOOOO");
    	
    for( int i = 0; i <20; i++)
	{
		
	System.out.println(ob.compute(list.get(i).get(0)).get(0));
	
	//System.out.println(list.get(1).get(i));
	}
	
    	*/
   
    	
    
    	NNFactory.save(ob, "VierGe");
    		
    
    }
    
    public static void pictures(String[] args) throws IOException, InterruptedException{
    	
    	
    	//int[] layers = {28*28, 200,80,10};
    	
    	NNObj ob = NNFactory.restore_from_path("Lesen");
    	
    	Mnist m = new Mnist("train-images.idx3-ubyte");
    	
    	
    	/*
    	int [][] bytes = m.read(0);
    	double[][] data = new double[1][bytes.length * bytes[0].length];
    	
    	
    	ArrayList<SimpleMatrix> inputs = new ArrayList<SimpleMatrix>();
    	ArrayList<SimpleMatrix> outputs = new ArrayList<SimpleMatrix>();
    	
    	for(int u = 0; u < 600; u++ ){
    		System.out.println("Neues U: " +u);
    	for(int i = 100*u; i< 100; i++)
    	{
    	bytes = m.read(i);
    	for(int j = 0; j< bytes.length; j++)
    	{
    		for(int h = 0; h < bytes[j].length; h++)
    		{
    			data[0][j*bytes[0].length + h] = (double) bytes[j][h];
    			
    		}
    	}
    	
    	inputs.add(new SimpleMatrix(data));
    	
    	int label = m.label(i);
    	
    	double[][] labell = new double[1][10];
    	labell[0][label] = 1;
    	outputs.add(new SimpleMatrix(labell));
 
  	

    	}
    	
    	
    	NNTrainer.train(inputs, outputs,0.01, ob);
    	NNFactory.save(ob, "Lesen");
    	
    	}
    	
    	
    	*/
    	for( int i = 0; i < 10; i++)
    	{
    		 int [][] bytes = m.read(i);
    	Image img = Mnist.getImageFromArray(bytes,m.nx, m.ny);
    	    		
    	    		ImageIcon icon = new ImageIcon(img);
    	            JLabel label = new JLabel(icon);
    	            JOptionPane.showMessageDialog(null, label);
    	            System.out.println(m.label(i));
    	           
    	        	double[][] data = new double[1][bytes.length * bytes[0].length];
    	            
    	        	for(int j = 0; j< bytes.length; j++)
    	        	{
    	        		for(int h = 0; h < bytes[j].length; h++)
    	        		{
    	        			data[0][j*bytes[0].length + h] = (double) bytes[j][h];
    	        			
    	        		}
    	        	}
    	        	
    	        	SimpleMatrix test = ob.compute(new SimpleMatrix(data));
    	    
    	        	
    	        	int lllabel = 0;
    	        	double acc = -10.0;
    	        	for(int g = 0; g <test.getNumElements(); g++ )
    	        	{
    
    	        		if(((double) test.get(g) ) > acc)
    	        		{
    	        			
    	        			lllabel = g;
    	        			acc = test.get(g);
    	        		}
    	        	}
    	        	
    	        	if(lllabel == m.label(i))
    	        		System.out.println("Richtig!!");
    	        	else
    	        		System.out.println("Falsch!!!");
    	        	System.out.println(acc);
    	        	
    	    	
    	}
    	
    	
    	

    	
    	
    
    
    
    	}
    
  
   
}
