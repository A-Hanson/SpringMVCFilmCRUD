package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryItem;

@Component
public class FilmDAOjdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String USER = "student";
	private static final String PWORD = "student";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWORD);
			String sql = "SELECT * " + "FROM film " + "JOIN language ON film.language_id = language.id  "
					+ "LEFT JOIN film_category ON film.id = film_category.film_id "
					+ "LEFT JOIN category ON film_category.category_id = category.id " + "WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"), rs.getString("name"),
						rs.getString("category.name"));
				film.setActors(findActorsByFilmId(filmId));
				film.setInventory(getFilmInventory(filmId));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWORD);
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWORD);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name "
					+ "FROM film JOIN film_actor ON film.id = film_actor.film_id "
					+ "JOIN actor ON film_actor.actor_id = actor.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			// use while loop for populating a list.
			while (rs.next()) {
				Actor actor = new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
						rs.getString("actor.last_name"));
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyWord) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWORD);
			String sql = "SELECT * FROM film WHERE (title LIKE ?) OR (description LIKE ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				Film film = findFilmById(id);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return films;
	}

	private List<InventoryItem> getFilmInventory(int filmId) {
		List<InventoryItem> inventory = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWORD);
			String sql = "SELECT * FROM inventory_item  WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				InventoryItem item = new InventoryItem(rs.getInt("id"), rs.getInt("film_id"), rs.getInt("store_id"),
						rs.getString("media_condition"), rs.getTimestamp("last_update").toLocalDateTime());
				inventory.add(item);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return inventory;
	}

	@Override
	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			film.setLanguageId(1);
			conn = DriverManager.getConnection(URL, USER, PWORD);
			conn.setAutoCommit(false);

			String sql = "INSERT INTO film (title, language_id) " + " VALUES(?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setInt(2, film.getLanguageId());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
				}
			} else {
				film = null;
			}

			conn.commit();
			System.out.println("Added to database: " + film);
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);
		}
		return film;
	}

	@Override
	public boolean deleteFilm(int filmid) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWORD);
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM film WHERE film.id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			System.out.println("******************" + filmid);
			stmt.setInt(1, filmid);
			System.out.println("******************" + filmid);
			
			int updateCount = stmt.executeUpdate();
			
			System.out.println(updateCount+ "*******************");
			
			conn.commit();
		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}

		return true;
	}

	@Override
	public Film updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWORD);
			conn.setAutoCommit(false);

			System.out.println("************************" + film.getId());
			
			String sql = "UPDATE film "
					+ "SET film.title=?, film.description=? " 
					+ "WHERE film.id=?";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getId());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				System.out.println("Film was updated successfully.");
				System.out.println(film);
			} else {
				System.out.println("Film was unable to be updated.");
				film = null;
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);
		}
		return film;
	}
	
}
