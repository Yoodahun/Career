package orm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Record;
import database.OracleClient;

public class RecordORM
{

  public static String select(String id) throws ClassNotFoundException, SQLException
  {
    /*すべてのタスクを探して完了しているかどうか、期限はいつまでなのかの順番でソートして返す。*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" MIN(DATE_ID) ");
    sql.append(" FROM ");
    sql.append(" RECORD ");
    sql.append(" WHERE ");
    sql.append(" EMP_ID = ?");
    sql.append(" AND ");
    sql.append(" ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, id);
    Object[] a = al1.get(0);
    Date date = (Date) a[0];

    return new SimpleDateFormat("yyyy-MM-dd")
        .format(date);

  }

  public static Record select(String dateId, String empId) throws ClassNotFoundException, SQLException
  {
    /*すべてのタスクを探して完了しているかどうか、期限はいつまでなのかの順番でソートして返す。*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" R.DATE_ID, R.EMP_ID, R.START_TIME, R.END_TIME, R.WORK_ID, W.NAME, R.REST_TIME ");
    sql.append(" FROM ");
    sql.append(" RECORD R INNER JOIN WORK W ");
    sql.append(" ON R.WORK_ID = W.ID ");
    sql.append(" WHERE ");
    sql.append(" R.DATE_ID = TO_DATE(?, 'YYYY-MM-DD HH24:MI') ");
    sql.append(" AND ");
    sql.append(" R.EMP_ID = ? ");
    sql.append(" AND ");
    sql.append(" R.ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, dateId, empId);

    return new Record(al1.get(0));

  }

  public static Record select(int id) throws ClassNotFoundException, SQLException
  {
    /*　IDで探す。1件しかReturnしない　*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" R.DATE_ID, R.EMP_ID, R.START_TIME, R.END_TIME, R.WORK_ID, W.NAME, R.REST_TIME ");
    sql.append(" FROM ");
    sql.append(" RECORD R INNER JOIN WORK W ");
    sql.append(" ON R.WORK_ID = W.ID ");
    sql.append(" WHERE ");
    sql.append(" R.DATE_ID = TO_DATE(?, 'YYYY-MM-DD HH24:MI') ");
    sql.append(" AND ");
    sql.append(" R.ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, Integer.toString(id));

    return new Record(al1.get(0));

  }

  public static List<Record> select(String empId, String startDate, String endDate)
      throws ClassNotFoundException,
      SQLException
  {
    /*　IDで探す。1件しかReturnしない　*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" R.DATE_ID, R.EMP_ID, R.START_TIME, R.END_TIME, R.WORK_ID, W.NAME, R.REST_TIME ");
    sql.append(" FROM ");
    sql.append(" RECORD R LEFT OUTER JOIN WORK W ");
    sql.append(" ON R.WORK_ID = W.ID ");
    sql.append(" WHERE ");
    //    sql.append(" ( TO_CHAR(R.START_TIME, 'YY-MM-DD') ");
    //    sql.append(" BETWEEN ? AND ?  ");
    //    sql.append(" OR ");
    //    sql.append(" (R.START_TIME IS NULL ");
    //    sql.append(" OR ");
    //    sql.append(" R.END_TIME IS NULL  ");
    //    sql.append(" AND ");
    sql.append(" (TO_CHAR(R.DATE_ID, 'YYYY-MM-DD') ");
    sql.append(" BETWEEN ? AND ?  ) ");
    sql.append(" AND ");
    sql.append(" R.EMP_ID = ? ");
    sql.append(" AND ");
    sql.append(" R.ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, startDate, endDate, empId);
    List<Record> al2 = new ArrayList<Record>();
    for (Object[] oa : al1)
    {
      Record record = new Record(oa);
      al2.add(record);
    }
    return al2;
  }

  public static boolean insert(String dateId, String empId, String startTime, String endTime,
      String workId, String restTime, String createId) throws SQLException, ClassNotFoundException
  {

    /*
     * Insertする。
     * PKであるIDは Oracle Sequenceを使う。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO RECORD ");
    sql.append("(");

    sql.append(" DATE_ID, ");
    sql.append(" EMP_ID, ");
    sql.append(" START_TIME, ");
    sql.append(" END_TIME, ");
    sql.append(" WORK_ID, ");
    sql.append(" REST_TIME, ");
    sql.append(" CREATE_ID, ");
    sql.append(" CREATE_TIME ");

    sql.append(")");
    sql.append("VALUES ");
    sql.append("( ");
    sql.append(" ?, ");
    sql.append(" ?, ");
    sql.append(" TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ");
    sql.append(" TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ");
    sql.append(" ?, ");
    sql.append(" ?, ");
    sql.append(" ?, ");
    sql.append(" SYSDATE ");

    sql.append(")");

    return OracleClient.execute(sql, dateId, empId, startTime, endTime, workId, restTime, createId);

  }

  public static boolean insert(String dateId, String empId, String startTime,
      String workId, String restTime, String createId) throws SQLException, ClassNotFoundException
  {

    /*
     * Insertする。
     * PKであるIDは Oracle Sequenceを使う。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO RECORD ");
    sql.append("(");

    sql.append(" DATE_ID, ");
    sql.append(" EMP_ID, ");
    sql.append(" START_TIME, ");
    sql.append(" WORK_ID, ");
    sql.append(" REST_TIME, ");
    sql.append(" CREATE_ID, ");
    sql.append(" CREATE_TIME ");

    sql.append(")");
    sql.append("VALUES ");
    sql.append("( ");
    sql.append(" ?, ");
    sql.append(" ?, ");
    sql.append(" TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ");
    sql.append(" ?, ");
    sql.append(" ?, ");
    sql.append(" ?, ");
    sql.append(" SYSDATE ");

    sql.append(")");

    return OracleClient.execute(sql, dateId, empId, startTime, workId, restTime, createId);

  }

  public static boolean update(String dateId, String empId, String startTime, String endTime, String workId,
      String restTime, String updateId) throws SQLException
  {
    /*
     * Update
     * IDで探したタスクの内容や完了状況・優先度・期限・備考などを修正する。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE RECORD ");
    sql.append(" SET ");

    sql.append(" START_TIME = TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ");
    sql.append(" END_TIME = TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ");
    sql.append(" WORK_ID = ?, ");
    sql.append(" REST_TIME = ?, ");
    sql.append(" UPDATE_ID = ?, ");
    sql.append(" UPDATE_TIME = SYSDATE ");
    sql.append(" WHERE ");
    sql.append(" DATE_ID = TO_DATE(?, 'YYYY-MM-DD HH24:MI') ");
    sql.append(" AND ");
    sql.append(" EMP_ID = ? ");

    return OracleClient.execute(sql, startTime, endTime, workId, restTime, updateId, dateId, empId);

  }

  public static boolean update(String dateId, String empId, String startTime, String workId,
      String restTime, String updateId) throws SQLException
  {
    /*
     * Update
     * IDで探したタスクの内容や完了状況・優先度・期限・備考などを修正する。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE RECORD ");
    sql.append(" SET ");

    sql.append(" START_TIME = TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ");
    sql.append(" WORK_ID = ?, ");
    sql.append(" REST_TIME = ?, ");
    sql.append(" UPDATE_ID = ?, ");
    sql.append(" UPDATE_TIME = SYSDATE ");
    sql.append(" WHERE ");
    sql.append(" DATE_ID = TO_DATE(?, 'YYYY-MM-DD HH24:MI') ");
    sql.append(" AND ");
    sql.append(" EMP_ID = ? ");

    return OracleClient.execute(sql, startTime, workId, restTime, updateId, dateId, empId);

  }

  public static boolean delete(String id) throws SQLException
  {
    /*
     * Delete
     * IDで探したタスクを削除する。
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE RECORD");
    sql.append(" SET ");

    sql.append(" ISDELETED = 1 ");

    sql.append(" WHERE ");
    sql.append(" DATE_ID = ? AND EMP_ID = ? ");

    return OracleClient.execute(sql, id);

  }

}
