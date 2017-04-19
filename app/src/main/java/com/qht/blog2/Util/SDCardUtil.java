package com.qht.blog2.Util;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SDCardUtil {


	/**
	 * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
	 *
	 * @return
	 */
	public static boolean isSdCardExist() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}


	/**
	 * 循环获取***文件夹下文件
	 *
	 * @return
	 */
	public static List<File> getFiles(String path) {

		File root = new File(path);
		List<File> files = new ArrayList<File>();
		if(TextUtil.isEmpty(path)){
			return files;
		}
		if (!root.isDirectory()) {
			files.add(root);
		} else {
			File[] subFiles = root.listFiles();
			for (File f : subFiles) {
				files.addAll(getFiles(f.getAbsolutePath()));
			}
		}
		return files;
	}

	/**
	 * 获取文件行数
	 * @param  filename，文件名
	 */
	public static int getFileLineCounts(String filename) {
		int cnt = 0;
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(filename));
			byte[] c = new byte[1024];
			int readChars = 0;
			while ((readChars = is.read(c)) != -1) {
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++cnt;
					}
				}
			}
		} catch (Exception ex) {
			cnt = -1;
			ex.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return cnt;
	}


	/**
	 * 写入文本到SDcard
	 * @param  file，写入路径
	 * @param  content，文本内容
	 * @param  space，写入是否需要换行
	 * @throws IOException
	 */
	public static void writetxt(File file, String content, boolean space)
			throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile raf = null;
		raf = new RandomAccessFile(file, "rw");
		raf.seek(file.length());
		try {
			raf.write(content.getBytes());
			if (space)
				raf.writeBytes("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			raf.close();
		}
	}

	/**
	 * 判断手机是否插入SIM卡
	 * @param  context
	 * */
	public static  boolean judgeIS_SIM(Context context) {
		// TODO Auto-generated method stub
		TelephonyManager telManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String operator = telManager.getSimOperator();
		if(operator!=null&&!operator.equals("")){
			return true;
		}
		return false;
	}
}
