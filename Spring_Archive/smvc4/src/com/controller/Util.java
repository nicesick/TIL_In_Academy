package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf) {
		byte[] data;
		String imgname = mf.getOriginalFilename();
		String imgPath = "C:/Users/student/spring/smvc3/web/img/";
		
		try {
			data = mf.getBytes();
			FileOutputStream fo = new FileOutputStream(imgPath + imgname);
			fo.write(data);
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
