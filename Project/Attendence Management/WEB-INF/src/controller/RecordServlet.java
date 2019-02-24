package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Emp;
import model.Record;
import model.Response;
import net.arnx.jsonic.JSON;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import orm.RecordORM;

public class RecordServlet extends HttpServlet
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");
    String date = req.getParameter("date");
    String select = req.getParameter("select");
    Response response = null;

    if (date != null && select == null) //dateIdがあるとき。ログインしたとき。
    {

      HttpSession session = req.getSession(false);
      Emp e = (Emp) session.getAttribute("loginuser");
      String empId = e.getId();
      try
      {
        Record record = RecordORM.select(date, empId);
        response = Response.getSuccess(record);

      }
      catch (IndexOutOfBoundsException ex)
      {
        response = Response.getSuccess(); //まだデータが入っていない場合
      }
      catch (Exception ex)
      {
        response = Response.getFailure(ex);
        ex.printStackTrace();
      }
    }
    else
    //期間検索
    {

      String empId = req.getParameter("id");
      String startDate = req.getParameter("startDate");
      String endDate = req.getParameter("endDate");

      //      String[] d = startDate.split("-");
      //      String[] e = endDate.split("-");
      //      String startId = d[0] + d[1] + d[2];
      //      String endId = e[0] + e[1] + e[2];

      try
      {
        List<Record> records = RecordORM.select(empId, startDate, endDate);
        response = Response.getSuccess(records);

      }
      catch (Exception ex)
      {

        response = Response.getFailure(ex);
        ex.printStackTrace();

      }
    }

    //    }
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("application/json");
    JSON.encode(response, resp.getWriter());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    // TODO 自動生成されたメソッド・スタブ
    req.setCharacterEncoding("UTF-8");
    if (req.getParameter("method").equals("put"))
    {
      doPut(req, resp);
      return;
    } else if (req.getParameter("method").equals("delete"))
    {
      doDelete(req, resp);
      return;
    }

    HttpSession session = req.getSession(false);
    Emp e = (Emp) session.getAttribute("loginuser");
    Response response = null;
    boolean insert = false;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    DateTime st = null;
    DateTime et = null;

    String empId = e.getId();
    String work = req.getParameter("workType");
    String startTime = req.getParameter("startTime");
    String endTime = req.getParameter("endTime");
    String date = req.getParameter("date");
    int restTime = 0;

    try
    {

      String[] d = date.split("-");
      String dateId = d[0] + d[1] + d[2];

      if (startTime != null && endTime != "")
      {
        st = new DateTime(sdf.parse(date + " " + startTime));
        et = new DateTime(sdf.parse(date + " " + endTime));
        int diff = Hours.hoursBetween(st, et).getHours();

        if (st.getHourOfDay() < 12 && et.getHourOfDay() >= 13)
        {
          restTime += 60;
        } else if ((st.getHourOfDay() == 12) && (et.getHourOfDay() == 12))
        {
          restTime = et.getMinuteOfHour() - st.getMinuteOfHour();
        }
        else if (st.getHourOfDay() == 12)
        {
          restTime = 60 - st.getMinuteOfHour();
        } else if (et.getHourOfDay() == 12)
        {
          restTime = et.getMinuteOfHour();
        }
        else
        {
          if (diff <= 6)
          {
            restTime = 0;
          }
          else if (diff >= 6)
          {
            restTime += 60;
          }
        }
        if ((diff >= 8) && Math.abs(et.getMinuteOfHour() - st.getMinuteOfHour()) >= 1)
        {
          diff -= 8;
          while (diff >= 0)
          {
            restTime += 30;
            diff -= 2;
          }

        }

        insert = RecordORM.insert(date, empId, (date + " " + startTime), (date + " " + endTime), work,
            String.valueOf(restTime), empId);
        if (insert)
        {
          Record record = RecordORM.select(date, empId);
          session.setAttribute("record", record);
          response = Response.getSuccess(record);
        }

      } else if (startTime != null && endTime == "")
      {
        st = new DateTime(sdf.parse(date + " " + startTime));
        insert = RecordORM.insert(date, empId, (date + " " + startTime), work, "0", empId);
        if (insert)
        {
          Record record = RecordORM.select(date, empId);
          response = Response.getSuccess(record);
        }
      } else if (startTime == null && endTime == null)
      {
        insert = RecordORM.insert(date, empId, null, null, work, "0", empId);
        if (insert)
        {
          Record record = RecordORM.select(date, empId);
          response = Response.getSuccess(record);
        }
      }

    }
    catch (SQLException ex)
    {
      if (ex.getMessage().contains("PK") || ex.getMessage().contains("ORA-00001"))
      {
        String message = "今日の勤務データが入力されています。" + "\n";
        message += "勤務データは一日一回しか入力できません。";
        response = Response.getFailure(message);
      } else
      {
        response = Response.getFailure(ex);
      }
      ex.printStackTrace();

    }
    catch (Exception ex)
    {
      response = Response.getFailure(ex);
      ex.printStackTrace();
      // TODO: handle exception
    }
    finally
    {
      resp.setCharacterEncoding("utf-8");
      resp.setContentType("application/json");
      JSON.encode(response, resp.getWriter());
    }

  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    Response response = null;
    HttpSession session = req.getSession(false);
    Emp e = (Emp) session.getAttribute("loginuser");
    String updateId = e.getId();
    String id = req.getParameter("id");
    String startTime = req.getParameter("startTime");
    String endTime = req.getParameter("endTime");
    String work = req.getParameter("workType");
    String date = req.getParameter("date");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    DateTime st = null;
    DateTime et = null;
    int restTime = 0;
    boolean update = false;

    try
    {
      String[] d = date.split("-");
      String dateId = d[0] + d[1] + d[2];

      if (startTime == null && endTime == null)
      {
        update = RecordORM.update(date, id, null, null, work,
            "0", updateId);
      }
      else if (startTime != null && endTime == "")
      {
        st = new DateTime(sdf.parse(date + " " + startTime));
        update = RecordORM.update(date, id, (date + " " + startTime), work, "0", updateId);
        if (update)
        {
          Record record = RecordORM.select(date, id);
          response = Response.getSuccess(record);
        }
      }

      else
      {
        st = new DateTime(sdf.parse(date + " " + startTime));
        et = new DateTime(sdf.parse(date + " " + endTime));
        int diff = Hours.hoursBetween(st, et).getHours();

        if (st.getHourOfDay() < 12 && et.getHourOfDay() >= 13)
        {
          restTime += 60;
        } else if ((st.getHourOfDay() == 12) && (et.getHourOfDay() == 12))
        {
          restTime = et.getMinuteOfHour() - st.getMinuteOfHour();
        }
        else if (st.getHourOfDay() == 12)
        {
          restTime = 60 - st.getMinuteOfHour();
        } else if (et.getHourOfDay() == 12)
        {
          restTime = et.getMinuteOfHour();
        }
        else
        {
          if (diff <= 6)
          {
            restTime = 0;
          }
          else if (diff >= 6)
          {
            restTime += 60;
          }
        }
        if ((diff >= 8) && Math.abs(et.getMinuteOfHour() - st.getMinuteOfHour()) >= 0)
        {
          diff -= 8;
          while (diff > 0)
          {
            restTime += 30;
            diff -= 2;
          }

        }
        update = RecordORM.update(date, id, (date + " " + startTime), (date + " " + endTime), work,
            String.valueOf(restTime), updateId);
      }

      if (update)
      {
        Record record = RecordORM.select(date, id);
        response = Response.getSuccess(record);
      } else
      {
        response = Response.getFailure("変更ができませんでした。");
      }

    }
    catch (Exception ex)
    {
      response = Response.getFailure(ex);
      ex.printStackTrace();
      // TODO: handle exception
    }
    finally
    {
      resp.setCharacterEncoding("utf-8");
      resp.setContentType("application/json");
      JSON.encode(response, resp.getWriter());
    }

  }

}
