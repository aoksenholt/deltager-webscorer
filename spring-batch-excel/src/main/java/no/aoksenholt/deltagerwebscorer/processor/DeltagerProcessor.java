package no.aoksenholt.deltagerwebscorer.processor;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import no.aoksenholt.deltagerwebscorer.model.Deltager;
import no.aoksenholt.deltagerwebscorer.model.Webscorer;

public class DeltagerProcessor implements ItemProcessor<Deltager, Webscorer> {
    Log log = LogFactory.getLog(getClass());

    @Override
    public Webscorer process(Deltager deltager) throws Exception {
	Webscorer rv = new Webscorer();
	rv.setFirstname(deltager.getFornavn());
	rv.setLastname(deltager.getEtternavn());
	rv.setTeamname(deltager.getKlubb());
	rv.setGender(getGender(deltager.getKlasse()));
	rv.setAge(getAge(deltager));
	rv.setDistance(deltager.getPameldingTil());
	rv.setCategory(deltager.getKlasse());
	rv.setInfo1(deltager.getTskjorte());
	rv.setInfo2(deltager.getKondis());

	return rv;
    }

    private long getAge(Deltager deltager) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());

	try {
	    long ifodselsar = Long.parseLong(deltager.getFodselsar());
	    long age = cal.get(Calendar.YEAR) - ifodselsar;
	    return age > 0 ? age : 0;
	} catch (NumberFormatException e) {
	    log.error("Feil format på fødselsår " + deltager.getFodselsar() + " (" + deltager.getEtternavn() + ", " + deltager.getFornavn() + ")");
	}

	return 0;
    }

    private String getGender(String klasse) {
	if (klasse.startsWith("M")) {
	    return "Male";
	}

	return "Female";
    }

}
