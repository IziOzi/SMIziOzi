/*
 * Copyright (c) 2014 Martino Lessio -
 * www.martinolessio.com
 * martino [at] iziozi [dot] org
 *
 *
 * This file is part of the IziOzi project.
 *
 * IziOzi is free software:
 * you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * IziOzi is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with IziOzi.
 * If not, see http://www.gnu.org/licenses/.
 */

package it.smdevelopment.iziozi.gui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import it.smdevelopment.iziozi.R;
import it.smdevelopment.iziozi.core.SMIziOziDatabaseHelper;




public class CreateButtonActivity extends OrmLiteBaseActivity<SMIziOziDatabaseHelper> {

    public final static String IMAGE_FILE = "image_file";

    private SearchView mSearchView;
    private ImageButton mImageButton;
    private EditText mTitleText, mTextText;
    private TextView mTapHereTextView;

    private String mImageFile;
    private String mImageTitle;
    private String mImageText;

    private int mButtonIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {
            mImageTitle = extras.getString(BoardActivity.BUTTON_TITLE);
            mImageFile = extras.getString(BoardActivity.BUTTON_IMAGE_FILE);
            mImageText = extras.getString(BoardActivity.BUTTON_TEXT);
        }

        mButtonIndex = getIntent().getExtras().getInt(BoardActivity.BUTTON_INDEX);
		
		setContentView(R.layout.create_button_activity_layout);

        mImageButton = (ImageButton) findViewById(R.id.CreateButtonImageBtn);
        mTitleText = (EditText) findViewById(R.id.CreateButtonTitleText);
        mTextText = (EditText) findViewById(R.id.CreateButtonTextText);
        mTapHereTextView = (TextView) findViewById(R.id.CreateButtonTapLabel);

        if(mImageTitle != null)
            mTitleText.setText(mImageTitle);

        if(mImageText != null)
            mTextText.setText(mImageText);

        if(mImageFile != null && mImageFile.length() > 0) {
            mImageButton.setImageBitmap(BitmapFactory.decodeFile(mImageFile));
            mTapHereTextView.setVisibility(View.INVISIBLE);
        }

	}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Bundle extras = intent.getExtras();
        String pictoFile = extras.getString(IMAGE_FILE);
        if(pictoFile != null)
        {
            mImageFile = pictoFile;

            mImageButton.setImageBitmap(BitmapFactory.decodeFile(pictoFile));
            mTapHereTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_button, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(! hasFocus)
                    mSearchView.setIconified(true);
            }
        });

        mSearchView = searchView;

        return true;
    }

    public void doSave(View v){
        Intent resultIntent = new Intent();
        if(mImageFile != null)
            resultIntent.putExtra(BoardActivity.BUTTON_IMAGE_FILE, mImageFile);

        mImageTitle = mTitleText.getText().toString();

        if(mImageTitle != null && mImageTitle.length() > 0)
            resultIntent.putExtra(BoardActivity.BUTTON_TITLE, mImageTitle);

        mImageText = mTextText.getText().toString();

        if(mImageText != null && mImageText.length() > 0)
            resultIntent.putExtra(BoardActivity.BUTTON_TEXT, mImageText);

        resultIntent.putExtra(BoardActivity.BUTTON_INDEX, mButtonIndex);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void doTapOnImage(View v){
        mSearchView.setIconified(false);
        mSearchView.requestFocus();
    }


}
