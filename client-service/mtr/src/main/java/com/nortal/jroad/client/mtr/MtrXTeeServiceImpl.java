package com.nortal.jroad.client.mtr;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.mtr.database.MtrXRoadDatabase;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartDocument;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartResponseDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MtrXTeeServiceImpl")
public class MtrXTeeServiceImpl implements MtrXTeeService {

	@Autowired
	private MtrXRoadDatabase mtrXRoadDatabase;

	public EraisikuSoidukikaartResponseDocument.EraisikuSoidukikaartResponse eraisikuSoidukikaartV1(
		EraisikuSoidukikaartDocument.EraisikuSoidukikaart eraisikuSoidukikaart) throws XRoadServiceConsumptionException {
	    
		return mtrXRoadDatabase.eraisikuSoidukikaartV1(eraisikuSoidukikaart);
	}
}
