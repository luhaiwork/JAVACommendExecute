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
		Runtime run = Runtime.getRuntime();// �����뵱ǰ Java Ӧ�ó�����ص�����ʱ����
		try {
			Process p = run.exec(cmd);// ������һ��������ִ������
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null)
				// �������ִ�к��ڿ���̨�������Ϣ
				System.out.println(lineStr);// ��ӡ�����Ϣ
			// ��������Ƿ�ִ��ʧ�ܡ�
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)// p.exitValue()==0��ʾ����������1������������
					System.err.println("����ִ��ʧ��!");
			}
			inBr.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
