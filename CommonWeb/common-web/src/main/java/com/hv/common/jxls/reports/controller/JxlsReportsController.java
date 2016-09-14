package com.hv.common.jxls.reports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author harshul.varshney
 *
 */
@Controller
public class JxlsReportsController {
	
	@RequestMapping(value = { "/jxls/reports" }, method = RequestMethod.GET)
    public ModelAndView getSampleReport(ModelMap model) {
		
		return new ModelAndView("jxls_SAMPLE_REPORT");
    }

}
