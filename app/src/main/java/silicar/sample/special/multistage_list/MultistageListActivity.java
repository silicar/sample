package silicar.sample.special.multistage_list;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import silicar.library.adapter.AdapterListener;
import silicar.sample.LauncherActivity;
import silicar.sample.R;
import silicar.sample.databinding.ActivitySampleListBinding;

public class MultistageListActivity extends AppCompatActivity {

    ActivitySampleListBinding binding;
    MultistageListAdapter multistageListAdapter;
    List<ListDataVo> dataVoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_list);
        initData();
        initSampleList();
        initListener();
    }

    /**
     * 初始化例子列表
     */
    private void initData() {
        dataVoList = new ArrayList<>();
        //列子1
        addData(new ListDataVo("Sample1", new Intent(this, LauncherActivity.class), null));
        //2级例子列表2
        List<ListDataVo> voList = new ArrayList<>();
        ListDataVo vo = new ListDataVo("Sample List 1", null, voList);
        addData(vo);
        voList.add(new ListDataVo("Sample2", new Intent(this, LauncherActivity.class), voList));//嵌套列表
        voList.add(new ListDataVo("Sample3", new Intent(this, LauncherActivity.class), null));
        voList.add(new ListDataVo("Sample4", new Intent(this, LauncherActivity.class), null));
        voList.add(new ListDataVo("Sample5", new Intent(this, LauncherActivity.class), null));
    }

    private void addData(ListDataVo dataVo) {
        dataVoList.add(dataVo);
    }

    /**
     * 初始化数据
     */
    private void initSampleList() {
        multistageListAdapter = new MultistageListAdapter(this);
        multistageListAdapter.addList(dataVoList, 1, 0);
        binding.listView.setAdapter(multistageListAdapter.adapter);
    }

    private void initListener() {
        multistageListAdapter.setOnItemClickListener(new AdapterListener.OnItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position) {
                ListDataWrapper<ListDataVo> vo = multistageListAdapter.list.get(position);
                //Log.e("Expand", "-" + vo.isExpand());
                //Log.e("HasChild", "-" + vo.isHasChild());
                if (vo.isHasChild()) {
                    if (!vo.isExpand()) {
                        multistageListAdapter.addList(vo.getChildList(), vo.getLevel() + 1, vo.getPosition());
                    } else {
                        multistageListAdapter.removeList(vo.getLevel(), vo.getPosition());
                    }
                    vo.setExpand(!vo.isExpand());
                    multistageListAdapter.adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}
