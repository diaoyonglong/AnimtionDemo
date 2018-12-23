package example.com.animationdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements showMenuDialog.menuCallBack {
    private showMenuDialog menuDialog;
    private Context context;
    private ImageView showMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        menuDialog = new showMenuDialog(context, this, 0);
        showMenu = (ImageView) findViewById(R.id.show_menu);
        showMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.show();
                showMenu.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onPressBtn(int id) {
        showMenu.setVisibility(View.VISIBLE);

            Toast.makeText(context, "----pass>>>"+id, Toast.LENGTH_SHORT).show();

    }
}
