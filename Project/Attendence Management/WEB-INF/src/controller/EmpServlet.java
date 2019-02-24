package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Emp;
import model.Response;
import net.arnx.jsonic.JSON;
import orm.EmpORM1;

public class EmpServlet extends HttpServlet
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");

    Response response = null;
    List<Emp> emps = null;
    HttpSession session = req.getSession(false);
    Emp e = (Emp) session.getAttribute("loginuser");

    try
    {
      if (req.getParameter("emplist") != null)
      {
        switch (e.getPositionId())
          {
            case 2: //部長
              emps = EmpORM1.select(e.getPositionId(), e.getDepartId());
              break;
            case 3://課長
              emps = EmpORM1.select(
                  e.getPositionId(),
                  e.getDepartId(),
                  e.getSectionId()
                  );
              break;
            case 4: //一般
              Emp emp = EmpORM1.select(e.getId());
              emps = new ArrayList<Emp>();
              emps.add(emp);
              break;

            default: //役員
              emps = EmpORM1.select();
              break;
          }
      }
      else
      {
        emps = EmpORM1.select();
      }

      response = Response.getSuccess(emps);
    }
    catch (NullPointerException ex)
    {

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
    if (req.getParameter("method").equals("put"))
    {
      doPut(req, resp);
      return;
    } else if (req.getParameter("method").equals("delete"))
    {
      doDelete(req, resp);
      return;
    }
    Response response = null;
    Emp emp = null;

    String name = req.getParameter("name");
    String password = req.getParameter("password");
    String depart = req.getParameter("depart");
    String section = req.getParameter("section");
    String position = req.getParameter("position");

    try
    {
      emp = EmpORM1.insert(name, password, depart, section, position);
      response = Response.getSuccess(emp);
    }
    catch (SQLException ex)
    {
      if (ex.getMessage().contains("ORA-12899") || ex.getMessage().contains("大きすぎ"))
      {
        response = Response.getFailure("入力値が長いです。最大20文字です。");
      } else if (ex.getMessage().contains("ORA-01400") || ex.getMessage().contains("NULL"))
      {
        response = Response.getFailure("名前やパスワードは必ず入力してください。");
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
    String id = req.getParameter("id");
    String name = req.getParameter("name");
    String depart = req.getParameter("depart");
    String section = req.getParameter("section");
    String position = req.getParameter("position");

    try
    {
      boolean insert = EmpORM1.update(id, name, depart, section, position);
      if (insert)
      {
        Emp emp = EmpORM1.select(id);
        response = Response.getSuccess(emp);

      } else
      {
        response = Response.getFailure("変更に失敗しました。管理者にお問い合わせください。");
      }
    }
    catch (SQLException ex)
    {
      if (ex.getMessage().contains("ORA-12899") || ex.getMessage().contains("大きすぎ"))
      {
        response = Response.getFailure("入力値が長いです。最大20文字です。");
      } else if (ex.getMessage().contains("ORA-01407") || ex.getMessage().contains("NULL"))
      {
        response = Response.getFailure("名前は必ず入力してください。");
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
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    Response response = null;
    String id = req.getParameter("id");

    try
    {
      boolean delete = EmpORM1.delete(id);
      if (delete)
      {
        response = Response.getSuccess(delete);
      } else
      {
        response = Response.getFailure("削除に失敗しました。管理者にお問い合わせください。");
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
