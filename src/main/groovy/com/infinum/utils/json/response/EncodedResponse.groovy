package com.infinum.utils.json.response

import groovy.json.JsonBuilder
import groovy.json.JsonOutput

import com.infinum.utils.json.encoding.EncodingUtils


/**
 * Packages up the Data for compression
 * 
 * When creating some packaged data, you should be using EncodedResponse(String name) 
 * constructor and the addData(Object data) method.  Registering the jsonProperties
 * closure in your BootStrap.groovy will enable the EncodedResponse to correctly
 * serialize to json automatically
 */
class EncodedResponse<T> {

	/**
	 * Sometimes we persist/cache these responses.  The id allows us to fetch.
	 */
	UUID id

	/**
	 * Sometimes we pass around a name in order to distinguish what kind
	 * of processing should happen to this data.
	 */
	String name

	/**
	 * Descriptive message to the client about the action that occurred
	 */
	String message
	
	/**
	 * Base64 encoded zipped JSON of the data we are passing around
	 */
	String encodedData
	
	/**
	 * Time that the underlying action started processing
	 */
	Date startTime
	
	/**
	 * Time that the underlying action stopped processing
	 */
	Date endTime
	
	/**
	 * Was the underlying action successful
	 */
	Boolean success
	
	EncodedResponse(){}
	
	EncodedResponse(String name){
		this.id = UUID.randomUUID()
		this.startTime = new Date()
		this.name = name
		this.message = "$name EncodedResponse $id started at $startTime, but has not finished"
	}
	
	void addData(T data, String message = null){
		endTime = new Date()
		this.encodedData = encodeData(data)
		this.success = true
		
		if(message){
			this.message = message
		}else{
			this.message = "$name EncodedResponse $id generated successfully from $startTime to $endTime"
		}
	}
	
	String encodeData(T data){
		if(!data) return ''
		
		String rawJson = JsonOutput.toJson(data)
		return EncodingUtils.encodeData(rawJson)
	}
	
	String extractDataAsJsonString(){
		return EncodingUtils.decodeData(encodedData)
	}
	
	String toString(){
		return message
	}
	
	/**
	 * In an error condition, we'll just pass back a message
	 */
	static EncodedResponse asError(String message){
		return new EncodedResponse<T>(success:false, message:message)
	}

	JsonBuilder asJsonBuilder(){
		JsonBuilder jsonBuilder = new JsonBuilder()
		jsonBuilder {
			id id
			name name
			success success
			encodedData encodedData
			message message
			startTime startTime
			endTime endTime
		}
	}

	public static jsonProperties = { EncodedResponse resp ->
		['id':resp.id, 'name':resp.name, 'success': resp.success, 
				'encodedData':resp.encodedData, 'message':resp.message,
				'startTime':resp.startTime, 'endTime':resp.endTime]
	}
}
