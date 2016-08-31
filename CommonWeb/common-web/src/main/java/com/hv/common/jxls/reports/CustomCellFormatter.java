package com.hv.common.jxls.reports;

import org.jxls.transform.poi.PoiTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author harshul.varshney
 * Cell design formatter for excel reports generated through JXLS.
 *
 */
public class CustomCellFormatter {

	public static final Logger logger = LoggerFactory.getLogger(CustomCellFormatter.class);
	
    PoiTransformer transformer;
    private static final String DEFAULT_FONT = "SansSerif";
    
	public CustomCellFormatter(PoiTransformer transformer) {
		this.transformer = transformer;
	}
	
	public void formatCell() {
		
	}
}
