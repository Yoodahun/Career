package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Emp;
import model.Response;
import net.arnx.jsonic.JSON;
import orm.EmpORM1;

public class PasswordServlet extends HttpServlet
{
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("UTF-8");

    Response response = null;
    Emp insert = null;

    String id = req.getParameter("id");
    String password = req.getParameter("password");

    try
    {
      insert = EmpORM1.select(id, password);
    }
    catch (Exception ex)
    {
      response = Response.getFailure(ex);
      // TODO 自動生成された catch ブロック
      ex.printStackTrace();
    }
    if (insert instanceof Emp)
    {
      doPut(req, resp);
      return;
    } else
    {
      response = Response.getFailure("パスワード変更に失敗しました。IDやパスワードを確認してください。");

    }
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("application/json");
    JSON.encode(response, resp.getWriter());
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    Response response = null;
    String id = req.getParameter("id");
    String newPassword = req.getParameter("newPassword");

    try
    {
      boolean result = EmpORM1.update(id, newPassword);
      if (result)
      {
        response = Response.getSuccess("パスワード変更成功。");
      } else
      {
        response = Response.getFailure("パスワード更新に失敗しました。 管理者にお問い合わせください。");
      }

    }
    catch (SQLException ex)
    {
      if (ex.getMessage().contains("ORA-12899") || ex.getMessage().contains("大きすぎ"))
      {
        response = Response.getFailure("入力値が長いです。最大20文字です。");
      } else
      {
        response = Response.getFailure(ex);
        ex.printStackTrace();
      }

      // TODO 自動生成された catch ブロック
      ex.printStackTrace();
    }
    catch (Exception ex)
    {
      response = Response.getFailure(ex);
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
