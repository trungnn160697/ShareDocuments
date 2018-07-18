package com.rabiloo.sharedocument.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	public static String upload(MultipartFile file, String url, String type) {
		String image = "";
		if (!file.getOriginalFilename().isEmpty()) {
			try {
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(new File(url, file.getOriginalFilename())));
				image = type + "=" + file.getOriginalFilename();
				outputStream.write(file.getBytes());
				outputStream.flush();
				outputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}
}
