package it.smdevelopment.iziozi.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import it.smdevelopment.iziozi.R;

public class CreateButtonActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.create_button_activity_layout);
		
		
	}

    public void doSave(View v){
        Toast.makeText(getApplicationContext(),"save!",Toast.LENGTH_SHORT).show();
    }

}