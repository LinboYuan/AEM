package aem.aemtester.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import aem.aemtester.R;

public class EditActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        sharedPreferences = this.getSharedPreferences(this.getResources().getString(R.string.shared_preferences_name), MODE_PRIVATE);
        Button showMsgBtn = (Button) this.findViewById(R.id.saveBtn);
        msgText = (TextView) EditActivity.this.findViewById(R.id.msgTxt);
        showMsgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(EditActivity.this.getResources().getString(R.string.sp_str_testField), EditActivity.this.msgText.getText().toString());
                editor.commit();
                EditActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}