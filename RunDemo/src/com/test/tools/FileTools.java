package com.test.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTools {
	public static void createFile(String FilePath, String text)
			throws IOException {
		File file = new File(FilePath);
		file.getParentFile().mkdirs();
		FileWriter fw = new FileWriter(file);
		fw.write(text);
		fw.flush();
		fw.close();
	}

//	public static void main(String[] args) {
//		// System.out.println("test1");
//		try {
//			createFile("D:/tmp/aa.txt", "ssssssssssssssssssssss");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
