package sample.kouta.recyclerviewsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class a extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scroll_list);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout=findViewById(R.id.toolbarLayout);
        toolbarLayout.setTitle((getString(R.string.toolbar_title)));
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        RecyclerView lvMenu=findViewById(R.id.lvMenu);
        LinearLayoutManager layout=new LinearLayoutManager(a.this);
        lvMenu.setLayoutManager(layout);
        List<Map<String,Object>> menuList=createTeisyokuList();
        RecyclerListAdapter adapter=new RecyclerListAdapter(menuList);
        lvMenu.setAdapter(adapter);
    }


    private class RecyclerListViewHolder extends RecyclerView.ViewHolder{
        public TextView _tvMenuName;
        public TextView _tvMenuPrice;

        public RecyclerListViewHolder(View itemView){
            super(itemView);
            _tvMenuName=itemView.findViewById(R.id.tvMenuName);
            _tvMenuPrice=itemView.findViewById(R.id.tvMenuPrice);
        }
    }

    private class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListViewHolder>{
        private List<Map<String,Object>> _listData;

        public RecyclerListAdapter(List<Map<String,Object>> listData){
            _listData=listData;
        }
        @Override
        public RecyclerListViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater inflater=LayoutInflater.from(a.this);
            View view=inflater.inflate(R.layout.row,parent,false);
            RecyclerListViewHolder holder=new RecyclerListViewHolder(view);
            return holder;
        }
        @Override
        public void onBindViewHolder(RecyclerListViewHolder holder,int position){
            Map<String,Object> item=_listData.get(position);
            String menuName=(String) item.get("name");
            int menuPrice=(Integer) item.get("price");
            String menuPriceStr=String.valueOf(menuPrice);
            holder._tvMenuName.setText(menuName);
            holder._tvMenuPrice.setText(menuPriceStr);
        }
        @Override
        public int getItemCount(){
            return _listData.size();
        }
    }
}
