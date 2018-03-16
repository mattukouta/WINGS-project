import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

import sample.kouta.menusample.R;

/**
 * Created by kouta on 2018/03/16.
 */

public class ax extends AppCompatActivity{

    private static final String[] FROM={"name","price"};
    private static final int [] TO={R.id.tvMenuName,R.id.tvMenuPrice};
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
    }
}
