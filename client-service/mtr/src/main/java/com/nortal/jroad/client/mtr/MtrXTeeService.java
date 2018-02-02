package com.nortal.jroad.client.mtr;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartResponseDocument;

import java.util.Calendar;

public interface MtrXTeeService {

	EraisikuSoidukikaartResponseDocument.EraisikuSoidukikaartResponse eraisikuSoidukikaartV1(Calendar calendar) throws XRoadServiceConsumptionException;
}
