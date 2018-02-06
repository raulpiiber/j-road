package com.nortal.jroad.client.service.consumer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.service.callback.CustomCallback;
import com.nortal.jroad.client.service.configuration.XRoadServiceConfiguration;
import com.nortal.jroad.client.service.extractor.CustomExtractor;
import com.nortal.jroad.client.service.extractor.Digiluguv6XRoadConsumerMessageExtractor;
import com.nortal.jroad.client.util.XmlBeansUtil;
import com.nortal.jroad.model.XRoadMessage;
import com.nortal.jroad.model.XmlBeansXRoadMetadata;

@Service("digiluguv6XRoadConsumer")
public class Digiluguv6XRoadConsumer extends StandardXRoadConsumer {

	public static final int DEFAULT_READ_TIMEOUT = 10000;
	public static final int DEFAULT_CONNECTION_TIMEOUT = 10000;

	private int customReadTimeout = DEFAULT_READ_TIMEOUT;
	private int customConnectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
	private Map<String, XmlBeansXRoadMetadata> metadata;

	@Override
	protected void initGateway() throws Exception {
		super.initGateway();
		metadata = XmlBeansUtil.loadMetadata();
		setMessageSender(new CustomHttpUrlConnectionMessageSender());
	}

	private class CustomHttpUrlConnectionMessageSender extends HttpUrlConnectionMessageSender {

		@Override
		protected void prepareConnection(HttpURLConnection connection) throws IOException {
			super.prepareConnection(connection);
			connection.setReadTimeout(customReadTimeout);
			connection.setConnectTimeout(customConnectionTimeout);
		}
	}

	@Override
	public <I, O> XRoadMessage<O> sendRequest(XRoadMessage<I> input, XRoadServiceConfiguration xroadServiceConfiguration)
			throws XRoadServiceConsumptionException {
		return sendRequest(input, xroadServiceConfiguration, null, null);
	}

	@Override
	public <I, O> XRoadMessage<O> sendRequest(XRoadMessage<I> input, XRoadServiceConfiguration xroadServiceConfiguration, CustomCallback callback,
											  CustomExtractor extractor) throws XRoadServiceConsumptionException {

		if (extractor == null) {

			XmlBeansXRoadMetadata curdata = metadata.get(xroadServiceConfiguration.getWsdlDatabase().toLowerCase()
					+ xroadServiceConfiguration.getMethod().toLowerCase());

			if (curdata == null) {
				throw new IllegalStateException(String.format("Could not find metadata for %s.%s! Most likely the method name has been specified incorrectly.",
						xroadServiceConfiguration.getWsdlDatabase().toLowerCase(),
						xroadServiceConfiguration.getMethod().toLowerCase()));
			}

			return super.sendRequest(input, xroadServiceConfiguration, callback, new Digiluguv6XRoadConsumerMessageExtractor(curdata));
		}
		return super.sendRequest(input, xroadServiceConfiguration, callback, extractor);
	}

	public void setReadTimeout(int timeout) {
		customReadTimeout = timeout;
	}

	public void setConnectionTimeout(int timeout) {
		customConnectionTimeout = timeout;
	}
}