package org.jcc.dto;


public class DMSCoordinatePairDTO {

  DMSCoordinateDTO latitude;

  DMSCoordinateDTO longitude;
  
  public DMSCoordinatePairDTO(){
    super();
  }
  
  public DMSCoordinatePairDTO(DMSCoordinateDTO latitude, DMSCoordinateDTO longitude){
    super();
    this.latitude=latitude;
    this.longitude=longitude;
  }

  public DMSCoordinateDTO getLatitude() {
    return latitude;
  }

  public void setLatitude(DMSCoordinateDTO latitude) {
    this.latitude = latitude;
  }

  public DMSCoordinateDTO getLongitude() {
    return longitude;
  }

  public void setLongitude(DMSCoordinateDTO longitude) {
    this.longitude = longitude;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
    result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DMSCoordinatePairDTO other = (DMSCoordinatePairDTO) obj;
    if (latitude == null) {
      if (other.latitude != null)
        return false;
    } else if (!latitude.equals(other.latitude))
      return false;
    if (longitude == null) {
      if (other.longitude != null)
        return false;
    } else if (!longitude.equals(other.longitude))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DMSCoordinatePairDTO [latitude=" + latitude + ", longitude=" + longitude + "]";
  }
}
