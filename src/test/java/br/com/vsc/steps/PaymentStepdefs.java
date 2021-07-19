package br.com.vsc.steps;

import br.com.vsc.pages.PaymentPage;
import io.cucumber.java.pt.Quando;

public class PaymentStepdefs {
    PaymentPage paymentPage = new PaymentPage();
    @Quando("estiver na tela de Pagamento, preencher os dados e avançar")
    public void estiver_na_tela_de_Pagamento_preencher_os_dados_e_avançar() throws Exception {
        paymentPage.validatePaymentPage();
        paymentPage.clickDivCreditCard();
    }
}
