package sample.kouta.fragmentsample;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class clas extends Fragment{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
    }
    private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(_parentActivity, MenuListActivity.class);
        }
    }
}
