package org.apache.dearbaby.cache;

import org.apache.dearbaby.data.SinResult;
import org.apache.dearbaby.query.IExecutor;

public   class SimpCacheTableConf extends CacheTableConf {

	 
	
	public   SinResult loadCacheRule(){
		if(result==null){
			getCache();
		}
		return result;
	}
	 
}