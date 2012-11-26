/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.mule.api.MuleContext;

import com.google.gson.JsonObject;

public class ElasticSearchClientImpl implements ElasticSearchClient {
	
	

	@Override
	public void indexDocument(String jsonString, String indexName,
			String indexType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createIndex(Map<Object, Object> source) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createIndex(String indexName, String indexType)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createIndex(Map<Object, Object> source, String indexName,
			String indexType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void indexDocument(JsonObject object, String indexName,
			String indexType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> executeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initClient(Map<String, String> properties) {
		
	}

	@Override
	public void shutdownClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMuleContext(MuleContext context) {
		// TODO Auto-generated method stub
		
	}

}
