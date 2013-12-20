package com.infinum.utils.json.response

class SingleResponse<T> {
		
	Boolean success
	
	/**
	 * Payload - data affected by an action
	 */
	T data
	
	/**
	 * Descriptive message to the client about the action that occurred
	 */
	String message

	SingleResponse(){ }
	
	/**
	 * The data requested, as well as a flash message about the action.
	 */
	SingleResponse(T data, String message){
		super()
		success = true
		this.data = data
		this.message = message
	}

	/**
	 * The data requested
	 */
	SingleResponse(T data){
		super()
		success = true
		this.data = data
	}
	
	/**
	 * In an error condition, we'll just pass back a message
	 */
	static SingleResponse asError(String message){
		return new SingleResponse<T>(success:false, message:message)
	}
	
	static jsonProperties = { SingleResponse resp ->
		['success': resp.success, 'data':resp.data, 'message':resp.message]
	}
}