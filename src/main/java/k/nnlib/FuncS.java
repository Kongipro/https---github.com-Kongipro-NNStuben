package k.nnlib;
import org.ejml.simple.*;




public class  FuncS extends Func{
	
	public String get_name()
	{
		return name;
		
	}
	
	public static String name = "k.nnlib.FuncS";
	
	public SimpleMatrix compute(SimpleMatrix input)
	{
		return  input.scale(-0.05).elementExp().plus(1).elementPower(-1);
	}
	
	public SimpleMatrix computederv(SimpleMatrix input)
	{
		
	SimpleMatrix temp = input;

	return temp.scale(-0.05).elementExp().scale(0.05).elementDiv(temp.scale(-0.05).elementExp().plus(1).elementPower(2));
		
	}
	
}