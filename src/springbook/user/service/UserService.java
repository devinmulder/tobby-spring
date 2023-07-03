package springbook.user.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springbook.user.dao2.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class UserService {

	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;

	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void upgradeLevels() throws SQLException /*throws Exception*/ {

		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

		TransactionStatus status = transactionManager.getTransaction((new DefaultTransactionDefinition()));

		try {
			List<User> users = userDao.getAll();
			for (User user : users) {
				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			transactionManager.commit(status);
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		switch(currentLevel) {
			case BASIC:	return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
			case GOLD: return false;
//			default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
			default: return false;
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}


	public void add(User user) {
		if (user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}

}

