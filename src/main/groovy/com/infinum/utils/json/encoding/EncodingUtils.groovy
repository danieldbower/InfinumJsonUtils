package com.infinum.utils.json.encoding

import groovy.transform.CompileStatic

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

@CompileStatic
class EncodingUtils {

	/**
	 * Zip, and then base64 encode a string
	 * @param s
	 * @return base 64 encoded, zipped version of string
	 */
	static String encodeData(String s){
		if(!s){
			return
		}
		
		ByteArrayOutputStream targetStream = new ByteArrayOutputStream()
		GZIPOutputStream zipStream = new GZIPOutputStream(targetStream)
		zipStream.write(s.bytes)
		zipStream.close()

		return base64EncodeByteArrayOutputStream(targetStream)
	}

	/**
	 * Supply a Writer to the closure, close and output the writer to a base64
	 * encoded, zipped version
	 * @param encodingData
	 * @return base 64 encoded, zipped string of what was fed to the writer
	 */
	static String withEncodingWriter(Closure encodingData){
		ByteArrayOutputStream targetStream = new ByteArrayOutputStream()
		GZIPOutputStream zipStream = new GZIPOutputStream(targetStream)
		OutputStreamWriter osWriter = new OutputStreamWriter(zipStream)

		encodingData.call(osWriter)

		return base64EncodeByteArrayOutputStream(targetStream)
	}

	/**
	 * Decode a base64 encoded, zipped string
	 * @param encoded string
	 * @return decoded string
	 */
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

	/**
	 * Take a ByteArrayOutputStream, close and base64 encode it to a string
	 * @param byteArrayOutputStream
	 * @return encoded String
	 */
	static String base64EncodeByteArrayOutputStream(ByteArrayOutputStream os){
		byte[] zipped = os.toByteArray()
		os.close()
		return zipped?.encodeBase64()?.toString()
	}
}
