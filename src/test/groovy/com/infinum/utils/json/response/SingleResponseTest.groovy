package com.infinum.utils.json.response

import org.junit.Test

class SingleResponseTest {

	@Test
	void constructor1(){
		SingleResponse sr = new SingleResponse()
		
		assert !sr.success
		assert null == sr.message
		assert null == sr.data
		
		
		Map mapVal = [mykey1:1, mykey2:2] 
		String message = 'successful Invocation'
		sr = SingleResponse.asSuccess(mapVal, message)
		
		assert sr.success
		assert mapVal == sr.data
		assert message == sr.message
	}
	
	@Test
	void asError(){
		String message = 'failure'
		SingleResponse sr = SingleResponse.asError(message)
		
		assert !sr.success
		assert message == sr.message
		assert null == sr.data
		
	}

	@Test
	void asJson(){
		SingleResponse sr = SingleResponse.asSuccess([name:'testMap'], "successfully instantiated")
		assert """{"success":true,"data":{"name":"testMap"},"message":"successfully instantiated"}""" == sr.asJsonBuilder().toString()
	}
}
