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
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.bean.MoviesBean;

public class JiMoviesAdapter extends BaseAdapter {
    private Context mcontext;
    private List<MoviesBean.ResultBean> mlist;

    public JiMoviesAdapter(Context mcontext, List<MoviesBean.ResultBean> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if (convertView==null){
            convertView=View.inflate(mcontext, R.layout.ji_movies_item,null);
            holder=new Viewholder();
            holder.jiImage=convertView.findViewById(R.id.ji_image);
            holder.jiName=convertView.findViewById(R.id.ji_name);
            holder.jiSummary=convertView.findViewById(R.id.ji_summary);
            convertView.setTag(holder);
        }else{
            holder= (Viewholder) convertView.getTag();
        }

        holder.jiName.setText(mlist.get(position).getName());
        holder.jiSummary.setText(mlist.get(position).getSummary());
        ImageLoader.getInstance().displayImage(mlist.get(position).getImageUrl(),holder.jiImage);
        return convertView;
    }
    class Viewholder{
        TextView jiName,jiSummary;
        ImageView jiImage;
    }
}
