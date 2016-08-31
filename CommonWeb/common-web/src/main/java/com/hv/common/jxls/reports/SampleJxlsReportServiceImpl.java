package com.hv.common.jxls.reports;

import java.util.Date;
import java.util.Map;

import org.jxls.common.Context;
import org.springframework.stereotype.Service;

/**
 * Sample implementation of JxlsReportService, used to dixplay some concepts of JXLS library.
 * @author harshul.varshney
 *
 */
@Service("sampleJxlsReportService")
public class SampleJxlsReportServiceImpl  implements JxlsReportService {

	@Override
	public void fillData(Map<String, Object> parameters) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> functionMap = (Map<String, Object>) parameters.get(JxlsReportService.FUNCTION_MAP);
		
		//added custom function in JXLS functionMap, now I'll be able to call any API present in SampleJxlsReportServiceImpl from excel template
		functionMap.put("myfunction", new SampleJxlsReportServiceImpl());
		
		Context context = (Context) parameters.get(JxlsReportService.CONTEXT);
		context.putVar("name", "Harshul");
		context.putVar("generationTime", new Date());
		context.putVar("showText", true);
	}
	
	/**
	 * Returns the sum of input parameters.
	 * @param a
	 * @param b
	 * @return
	 */
	public Integer getSumOfNumbers(int a, int b) {
		return a+b;
	}

}
