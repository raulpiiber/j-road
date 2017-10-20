package com.nortal.jroad.client.ariregv6;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Ettevotja;
import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class Ariregv6XTeeServiceImplTest extends BaseXRoadServiceImplTest {

	@Resource
	private Ariregv6XTeeService service;

	@Test
    public void findDetailandmedV1Test() throws XRoadServiceConsumptionException {

        List<DetailandmedV5Ettevotja> response = service.findDetailandmedV1(10379733, ArrayUtils.EMPTY_STRING_ARRAY, true, true, true, 
                true, true,true, 3, "12345678901");
        
        System.out.println("response: " + response);
        
        Assert.assertNotNull(response);
    }
}
