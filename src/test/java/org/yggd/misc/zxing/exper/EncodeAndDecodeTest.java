package org.yggd.misc.zxing.exper;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class EncodeAndDecodeTest {

	public static final String JPG_FILE_NAME = "QR.jpg";
	public static final String PNG_FILE_NAME = "QR.png";
	public static final String BMP_FILE_NAME = "QR.bmp";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File jpg = new File(JPG_FILE_NAME);
		File png = new File(PNG_FILE_NAME);
		File bmp = new File(BMP_FILE_NAME);
		jpg.delete();
		png.delete();
		bmp.delete();
	}

	/**
	 * JPG file format test.
	 */
	@Test
	public void testQRJpg() {
		QRWriter writer = new QRWriterImpl();
		File jpgFile = new File(JPG_FILE_NAME);
		writer.write("http://www.google.com/", jpgFile);

		QRReader reader = new QRReaderImpl();
		String text = reader.read(jpgFile);
		assertEquals("http://www.google.com/", text);
	}

	@Test
	public void testQRPng() throws IOException {
		QRWriter writer = new QRWriterImpl();
		File pngFile = new File(PNG_FILE_NAME);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(pngFile);
			writer.write("http://www.google.com/", PNG_FILE_NAME, fos);
		} finally {
			fos.close();
		}

		QRReader reader = new QRReaderImpl();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(pngFile);
			String text = reader.read(fis);
			assertEquals("http://www.google.com/", text);
		} finally {
			fis.close();
		}
	}

	@Test
	public void testQRBmp() {
		QRWriter writer = new QRWriterImpl();
		File f = new File(BMP_FILE_NAME);
		writer.write("hoge fuga...", f);

		String text = new QRReaderImpl().read(f);
		assertEquals("hoge fuga...", text);
	}

}
