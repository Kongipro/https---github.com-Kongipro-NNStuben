package k.nnlib;

import k.nnlib.NNObj;

import java.util.ArrayList;


import org.ejml.simple.*;

public class NNTrainer {
	
	public static NNObj train(ArrayList<ArrayList<SimpleMatrix>> inputs, double factor, NNObj network) throws Error, InterruptedException
	{
		
		int columns = inputs.size();
		
		
		
		
		for( int i = 0; i < columns; i++)
		{
			network = trainsingle(inputs.get(i).get(0),inputs.get(i).get(1), factor, network);
			
			
			
		}
		

		
		
		
		return network;
		
		
		
		
	}
	
	
	public static NNObj train(ArrayList<SimpleMatrix> inputs, ArrayList<SimpleMatrix> outputs, double factor, NNObj network) throws Error, InterruptedException
	{
		
		int columns = inputs.size();
		
		if(columns != outputs.size())
		{
			throw new Error();
		}
		
		
		for( int i = 0; i < columns; i++)
		{
			network = trainsingle(inputs.get(i),outputs.get(i), factor, network);
			
			
			
		}
		
	
		
	


		
		
		
		
		
		
		return network;
		
		
		
		
	}
	
	
	private static NNObj trainsingle(SimpleMatrix inputs, SimpleMatrix outputs, double factor, NNObj network) throws InterruptedException
	{
		
		ArrayList<ArrayList<SimpleMatrix>> netswo = network.computenetswo(inputs);
		
		
		int size = network.steps;
		
		SimpleMatrix ti = netswo.get(1).get(size-1);
		SimpleMatrix pi = network.flist.get(size-1).computederv(netswo.get(0).get(size-1));
		System.out.println(computeError(netswo.get(1).get(size),outputs));
		SimpleMatrix di = netswo.get(1).get(size).minus(outputs).elementMult(pi);
		
		
		
		// go trough the steps
		
		for(int j = size-1 ; j >= 0; j--)
		{
			
		
		int row = network.weights.get(j).numRows();
		int colums = network.weights.get(j).numCols();
		
		double[][] adweights = new double[row][colums];
		for( int ii = 0 ; ii < row; ii++)
		{
			
			
			for(int jj = 0; jj < colums; jj++)
			{
				
			
				
				adweights[ii][jj] = -factor*di.get(jj)*ti.get(ii);
				
			}
		}
		
			
		SimpleMatrix adw = new SimpleMatrix(adweights);
			
		
		
		
		
		if( j != 0)
		{
			
		ti = netswo.get(1).get(j-1);
		pi = network.flist.get(j-1).computederv(netswo.get(0).get(j-1));
		di = di.mult(network.weights.get(j).transpose()).elementMult(pi);
		
		}
		
		
		network.weights.set(j, network.weights.get(j).plus(adw));
		
		
			
			
			
		}
		
		
		
		
		
		
		
		
		return network;
		
		
		
		
	}
	
	
	public static double computeError(SimpleMatrix outputs, SimpleMatrix goutputs )
	{
		
		goutputs = goutputs.minus(outputs);
	
		
		
		goutputs = goutputs.elementPower(2);
		goutputs = goutputs.scale(0.5);
		return goutputs.elementSum();
		
		
	}
	
	
	
	
	
}

