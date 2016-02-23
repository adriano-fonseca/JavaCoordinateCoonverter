package org.jcc.converter;

import org.jcc.coordinate.type.UtilEnums;
import org.jcc.dto.DDCoordinatePairDTO;
import org.jcc.dto.DMSCoordinateDTO;
import org.jcc.dto.DMSCoordinatePairDTO;
import org.jcc.exception.BadCoordinateException;
import org.jcc.exception.CoordinateTypeException;

public class CoordinateConverter {
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
    double latitude = convertDMStoDD(dmsCoordinatePairDTO.getLatitude());
    double longitude = convertDMStoDD(dmsCoordinatePairDTO.getLongitude());
    return new DDCoordinatePairDTO(latitude, longitude);
  }
  
  
  private static double convertDMStoDD(DMSCoordinateDTO dmsCoordinateDTO) {
    double dd = 0;
    boolean negativeCoordinate = isNegative(dmsCoordinateDTO.getDirection());
    double part1 = (dmsCoordinateDTO.getMinutes() / 60);
    double part2 = (dmsCoordinateDTO.getSeconds() / 3600);
    dd = dmsCoordinateDTO.getDegrees() + part1 + part2;
    dd = negativeCoordinate ? (dd * -1D) : dd;
    return dd;
  }

  private static DMSCoordinateDTO convertDDtoDMS(double ddCoordinate, String coordinateType) {
    long integralPart;
    long integralPartFromMinutes;

    double fractionaryPart;
    double fractionaryPartFromMinutes;

    // Get user input
    integralPart = (long) ddCoordinate;
    fractionaryPart = ddCoordinate - integralPart;
    if(integralPart<0){
      integralPart = integralPart*-1;
      fractionaryPart = fractionaryPart*-1D;
    }
    
    double minutes = 60 * (fractionaryPart);

    integralPartFromMinutes = (long) minutes;
    fractionaryPartFromMinutes = minutes - integralPartFromMinutes;
    double seconds = 60 * fractionaryPartFromMinutes;
    char direction = defineDirection(ddCoordinate, coordinateType);
    return new DMSCoordinateDTO(integralPart, minutes, seconds, direction);

  }

  private static char defineDirection(double ddCoordinate, String coordinateType) {
    //    Remember, locations in the Southern Hemisphere (S) are at negative latitudes,
    //    and locations in the Western Hemisphere (W) are at negative longitudes. 
    //    Therefore, locations in the Northern Hemisphere (N) are at positive latitudes,
    //    and locations in the Eastern Hemisphere (E) are at positive longitudes.

    if (coordinateType.equalsIgnoreCase("Lat")) {
      if (ddCoordinate < 0) {
        return 'S';
      } else {
        return 'N';
      }
    } else if (coordinateType.equalsIgnoreCase("Lon")) {
      if (ddCoordinate < 0) {
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
