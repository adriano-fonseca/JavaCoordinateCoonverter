package org.jcc.test;

import junit.framework.Assert;

import org.jcc.converter.CoordinateConverter;
import org.jcc.dto.DDCoordinatePairDTO;
import org.jcc.dto.DMSCoordinateDTO;
import org.jcc.dto.DMSCoordinatePairDTO;
import org.junit.Before;
import org.junit.Test;

public class TestCoordinateUnitConverters {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testConvertDMSCoordinatePairtoDDPair() {
    //    Girassol, Petropolis - Porto Alegere -30.0450479 -51.12681359999999
    //    -30.0450479  --->  30° 2' 42,172" S
    //    -51.12681359999999 ---> 51° 7' 36,528" W
    DMSCoordinateDTO latide = new DMSCoordinateDTO(30, 2, 42.172, 'S');
    DMSCoordinateDTO longitude = new DMSCoordinateDTO(51, 7, 36.528, 'W');
    DMSCoordinatePairDTO dmsPairTested = new DMSCoordinatePairDTO(latide, longitude);
    DDCoordinatePairDTO ddPairExpected = new DDCoordinatePairDTO(-30.04504777777778D,-51.12681333333333D);
    DDCoordinatePairDTO ddPairReturned = CoordinateConverter.convertDMSCoordinatePairtoDDPair(dmsPairTested);
    Assert.assertEquals(ddPairExpected, ddPairReturned);
  }
  
  
  @Test
  public void testConvertDDCoordinatePairtoDMSPair() {
    //    Girassol, Petropolis - Porto Alegere -30.0450479 -51.12681359999999
    //    -30.0450479  --->  30° 2' 42,172" S
    //    -51.12681359999999 ---> 51° 7' 36,528" W
    DMSCoordinateDTO latitude = new DMSCoordinateDTO(30.0, 2.702866666666708, 42.17200000000247, 'S');
    DMSCoordinateDTO longitude = new DMSCoordinateDTO(51, 7.60879999999986, 36.52799999999161, 'W');
    DMSCoordinatePairDTO dmsPairExpected = new DMSCoordinatePairDTO(latitude, longitude);
    DDCoordinatePairDTO ddPairTested = new DDCoordinatePairDTO(-30.04504777777778D,-51.12681333333333D);
    DMSCoordinatePairDTO dmsPairReturned = CoordinateConverter.convertDDCoordinatePairToDMSPair(ddPairTested);
    dmsPairReturned.getLatitude().toString();
    dmsPairReturned.getLongitude().toString();
    Assert.assertEquals(dmsPairExpected, dmsPairReturned);
  }
  
  
//  @Test
//  public void testConvertDDtoDMS() {
//    //    Girassol, Petropolis - Porto Alegere -30.0450479 -51.12681359999999
//    //    -30.0450479  --->  30° 2' 42,172" S
//    //    -51.12681359999999 ---> 51° 7' 36,528" W
//    DMSCoordinateDTO dmsDTO = CoordinateConverter.convertDDtoDMS(-30.04504777777778D, "Lat");
//    DMSCoordinateDTO dmsDTOExpected = new DMSCoordinateDTO(30.0, 2.702866666666708, 42.17200000000247, 'S');
//    Assert.assertEquals(dmsDTO, dmsDTOExpected);
//  }
  

  //Facebook Silicon Valley
  //N 37º 29 5.451"
  //W 122º 8 54.189"

  //Louvre Museum, Paris
  //48.8606111  -> N 48 51' 38.2"
  //2.3376439999999548 -> E 2 20' 15.518"

}
