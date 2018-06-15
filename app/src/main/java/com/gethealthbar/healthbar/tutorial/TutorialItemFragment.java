package com.gethealthbar.healthbar.tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gethealthbar.healthbar.R;


/**
 * Created by Alexandr.
 */
public class TutorialItemFragment extends Fragment {

    private int imageID;
    private String text;

    private ImageView backgroundImage;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tutorial, container, false);

        backgroundImage = rootView.findViewById(R.id.background);
        textView = rootView.findViewById(R.id.text);
        backgroundImage.setImageResource(imageID);
        textView.setText(text);

        return rootView;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
