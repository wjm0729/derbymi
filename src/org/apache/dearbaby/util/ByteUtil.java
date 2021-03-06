package org.apache.dearbaby.util;

import java.math.BigDecimal;
import java.nio.charset.Charset;

public class ByteUtil {

	/**
	 * compare to number or dicamal ascii byte array, for number :123456 ,store
	 * to array [1,2,3,4,5,6]
	 * 
	 * @param b1
	 * @param b2
	 * @return -1 means b1 < b2, or 0 means b1=b2 else return 1
	 */
	public static int compareNumberByte(byte[] b1, byte[] b2) {
		if (b1 == null || b1.length == 0)
			return -1;
		else if (b2 == null || b2.length == 0)
			return 1;
		boolean isNegetive = b1[0] == 45 || b2[0] == 45;
		if (isNegetive == false && b1.length != b2.length) {
			return b1.length - b2.length;
		}
		int len = b1.length > b2.length ? b2.length : b1.length;
		int result = 0;
		int index = -1;
		for (int i = 0; i < len; i++) {
			int b1val = b1[i];
			int b2val = b2[i];
			if (b1val > b2val) {
				result = 1;
				index = i;
				break;
			} else if (b1val < b2val) {
				index = i;
				result = -1;
				break;
			}
		}
		if (index == 0) {
			// first byte compare
			return result;
		} else {
			if (b1.length != b2.length) {

				int lenDelta = b1.length - b2.length;
				return isNegetive ? 0 - lenDelta : lenDelta;

			} else {
				return isNegetive ? 0 - result : result;
			}
		}
	}

	public static byte[] compareNumberArray2(byte[] b1, byte[] b2, int order) {
		if (b1.length <= 0 && b2.length > 0) {
			return b2;
		}
		if (b1.length > 0 && b2.length <= 0) {
			return b1;
		}
		int len = b1.length > b2.length ? b1.length : b2.length;
		for (int i = 0; i < len; i++) {
			if (b1[i] != b2[i])
				if (order == 1)
					return ((b1[i] & 0xff) - (b2[i] & 0xff)) > 0 ? b1 : b2;
				else
					return ((b1[i] & 0xff) - (b2[i] & 0xff)) > 0 ? b2 : b1;
		}

		return b1;
	}

	public static byte[] getBytes(short data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		return bytes;
	}

	public static byte[] getBytes(char data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data);
		bytes[1] = (byte) (data >> 8);
		return bytes;
	}

	public static byte[] getBytes(int data) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		bytes[2] = (byte) ((data & 0xff0000) >> 16);
		bytes[3] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}

	public static byte[] getBytes(long x) {
		byte[] bb=new byte[8];
		    bb[ 0] = (byte) (x >> 56); 
	        bb[ 1] = (byte) (x >> 48); 
	        bb[ 2] = (byte) (x >> 40); 
	        bb[ 3] = (byte) (x >> 32); 
	        bb[ 4] = (byte) (x >> 24); 
	        bb[ 5] = (byte) (x >> 16); 
	        bb[ 6] = (byte) (x >> 8); 
	        bb[ 7] = (byte) (x >> 0); 
	    return bb;
	}

	public static byte[] getBytes(float data) {
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
	}

	public static byte[] getBytes(double data) {
		long intBits = Double.doubleToLongBits(data);
		return getBytes(intBits);
	}

	public static byte[] getBytes(String data, String charsetName) {
		Charset charset = Charset.forName(charsetName);
		return data.getBytes(charset);
	}

	public static byte[] getBytes(String data) {
		return getBytes(data, "UTF-8");
	}

	public static short getShort(byte[] bytes) {
		return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	public static char getChar(byte[] bytes) {
		return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	public static int getInt(byte[] bytes) {
		return Integer.parseInt(new String(bytes));
		// return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 &
		// (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
	}

	public static long getLong(byte[] bytes) {
		return Long.parseLong(new String(bytes));
		// return(0xffL & (long)bytes[0]) | (0xff00L & ((long)bytes[1] << 8)) |
		// (0xff0000L & ((long)bytes[2] << 16)) | (0xff000000L & ((long)bytes[3]
		// << 24))
		// | (0xff00000000L & ((long)bytes[4] << 32)) | (0xff0000000000L &
		// ((long)bytes[5] << 40)) | (0xff000000000000L & ((long)bytes[6] <<
		// 48)) | (0xff00000000000000L & ((long)bytes[7] << 56));
	}

	public static double getDouble(byte[] bytes) {
		return Double.parseDouble(new String(bytes));
	}

	public static String getString(byte[] bytes, String charsetName) {
		return new String(bytes, Charset.forName(charsetName));
	}

	public static String getString(byte[] bytes) {
		return getString(bytes, "UTF-8");
	}

	public static String getDate(byte[] bytes) {
		return new String(bytes);
	}

	public static String getTimestmap(byte[] bytes) {
		return new String(bytes);
	}
	
	public static Object plus(Object left,Object right){
		return MathUtil.add(new BigDecimal(left.toString()), new BigDecimal(right.toString()));
	}
	
	public static Object times(Object left,Object right){
		return MathUtil.multiply(new BigDecimal(left.toString()), new BigDecimal(right.toString()));
	}
	
	public static Object minus(Object left,Object right){
		return MathUtil.subtract(new BigDecimal(left.toString()), new BigDecimal(right.toString()));
	}
	
	public static Object divide(Object left,Object right){
		return MathUtil.divide( left.toString() , right.toString());
	}
	
	public static Integer getIntegerX(byte[] bytes){
		return  Integer.valueOf(new String(bytes));
	}

	public static Float getFloatX(byte[] bytes){
		return  Float.valueOf(new String(bytes));
	}
	
	public static Long getLongX(byte[] bytes){
		return  Long.valueOf(new String(bytes));
	}
	

	public static Short getShortX(byte[] bytes){
		return  Short.valueOf(new String(bytes));
	}
	

	public static Double getDoubleX(byte[] bytes){
		return  Double.valueOf(new String(bytes));
	}
	

	public static BigDecimal getBigDecimalX(byte[] bytes){
		return new BigDecimal(new String(bytes));
	}
	
	public static Object getCol(byte[] bytes,int type){
		switch(type){
			case ColType.XBIG :return getBigDecimalX(bytes);
			case ColType.XINT :return getIntegerX(bytes);
			case ColType.XFLOAT :return getFloatX(bytes);
			case ColType.XSHORT :return getShortX(bytes);
			case ColType.XLONG :return getLongX(bytes);
			case ColType.XDOUBLE :return getDoubleX(bytes);
			case ColType.XSTR :return getStringX(bytes);
		}
		return null;
	}
	
	
	public static String getStringX(byte[] bytes){
		return new String(bytes) ;
	}
	
	public static int byte2int(byte[] res,int begin){
		  
		  
		int targets = (res[begin] & 0xff) | ((res[begin+1] << 8) & 0xff00) // | 表示安位或   
		| ((res[begin+2] << 24) >>> 8) | (res[begin+3] << 24);   
		return targets;   
	}
	
	public static long byte2long(byte[] bb,int begin){
		 
		 return ((((long) bb[ 0] & 0xff) << 56) 
	               | (((long) bb[ 1] & 0xff) << 48) 
	               | (((long) bb[ 2] & 0xff) << 40) 
	               | (((long) bb[ 3] & 0xff) << 32) 
	               | (((long) bb[ 4] & 0xff) << 24) 
	               | (((long) bb[ 5] & 0xff) << 16) 
	               | (((long) bb[ 6] & 0xff) << 8) | (((long) bb[ 7] & 0xff) << 0));  
		  
	 
	}
	
	public static int byte2intShort(byte[] res,int begin){
		  
		  
		int targets = (res[begin] & 0xff) | ((res[begin+1] << 8) & 0xff00); // | 表示安位或   
		//| ((res[begin+2] << 24) >>> 8) | (res[begin+3] << 24);   
		return targets;   
	}
	
	 public static byte[] int2byteShort(int res) {  
		 byte[] targets = new byte[4];  
		   
		 targets[0] = (byte) (res & 0xff);// 最低位   
		 targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
		 return targets;   
	} 
	 
	 public static byte[] int2byte(int res) {  
		 byte[] targets = new byte[4];  
		   
		 targets[0] = (byte) (res & 0xff);// 最低位   
		 targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
		 targets[2] = (byte) ((res >> 16) & 0xff);// 次高位   
		 targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。   
		 return targets;   
	} 
	 
	 public static void intCopybyte(int res,byte[] targets,int begin) {  
		 
		    
		 targets[begin] = (byte) (res & 0xff);    
		 targets[begin+1] = (byte) ((res >> 8) & 0xff);
		 targets[begin+2] = (byte) ((res >> 16) & 0xff);   
		 targets[begin+3] = (byte) (res >>> 24); 
		  
	} 
	 
	 public static void shortCopybyte(int res,byte[] targets,int begin) {  
		 
		    
		 targets[begin] = (byte) (res & 0xff);    
		 targets[begin+1] = (byte) ((res >> 8) & 0xff);
		  
	} 

	public static long compInt(int b,int r){
		long bl=(int)b;
		
		long k=(bl << 32)|r;
		return k;
	}
	
	public static int getIntLow(long b){
		int bl=(int)b;
		return bl;
	}
	public static int getIntHght(long b){
		long bt=b;
		int bl=(int)(bt>>>32);
		return bl;
	}
	
	public static byte[] getHashBytes(Object key){
		 return key.toString().getBytes();
		 
	}
	
	 
	
	
	public  static byte[] getHashKey(Object key,Object value){
		byte[] bk =getHashBytes(key);
		byte[] bv= getBytes((long)value);
	
		byte[] v=new byte[bk.length+8];
		System.arraycopy(bk, 0,v , 0, bk.length);
		System.arraycopy(bv, 0,v , bk.length,8 );
	//	System.out.println("getHashKey   "+new String(v));
		return v;
	}
	
	
	public static byte[] growUp (byte[] o,int leng){
		byte[] tmp=new byte[o.length+leng];
		System.arraycopy(o,0, tmp, 0,o.length);
		return tmp;
		 
	}
	
	public static int[] growUp (int[] o,int leng){
		int[] tmp=new int[o.length+leng];
		System.arraycopy(o,0, tmp, 0,o.length);
		return tmp;
	 }
	
	public static long[] growUp (long[] o,int leng){
		long[] tmp=new long[o.length+leng];
		System.arraycopy(o,0, tmp, 0,o.length);
		return tmp;
	 }
	
	 
	public static <T> T[] growUpObjec (T[] o,int leng){
		T[] tmp=(T[]) new Object[o.length+leng];
		System.arraycopy(o,0, tmp, 0,o.length);
		return tmp;
	 }
}
