package com.sojava.beehive.framework.component.medicalimaging.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sojava.beehive.framework.ActionSupport;
import com.sojava.beehive.framework.component.medicalimaging.service.MiExecutedService;

import javax.annotation.Resource;

@Controller("MedicalImaging/Entry")
@Scope("prototype")
@Namespace("/MedicalImaging")
public class Entry extends ActionSupport {
	private static final long serialVersionUID = 8469604168969899250L;

	@Resource private MiExecutedService miExecutedService;
	private String action;

	@Action(value = "Entry.*", results = {
		@Result(name = "index", location = "index.jsp"),
		@Result(name = "import", location = "import.jsp"),
		@Result(name = "merit", location = "merit.jsp")
	})
	@Override
	public String input() throws Exception {
		super.execute();
		String actionName = this.getActionContext().getName();
		if (actionName.split("\\Q.\\E").length > 1) actionName = actionName.split("\\Q.\\E")[1];
		else actionName = "index";

		if (actionName.equalsIgnoreCase("query")) {
			return null;
		} else {
			return actionName;
		}
	}

	public MiExecutedService getMiExecutedService() {
		return miExecutedService;
	}

	public void setMiExecutedService(MiExecutedService miExecutedService) {
		this.miExecutedService = miExecutedService;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
