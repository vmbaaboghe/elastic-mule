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

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticSearchClientImpl implements IElasticSearchClient {

	private static final long serialVersionUID = -8058595618100173542L;

	/**
	 * Direct client configured
	 */
	private Client directClient;

	
	@Override
	public void indexDocument(String jsonString, String indexName,
			String indexType) throws IOException {

		this.directClient.prepareIndex(indexName, indexType)
		.setSource(jsonString).execute().actionGet();
	}


	@Override
	public void createIndex(String indexName, String indexType)
			throws IOException {
		this.directClient.prepareIndex(indexName,indexType).execute().actionGet();		
	}


	@Override
	public void initClient(Map<String, Object> properties) {
		this.directClient = new TransportClient()
		.addTransportAddress(new InetSocketTransportAddress((String)properties.get(ElasticSearchConnector.CLUSTER_HOST),
				(Integer)properties.get(ElasticSearchConnector.CLUSTER_PORT)));
	}


	@Override
	public void shutdownClient() {
		this.directClient.close();
	}
	
	@Override
	public List<Object> executeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
