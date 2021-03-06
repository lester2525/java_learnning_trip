package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.JsonUtil;
import dao.CommentDao;
import dao.CommentDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import models.Comment;
import models.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ActionServlet
 */
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		switch (type) {
		case "login":
			dologin(request, response);
			break;
		case "newComment":
			doAddComment(request,response);
			break;
			
		case "logout":
			session.removeAttribute("userName");
			RequestDispatcher dispatcher =  request.getRequestDispatcher("/index.jsp");
	        dispatcher.forward(request, response);
			break;
		case "goPage":
			showCommentByPage(request, response);
			break;
		case "reg":
			doReg(request,response);
		default:
			
			break;
		}

	}

	private void doReg(HttpServletRequest request, HttpServletResponse response) {
		System.out.print("reg");
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String inputCode = request.getParameter("inputCode");
		String verifyCode = (String)session.getAttribute("validateCode");

		UserDao userDao = new UserDaoImpl();
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setNickname(nickName);
		user.setAvatar(" ");
		System.out.println(inputCode);
		if(inputCode.toUpperCase().equals(verifyCode)){
			boolean t = userDao.Add(user);
			if(t){

				try {
					response.setContentType("text/html");
					response.setCharacterEncoding("utf-8");
					response.getWriter().print("<script>alert('注册成功');window.location.href='index.jsp';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				try {
					response.getWriter().print("<script>alert('注册失败');history.go(-1)；</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().print("<script>alert('验证码错误');history.go(-1)；</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}

	private void showCommentByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String pageStr = request.getParameter("page");
//		System.out.println(pageStr);
		CommentDao commentDao = new CommentDaoImpl();
		int page =1;
		int pageSize = 5;
		int totalPage =0;
		int u = commentDao.getAllComments().size()%pageSize;
		if(u == 0){
			totalPage=(int) commentDao.getAllComments().size()/pageSize;
		}else {
			totalPage=(int) commentDao.getAllComments().size()/pageSize+1;
		}
		if(pageStr != null){
			page += Integer.parseInt(pageStr);
		}else {
			page = 1;
		}
		if(page <1){
			page = 1;
		}
		if(page > totalPage){
			page = totalPage;
		}
		
		int start = (page -1)*pageSize;

		if(start <0){
			start = 0;
		}
		List<Comment> commentbypagelist = commentDao.getAllCommentsByPage(start,pageSize);
		for (Comment comment : commentbypagelist) {
			System.out.println(comment.getId());
		}
		JSONArray jsonComList =JSONArray.fromObject(commentbypagelist);

		session.setAttribute("currentPage", page);
		session.setAttribute("totalPage", totalPage);
		System.out.println(jsonComList);
        session.setAttribute("clist", jsonComList);
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/comments.jsp");
        dispatcher.forward(request, response);
	}
	private void doAddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String content = request.getParameter("content");
		String inputCode = request.getParameter("inputCode");  
	    String verifyCode = (String)session.getAttribute("validateCode");
	    
		CommentDao commentDao = new CommentDaoImpl();
		UserDao userDao = new UserDaoImpl();
		String username = (String) session.getAttribute("userName");
		if(username !=null){
			if(verifyCode.equals(inputCode.toUpperCase())){
				int userid = userDao.findUserByName(username).getId();
				boolean i = commentDao.newComment(content, userid);
				if(i){
					showCommentByPage(request,response);
						
				}else {
//					System.out.println("插入失败");
					try {
						response.setContentType("text/html");
						response.setCharacterEncoding("utf-8");
						response.getWriter().print("<script>alert('评论失败');history.go(-1)；</script>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
//				System.out.println("插入失败");
				try {
					response.setContentType("text/html");
					response.setCharacterEncoding("utf-8");
					response.getWriter().print("<script>alert('验证码错误');history.go(-1);</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
//			System.out.println("请先登陆");
			try {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("<script>alert('请先登陆');history.go(-1);</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void dologin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		CommentDao commentDao = new CommentDaoImpl();
		List<Comment> commList = new ArrayList<Comment>();
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String inputCode = request.getParameter("inputCode");  
	    String verifyCode = (String)session.getAttribute("validateCode");
//	    System.out.println("inputcode:"+inputCode+"\n verifycode:"+verifyCode);
//		System.out.println("name:"+username+"\n password:"+password);
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findUserByName(username);
		if(verifyCode.equals(inputCode.toUpperCase())){
			if(user !=null){
				if(password.equals(user.getPassword())){
					
//					System.out.println("登录成功");
					/*response.setContentType("text/html");
					PrintWriter out = response.getWriter();

			        out.println("<html><head><title>Login Result</title></head>");
			        out.println("<body> username: " + username + "<br>");
			        out.println("password: " + password + "</body></html>");

			        out.flush();*/
					session.setAttribute("userName", username);
					showCommentByPage(request, response);
			        /*commList = commentDao.getAllCommentsByPage(0, 5);
			        
//			        String jsonComList = JsonUtil.javaList2JsonList(commList);
			        JSONArray jsonComList =JSONArray.fromObject(commList);
//			        System.out.println(jsonComList.toString());
			        session.setAttribute("clist", jsonComList);
			        RequestDispatcher dispatcher =  request.getRequestDispatcher("/comments.jsp");
			        dispatcher.forward(request, response);*/
			        
				}else {
//					System.out.println("密码错误");
					response.setContentType("text/html");
					response.setCharacterEncoding("utf-8");
					response.getWriter().print("<script>alert('密码错误');history.go(-1);</script>");
				}
			}else {
//				System.out.println("用户名不存在");
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("<script>alert('用户不存在');history.go(-1);</script>");
			}
		}else {
//			System.out.println("验证码错误");
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("<script>alert('验证码错误');history.go(-1);</script>");
		}
	}
	
}
