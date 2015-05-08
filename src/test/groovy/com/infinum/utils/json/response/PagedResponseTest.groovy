package com.infinum.utils.json.response

import org.junit.Test

class PagedResponseTest {

	@Test
	void mergeTestA(){
		PagedResponse a = new PagedResponse([0,1,2,3,4], 5, 0, 10, 'first half')
		PagedResponse b = new PagedResponse([5,6,7,8,9], 5, 5, 10, 'second half')
		
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
		PagedResponse a = new PagedResponse([0,1,2,3,4], 5, 0, 12, 'first half')
		PagedResponse b = new PagedResponse([5,6,7,8,9], 5, 5, 12, 'second half')
		
		PagedResponse c = PagedResponse.merge(a, b)
		
		assert c.maxResults == 10
		assert c.offset == 0
		assert c.total == 12
		assert c.message == 'first half -- and -- second half'

		assert !c.endOfTotalResultSet()
		assert !c.allOfTotalResultSet()
	}

}
