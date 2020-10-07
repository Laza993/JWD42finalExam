package jwd.finalExam.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.finalExam.model.Racun;
import jwd.finalExam.model.Transakcija;
import jwd.finalExam.repository.TransakcijaRepository;
import jwd.finalExam.service.RacunService;
import jwd.finalExam.service.TransakcijaService;

@Transactional
@Service
public class JpaTransakcijaService implements TransakcijaService {
	
	private final Long racunIntesaID = 53L;
	private final Long racunKomercijalnaID = 54L;
	
	private final Long bankaIntesaID = 1L;

	@Autowired
	TransakcijaRepository transakcijaRepostory;
	@Autowired
	RacunService racunService;

	@Override
	public Transakcija makeTransaction(Transakcija transakcijaOdKorisnika) {
		Racun racunBanke = null;
		
		if(transakcijaOdKorisnika.getRacunUplatioc().getStanje() >= transakcijaOdKorisnika.getIznos()){	
			
			if(transakcijaOdKorisnika.getRacunPrimaoc().getStanje() == null) {
				transakcijaOdKorisnika.getRacunPrimaoc().setStanje((double)0);
			}
			
			Double provizija = transakcijaOdKorisnika.getRacunUplatioc().getTip().getProvizija();
			Double procenatZaBanku = transakcijaOdKorisnika.getIznos() * provizija / 100;
						
			if(procenatZaBanku > 1000) {
				procenatZaBanku = (double) 1000;
			}
			
			if(transakcijaOdKorisnika.getRacunUplatioc().getBanka().getId() == bankaIntesaID) {
				racunBanke =  racunService.findOne(racunIntesaID);
			}else {
				racunBanke =  racunService.findOne(racunKomercijalnaID);
			}
			if(racunBanke.getStanje() == null) {
				racunBanke.setStanje((double)0);
			}
			
			racunBanke.setStanje(racunBanke.getStanje() + procenatZaBanku);
			
			Double iznosZaPrenos = transakcijaOdKorisnika.getIznos() - procenatZaBanku;
			
			transakcijaOdKorisnika.getRacunUplatioc().setStanje(transakcijaOdKorisnika.getRacunUplatioc().getStanje() - transakcijaOdKorisnika.getIznos());
			
			transakcijaOdKorisnika.getRacunPrimaoc().setStanje(transakcijaOdKorisnika.getRacunPrimaoc().getStanje() + iznosZaPrenos);
			transakcijaOdKorisnika.setStateOfTransaction(true);

			transakcijaRepostory.save(transakcijaOdKorisnika);
		}else {
			transakcijaOdKorisnika.setStateOfTransaction(false);
			transakcijaRepostory.save(transakcijaOdKorisnika);
		}
		return transakcijaOdKorisnika;
	}

	@Override
	public Transakcija findOne(Long id) {
		return transakcijaRepostory.findOne(id);
	}

	@Override
	public List<Transakcija> findAll() {

		return transakcijaRepostory.findAll();
	}

}
