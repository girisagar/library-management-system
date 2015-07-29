package lms.business.rulsets;

import java.awt.Component;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import lms.ui.InterfaceController;
import lms.ui.LoginForm;
import lms.ui.RuleSet;

public class LoginRuleSet implements RuleSet{
	InterfaceController controller = new InterfaceController();
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


