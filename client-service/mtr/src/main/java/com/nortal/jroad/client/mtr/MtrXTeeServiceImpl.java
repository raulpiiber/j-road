package com.nortal.jroad.client.mtr;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.mtr.database.MtrXRoadDatabase;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartDocument;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartParing;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartResponseDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("MtrXTeeServiceImpl")
public class MtrXTeeServiceImpl implements MtrXTeeService {

	@Autowired
	private MtrXRoadDatabase mtrXRoadDatabase;

	public EraisikuSoidukikaartResponseDocument.EraisikuSoidukikaartResponse eraisikuSoidukikaartV1(Calendar calendar) throws XRoadServiceConsumptionException {

		EraisikuSoidukikaartDocument.EraisikuSoidukikaart eraisikuSoidukikaart = EraisikuSoidukikaartDocument.EraisikuSoidukikaart.Factory.newInstance();
		EraisikuSoidukikaartParing eraisikuSoidukikaartParing = EraisikuSoidukikaartParing.Factory.newInstance();
		eraisikuSoidukikaartParing.setAlates(calendar);

		eraisikuSoidukikaart.setRequest(eraisikuSoidukikaartParing);
	    
		return mtrXRoadDatabase.eraisikuSoidukikaartV1(eraisikuSoidukikaart);
	}
}
