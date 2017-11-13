package my.edu.tarc.lab31toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class MainActivity extends AppCompatActivity {
    private TextView textViewMessage;
    private float size, max = 48, min = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessage = (TextView)findViewById(R.id.textViewMessage);

        //Determine font size in SP = font size in pixel (PX) / screen density
        size = textViewMessage.getTextSize() / getScreenDensity();

        Log.d("Main", "Size " + size);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public float getScreenDensity(){
        float sizeDensity=0;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        sizeDensity = metrics.density;

        return sizeDensity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }else if(id == R.id.action_increase){
            if(size < max)
                size += 1;
            else
                Toast.makeText(getApplicationContext(), getString(R.string.warning_max), Toast.LENGTH_SHORT).show();
            textViewMessage.setText(getString(R.string.hello) + " in size " + size);
            textViewMessage.setTextSize(COMPLEX_UNIT_SP, size);
            return true;
        }else if(id == R.id.action_decrease){
            if(size > min)
                size -= 1;
            else
                Toast.makeText(getApplicationContext(), getString(R.string.warning_min), Toast.LENGTH_SHORT).show();
            textViewMessage.setText(getString(R.string.hello) + " in size " + size);
            textViewMessage.setTextSize(COMPLEX_UNIT_SP, size);
            return true;
        }else if(id == R.id.action_about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
