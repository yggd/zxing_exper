package org.yggd.misc.zxing.exper;

import java.io.File;
import java.io.InputStream;

/**
 * Decode to text from image.
 */
public interface QRReader {

	/**
	 * decode from image.
	 *
	 * @param f image file
	 * @return embedded text
	 */
	String read(File f);

	/**
	 * decode from image stream.
	 *
	 * @param is image input stream
	 * @return embedded text
	 */
	String read(InputStream is);
}
