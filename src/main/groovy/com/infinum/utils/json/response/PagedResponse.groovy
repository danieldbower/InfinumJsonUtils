package com.infinum.utils.json.response

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

	public PagedResponse(){
		super()
		
		data = []
	}

	/**
	 * The entire dataset we requested
	 */
	public PagedResponse(Collection<T> data){
		super()
		success = true
		this.data = data
		this.total = data?.size()
		this.message = "Success"
	}

	
	/**
	 * The entire dataset we requested, as well as a flash message about the action
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
	
	Iterator<T> iterator(){
		return data.iterator()
	}
	
	/**
	 * In an error condition, we'll just pass back a message
	 */
	static PagedResponse asError(String message){
		new PagedResponse(success:false, message:message)
	}
	
	public static jsonProperties = { PagedResponse resp ->
		['success': resp.success, 'data':resp.data, 'total': resp.total,
				'maxResults':resp.maxResults, 'offset':resp.offset,
				'message':resp.message]
	}
}
