package com.biz.oracle.exec;

import com.biz.oracle.dao.IoListDao;

public class IoExec01 {

	public static void main(String[] args) {
		/*
		 * dao class를 사용하기 위해 객체로 생성
		 */
		IoListDao dao = new IoListDao();
		/*
		 * db연결
		 */
		dao.dbConnection();
		/*
		 * 데이터를 읽어서 console에 표시
		 */
		dao.selectIoList();
	}
}
