package org.jcc.format.parsers;

import org.jcc.dto.DDCoordinatePairDTO;
import org.jcc.dto.DMSCoordinateDTO;

public class ParserBetweenFormat {
  public static DDCoordinatePairDTO parseDDCoordinateToDDCoordinateDTO(String coordinate) {
    coordinate = "-45.4566 65.4566";
    String[] ddCoordinateArray = coordinate.split(" ");
    double latitude = Double.parseDouble(ddCoordinateArray[0]);
    double longitude = Double.parseDouble(ddCoordinateArray[1]);
    return new DDCoordinatePairDTO(latitude, longitude);

  }

  public static DMSCoordinateDTO parseDMSCoordinateToDMSCoordinateDTO(String coordinate) {
    coordinate = "40° 26\' 46\" W";
    int length = coordinate.length();
    String[] ddCoordinateArray = coordinate.split(" ");
    int indiceDegree = ddCoordinateArray[0].indexOf("°");
    char direction = coordinate.charAt(length - 1);

    int degrees = Integer.parseInt(ddCoordinateArray[0].substring(0, indiceDegree));
    int minutes = Integer.parseInt(ddCoordinateArray[1].substring(0, 2));
    int seconds = Integer.parseInt(ddCoordinateArray[2].substring(0, 2));
    return new DMSCoordinateDTO(degrees, minutes, seconds, direction);
  }
}
