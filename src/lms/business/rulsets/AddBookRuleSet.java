package lms.business.rulsets;

import java.util.List;

import javafx.scene.control.TextField;
import lms.business.Author;

public class AddBookRuleSet {

	public void applyRule(TextField textIsbn, TextField textTitle, List<Author> selectedAuthors) throws RuleException {
		if(textIsbn.getText().trim().isEmpty()) {
			throw new RuleException("ISBN can't be empty");
		}

		if(!(textIsbn.getText().matches("[0-9][0-9-]*[0-9]"))) {
			throw new RuleException("Invalid ISBN number");
		}

		if(textTitle.getText().trim().isEmpty()) {
			throw new RuleException("Book title can't be empty");
		}
		
		
		if(selectedAuthors.size() == 0) {
			throw new RuleException("Select author");
		}
		
	}

}
