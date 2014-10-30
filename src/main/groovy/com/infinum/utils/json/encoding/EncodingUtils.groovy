package com.infinum.utils.json.encoding

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class EncodingUtils {
	
	static String encodeData(String s){
		if(!s){
			return
		}
		
		ByteArrayOutputStream targetStream = new ByteArrayOutputStream()
		GZIPOutputStream zipStream = new GZIPOutputStream(targetStream)
		zipStream.write(s.getBytes())
		zipStream.close()
		byte[] zipped = targetStream.toByteArray()
		targetStream.close()
		
		return zipped?.encodeBase64()?.toString()
	}
	
	static String decodeData(String encoded){
		byte[] zipped = encoded.decodeBase64()
		
		if (!zipped.length) {
			return
		}
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(zipped)
		GZIPInputStream zipStream = new GZIPInputStream(inputStream)
		BufferedReader reader = zipStream.newReader()
		
		return reader?.getText()
	}
}
