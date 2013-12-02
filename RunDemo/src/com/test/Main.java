package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.test.tools.FileTools;

public class Main {
	public static void main(String[] args) {
		// String cmd = "ping www.baidu.com";
		String databaseName="databaseName";
		String toChangUserName="toChangUserName";
		String toChangUserPass="toChangUserPass";
		String path = "D:/tmp/sss/aa.bat";
		String cmd = "sqlplus  sys/sys@"+databaseName+" as sysdba @"+path;
		StringBuilder sb = new StringBuilder();
		sb.append("alter user "+toChangUserName+" identified by "+toChangUserPass+";");
		sb.append("\n");
		sb.append("exit;");
		try {
			FileTools.createFile(path, sb.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
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
		//ִ����ɺ�ɾ�����ɵ��ļ�
		FileTools.deleteFile(path);
	}
}
