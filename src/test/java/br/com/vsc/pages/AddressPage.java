package br.com.vsc.pages;

import br.com.vsc.utils.PageBase;

public class AddressPage extends PageBase {
    private String inputCepID = "vsc-addressregistration-cep";
    private String inputNumberID = "vsc-addressregistration-number";
    private String headerAddressID = "vsc-addressregistration-h2";
    private String forwardAdressID = "vsc-addressregistration-button";

    public void fillCEPfield(String CEP) throws Exception {
        if( elementExist(SelectorType.ID, inputCepID, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.ID, inputCepID);
            fillFieldElementWeb(SelectorType.ID, inputCepID, CEP);
        }
    }

    public void fillNumberfield(String Number) throws Exception {
        if( elementExist(SelectorType.ID, inputNumberID, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.ID, inputNumberID);
            fillFieldElementWeb(SelectorType.ID, inputNumberID, Number);
        }
    }

    public void validateAddressScreen() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando tela de endereço",
                elementExist(SelectorType.ID, headerAddressID, Constants.TIME_WAIT_ELEMENT_EXIST ) );
    }

    public void clickButtonForwardAddress() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando preenchimento de informações na tela de endereço",
                elementExist(SelectorType.ID, forwardAdressID, Constants.TIME_WAIT_ELEMENT_EXIST ));
        clickWebElement(SelectorType.ID, forwardAdressID);
    }
}
