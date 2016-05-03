package com.infinum.utils.json.encoding

/**
 * Adapter that can be backed with a Json Serialization Library
 */
interface JsonSerializerAdapter {

	String serializeData(Object data)

	String serializeAndEncodeData(Object data)
}