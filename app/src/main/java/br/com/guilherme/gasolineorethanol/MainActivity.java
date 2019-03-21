package br.com.guilherme.gasolineorethanol;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView priceGasolineTextView;
    private SeekBar priceGasolineSeekBar;
    private TextView priceEthanolTextView;
    private SeekBar priceEthanolSeekBar;
    private TextInputEditText optionBetterInput;
    private ImageView typeImageView;

    private double priceGasoline;
    private double priceEthanol;
    private double opcao;

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceGasolineTextView = (TextView) findViewById(R.id.priceGasolineTextView);
        priceGasolineSeekBar = (SeekBar) findViewById(R.id.priceGasolineSeekBar);
        priceEthanolTextView = (TextView) findViewById(R.id.priceEthanolTextView);
        priceEthanolSeekBar = (SeekBar) findViewById(R.id.priceEthanolSeekBar);
        optionBetterInput = (TextInputEditText) findViewById(R.id.optionBetterInput);
        typeImageView = (ImageView) findViewById(R.id.typeImageView);

        priceGasoline = priceEthanol = 0;

        calculo();

        priceGasolineSeekBar.setOnSeekBarChangeListener(valor);
        priceEthanolSeekBar.setOnSeekBarChangeListener(valor);


    }


    private void calculo (){
        opcao = priceEthanol / priceGasoline;
        priceGasolineTextView.setText(currencyFormat.format(priceGasoline));
        priceEthanolTextView.setText(currencyFormat.format(priceEthanol));

        if(opcao >=0.7){

            optionBetterInput.setText(R.string.gasoline);
            typeImageView.setImageResource(R.drawable.gasoline);

        }else if(opcao <= 0.7){

            optionBetterInput.setText(R.string.ethanol);
            typeImageView.setImageResource(R.drawable.ethanol);

        }else{
            optionBetterInput.setText(R.string.nothing);
            typeImageView.setImageResource(R.drawable.start);

        }
    }

    private SeekBar.OnSeekBarChangeListener valor = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            if (seekBar.getId() == R.id.priceGasolineSeekBar){
                priceGasoline = progress / 100.;
            }
            else{
                priceEthanol = progress / 100.;
            }
                calculo();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };
}
