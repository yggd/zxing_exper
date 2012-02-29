package org.yggd.misc.zxing.exper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Encode text into QRCode image.
 */
public class QRWriterImpl implements QRWriter {

	/**
	 * default image size(pixel).
	 */
	public static final int DEFAULT_IMAGE_SIZE = 200;

	/**
	 * Write into Image as QRCode.
	 * size as default (200px * 200px)
	 *
	 * @param text text(embedded as URL)
	 * @param fileName image file name
	 * @param os OutputStream
	 */
	public void write(String text, String fileName, OutputStream os) {
		write(text, fileName, os, DEFAULT_IMAGE_SIZE, DEFAULT_IMAGE_SIZE);
	}

	/**
	 * Write into Image as QRCode.
	 *
	 * @param text text(embedded as URL)
	 * @param fileName image filename
	 * @param os OutputStream
	 * @param width image width(px)
	 * @param height image height(px)
	 */
	public void write(String text, String fileName, OutputStream os, int width,
			int height) {
		assert text != null && fileName != null : "text and file format must be not null.";
		assert os != null : "output stream must be not null.";
		assert width > 0 && height > 0 : "width and height must be positive size.";

		// It is considered the name of a format is an extension.
		int lastperiodIndex = fileName.lastIndexOf('.');
		assert lastperiodIndex > 0 : "filename must have extention.[" + fileName + "]";
		String format = fileName.substring(lastperiodIndex + 1);

		try {
			BitMatrix matrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE,
					width, height);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
			ImageIO.write(image, format, os);
		} catch (WriterException e) {
			throw new ImageConvertException(e);
		} catch (IOException e) {
			throw new ImageConvertException(e);
		}
	}

	/**
	 * Write into Image as QRCode.
	 * size as default (200px * 200px)
	 *
	 * @param text text(embedded as URL)
	 * @param file output image file
	 */
	public void write(String text, File f) {
		write(text, f, DEFAULT_IMAGE_SIZE, DEFAULT_IMAGE_SIZE);
	}

	/**
	 * Write into Image as QRCode.
	 * @param text text(embedded as URL)
	 * @param format Image format
	 * @param file output image file
	 * @param width image width(px)
	 * @param height image height(px)
	 */
	public void write(String text, File f, int width, int height) {
		assert text != null && f != null : "text and file must be not null.";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			write(text, f.getAbsolutePath(), fos, width, height);
		} catch (FileNotFoundException e) {
			throw new ImageConvertException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// ignore file close error
				}
			}
		}
	}
}
