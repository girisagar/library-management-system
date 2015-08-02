package lms.business.rulsets;

import javafx.scene.control.TextField;

public class AddBookCopyRuleSet {

	public void applyRule(TextField textNumberOfCopy) throws RuleException {
		if(textNumberOfCopy.getText().trim().isEmpty()) {
			throw new RuleException("Input the number of copies.");
		}
		
	}

}
