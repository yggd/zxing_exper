package org.yggd.misc.zxing.exper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * Decode to text from image.
 */
public class QRReaderImpl implements QRReader {

	/**
	 * decode from image.
	 *
	 * @param f image file
	 * @return embedded text
	 */
	public String read(File f) {
		assert f != null && f.isFile() && f.canRead() : "file must be readable.";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			return read(fis);
		} catch (FileNotFoundException e) {
			throw new ImageConvertException(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// ignore file close error
				}
			}
		}
	}

	/**
	 * decode from image stream.
	 *
	 * @param is image input stream
	 * @return embedded text
	 */
	public String read(InputStream is) {
		Result result = null;
		try {
			BufferedImage image = ImageIO.read(is);
		    LuminanceSource source = new BufferedImageLuminanceSource(image);
		    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		    result = new MultiFormatReader().decode(bitmap);
		} catch (IOException e) {
			throw new ImageConvertException(e);
		} catch (NotFoundException e) {
			throw new ImageConvertException(e);
		}
		if (result == null) {
			throw new ImageConvertException("Decode image failed.");
		}
	    return result.getText();
	}
}
