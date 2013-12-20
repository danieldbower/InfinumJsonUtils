package com.infinum.utils.json.response

import org.junit.Test

class EncodedResponseTest {

	@Test
	void environmentSanity(){
		assert(true)
	}
	
	@Test
	void basicConstructor(){
		assert new EncodedResponse()
		
		assert new EncodedResponse(id:UUID.randomUUID(), name:'myDs', message: 'testing',
				encodedData:'encodedData', startTime:new Date(), endTime: new Date(),
				success:true)
	}
	
	@Test
	void additionalConstructor(){
		EncodedResponse ds = new EncodedResponse('myDs')
		assert ds.id
		assert ds.startTime
		assert ds.name == 'myDs'
		assert (!ds.endTime)
		assert !ds.encodedData
	}
	
	@Test
	void addData(){
		EncodedResponse ds = new EncodedResponse('myDs')
		def data = ['blue Jay', 'cardinal', 'eagle', 'turkey']
		ds.addData(data)
		assert ds.endTime
		assert ds.encodedData
		
	}
	
	@Test
	void encodeData(){
		EncodedResponse ds = new EncodedResponse('myDs')
		def data = ['blue Jay', 'cardinal', 'eagle', 'turkey']
		ds.addData(data)
		assert ds.encodedData.size()>0
	}
	
	@Test
	void extractData(){
		EncodedResponse ds = new EncodedResponse('myDs')
		def data = ['blue Jay', 'cardinal', 'eagle', 'turkey']
		ds.addData(data)

		println ds.encodedData
			
		String jsonString = ds.extractDataAsJsonString()
		assert jsonString
		
		println jsonString
	}
	
	@Test
	void toStringTest(){
		EncodedResponse ds = new EncodedResponse('myDs')
		assert ds.toString().endsWith('but has not finished')
		
		def data = ['blue Jay', 'cardinal', 'eagle', 'turkey']
		ds.addData(data)
		assert ds.toString().contains('successfully')
	}
	
	@Test
	void jsonProperties(){
		EncodedResponse ds = new EncodedResponse('myDs')
		def data = ['blue Jay', 'cardinal', 'eagle', 'turkey']
		ds.addData(data)
		
		Map resp = EncodedResponse.jsonProperties.call(ds)
		assert resp
		assert resp.success
		assert resp.encodedData
		assert resp.message
		assert resp.id
		assert resp.name
		assert resp.startTime
		assert resp.endTime
	}
}
