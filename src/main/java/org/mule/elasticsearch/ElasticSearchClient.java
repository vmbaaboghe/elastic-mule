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

public interface ElasticSearchClient extends java.io.Serializable {
	
	public void createIndex(Map<Object,Object> source)  throws IOException;
	
	
	public void createIndex(String indexName,String indexType) throws IOException;
	
	public void createIndex(Map<Object,Object> source,String indexName,String indexType) throws IOException;
	
	/**
	 * Index a document stored as JSON object
	 * @param object
	 * @param indexType 
	 * @param indexName 
	 */
	public void indexDocument(JsonObject object, String indexName, String indexType ) throws IOException;
	
	/**
	 * Index a document stored as JSON object
	 * @param object
	 * @param indexType 
	 * @param indexName 
	 */
	public void indexDocument(String jsonString, String indexName, String indexType ) throws IOException;
	
	/**
	 * Execute a search in the cluster via a query
	 * @param query
	 * @return
	 */
	public List<Object> executeQuery(String query);
	
	/**
	 * Initialise client properties
	 * @param properties
	 */
	public void initClient(Map<String, String> properties);
	
	/**
	 * Shutdown the current client
	 */
	public void shutdownClient();

	public void setMuleContext(MuleContext context);
}
