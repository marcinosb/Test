package environmentconfig;

public class Bet {
  String whoBets = "";
  String bet = "";
  String betNum = "";
  String delayBetId = "";
  String betTypeCode = "";
  String stake = "";
  String leg = "";
  String legType = "";
  String part = "";
  String outcomeId = "";
  String priceType = "";
  String priceNum  = "";
  String priceDen = "";

  public Bet(String betNum, String delayBetId, String betTypeCode, String stake, String legType, String outcomeId, String priceType, String priceDen, String priceNum){
    this.betNum = betNum;
    this.delayBetId = delayBetId;
    this.betTypeCode = betTypeCode;
    this.stake = stake;
    this.legType = legType;
    this.outcomeId = outcomeId;
    this.priceDen = priceDen;
    this.priceNum = priceNum;
    this.priceType = priceType;
  }

  public String getWhoBets() {
    return whoBets;
  }

  public void setWhoBets(String whoBets) {
    this.whoBets = whoBets;
  }

  public String getBet() {
    return bet;
  }

  public void setBet(String bet) {
    this.bet = bet;
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

  public String getLeg() {
    return leg;
  }

  public void setLeg(String leg) {
    this.leg = leg;
  }

  public String getLegType() {
    return legType;
  }

  public void setLegType(String legType) {
    this.legType = legType;
  }

  public String getPart() {
    return part;
  }

  public void setPart(String part) {
    this.part = part;
  }

  public String getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(String outcomeId) {
    this.outcomeId = outcomeId;
  }

  public String getPriceType() {
    return priceType;
  }

  public void setPriceType(String priceType) {
    this.priceType = priceType;
  }

  public String getPriceNum() {
    return priceNum;
  }

  public void setPriceNum(String priceNum) {
    this.priceNum = priceNum;
  }

  public String getPriceDen() {
    return priceDen;
  }

  public void setPriceDen(String priceDen) {
    this.priceDen = priceDen;
  }
}