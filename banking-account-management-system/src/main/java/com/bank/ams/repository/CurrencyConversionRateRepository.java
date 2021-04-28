package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.Currency;
import com.bank.ams.model.CurrencyConversionrate;

@Repository
public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionrate, Currency> {

}
