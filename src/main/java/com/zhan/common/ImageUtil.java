package com.zhan.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.alibaba.simpleimage.ImageFormat;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.io.ByteArrayInputStream;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;

public class ImageUtil {
	
	
	public static boolean reSize(String orgFile,String desFile, int height, int width) {
		File in = new File(orgFile);      //原图片
		File out = new File(desFile);       //目的图片
        ScaleParameter scaleParam = new ScaleParameter(width, height);  //将图像缩略到1024x1024以内，不足1024x1024则不做任何处理
        
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);
        
            wr.render();                            //触发图像处理
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
	}
	
	public static void copyImage(String orgFile,String desFile) {
		File in = new File(orgFile);      //原图片
		File out = new File(desFile);       //目的图片
		
    	FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
        	 inStream = new FileInputStream(in);
             outStream = new FileOutputStream(out);
             ImageRender rr = new ReadRender(inStream);
             
             ImageFormat imageFormat;
             if( orgFile.endsWith(".png") ){
            	 imageFormat = ImageFormat.PNG;
             }else if( orgFile.endsWith(".jpg") ){
            	 imageFormat = ImageFormat.JPEG;
             }else if( orgFile.endsWith(".gif") ){
            	 imageFormat = ImageFormat.GIF;
             }else{
            	 imageFormat = ImageFormat.UNKNOWN;
             }
             
             wr = new WriteRender(rr, outStream, imageFormat);
             wr.render();
        }catch(Exception e){
        	e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
	}

    public static void copyImage(byte[] bytes, String desFile) {
    	File pf = new File(desFile).getParentFile();
    	if( !pf.exists() ){
    		pf.mkdirs();
    	}
    	ByteArrayInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
        	 inStream = new ByteArrayInputStream(bytes);
             outStream = new FileOutputStream(new File(desFile));
             ImageRender rr = new ReadRender(inStream);
             wr = new WriteRender(rr, outStream);
             wr.render();
        }catch(Exception e){
        	e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
	}
    
    
    /**
     * 上传图片不改变其格式
     * @param bytes
     * @param orgFileName
     * @param desFile
     */
    public static void copyImageOriginal(byte[] bytes,String orgFileName, String desFile) {
    	File pf = new File(desFile).getParentFile();
    	if( !pf.exists() ){
    		pf.mkdirs();
    	}
    	ByteArrayInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
        	 inStream = new ByteArrayInputStream(bytes);
             outStream = new FileOutputStream(new File(desFile));
             ImageRender rr = new ReadRender(inStream);
             
             ImageFormat imageFormat;
             if( orgFileName.endsWith(".png") ){
            	 imageFormat = ImageFormat.PNG;
             }else if( orgFileName.endsWith(".jpg") ){
            	 imageFormat = ImageFormat.JPEG;
             }else if( orgFileName.endsWith(".gif") ){
            	 imageFormat = ImageFormat.GIF;
             }else{
            	 imageFormat = ImageFormat.UNKNOWN;
             }
             
             wr = new WriteRender(rr, outStream,imageFormat);
             wr.render();
        }catch(Exception e){
        	e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
	}
}