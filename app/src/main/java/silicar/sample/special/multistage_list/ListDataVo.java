package silicar.sample.special.multistage_list;

import android.content.Intent;

import java.util.List;

/**
 * Created by Brady on 2016/7/9.
 */
public class ListDataVo {
    String title;
    Intent intent;
    List<ListDataVo> dataVoList;

    public ListDataVo() {
    }

    public ListDataVo(String title, Intent intent, List<ListDataVo> dataVoList) {
        this.title = title;
        this.intent = intent;
        this.dataVoList = dataVoList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<ListDataVo> getDataVoList() {
        return dataVoList;
    }

    public void setDataVoList(List<ListDataVo> dataVoList) {
        this.dataVoList = dataVoList;
    }
}
