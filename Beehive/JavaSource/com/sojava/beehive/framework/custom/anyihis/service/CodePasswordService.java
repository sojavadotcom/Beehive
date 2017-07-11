package com.sojava.beehive.framework.custom.anyihis.service;

import com.sojava.beehive.framework.component.user.bean.AnyihisCodePwd;

import java.util.List;

public interface CodePasswordService {

	List<AnyihisCodePwd> list() throws Exception;
	String getCode(char word) throws Exception;
}