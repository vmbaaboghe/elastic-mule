/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch.test;


import org.junit.Test;

import java.util.Date;
import java.util.HashMap;



public class IndexIntegrationTest extends AbstractIntegrationTest {

	HashMap<Object, Object> source = new HashMap<Object, Object>();
	
	
	@Test
    public void testDefaultIndex() {
        try{
        	source.put("message", "I want to go to santiago bernabeu");
        	source.put("postDate",new Date());
        	source.put("location", "Puteaux");
        	elasticSearchConnector.createNewIndex(source);        	
        } catch (Exception e) {
            fail("Failed during the create index with valid parameters. Exception:" + e.getMessage());
            e.printStackTrace();
        }
    }
	
	@Test
    public void testIndexNameSpecified() {
        try{
        	source.put("message", "I became crazy, I'm on my way to Camp Nou!!");
        	source.put("postDate",new Date());
        	source.put("location", "Barcelona");
        	elasticSearchConnector.createNewIndex(source,"facebook","post");
        } catch (Exception e) {
        	e.printStackTrace();
            fail("Failed during the create index with valid parameters. Exception:" + e.getMessage());
            e.printStackTrace();
        }
    }

}
