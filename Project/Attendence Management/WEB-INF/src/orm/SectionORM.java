package orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Section;
import database.OracleClient;

public class SectionORM
{
  public static List<Section> select() throws ClassNotFoundException,
      SQLException
  {
    /* すべてのタスクを探して完了しているかどうか、期限はいつまでなのかの順番でソートして返す。 */
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" S.DEPART_ID, D.NAME, S.ID, S.NAME ");
    sql.append(" FROM ");
    sql.append(" SECTION S INNER JOIN DEPART D ");
    sql.append(" ON S.DEPART_ID = D.ID ");
    sql.append(" WHERE ");
    sql.append(" S.ISDELETED = 0 ");
    sql.append(" ORDER BY ");
    sql.append(" S.DEPART_ID, S.ID ");

    List<Object[]> al1 = OracleClient.select(sql);
    List<Section> al2 = new ArrayList<Section>();
    for (Object[] oa : al1)
    {
      Section section = new Section(oa);
      al2.add(section);
    }
    return al2;

  }

  public static List<Section> select(String departId) throws ClassNotFoundException,
      SQLException
  {
    /* すべてのタスクを探して完了しているかどうか、期限はいつまでなのかの順番でソートして返す。 */
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" S.DEPART_ID, D.NAME, S.ID, S.NAME ");
    sql.append(" FROM ");
    sql.append(" SECTION S INNER JOIN DEPART D ");
    sql.append(" ON S.DEPART_ID = D.ID ");
    sql.append(" WHERE ");
    sql.append(" S.DEPART_ID = ? ");
    sql.append(" AND ");
    sql.append(" S.ISDELETED = 0 ");
    sql.append(" ORDER BY ");
    sql.append(" S.ID ");

    List<Object[]> al1 = OracleClient.select(sql, departId);
    List<Section> al2 = new ArrayList<Section>();
    for (Object[] oa : al1)
    {
      Section section = new Section(oa);
      al2.add(section);
    }
    return al2;

  }

  public static Section select(int id) throws ClassNotFoundException,
      SQLException
  {
    /* 　IDで探す。1件しかReturnしない　 */
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" S.DEPART_ID, D.NAME, S.ID, S.NAME ");
    sql.append(" FROM ");
    sql.append(" SECTION S INNER JOIN DEPART D ");
    sql.append(" ON S.DEPART_ID = D.ID ");
    sql.append(" WHERE ");
    sql.append(" S.ID = ? ");
    sql.append(" AND ");
    sql.append(" S.ISDELETED = 0 ");

    List<Object[]> al1 = OracleClient.select(sql, Integer.toString(id));

    return new Section(al1.get(0));

  }

  public static Section insert(String departId, String name) throws SQLException,
      ClassNotFoundException
  {
    /*
     * Insertする。 PKであるIDは Oracle Sequenceを使う。
     */
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO SECTION ");
    sql.append("(");
    sql.append("DEPART_ID, ");
    sql.append("ID, ");
    sql.append("NAME");

    sql.append(")");
    sql.append("VALUES ");
    sql.append("( ");
    sql.append(" ?, SEQ_SECTION.nextval, ? ");
    sql.append(")");

    int i = OracleClient.insert(sql, departId, name);
    return select(i);

  }

  public static boolean update(String id, String departId, String name) throws SQLException
  {
    /*
     * Update IDで探したタスクの内容や完了状況・優先度・期限・備考などを修正する。
     */
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE SECTION ");
    sql.append(" SET ");
    sql.append(" DEPART_ID = ?, ");
    sql.append(" NAME = ? ");

    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, departId, name, id);

  }

  public static boolean delete(String id) throws SQLException
  {
    /*
     * Delete IDで探したタスクを削除する。
     */
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE SECTION");
    sql.append(" SET ");

    sql.append(" ISDELETED = 1 ");

    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, id);

  }

}
