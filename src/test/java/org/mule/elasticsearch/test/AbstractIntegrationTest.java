/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch.test;

import org.junit.Before;
import org.mule.elasticsearch.ElasticSearchConnector;
import org.mule.tck.AbstractMuleTestCase;


public abstract class  AbstractIntegrationTest extends AbstractMuleTestCase {
	
	protected ElasticSearchConnector elasticSearchConnector;
	

	@Before
	public void doSetUp() throws Exception {		
		elasticSearchConnector = new ElasticSearchConnector();
		elasticSearchConnector.setClusterPort(9300);
		elasticSearchConnector.setClusterHost("localhost");
		elasticSearchConnector.init();		
	}
	
}
