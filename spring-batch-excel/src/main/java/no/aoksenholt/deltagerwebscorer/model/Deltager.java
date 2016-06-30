package no.aoksenholt.deltagerwebscorer.model;

public class Deltager {
    private String etternavn;
    private String fornavn;
    private String pameldingTil;
    private String epost;
    private String klubb;
    private String fodselsar;
    private String klasse;
    private String tskjorte;
    private String kondis;

    public Deltager() {
	// TODO Auto-generated constructor stub
    }

    public String getEtternavn() {
	return etternavn;
    }

    public void setEtternavn(String etternavn) {
	this.etternavn = etternavn;
    }

    public String getFornavn() {
	return fornavn;
    }

    public void setFornavn(String fornavn) {
	this.fornavn = fornavn;
    }

    public String getPameldingTil() {
	return pameldingTil;
    }

    public void setPameldingTil(String pameldingTil) {
	this.pameldingTil = pameldingTil;
    }

    @Override
    public String toString() {
	return this.etternavn + ", " + this.fornavn;
    }

    public String getEpost() {
	return epost;
    }

    public void setEpost(String epost) {
	this.epost = epost;
    }

    public String getKlubb() {
	return klubb;
    }

    public void setKlubb(String klubb) {
	this.klubb = klubb;
    }

    public String getFodselsar() {
	return fodselsar;
    }

    public void setFodselsar(String fodselsar) {
	this.fodselsar = fodselsar;
    }

    public String getKlasse() {
	return klasse;
    }

    public void setKlasse(String klasse) {
	this.klasse = klasse;
    }

    public String getTskjorte() {
	return tskjorte;
    }

    public void setTskjorte(String tskjorte) {
	this.tskjorte = tskjorte;
    }

    public String getKondis() {
	return kondis;
    }

    public void setKondis(String kondis) {
	this.kondis = kondis;
    }

}
