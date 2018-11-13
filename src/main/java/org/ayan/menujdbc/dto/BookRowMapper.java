package org.ayan.menujdbc.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setBookid(rs.getLong("bookid"));
		book.setTitle(rs.getString("title"));
		book.setPrice(rs.getDouble("price"));
		book.setVolume(rs.getString("volume"));
		book.setPublishDate(rs.getDate("publishDate").toString());

		return book;
	}

}
