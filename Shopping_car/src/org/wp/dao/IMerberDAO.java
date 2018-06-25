package org.wp.dao;

import org.wp.vo.Member;

public interface IMerberDAO extends IDAO<Member, String>{
	public boolean findLogin(Member vo) throws Exception;
	public void doUpdateLastdate(String id) throws Exception;

}
