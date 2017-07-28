/**
 * 
 */
package com.vis.callbacks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author vis
 *
 */
public class MqttSubCallback implements MqttCallback {

	private static final Logger LOGGER = LogManager.getLogger(MqttSubCallback.class.getName());

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		// get url from redis for this topic and hit the url;
		try {
			String url = (String) redisTemplate.opsForValue().get(topic);
			UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("message", mqttMessage)
					.build();
			restTemplate.getForObject(uriComponents.toString(), String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while sending data to the client-", ex);
		}
	}

}