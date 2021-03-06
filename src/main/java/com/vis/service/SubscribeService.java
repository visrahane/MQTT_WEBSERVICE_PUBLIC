/*******************************************************************************
 * Copyright (c) 2017, Autonomous Networks Research Group. All rights reserved.
 * contributor: Vishal D. Rahane
 * Read license file in main directory for more details
 ******************************************************************************/
/**
 * 
 */
package com.vis.service;

import com.vis.models.SubscribeRequest;
import com.vis.models.SubscribeResponse;

/**
 * @author vis
 *
 */
public interface SubscribeService {

	SubscribeResponse subscribeToTopic(SubscribeRequest subscriptionRequest);
}
