package com.airtel.africa.tt.service;

import java.io.IOException;

public interface TimingTaskService {

	public void autoAssign();
	
	public void blacklistSubscriber();
	
	//public void hotlineSubscriber();
	
	//public void unHotlineSubscriber();
	
	public void autoReject();
	
	public void barSubscriber() throws IOException;
}
