package org.yggd.misc.zxing.exper;

/**
 * Failed to convert image.
 */
public class ImageConvertException extends RuntimeException {

	private static final long serialVersionUID = -8007181927378588056L;

	public ImageConvertException() {
		super();
	}

	public ImageConvertException(String message) {
		super(message);
	}

	public ImageConvertException(String message, Exception cause) {
		super(message, cause);
	}

	public ImageConvertException(Exception cause) {
		super(cause);
	}
}
