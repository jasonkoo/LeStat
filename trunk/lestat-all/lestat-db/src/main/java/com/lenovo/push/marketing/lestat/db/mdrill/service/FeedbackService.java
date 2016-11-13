package com.lenovo.push.marketing.lestat.db.mdrill.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.mdrill.dao.FeedbackDao;
import com.lenovo.push.marketing.lestat.db.mdrill.entity.FeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mdrill.param.Param;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.CfFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.FeedbackResult;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.SimpleFeedbackResult;

@Service("mdrillFeedbackService")
public class FeedbackService {
	
	@Autowired
	private FeedbackDao mdrillFeedbackDao;
	
	private String UNKNOWN = "unknown";
	
	
	public SimpleFeedbackResult getFeedbackResultDup(String adId, String thedate) {
		SimpleFeedbackResult sfr = new SimpleFeedbackResult();
		sfr.setAdId(adId);
		sfr.setDate(thedate);
		
		FeedbackEntity fe = mdrillFeedbackDao.getFeedbackResultDup(adId, thedate);
		sfr.setArrived((long)fe.getArrived());
		sfr.setDisplayed((long)fe.getDisplayed());
		sfr.setSysmsgclicked((long)fe.getSysmsgclicked());
		sfr.setS2nddisplayed((long)fe.getS2nddisplayed());
		sfr.setS2ndclicked((long)fe.getS2ndclicked());
		sfr.setDownloaded((long)fe.getDownloaded());
		sfr.setInstalled((long)fe.getInstalled());
		sfr.setActivated((long)fe.getActivated());
		
		return sfr;
	}
	
	public SimpleFeedbackResult getDistCountByColx(String adId, String thedate, String colx) {
		SimpleFeedbackResult sfr = new SimpleFeedbackResult();
		sfr.setAdId(adId);
		sfr.setDate(thedate);
		FeedbackEntity fe = mdrillFeedbackDao.getDistCountByColx(adId, thedate, colx);
		
		sfr.setDistCount((long)fe.getSum());
		
		return sfr;
	}
	
	public CfFeedbackResult getDailyCfFeedbackResultDup(String adId, List<String> pnList, String sd, String ed){
		CfFeedbackResult cfFeedbackResult = new CfFeedbackResult();
		cfFeedbackResult.setAdId(adId);
		cfFeedbackResult.setSd(sd);
		cfFeedbackResult.setEd(ed);
		
		List<FeedbackResult> fbResults = new ArrayList<FeedbackResult>();
		cfFeedbackResult.setList(fbResults);
		
		long sumArrived = 0;
		
		List<FeedbackEntity> fbEntities = mdrillFeedbackDao.getDailyCfFeedbackResultDup(adId, pnList, sd, ed);
		if (fbEntities != null && fbEntities.size() >0) {
			for (FeedbackEntity fbEntity : fbEntities) {
				
				// Special treatment for arrive event
				// The arrive event does not carry package name which will result in ac_id='unknown'
				// in which case 'unknown' will become a package name and a group
				// we need to filter out this group
				FeedbackResult fbr = new FeedbackResult(fbEntity);
				if ( !fbr.getPkgName().equals(UNKNOWN) ) {
					fbResults.add(fbr);	
				}
							
				sumArrived += fbEntity.getArrived();
			}
		}
		
		cfFeedbackResult.setSumArrived(sumArrived);
		
		return cfFeedbackResult;
		
	}
	
	public CfFeedbackResult getDailyCfFeedbackResultDist(String adId, List<String> pnList, String sd, String ed) {
		CfFeedbackResult cfFeedbackResult = new CfFeedbackResult();
		cfFeedbackResult.setAdId(adId);
		cfFeedbackResult.setSd(sd);
		cfFeedbackResult.setEd(ed);
		
		List<FeedbackResult> fbResults = new ArrayList<FeedbackResult>();
		cfFeedbackResult.setList(fbResults);
		
		long sumArrived = 0;
		
		for (String eventName: Param.EVENT_COL_MAP.keySet()) {
			String colx = Param.EVENT_COL_MAP.get(eventName);
			
			List<FeedbackEntity> fbEntities = mdrillFeedbackDao.getDailyCfFeedbackResultDist(adId, pnList, sd, ed, colx, eventName);
			if (fbEntities != null && fbEntities.size() >0) {
				// Special treatment for arrive event --- no package name provided
				if (eventName.equals(Param.ARRIVED)) {
					for(FeedbackEntity fbEntity : fbEntities) {
						sumArrived += fbEntity.getArrived();
					}
				} else {
					try {
						combineDailyFeedbackResult(fbResults, fbEntities, eventName);
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		}		
	
		cfFeedbackResult.setSumArrived(sumArrived);		
		return cfFeedbackResult;
	}
	
	private void combineDailyFeedbackResult(List<FeedbackResult> fbResults, List<FeedbackEntity> fbEntities, String eventName) 
			throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (fbResults == null) {
			throw new IllegalArgumentException("Exception: fbResult == null!");
		} else {
			if (fbResults.size() == 0) {
				if (fbEntities != null && fbEntities.size() > 0) {
					for (FeedbackEntity fbEntity : fbEntities) {
						fbResults.add(new FeedbackResult(fbEntity));
					}
				}				
			} else {
				
				if (fbEntities != null && fbEntities.size() > 0) {
					
					String getMethodName = "get" + WordUtils.capitalize(eventName);
					String setMethodName = "set" + WordUtils.capitalize(eventName);
					Method fbResultGetMethod = FeedbackResult.class.getMethod(getMethodName);
					Method fbResultSetMethod = FeedbackResult.class.getMethod(setMethodName, long.class);
					Method fbEntityGetMethod = FeedbackEntity.class.getMethod(getMethodName);
					boolean exists = false;
					
					for (FeedbackEntity fbEntity : fbEntities) {
						exists = false;
						for (FeedbackResult fbResult : fbResults) {
							if(fbEntity.getAdId().equals(fbResult.getAdId()) && fbEntity.getPkgName().equals(fbResult.getPkgName())) {
								exists = true;								
								long oldVal = (Long)fbResultGetMethod.invoke(fbResult);
								double newVal = (Double)fbEntityGetMethod.invoke(fbEntity);
								oldVal = oldVal + (long) newVal;
								fbResultSetMethod.invoke(fbResult, oldVal);
							}
						}
						if (exists == false) {
							fbResults.add(new FeedbackResult(fbEntity));
						}
					}
				}			
			}			
		}		
	}
	
	
	public CfFeedbackResult getDateRangeCfFeedbackResultDup(String adId, List<String> pnList, String sd, String ed){
		CfFeedbackResult cfFeedbackResult = new CfFeedbackResult();
		cfFeedbackResult.setAdId(adId);
		cfFeedbackResult.setSd(sd);
		cfFeedbackResult.setEd(ed);
		
		List<FeedbackResult> fbResults = new ArrayList<FeedbackResult>();
		cfFeedbackResult.setList(fbResults);
		
		long sumArrived = 0;
		
		List<FeedbackEntity> fbEntities = mdrillFeedbackDao.getDateRangeCfFeedbackResultDup(adId, pnList, sd, ed);
		if (fbEntities != null && fbEntities.size() > 0) {
			for (FeedbackEntity fbEntity : fbEntities) {		
				fbResults.add(new FeedbackResult(fbEntity, sd, ed));				
				sumArrived += fbEntity.getArrived();
			}
		}
		
		cfFeedbackResult.setSumArrived(sumArrived);
		
		return cfFeedbackResult;
		
	}
	
	
	
	public CfFeedbackResult getDateRangeCfFeedbackResultDist(String adId, List<String> pnList, String sd, String ed) {
		CfFeedbackResult cfFeedbackResult = new CfFeedbackResult();
		cfFeedbackResult.setAdId(adId);
		cfFeedbackResult.setSd(sd);
		cfFeedbackResult.setEd(ed);
		
		List<FeedbackResult> fbResults = new ArrayList<FeedbackResult>();
		cfFeedbackResult.setList(fbResults);
		
		long sumArrived = 0;
		
		for (String eventName: Param.EVENT_COL_MAP.keySet()) {
			String colx = Param.EVENT_COL_MAP.get(eventName);
			
			List<FeedbackEntity> fbEntities = mdrillFeedbackDao.getDateRangeCfFeedbackResultDist(adId, pnList, sd, ed, colx, eventName);
			if (fbEntities != null && fbEntities.size() > 0) {
				if (eventName.equals(Param.ARRIVED)) {
					for(FeedbackEntity fbEntity : fbEntities) {
						sumArrived += fbEntity.getArrived();
					}
				}
				try {
					combineDateRangeFeedbackResult(fbResults, fbEntities, eventName, sd, ed);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}			
		}		
	
		cfFeedbackResult.setSumArrived(sumArrived);		
		return cfFeedbackResult;
	}
	
	
	private void combineDateRangeFeedbackResult(List<FeedbackResult> fbResults, List<FeedbackEntity> fbEntities, String eventName, String sd, String ed) 
			throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (fbResults == null) {
			throw new IllegalArgumentException("Exception: fbResult == null!");
		} else {
			if (fbResults.size() == 0) {
				if (fbEntities != null && fbEntities.size() > 0) {
					for (FeedbackEntity fbEntity : fbEntities) {
						fbResults.add(new FeedbackResult(fbEntity, sd, ed));
					}
				}				
			} else {
				
				if (fbEntities != null && fbEntities.size() > 0) {
					
					String getMethodName = "get" + WordUtils.capitalize(eventName);
					String setMethodName = "set" + WordUtils.capitalize(eventName);
					Method fbResultGetMethod = FeedbackResult.class.getMethod(getMethodName);
					Method fbResultSetMethod = FeedbackResult.class.getMethod(setMethodName, long.class);
					Method fbEntityGetMethod = FeedbackEntity.class.getMethod(getMethodName);
					boolean exists = false;
					
					for (FeedbackEntity fbEntity : fbEntities) {
						exists = false;
						for (FeedbackResult fbResult : fbResults) {
							if(fbEntity.getAdId().equals(fbResult.getAdId()) && fbEntity.getPkgName().equals(fbResult.getPkgName())) {
								exists = true;								
								long oldVal = (Long)fbResultGetMethod.invoke(fbResult);
								double newVal = (Double)fbEntityGetMethod.invoke(fbEntity);
								oldVal = oldVal + (long) newVal;
								fbResultSetMethod.invoke(fbResult, oldVal);
							}
						}
						if (exists == false) {
							fbResults.add(new FeedbackResult(fbEntity, sd, ed));
						}
					}
				}			
			}			
		}		
	}
	
}
