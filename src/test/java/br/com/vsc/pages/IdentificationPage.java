package br.com.vsc.pages;

import br.com.vsc.utils.PageBase;

public class IdentificationPage extends PageBase {

	private String headerIdentifiqueSeId 	= 	"vsc-identify-h2";
	private String cartToYouCpfID 			= 	"vsc-identify-cardToYou-card";
	private String cartToCompanyCnpjID 		= 	"vsc-identify-cardToCompany-card";
	private String fieldCpfIdentifyXPATH	= 	"//input[@id='vsc-identify-field-cpf']";
	private String buttonOKfieldCPFID 		= 	"vsc-identify-field-cpf-btn-ok";
	private String buttonAvancarIdentificacaoXPATH =	"//button[@id='vsc-identify-btn-register']";

	public void validateIdentificationScreen() throws Exception {
		assertTrue(Constants.GET_PRINT,"Validando Tela de Identificação", elementExist(SelectorType.ID, headerIdentifiqueSeId, Constants.TIME_WAIT_ELEMENT_EXIST) );
	}

	public void fillCPFfield(String CPF) throws Exception {
		if( elementExist(SelectorType.XPATH, fieldCpfIdentifyXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) ) {
			clickWebElement(SelectorType.XPATH, fieldCpfIdentifyXPATH);
			fillFieldElementWeb(SelectorType.XPATH, fieldCpfIdentifyXPATH, CPF);
		}
	}

	public void clickButtonCartCPFToYou() throws Exception {
		if( elementExist(SelectorType.ID, cartToYouCpfID, Constants.TIME_WAIT_ELEMENT_EXIST ) )
			clickWebElement(SelectorType.ID, cartToYouCpfID);
	}

	public void clickButtonOkCpfField() throws Exception {
		if( elementExist(SelectorType.ID, buttonOKfieldCPFID, Constants.TIME_WAIT_ELEMENT_EXIST ) )
			clickWebElement(SelectorType.ID, buttonOKfieldCPFID);
	}

	public void clickButtonFowardIdentification() throws Exception {
		assertTrue(Constants.GET_PRINT, "Validando preenchimento de Informações Tela de Identificação",
				elementExist(SelectorType.XPATH, buttonAvancarIdentificacaoXPATH, Constants.TIME_WAIT_ELEMENT_EXIST ) );
		clickWebElement(SelectorType.XPATH, buttonAvancarIdentificacaoXPATH);
	}



	public void clicarCartCNPJToCompany() {

	}
}
