package com.kwon.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kwon.music.vo.MusicVO;

public class MusicDAO {
	private static final String INSERT_SQL = "insert into tbl_music values(?, ? );";
	private static final String LIST_SQL = "select * from tbl_music order by num desc limit 0, 10;";
	
	public void insertMusic(MusicVO vo){

		try(Connection conn = JDBCUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
			stmt.setString(1, vo.getNum());
			stmt.setString(2, vo.getTitle());
			int cnt = stmt.executeUpdate();
			System.out.println(cnt == 1 ? "success" : "fail");
		}catch(SQLException e){
			System.out.println("insert error : " + e);
		}		
	}
}