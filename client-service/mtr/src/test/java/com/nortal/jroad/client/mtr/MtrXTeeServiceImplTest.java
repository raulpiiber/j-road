package com.nortal.jroad.client.mtr;


import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartResponseDocument;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;

public class MtrXTeeServiceImplTest extends BaseXRoadServiceImplTest {

    @Resource
    private MtrXTeeServiceImpl mtrXTeeService;
    
    @Test
    public void eraisikuSoidukikaartV1Test() throws XRoadServiceConsumptionException {

        Calendar cal = Calendar.getInstance();
        cal.set(2017, 10, 29);
        
        EraisikuSoidukikaartResponseDocument.EraisikuSoidukikaartResponse response = mtrXTeeService.eraisikuSoidukikaartV1(cal);
        
        Assert.assertTrue(response.getResponse().getSoidukikaardid().getSoidukikaartList().size() > 0);
    }
}