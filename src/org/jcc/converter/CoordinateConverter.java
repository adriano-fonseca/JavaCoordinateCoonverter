package org.jcc.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jcc.coordinate.type.UtilEnums;
import org.jcc.dto.DDCoordinatePairDTO;
import org.jcc.dto.DMSCoordinateDTO;
import org.jcc.dto.DMSCoordinatePairDTO;
import org.jcc.exception.BadCoordinateException;
import org.jcc.exception.CoordinateTypeException;

public class CoordinateConverter {
  
  final static BigDecimal ZERO = new BigDecimal("0");
  final static BigDecimal SIXTY = new BigDecimal("60");
  final static BigDecimal THREETHOUSANDSIXHUNDRED = new BigDecimal("3600");
  final static int SCALE = 13;
  final static RoundingMode ROUNDINGMODE = RoundingMode.HALF_UP;
  
  /*
  link converter - http://www.gps-coordinates.net/gps-coordinates-converter
  Earth is divided by the equator     (0° latitude)
  And for Prime meridian (Greenwich). (0° longitude

  (Lat, Lon) = (40.4461111 , -40.44611083333333)   =>  (40° 26' 46" N  , 40° 26' 45.998 W)

  DD (decimal degrees)  -> 40.4461111
  DMS (Degree, minutes, seconds, direction)  -> 40° 26' 46" N 

  ========================
  = To convert DMS to DD =
  ========================

  DD = degrees + (minutes/60) + (seconds/3600)

  ========================
  = To convert DD to DMS =
  ========================
  61.44, -61,44 

  lat dd=61
  lat mm.gg=60*0.44=26.4
  lat ss=60*gg=60*0,4=24

  lat = 61° 26,4' 24"

  Remember, locations in the Southern Hemisphere (S) are at negative latitudes,
  and locations in the Western Hemisphere (W) are at negative longitudes. 
  Therefore, locations in the Northern Hemisphere (N) are at positive latitudes,
  and locations in the Eastern Hemisphere (E) are at positive longitudes. 


  ->   40°26.767′ N (Positive)
  -> - 40°26.767′ S (Negative)
  ->   40°26.767′ E (Positive)  
  ->   40°26.767′ W (Negative)  


  */

  public static DMSCoordinatePairDTO convertDDCoordinatePairToDMSPair(DDCoordinatePairDTO ddCoordinate) {
      DMSCoordinatePairDTO pairDMS = new DMSCoordinatePairDTO();
      DMSCoordinateDTO latitude = convertDDtoDMS(ddCoordinate.getLatitude(), UtilEnums.CoordinateType.LATITUDE.getId());
      DMSCoordinateDTO longitude = convertDDtoDMS(ddCoordinate.getLongitude(), UtilEnums.CoordinateType.LONGITUDE.getId());
      pairDMS.setLatitude(latitude);
      pairDMS.setLongitude(longitude);
      return pairDMS;
  }
  
  public static DDCoordinatePairDTO convertDMSCoordinatePairtoDDPair(DMSCoordinatePairDTO dmsCoordinatePairDTO) {
    BigDecimal latitude = convertDMStoDD(dmsCoordinatePairDTO.getLatitude());
    BigDecimal longitude = convertDMStoDD(dmsCoordinatePairDTO.getLongitude());
    return new DDCoordinatePairDTO(latitude, longitude);
  }
  
  
  private static BigDecimal convertDMStoDD(DMSCoordinateDTO dmsCoordinateDTO) {
    BigDecimal dd = ZERO;
    BigDecimal divisor60 = SIXTY;
    BigDecimal divisor3600 = THREETHOUSANDSIXHUNDRED;
    
    boolean negativeCoordinate = isNegative(dmsCoordinateDTO.getDirection());
    BigDecimal part1 = dmsCoordinateDTO.getMinutes();
    BigDecimal part2 = dmsCoordinateDTO.getSeconds();
        
    part1 = part1.divide(divisor60, SCALE, ROUNDINGMODE);
    part2 = part2.divide(divisor3600, SCALE, ROUNDINGMODE);
    dd = dmsCoordinateDTO.getDegrees().add(part1).add(part2);
    dd = negativeCoordinate ? dd.multiply(new BigDecimal(-1)) : dd;
    return dd;
  }

  private static DMSCoordinateDTO convertDDtoDMS(BigDecimal ddCoordinate, String coordinateType) {
    BigDecimal integralPart;
    BigDecimal integralPartFromMinutes;

    BigDecimal fractionaryPart;
    BigDecimal fractionaryPartFromMinutes;

    BigDecimal negativeOne = new BigDecimal(-1);
    // Get user input
    integralPart = ddCoordinate;
    fractionaryPart = ddCoordinate.subtract(integralPart);
    int answer = integralPart.compareTo(ZERO); // compare bg1 with bg2
    System.out.println(answer);
    if(answer==-1){
      integralPart = integralPart.multiply(negativeOne);
      fractionaryPart = fractionaryPart.multiply(negativeOne);
    }
    
    BigDecimal minutes = SIXTY.multiply(fractionaryPart);

    integralPartFromMinutes = minutes;
    fractionaryPartFromMinutes = minutes.subtract(integralPartFromMinutes);
    BigDecimal seconds = SIXTY.multiply(fractionaryPartFromMinutes);
    char direction = defineDirection(ddCoordinate, coordinateType);
    return new DMSCoordinateDTO(integralPart, minutes, seconds, direction);
  }

  private static char defineDirection(BigDecimal ddCoordinate, String coordinateType) {
    //    Remember, locations in the Southern Hemisphere (S) are at negative latitudes,
    //    and locations in the Western Hemisphere (W) are at negative longitudes. 
    //    Therefore, locations in the Northern Hemisphere (N) are at positive latitudes,
    //    and locations in the Eastern Hemisphere (E) are at positive longitudes.
    int answer = ddCoordinate.compareTo(ZERO);
    if (coordinateType.equalsIgnoreCase("Lat")) {
      
      if (answer==-1) {
        return 'S';
      } else {
        return 'N';
      }
    } else if (coordinateType.equalsIgnoreCase("Lon")) {
      if (answer==-1) {
        return 'W';
      } else {
        return 'E';
      }
    } else {
      throw new CoordinateTypeException("This coordinate type is not supported.");
    }
  }

  private static boolean isNegative(char orientation) {
    if (orientation == 'N' || orientation == 'E') {
      return false;
    } else if (orientation == 'S' || orientation == 'W') {
      return true;
    } else {
      throw new BadCoordinateException("Format of Coordinate not recognized or not supported.");
    }
  }

}
