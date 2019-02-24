package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleClient
{
  private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
  private static final String USER = "APPUSER";
  private static final String PASSWORD = "APPPASS";

  static
  {
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    catch (ClassNotFoundException e)
    {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }
  }

  public static List<Object[]> select(StringBuffer sql, Object... params) throws SQLException, ClassNotFoundException
  {
    return select(sql.toString(), params);
  }

  public static List<Object[]> select(String sql, Object... params) throws SQLException, ClassNotFoundException
  {
    List<Object[]> al = null;
    Connection cn = null;
    try
    {
      // 接続する

      cn = DriverManager.getConnection(URL, USER, PASSWORD);

      //Query
      PreparedStatement st = cn.prepareStatement(sql);
      //      Statement st = cn.createStatement();

      //Setting parameter
      if (sql.contains("?") && params != null && params.length > 0)
      {
        int n = 1;
        for (Object param : params)
        {
          st.setObject(n, param);
          n++;
        }
      }

      //結果の取得する
      ResultSet rs = st.executeQuery();//結果
      ResultSetMetaData md = rs.getMetaData();//メタデータの取得
      int fieldCount = md.getColumnCount();//コラムの数
      al = new ArrayList<Object[]>();

      while (rs.next())
      {
        Object[] objectArray = new Object[fieldCount];//横に何個あるか
        for (int n = 0; n < fieldCount; n++)
        {
          objectArray[n] = rs.getObject(n + 1); //コラムのナンバー

        }
        al.add(objectArray);
      }
    }

    finally
    {
      //切断
      if (cn != null)
      {
        cn.close();
      }

    }

    return al;
  }

  public static int insert(StringBuffer sql, Object... params) throws SQLException
  {
    return insert(sql.toString(), params);
  }

  public static int insert(String sql, Object... params) throws SQLException
  {

    Connection cn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    int pkID = 0;

    try
    {
      // 接続する
      cn = DriverManager.getConnection(URL, USER, PASSWORD);
      st = cn.prepareStatement(sql, new String[] { "id" });

      //Queryss

      //Setting parameter
      if (sql.contains("?") && params != null && params.length > 0)
      {
        int n = 1;
        for (Object param : params)
        {
          st.setObject(n, param);
          n++;
        }
      }
      //結果の取得する

      st.executeQuery();
      rs = st.getGeneratedKeys();//自動的に生成されるPKの情報をもらう準備
      if (rs.next())
      {
        pkID = rs.getInt(1);//1列目のデータの取得。PK。
        System.out.println(pkID);
      }
    }

    finally
    {
      //切断
      if (cn != null)
      {
        cn.close();
      }

    }
    return pkID;
  }

  public static boolean execute(StringBuffer sql, Object... params) throws SQLException
  {
    return execute(sql.toString(), params);
  }

  public static boolean execute(String sql, Object... params) throws SQLException
  {
    boolean b = false;
    Connection cn = null;
    try
    {
      // 接続する
      cn = DriverManager.getConnection(URL, USER, PASSWORD);

      //Query
      PreparedStatement st = cn.prepareStatement(sql);

      //Setting parameter
      if (sql.contains("?") && params != null && params.length > 0)
      {
        int n = 1;
        for (Object param : params)
        {
          st.setObject(n, param);
          n++;
        }
      }
      //結果の取得する
      b = st.executeUpdate() > 0;

    }

    finally
    {
      //切断
      if (cn != null)
      {
        cn.close();
      }

    }
    return b;
  }
}
