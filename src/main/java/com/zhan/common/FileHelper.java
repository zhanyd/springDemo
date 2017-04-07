package com.zhan.common;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;


public class FileHelper {

	private static final int BUFFER_SIZE = 20 * 1024; // 20K
	
	/**
	 * 将文件上传至服务器
	 * 
	 * @param File src 源文件
	 * @param File dst 目标文件(需包含路径)
	 * @return boolean
	 */
	public static boolean upload(String src, String dst) {
		boolean status = true;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}
		}catch(Exception e){
			status = false;
			e.printStackTrace();
		}finally {
			try{
				if (null != in){
					in.close();
				}
				if (null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	
	
	
	
	/** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径 
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @return 
     */  
    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
        boolean flag = false;  
        File sourceFile = new File(sourceFilePath);  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
          
        if(sourceFile.exists() == false){  
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
        }else{  
            try {  
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile.exists()){  
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
                }else{  
                    File[] sourceFiles = sourceFile.listFiles();  
                    if(null == sourceFiles || sourceFiles.length<1){  
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
                    }else{  
                        fos = new FileOutputStream(zipFile);  
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                        byte[] bufs = new byte[1024*10];  
                        for(int i=0;i<sourceFiles.length;i++){  
                            //创建ZIP实体，并添加进压缩包  
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
                            zos.putNextEntry(zipEntry);  
                            //读取待压缩的文件并写进压缩包里  
                            fis = new FileInputStream(sourceFiles[i]);  
                            bis = new BufferedInputStream(fis, 1024*10);  
                            int read = 0;  
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                                zos.write(bufs,0,read);  
                            }  
                        }  
                        flag = true;  
                    }  
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } finally{  
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                    if(null != zos) zos.close();  
                    if(null != fos) fos.close();  
                    if(null != fis) fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return flag;  
    }  
    
    
    
    /** 
     * 将sourceFileList文件，打包成fileName名称的zip文件，直接下载 
     * @param sourceFileList :待压缩的文件路
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @return 
     * @throws UnsupportedEncodingException 
     */  
    public static boolean fileToZip( ArrayList<HashMap<String,Object>> sourceFileList,String fileName,HttpServletResponse res) throws UnsupportedEncodingException{  
        boolean flag = false;   
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        OutputStream fos = null;  
        ZipOutputStream zos = null;  
          
        res.setContentType("application/x-zip-compressed");
		res.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".zip");
		
        if(sourceFileList == null ||  sourceFileList.size() < 1){  
            System.out.println("待压缩的文件不存在.");  
        }else{  
            try {  
                    fos = res.getOutputStream();  
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                    byte[] bufs = new byte[1024*10];  
                    for(int i=0; i<sourceFileList.size(); i++){  
                        //创建ZIP实体，并添加进压缩包  
                        //ZipEntry zipEntry = new ZipEntry(sourceFileList.get(i).getName());  
                        ZipEntry zipEntry = new ZipEntry(sourceFileList.get(i).get("fileName").toString());  
                        zos.putNextEntry(zipEntry);  
                        
                        //读取待压缩的文件并写进压缩包里  
                        fis = new FileInputStream((File)sourceFileList.get(i).get("file"));  
                        bis = new BufferedInputStream(fis, 1024*10);  
                        int read = 0;  
                        while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                            zos.write(bufs,0,read);  
                        }  
                    }
                    zos.flush();
                    flag = true;  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } finally{  
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                    if(null != zos) zos.close();  
                    if(null != fos) fos.close();  
                    if(null != fis) fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return flag;  
    } 

}
