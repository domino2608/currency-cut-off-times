package com.dominikc.currencyraterest.util;

import com.dominikc.currencyraterest.model.CountryCutOffTime;
import com.dominikc.currencyraterest.model.CutOffTime;
import com.dominikc.currencyraterest.model.CutOffTimeStaticValue;
import com.dominikc.currencyraterest.repository.CountryCutOffTimesRepository;
import com.dominikc.currencyraterest.repository.CutOffTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    private final CountryCutOffTimesRepository countryCutOffTimesRepository;

    @Autowired
    public DataLoader(CountryCutOffTimesRepository countryCutOffTimesRepository) {
        this.countryCutOffTimesRepository = countryCutOffTimesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountryCutOffTime aed = new CountryCutOffTime("AED", "United Arab Emirates");
        aed.setToday(CutOffTime.neverPossible());
        aed.setTomorrow(CutOffTime.fromHour(14));
        aed.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(aed);

        CountryCutOffTime aud = new CountryCutOffTime("AUD", "Australia");
        aud.setToday(CutOffTime.neverPossible());
        aud.setTomorrow(CutOffTime.fromHour(14));
        aud.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(aud);

        CountryCutOffTime bgn = new CountryCutOffTime("BGN", "Bulgaria");
        bgn.setToday(CutOffTime.neverPossible());
        bgn.setTomorrow(CutOffTime.fromHour(14));
        bgn.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(bgn);

        CountryCutOffTime cad = new CountryCutOffTime("CAD", "Canada");
        cad.setToday(CutOffTime.fromTime(15, 30));
        cad.setTomorrow(CutOffTime.alwaysPossible());
        cad.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(cad);

        CountryCutOffTime chf = new CountryCutOffTime("CHF", "Switzerland");
        chf.setToday(CutOffTime.fromHour(10));
        chf.setTomorrow(CutOffTime.alwaysPossible());
        chf.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(chf);

        CountryCutOffTime cnh = new CountryCutOffTime("CNH", "China (Hong Kong)");
        cnh.setToday(CutOffTime.neverPossible());
        cnh.setTomorrow(CutOffTime.fromHour(14));
        cnh.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(cnh);

        CountryCutOffTime czk = new CountryCutOffTime("CZK", "Czech Republic");
        czk.setToday(CutOffTime.fromHour(11));
        czk.setTomorrow(CutOffTime.alwaysPossible());
        czk.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(czk);

        CountryCutOffTime dkk = new CountryCutOffTime("DKK", "Denmark");
        dkk.setToday(CutOffTime.fromTime(15, 30));
        dkk.setTomorrow(CutOffTime.alwaysPossible());
        dkk.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(dkk);

        CountryCutOffTime eur = new CountryCutOffTime("EUR", "Euro Area");
        eur.setToday(CutOffTime.fromHour(16));
        eur.setTomorrow(CutOffTime.alwaysPossible());
        eur.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(eur);

        CountryCutOffTime gbp = new CountryCutOffTime("GBP", "United Kingdom");
        gbp.setToday(CutOffTime.fromTime(15, 30));
        gbp.setTomorrow(CutOffTime.alwaysPossible());
        gbp.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(gbp);

        CountryCutOffTime hkd = new CountryCutOffTime("HKD", "Hong Kong");
        hkd.setToday(CutOffTime.neverPossible());
        hkd.setTomorrow(CutOffTime.fromHour(14));
        hkd.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(hkd);

        CountryCutOffTime hrk = new CountryCutOffTime("HRK", "Croatia");
        hrk.setToday(CutOffTime.neverPossible());
        hrk.setTomorrow(CutOffTime.fromHour(14));
        hrk.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(hrk);

        CountryCutOffTime huf = new CountryCutOffTime("HUF", "Hungary");
        huf.setToday(CutOffTime.fromHour(11));
        huf.setTomorrow(CutOffTime.alwaysPossible());
        huf.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(huf);


        CountryCutOffTime ils = new CountryCutOffTime("ILS", "Israel");
        ils.setToday(CutOffTime.neverPossible());
        ils.setTomorrow(CutOffTime.fromHour(14));
        ils.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(ils);

        CountryCutOffTime jpy = new CountryCutOffTime("JPY", "Japan");
        jpy.setToday(CutOffTime.neverPossible());
        jpy.setTomorrow(CutOffTime.fromTime(15, 30));
        jpy.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(jpy);

        CountryCutOffTime mxn = new CountryCutOffTime("MXN", "Mexico");
        mxn.setToday(CutOffTime.fromHour(11));
        mxn.setTomorrow(CutOffTime.alwaysPossible());
        mxn.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(mxn);

        CountryCutOffTime nok = new CountryCutOffTime("NOK", "Norway");
        nok.setToday(CutOffTime.fromHour(15));
        nok.setTomorrow(CutOffTime.alwaysPossible());
        nok.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(nok);

        CountryCutOffTime nzd = new CountryCutOffTime("NZD", "New Zealand");
        nzd.setToday(CutOffTime.neverPossible());
        nzd.setTomorrow(CutOffTime.fromHour(14));
        nzd.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(nzd);

        CountryCutOffTime pln = new CountryCutOffTime("PLN", "Poland");
        pln.setToday(CutOffTime.fromHour(10));
        pln.setTomorrow(CutOffTime.alwaysPossible());
        pln.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(pln);

        CountryCutOffTime ron = new CountryCutOffTime("RON", "Romania");
        ron.setToday(CutOffTime.neverPossible());
        ron.setTomorrow(CutOffTime.fromHour(14));
        ron.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(ron);

        CountryCutOffTime rub = new CountryCutOffTime("RUB", "Russia");
        rub.setToday(CutOffTime.neverPossible());
        rub.setTomorrow(CutOffTime.fromHour(13));
        rub.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(rub);

        CountryCutOffTime rsd = new CountryCutOffTime("RSD", "Serbia");
        rsd.setToday(CutOffTime.neverPossible());
        rsd.setTomorrow(CutOffTime.neverPossible());
        rsd.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(rsd);

        CountryCutOffTime sar = new CountryCutOffTime("SAR", "Saudi Arabia");
        sar.setToday(CutOffTime.neverPossible());
        sar.setTomorrow(CutOffTime.fromHour(14));
        sar.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(sar);

        CountryCutOffTime sek = new CountryCutOffTime("SEK", "Sweden");
        sek.setToday(CutOffTime.fromTime(15, 30));
        sek.setTomorrow(CutOffTime.alwaysPossible());
        sek.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(sek);

        CountryCutOffTime sgd = new CountryCutOffTime("SGD", "Singapore");
        sgd.setToday(CutOffTime.neverPossible());
        sgd.setTomorrow(CutOffTime.fromHour(14));
        sgd.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(sgd);

        CountryCutOffTime thb = new CountryCutOffTime("THB", "Thailand");
        thb.setToday(CutOffTime.neverPossible());
        thb.setTomorrow(CutOffTime.fromHour(9));
        thb.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(thb);

        CountryCutOffTime tur = new CountryCutOffTime("TRY", "Turkey");
        tur.setToday(CutOffTime.neverPossible());
        tur.setTomorrow(CutOffTime.fromHour(14));
        tur.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(tur);

        CountryCutOffTime usd = new CountryCutOffTime("USD", "United States");
        usd.setToday(CutOffTime.fromHour(16));
        usd.setTomorrow(CutOffTime.alwaysPossible());
        usd.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(usd);

        CountryCutOffTime zar = new CountryCutOffTime("ZAR", "South Africa");
        zar.setToday(CutOffTime.neverPossible());
        zar.setTomorrow(CutOffTime.fromHour(14));
        zar.setAfterTomorrow(CutOffTime.alwaysPossible());
        countryCutOffTimesRepository.save(zar);
    }

}
