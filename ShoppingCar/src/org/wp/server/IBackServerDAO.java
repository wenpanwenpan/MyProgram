package org.wp.server;
import java.util.Map;


public interface IBackServerDAO {
	public Map<String , Object> findEmpUpdatePre(Integer id) throws Exception;
	public Map<String , Object> findEmpInsertPre() throws Exception;
	
}
