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

public interface UserService {


	public void upgradeLevels();

	public void add(User user);

}

