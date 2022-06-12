package com.example.hyodor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FragHome fragHome;
    private FragCummunity fragCummunity;
    private FragGift fragGift;
    private FragHearth fragHearth;
    private FragMypage fragMypage;

    @Override public void onBackPressed(){ //뒤로가기 누르면 종료
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home:
                        setFrag(0);
                        break;
                    case R.id.cummunity:
                        setFrag(1);
                        break;
                    case R.id.gift:
                        setFrag(2);
                        break;
                    case R.id.hearth:
                        setFrag(3);
                        break;
                    case R.id.mypage:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });
        fragHome = new FragHome();
        fragCummunity = new FragCummunity();
        fragGift = new FragGift();
        fragHearth = new FragHearth();
        fragMypage = new FragMypage();
        setFrag(0); //첫 화면 선택

        if(FirebaseAuth.getInstance().getCurrentUser() == null){ //로그아웃버튼
            startLoginActivity();
        }
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startLoginActivity();
                    break;
            }
        }
    };


    private void startLoginActivity() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void setFrag(int n){        // 교체가 일어나는 실행문
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.fragments_frame,fragHome);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.fragments_frame,fragCummunity);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.fragments_frame,fragGift);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.fragments_frame,fragHearth);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.fragments_frame,fragMypage);
                ft.commit();
                break;
        }

    }
}