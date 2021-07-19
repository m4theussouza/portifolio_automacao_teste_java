package br.com.vsc.steps;

import br.com.vsc.pages.Constants;
import br.com.vsc.pages.PersonalDataPage;
import br.com.vsc.utils.Excel;
import io.cucumber.java.pt.Quando;

public class PersonalDataStepdefs {
    PersonalDataPage personalDataPage = new PersonalDataPage();
    Excel vscData = new Excel();

    @Quando("estiver na tela de Dados Cadastrais, preencher dados e avançar")
    public void estiver_na_tela_de_Dados_Cadastrais_preencher_dados_e_avançar() throws Exception{
        personalDataPage.validatePersonalDataScreen();
        personalDataPage.fillNameAndLastname(vscData.getData("Nome Sobrenome"));
        personalDataPage.fillBirthDate(vscData.getData("Data De Nascimento"));
        personalDataPage.fillCellPhone(vscData.getData("Celular"));
        personalDataPage.fillEmail(vscData.getData("Email"));
        personalDataPage.fillConfirmEmail(vscData.getData("Email"));
        personalDataPage.selectActivity(vscData.getData("Ramo de Atividade"));
        personalDataPage.fillAverageBilling(vscData.getData("Faturamento Mensal"));
        personalDataPage.clickButtonForwardPersonalData();

    }













}
