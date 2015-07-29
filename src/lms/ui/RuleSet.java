package lms.ui;

//import java.awt.Component;

import javafx.application.Application;
import lms.business.rulsets.RuleException;

public interface RuleSet {
	public void applyRules(Application ob) throws RuleException;
}