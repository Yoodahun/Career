package orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Emp;
import database.OracleClient;

public class EmpORM1
{
  public static Emp select(String id, String password) throws ClassNotFoundException, SQLException
  {
    /*すべてのタスクを探して完了しているかどうか、期限はいつまでなのかの順番でソートして返す。*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" E.ID, E.NAME, D.ID, D.NAME, S.ID, S.NAME, P.ID, P.NAME ");
    sql.append(" FROM ");
    sql.append(" EMP E INNER JOIN DEPART D ON E.DEPART_ID = D.ID ");
    sql.append(" INNER JOIN SECTION S ON E.SECTION_ID = S.ID AND E.DEPART_ID = S.DEPART_ID ");
    sql.append(" INNER JOIN POSITION P ON E.POSITION_ID = P.ID ");
    sql.append(" WHERE ");
    sql.append(" E.ID = ? ");
    sql.append(" AND ");
    sql.append(" E.PASSWORD = ? ");
    sql.append(" AND ");
    sql.append(" E.ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, id, password);

    return new Emp(al1.get(0));

  }

  public static Emp select(String id) throws ClassNotFoundException, SQLException
  {
    /*　IDで探す。1件しかReturnしない　*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" E.ID, E.NAME, D.ID, D.NAME, S.ID, S.NAME, P.ID, P.NAME ");
    sql.append(" FROM ");
    sql.append(" EMP E INNER JOIN DEPART D ON E.DEPART_ID = D.ID ");
    sql.append(" INNER JOIN SECTION S ON E.SECTION_ID = S.ID AND E.DEPART_ID = S.DEPART_ID ");
    sql.append(" INNER JOIN POSITION P ON E.POSITION_ID = P.ID ");
    sql.append(" WHERE ");
    sql.append(" E.ID = ? ");
    sql.append(" AND ");
    sql.append(" E.ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, id);

    return new Emp(al1.get(0));

  }

  public static List<Emp> select() throws ClassNotFoundException, SQLException
  {
    /*　検索　
     * 件名や備考のなかで探す。
     * LIKE　キーワードと％を使う。*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" E.ID, E.NAME, D.ID, D.NAME, S.ID, S.NAME, P.ID, P.NAME ");
    sql.append(" FROM ");
    sql.append(" EMP E INNER JOIN DEPART D ON E.DEPART_ID = D.ID ");
    sql.append(" INNER JOIN SECTION S ON E.SECTION_ID = S.ID AND E.DEPART_ID = S.DEPART_ID ");
    sql.append(" INNER JOIN POSITION P ON E.POSITION_ID = P.ID ");
    sql.append(" WHERE ");
    sql.append(" E.ISDELETED = 0 ");
    sql.append(" ORDER BY ");
    sql.append(" E.POSITION_ID ");

    List<Object[]> al1 = OracleClient.select(sql);
    List<Emp> al2 = new ArrayList<Emp>();
    for (Object[] oa : al1)
    {
      Emp task = new Emp(oa);
      al2.add(task);
    }
    return al2;

  }

  public static List<Emp> select(int positionId, int departId) throws ClassNotFoundException,
      SQLException
  {
    /*　検索　
     * 件名や備考のなかで探す。
     * LIKE　キーワードと％を使う。*/
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT ");
    sql.append(" E.ID, E.NAME, D.ID, D.NAME, S.ID, S.NAME, P.ID, P.NAME ");
    sql.append(" FROM ");
    sql.append(" EMP E INNER JOIN DEPART D ON E.DEPART_ID = D.ID ");
    sql.append(" INNER JOIN SECTION S ON E.SECTION_ID = S.ID AND E.DEPART_ID = S.DEPART_ID ");
    sql.append(" INNER JOIN POSITION P ON E.POSITION_ID = P.ID ");
    sql.append(" WHERE ");
    sql.append(" E.POSITION_ID >= ? ");
    sql.append(" AND ");
    sql.append(" E.DEPART_ID = ? ");
    sql.append(" AND ");
    sql.append(" E.ISDELETED = 0 ");
    sql.append(" ORDER BY ");
    sql.append(" E.ID ");

    List<Object[]> al1 = OracleClient.select(sql, Integer.toString(positionId), Integer.toString(departId));
    List<Emp> al2 = new ArrayList<Emp>();
    for (Object[] oa : al1)
    {
      Emp task = new Emp(oa);
      al2.add(task);
    }
    return al2;

  }

  public static List<Emp> select(int positionId, int departId, int sectionId) throws ClassNotFoundException,
      SQLException
  {
    /*　検索　
     * 件名や備考のなかで探す。
     * LIKE　キーワードと％を使う。*/
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT ");
    sql.append(" E.ID, E.NAME, D.ID, D.NAME, S.ID, S.NAME, P.ID, P.NAME ");
    sql.append(" FROM ");
    sql.append(" EMP E INNER JOIN DEPART D ON E.DEPART_ID = D.ID ");
    sql.append(" INNER JOIN SECTION S ON E.SECTION_ID = S.ID AND E.DEPART_ID = S.DEPART_ID ");
    sql.append(" INNER JOIN POSITION P ON E.POSITION_ID = P.ID ");
    sql.append(" WHERE ");
    sql.append(" E.POSITION_ID >= ? ");
    sql.append(" AND ");
    sql.append(" E.DEPART_ID = ? ");
    sql.append(" AND ");
    sql.append(" E.SECTION_ID = ? ");
    sql.append(" AND ");
    sql.append(" ISDELETED = 0 ");
    sql.append(" ORDER BY ");
    sql.append(" E.ID ");

    List<Object[]> al1 = OracleClient.select(sql, Integer.toString(positionId),
        Integer.toString(departId),
        Integer.toString(sectionId));
    List<Emp> al2 = new ArrayList<Emp>();
    for (Object[] oa : al1)
    {
      Emp task = new Emp(oa);
      al2.add(task);
    }
    return al2;

  }

  public static Emp insert(String name, String password, String depart, String section,
      String position) throws SQLException, ClassNotFoundException
  {
    /*
     * Insertする。
     * PKであるIDは Oracle Sequenceを使う。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO EMP ");
    sql.append("(");

    sql.append(" ID, ");
    sql.append(" NAME, ");
    sql.append(" PASSWORD, ");
    sql.append(" DEPART_ID, ");
    sql.append(" SECTION_ID,");
    sql.append(" POSITION_ID ");

    sql.append(")");
    sql.append("VALUES ");
    sql.append("( ");
    sql.append(" SEQ_EMP.nextval, ?, ?, ?, ?, ? ");
    sql.append(")");

    int i = OracleClient.insert(sql, name, password, depart, section, position);

    return select(String.valueOf(i));

  }

  public static boolean update(String id, String name, String depart, String section, String position)
      throws SQLException
  {
    /*
     * Update
     * IDで探したタスクの内容や完了状況・優先度・期限・備考などを修正する。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE EMP ");
    sql.append(" SET ");

    sql.append(" NAME = ?, ");
    sql.append(" DEPART_ID = ?, ");
    sql.append(" SECTION_ID = ?,");
    sql.append(" POSITION_ID = ? ");

    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, name, depart, section,
        position, id);

  }

  public static boolean update(String id, String password) throws SQLException
  {

    /*
     * Update
     * IDで探したタスクの完了の状況だけ変更する。
     *
     * */

    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE EMP ");
    sql.append(" SET ");
    sql.append(" PASSWORD = ? ");
    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, password, id);

  }

  public static boolean delete(String id) throws SQLException
  {
    /*
     * Delete
     * IDで探したタスクを削除する。
     * */
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE EMP ");
    sql.append(" SET ");
    sql.append(" ISDELETED = 1 ");
    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, id);

  }

}
