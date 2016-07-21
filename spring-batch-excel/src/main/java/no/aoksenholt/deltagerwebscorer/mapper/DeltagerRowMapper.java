package no.aoksenholt.deltagerwebscorer.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import no.aoksenholt.deltagerwebscorer.model.Deltager;

public class DeltagerRowMapper implements RowMapper<Deltager> {
    Log log = LogFactory.getLog(getClass());

    @Override
    public Deltager mapRow(RowSet rowSet) throws Exception {
	Deltager deltager = new Deltager();

	deltager.setEtternavn(rowSet.getColumnValue(1));
	deltager.setFornavn(rowSet.getColumnValue(2));
	deltager.setPameldingTil(rowSet.getColumnValue(5));
	deltager.setKlubb(rowSet.getColumnValue(11));
	String fodselsar = rowSet.getColumnValue(12);
	deltager.setFodselsar(fixNumber(fodselsar));
	deltager.setKlasse(rowSet.getColumnValue(13));
	deltager.setTskjorte(rowSet.getColumnValue(14));
	deltager.setKondis(fixNumber(rowSet.getColumnValue(16)));

	log.info("Deltager " + rowSet.getCurrentRowIndex() + ", " + deltager.getEtternavn() + ", " + deltager.getFornavn());

	return deltager;
    }

    private String fixNumber(String number) {
	if (number.contains(".")) {
	    return number.substring(0, number.indexOf("."));
	}
	return number;
    }

}
