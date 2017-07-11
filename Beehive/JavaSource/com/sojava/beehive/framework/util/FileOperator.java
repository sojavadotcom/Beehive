package com.sojava.beehive.framework.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperator {
	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) throws Exception {
		File path = new File(folderPath);
		File parent = new File(path.getParent());
		if (!parent.exists() || !parent.isDirectory()) newFolder(parent.getAbsolutePath());
		if (!path.exists() || !path.isDirectory()) {
			path.mkdir();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) throws Exception {
		File filePath = new File(filePathAndName);
		newFolder(filePath.getParent());
		if (!filePath.exists()) {
			filePath.createNewFile();
		}
		FileWriter out = null;
		PrintWriter writer = null;
		try {
			out = new FileWriter(filePath);
			writer = new PrintWriter(out);
			writer.println(fileContent);
		}
		finally {
			writer.close();
			out.close();
		}
	}

	public static void newFile(String filePathAndName, byte[] buff) throws Exception {
		File filePath = new File(filePathAndName);
		newFolder(filePath.getParent());
		if (!filePath.exists()) {
			filePath.createNewFile();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			out.write(buff);
		}
		finally {
			out.close();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) throws Exception {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		java.io.File myDelFile = new java.io.File(filePath);
		return myDelFile.delete();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFolder(String folderPath) throws Exception {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			return myFilePath.delete(); // 删除空文件夹
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) throws Exception {
		@SuppressWarnings("unused")
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { // 文件存在时
			InputStream inStream = new FileInputStream(oldPath); // 读入原文件
			File newfile = new File(newPath);
			if (!newfile.exists()) {
				new File(newfile.getParent()).mkdirs();
				newfile.createNewFile();
			}
			FileOutputStream fs = new FileOutputStream(newfile);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
			fs.close();
			inStream.close();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) throws Exception {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}
			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath
						+ "/" + (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {// 如果是子文件夹
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) throws Exception {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) throws Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 列出指定目录内容
	 * 
	 * @param path
	 *            String 路径 如c:/fqf.txt
	 * @param actionType
	 *            FileActionType 操作类型
	 * @return boolean
	 */
	public static List<FileListType> List(String path, FileAction actionType) throws Exception {
		List<FileListType> list = new ArrayList<FileListType>();

		File files = new File(path);
		String[] fileList = files.list();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i ++) {
				String name = fileList[i];
				File file = new File(path + File.separator + name);
				if ((actionType.equals(FileAction.All)) ||
					(actionType.equals(FileAction.Directory) && file.isDirectory()) ||
					(actionType.equals(FileAction.File) && file.isFile())) {

					FileListType fileInfo = new FileListType();
					fileInfo.setName(file.getName());
					fileInfo.setPath(file.getPath());
					fileInfo.setSize(file.length());
					fileInfo.setType(file.getName().lastIndexOf(".") != -1 ? file.getName().substring(file.getName().lastIndexOf(".")+1) : "");
					fileInfo.setKind(file.isDirectory() ? "Directory" : "File");
					fileInfo.setIsHidden(file.isHidden());
					list.add(fileInfo);
				}
			}
		}

		return list;
	}
}