package com.example.northlordv2.Observables;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class EditTextObservable {
    public static Observable<String> getObservable(final EditText editText){
        final PublishSubject<String> publishSubject=PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                publishSubject.onNext(editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return publishSubject;
    }
}
