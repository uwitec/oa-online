package com.fhi.index.webservice;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.fhi.user.vo.FhiUser;
@WebService
@Resource
public interface userWebServiceTestIn {
	public FhiUser sayHello(String userName,String passWord);
	public String changePassword(String userId,String passWord,String OldPassword);
	public String queryPermission(FhiUser fu);
}
