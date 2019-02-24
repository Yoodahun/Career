package model;

import java.math.BigDecimal;

public class Section extends Entity
{

  private Integer departId;
  private String departName;

  public Section(Object[] obj)
  {

    this(
        ((BigDecimal) obj[0]).intValue(),
        (String) obj[1],
        ((BigDecimal) obj[2]).intValue(),
        (String) obj[3]

    );

    // TODO 自動生成されたコンストラクター・スタブ
  }

  public Section(Integer departId, String departName, Integer id, String string)
  {
    super(id, string);
    this.departId = departId;
    this.departName = departName;

    // TODO 自動生成されたコンストラクター・スタブ
  }

  public Integer getDepartId()
  {
    return departId;
  }

  public String getDepartName()
  {
    return departName;
  }
}
