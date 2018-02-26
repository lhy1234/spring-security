package com.imooc.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.dto.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {
	
	//上传的文件路径
	private String filePath = "F:/";
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception, IOException{
		
		System.err.println(file.getName());
		System.err.println(file.getOriginalFilename());
		System.err.println(file.getSize());
		
		String localPath = "F:/";
		File localFile = new File(localPath,new Date().getTime()+".txt");
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	//下载
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws IOException{
			
		InputStream is = new FileInputStream(new File(filePath,id+".txt"));
			OutputStream os = response.getOutputStream();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			//文件复制
			IOUtils.copy(is, os);
			os.flush();
	}

}
