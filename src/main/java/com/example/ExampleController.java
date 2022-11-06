package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record ExampleController(DbProperties dbProperties) {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");

	@GetMapping("/")
	public Object get() throws SQLException {
		String name = "-";
		LocalDateTime dateTime = LocalDateTime.now();
		try (Connection con = DriverManager.getConnection(
				dbProperties.getUrl(),
				dbProperties.getUsername(),
				dbProperties.getPassword());
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select name from demo where id = 1")) {
			if (rs.next()) {
				name = rs.getString(1);
			}
		}
		LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
		map.put("name", name);
		map.put("dateTime", dateTime.format(dateTimeFormatter));
		return map;
	}
}
