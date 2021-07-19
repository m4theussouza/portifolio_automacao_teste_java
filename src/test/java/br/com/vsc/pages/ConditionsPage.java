package br.com.vsc.pages;

import br.com.vsc.utils.PageBase;

public class ConditionsPage extends PageBase {
    private String headerConditionsID = "vsc-conditions-h2";
    private String labelLgpdXPATH = "//label[normalize-space(text())='Declaro que o cliente foi informado']";
    private String buttonForwardConditionsID = "vsc-conditions-button";

    public void validateConditionsScreen() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando tela de Condicoes",
                elementExist(SelectorType.ID, headerConditionsID, Constants.TIME_WAIT_ELEMENT_EXIST) );
    }

    public void selectCheckboxLgpd() throws Exception {
        if( elementExist(SelectorType.XPATH, labelLgpdXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.XPATH, labelLgpdXPATH);
        }
    }

    public void clickButtonForwardConditions() throws Exception {
        if( elementExist(SelectorType.ID, buttonForwardConditionsID, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.ID, buttonForwardConditionsID);
        }
    }
}
