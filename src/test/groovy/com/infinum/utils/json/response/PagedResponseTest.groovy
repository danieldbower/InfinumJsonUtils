package com.infinum.utils.json.response

import org.junit.Test

class PagedResponseTest {

	@Test
	void mergeTestA(){
		PagedResponse a = PagedResponse.asSuccess([0,1,2,3,4], 10, 5, 0, 'first half')
		PagedResponse b = PagedResponse.asSuccess([5,6,7,8,9], 10, 5, 5, 'second half')
		
		PagedResponse c = PagedResponse.merge(a, b)
		
		assert c.maxResults == 10
		assert c.offset == 0
		assert c.total == 10
		assert c.message == 'first half -- and -- second half'
		
		assert c.endOfTotalResultSet()
		assert c.allOfTotalResultSet()
	}

	@Test
	void mergeTestB(){
		PagedResponse a = PagedResponse.asSuccess([0,1,2,3,4], 12, 5, 0, 'first half')
		PagedResponse b = PagedResponse.asSuccess([5,6,7,8,9], 12, 5, 5, 'second half')
		
		PagedResponse c = PagedResponse.merge(a, b)
		
		assert c.maxResults == 10
		assert c.offset == 0
		assert c.total == 12
		assert c.message == 'first half -- and -- second half'

		assert !c.endOfTotalResultSet()
		assert !c.allOfTotalResultSet()
	}

	@Test
	void asJson(){
		PagedResponse a = PagedResponse.asSuccess([0,1,2,3,4], 5, 0, 10, 'successfully instantiated')
		assert """{"success":true,"data":[0,1,2,3,4],"total":5,"maxResults":0,"offset":10,"message":"successfully instantiated","endOfTotalResultSet":true,"allOfTotalResultSet":true}""" == a.asJsonBuilder().toString()
	}
}
