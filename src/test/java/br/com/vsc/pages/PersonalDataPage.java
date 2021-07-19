package br.com.vsc.pages;

import br.com.vsc.utils.PageBase;

public class PersonalDataPage extends PageBase {
    private String headerPersonalDataID = "vsc-personaldata-h2";
    private String fieldNameAndLastnameXPATH = "//input[@id='vsc-personaldata-name']";
    private String fieldBirthDateXPATH = "//input[@id='vsc-personaldata-birthdate']";
    private String fieldCellPhoneXPATH = "//input[@id='vsc-personaldata-celphone']";
    private String fieldEmailXPATH = "//input[@id='vsc-personaldata-email']";
    private String fieldConfirmEmailXPATH = "//input[@id='vsc-personaldata-confirmemail']";
    private String fieldActivityXPATH = "//mat-select[@id='vsc-personaldata-activity-select']";
    private String fieldAverageBillingXPATH = "//input[@id='vsc-personaldata-averagebilling']";
    private String buttonAvancarDadosPessoaisID = "vsc-personaldata-button";

    public void validatePersonalDataScreen() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando tela de dados pessoais",
                elementExist(SelectorType.ID, headerPersonalDataID, Constants.TIME_WAIT_ELEMENT_EXIST ));
    }

    public void fillNameAndLastname(String NameAndLastname) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldNameAndLastnameXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldNameAndLastnameXPATH);
            fillFieldElementWeb(SelectorType.XPATH, fieldNameAndLastnameXPATH, NameAndLastname);
        }
    }

    public void fillBirthDate(String BirthDate) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldBirthDateXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldBirthDateXPATH);
            fillFieldElementWeb(SelectorType.XPATH, fieldBirthDateXPATH, BirthDate);
        }
    }

    public void fillCellPhone(String CellPhone) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldCellPhoneXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldCellPhoneXPATH);
            fillFieldElementWeb(SelectorType.XPATH, fieldCellPhoneXPATH, CellPhone);
        }
    }

    public void fillEmail(String Email) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldEmailXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldEmailXPATH);
            fillFieldElementWeb(SelectorType.XPATH, fieldEmailXPATH, Email);
        }
    }

    public void fillConfirmEmail(String ConfirmEmail) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldConfirmEmailXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldConfirmEmailXPATH);
            fillFieldElementWeb(SelectorType.XPATH, fieldConfirmEmailXPATH, ConfirmEmail);
        }
    }

    public void fillAverageBilling(String AverageBilling) throws Exception {
        if( elementExist(SelectorType.XPATH, fieldAverageBillingXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldAverageBillingXPATH);
            fillFieldElementWeb(SelectorType.XPATH, fieldAverageBillingXPATH, AverageBilling);
        }
    }

    public void selectActivity(String Activity) throws Exception {
        String optionActivityXPATH = "//span[normalize-space(text())='"+ Activity +"']";
        if( elementExist(SelectorType.XPATH, fieldActivityXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, fieldActivityXPATH);
        }
        if( elementExist(SelectorType.XPATH, optionActivityXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, optionActivityXPATH);
        }
    }

    public void clickButtonForwardPersonalData() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando preenchimento de Informações Tela de Dados Pessoais",
                elementExist(SelectorType.ID, buttonAvancarDadosPessoaisID, Constants.TIME_WAIT_ELEMENT_EXIST ) );
        clickWebElement(SelectorType.ID, buttonAvancarDadosPessoaisID);
    }
}
