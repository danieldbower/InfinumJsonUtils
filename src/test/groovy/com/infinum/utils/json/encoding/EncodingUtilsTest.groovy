package com.infinum.utils.json.encoding

import org.junit.Test

class EncodingUtilsTest {

	@Test
	void encodeAndDecode(){
		String orig = 'This little piggy went to the market, This little piggy stayed home, This little piggy had roast beef, This little piggy had none, And this little piggy cried wee wee wee all the way to town'
		String encoded = EncodingUtils.encodeData(orig)
		println "Encoded: $encoded"
		
		String decoded = EncodingUtils.decodeData(encoded)
		println "Decoded: $decoded"
		
		println "Original Size: ${orig.size()}; Encoded Size: ${encoded.size()}"
		assert orig.size()>encoded.size()
		
		assert orig == decoded
	}
}
