package com.nortal.jroad.client.mtr;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartDocument;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartResponseDocument;

public interface MtrXTeeService {

	EraisikuSoidukikaartResponseDocument.EraisikuSoidukikaartResponse eraisikuSoidukikaartV1(
		EraisikuSoidukikaartDocument.EraisikuSoidukikaart eraisikuSoidukikaart) throws XRoadServiceConsumptionException;
}
