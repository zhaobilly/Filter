package com.cs.filter.watermark;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {

	public static byte[] waterMark(byte[]imageData,String waterMarkFile) throws IOException{
		int paddingRight=10;
		int paddingBottom=10;
		
		Image image=new ImageIcon(imageData).getImage();
		int imageWidth=image.getWidth(null);
		int imageHeight=image.getHeight(null);
		
		Image waterMark=ImageIO.read(new File(waterMarkFile));
		int waterWidth=waterMark.getWidth(null);
		int waterHeight=waterMark.getHeight(null);
		
		if(imageWidth<waterWidth+2*paddingRight||imageHeight<waterHeight+2*paddingBottom){
			return imageData;
		}
		
		BufferedImage bufferdImage=new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB);
		Graphics g=bufferdImage.createGraphics();
		g.drawImage(image, 0,0,imageWidth,imageHeight,null);
		g.drawImage(waterMark, imageWidth - waterWidth - paddingRight,
				imageHeight - waterHeight - paddingBottom, waterWidth,
				waterHeight, null);
		g.dispose();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bufferdImage);
		byte[] data=out.toByteArray();
		out.close();
		return data;
	}
}
