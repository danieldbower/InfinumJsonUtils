package com.infinum.utils.json.encoding

import grails.converters.JSON

/**
 * Use Groovy JsonOutput class for serialization
 */
class GrailsJsonSerializerAdapter implements JsonSerializerAdapter{

	@Override
	String serializeData(Object data) {
		if(data){
			JSON rawJson = new JSON(data)
			return rawJson.toString()
		}
		return ''
	}

	@Override
	String serializeAndEncodeData(Object data) {
		if(data){
			JSON rawJson = new JSON(data)
			return EncodingUtils.withEncodingWriter { writer ->
				rawJson.render(writer)
			}
		}
		return ''
	}
}