package com.kwon.music.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kwon.music.dao.MusicDAO;
import com.kwon.music.vo.MusicVO;


/**
 * Servlet implementation class musicListServlet
 */
@WebServlet("/MusicListServlet")
public class MusicListServlet extends HttpServlet {
	
	
	String number,title;
	String IP="192.168.120.131";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ddddddddddddddddddddd");
		number = request.getParameter("num");
		title =request.getParameter("title");
		
		boolean flag = sendArduinoMessage(IP, number);
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("success");
		response.setContentType("text/plain;charset=utf-8");
		
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
		
		
	

	boolean sendArduinoMessage(String ip, String data){
		boolean flag = false;
		Socket s = null;
		InputStream is = null;
		OutputStream os;
		System.out.println("ip : " + ip);
		try{
			s = new Socket(ip,8765);			
			is = s.getInputStream();
			os = s.getOutputStream();
			char c = data.charAt(0);
			os.write((byte)c);
			int rData = is.read();
			if(rData != 'E'){
				flag = true;
			}
		}catch(IOException e){
			System.out.println("sendError : " + e);
		}finally{
			if(s != null){
				try{
					s.close();
				}catch(IOException e){}
			}
		}
		return flag;
	}

}
