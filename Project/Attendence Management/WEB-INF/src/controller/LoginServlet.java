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
import orm.EmpORM1;

public class LoginServlet extends HttpServlet
{
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    Response r = Response.getFailure("セッションが切れました。再ログインしてください。");
    HttpSession session = req.getSession(false);
    try
    {
      req.setCharacterEncoding("utf-8");
      String loginUser = req.getParameter("loginuser");
      if (session == null)
      {
        r = Response.getFailure("正しくない接続です。ログイン画面に戻ります。");
        //        resp.sendRedirect("../index.html?sc=701");
        return;
      }
      else if (loginUser.equals("me")) //合っているかどうか確認
      {
        //        HttpSession session = req.getSession(false);
        Emp e = (Emp) session.getAttribute("loginuser"); //現在のユーザー情報
        if (e != null)
        {
          r = Response.getSuccess(e);
        }

      }
      else if (loginUser.equals("out"))//logout
      {
        session.invalidate();
        r = Response.getSuccess();
        resp.sendRedirect("../index.html?sc=701");
        return;
      }

    }
    catch (Exception ex)
    {
      r = Response.getFailure("正しくない接続です。ログアウトします。");
      resp.sendRedirect("../index.html?sc=701");
      ex.printStackTrace();

    }
    finally
    {
      resp.setCharacterEncoding("utf-8");
      resp.setContentType("application/json");
      JSON.encode(r, resp.getWriter());
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    req.setCharacterEncoding("utf-8");
    String id = req.getParameter("id");
    String password = req.getParameter("password");
    String url = "../index.html?sc=401"; //ログインが失敗したとき
    try
    {
      Emp e = EmpORM1.select(id, password); //セッションに預ける
      HttpSession hs = req.getSession(); //新しく作る
      hs.setAttribute("loginuser", e);
      url = "../initial.html";

    }
    catch (Exception ex)
    {

      ex.printStackTrace();
    }
    finally
    {
      resp.sendRedirect(url); //Redirect.
    }

  }
}
