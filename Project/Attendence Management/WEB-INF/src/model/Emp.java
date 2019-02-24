package model;

import java.math.BigDecimal;

public class Emp
{
  private Integer id;
  private String name;
  private Integer departId;
  private String departName;
  private Integer sectionId;
  private String sectionName;
  private Integer positionId;
  private String positionName;

  public Emp(Object[] obj)
  {
    this(
        ((BigDecimal) obj[0]).intValue(),
        (String) obj[1],
        ((BigDecimal) obj[2]) == null ? null : ((BigDecimal) obj[2]).intValue(),
        (String) obj[3] == null ? null : (String) obj[3],
        ((BigDecimal) obj[4]) == null ? null : ((BigDecimal) obj[4]).intValue(),
        (String) obj[5] == null ? null : (String) obj[5],
        ((BigDecimal) obj[6]).intValue(),
        (String) obj[7]);

  }

  public Emp(Integer id, String name, Integer departId, String departName, Integer sectionId,
      String sectionName,
      Integer positionId, String positionName)
  {

    this.id = id;
    this.name = name;
    this.departId = departId;
    this.departName = departName;
    this.sectionId = sectionId;
    this.sectionName = sectionName;
    this.positionId = positionId;
    this.positionName = positionName;
  }

  public String getId()
  {
    return String.format("%06d", id);
  }

  public String getName()
  {
    return this.name;
  }

  public Integer getDepartId()
  {
    return departId;
  }

  public String getDepartName()
  {
    return departName;
  }

  public Integer getSectionId()
  {
    return sectionId;
  }

  public String getSectionName()
  {
    return sectionName;
  }

  public Integer getPositionId()
  {
    return positionId;
  }

  public String getPositionName()
  {
    return positionName;
  }

}
