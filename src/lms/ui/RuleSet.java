package lms.ui;

import java.awt.Component;

import lms.business.rulsets.RuleException;

public interface RuleSet {
	public void applyRules(Component ob) throws RuleException;
}