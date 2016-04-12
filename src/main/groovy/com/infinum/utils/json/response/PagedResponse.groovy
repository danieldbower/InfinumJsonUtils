package com.infinum.utils.json.response

import groovy.json.JsonBuilder

class PagedResponse<T> implements Iterable<T> {
	
	Boolean success
	
	/**
	 * Payload - Paged Data
	 */
	Collection<T> data

	/**
	 * The total number of results, outside of pagination
	 */
	int total

	/**
	 * The maximum number of results requested
	 */
	int maxResults

	/**
	 * The offset to begin the maximum number of results
	 */
	int offset

	/**
	 * Descriptive message to the client about the action that occurred
	 */
	String message

	/**
	 * Calculate whether this is the last in a series of Paged Responses
	 */
	boolean endOfTotalResultSet(){
		return ( (offset + maxResults) >= total)
	}

	void setEndOfTotalResultSet(boolean val){
		//calculated val
	}

	void setAllOfTotalResultSet(boolean val){
		//calculatedVal
	}

	boolean allOfTotalResultSet(){
		return (data.size() == total)
	}

	public PagedResponse(){
		super()
		
		data = []
	}

	/**
	 * The entire dataset we requested
	 * @deprecated Use asSuccess Methods instead
	 */
	@Deprecated 
	public PagedResponse(Collection<T> data){
		super()
		success = true
		this.data = data
		this.total = data?.size()
		this.message = "Success"
	}

	
	/**
	 * The entire dataset we requested, as well as a flash message about the action
	 * @deprecated Use asSuccess Methods instead
	 */
	public PagedResponse(Collection<T> data, String message){
		super()
		success = true
		this.data = data
		this.total = data?.size()
		this.message = message
	}

	/**
	 * The paged dataset we requested, as well as the paging parameters and a flash message about the action
	 * @deprecated Use asSuccess Methods instead
	 */
	public PagedResponse(Collection<T> data, int maxResults, int offset,
							int total, String message){
		super()
		success = true
		this.data = data
		this.total = total
		this.maxResults = maxResults
		this.offset = offset
		this.message = message
	}

	/**
	 * Iterate over data existing in this set, not the total set
	 */
	Iterator<T> iterator(){
		return data.iterator()
	}
	
	/**
	 * In an error condition, we'll just pass back a message
	 */
	static PagedResponse asError(String message){
		new PagedResponse(success:false, message:message)
	}
	
	/**
	 * The entire dataset we requested, as well as a flash message about the action
	 * optional message parameter to pass back to the requestor
	 */
	static PagedResponse asSuccess(Collection data, String message = "Success"){
		PagedResponse resp = new PagedResponse()
		resp.success = true
		resp.data = data
		resp.total = data?.size()
		resp.message = message
		return resp
	}
	
	/**
	 * The entire dataset we requested, paging parameters used to generate the dataset
	 * optional message parameter to pass back to the requestor
	 */
	static PagedResponse asSuccess(Collection data, int total, int maxResults, int offset,
			String message = "Success"){

		PagedResponse resp = new PagedResponse()
		resp.success = true
		resp.data = data
		resp.total = total
		resp.maxResults = maxResults
		resp.offset = offset
		resp.message = message
		return resp
	}

	/**
	 * Add two PagedResponses from the same overall set.
	 * Be sure to provide consecutive PagedResponses
	 */
	static PagedResponse<T> merge(PagedResponse<T> a, PagedResponse<T> b){
		if(!a && b){
			return b
		}
		if(a && !b){
			return a
		}
		if(!a && !b){
			return null
		}
		
		if(!a.success || !b.success){
			return null
		}
		
		Collection<T> newCollection = a.data + b.data
		int maxResults = a.maxResults + b.maxResults
		int offset = ((a.offset>b.offset)?b.offset:a.offset)
		return new PagedResponse(newCollection, maxResults, offset,
			a.total, a.message + " -- and -- "+ b.message)
	}

	JsonBuilder asJsonBuilder(){
		JsonBuilder jsonBuilder = new JsonBuilder()
		jsonBuilder {
			success success
			data data
			total total
			maxResults maxResults
			offset offset
			message message
			endOfTotalResultSet(endOfTotalResultSet())
			allOfTotalResultSet(allOfTotalResultSet())
		}
	}

	public static jsonProperties = { PagedResponse resp ->
		['success': resp.success, 'data':resp.data, 'total': resp.total,
				'maxResults':resp.maxResults, 'offset':resp.offset,
				'message':resp.message, 
				'endOfTotalResultSet':resp.endOfTotalResultSet(),
				'allOfTotalResultSet':resp.allOfTotalResultSet()]
	}
}
