/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.67
 * Generated at: 2017-07-27 08:26:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class user_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/addUser.action\" method=\"post\">\r\n");
      out.write("    <table width=\"80%\" height=\"200px\" border=\"1px\" align=\"center\">\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td colspan=\"2\">\r\n");
      out.write("          <h3>用户注册</h3>\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>用户名:</td>\r\n");
      out.write("        <td><input type=\"text\" name=\"username\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>密码：</td>\r\n");
      out.write("        <td><input type=\"password\" name=\"password\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>真实姓名：</td>\r\n");
      out.write("        <td><input type=\"text\" name=\"name\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("       <tr align=\"center\">\r\n");
      out.write("        <td>电话：</td>\r\n");
      out.write("        <td><input type=\"tel\" name=\"tel\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>家庭地址：</td>\r\n");
      out.write("        <td><input type=\"text\" name=\"userInfo.addr\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>年龄：</td>\r\n");
      out.write("        <td><input type=\"text\" name=\"userInfo.Age\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>生日：</td>\r\n");
      out.write("        <td><input type=\"text\" name=\"userInfo.birthday\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>爱好：</td>\r\n");
      out.write("        <td><input type=\"checkbox\" name=\"hobby\" value=\"羽毛球\">羽毛球\r\n");
      out.write("        <input type=\"checkbox\" name=\"hobby\" value=\"吃鸡\">吃鸡\r\n");
      out.write("        <input type=\"checkbox\" name=\"hobby\" value=\"骑马\">骑马\r\n");
      out.write("        <input type=\"checkbox\" name=\"hobby\" value=\"唱歌\">唱歌\r\n");
      out.write("        <input type=\"checkbox\" name=\"hobby\" value=\"听音乐\">听音乐</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      \r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td>状态：</td>\r\n");
      out.write("        <td><input type=\"radio\" name=\"state\" value=\"成年人\">成年人\r\n");
      out.write("        \t<input type=\"radio\" name=\"state\" value=\"未成年人\">未成年人\r\n");
      out.write("        </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr align=\"center\">\r\n");
      out.write("        <td></td>\r\n");
      out.write("        <td><input type=\"submit\" name=\"注册\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("  </form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
