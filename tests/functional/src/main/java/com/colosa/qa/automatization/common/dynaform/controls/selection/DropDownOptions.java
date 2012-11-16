package com.colosa.qa.automatization.common.dynaform.controls.selection;

import com.colosa.qa.automatization.common.dynaform.controls.ControlOptions;
import com.colosa.qa.automatization.common.dynaform.controls.selection.DropdownOption;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class DropDownOptions extends ControlOptions{

	private ArrayList<DropDownOption> options = new ArrayList();

	public void addOption(String label, String value){
		//options.add();
	}
/*
	public Map<String, String> getOptions(){
		return this.options;
	}*/

	@Override
	public void fillForm() throws Exception{
		super.fillForm();
		for(String[] x:this.opts)
			for(String y:x)
				System.out.println(y);
	}
	
}