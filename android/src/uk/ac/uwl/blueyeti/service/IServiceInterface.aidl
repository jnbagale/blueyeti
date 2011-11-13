package uk.ac.uwl.blueyeti.service;

import uk.ac.uwl.blueyeti.service.Information;

interface IServiceInterface {

	void startService();
	Information getLastInfo();
	List<Information> getAllInfo();
	

}