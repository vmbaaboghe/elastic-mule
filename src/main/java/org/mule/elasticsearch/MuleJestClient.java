/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.core.Index;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;

import com.google.gson.JsonObject;

public class MuleJestClient implements ElasticSearchClient {

	private static final long serialVersionUID = 7363183415770832044L;

	private JestClient client;

	final static Logger log = Logger.getLogger(MuleJestClient.class);

	private MuleContext muleContext;

	public void initClient(Map<String, String> properties) {
		//Initialize Jest client
		log.debug("Starting initialisation of MuleJest Client");
		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(properties.get(ElasticSearchConnector.CLUSTER));
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST,servers);

		// Construct a new Jest client according to configuration via factory
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig);
		this.client = factory.getObject();
		log.debug("Ending initialisation of MuleJest Client");
		
	}
	
    /***
     * Create an new indexation tool 
     ***/
	@Override
	public void createIndex(Map<Object,Object> source,String indexName,String indexType) throws IOException {
		log.debug("starting indexation ");
		Index index = new Index.Builder(source).index(indexName).type(indexType).build();
		this.client.execute(index);
		log.debug("end of indexation ");
	}
	
	/*
     *Create a default Index 
     */
	@Override
	public void createIndex(Map<Object,Object> source) throws IOException {
		Index index = new Index.Builder(source).index("defaultIndexName").type("defaultIndexType").build();		
		this.client.execute(index);
	}	

	
	
	@Override
	public void createIndex(String indexName,String indexType) throws IOException {
		Index index = new Index.Builder(new HashMap()).index(indexName).type(indexType).build();		
		this.client.execute(index);
	}
	

	@Override
	public void indexDocument(String jsonString, String indexName,
			String indexType) throws IOException {
		log.debug("start indexation of a json element as string ");
		Index index = new Index.Builder(jsonString).index(indexName).type(indexType).build();		
		this.client.execute(index);
		log.debug("start indexation of a json element as string ");
	}

	/**
	 * Index a document by using a Json object
	 */
	public void indexDocument(JsonObject object,String indexName, String indexType ) throws IOException {
		Index index = new Index.Builder(object).index(indexName).type(indexType).build();		
		this.client.execute(index);
	}

	public List<Object> executeQuery(String query) {
		return null;//TODO to implements later
	}

	/**
	 * Stop the current client
	 */
	public void shutdownClient() {
		this.client.shutdownClient();
	}

	/**
	 * Set the mule context used by this client during execution
	 */
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
	}

}
