package com.example.app_pedido_de_comida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView w = findViewById(R.id.web);
        w.setWebViewClient(new WebViewClient());
        w.setWebChromeClient(new WebChromeClient());


        //habilitando a execução de javascript
        WebSettings conf = w.getSettings();
        conf.setJavaScriptEnabled(true);

        w.addJavascriptInterface(new Ponte(this), "Chamada");

        w.loadUrl("file:///android_asset/index.html");
    }

    public class Ponte {
        Context contexto;


        public Ponte(Context contexto) {
            this.contexto = contexto;
        }

        @JavascriptInterface
        public void envia(String client, String product, String flavor, String value) {

            try {
                BancoDeDados bd = new BancoDeDados(this.contexto);
                bd.insereprodutos(client, product, flavor,  Double.parseDouble(value));
                Toast.makeText(this.contexto, "Conta salvada", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(this.contexto, "Erro na criação:"+ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        public String result;

        @JavascriptInterface
        public void consultar() {
            BancoDeDados bd = new BancoDeDados(this.contexto);
            ArrayList<String> resultado =  bd.consultar();
            String mensagem = "";
            for (int i=0; i< resultado.size(); i++) {
                mensagem += resultado.get(i);
            }
            result = mensagem;

        }

        @JavascriptInterface
        public String getResult() {
            consultar();
            return result;
        }
    }
}