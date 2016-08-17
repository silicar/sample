package silicar.library.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 无限高ListView
 * Created by Work on 2015/9/21.
 */
public class InfiniteListView extends ListView {
    private static int maxSize = -1 >>> 2;

    public InfiniteListView(Context context) {
        super(context);
    }

    public InfiniteListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfiniteListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfiniteListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 设置高度为最大值
         */
        int expandSpec = MeasureSpec.makeMeasureSpec( maxSize, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
