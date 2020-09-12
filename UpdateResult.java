package jums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hayashi-s
 */

	@WebServlet("/UpdateResult")
	public class UpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try{
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            UserDataBeans udb =new UserDataBeans();
            udb.setName(request.getParameter("name"));
            udb.setYear(request.getParameter("year"));
            udb.setMonth(request.getParameter("month"));
            udb.setDay(request.getParameter("day"));
            udb.setTel(request.getParameter("tel"));
            udb.setType(request.getParameter("type"));
            udb.setComment(request.getParameter("comment"));

            UserDataDTO searchData=new UserDataDTO();
            udb.UD2DTOMapping(searchData);
            //DB用のJavaBeansにデータ処理検索　search()はDBにSQL文で指示が示してある。

            //UserDataDTO　insertData=
            UserDataDAO.getInstance().update(searchData);

    //結果画面での表示用に入力パラメータ―をリクエストパラメータとして保持
            request.setAttribute("resultData", udb);

            request.getRequestDispatcher("/updateresult.jsp").forward(request, response);

        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
