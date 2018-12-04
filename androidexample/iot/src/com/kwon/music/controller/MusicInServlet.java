package com.kwon.music.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kwon.music.dao.MusicDAO;
import com.kwon.music.vo.MusicVO;

/**
 * Servlet implementation class TempInServlet
 */
@WebServlet("/MusicInServlet")
public class MusicInServlet extends HttpServlet {

	int cnt = 0;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		cnt++;
		
		String number = request.getParameter("num");
		String title = request.getParameter("title");
		
		System.out.println("number: "  + number);
		System.out.println("title : "  + title);
		
		MusicDAO dao = new MusicDAO();
		MusicVO vo = new MusicVO();
		vo.setNum(number);
		vo.setTitle(title);
		dao.insertMusic(vo);
		out.println("success");
	}
	
	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
