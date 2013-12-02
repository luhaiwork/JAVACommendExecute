package com.test;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		// windows
		// String cmd = "F:\\apache-tomcat-6.0.20.exe";
		// String cmd =
		// "D:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXE F:\\test.doc";
		// String cmd = "cmd.exe /c start F:\\test.doc";
//		String cmd = "ping www.baidu.com";
		String cmd="test";

		// linux
		// String cmd = "./fork_wait";
		// String cmd = "ls -l";
		// String[] cmd=new String[3];
		// cmd[0]="/bin/sh";
		// cmd[1]="-c";
		// cmd[2]="ls -l ./";
		Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
		try {
			Process p = run.exec(cmd);// 启动另一个进程来执行命令
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null)
				// 获得命令执行后在控制台的输出信息
				System.out.println(lineStr);// 打印输出信息
			// 检查命令是否执行失败。
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)// p.exitValue()==0表示正常结束，1：非正常结束
					System.err.println("命令执行失败!");
			}
			inBr.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
