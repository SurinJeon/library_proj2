package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj2.dao.UserDao;
import library_proj2.dto.User;
import library_proj2.util.JdbcUtil;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance = new UserDaoImpl();

	public static UserDaoImpl getInstance() {
		return instance;
	}

	private UserDaoImpl() {};

	private User getUser(ResultSet rs) throws SQLException {
		int userNo = 0;
		String userName = null;
		Date userBirth = null;
		String account = null;
		String tel = null;
		String phone = null;
		String address = null;

		try {
			userNo = rs.getInt("userno");
		} catch (SQLException e) {
		}

		try {
			userName = rs.getString("username");
		} catch (SQLException e) {
		}

		try {
			userBirth = rs.getDate("userbirth");
		} catch (SQLException e) {
		}

		try {
			account = rs.getString("account");
		} catch (SQLException e) {
		}

		try {
			tel = rs.getString("tel");
		} catch (SQLException e) {
		}

		try {
			phone = rs.getString("phone");
		} catch (SQLException e) {
		}

		try {
			address = rs.getString("address");
		} catch (SQLException e) {
		}

		return new User(userNo, userName, userBirth, account, tel, phone, address);
	}

	@Override
	public List<User> selectUserByAll() {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				List<User> list = new ArrayList<User>();
				do {
					list.add(getUser(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByNo(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where userno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByName(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where username like ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%" + user.getUserName() + "%");

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByPhone(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where phone like ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%" + user.getPhone() + "%");

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByAccount(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where account like ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%" + user.getAccount() + "%");

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertUser(User user) {
		String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());
			pstmt.setString(2, user.getUserName());
			pstmt.setTimestamp(3, new Timestamp(user.getUserBirth().getTime()));
			pstmt.setString(4, user.getAccount());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getAddress());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(User user) {
		String sql = "update user set userno = ?, username = ?, userbirth = ?, account = ?, tel = ?, phone = ?, address = ?"
				+ " where userno = ?";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());
			pstmt.setString(2, user.getUserName());
			pstmt.setTimestamp(3, new Timestamp(user.getUserBirth().getTime()));
			pstmt.setString(4, user.getAccount());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getAddress());
			pstmt.setInt(8, user.getUserNo());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(User user) {
		String sql = "delete from user where userno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int nextUserNo() {
		String sql = "select max(userno) from user";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int userCount() {
		String sql = "select count(userno) from user";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				int res = rs.getInt(1);
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
