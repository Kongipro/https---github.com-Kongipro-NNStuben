package k.nnlib;
import org.ejml.simple.*;




public abstract class Func{
	
	
	public  abstract  String get_name();
	
	public abstract SimpleMatrix compute(SimpleMatrix input);
	
	
	public abstract SimpleMatrix computederv(SimpleMatrix input);
	
	
}