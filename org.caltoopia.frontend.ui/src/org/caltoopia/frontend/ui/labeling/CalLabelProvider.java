
package org.caltoopia.frontend.ui.labeling;

import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstState;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.AstInequality;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;
import com.google.inject.Inject;

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class CalLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	public CalLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}
	
	public String text(AstAction action) {
		AstTag tag = action.getTag();
		if (tag == null) {
			return "(untagged)";
		} else {
			return concatenateTags(action.getTag());
		}
	}

	public String text(AstVariable var) {
		return var.getName() + " - " + doType(var.getType()); 
	}

	public String text(AstPort port) {
		return port.getName() + " - " + doType(port.getType()); 
	}
	
	public String text(AstState state) {
		return state.getName();
	}

	public String text(AstTransition transition) {
		return getText(transition.getSource()) + " --> "
				+ getText(transition.getTarget());
	}
	
	public String text(AstInequality inequality) {
		String result = concatenateTags(inequality.getTags().get(0));
		result += " > ";
		for (int i = 1; i < inequality.getTags().size(); i++) {
			result += concatenateTags(inequality.getTags().get(i));
			if (i < inequality.getTags().size() - 1)
				result += ", ";
		}
		return result;
	}
	
	private String concatenateTags(AstTag tag) {
		String id = tag.getIdentifiers().get(0);
		for (int i = 1; i < tag.getIdentifiers().size(); i++) 
			id = id + "." + tag.getIdentifiers().get(i);
		return id;
	}
	
	private String doType(AstType type) {
		if (type.getBuiltin() != null) {
			return type.getBuiltin();
		} else {
			return type.getName().getName();
		}
	}
}
