package com.nortal.jroad.client.mtr;


import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartDocument;
import com.nortal.jroad.client.mtr.types.ee.x_road.mtr.producer.EraisikuSoidukikaartParing;
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

        EraisikuSoidukikaartDocument.EraisikuSoidukikaart eraisikuSoidukikaart = EraisikuSoidukikaartDocument.EraisikuSoidukikaart.Factory.newInstance();
        EraisikuSoidukikaartParing eraisikuSoidukikaartParing = EraisikuSoidukikaartParing.Factory.newInstance();
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 10, 29);
        eraisikuSoidukikaartParing.setAlates(cal);
        
        eraisikuSoidukikaart.setRequest(eraisikuSoidukikaartParing);
        
        EraisikuSoidukikaartResponseDocument.EraisikuSoidukikaartResponse response = mtrXTeeService.eraisikuSoidukikaartV1(eraisikuSoidukikaart);
        
        Assert.assertTrue(response.getResponse().getSoidukikaardid().getSoidukikaartList().size() > 0);
    }
}