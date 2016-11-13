package com.lenovo.push.marketing.lestat.engine.server.http;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.springframework.stereotype.Component;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.CfErrorResult;
import com.lenovo.push.marketing.lestat.engine.dbproxy.DbProxy;
import com.lenovo.push.marketing.lestat.engine.param.Param;
import com.lenovo.push.marketing.lestat.engine.result.CfErrorJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.CfErrorJsonResultList;
import com.lenovo.push.marketing.lestat.engine.util.ParamUtil;

@Component("errorMethodHandlerImpl")
public class ErrorMethodHandlerImpl {
	
	@Resource(name = "dbProxy")
	private DbProxy dbProxy;
	
	public CfErrorJsonResultList getCfError(List<NameValuePair> params, HttpBody body) throws UnsupportedEncodingException, ParseException{
		String adId = ParamUtil.getParameter(params, Param.AD_ID);
		String sd = ParamUtil.getParameter(params, Param.START_DATE);
		String ed = ParamUtil.getParameter(params, Param.END_DATE);
		
		long df = DateUtil.getDateDiff(ed,sd,DateUtil.DATE_PATTERN);
		if (df<0) {
			throw new IllegalArgumentException("invalid sd-ed pair");
		}
		if (df>=Param.FEEDBACK_MAX_DATE_DIFF) {
			throw new IllegalArgumentException("maximal time span of " + Param.FEEDBACK_MAX_DATE_DIFF + " days");
		}
		
		List<CfErrorResult> cfErrorResults =  dbProxy.getCfError(adId, sd, ed);
		
		List<CfErrorJsonResult> cfErrorJsonResults = new ArrayList<CfErrorJsonResult>();
		
		if ( cfErrorResults != null && cfErrorResults.size() > 0) {
			
			for (CfErrorResult cfErrorResult : cfErrorResults) {
				CfErrorJsonResult cfErrorJsonResult = new CfErrorJsonResult();
				
				cfErrorJsonResult.setAdId(cfErrorResult.getAdId());
				cfErrorJsonResult.setSd(cfErrorResult.getSd());
				cfErrorJsonResult.setEd(cfErrorResult.getEd());
				cfErrorJsonResult.setType(cfErrorResult.getType());
				cfErrorJsonResult.setErrorCode(cfErrorResult.getErrorCode());
				cfErrorJsonResult.setCount(cfErrorResult.getCount());
				
				cfErrorJsonResults.add(cfErrorJsonResult);
				
			}
		}
		
		CfErrorJsonResultList cfErrorJsonResultList =  new CfErrorJsonResultList(cfErrorJsonResults);		
		return  cfErrorJsonResultList;
	}

}
