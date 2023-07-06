package springbook.user.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springbook.user.dao2.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.util.List;

public class UserServiceTx implements UserService {

	UserService userService;
	private PlatformTransactionManager transactionManager;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void upgradeLevels() {

		TransactionStatus status = transactionManager.getTransaction((new DefaultTransactionDefinition()));

		try {

			userService.upgradeLevels();

			transactionManager.commit(status);
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

	public void add(User user) {
		userService.add(user);
	}

}

