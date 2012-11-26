elasticMule
===========

It's a simple component using the Jest client which allow you to connect mule esb and an elastic search cluster.
To use it , you just have to add jar got with a mvn package command and add it to the classpath of your mule
application.

pre-requisites
Java 1.6

    ---------------------------------------------------
    | Mule Elastic Search Plugin | ElasticSearch       |
    ---------------------------------------------------
    | master                     | 0.19.11 -> master   |
    ---------------------------------------------------
    | 0.1                        | 0.19.11 -> master   |
    ---------------------------------------------------
    
How to use it
=============

In a mule flow application, you can use the ElasticSearchConnector class with the following
instruction :

---------------------------------------------------------------------------
 
       <component doc:name="Elastic Search Connector">
          <singleton-object class="org.mule.elasticsearch.ElasticSearchConnector"  >
                <property key="clusterAddress" value="${mule.clusterAddress.property}"/>
           	 <property key="indexName" value="${mule.indexName.value}"/>
           	 <property key="indexType" value="${mule.indexType.value}"/>
          </singleton-object>
        </component>    
        
---------------------------------------------------------------------------        

In your mule-app.properties, you'll have to define the following properties:

    -------------------------------------------------------
    |mule.clusterAddress.property=http://localhost:9200    |
    |mule.indexName.value=indexName                        |
    |mule.indexType.value=indexType                        |
    -------------------------------------------------------
    
Supported operation
==========

- For the moment this component wait for a json document to index it in the index specified in the property file.
- It can also creates an index and a type with an array of two specified values.
