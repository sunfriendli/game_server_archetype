#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Servlet implementation class CoreServlet
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = LoggerFactory
			.getLogger(CoreServlet.class);
    /**
     * @see HttpServlet${symbol_pound}HttpServlet()
     */
    public CoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		logger.info("servlet service");
	}

	@Override
	public void destroy() {
		logger.info("CoreServelet destroy");
	}

	@Override
	public void init() throws ServletException {
		logger.info("CoreServelet init");
		GameInit.init();// 初始化登录服务器
	}


}
