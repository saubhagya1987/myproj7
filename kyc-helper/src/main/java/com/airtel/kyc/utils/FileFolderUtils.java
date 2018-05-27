package com.airtel.kyc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.security.crypto.codec.Base64;

public class FileFolderUtils {
	
	
	/**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
	public static void unZipIt(InputStream zipFile, String outputFolder) {
		byte[] buffer = new byte[1024];
		try {
			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(zipFile);
			
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);
				System.out.println("file unzip : " + newFile.getAbsoluteFile());
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static String extractAll(InputStream zipFileStream, String outputFolder) {
		try {
	           // get the zip file content
				ZipInputStream zis = new ZipInputStream(zipFileStream);
				// get the zipped file list entry
				ZipEntry zipEntry = zis.getNextEntry();
				long l = System.currentTimeMillis();
				outputFolder = outputFolder + l;
				while(zipEntry !=  null) {
	              //  ZipEntry zipEntry = (ZipEntry) enu.nextElement();
	                String name = zipEntry.getName();
	                long size = zipEntry.getSize();
	                long compressedSize = zipEntry.getCompressedSize();
	                System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
	                        name, size, compressedSize);

	                // Do we need to create a directory ?
	                File file = new File(outputFolder+name);
	                if (name.endsWith("/")) {
	                    file.mkdirs();
	                    zipEntry = zis.getNextEntry();
	                    continue;
	                }

	                File parent = file.getParentFile();
	                if (parent != null) {
	                    parent.mkdirs();
	                }

	                // Extract the file
	    			FileOutputStream fos = new FileOutputStream(file);
	    			 byte[] buffer = new byte[1024];
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
	    			
	                zipEntry = zis.getNextEntry();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return outputFolder;
	}

	public static String encodeFileToBase64Binary(File file)
			throws IOException {
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encode(bytes);
		String encodedString = new String(encoded);
		return encodedString;
	}
	
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}

	public static void main(String[] args) {
		extractAll(null,null);
	}
	
}
