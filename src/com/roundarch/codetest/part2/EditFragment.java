package com.roundarch.codetest.part2;

import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.roundarch.codetest.ProgressDialogFragment;
import com.roundarch.codetest.R;

public class EditFragment extends Fragment {
    public static final int RESULT_SAVE = 1;
    public static final String EXTRA_MODEL = "extra_model";
    private static AsyncTask asyncTask;


    private DataModel mModel;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;

    // TODO - This fragment should allow you to edit the fields of DataModel
    // TODO - Then when you click the save button, it should get the DataModel back to the prior activity
    // TODO - so it's up to date
    @Override
    public View
            onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container);
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_save();
            }
        });

        edit1 = (EditText)view.findViewById(R.id.editText1);
        edit2 = (EditText)view.findViewById(R.id.editText2);
        edit3 = (EditText)view.findViewById(R.id.editText3);

        setRetainInstance(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void modifyModelOperation(final DataModel model) {
        showLoadingDialog();
        refreshModelFromViews();

        swapText(model);

        asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                double newValue = BlackBox.doMagic(model.getText3());
                model.setText3(newValue);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Intent result = new Intent();
                result.putExtra(EXTRA_MODEL, (Parcelable)model);
                getActivity().setResult(RESULT_SAVE, result);
                getActivity().finish();
            }
        };
        asyncTask.execute();
    }

    private void showLoadingDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ProgressDialogFragment editNameDialog = new ProgressDialogFragment();
        editNameDialog.show(fm, "progress_dialog_fragment");
    }

    private void refreshModelFromViews() {
        mModel.setText1(edit1.getText().toString());
        mModel.setText2(edit2.getText().toString());
        mModel.setText3(Double.parseDouble(edit3.getText().toString()));
    }

    // Modifies the data model to swap the values in text1 and text2
    private void swapText(DataModel model) {
        String temp = model.getText1();
        model.setText1(model.getText2());
        model.setText2(temp);
        temp = null;
    }

    public void onClick_save() {
        modifyModelOperation(mModel);
    }

    public void setModel(DataModel model) {
        mModel = model;
        refreshViewsFromModel();
    }

    private void refreshViewsFromModel() {
        edit1.setText(mModel.getText1());
        edit2.setText(mModel.getText2());
        edit3.setText(String.valueOf(mModel.getText3()));
    }
}
