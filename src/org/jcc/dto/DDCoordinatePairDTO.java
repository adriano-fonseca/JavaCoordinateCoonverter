package org.jcc.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DDCoordinatePairDTO {
  private BigDecimal latitude;
  private BigDecimal longitude;
  final static int SCALE = 13;
  final static RoundingMode ROUNDINGMODE = RoundingMode.HALF_UP;
  
  public DDCoordinatePairDTO(){
    super();
  }
  
  public DDCoordinatePairDTO(BigDecimal latitude, BigDecimal longitude) {
    super();
    this.latitude=latitude.setScale(SCALE, ROUNDINGMODE);
    this.longitude=longitude.setScale(SCALE, ROUNDINGMODE);
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
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
    DDCoordinatePairDTO other = (DDCoordinatePairDTO) obj;
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
    return "DDCoordinatePairDTO [latitude=" + latitude + ", longitude=" + longitude + "]";
  }
  
  
}
