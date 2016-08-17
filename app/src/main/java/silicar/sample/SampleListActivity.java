package silicar.sample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import silicar.library.adapter.AdapterListener;
import silicar.sample.databinding.ActivitySampleListBinding;
import silicar.sample.material.MaterialTextInputActivity;
import silicar.sample.net.okhttp.OkHttpActivity;
import silicar.sample.special.multistage_list.MultistageListActivity;

import java.util.ArrayList;
import java.util.List;

public class SampleListActivity extends AppCompatActivity {

    ActivitySampleListBinding binding;
    SampleListAdapter sampleListAdapter;
    List<DataVo> dataVoList;

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
        //特殊控件
        special();
        //网络
        network();
        //Material Design
        material();
        //测试
        test();
    }

    // 特殊控件
    private void special() {
        List<DataVo> voList = new ArrayList<>();
        dataVoList.add(new DataVo("特殊控件", voList));
        voList.add(new DataVo("多级列表", new Intent(this, MultistageListActivity.class)));
    }

    // 网络请求
    private void network() {
        List<DataVo> voList = new ArrayList<>();
        dataVoList.add(new DataVo("网络请求", voList));
        voList.add(new DataVo("OkHttp + Gson", new Intent(this, OkHttpActivity.class)));
    }

    // Material Design
    private void material() {
        List<DataVo> voList = new ArrayList<>();
        dataVoList.add(new DataVo("Material Design", voList));
        voList.add(new DataVo("TextInput", new Intent(this, MaterialTextInputActivity.class)));
    }

    // 临时测试
    private void test() {
        List<DataVo> voList = new ArrayList<>();
        dataVoList.add(new DataVo("测试", voList));
    }

    /**
     * 初始化数据
     */
    private void initSampleList() {
        sampleListAdapter = new SampleListAdapter(this);
        sampleListAdapter.addList(dataVoList, 1, 0);
        binding.listView.setAdapter(sampleListAdapter.adapter);
    }

    private void initListener() {
        sampleListAdapter.setOnItemClickListener(new AdapterListener.OnItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position) {
                DataWrapper<DataVo> vo = sampleListAdapter.list.get(position);
                //Log.e("Expand", "-" + vo.isExpand());
                //Log.e("HasChild", "-" + vo.isHasChild());
                if (vo.isHasChild()) {
                    if (!vo.isExpand()) {
                        sampleListAdapter.addList(vo.getChildList(), vo.getLevel() + 1, vo.getPosition());
                    } else {
                        sampleListAdapter.removeList(vo.getLevel(), vo.getPosition());
                    }
                    vo.setExpand(!vo.isExpand());
                    sampleListAdapter.adapter.notifyDataSetChanged();
                } else {
                    Intent intent = vo.getData().getIntent();
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
    }
}
