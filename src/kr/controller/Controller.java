package kr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public String handRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
