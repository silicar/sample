package silicar.sample.special.multistage_list;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import silicar.library.adapter.CommonAdapter;
import silicar.library.adapter.CommonViewHolder;
import silicar.sample.R;
import silicar.sample.databinding.ItemSampleListBinding;

/**
 * Created by Brady on 2016/7/8.
 */
public class MultistageListAdapter extends ListDataWrapperAdapter {

    Resources r = Resources.getSystem();

    public MultistageListAdapter(Context context) {
        this(context, R.layout.item_sample_list);
    }

    public MultistageListAdapter(Context context, int layoutId) {
        super(context, layoutId);
        adapter = new CommonAdapter<ListDataWrapper>(context, list, layoutId) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, ListDataWrapper item, int position) {
                holder.itemView.setTag(POSITION, position);
                holder.itemView.setOnClickListener(mOnItemClickListener);
                ItemSampleListBinding binding = holder.getBinding();
                switch (item.getLevel()) {
                    case 1:
                        setStatus(binding, item, 24);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                        break;
                    case 2:
                        setStatus(binding, item, 20);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        break;
                    case 3:
                        setStatus(binding, item, 16);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        break;
                    case 4:
                        setStatus(binding, item, 12);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                        break;
                    default:
                        setStatus(binding, item, 12);
                        binding.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                        break;
                }
                ListDataVo dataVo = (ListDataVo) item.getData();
                StringBuilder str = new StringBuilder();
                if (item.getChildList()!= null && !item.getChildList().isEmpty())
                    str.append(item.getLevel()).append("级，列表 ");
                else
                    str.append(item.getLevel()).append("级，条目 ");
                str.append(dataVo.getTitle());
                binding.titleTv.setText(str.toString());
            }
        };
    }

    private void setStatus(ItemSampleListBinding binding, ListDataWrapper item, float size) {
        if (item.getChildList() != null && !item.getChildList().isEmpty()) {
            if (item.isExpand())
                binding.statusIv.setBackgroundResource(R.drawable.draw_unfold_status_24dp);
            else
                binding.statusIv.setBackgroundResource(R.drawable.draw_fold_status_24dp);
        } else {
            binding.statusIv.setBackgroundResource(R.drawable.draw_star_status_24dp);
        }

        binding.statusIv.getLayoutParams().width = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, size, r.getDisplayMetrics());
        binding.statusIv.getLayoutParams().height = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, size, r.getDisplayMetrics());
    }

    @Override
    protected void onItemClick(View view, int position) {
        Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean onItemLongClick(View view, int position) {
        return false;
    }

    @Override
    protected void onChildClick(View view, int position) {

    }

    @Override
    protected boolean onChildLongClick(View view, int position) {
        return false;
    }
}
