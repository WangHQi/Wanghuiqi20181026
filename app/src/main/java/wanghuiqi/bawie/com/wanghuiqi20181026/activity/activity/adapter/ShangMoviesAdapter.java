package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.bean.Movies;

public class ShangMoviesAdapter extends BaseAdapter {
    private Context context;
    private List<Movies.ResultBean> list;

    public ShangMoviesAdapter(Context context, List<Movies.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.shang_movies_item,null);
            holder=new Viewholder();
            holder.shangImage=convertView.findViewById(R.id.shang_image);
            holder.shangName=convertView.findViewById(R.id.shang_name);
            holder.shangSummary=convertView.findViewById(R.id.shang_summary);
            convertView.setTag(holder);
        }else{
            holder= (Viewholder) convertView.getTag();
        }

        holder.shangName.setText(list.get(position).getName());
        holder.shangSummary.setText(list.get(position).getSummary());
        ImageLoader.getInstance().displayImage(list.get(position).getImageUrl(),holder.shangImage);
        return convertView;
    }
    class Viewholder{
        TextView shangName,shangSummary;
        ImageView shangImage;
    }
}
