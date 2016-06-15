package github.kcs.board.servlet.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import github.kcs.board.BoardContext;
import github.kcs.board.dao.PostDao;
import github.kcs.board.vo.CodeVO;

/**
 * Servlet Filter implementation class CodeSeter
 */
@WebFilter("/*")
public class CodeSeter implements Filter {

    /**
     * Default constructor. 
     */
    public CodeSeter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
	    HttpServletRequest req = (HttpServletRequest) request;
	    
	    ServletContext context = req.getServletContext();
	    BoardContext btx = (BoardContext) context.getAttribute("BOARD_CTX");
	    PostDao dao = btx.getPostDao();
	    List<CodeVO> codeList = dao.findAllCategory() ; // ????????? ;
	    req.setAttribute("codes", codeList);
	    
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
