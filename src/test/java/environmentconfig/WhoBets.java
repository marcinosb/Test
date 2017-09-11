package environmentconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "whoBets")
@XmlAccessorType(XmlAccessType.FIELD)
public class WhoBets {

  public WhoBets() {}

  public WhoBets(Bet bet) {
    this.bet = bet;
  }

  @XmlElement
  public Bet bet;

  public Bet getBet() {
    return bet;
  }

  public void setBet(Bet bet) {
    this.bet = bet;
  }
}