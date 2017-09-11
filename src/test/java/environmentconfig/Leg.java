package environmentconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Leg {

  @XmlElement
  String legType = "";
  @XmlElement
  Part part;

  public Leg() {}

  public Leg(String legType, Part part) {
    this.legType = legType;
    this.part = part;
  }

  public String getLegType() {
    return legType;
  }

  public void setLegType(String legType) {
    this.legType = legType;
  }

  public Part getPart() {
    return part;
  }

  public void setPart(Part part) {
    this.part = part;
  }
}
