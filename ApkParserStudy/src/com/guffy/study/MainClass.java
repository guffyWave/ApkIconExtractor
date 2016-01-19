package com.guffy.study;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import net.dongliu.apk.parser.ApkParser;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.UseFeature;

public class MainClass {

	public static void main(String[] args) {

		String apkPath = "/home/gufran/Desktop/app-release.apk";
		String outputIconPath = "/home/gufran/Desktop/";

		try {
			ApkParser apkParser = new ApkParser(apkPath);
			ApkMeta apkMeta = apkParser.getApkMeta();
			// System.out.println(apkMeta.getLabel());
			// System.out.println(apkMeta.getPackageName());
			// System.out.println(apkMeta.getVersionCode());

			String iconPath = apkMeta.getIcon();

			ZipFile zipFile = new ZipFile(apkPath);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				// System.out.println(entry.getName() + "");
				if (entry.getName().equals(iconPath)) {
					System.out.println(entry.getName() + "");
					InputStream inputStream = zipFile.getInputStream(entry);
					FileOutputStream fileOutputStream = new FileOutputStream(
							new File(outputIconPath + "/icon.png"));
					IOUtils.copy(inputStream, fileOutputStream);
					inputStream.close();
					fileOutputStream.close();
					System.out.println("Icon exctracted successfully !");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
