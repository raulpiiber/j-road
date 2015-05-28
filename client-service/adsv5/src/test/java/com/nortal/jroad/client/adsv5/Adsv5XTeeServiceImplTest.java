package com.nortal.jroad.client.adsv5;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.nortal.jroad.client.adsv5.Adsv5XTeeService.NormalParamCallback;
import com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalRequestType.NormalParam;
import com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalRequestType.NormalParam.Aadressid;
import com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalRequestType.NormalParam.Aadressid.Aadress;
import com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalVastusType;
import com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalVastusType.NormalTulem;
import com.nortal.jroad.client.exception.XTeeServiceConsumptionException;
import com.nortal.jroad.client.test.BaseXTeeServiceImplTest;

public class Adsv5XTeeServiceImplTest extends BaseXTeeServiceImplTest {
  @Resource
  private Adsv5XTeeServiceImpl adsXTeeServiceImpl;
  
  @Test
  public void adsNormalOk() throws XTeeServiceConsumptionException {
  	ADSnormalVastusType v = adsXTeeServiceImpl.adsNormal(new NormalParamCallback() {
			public void populate(NormalParam normalParam) {
		  	Aadressid aadressid = normalParam.addNewAadressid();
		  	Aadress aadress = aadressid.addNewAadress();
		  	aadress.setTekst("kadaka tee 163");
      }
  	});
  	  	
  	NormalTulem result1 = v.getNormalTulemArray(0);
  	Assert.assertNotNull(result1);
  	com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalVastusType.NormalTulem.Aadressid.Aadress firstAdr = result1.getAadressid().getAadressArray(0);
	  Assert.assertNotNull(firstAdr);
  }
  
  @Test
  public void adsNormalNotFound() throws XTeeServiceConsumptionException {
  	ADSnormalVastusType v = adsXTeeServiceImpl.adsNormal(new NormalParamCallback() {
			public void populate(NormalParam normalParam) {
		  	Aadressid aadressid = normalParam.addNewAadressid();
		  	Aadress aadress = aadressid.addNewAadress();
		  	aadress.setTekst("kadaka tee 7001");
      }
  	});
  	
  	NormalTulem result1 = v.getNormalTulemArray(0);
  	Assert.assertNotNull(result1);
  	Assert.assertNotNull(result1.getTeade());
  	
  	com.nortal.jroad.client.adsv5.types.ee.x_road.adsv5.producer.ADSnormalVastusType.NormalTulem.Aadressid.Aadress firstAdr = result1.getAadressid().getAadressArray(0);
	  Assert.assertNull(firstAdr.getAdsTase1().getKood());
  }
}
