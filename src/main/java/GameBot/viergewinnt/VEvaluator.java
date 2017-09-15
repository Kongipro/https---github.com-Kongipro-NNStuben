//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameBot.viergewinnt;

import org.ejml.simple.SimpleMatrix;

import GameBot.basicclass.Evaloator;
import GameBot.basicclass.State;
import GameBot.basicclass.Statevar;
import k.nnlib.NNObj;

public class VEvaluator extends Evaloator {
	
	NNObj Ev;
    public VEvaluator(NNObj o) {
    	Ev = o;
    }

    public float evaluate(State state) {
    	
    	Statevar v = state.var;
    	double [][] m = new double[1][v.board.length*v.board[0].length];
    	for(int i = 0; i <v.board.length; i++)
    	{
    		for ( int j = 0; j< v.board[i].length; j++)
    			{
    			if(v.board[i][j] == 2)
    			{
    				m[0][i*v.board[0].length+j] = -1.0;
    			}else {
    				
    			
    			m[0][i*v.board[0].length+j] = (double) v.board[i][j];
    			}
    			}
    	}
    	
    	SimpleMatrix mat = new SimpleMatrix(m);
    	
    
        float f = (float) Ev.compute(mat).get(0);
        
        if( f > 1000 || f < -1000)
        	System.out.println("hmmmmm"+ f);
        
        return f;
        
    }
}
