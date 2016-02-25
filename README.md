# Java Coordinate Converter

This is a simple library that provide methods to convert coordinate from DD (Decimal Degree) to DMS (Degree, minute and Second).

# How to use

The following methods show how you can do the conversion between this two formats.

```Java
@Test
  public void testConvertDMSCoordinatePairtoDDPair() {
    DMSCoordinateDTO latide = new DMSCoordinateDTO(30, 2, 42.172, 'S');
    DMSCoordinateDTO longitude = new DMSCoordinateDTO(51, 7, 36.528, 'W');
    DMSCoordinatePairDTO dmsPairTested = new DMSCoordinatePairDTO(latide, longitude);
    DDCoordinatePairDTO ddPairExpected = new DDCoordinatePairDTO(-30.04504777777778D,-51.12681333333333D);
    DDCoordinatePairDTO ddPairReturned = CoordinateConverter.convertDMSCoordinatePairtoDDPair(dmsPairTested);
    Assert.assertEquals(ddPairExpected, ddPairReturned);
  }
  
  
  @Test
  public void testConvertDDCoordinatePairtoDMSPair() {
    DMSCoordinateDTO latitude = new DMSCoordinateDTO(30.0, 2.702866666666708, 42.17200000000247, 'S');
    DMSCoordinateDTO longitude = new DMSCoordinateDTO(51, 7.60879999999986, 36.52799999999161, 'W');
    DMSCoordinatePairDTO dmsPairExpected = new DMSCoordinatePairDTO(latitude, longitude);
    DDCoordinatePairDTO ddPairTested = new DDCoordinatePairDTO(-30.04504777777778D,-51.12681333333333D);
    DMSCoordinatePairDTO dmsPairReturned = CoordinateConverter.convertDDCoordinatePairToDMSPair(ddPairTested);
    dmsPairReturned.getLatitude().toString();
    dmsPairReturned.getLongitude().toString();
    Assert.assertEquals(dmsPairExpected, dmsPairReturned);
  }
```
