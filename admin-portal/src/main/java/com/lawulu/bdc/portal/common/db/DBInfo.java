package com.lawulu.bdc.portal.common.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DBInfo {

	String dbuser;
	String dbpwd;
	
	String sid;
	
	String serverip;
	String serverport;
	String deptid;
	String pdeptid;
	String deptname;
	String depttype;

	
	
}
