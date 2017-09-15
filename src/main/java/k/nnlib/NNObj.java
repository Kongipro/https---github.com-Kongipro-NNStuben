package k.nnlib;

import org.ejml.simple.*;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class NNObj{
	
	public NNObj (ArrayList<SimpleMatrix> weightst, ArrayList<SimpleMatrix> offsetst, ArrayList<Func> flistt, int stepst)
	{
		weights = weightst;
		offsets = offsetst;
		flist = flistt;
		steps = stepst;
		
	}
	
	
		public ArrayList<SimpleMatrix> weights;
		public ArrayList<SimpleMatrix> offsets;
		public int steps;
		
		public ArrayList<Func> flist;
		
		
		
		
		public SimpleMatrix compute(SimpleMatrix inputts)
		{
			SimpleMatrix inputs = inputts.copy();
			for(int i = 0; i < steps; i++)
			{
				inputs = inputs.mult(weights.get(i));
				inputs = inputs.plus(offsets.get(i));
				inputs = flist.get(i).compute(inputs);
				
			}
			return inputs;
		}
		
		
		public ArrayList<ArrayList<SimpleMatrix>>computenetswo(SimpleMatrix inputs)
		{
			ArrayList<SimpleMatrix> nets = new ArrayList<SimpleMatrix>();
			ArrayList<SimpleMatrix> outs = new ArrayList<SimpleMatrix>();
			outs.add(inputs.copy());
			
			for(int i = 0; i < steps; i++)
			{
			
			inputs = inputs.mult(weights.get(i));
			inputs = inputs.plus(offsets.get(i));
			nets.add(inputs.copy());
			inputs = flist.get(i).compute(inputs);
			outs.add(inputs.copy());
			}
			
			ArrayList<ArrayList<SimpleMatrix>> ret = new ArrayList<ArrayList<SimpleMatrix>>();
			ret.add(nets);
			ret.add(outs);
			
			return ret;
		}
		
		
		public void print()
		{
			for(int i = 0; i < steps; i++)
			{
				System.out.println("Step i: "+ i);
				weights.get(i).print();
				offsets.get(i).print();
				
				
			}
			System.out.println("Stepts: " + steps);
			
			
		}
		
		
		public void save_to_file(String path) throws IOException
		{
			
			for( int i = 0; i < weights.size(); i++)
			{
				
				weights.get(i).saveToFileBinary(path + "/weight"+i);
			}
			for(int i = 0; i< offsets.size(); i++)
			{
				offsets.get(i).saveToFileBinary(path + "/offset"+i);
				
			}
			
			String FString = steps + "::";
			for(int i = 0;i<  flist.size(); i++)
			{
				FString += flist.get(i).get_name() + ";";
				
			}
			
			File myFoo = new File(path +"/Description.txt");
			FileWriter fooStream = new FileWriter(myFoo, false);
			fooStream.write(FString);
			fooStream.close();
			
		}
		
		
	
}