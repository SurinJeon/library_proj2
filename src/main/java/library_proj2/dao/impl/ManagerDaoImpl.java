package library_proj2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library_proj2.dao.ManagerDao;
import library_proj2.dto.Manager;
import library_proj2.util.JdbcUtil;

public class ManagerDaoImpl implements ManagerDao {
	private static final ManagerDaoImpl instance = new ManagerDaoImpl();

	public static ManagerDaoImpl getInstance() {
		return instance;
	}

	private ManagerDaoImpl() {};
	
	private Manager getManager(ResultSet rs) throws SQLException {
		String mngAccount = rs.getString("mngaccount");
		String passwd = rs.getString("passwd");
		return new Manager(mngAccount, passwd);
	}

	@Override
	public Manager selectManagerById(Manager manager) {
		String sql = "select mngaccount, passwd from manager where mngaccount = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, manager.getMngAccount());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getManager(rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
