package k.nnlib;
import org.ejml.simple.*;




public class  FuncL extends Func{
	
	
	public String get_name()
	{
		return name;
	}
	public static String name = "k.nnlib.FuncL";
	
	public SimpleMatrix compute(SimpleMatrix input)
	{
		return  input.scale(0.5);
	}
	
	public SimpleMatrix computederv(SimpleMatrix input)
	{
		
	
	
	return input.scale(0).plus(0.5);
		
	}
	
}