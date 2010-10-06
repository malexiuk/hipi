package hipi.image;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.Writable;

public class ImageHeader implements Writable, RawComparator<BinaryComparable> {

	public enum ImageType {
		UNSUPPORTED_IMAGE(0x0),
		JPEG_IMAGE(0x1), 
		PNG_IMAGE(0x2), 
		PPM_IMAGE(0x3);

		private int _val;
		ImageType(int val) {
			_val = val;
		}

		public static ImageType fromValue(int value) {
			for (ImageType type : values()) {
				if (type._val == value) {
					return type;
				}
			}
			return getDefault();
		}

		public int toValue() {
			return _val;
		}

		public static ImageType getDefault() {
			return UNSUPPORTED_IMAGE;
		}
	}

	/**
	 * A Map containing the key-value pairs where the key is the field name as
	 * it appears in the EXIF 2.2 specification and the value is the
	 * corresponding information for that field.
	 */
	private Map<String, String> _exif_information;
	/**
	 * The image type of this image. Usually read from the image file's first
	 * few bytes.
	 */
	private ImageType _image_type;

	private static final Log LOG = LogFactory.getLog(ImageHeader.class
			.getName());

	/**
	 * Adds an EXIF field to this header object. The information consists of a
	 * key-value pair where the key is the field name as it appears in the EXIF
	 * 2.2 specification and the value is the corresponding information for that
	 * field.
	 * 
	 * @param key
	 *            the field name of the EXIF information
	 * @param value
	 *            the EXIF information
	 */
	public void addEXIFInformation(String key, String value) {
		if (_exif_information.containsKey(key)) {
			LOG.warn("Overwriting EXIF information for field " + key + " with "
					+ value + " (old value: " + _exif_information.get(key)
					+ ")");
		}

		_exif_information.put(key, value);
	}

	/**
	 * Get an EXIF value designated by the key. The key should correspond to the
	 * 'Field Name' in the EXIF 2.2 specification.
	 * 
	 * @param key
	 *            the field name of the EXIF information desired
	 * @return either the value corresponding to the key or the empty string if
	 *         the key was not found
	 */
	public String getEXIFInformation(String key) {
		String value = _exif_information.get(key);

		if (value == null) {
			return "";
		} else {
			return value;
		}
	}

	/**
	 * Get the image type.
	 * 
	 * @return
	 */
	public ImageType getImageType() {
		return _image_type;
	}

	public int compare(BinaryComparable o1, BinaryComparable o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int compare(byte[] arg0, int arg1, int arg2, byte[] arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub

	}
}
