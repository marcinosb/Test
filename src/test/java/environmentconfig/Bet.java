package environmentconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Bet {
  @XmlElement
  String betNum = "";
  @XmlElement
  String delayBetId = "";
  @XmlElement
  String betTypeCode = "";
  @XmlElement
  String stake = "";
  @XmlElement
  Leg leg;

  public Bet(){}

  public Bet(String betNum, String delayBetId, String betTypeCode, String stake, Leg leg) {
    this.betNum = betNum;
    this.delayBetId = delayBetId;
    this.betTypeCode = betTypeCode;
    this.stake = stake;
    this.leg = leg;
  }

  public String getBetNum() {
    return betNum;
  }

  public void setBetNum(String betNum) {
    this.betNum = betNum;
  }

  public String getDelayBetId() {
    return delayBetId;
  }

  public void setDelayBetId(String delayBetId) {
    this.delayBetId = delayBetId;
  }

  public String getBetTypeCode() {
    return betTypeCode;
  }

  public void setBetTypeCode(String betTypeCode) {
    this.betTypeCode = betTypeCode;
  }

  public String getStake() {
    return stake;
  }

  public void setStake(String stake) {
    this.stake = stake;
  }

  public Leg getLeg() {
    return leg;
  }

  public void setLeg(Leg leg) {
    this.leg = leg;
  }
}
