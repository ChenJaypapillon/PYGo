package com.pinyougou.manger.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 文件上传controller
 * @author ChainJay
 *
 */
import org.springframework.web.multipart.MultipartFile;

import entity.Result;
import util.FastDFSClient;
@RestController()
public class UploadController {
	
	@Value("${FILE_SERVER_URL}")
	private  String FILE_SERVER_URL;//文件服务器地址 
	
	@RequestMapping("/upload")
	public Result upload(MultipartFile file) {
		
		
		System.out.println("进到uploadcontroller了");
		//1.去文件的扩展名
		String originalFilename = file.getOriginalFilename();//获取文件名的全名称
		
		String extName =originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		
		try { 
			//2、创建一个 FastDFS 的客户端 
			
			  util.FastDFSClient fastDFSClient  = new FastDFSClient("classpath:config/fdfs_client.conf"); 
			   //3、执行上传处理 
			   String path = fastDFSClient.uploadFile(file.getBytes(), extName); 
			 
			   String url = FILE_SERVER_URL+path;    
			   
			   System.out.println("完整路径是:"+url);
			   return new Result(true,url);    
			  } catch (Exception e) { 
			   e.printStackTrace(); 
			   return new Result(false, "上传失败"); 
			  }   
			 }
	}

