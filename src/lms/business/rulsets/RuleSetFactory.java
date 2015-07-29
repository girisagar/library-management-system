package lms.business.rulsets;

import java.awt.Component;
import java.util.HashMap;

import javafx.application.Application;
import lms.ui.LoginForm;
import lms.ui.RuleSet;

final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Application>, RuleSet> map = new HashMap<>();
	static {
// add your window's ruleset here.
		map.put(LoginForm.class, new LoginRuleSet());
//		map.put(ProfileWindow.class, new CustomerProfileRuleSet());
		
		
	}
	
	public static RuleSet getRuleSet(Application c) {
		Class<? extends Application> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
