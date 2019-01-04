package com.biz.oracle.exec;

import com.biz.oracle.dao.SubjectDao;

public class SubjectExec {

	public static void main(String[] args) {
		SubjectDao sd= new SubjectDao();
		
		sd.subConn();
		sd.subSelect();
	}
}
