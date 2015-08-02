package lms.business.rulsets;

import javafx.scene.control.TextField;

public class AddMemberRuleSet {

	public void applyRule(TextField textId, TextField textStreet, TextField textNumber, TextField textFirstName,
			TextField textEmail, TextField textZip, TextField textState, TextField textCity) throws RuleException{
		//check for the validation
		
		if(textId.getText().trim().isEmpty() ||
				textStreet.getText().trim().isEmpty() ||
				textNumber.getText().trim().isEmpty() ||
				textFirstName.getText().trim().isEmpty() ||
				textEmail.getText().trim().isEmpty() ||
				textZip.getText().trim().isEmpty() ||
				textState.getText().trim().isEmpty() ||
				textCity.getText().trim().isEmpty()) {
			throw new RuleException("Fields can't be empty");
		}
		
	}

	
	
}
