package model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record
{
  private Date id;
  private Integer empId;
  private Date startTime;
  private Date endTime;
  private Integer workId;
  private String workTypeName;
  private int restTime;

  public Record(Object[] obj)
  {
    this((Date) obj[0], ((BigDecimal) obj[1]).intValue(),
        (Date) obj[2] == null ? null : (Date) obj[2], obj[3] == null ? null
            : (Date) obj[3], ((BigDecimal) obj[4]).intValue(), (String) obj[5],
        obj[6] == null ? 0 : ((BigDecimal) obj[6]).intValue());
  }

  public Record(Date id, Integer empId, Date startTime, Date endTime,
      Integer workId, String workName, int restTime)
  {

    this.id = id;
    this.empId = empId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.workId = workId;
    this.workTypeName = workName;
    this.restTime = restTime;
  }

  public String getStartTime()
  {
    return startTime == null ? null : new SimpleDateFormat("HH:mm")
        .format(startTime);
  }

  public String getId()
  {
    return new SimpleDateFormat("yyyy-MM-dd")
        .format(id);
  }

  public String getEmpId()
  {
    return String.format("%06d", empId);
  }

  public String getEndTime()
  {
    return endTime == null ? null : new SimpleDateFormat("HH:mm")
        .format(endTime);
  }

  public Integer getWorkId()
  {
    return workId;
  }

  public String getWorkTypeName()
  {
    return workTypeName;
  }

  public long getWorkTime()
  {
    long minutes = 0;

    if (endTime != null)

    {
      long longStartTime = startTime.getTime();
      long longEndTime = endTime.getTime();

      minutes = (longEndTime - longStartTime) / 60000;
      minutes = minutes - restTime;
    }

    return minutes;
  }

  public int getRestTime()
  {
    return restTime;
  }

}
