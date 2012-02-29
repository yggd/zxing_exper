package org.yggd.misc.zxing.exper;

import java.io.File;
import java.io.OutputStream;

/**
 * Encode text into QRCode image.
 */
public interface QRWriter {

	/**
	 * Write into Image as QRCode.
	 * size as default (200px * 200px)
	 *
	 * @param text text(embedded as URL)
	 * @param file output image file
	 */
	void write(String text, File f);

	/**
	 * Write into Image as QRCode.
	 * @param text text(embedded as URL)
	 * @param format Image format
	 * @param file output image file
	 * @param width image width(px)
	 * @param height image height(px)
	 */
	void write(String text, File f, int width, int height);

	/**
	 * Write into Image as QRCode.
	 * size as default (200px * 200px)
	 *
	 * @param text text(embedded as URL)
	 * @param fileName image file name
	 * @param os OutputStream
	 */
	void write(String text, String fileName, OutputStream os);

	/**
	 * Write into Image as QRCode.
	 *
	 * @param text text(embedded as URL)
	 * @param fileName image filename
	 * @param os OutputStream
	 * @param width image width(px)
	 * @param height image height(px)
	 */
	void write(String text, String format, OutputStream os, int width, int height);
}
