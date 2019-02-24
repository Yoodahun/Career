package model;

import java.math.BigDecimal;

public class Entity
{
  protected Integer id;
  private String name;

  public Entity(Object[] obj)
  {
    this(
        ((BigDecimal) obj[0]).intValue(),
        (String) obj[1]);

  }

  public Entity(Integer id, String name)
  {
    this.id = id;
    this.name = name;

    // TODO 自動生成されたコンストラクター・スタブ
  }

  public Integer getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

}
