package com.example.lence.rxtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.editText)
    EditText login;
    @BindView(R.id.editText2)
    EditText pass;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button.setEnabled(false);

        Observable<String> loginObservable = RxEditText.getTextWatcherObservable(login);
        Observable<String> passObservable = RxEditText.getTextWatcherObservable(pass);

        Observable.combineLatest(loginObservable, passObservable, new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(@NonNull String s, @NonNull String s2) throws Exception {

                return !(s.isEmpty()||s2.isEmpty());
//                if(s.isEmpty()||s2.isEmpty())
//                    return false;
//                else return true;
            }
        })

                .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                button.setEnabled(aBoolean);

            }
        });


    }

    @OnClick(R.id.button)
    public void onViewClicked() {


    }
}
