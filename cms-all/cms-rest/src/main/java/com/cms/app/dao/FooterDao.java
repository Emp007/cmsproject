package com.cms.app.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Footer;
import com.cms.model.Templet;

public interface FooterDao extends MongoRepository<Footer, Long> {

	@Query("{'host.$id' : ?0}")
	 public Footer findByFooterId(long id);
	
	 @Query("{ 'templetName' : ?0 }")
	 public Footer findByName(String name);
	 
	 public List<Footer> findByTempletId(long id);
	 
	 public List<Footer> findByHostId(long id);
	 
	 public long countByHostId(long id);
	 
	 @Query("{'footerName' : ?0 , 'hostId' : ?1}")
	 public Footer getAllFooterByFooterNameAndhostId(String footerName, long hostId);
}
