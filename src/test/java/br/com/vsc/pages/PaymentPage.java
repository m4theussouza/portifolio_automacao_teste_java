package br.com.vsc.pages;

import br.com.vsc.utils.PageBase;

public class PaymentPage extends PageBase {
    private String headerPagamentoXPATH = "//h2[text()='Pagamento']";
    private String spanPaymentCreditCardID = "payment-section-creditcard";
    private String labelLgpdXPATH = "//label[normalize-space(text())='Declaro que o cliente foi informado']";
    private String buttonForwardPaymentID = "vsc-conditions-button";

    public void validatePaymentPage() throws Exception {
        assertTrue(Constants.GET_PRINT, "Validando tela de Pagamento",
                elementExist(SelectorType.XPATH, headerPagamentoXPATH, Constants.TIME_WAIT_ELEMENT_EXIST) );
    }

    public void clickDivCreditCard() throws Exception {
        if( elementExist(SelectorType.ID, spanPaymentCreditCardID, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.ID, spanPaymentCreditCardID);
            System.out.println("Teste");
        }
    }

    public void clickButtonForwardPayment() throws Exception {
        if( elementExist(SelectorType.ID, buttonForwardPaymentID, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
            clickWebElement(SelectorType.ID, buttonForwardPaymentID);
            System.out.println("Teste");
        }
    }
}
