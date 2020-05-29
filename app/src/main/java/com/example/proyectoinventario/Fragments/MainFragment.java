package com.example.proyectoinventario.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectoinventario.MainActivity;
import com.example.proyectoinventario.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainFragment extends Fragment {
    private ImageButton btnScanner;
    private TextView tvBarCode;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment,container,false);
        btnScanner = view.findViewById(R.id.btnQr);
        tvBarCode = view.findViewById(R.id.qrLabel);

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escanear();
            }
        });

        return view;
    }

    public void escanear(){
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(MainFragment.this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("ESCANEAR CODIGO");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        btnScanner = (ImageButton) view.findViewById(R.id.btnQr);
//        tvBarCode = (TextView) view.findViewById(R.id.qrLabel);
//
//        btnScanner.setOnClickListener();
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(getContext(), "Cancelaste el escaneo", Toast.LENGTH_SHORT).show();
            }else{
//                fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, new NewArticle());
//                fragmentTransaction.commit();
                tvBarCode.setText(result.getContents().toString());
            }
        }else{
            super.onActivityResult(requestCode, resultCode,data);
        }

//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
//        if (result != null){
//            if (result.getContents() != null){
//                tvBarCode.setText("El codig de barras es:\n" + result.getContents());
//            }else{
//                tvBarCode.setText("El codigo de barras no fue capturado");
//            }
//        }
    }


//    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.btnQr:
//                    new IntentIntegrator.forSupportFragment(MainFragment.this).initiateScan();
//                    break;
//            }
//        }
//    };

}
