package lms.business.rulsets;

import javafx.application.Application;
import lms.ui.LoginController;
import lms.ui.RuleSet;

public class LoginRuleSet implements RuleSet{
	LoginController controller = new LoginController();
	@Override
	public void applyRules(Application ob) throws RuleException {
		// TODO Auto-generated method stub
//		LoginForm login = (LoginForm) ob;
		// A. All fields must be nonempty
		if (controller.getUserName().equals("sagar") && controller.getPassword().isEmpty()){
			throw new RuleException("All fields must be nonempty");
		}
	}
	
}


