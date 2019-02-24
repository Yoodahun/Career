package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Entity;
import model.Response;
import net.arnx.jsonic.JSON;
import orm.WorkORM;

public class WorkServlet extends HttpServlet
{
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");
    List<Entity> workTypes = null;
    Response response = null;

    try
    {
      workTypes = WorkORM.select();
      response = Response.getSuccess(workTypes);

    }
    catch (Exception ex)
    {
      response = Response.getFailure(ex);
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

    if (req.getParameter("method").equals("put"))
    {
      doPut(req, resp);
      return;
    } else if (req.getParameter("method").equals("delete"))
    {
      doDelete(req, resp);
      return;
    } else
    {
      String name = req.getParameter("workName");
      try
      {
        Entity insert = WorkORM.insert(name);
        response = Response.getSuccess(insert);
      }
      catch (SQLException ex)
      {
        if (ex.getMessage().contains("ORA-12899") || ex.getMessage().contains("大きすぎ"))
        {
          response = Response.getFailure("入力値が長いです。");
        } else if (ex.getMessage().contains("ORA-01400") || ex.getMessage().contains("NULL"))
        {
          response = Response.getFailure("勤務種類は必ず入力してください。");
        }
        else
        {
          response = Response.getFailure(ex);
        }

        // TODO 自動生成された catch ブロック
        ex.printStackTrace();
      }
      catch (Exception ex)
      {
        response = Response.getFailure(ex);
        // TODO 自動生成された catch ブロック
        ex.printStackTrace();
      }
      finally
      {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        JSON.encode(response, resp.getWriter());
      }

    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    Response response = null;
    String id = req.getParameter("workId");
    String name = req.getParameter("workName");

    try
    {
      boolean result = WorkORM.update(id, name);
      if (result)
      {
        Entity update = WorkORM.select(Integer.valueOf(id));
        response = Response.getSuccess(update);
      }
    }
    catch (SQLException ex)
    {
      if (ex.getMessage().contains("ORA-12899") || ex.getMessage().contains("大きすぎ"))
      {
        response = Response.getFailure("入力値が長いです。最大20文字です。");
      } else if (ex.getMessage().contains("ORA-01407") || ex.getMessage().contains("NULL"))
      {
        response = Response.getFailure("勤務種類は必ず入力してください。");
      }
      else
      {
        response = Response.getFailure(ex);
      }

      // TODO 自動生成された catch ブロック
      ex.printStackTrace();
    }
    catch (Exception ex)
    {
      response = Response.getFailure(ex);
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
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    Response response = null;
    String id = req.getParameter("workId");

    try
    {
      boolean delete = WorkORM.delete(id);
      if (delete)
      {
        response = Response.getSuccess();
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
