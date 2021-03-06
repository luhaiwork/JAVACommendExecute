package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.test.tools.FileTools;
/**
 * 
 * @author luhai_laptop
 *
 *oracle 查看密码过期时间
 *SELECT * FROM dba_profiles s
    WHERE s.profile='DEFAULT' AND resource_name='PASSWORD_LIFE_TIME'
 */
public class Main {
	public static void main(String[] args) {
		if(!checkForExecOnce()){
			System.out.println("非法执行，请联系运营商");
			return ;
		}
		// String cmd = "ping www.baidu.com";
		String databaseName="a";
		String toChangUserName="a";
		String toChangUserPass="a";
		String path = "D:/tmp/sss/aa.bat";
		String cmd = "sqlplus  sys/sys@"+databaseName+" as sysdba @"+path;
		StringBuilder sb = new StringBuilder();
		sb.append(" ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;");
		sb.append("\n");//必须加入\n否则命令不执行
		sb.append("alter user "+toChangUserName+" identified by "+toChangUserPass+";");
		sb.append("\n");
		sb.append("exit;");
		try {
			FileTools.createFile(path, sb.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
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
		//执行完成后删除生成的文件
		FileTools.deleteFile(path);
	}
	private static boolean checkForExecOnce(){
		//注意 system32 文件能正确建立，但是写不进去东西的。
		String path="C:/Windows/System32/system_key.key";
		File file = new File(path);
		if(file.exists()){
			return false;
		}
		try {
			FileTools.createFile(path,"expire 2013");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return true;
	}
}
