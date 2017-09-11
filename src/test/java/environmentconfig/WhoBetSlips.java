package environmentconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "whoBetslips")
@XmlAccessorType(XmlAccessType.FIELD)
public class WhoBetSlips {

  @XmlElement
  public Leg leg;

  public WhoBetSlips(Leg leg) {
    this.leg = leg;
  }

  public WhoBetSlips(){ }

  public Leg getLeg() {
    return leg;
  }

  public void setLeg(Leg leg) {
    this.leg = leg;
  }
}
