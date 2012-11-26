/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch.utils;

import java.lang.reflect.Type;

import org.mule.module.json.JsonData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MuleJsonToGsonConverter {
	
	private GsonBuilder gson_builder;
	
	public MuleJsonToGsonConverter(){
		gson_builder = getGsonBuilder();
	}

	public JsonObject convert(JsonData jsonData){

		String sJsonData = jsonData.toString();
		return convert(sJsonData);
	}
	
	private GsonBuilder getGsonBuilder(){
		GsonBuilder gson_builder = new GsonBuilder();
		gson_builder.registerTypeAdapter(
				JsonElement.class,
				new JsonDeserializer<JsonElement>() {					
					public JsonElement deserialize(JsonElement arg0,
							Type arg1,JsonDeserializationContext arg2)
									throws JsonParseException {
						return arg0;
					}

				} );
		return gson_builder;
	}
	
	public JsonObject convert(String sData){
		Gson gson = gson_builder.create();
		JsonElement element = gson.fromJson(sData,JsonElement.class );
		JsonObject jsonObject = element.getAsJsonObject();
		return jsonObject;
	}
}
