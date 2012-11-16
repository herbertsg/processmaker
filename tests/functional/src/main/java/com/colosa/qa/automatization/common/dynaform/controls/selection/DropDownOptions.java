package com.colosa.qa.automatization.common.dynaform.controls.selection;

import com.colosa.qa.automatization.common.dynaform.controls.ControlOptions;
import com.colosa.qa.automatization.common.dynaform.controls.selection.DropDownOption;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class DropDownOptions extends ControlOptions{

	private ArrayList<DropDownOption> options = new ArrayList<DropDownOption>();

	public void addOption(String label, String value){
		options.add(new DropDownOption(label, value));
	}

	@Override
	public void fillForm() throws Exception{
		super.fillForm();
		int i = 1;
		String criteria = ConfigurationSettings-getInstance().getSetting("dynaformDesigner.webElement.dropdownModal.gridElementLocator");
		for(DropDownOption opt:this.options)
		{
			System.out.println(opt.getValue());
			
		}
	}
	
}