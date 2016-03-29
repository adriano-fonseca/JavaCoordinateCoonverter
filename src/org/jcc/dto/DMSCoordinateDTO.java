package org.jcc.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DMSCoordinateDTO {
  
  int degrees;
  int minutes;
  BigDecimal seconds;
  char direction;
  
  final static int SCALE = 13;
  final static RoundingMode ROUNDINGMODE = RoundingMode.HALF_UP;
  
  public DMSCoordinateDTO(int degree, int minutes, BigDecimal seconds, char direction){
    super();
    this.degrees=degree;
    this.minutes=minutes;
    this.seconds=seconds.setScale(3, ROUNDINGMODE);
    this.direction=direction;
  }

  public int getDegrees() {
    return degrees;
  }

  public void setDegrees(int degrees) {
    this.degrees = degrees;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
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
    result = prime * result + degrees;
    result = prime * result + direction;
    result = prime * result + minutes;
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
    if (degrees != other.degrees)
      return false;
    if (direction != other.direction)
      return false;
    if (minutes != other.minutes)
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
