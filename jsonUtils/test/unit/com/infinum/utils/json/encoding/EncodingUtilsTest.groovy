package com.infinum.utils.json.encoding

import org.junit.Test
import com.infinum.utils.json.response.EncodedResponse

class EncodingUtilsTest {

	@Test
	void encodeAndDecode(){
		EncodedResponse.jsonSerializerAdapter = new GrailsJsonSerializerAdapter()

		String orig = 'This little piggy went to the market, This little piggy stayed home, This little piggy had roast beef, This little piggy had none, And this little piggy cried wee wee wee all the way to town'
		String encoded = EncodingUtils.encodeData(orig)
		println "Encoded: $encoded"
		
		String decoded = EncodingUtils.decodeData(encoded)
		println "Decoded: $decoded"
		
		println "Original Size: ${orig.size()}; Encoded Size: ${encoded.size()}"
		assert orig.size()>encoded.size()
		
		assert orig == decoded
	}

	@Test
	void verifyStickinessOfJsonSerializerAdapter(){
		assert EncodedResponse.jsonSerializerAdapter instanceof GrailsJsonSerializerAdapter
	}

	@Test
	void encodeAndDecodeWithWriter(){
		String orig = 'This little piggy went to the market, This little piggy stayed home, This little piggy had roast beef, This little piggy had none, And this little piggy cried wee wee wee all the way to town'
		String encoded = EncodingUtils.withEncodingWriter{ Writer writer ->
			writer.write(orig)
			writer.close()
		}
		println "Encoded: $encoded"

		String decoded = EncodingUtils.decodeData(encoded)
		println "Decoded: $decoded"

		println "Original Size: ${orig.size()}; Encoded Size: ${encoded.size()}"
		assert orig.size()>encoded.size()

		assert orig == decoded
	}
}
