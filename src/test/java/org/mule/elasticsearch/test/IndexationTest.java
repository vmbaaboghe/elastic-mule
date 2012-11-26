/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch.test;

import io.searchbox.Parameters;
import io.searchbox.core.Index;

import org.junit.Test;

public class IndexationTest extends AbstractIntegrationTest{

	@Test
    public void testIndexDocument() {
        Index index = new Index.Builder(new Object()).index("facebook").type("post").id("1").build();
        assertEquals("PUT", index.getRestMethodName());
        assertEquals("facebook/post/1", index.getURI());
    }

    @Test
    public void testIndexDocumentWithVersionParameter() {
        Index index = new Index.Builder(new Object()).index("facebook").type("post").id("1").build();
        index.addParameter(Parameters.VERSION,3);
        assertEquals("PUT", index.getRestMethodName());
        assertEquals("facebook/post/1?version=3", index.getURI());
    }

    @Test
    public void testIndexDocumentWithoutId() {
        Index index = new Index.Builder(new Object()).index("facebook").type("post").build();       
        assertEquals("POST", index.getRestMethodName());
        assertEquals("facebook/post", index.getURI());
    }	

}
