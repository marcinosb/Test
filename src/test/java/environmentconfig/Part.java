package environmentconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Part {

  @XmlElement
  String outcomeId = "";
  @XmlElement
  String priceType = "";
  @XmlElement
  String priceNum  = "";
  @XmlElement
  String priceDen = "";

  public Part() {}

  public Part(String outcomeId, String priceType, String priceNum, String priceDen) {
    this.outcomeId = outcomeId;
    this.priceType = priceType;
    this.priceNum = priceNum;
    this.priceDen = priceDen;
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
