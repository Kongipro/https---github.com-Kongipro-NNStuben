package k.nnlib;

import org.ejml.simple.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class NNFactory{
	
	
	// Sigmoid FF Network
	
	public static NNObj createSigmoidFFN (int[] layers)
	{
	ArrayList<Func> Fu = new ArrayList<Func>();
	ArrayList<SimpleMatrix> weights = new ArrayList<SimpleMatrix>();
	ArrayList<SimpleMatrix> offsets = new ArrayList<SimpleMatrix>();
	Random rand = new Random();
	
	for(int i = 0; i< layers.length-1; i++)
	{
		if(i == layers.length-2){
		Fu.add(new FuncL());
		}
		else
		{
			Fu.add(new FuncS());
		}
		double[][] wstep = new double[layers[i]][layers[i+1]];
		double[][] offset = new double[1][layers[i+1]];
		
		for(int j = 0; j < layers[i]; j++)
		{
			
			for(int jj = 0; jj< layers[i+1]; jj++)
			{
				wstep[j][jj] = rand.nextDouble()*2-1;
			}
		}
		
		for(int j = 0; j< layers[i+1]; j++)
		{
			offset[0][j] = rand.nextDouble()*2-1;
		}
		
		weights.add(new SimpleMatrix(wstep));
		offsets.add(new SimpleMatrix(offset));
		
		
	}
	
	return new NNObj(weights,offsets,Fu,layers.length-1);
	
	
		
		
	}
	
	
	public static void save(NNObj net, String path) throws IOException
	{
		Path Ppath = Paths.get(path);
		
		if (!Files.exists(Ppath)) {
			File dir = new File(path);
			dir.mkdir();
		}
		
		net.save_to_file(path);
		
	}
	
	
	public static NNObj restore_from_path(String path)
	{
		
		try{
		
		
		
		Path Ppath = Paths.get(path);
		
		if (!Files.exists(Ppath)) {
			throw new IOException();
		}
		
		
		
		BufferedReader br = new BufferedReader(new FileReader(path+"/Description.txt"));
		String everything;
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		     everything = sb.toString();
		    
		    
		} 
		catch(IOException e)
		{
			throw new IOException();
		}
		finally {
		    br.close();
		}
		
		int pos1 = everything.indexOf("::");
		
		
		int numbers = Integer.parseInt( everything.substring(0,pos1));
		
		
		everything = everything.substring(pos1+2);
		
		int pos = everything.indexOf(";");
		ArrayList<Func> flist = new ArrayList<Func>();
		
		
		
		
		while(pos >= 1)
		{
			
		flist.add( (Func) Class.forName(everything.substring(0,pos)).newInstance());
		
		if(everything.lastIndexOf(";") == pos)
			break;
		everything = everything.substring(pos+1);
		pos = everything.indexOf(";");
		
		}
		
		ArrayList<SimpleMatrix> weights = new ArrayList<SimpleMatrix>();
		ArrayList<SimpleMatrix> offsets = new ArrayList<SimpleMatrix>();
		
		
		for(int i = 0; i <numbers; i++)
		{
			weights.add(SimpleMatrix.loadBinary(path + "/weight"+i));
			offsets.add(SimpleMatrix.loadBinary(path+"/offset"+i));
			
		}
		
		
		return new NNObj(weights, offsets,flist,numbers);
		
		
		
		
		}
		catch(Exception e)
		{
			System.out.println("The loading did not work properly");
			return null;
			
		}
		
		
		
	}
	
	
}
