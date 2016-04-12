package com.infinum.utils.json.encoding

import groovy.transform.CompileStatic

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

@CompileStatic
class EncodingUtils {
	
	static String encodeData(String s){
		if(!s){
			return
		}
		
		ByteArrayOutputStream targetStream = new ByteArrayOutputStream()
		GZIPOutputStream zipStream = new GZIPOutputStream(targetStream)
		zipStream.write(s.bytes)
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
		
		return reader?.text
	}
}
