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

    public SingleResponse(){
		super()
    }
    
    /**
     * The data requested, as well as a flash message about the action.
     */
    public SingleResponse(T data, String message){
		super()
		success = true
		this.data = data
		this.message = message
    }

    /**
     * The data requested
     */
    public SingleResponse(T data){
		super()
		success = true
		this.data = data
    }
    
    /**
     * In an error condition, we'll just pass back a message
     */
    public static SingleResponse asError(String message){
	    return new SingleResponse<T>(success:false, message:message)
    }
    
    public static jsonProperties = { SingleResponse resp ->
        ['success': resp.success, 'data':resp.data, 'message':resp.message]
    }   
}