package com.example.heitorcolangelo.espressotests.EspressoVersao2;

import android.support.test.rule.ActivityTestRule;

import com.example.heitorcolangelo.espressotests.EspressoVersao2.robot.RobotVersao2;
import com.example.heitorcolangelo.espressotests.ui.activity.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CasoDeTesteVersao2 {


    @Rule
    public ActivityTestRule<LoginActivity>
            mActivityRule = new ActivityTestRule<>(LoginActivity.class, false, true);



    @Before
    public void Setup(){

    }

    @After
    public void Finish(){


    }

    @Test
    public void TesteNumero1(){
        new RobotVersao2()
                .verificarTituloDaTela()
                .verificarCampoLogin()
                .verificarCampoSenha()
                .verificarBotaoLogin()
                .verificarImagem();
    }

    @Test
    public void TesteNumero2(){
        new RobotVersao2()
                .clicarNoLogin()
                .verificarPopup();

    }

    @Test
    public void TesteNumero3(){
        new RobotVersao2()
                .EscreverEmail()
                .clicarNoLogin()
                .verificarPopup();
    }

    @Test
    public void TesteNumero4(){
        new RobotVersao2()
                .EscreverSenha()
                .clicarNoLogin()
                .verificarPopup();
    }

    @Test
    public void TesteNumero5(){
        new RobotVersao2()
                .EscreverEmail()
                .EscreverSenha()
                .clicarNoLogin()
                .clicarEmVoltar()
                .verificarTituloDaTela();
    }

    @Test
    public void TesteNumero6(){
        new RobotVersao2()
                .EscreverEmail()
                .EscreverSenha()
                .clicarNoLogin()
                .clicarEmVoltar()
                .verificarTituloDaTela()
                .verificarCampoLogin()
                .verificarCampoSenha()
                .verificarBotaoLogin()
                .verificarImagem();
    }

    @Test
    public void TesteNumero7() throws InterruptedException{
        new RobotVersao2()
                .EscreverEmail()
                .EscreverSenha()
                .clicarNoLogin()
                .sleep(5)
                .ScrolarParaBaixo()
                .sleep(5);
    }

    @Test
    public void TesteNumero8() throws InterruptedException{
        new RobotVersao2()
                .EscreverEmail()
                .EscreverSenha()
                .clicarNoLogin()
                .sleep(5)
                .ClicarCard2()
                .sleep(5);
    }
}
