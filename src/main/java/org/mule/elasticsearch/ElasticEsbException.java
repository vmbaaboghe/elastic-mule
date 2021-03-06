/**
 * Project lead by Valery MBA ABOGHE for Ideo Technologies
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.elasticsearch;

public class ElasticEsbException extends Exception {
	
	private static final long serialVersionUID = -4749667624162528973L;

	public ElasticEsbException(String description){
		super(description);
	}
	
	public ElasticEsbException(String description,Exception exception){
		super(description,exception);
	}
}
