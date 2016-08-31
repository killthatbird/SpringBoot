package com.hv.common.jxls.reports;

import java.util.Map;

/**
 * 
 * @author harshul.varshney
 * interface to be implemented by any service class, which would be used to fill data in excel report using JXLS
 */
public interface JxlsReportService {
	
	public static final String FUNCTION_MAP = "FUNCTION_MAP";
	public static final String PARAMETER_MAP = "PARAMETER_MAP";
	public static final String BEAN_NAME = "BEAN_NAME";
	public static final String CONTEXT = "CONTEXT";
	
	/**
     * API for fetching reporting data result set as a Collection
     * of JavaBean
     * @param parameters
     * @return a Collection of JavaBean representing the reporting data elements
     */
    void fillData(Map<String, Object> parameters);

}
