package com.yc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
@WebFilter(filterName="encodingServlet",urlPatterns={"/*"},
initParams={@WebInitParam(name="charset",value="UTF-8")})
public class EncodingFilter implements Filter {
     FilterConfig config;
     String charset;
	@Override
	public void destroy() {
		//System.out.println("destroy------");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("doFilter------");
		//设置字符编码集
		req.setCharacterEncoding(charset);
		resp.setCharacterEncoding(charset);
		//允许通过
		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
		charset=config.getInitParameter("charset");
		//System.out.println("init------");
	}

}
