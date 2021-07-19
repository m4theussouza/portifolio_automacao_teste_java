package br.com.vsc.steps;

import br.com.vsc.pages.ConditionsPage;
import io.cucumber.java.pt.Quando;

public class ConditionsStepdefs {
    ConditionsPage conditionsPage = new ConditionsPage();
    @Quando("estiver na tela de Condicoes, aceitar condicoes e avançar")
    public void estiver_na_tela_de_Condicoes_aceitar_condicoes_e_avançar() throws Exception {
        conditionsPage.validateConditionsScreen();
        conditionsPage.selectCheckboxLgpd();
        conditionsPage.clickButtonForwardConditions();
    }
}
