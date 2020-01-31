package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private View view;

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_background, null);
        textView = view.findViewById(R.id.tv1);
        imageView = view.findViewById(R.id.iv1);

        //change the font family of text view in tabs(Make sure internet is on, for the first time to prevent crash)
        //if(internet is on)
        Typeface typeface = ResourcesCompat.getFont(getApplication(), R.font.poppins_medium);
        textView.setTypeface(typeface);
        //else
        //..default font


        setCustomView(0, 1, 2, 3);
        setTextAndImageWithAnimation("HOME", R.drawable.ic_home);
        handleFragment(new HomeFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

                    case 1: //catergories
                        setCustomView(1, 0, 2, 3);
                        setTextAndImageWithAnimation("CATEGORIES", R.drawable.ic_categories);

                        handleFragment(new CategoriesFragment());
                        break;

                    case 2: //settings
                        setCustomView(2, 1, 0, 3);
                        setTextAndImageWithAnimation("SETTINGS", R.drawable.ic_settings);

                        handleFragment(new SettingsFragment());
                        break;

                    case 3:
                        setCustomView(3, 1, 2, 0);
                        setTextAndImageWithAnimation("PROFILE", R.drawable.ic_person);

                        handleFragment(new ProfileFragment());
                        break;
                    case 0:

                    default:
                        setCustomView(0, 1, 2, 3);
                        setTextAndImageWithAnimation("HOME", R.drawable.ic_home);

                        handleFragment(new HomeFragment());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setCustomView(int selectedtab, int non1, int non2, int non3) {
        Objects.requireNonNull(tabLayout.getTabAt(selectedtab)).setCustomView(view);
        Objects.requireNonNull(tabLayout.getTabAt(non1)).setCustomView(null);
        Objects.requireNonNull(tabLayout.getTabAt(non2)).setCustomView(null);
        Objects.requireNonNull(tabLayout.getTabAt(non3)).setCustomView(null);
    }

    private void setTextAndImageWithAnimation(String text, int images) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        textView.setText(text);
        imageView.setImageResource(images);
        textView.startAnimation(animation);
        imageView.startAnimation(animation);
    }

    private void handleFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}