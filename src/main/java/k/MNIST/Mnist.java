package k.MNIST;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.MemoryImageSource;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.file.*;

public class Mnist
{

public int length, nx, ny, nr;
public int label(int nr){ return a[nr+8]&0xff; }
byte[] b, a;

public Mnist( String failas ) throws IOException
{
	Path path = Paths.get(failas);
	
	b = Files.readAllBytes( path );
	a = Files.readAllBytes( Paths.get(failas.replace("images.idx3","labels.idx1")) );
	length = readInt(4);
	nx = readInt(8);
	ny = readInt(12);
	System.out.println( length );
}
public int readInt( int off )
{
	return (b[off+3]&0xff) | ((b[off+2]&0xff)<<8) | ((b[off+1]&0xff)<<16) | ((b[off]&0xff)<<24);
}
public int[][] read( int nr )
{
	int[][] d = new int[nx][ny];
	for ( int y = 0, xy = 16 + nr*nx*ny; y != ny; y++)
	for ( int x = 0; x != nx; x++)
	{
		//int g = 255 - (b[xy++]&0xff);
		d[y][x] = b[xy+y*nx+x]&0xFF;
				// 0xff000000 | g | ( g << 8 ) | ( g << 16 ); 
	}
	

	this.nr = nr;
	return d ;
}
/*public static void binarize( MyImage mi, final int trshld, int[][] g )
{
 final int w = mi.w, h = mi.h;
 int[] d = mi.data;
 for ( int j = 0, n = 0; j != h; j++ )
	for ( int i = 0; i != w; i++, n++ )
		g[j][i] = ( ( d[n]&0xff ) >= trshld ) ? 0 : 0xff;
}*/

public static void toGrey( int[][] g)
{
 final int w = g.length, h = g[0].length;
 for ( int j = 0; j != h; j++ )
	for ( int i = 0; i != w; i++ )
 		g[j][i] = ( g[j][i] == 0 ) ? 0xff000000 : 0xffffffff;
}


public static Image getImageFromArray(int[][] pixels, int width, int height) {
	byte [] pixelsi = new byte[width*height];
	for(int i = 0; i < height; i++)
	{
		for(int j = 0; j< width; j++)
		{
			pixelsi[i*height+j] = (byte) pixels[i][j];

			
			
		}
		
	}
	

    
    return getImageFromArray(pixelsi,width,height);
}


public static Image getImageFromArray(byte[] pixels, int width, int height) {
	
	    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	    byte [] newData = ((DataBufferByte) newImage.getRaster().getDataBuffer()).getData();

	    for (int i = 0; i < pixels.length; i++)
	    {
	        newData[i] = pixels[i];
	    }
	    return newImage;

}
/*
public static void main( String[] args )
{
	
	/
	MyImage.fr = new javax.swing.JFrame[17*10];
	Mnist m = new Mnist( "d:/bastys/academic/ate/skaiciai/mnist/train-images.idx3-ubyte" );
	Thinning t =  new Thinning();
	int[][] g = new int[0][0];
	for ( int n = 0; n != m.length; n++ ) if ( m.label(n) == 6 )
	{
		MyImage mi = m.read(n);
		mi.draw(""+m.label(n));
		if ( g.length != mi.h || g[0].length != mi.w )
			g = new int[mi.h][mi.w];
		binarize( mi, 128, g);
		g = t.processImage( g );
		toGrey(g);
		new MyImage(g).draw(""+m.label(n));
	}
}

*/

	
}