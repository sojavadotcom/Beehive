package com.sojava.beehive.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static byte[] compress(Map<String, byte[]> srcFiles) {
		byte[] result = null;

		try {
			ByteArrayOutputStream bof = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(bof);
			ZipOutputStream zos = new ZipOutputStream(bos);
			Set<String> entryNames = srcFiles.keySet();

			for(String entryName: entryNames) {

				// 创建Zip条目
				ZipEntry entry = new ZipEntry(entryName);
				zos.putNextEntry(entry);

				ByteArrayInputStream bif = new ByteArrayInputStream(srcFiles.get(entryName));
				BufferedInputStream bis = new BufferedInputStream(bif);

				byte[] b = new byte[1024];
				while (bis.read(b, 0, 1024) != -1) {
					zos.write(b, 0, 1024);
				}
				bis.close();
				bif.close();
				zos.closeEntry();
			}

			zos.flush();
			zos.close();

			bof.close();
			result = bof.toByteArray();
		} catch (IOException e) {}

		return result;
	}

	public static Map<String, byte[]> decompress(InputStream src) {
		Map<String, byte[]> result = new HashMap<String, byte[]>();
		try {
			BufferedInputStream bis = new BufferedInputStream(src);
			ZipInputStream zis = new ZipInputStream(bis, CheckUtil.getCharset(bis));

			BufferedOutputStream bos = null;

			//byte[] b = new byte[1024];
			ZipEntry entry = null;
			while ((entry=zis.getNextEntry()) != null) {
				String entryName = entry.getName();
				ByteArrayOutputStream buf = new ByteArrayOutputStream();
				bos = new BufferedOutputStream(buf);
				int b = 0;
				while ((b = zis.read()) != -1) {
					bos.write(b);
				}
				bos.flush();
				bos.close();

				buf.close();
				result.put(entryName, buf.toByteArray());
			}
			zis.close();
		} catch (IOException e) {}

		return result;
	}
}
