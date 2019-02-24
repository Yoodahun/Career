package orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Entity;
import database.OracleClient;

public class DepartORM
{
  public static List<Entity> select() throws ClassNotFoundException, SQLException
  {
    /*すべてのタスクを探して完了しているかどうか、期限はいつまでなのかの順番でソートして返す。*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" * ");
    sql.append(" FROM ");
    sql.append(" DEPART ");
    sql.append(" WHERE ");
    sql.append(" ISDELETED = 0 ");
    sql.append(" ORDER BY ");
    sql.append(" ID");

    List<Object[]> al1 = OracleClient.select(sql);
    List<Entity> al2 = new ArrayList<Entity>();
    for (Object[] oa : al1)
    {
      Entity task = new Entity(oa);
      al2.add(task);
    }
    return al2;

  }

  public static Entity select(int number) throws ClassNotFoundException, SQLException
  {
    /*　IDで探す。1件しかReturnしない　*/
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append(" * ");
    sql.append(" FROM ");
    sql.append(" DEPART ");
    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    List<Object[]> al1 = OracleClient.select(sql, Integer.toString(number));

    return new Entity(al1.get(0));

  }

  public static Entity insert(String name) throws SQLException, ClassNotFoundException
  {
    /*
     * Insertする。
     * PKであるIDは Oracle Sequenceを使う。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO DEPART ");
    sql.append("(");

    sql.append("ID, ");
    sql.append("NAME");

    sql.append(")");
    sql.append("VALUES ");
    sql.append("( ");
    sql.append(" SEQ_DEPART.nextval, ? ");
    sql.append(")");

    int i = OracleClient.insert(sql, name);
    return select(i);

  }

  public static boolean update(String id, String name) throws SQLException
  {
    /*
     * Update
     * IDで探したタスクの内容や完了状況・優先度・期限・備考などを修正する。
     *
     * */
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE DEPART ");
    sql.append(" SET ");

    sql.append(" NAME = ? ");

    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, name, id);

  }

  public static boolean delete(String id) throws SQLException
  {
    /*
     * Delete
     * IDで探したタスクを削除する。
     * */
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE DEPART ");
    sql.append(" SET ");

    sql.append(" ISDELETED = 1 ");

    sql.append(" WHERE ");
    sql.append(" ID = ? ");

    return OracleClient.execute(sql, id);

  }
}
