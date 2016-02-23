package org.jcc.dto;

public class DMSCoordinateDTO {
  
  double degrees;
  double minutes;
  double seconds;
  char direction;
  
  public DMSCoordinateDTO(double degree, double minutes, double seconds, char direction){
    super();
    this.degrees=degree;
    this.minutes=minutes;
    this.seconds=seconds;
    this.direction=direction;
  }

  public double getDegrees() {
    return degrees;
  }

  public void setDegrees(double degrees) {
    this.degrees = degrees;
  }

  public double getMinutes() {
    return minutes;
  }

  public void setMinutes(double minutes) {
    this.minutes = minutes;
  }

  public double getSeconds() {
    return seconds;
  }

  public void setSeconds(double seconds) {
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
    long temp;
    temp = Double.doubleToLongBits(degrees);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + direction;
    temp = Double.doubleToLongBits(minutes);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(seconds);
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    if (Double.doubleToLongBits(degrees) != Double.doubleToLongBits(other.degrees))
      return false;
    if (direction != other.direction)
      return false;
    if (Double.doubleToLongBits(minutes) != Double.doubleToLongBits(other.minutes))
      return false;
    if (Double.doubleToLongBits(seconds) != Double.doubleToLongBits(other.seconds))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DMSCoordinateDTO [degrees=" + degrees + ", minutes=" + minutes + ", seconds=" + seconds + ", direction=" + direction + "]";
  }
}
