package com.cms.app.contollers.admin;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cms.app.dto.Response;
import com.cms.app.exception.CMSException;
import com.cms.app.service.PageService;
import com.cms.app.service.TempletService;
import com.cms.model.Host;
import com.cms.model.Page;
import com.cms.model.Templet;
import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping(value = "OM", produces = MediaType.APPLICATION_JSON_VALUE)
public class LandingPageController {

	private final static Logger LOGGER = LoggerFactory.getLogger(LandingPageController.class);

	@Autowired
	@Qualifier("hostServiceImpl")
	private com.cms.app.service.HostService hostService;

	@Autowired
	@Qualifier("templetServiceImpl")
	private TempletService templetService;

	@Autowired
	@Qualifier("pageServiceImpl")
	private PageService pageService;

	@RequestMapping(value = "{hostName}/{appName}/{indexPageName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Page>>> getIndexPage(@PathVariable("hostName") String hostName,
			@PathVariable("appName") String appName, @PathVariable("indexPageName") String indexPageName) {

		List<Page> pages = new ArrayList<Page>();
		try {
			Host host = hostService.findByName(URLDecoder.decode(hostName, "UTF-8"));
			LOGGER.info("Host filter successfully");
			if (host != null) {
				pages = pageService.findByHostId(host.getId());
				LOGGER.info("pages Filter successfully filter successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while filter hostName and templetName :" + hostName + "," + appName,
					e);
		}
		return ResponseEntity
				.ok(new Response<List<Page>>(HttpStatus.OK.value(), "Pages fetch successfully", pages));
	}
	

}
