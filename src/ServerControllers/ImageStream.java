/**
 * Image Stream class implements Serializable
 */
package ServerControllers;

import java.io.Serializable;

public class ImageStream implements Serializable{
	private String fileName=null;
	private int size=0;
	public byte[] imageStreamByteArray;
	
	public ImageStream(String fileName)
	{
		this.fileName=fileName;
	}
	/**
	 * initializing the array of bytes
	 * @param size
	 */
	public void initArray(int size)
	{
		imageStreamByteArray = new byte [size];	
	}
	/**
	 * 
	 * @return fileName String
	 */
	public String getFileName() {
		return fileName;
	}
/**
 * 
 * @param fileName
 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 
	 * @return int size of file in bytes 
	 */
	public int getSize() {
		return size;
	}
/**
 * sets size of the file
 * @param size int
 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * return the image in bytes
	 * @return imageStreamByteArray;
	 */
	public byte[] getImageStreamByteArray() {
		return imageStreamByteArray;
	}
	/**
	 * return value in index
	 * @param i int
	 * @return imageStreamByteArray[i]
	 */
	public byte getImageStreamByteArray(int i) {
		return imageStreamByteArray[i];
	}
	/**
	 * sets the imageStreamByteArray byte[]
	 * @param imageStreamByteArray
	 */
	public void setImageStreamByteArray(byte[] imageStreamByteArray) {
		
		for(int i=0;i<imageStreamByteArray.length;i++)
		this.imageStreamByteArray[i] = imageStreamByteArray[i];
	}
}
