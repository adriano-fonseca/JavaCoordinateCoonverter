package org.jcc.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DMSCoordinateDTO {
  
  BigDecimal degrees;
  BigDecimal minutes;
  BigDecimal seconds;
  char direction;
  
  final static int SCALE = 13;
  final static RoundingMode ROUNDINGMODE = RoundingMode.HALF_UP;
  
  public DMSCoordinateDTO(BigDecimal degree, BigDecimal minutes, BigDecimal seconds, char direction){
    super();
    this.degrees=degree.setScale(SCALE, ROUNDINGMODE);
    this.minutes=minutes.setScale(SCALE, ROUNDINGMODE);
    this.seconds=seconds.setScale(SCALE, ROUNDINGMODE);
    this.direction=direction;
  }

  public BigDecimal getDegrees() {
    return degrees;
  }

  public void setDegrees(BigDecimal degrees) {
    this.degrees = degrees;
  }

  public BigDecimal getMinutes() {
    return minutes;
  }

  public void setMinutes(BigDecimal minutes) {
    this.minutes = minutes;
  }

  public BigDecimal getSeconds() {
    return seconds;
  }

  public void setSeconds(BigDecimal seconds) {
    this.seconds = seconds;
  }

  public char getDirection() {
    return direction;
  }

  public void setDirection(char direction) {
    this.direction = direction;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((degrees == null) ? 0 : degrees.hashCode());
    result = prime * result + direction;
    result = prime * result + ((minutes == null) ? 0 : minutes.hashCode());
    result = prime * result + ((seconds == null) ? 0 : seconds.hashCode());
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
    DMSCoordinateDTO other = (DMSCoordinateDTO) obj;
    if (degrees == null) {
      if (other.degrees != null)
        return false;
    } else if (!degrees.equals(other.degrees))
      return false;
    if (direction != other.direction)
      return false;
    if (minutes == null) {
      if (other.minutes != null)
        return false;
    } else if (!minutes.equals(other.minutes))
      return false;
    if (seconds == null) {
      if (other.seconds != null)
        return false;
    } else if (!seconds.equals(other.seconds))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DMSCoordinateDTO [degrees=" + degrees + ", minutes=" + minutes + ", seconds=" + seconds + ", direction=" + direction + "]";
  }
}
