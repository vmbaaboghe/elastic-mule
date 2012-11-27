/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Callable;
import org.mule.module.json.JsonData;


/**
 * ElasticSearch is a powerful indexed search engine.
 * For more informations, see http://www.elasticsearch.org
 * @author Valery MBAABOGHE
 */
public class ElasticSearchConnector implements MuleContextAware,Callable  {
	
	private IElasticSearchClient elasticSearchClient;
	
	public static final String CLUSTER_HOST="host";
	public static final String CLUSTER_PORT="port"; 

	protected transient Log logger = LogFactory.getLog(getClass());

	private String clusterHost;
	
	private Integer clusterPort;

	private String indexName;

	private String indexType;
	
	private MuleContext muleContext;
	
	public ElasticSearchConnector(){		
	}

	public void init() {
	 	elasticSearchClient = new ElasticSearchClientImpl();

		Map<String,Object> props = new HashMap<String,Object>();

		props.put(CLUSTER_HOST, getClusterHost());
		props.put(CLUSTER_PORT, getClusterPort());
		elasticSearchClient.initClient(props);		
	}

	private IElasticSearchClient getElasticSearchClient()  {
		if(elasticSearchClient==null){
			init();
		}
		return elasticSearchClient;
	}


	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
	}
	

	/**
	 * Create new Index
	 * @param source
	 * @throws ElasticEsbException
	 */
	public void createIndexNameType(String indexName,String indexType) throws ElasticEsbException {
		try{
			getElasticSearchClient().createIndex(indexName,indexType);
		} catch(IOException ioException){
			throw new ElasticEsbException("Fail to create new Index ",ioException);
		}
	}	


	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		MuleMessage muleMessage = eventContext.getMessage();
		String jsonData = null;
		Object payload = muleMessage.getPayload();
		if(payload instanceof JsonData ){ //Index a new document
			jsonData = payload.toString();
		}
		else if(payload instanceof String){ //Index a json document received as string
			jsonData = (String)payload;
		}
		else if(payload instanceof Object[] ){ //Create a new index
			Object[] objects = (Object[])payload;
			if(objects.length>=2){
				setIndexName((String)objects[0]);
				setIndexType((String)objects[1]);
			}
		}
		else{
			throw new Exception(" Unsupported format for this component ");
		}		
		if(jsonData==null){
			getElasticSearchClient().createIndex(getIndexName(), getIndexType());			
		}else{
			getElasticSearchClient().indexDocument(jsonData,getIndexName(),getIndexType());
		}		
		return payload;
	}

	public String getClusterHost() {
		return clusterHost;
	}

	public void setClusterHost(String clusterHost) {
		this.clusterHost = clusterHost;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}


	public Integer getClusterPort() {
		return clusterPort;
	}


	public void setClusterPort(Integer clusterPort) {
		this.clusterPort = clusterPort;
	}

	public void createIndexNameType(String jsonString, String indexName,String indexType) throws ElasticEsbException {
		try {
			getElasticSearchClient().indexDocument(jsonString, indexName, indexType);
		} catch (IOException ioException) {
			throw new ElasticEsbException("Fail to create new Index ",ioException);
		}
	}
	
}
