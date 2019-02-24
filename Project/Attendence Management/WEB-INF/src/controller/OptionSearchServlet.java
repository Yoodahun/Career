package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Emp;
import model.Response;
import net.arnx.jsonic.JSON;
import orm.RecordORM;

public class OptionSearchServlet extends HttpServlet
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");
    Response response = null;
    HttpSession session = req.getSession(false);
    Emp e = (Emp) session.getAttribute("loginuser");
    try
    {
      String minDate = RecordORM.select(e.getId());
      if (minDate != null)
      {
        response = Response.getSuccess(minDate);
      } else
      {
        response = Response.getFailure("DBにまだデータが入っていません。データを入力してください。");
      }

    }
    catch (NullPointerException ex)
    {
      response = Response.getFailure("接続が切れました。");
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
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");
    Response response = null;
    String id = req.getParameter("id");
    try
    {
      String minDate = RecordORM.select(id);
      if (minDate != null)
      {
        response = Response.getSuccess(minDate);
      } else
      {
        response = Response.getFailure("DBにまだデータが入っていません。データを入力してください。");
      }

    }
    catch (NullPointerException ex)
    {
      response = Response.getFailure("データがありません。");
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
}
