package br.com.vsc.pages;

import br.com.vsc.utils.PageBase;

public class BankDomicilePage extends PageBase {
    private String headerBankDomicileID = "vsc-accountregistration-h2";
    private String fieldBankID = "vsc-bankaccount-bankselect-select";
    private String radioButtonCheckingAccountID = "vsc-accountregistration-radio-corrente";
    private String fieldAgencyXPATH = "//input[@id='vsc-bankaccount-agency']";
    private String fieldAccountXPATH = "//input[@id='vsc-bankaccount-bankaccount']";
    private String fieldDigitXPATH = "//input[@id='vsc-bankaccount-accountdigit']";
    private String buttonForwardBankDomicileID = "vsc-accountregistration-button";

    public void validateBankDomicileScreen() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando tela de Domicilio Bancario",
                elementExist(SelectorType.ID, headerBankDomicileID, Constants.TIME_WAIT_ELEMENT_EXIST) );
    }

    public void selectBank(String Bank) throws Exception {
        String optionBankXPATH = "//span[contains(.,'"+Bank+"')]";
        if( elementExist(SelectorType.ID, fieldBankID, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.ID, fieldBankID);
        }
        if( elementExist(SelectorType.XPATH, optionBankXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, optionBankXPATH);
        }
    }

    public void selectCheckingAccount() throws Exception {
        if( elementExist(SelectorType.ID, radioButtonCheckingAccountID, Constants.TIME_WAIT_ELEMENT_EXIST ) )
            clickWebElement(SelectorType.ID, radioButtonCheckingAccountID);
    }

    public void fillAgency(String Agency) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldAgencyXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) )
            fillFieldElementWeb(SelectorType.XPATH, fieldAgencyXPATH, Agency);
    }

    public void fillAccountAndDigit(String Account, String Digit) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldAccountXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) )
            fillFieldElementWeb(SelectorType.XPATH, fieldAccountXPATH, Account);
        if( elementExist(SelectorType.XPATH, fieldDigitXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) )
            fillFieldElementWeb(SelectorType.XPATH, fieldDigitXPATH, Digit);
    }

    public void clickButtonForwardBankDomicile() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando preenchimento de dados Domicilio Bancario",
                elementExist(SelectorType.ID, buttonForwardBankDomicileID,Constants.TIME_WAIT_ELEMENT_EXIST));
        clickWebElement(SelectorType.ID, buttonForwardBankDomicileID);
    }
}
