package com.hv.common.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.Constants;
import org.apache.poi.ss.usermodel.DateUtil;
import org.jxls.area.Area;
import org.jxls.area.CommandData;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.command.IfCommand;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.formula.FastFormulaProcessor;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.hv.common.ApplicationContextProvider;
import com.hv.common.jxls.reports.CustomCellFormatter;
import com.hv.common.jxls.reports.JxlsReportService;

/**
 * 
 * @author harshul.varshney
 *
 */
public class CustomJxlsReportView extends AbstractUrlBasedView {

	public static final Logger logger = LoggerFactory.getLogger(CustomJxlsReportView.class);
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	
	/**
	 * Default constructor.
	 * Sets the content type to application/vmd.ms-excel
	 */
	public CustomJxlsReportView() {
		setContentType(CONTENT_TYPE);
	}
	
	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long startTime = System.currentTimeMillis();

		processJxlsTemplate(model, request, response);

		logger.debug("{} | {} sec taken to generate report", getUrl(), (System.currentTimeMillis() - startTime)/1000);
	}
	
	private void processJxlsTemplate(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String beanName = "sampleJxlsReportService";
		String headerKey = "Content-Disposition";
		String templateFilePath = getTemplatePath();
		String fileExtension = templateFilePath.substring(templateFilePath.lastIndexOf("."), templateFilePath.length());
		String reportName = "";
		reportName = "JXLS_SAMPLE_REPORT_"+ new Date() + fileExtension;
		
		//process template
		try(InputStream in = new FileInputStream(templateFilePath)) {
			try(OutputStream os = response.getOutputStream()) {
				
				Transformer transformer = TransformerFactory.createTransformer(in, os);
				JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
                Map<String, Object> functionMap = new HashMap<>();
                evaluator.getJexlEngine().setFunctions(functionMap);
				
				//get data to fill report
				Context context = PoiTransformer.createInitialContext();
				fillData(context, functionMap, beanName);
                
				String headerValue = String.format("attachment; filename=\"%s\"", reportName);
				response.setContentType("application/vnd.ms-excel");
				response.setHeader(headerKey, headerValue);
				
				AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer, true);
				List<Area> xlsAreaList = areaBuilder.build();
				for (Area xlsArea : xlsAreaList) {
					supressInvalidAreaError(xlsArea);
					xlsArea.applyAt(new CellRef(xlsArea.getStartCellRef().getCellName()), context);
					xlsArea.setFormulaProcessor(new FastFormulaProcessor());
					xlsArea.processFormulas();
				}
				
				//call custom cell formatters after sheet is filled with data
				formatCell(transformer);
				
				transformer.write();
				//fill data in template
				//JxlsHelper.getInstance().processTemplate(context, transformer);
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	private String getTemplatePath() throws MalformedURLException {
		String path;
		try {
			path = new java.net.URL(getUrl()).getPath();
		}
		catch(MalformedURLException e) {
			logger.error(e.getMessage());
			throw e;
		}
		return path;
	}
	
	private void fillData(Context context, Map<String, Object> functionMap, String beanName) {
		
		JxlsReportService reportService = (JxlsReportService) ApplicationContextProvider.getApplicationContext().getBean(beanName);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(JxlsReportService.FUNCTION_MAP, functionMap);
		parameters.put(JxlsReportService.CONTEXT, context);
		
		reportService.fillData(parameters);
	}
	
	/**
	 * Custom cell formatters can be invoked through this method.
	 * These formatters could be used to merge multiple cells/change cell style/set or update cell data type etc.
	 * @param transformer
	 * @param reportName
	 */
	private void formatCell(Transformer transformer) {
		
		new CustomCellFormatter((PoiTransformer)transformer).formatCell();
		
	}
	
	/**
	 * @param xlsArea
	 * @see https://bitbucket.org/leonate/jxls/issues/53/nullpointerexception-is-thrown-when
	 */
	private  void supressInvalidAreaError(Area xlsArea){
		for (CommandData commandData : xlsArea.getCommandDataList()) {
			if (commandData.getCommand() instanceof IfCommand) {
				IfCommand command = (IfCommand) commandData.getCommand();
				if (null != command.getElseArea() && null == command.getElseArea().getTransformer()) {
					command.getElseArea().setParentCommand(command);
				}
			}
		}
	}

}
