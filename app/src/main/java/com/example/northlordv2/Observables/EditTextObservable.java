package com.example.northlordv2.Observables;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.northlordv2.application.NumberPointMaker;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class EditTextObservable {
    public static Observable<String> getObservable(final EditText editText){
        final PublishSubject<String> publishSubject=PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            int i=0;boolean m=false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("observer","----------"+m);
                Log.d("observer",NumberPointMaker.makePoints(s.toString()));
                publishSubject.onNext(s.toString());



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return publishSubject;
    }
    public static Observable<String> getObservableWithSpaces(final EditText editText){
        final PublishSubject<String> publishSubject=PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            int i=0;boolean m=false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d("observer","----------"+m);
//                Log.d("observer",NumberPointMaker.makePoints(s.toString()));
                if(!m){
                    m=!m;

                    publishSubject.onNext(NumberPointMaker.deletePoints(s.toString()));
                    editText.setText(NumberPointMaker.makePoints(s.toString()));
                    editText.setSelection(editText.getText().toString().length());
                }else {
                    m=!m;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return publishSubject;
    }
}
