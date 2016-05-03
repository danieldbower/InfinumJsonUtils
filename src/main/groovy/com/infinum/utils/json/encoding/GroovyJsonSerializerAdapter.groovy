package com.infinum.utils.json.encoding

import groovy.json.JsonOutput

/**
 * Use Groovy JsonOutput class for serialization
 */
class GroovyJsonSerializerAdapter implements JsonSerializerAdapter{

	@Override
	String serializeData(Object data) {
		if(data){
			return JsonOutput.toJson(data)
		}
		return ''
	}

	@Override
	String serializeAndEncodeData(Object data) {
		String rawJson = ''
		if(data){
			rawJson = JsonOutput.toJson(data)
		}
		return EncodingUtils.encodeData(rawJson)
	}
}
