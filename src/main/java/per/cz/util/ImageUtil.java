package per.cz.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImageUtil {
	/**
	 * @param param <br>
	 * size_type<br>
	 * size_x<br>
	 * size_y<br>
	 * size_height<br>
	 * size_width<br>
	 * scale<br>
	 * scale_hints<br>
	 * scale_dis_image_type<br>
	 * @return
	 * @throws Exception
	 */
	public static synchronized BufferedImage getScreenCapture(Map<String,Object> param) throws Exception
	{
		if(param==null)
		{
			param=new HashMap<String, Object>();
		}
		if(param.get("size_type")==null)
		{
			param.put("size_type", "full");
		}
		String size_type = param.get("size_type").toString();
		Robot robot = new Robot();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage bImage=null;
		Rectangle screenRect=null;
		if(size_type.trim().equals("full"))
		{
			screenRect=new Rectangle(dim.width, dim.height);
		}
		else if(size_type.trim().equals("custom"))
		{
			int size_x= param.get("size_x")==null?0:Math.abs(Integer.parseInt(param.get("size_x").toString().trim()));
			int size_y= param.get("size_y")==null?0:Math.abs(Integer.parseInt(param.get("size_y").toString().trim()));
			int size_height= param.get("size_height")==null?dim.height:Math.abs(Integer.parseInt(param.get("size_height").toString().trim()));
			if(size_height>dim.height-size_y)
			{
				size_height=dim.height-size_y;
			}
			int size_width= param.get("size_width")==null?dim.width:Math.abs(Integer.parseInt(param.get("size_width").toString().trim()));
			if(size_width>dim.width-size_x)
			{
				size_width=dim.width-size_x;
			}
			screenRect = new Rectangle(size_x,size_y, size_width,size_height);
		}
		bImage = robot.createScreenCapture(screenRect);
		if(param.get("scale")!=null)
		{
			double scale=Math.abs(Double.parseDouble(param.get("scale").toString().trim()));
			if(scale-1!=0)
			{
				int dis_h=(int) (screenRect.height*scale);
				int dis_w=(int) (screenRect.width*scale);
				int scale_hints=Image.SCALE_SMOOTH;
				int scale_dis_image_type=BufferedImage.TYPE_INT_RGB;
				if(param.get("scale_hints")!=null)
				{
					scale_hints=Integer.parseInt(param.get("scale_hints").toString().trim());
				}
				if(param.get("scale_dis_image_type")!=null)
				{
					scale_dis_image_type=Integer.parseInt(param.get("scale_dis_image_type").toString().trim());
				}
				bImage=scale(bImage,dis_h,dis_w,scale_hints,scale_dis_image_type);
			}
		}
		return bImage;
	}
	public static synchronized BufferedImage scale(Image src, int h, int w,int scaleHints,int disImageType)
	{
		if (src == null)
		{
			return null;
		}
		BufferedImage tag = null;
		// BufferedImage src = ImageIO.read(new File(srcImageFile)); //
		//Image.SCALE_FAST
		Image image = src.getScaledInstance(w, h,scaleHints );
		//BufferedImage.TYPE_INT_RGB
		tag = new BufferedImage(w, h,disImageType);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); //
		g.dispose();
		// ImageIO.write(tag, "JPEG", new File(result));//

		return tag;
	}
	/**
	 * @param in
	 * @param formatName jpg|png|jpeg
	 * @param out File|OutputStream|ImageOutputStream
	 * @return
	 * @throws IOException
	 */
	public static synchronized boolean saveImage(BufferedImage in, String formatName,Object out) throws IOException
	{
		if(out !=null&&in!=null)
		{	
			if(formatName==null||formatName.trim().equals(""))
			{
				formatName="jpg";
			}
			if(out instanceof File)
				return ImageIO.write(in, formatName,(File) out);
			else if(out instanceof OutputStream )
				return ImageIO.write(in, formatName, (OutputStream )out);
			else if(out instanceof ImageOutputStream  )
				return ImageIO.write(in, formatName, (ImageOutputStream  )out);
		}
		return false;
	}
	/**
	 * @param in File|InputStream|URL|ImageInputStream
	 * @return BufferedImage
	 * @throws IOException
	 */
	public static synchronized BufferedImage ReadImage(Object in) throws IOException
	{
		if(in !=null)
		{
			if(in instanceof File)
				return ImageIO.read((File)in);
			else if(in instanceof InputStream)
				return ImageIO.read((InputStream)in);
			else if(in instanceof URL)
				return ImageIO.read((URL)in);
			else if(in instanceof ImageInputStream )
				return ImageIO.read((ImageInputStream )in);
		}
		return null;
	}
	//////////////////获取图像像素矩阵\\\\\\\\\
	public static  int[]getPixArray(Image img, int x, int y, int w, int h, int off, int scansize){
		int[] pix=new int[w*h];
		PixelGrabber pg=null;
		try{
			//im, 0, 0, w, h, pix, 0, w
			pg = new PixelGrabber(img, x, y, w, h, pix, off, scansize);
			if(pg.grabPixels()!=true)
				try{
					throw new java.awt.AWTException("pg error"+pg.status());
				}catch(Exception eq){
					eq.printStackTrace();
				}
		} catch(Exception ex){
			ex.printStackTrace();

		}
		return pix;
	}
	//////////////////灰度转换\\\\\\\\\\\
	public static  void RGBtoGray(int[] ImageSource){
		int length=ImageSource.length;
		ColorModel colorModel=ColorModel.getRGBdefault();
		int k,r,g,b;
		for(k=0;k<length;k++)
		{
			r = colorModel.getRed(ImageSource[k]);
			g = colorModel.getGreen(ImageSource[k]);
			b = colorModel.getBlue(ImageSource[k]);
			int gray=(int)(r*0.3+g*0.59+b*0.11);
			r=g=b=gray;
			ImageSource[k]=(255 << 24) | (r << 16) | (g << 8 )| b;
		}
	}

	public static void main(String[] args) {
		try {
			Map<String,Object> m=new HashMap<String, Object>();
//			m.put("scale", 1);
			m.put("size_type", "custom");
			m.put("size_x", 100);
			m.put("size_y", 100);
			m.put("size_height", 100);
			m.put("size_width", 100);
			ImageUtil.saveImage(ImageUtil.getScreenCapture(m),"png",new File("F:\\11.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
