package com.example.heitorcolangelo.espressotests.EspressoVersao2.robot;

import com.example.heitorcolangelo.espressotests.R;
import com.example.heitorcolangelo.espressotests.common.ScreenRobot;

import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.EMAIL;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.HINT_EMAIL;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.HINT_SENHA;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.SENHA;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.TITULO_LISTA_DE_USUARIOS;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.TITULO_LOGIN;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.TXT1_POPUP;
import static com.example.heitorcolangelo.espressotests.EspressoVersao2.constants.ConstantsVersao2.TXT2_POPUP;

public class RobotVersao2 extends ScreenRobot<RobotVersao2> {


    private static final int CAMPO_EMAIL = R.id.login_username;
    private static final int CAMPO_SENHA = R.id.login_password;
    private static final int BOTAO_LOGIN = R.id.login_button;
    private static final int IMAGEM_LOGIN = R.id.login_image;
    private static final int VIEW_PARA_SCROLL = R.id.recycler_view;
    private static final int NOME_PARA_O_USUARIO = R.id.user_view_name;


    public RobotVersao2 verificarTituloDaTela(){
        checkViewContainsText(TITULO_LOGIN);
        return this;
    }

    public RobotVersao2 verificarCampoLogin(){
        checkViewHasHint(CAMPO_EMAIL, HINT_EMAIL);
        return this;
    }

    public RobotVersao2 verificarCampoSenha(){
        checkViewHasHint(CAMPO_SENHA, HINT_SENHA);
        return this;
    }

    public RobotVersao2 verificarBotaoLogin(){
        checkIsDisplayed(BOTAO_LOGIN);
        return this;
    }

    public RobotVersao2 verificarImagem(){
        checkIsDisplayed(IMAGEM_LOGIN);
        return this;
    }

    public RobotVersao2 clicarNoLogin(){
        clickOnView(BOTAO_LOGIN);
        return this;
    }

    public RobotVersao2 verificarPopup(){
        checkDialogWithTextIsDisplayed(TXT1_POPUP);
        checkDialogWithTextIsDisplayed(TXT2_POPUP);
        return this;
    }

    public RobotVersao2 EscreverEmail(){
        enterTextIntoView(CAMPO_EMAIL, EMAIL);
        return this;
    }

    public RobotVersao2 EscreverSenha(){
        enterTextIntoView(CAMPO_SENHA, SENHA);
        return this;
    }

    public RobotVersao2 verificarTituloDaTela2(){
        checkViewContainsText(TITULO_LISTA_DE_USUARIOS);
        return this;
    }

    public RobotVersao2 clicarEmVoltar(){
    pressBack();
    return this;
    }

    public RobotVersao2 ScrolarParaBaixo(){
        scrollViewDown(VIEW_PARA_SCROLL);
         return this;
    }

    public RobotVersao2 ClicarCard2(){
        clickOnCardForList(NOME_PARA_O_USUARIO, 2);
        return this;
    }

}
