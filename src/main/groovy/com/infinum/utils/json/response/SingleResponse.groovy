package com.infinum.utils.json.response

import groovy.json.JsonBuilder

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
	
	SingleResponse(boolean success, T data, String message){
		super()
		this.success = success
		this.data = data
		this.message = message
	}
	
	/**
	 * In an error condition, we'll just pass back a message
	 */
	static SingleResponse asError(String message){
		return new SingleResponse(false, null, message)
	}
	
	static SingleResponse asSuccess(T data, String message){
		return new SingleResponse(true, data, message)
	}
	
	static SingleResponse asSuccess(String message){
		return new SingleResponse(true, null, message)
	}

	JsonBuilder asJsonBuilder(){
		JsonBuilder jsonBuilder = new JsonBuilder()
		jsonBuilder {
			success success
			data data
			message message
		}
	}

	static jsonProperties = { SingleResponse resp ->
		['success': resp.success, 'data':resp.data, 'message':resp.message]
	}
}