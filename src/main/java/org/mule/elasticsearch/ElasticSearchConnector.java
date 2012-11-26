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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Callable;
import org.mule.elasticsearch.utils.MuleJsonToGsonConverter;
import org.mule.module.json.JsonData;

import com.google.gson.JsonObject;

/**
 * ElasticSearch is a powerful indexed search engine.
 * For more informations, see http://www.elasticsearch.org
 * @author Valery MBAABOGHE
 */
@Module(name = "elasticsearch", schemaVersion = "0.0.1", description = "Elastic Search Integration", friendlyName = "Elastic Search")
public class ElasticSearchConnector implements MuleContextAware,Callable  {
	
	private ElasticSearchClient elasticSearchClient;

	private MuleJsonToGsonConverter muleJsonToGsonConverter;

	public static final String CLUSTER="cluster"; 

	protected transient Log logger = LogFactory.getLog(getClass());

	private String clusterAddress;

	private String indexName;

	private String indexType;
	
	public ElasticSearchConnector(){
		init();
	}
	

	@PostConstruct
	public void init() {
		elasticSearchClient = new MuleJestClient();

		/** Properties à prendre dans un fichier de conf **/
		Map<String,String> props = new HashMap<String,String>();

		props.put(CLUSTER, getClusterAddress());
		elasticSearchClient.initClient(props);

		muleJsonToGsonConverter = new MuleJsonToGsonConverter();
	}

	private ElasticSearchClient getElasticSearchClient()  {
		if(elasticSearchClient==null){
			init();
		}
		return elasticSearchClient;
	}


	@Override
	public void setMuleContext(MuleContext context) {
		getElasticSearchClient().setMuleContext(context);	
	}

	/**
	 * Create new Index
	 * @param source
	 * @throws ElasticEsbException
	 */
	@Processor
	public void createNewIndex(Map<Object, Object> source) throws ElasticEsbException {
		try{
			getElasticSearchClient().createIndex(source);
		} catch(IOException ioException){
			throw new ElasticEsbException("Fail to create new Index ",ioException);
		}
	}

	
	/**
	 * Create new Index
	 * @param source
	 * @throws ElasticEsbException
	 */
	@Processor
	public void createIndexJSON(JsonObject obj,String name,String type) throws ElasticEsbException {
		try{
			getElasticSearchClient().indexDocument(obj,name,type);
		} catch(IOException ioException){
			throw new ElasticEsbException("Fail to create new Index ",ioException);
		}
	}

	/**
	 * Create new Index
	 * @param source
	 * @throws ElasticEsbException
	 */
	@Processor
	public void createIndexNameType(String indexName,String indexType) throws ElasticEsbException {
		try{
			getElasticSearchClient().createIndex(indexName,indexType);
		} catch(IOException ioException){
			throw new ElasticEsbException("Fail to create new Index ",ioException);
		}
	}	

	/**
	 * Create new Index
	 * @param source
	 * @throws ElasticEsbException
	 */
	@Processor
	public void createNewIndex(Map<Object, Object> source,String indexName,String indexType) throws ElasticEsbException {
		try{
			getElasticSearchClient().createIndex(source,indexName,indexType);
		} catch(IOException ioException){
			throw new ElasticEsbException("Fail to create new Index ",ioException);
		}
	}

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		System.out.println(" Cluster address "+getClusterAddress());
		MuleMessage muleMessage = eventContext.getMessage();
		String jsonData = null;
		Object payload = muleMessage.getPayload();
		if(payload instanceof JsonData ){ //Index a new document
			jsonData = payload.toString();
		}
		if(payload instanceof String){ //Index a json document received as string
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

	public String getClusterAddress() {
		return clusterAddress;
	}

	public void setClusterAddress(String clusterAddress) {
		this.clusterAddress = clusterAddress;
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


}
