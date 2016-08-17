package silicar.library.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Map;
import java.util.Set;

/**
 * SharedPreferences工具类
 * Created by Work on 2015/8/9.
 * @version 1.0
 * @since 2015/8/9
 * @author 图图
 */
public class PreferencesUtil {

    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public PreferencesUtil(Context context, String fileName) {
        this.mContext = context;
        mPreferences = this.mContext.getSharedPreferences(fileName, this.mContext.MODE_PRIVATE);
    }

    public Map<String, ?> getAll()
    {
        return mPreferences.getAll();
    }

    public boolean getPreferences(String key, boolean defValue)
    {
        return mPreferences.getBoolean(key, defValue);
    }

    public int getPreferences(String key, int defValue)
    {
        return mPreferences.getInt(key, defValue);
    }

    public long getPreferences(String key, long defValue)
    {
        return mPreferences.getLong(key, defValue);
    }

    public float getPreferences(String key, float defValue)
    {
        return mPreferences.getFloat(key, defValue);
    }

    public String getPreferences(String key, String defValue)
    {
        return mPreferences.getString(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getPreferences(String key, Set<String> defValue)
    {
        return mPreferences.getStringSet(key, defValue);
    }

    public PreferencesUtil setPreferences(String key, boolean defValue)
    {
        edit();
        mEditor.putBoolean(key, defValue);
        return this;
    }

    public PreferencesUtil setPreferences(String key, int defValue)
    {
        edit();
        mEditor.putInt(key, defValue);
        return this;
    }

    public PreferencesUtil setPreferences(String key, long defValue)
    {
        edit();
        mEditor.putLong(key, defValue);
        return this;
    }

    public PreferencesUtil setPreferences(String key, float defValue)
    {
        edit();
        mEditor.putFloat(key, defValue);
        return this;
    }

    public PreferencesUtil setPreferences(String key, String defValue)
    {
        edit();
        mEditor.putString(key, defValue);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public PreferencesUtil setPreferences(String key, Set<String> defValue)
    {
        edit();
        mEditor.putStringSet(key, defValue);
        return this;
    }
    
    private void edit(){
        if (mEditor == null) {
            mEditor = mPreferences.edit();
        }
    }
    
    public void commit(){
        mEditor.commit();
        mEditor = null;
    }
}
