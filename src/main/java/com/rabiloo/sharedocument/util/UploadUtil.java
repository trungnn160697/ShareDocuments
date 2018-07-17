package com.rabiloo.sharedocument.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	public static String upload(MultipartFile file) {
		String image = "";
		if (!file.getOriginalFilename().isEmpty()) {
			try {
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(new File(Constants.URL_IMAGE_USER, file.getOriginalFilename())));
				image = "image=" + file.getOriginalFilename();
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
